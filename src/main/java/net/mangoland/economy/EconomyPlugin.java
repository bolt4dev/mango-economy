package net.mangoland.economy;

import com.hakan.core.HCore;
import com.hakan.core.plugin.Plugin;
import com.hakan.spinjection.SpigotBootstrap;
import com.hakan.spinjection.annotations.Scanner;
import net.mangoland.economy.hook.VaultHook;
import net.mangoland.economy.util.EconomyUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

@Plugin(
        name = "MangoEconomy",
        version = "1.0.0",
        apiVersion = "1.20",
        authors = "bolt4dev"
)
@Scanner("net.mangoland.economy")
public class EconomyPlugin extends JavaPlugin {

    private static EconomyPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        HCore.initialize(this);
        SpigotBootstrap.run(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling MangoEconomy.");
    }

    public static EconomyPlugin getInstance() {
        return instance;
    }
}
