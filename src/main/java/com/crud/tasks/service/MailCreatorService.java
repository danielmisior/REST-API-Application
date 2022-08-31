package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyConfig companyConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://danielmisior.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("regards", "Best regards,");
        context.setVariable("your_app", "Your Application");
        context.setVariable("company_config", companyConfig);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyTrelloEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context dailyContext = new Context();
        dailyContext.setVariable("message", message);
        dailyContext.setVariable("tasks_button", "Visit TaskApp");
        dailyContext.setVariable("tasks_url", "https://danielmisior.github.io");
        dailyContext.setVariable("trello_button", "Visit Trello");
        dailyContext.setVariable("trello_url", "https://trello.com/danielmisior1/boards");
        dailyContext.setVariable("admin_config", adminConfig);
        dailyContext.setVariable("company_config", companyConfig);
        dailyContext.setVariable("regards", "Best regards,");
        dailyContext.setVariable("your_app", "Your Application");
        dailyContext.setVariable("is_friend", false);
        dailyContext.setVariable("show_tasks_button", true);
        dailyContext.setVariable("show_trello_button", true);
        dailyContext.setVariable("app_functionality", functionality);
        return templateEngine.process("mail/daily-trello-card-mail", dailyContext);
    }
}
