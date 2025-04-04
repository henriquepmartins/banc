package com.henrique.banc.notification.application;

import com.henrique.banc.notification.domain.Notification;
import com.henrique.banc.transaction.domain.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public void sendNotification(Transaction transaction) {
        kafkaTemplate.send("transaction-notification", transaction);
    }
}
