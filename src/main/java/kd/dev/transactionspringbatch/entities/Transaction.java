package kd.dev.transactionspringbatch.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long accountID;
    private Date transactionDate;
    @Transient
    private String strTransactionDate;
    private String transactionType;
    private double amount;

    public Transaction(long accountID, Date transactionDate, String strTransactionDate, String transactionType, double amount) {
        this.accountID = accountID;
        this.transactionDate = transactionDate;
        this.strTransactionDate = strTransactionDate;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStrTransactionDate() {
        return strTransactionDate;
    }

    public void setStrTransactionDate(String strTransactionDate) {
        this.strTransactionDate = strTransactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
