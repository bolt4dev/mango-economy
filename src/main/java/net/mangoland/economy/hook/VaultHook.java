package net.mangoland.economy.hook;

import com.hakan.basicdi.annotations.Autowired;
import com.hakan.basicdi.annotations.Component;
import net.mangoland.economy.EconomyPlugin;
import net.mangoland.economy.config.EconomyConfig;
import net.mangoland.economy.service.EconomyService;
import net.mangoland.economy.util.EconomyUtil;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collections;
import java.util.List;

@Component
public class VaultHook implements Economy {
    private final EconomyConfig config;
    private final EconomyService service;

    @Autowired
    public VaultHook(EconomyConfig config,
                     EconomyService service) {
        this.config = config;
        this.service = service;
    }
    @Override
    public boolean isEnabled() {
        return EconomyPlugin.getInstance().isEnabled();
    }

    @Override
    public String getName() {
        return EconomyPlugin.getInstance().getName();
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return EconomyUtil.formatCurrency(amount);
    }

    @Override
    public String currencyNamePlural() {
        return config.currencyPluralName();
    }

    @Override
    public String currencyNameSingular() {
        return config.currencySingularName();
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return service.hasAccount(player.getUniqueId());
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return service.getByUID(player.getUniqueId()).getBalance();
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return  service.getByUID(player.getUniqueId()).hasEnoughMoney(amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        return this.service.getByUID(player.getUniqueId()).decreaseBalance(amount)
                ? new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, "Successful")
                : new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.FAILURE, "Failed to withdraw");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        return this.service.getByUID(player.getUniqueId()).increaseBalance(amount)
                ? new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, "Successful")
                : new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.FAILURE, "Failed to deposit");
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return true;
    }

    //region Expanded Methods
    @Override
    public boolean hasAccount(String playerName) {
        return hasAccount(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return hasAccount(player);
    }

    @Override
    public double getBalance(String playerName) {
        return getBalance(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public double getBalance(String playerName, String world) {
        return getBalance(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return getBalance(player);
    }

    @Override
    public boolean has(String playerName, double amount) {
        return has(Bukkit.getOfflinePlayer(playerName), amount);
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return has(player, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return withdrawPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return withdrawPlayer(player, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return depositPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return depositPlayer(player, amount);
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return createPlayerAccount(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return createPlayerAccount(player);
    }
    //endregion

    //region Bank (unused)
    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return Collections.emptyList();
    }
}
