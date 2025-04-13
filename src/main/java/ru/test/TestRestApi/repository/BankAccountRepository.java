package ru.test.TestRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.test.TestRestApi.data.BankAccount;

import java.util.UUID;

public interface BankAccountRepository extends CrudRepository<BankAccount, UUID> {
}
