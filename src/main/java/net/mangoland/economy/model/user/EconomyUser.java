package net.mangoland.economy.model.user;

import jakarta.persistence.*;

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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }


    // HANDLERS

    public void transact(EconomyUser target, double amount) {}
}
