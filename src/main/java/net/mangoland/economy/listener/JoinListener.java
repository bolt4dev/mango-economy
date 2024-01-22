package net.mangoland.economy.listener;

import com.hakan.basicdi.annotations.Autowired;
import com.hakan.basicdi.annotations.Component;
import com.hakan.spinjection.listener.annotations.EventListener;
import net.mangoland.economy.model.transaction.EconomyTransaction;
import net.mangoland.economy.model.user.EconomyUser;
import net.mangoland.economy.repository.EconomyRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;
import java.util.UUID;

@Component
public class JoinListener {
    private final EconomyRepository repository;
    @Autowired
    public JoinListener(EconomyRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        EconomyUser u = new EconomyUser(player.getUniqueId(), "test", 100);
        EconomyUser v = new EconomyUser(player.getUniqueId(), "test2", 50);
        this.repository.save(u);
        this.repository.save(v);
    }

}
