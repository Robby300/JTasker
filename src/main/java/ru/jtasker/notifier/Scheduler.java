package ru.jtasker.notifier;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.jtasker.service.NotifierService;

@Component
public class Scheduler {

    private final NotifierService notifierService;

    public Scheduler(NotifierService notifierService) {
        this.notifierService = notifierService;
    }

    @Scheduled(fixedRate = 10_000)
    public void sendNotification() {
        notifierService.notifyBeforeOneHour();
    }
}
