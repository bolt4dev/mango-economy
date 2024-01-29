package net.mangoland.economy.module;

import com.hakan.basicdi.annotations.Runner;
import com.hakan.spinjection.module.PluginModule;
import net.mangoland.economy.EconomyPlugin;
import net.mangoland.economy.hook.VaultHook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

public class VaultModule extends PluginModule {
    @Runner
    public Runnable run(VaultHook hook) {
        return () -> Bukkit.getServicesManager().register(Economy.class, hook, EconomyPlugin.getInstance(), ServicePriority.High);
    }
}
