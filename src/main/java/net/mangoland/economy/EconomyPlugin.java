package net.mangoland.economy;

import com.hakan.core.HCore;
import com.hakan.core.plugin.Plugin;
import com.hakan.spinjection.SpigotBootstrap;
import com.hakan.spinjection.annotations.Scanner;
import org.bukkit.plugin.java.JavaPlugin;

@Plugin(
        name = "MangoEconomy",
        version = "1.0.0",
        apiVersion = "1.20",
        authors = "graphxf"
)
@Scanner("net.mangoland.economy")
public class EconomyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        HCore.initialize(this);
        SpigotBootstrap.run(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling MangoEconomy");
    }
}