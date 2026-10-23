package org.example.businesslogic.banktransfer.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.banktransfer.dto.TransferRequest;
import org.example.businesslogic.banktransfer.model.Account;
import org.example.businesslogic.banktransfer.model.Transfer;
import org.example.businesslogic.banktransfer.model.TransferStatus;
import org.example.businesslogic.banktransfer.repository.AccountRepository;
import org.example.businesslogic.banktransfer.repository.TransferRepository;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferService {

    private static final BigDecimal VERIFICATION_LIMIT = new BigDecimal("5000");

    // for the demo we use a fixed code, a real bank would send it by SMS
    private static final String VERIFICATION_CODE = "1234";

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return findAccount(id);
    }

    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }

    // synchronized so two transfers can't change the same balance at the same time
    public synchronized Transfer transfer(TransferRequest request) {
        Account from = findAccount(request.fromAccountId());
        Account to = findAccount(request.toAccountId());

        Transfer transfer = new Transfer();
        transfer.setFromAccountId(from.getId());
        transfer.setToAccountId(to.getId());
        transfer.setAmount(request.amount());
        transfer.setCreatedAt(LocalDateTime.now());

        try {
            validate(request, from, to);
        } catch (BusinessException ex) {
            // save the failed transfer for history, balances stay the same
            transfer.setStatus(TransferStatus.FAILED);
            transfer.setFailureReason(ex.getMessage());
            transferRepository.save(transfer);
            throw ex;
        }

        // all checks passed, now update both accounts
        from.setBalance(from.getBalance().subtract(request.amount()));
        to.setBalance(to.getBalance().add(request.amount()));
        accountRepository.save(from);
        accountRepository.save(to);

        transfer.setStatus(TransferStatus.SUCCESS);
        return transferRepository.save(transfer);
    }

    private void validate(TransferRequest request, Account from, Account to) {
        BigDecimal amount = request.amount();

        if (from.getId().equals(to.getId())) {
            throw new BusinessException("Sender and receiver must be different accounts");
        }
        if (!from.isActive()) {
            throw new BusinessException("Sender account is not active");
        }
        if (!to.isActive()) {
            throw new BusinessException("Receiver account is not active");
        }
        if (from.getBalance().compareTo(amount) < 0) {
            throw new BusinessException("Not enough balance");
        }

        BigDecimal sentToday = transferRepository.sumSuccessfulTransfersFromAccount(from.getId(), LocalDate.now());
        BigDecimal dailyLimit = from.getType().getDailyLimit();
        if (sentToday.add(amount).compareTo(dailyLimit) > 0) {
            throw new BusinessException("Daily limit for " + from.getType() + " account is " + dailyLimit
                    + ", already sent today " + sentToday);
        }

        boolean needsVerification = amount.compareTo(VERIFICATION_LIMIT) > 0;
        if (needsVerification && !VERIFICATION_CODE.equals(request.verificationCode())) {
            throw new BusinessException("Transfers above " + VERIFICATION_LIMIT + " need a valid verification code");
        }
    }

    private Account findAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account " + id + " not found"));
    }
}
