package kd.dev.transactionspringbatch.dao;

import kd.dev.transactionspringbatch.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
