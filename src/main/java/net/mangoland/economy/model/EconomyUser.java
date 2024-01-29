package net.mangoland.economy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.bukkit.entity.Player;

import java.util.UUID;

@Entity
@Table(name = "economy_users")
public class EconomyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "balance", nullable = false)
    private double balance;



    public EconomyUser() {

    }

    public EconomyUser(Player player, double balance) {
        this(player.getUniqueId(), player.getName(), balance);
    }

    public EconomyUser(UUID uid, String name, double balance) {
        this.uid = uid;
        this.name = name;
        this.balance = balance;
    }

    public Integer getId() {
        return this.id;
    }

    public UUID getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = Math.max(balance, 0);
    }


    // HANDLERS
    public boolean hasEnoughMoney(double amount) {
        return this.balance >= Math.abs(amount);
    }

    public boolean increaseBalance(double amount) {
        this.balance += Math.abs(amount);
        return true;
    }

    public boolean decreaseBalance(double amount) {
        this.balance = Math.max(this.balance - Math.abs(amount), 0);
        return true;
    }

    public boolean decreaseBalanceWithCheck(double amount) {
        if(this.hasEnoughMoney(amount)) {
            this.balance = this.balance - Math.abs(amount);
            return true;
        } else {
            return false;
        }
    }
}
