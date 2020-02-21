package kd.dev.transactionspringbatch.processor;

import kd.dev.transactionspringbatch.entities.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class TransactionProcessor implements ItemProcessor<Transaction, Transaction> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    @Override
    public Transaction process(Transaction transaction) throws Exception {
        transaction.setTransactionDate(dateFormat.parse(transaction.getStrTransactionDate()));
        return transaction;
    }
}
