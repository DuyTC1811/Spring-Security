package com.example.springsecurity.configuration.datasources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.example.springsecurity.configuration.datasources.DataSourceType.READ_ONLY;
import static com.example.springsecurity.configuration.datasources.DataSourceType.READ_WRITE;

public class TransactionRoutingDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager
                .isCurrentTransactionReadOnly() ? READ_ONLY : READ_WRITE;
    }
}
