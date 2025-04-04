package com.henrique.banc.notification.domain;

import com.henrique.banc.notification.application.NotificationProducer;
import com.henrique.banc.transaction.domain.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationProducer notificationProducer;

    public void notify(Transaction transaction) {
        notificationProducer.sendNotification(transaction);
    }
}
