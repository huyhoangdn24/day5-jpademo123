package service;

import entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    private JpaTransactionManager transactionManager;

    // Dependency injection via constructor
    public AccountService(JpaTransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }
    public   void transferMoney(int sourceAccountId, int targetAccountId,double amount){
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            AccountEntity sourceAccount = accountRepository.findById(sourceAccountId).get();
            AccountEntity targetAccount = accountRepository.findById(targetAccountId).get();
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            sourceAccount.setBalance(targetAccount.getBalance() - amount);
            accountRepository.save(sourceAccount);
            accountRepository.save(targetAccount);
            transactionManager.commit(status);

        }catch (Exception exception){
            transactionManager.rollback(status);
            throw new RuntimeException(exception);
        }
    }
}
