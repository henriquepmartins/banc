package com.henrique.banc.authorization.domain;

import com.henrique.banc.shared.utils.exceptions.UnauthorizedTransactionException;
import com.henrique.banc.transaction.domain.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthorizerService {
    private static final Logger LOGGER = Logger.getLogger(AuthorizerService.class.getName());

    private final RestClient restClient;

    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://run.mocky.io/v3/b4ba36ac-ee03-4f92-bd9e-fdadc696de81")
                .build();
    }

    public void authorize(Transaction transaction) {
        LOGGER.info("Authorizing transaction: " + transaction);
        var response = restClient.get()
                .retrieve()
                .toEntity(Authorization.class);

        if (response.getStatusCode().isError() || !response.getBody().isAuthorized()) {
            LOGGER.info("Transaction not authorized: " + transaction);
            throw new UnauthorizedTransactionException("Unauthorized Transaction - %s".formatted(transaction));
        }

        LOGGER.info("Transaction authorized: " + transaction);
    }
}
