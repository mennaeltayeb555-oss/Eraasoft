package org.example.businesslogic.banktransfer.repository;

import org.example.businesslogic.banktransfer.model.Transfer;
import org.example.businesslogic.banktransfer.model.TransferStatus;
import org.example.businesslogic.common.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public class TransferRepository extends InMemoryRepository<Transfer> {

    public BigDecimal sumSuccessfulTransfersFromAccount(Long accountId, LocalDate date) {
        return findAll().stream()
                .filter(t -> t.getFromAccountId().equals(accountId))
                .filter(t -> t.getStatus() == TransferStatus.SUCCESS)
                .filter(t -> t.getCreatedAt().toLocalDate().equals(date))
                .map(Transfer::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
