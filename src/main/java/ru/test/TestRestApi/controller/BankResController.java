package ru.test.TestRestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.test.TestRestApi.data.BankAccount;
import ru.test.TestRestApi.data.OperationType;
import ru.test.TestRestApi.repository.BankAccountRepository;

import java.util.*;

@RequestMapping(path = "/api/v1/wallet")
@RestController
public class BankResController {
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankResController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
        initializeData();
    }

    @Transactional
    private void initializeData() {
        if (bankAccountRepository.count() == 0) {
            List<BankAccount> accounts = Arrays.asList(
                new BankAccount(OperationType.DEPOSIT, 1000),
                new BankAccount(OperationType.WITHDRAW, 2000),
                new BankAccount(OperationType.DEPOSIT, 3000)
            );
            bankAccountRepository.saveAll(accounts);
        }
    }

    @GetMapping
    @Transactional(readOnly = true)
    public Iterable<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping(path = "/{walletId}")
    @Transactional(readOnly = true)
    public Optional<BankAccount> getAccountById(@PathVariable UUID walletId) {
        return bankAccountRepository.findById(walletId);
    }

    @GetMapping(path = "/{walletId}/amount")
    @Transactional(readOnly = true)
    public int getAmount(@PathVariable UUID walletId) {
        return bankAccountRepository.findById(walletId)
                .map(BankAccount::getAmount)
                .orElse(0);
    }

    @PostMapping
    @Transactional
    public BankAccount postBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @PostMapping(path = "/batch")
    @Transactional
    public Iterable<BankAccount> postBankAccounts(@RequestBody List<BankAccount> bankAccounts) {
        return bankAccountRepository.saveAll(bankAccounts);
    }

    @PutMapping(path = "/{walletId}")
    @Transactional
    public ResponseEntity<BankAccount> putBankAccount(@PathVariable UUID walletId, @RequestBody BankAccount bankAccount) {
        try {
            if (!bankAccountRepository.existsById(walletId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            BankAccount existingAccount = bankAccountRepository.findById(walletId).get();
            existingAccount.setAmount(bankAccount.getAmount());
            return new ResponseEntity<>(bankAccountRepository.save(existingAccount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{walletId}")
    @Transactional
    public void deleteBankAccount(@PathVariable UUID walletId) {
        bankAccountRepository.deleteById(walletId);
    }
}
