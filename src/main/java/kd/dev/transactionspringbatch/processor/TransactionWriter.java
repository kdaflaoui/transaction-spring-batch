package kd.dev.transactionspringbatch.processor;

import kd.dev.transactionspringbatch.dao.TransactionRepository;
import kd.dev.transactionspringbatch.entities.Transaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionWriter implements ItemWriter<Transaction> {

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public void write(List<? extends Transaction> list) throws Exception {
        transactionRepository.saveAll(list);
    }
}
