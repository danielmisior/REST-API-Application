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
    private String taskWord;
    private String boardWord;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();

        taskWord = size == 1 ? " task" : " tasks";
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        null,
                        SUBJECT,
                        "Currently in database you got: " + size + taskWord
                )
        );
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void sendScheduledEmail() {
        long taskAmount = taskRepository.count();
        long trelloAmount = trelloFacade.fetchTrelloBoards().size();

        taskWord = taskAmount == 1 ? " task" : " tasks";
        boardWord = trelloAmount == 1 ? " board" : " boards";
        simpleEmailService.sendDailyEmail(
                new Mail(
                        adminConfig.getAdminMail(),
                        null,
                        SUBJECT_INFO,
                        "You have " + taskAmount + taskWord + " in your database and " +
                                trelloAmount + " Trello" + boardWord
                )
        );
    }
}