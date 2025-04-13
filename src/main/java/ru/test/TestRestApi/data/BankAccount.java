package ru.test.TestRestApi.data;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID walletId;
    
    private OperationType operationType;
    private int amount;

    public BankAccount() {
    }

    public BankAccount(UUID id, OperationType operationType, int amount) {
        this.walletId = id;
        this.operationType = operationType;
        this.amount = amount;
    }

    public BankAccount(OperationType operationType, int amount) {
        this.operationType = operationType;
        this.amount = amount;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
