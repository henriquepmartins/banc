package com.henrique.banc.notification.application;

import com.henrique.banc.notification.domain.Notification;
import com.henrique.banc.shared.utils.exceptions.NotificationException;
import com.henrique.banc.transaction.domain.Transaction;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service

public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc").build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "banc")
    public void receiveNotification(Transaction transaction) {
        LOGGER.info("Received transaction: {}", transaction);

        var response = restClient.get().retrieve().toEntity(Notification.class);

        if (response.getStatusCode().isError() || !response.getBody().message()) {
            throw new NotificationException("Error sending notification");
        }
    }
}
