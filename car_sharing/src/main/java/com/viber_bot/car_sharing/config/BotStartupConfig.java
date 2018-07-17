package com.viber_bot.car_sharing.config;

import com.viber.bot.api.ViberBot;
import com.viber_bot.car_sharing.service.ViberBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class BotStartupConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Inject
    private ViberBot viberBot;

    @Value("${application.viber-bot.webhook-url}")
    private String webhook;

    @Autowired
    private ViberBotService viberBotService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            viberBot.setWebhook(webhook).get();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("USAO U BOT STARTUP CONFIG!!!!");
        viberBot.onMessageReceived((event, message, response) -> viberBotService.onMessageReceived(event, message, response));
        viberBot.onConversationStarted(event -> viberBotService.onConversationStarted(event));
        viberBot.onSubscribe((event, response) -> viberBotService.onSubscribe(event, response));
        viberBot.onUnsubscribe((event) -> viberBotService.onUnsubscribe(event));
    }
}
