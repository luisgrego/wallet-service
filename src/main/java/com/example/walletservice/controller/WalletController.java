package com.example.walletservice.controller;

import com.example.walletservice.model.Wallet;
import com.example.walletservice.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/create")
    public Wallet createWallet(@RequestParam String userId) {
        return walletService.createWallet(userId);
    }

    @GetMapping("/{userId}")
    public Optional<Wallet> getWallet(@PathVariable String userId) {
        return walletService.getWalletByUserId(userId);
    }

    @PostMapping("/deposit")
    public void deposit(@RequestParam String userId, @RequestParam BigDecimal amount) {
        walletService.deposit(userId, amount);
    }

    @PostMapping("/withdraw")
    public boolean withdraw(@RequestParam String userId, @RequestParam BigDecimal amount) {
        return walletService.withdraw(userId, amount);
    }

    @PostMapping("/transfer")
    public boolean transfer(@RequestParam String fromUserId, @RequestParam String toUserId, @RequestParam BigDecimal amount) {
        return walletService.transfer(fromUserId, toUserId, amount);
    }
}
