package com.example.nobs.product.transactions;

import com.example.nobs.product.Command;
import com.example.nobs.product.Query;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional

public class TransferService implements Command<TransferDTO, String> {
    private final BankAccountRepo bankAccountRepo;

    public TransferService(BankAccountRepo bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }

    @Override
    public ResponseEntity<String> execute(TransferDTO transfer) {
        Optional<BankAccount> fromAccount = bankAccountRepo.findById(transfer.getFromUser());
        Optional<BankAccount> toAccount = bankAccountRepo.findById(transfer.getToUser());
        if(fromAccount.isEmpty() || toAccount.isEmpty())
            throw new RuntimeException("User not found");
        BankAccount from = fromAccount.get();
        BankAccount to = toAccount.get();

        add(to, transfer.getAmount());
        deduct(from, transfer.getAmount());
        return ResponseEntity.ok("Transfer successful");
    }
    private void deduct(BankAccount bankAccount, double amount) {
        if(bankAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }
    private void add(BankAccount bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}
