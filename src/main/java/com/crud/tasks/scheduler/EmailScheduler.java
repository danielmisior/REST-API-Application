package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private final TrelloFacade trelloFacade;
    private static final String SUBJECT = "Tasks: Once a day email";
    private static final String SUBJECT_INFO = "Application: Information email";

    @Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        if(size == 1) {
            simpleEmailService.send(
                    new Mail(
                            adminConfig.getAdminMail(),
                            null,
                            SUBJECT,
                            "Currently in database you got: " + size + " task"
                    )
            );
        } else {
            simpleEmailService.send(
                    new Mail(
                            adminConfig.getAdminMail(),
                            null,
                            SUBJECT,
                            "Currently in database you got: " + size + " tasks"
                    )
            );
        }
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void sendDailyTrelloCardEmail() {
        long taskAmount = taskRepository.count();
        long trelloAmount = trelloFacade.fetchTrelloBoards().size();
        if (taskAmount == 1 && trelloAmount == 1) {
            simpleEmailService.sendDailyEmail(
                    new Mail(
                            adminConfig.getAdminMail(),
                            null,
                            SUBJECT_INFO,
                            "You have " + taskAmount + " task in your database and "
                                    + trelloAmount + " Trello board"
                    )
            );
        } else {
            simpleEmailService.sendDailyEmail(
                    new Mail(
                            adminConfig.getAdminMail(),
                            null,
                            SUBJECT_INFO,
                            "You have " + taskAmount + " tasks in your database and "
                                    + trelloAmount + " Trello boards"
                    )
            );
        }
    }
}