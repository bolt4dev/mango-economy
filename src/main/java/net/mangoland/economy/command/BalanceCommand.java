package net.mangoland.economy.command;


import com.hakan.basicdi.annotations.Autowired;
import com.hakan.spinjection.command.annotations.Command;
import com.hakan.spinjection.command.annotations.CommandParam;
import com.hakan.spinjection.command.annotations.Executor;
import com.hakan.spinjection.command.annotations.Subcommand;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Formatter;
import net.mangoland.economy.config.EconomyLang;
import net.mangoland.economy.model.EconomyUser;
import net.mangoland.economy.service.EconomyService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(
        name = "balance",
        aliases = {"money", "bal"},
        description = "Balance command.",
        usage = "/balance [player]"
)
public class BalanceCommand {

    private final EconomyLang lang;
    private final EconomyService service;

    @Autowired
    public BalanceCommand(EconomyService service,
                          EconomyLang lang) {
        this.service = service;
        this.lang = lang;
    }


    @Subcommand
    public void balance(@Executor Player executor) {
        EconomyUser user = this.service.getByUID(executor.getUniqueId());
        executor.sendMessage(MiniMessage.miniMessage().deserialize(lang.balanceMessage(),
                Formatter.number("amount", user.getBalance())
        ));
    }


    @Subcommand
    public void balance(@Executor CommandSender executor,
                        @CommandParam Player target) {
        EconomyUser user = this.service.getByUID(target.getUniqueId());
        executor.sendMessage(MiniMessage.miniMessage().deserialize(lang.balanceMessageAnother(),
                Formatter.number("amount", user.getBalance())
        ));
    }
}
