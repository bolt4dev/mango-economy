package net.mangoland.economy.command;

import com.hakan.basicdi.annotations.Autowired;
import com.hakan.spinjection.command.annotations.Command;
import com.hakan.spinjection.command.annotations.CommandParam;
import com.hakan.spinjection.command.annotations.Executor;
import com.hakan.spinjection.command.annotations.Subcommand;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Formatter;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.mangoland.economy.config.EconomyLang;
import net.mangoland.economy.model.user.EconomyUser;
import net.mangoland.economy.service.EconomyService;
import org.bukkit.entity.Player;

@Command(
        name = "pay",
        aliases = {"transact"},
        description = "Money transfer command.",
        usage = "/pay <target> <amount>"
)
public class PayCommand {
    private final EconomyService service;
    private final EconomyLang lang;

    @Autowired
    public PayCommand(EconomyService service,
                          EconomyLang lang) {
        this.service = service;
        this.lang = lang;
    }



    @Subcommand
    public void pay(@Executor Player executor,
                    @CommandParam Player target,
                    @CommandParam double amount) {

        EconomyUser user = this.service.getByUID(executor.getUniqueId());
        EconomyUser targetUser = this.service.getByUID(target.getUniqueId());
        double correctAmount = Math.abs(amount);
        if (user.hasEnoughMoney(correctAmount)) {
            user.decreaseBalance(correctAmount);
            targetUser.increaseBalance(correctAmount);
            executor.sendMessage(MiniMessage.miniMessage().deserialize(lang.transactionSuccess(),
                    Formatter.number("amount", correctAmount),
                    Placeholder.unparsed("player", executor.getName()),
                    Placeholder.unparsed("target", target.getName())
            ));
            target.sendMessage(MiniMessage.miniMessage().deserialize(lang.transactionReceived(),
                    Formatter.number("amount", correctAmount),
                    Placeholder.unparsed("sender", executor.getName())
            ));
        } else {
            executor.sendMessage(MiniMessage.miniMessage().deserialize(lang.transactionFail(),
                    Formatter.number("amount", correctAmount),
                    Placeholder.unparsed("player", executor.getName()),
                    Placeholder.unparsed("target", target.getName())
            ));
        }
    }
}
