package net.mangoland.economy.model.transaction;

import jakarta.persistence.*;
import net.mangoland.economy.model.user.EconomyUser;


public class EconomyTransaction {

    private int id;
    private EconomyUser sender;
    private double amount;


    public EconomyTransaction() {}

    public EconomyTransaction(EconomyUser sender, double amount) {
        this.amount = amount;
        this.sender = sender;
    }
}
