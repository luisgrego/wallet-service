package com.example.walletservice.service;

import com.example.walletservice.model.Wallet;
import com.example.walletservice.repository.WalletRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(String userId) {
        Wallet wallet = new Wallet(userId, BigDecimal.ZERO);
        return walletRepository.save(wallet);
    }

    public Optional<Wallet> getWalletByUserId(String userId) {
        return Optional.ofNullable(walletRepository.findByUserId(userId));
    }

    public void deposit(String userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet != null) {
            wallet.setBalance(wallet.getBalance().add(amount));
            walletRepository.save(wallet);
        }
    }

    public boolean withdraw(String userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet != null && wallet.getBalance().compareTo(amount) >= 0) {
            wallet.setBalance(wallet.getBalance().subtract(amount));
            walletRepository.save(wallet);
            return true;
        }
        return false;
    }

    public boolean transfer(String fromUserId, String toUserId, BigDecimal amount) {
        Wallet sender = walletRepository.findByUserId(fromUserId);
        Wallet receiver = walletRepository.findByUserId(toUserId);

        if (sender != null && receiver != null && sender.getBalance().compareTo(amount) >= 0 && !fromUserId.equals(toUserId)) {
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
            walletRepository.save(sender);
            walletRepository.save(receiver);
            return true;
        }
        return false;
    }
}
