package net.mangoland.economy.command;

import com.hakan.basicdi.annotations.Autowired;
import com.hakan.spinjection.command.annotations.Command;
import com.hakan.spinjection.command.annotations.CommandParam;
import com.hakan.spinjection.command.annotations.Executor;
import com.hakan.spinjection.command.annotations.Subcommand;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Formatter;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.mangoland.economy.config.EconomyConfig;
import net.mangoland.economy.config.EconomyLang;
import net.mangoland.economy.model.user.EconomyUser;
import net.mangoland.economy.service.EconomyService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(
        name = "economy",
        aliases = {"eco"},
        description = "Economy main command.",
        usage = "/eco help"
)
public class EconomyCommand {

    private final EconomyService service;
    private final EconomyLang lang;
    private final EconomyConfig config;

    @Autowired
    public EconomyCommand(EconomyService service,
                          EconomyLang lang,
                          EconomyConfig config) {
        this.service = service;
        this.lang = lang;
        this.config = config;
    }



    @Subcommand(
            permission = "economy.help",
            permissionMessage = "§cYou don't have permission to use this command."
    )
    public void help(@Executor CommandSender executor) {
        executor.sendMessage(MiniMessage.miniMessage().deserialize(
                "<green>eco give [player] [amount] <yellow>-> <blue>give amount of money to target player."
        ));
        executor.sendMessage(MiniMessage.miniMessage().deserialize(
                "<green>eco set [player] [amount] <yellow>-> <blue>set target player's money to amount."
        ));
        executor.sendMessage(MiniMessage.miniMessage().deserialize(
                "<green>eco remove [player] [amount] <yellow>-> <blue>remove amount of money from target player."
        ));
        executor.sendMessage(MiniMessage.miniMessage().deserialize(
                "<green>eco reload <yellow>-> <blue>reload config."
        ));
    }



    @Subcommand(
            permission = "economy.set",
            permissionMessage = "§cYou don't have permission to use this command."
    )
    public void set(@Executor CommandSender executor,
                    @CommandParam("set") String arg,
                        @CommandParam Player target,
                        @CommandParam double amount) {
        EconomyUser user = this.service.getByUID(target.getUniqueId());
        user.setBalance(amount);
        executor.sendMessage(MiniMessage.miniMessage().deserialize(lang.adminSetBalance(),
                Formatter.number("amount", amount),
                Placeholder.unparsed("target", target.getName())
        ));
    }


    @Subcommand(
            permission = "economy.give",
            permissionMessage = "§cYou don't have permission to use this command."
    )
    public void give(@Executor CommandSender executor,
                    @CommandParam("give") String arg,
                    @CommandParam Player target,
                    @CommandParam double amount) {
        EconomyUser user = this.service.getByUID(target.getUniqueId());
        user.increaseBalance(amount);
        executor.sendMessage(MiniMessage.miniMessage().deserialize(lang.adminGiveBalance(),
                Formatter.number("amount", amount),
                Placeholder.unparsed("target", target.getName())
        ));
    }



    @Subcommand(
            permission = "economy.remove",
            permissionMessage = "§cYou don't have permission to use this command."
    )
    public void remove(@Executor CommandSender executor,
                       @CommandParam("remove") String arg,
                     @CommandParam Player target,
                     @CommandParam double amount) {
        EconomyUser user = this.service.getByUID(target.getUniqueId());
        user.decreaseBalance(amount);
        executor.sendMessage(MiniMessage.miniMessage().deserialize(lang.adminRemoveBalance(),
                Formatter.number("amount", amount),
                Placeholder.unparsed("target", target.getName())
        ));
    }


    @Subcommand(
            permission = "economy.reload",
            permissionMessage = "§cYou don't have permission to use this command."
    )
    public void reload(@Executor CommandSender executor,
                       @CommandParam("reload") String arg) {
        this.lang.reload();
        this.config.reload();
        executor.sendMessage("§aMango Economy has been reloaded.");
    }
}
