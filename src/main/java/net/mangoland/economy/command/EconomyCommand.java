package net.mangoland.economy.command;


import com.hakan.basicdi.annotations.Autowired;
import com.hakan.spinjection.command.annotations.Command;
import com.hakan.spinjection.command.annotations.CommandParam;
import com.hakan.spinjection.command.annotations.Executor;
import com.hakan.spinjection.command.annotations.Subcommand;
import net.mangoland.economy.service.EconomyService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(
        name = "economy",
        aliases = {"eco"},
        description = "Economy main command",
        usage = "/eco"
)
public class EconomyCommand {
    private final EconomyService service;

    @Autowired
    public EconomyCommand(EconomyService service) {
        this.service = service;
    }

    @Subcommand(
            permission = "economy.eco",
            permissionMessage = "You don't have permission to use this command!"
    )
    public void execute(@Executor Player executor) {
        executor.sendMessage(this.service.getByUID(executor.getUniqueId()).getBalance()+ "");
        this.service.getByUID(executor.getUniqueId()).setBalance(100.00);
    }
}
