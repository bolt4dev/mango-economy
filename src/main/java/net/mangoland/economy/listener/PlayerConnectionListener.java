package net.mangoland.economy.listener;

import com.hakan.basicdi.annotations.Autowired;
import com.hakan.basicdi.annotations.Component;
import com.hakan.spinjection.listener.annotations.EventListener;
import net.mangoland.economy.service.EconomyService;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

@Component
public class PlayerConnectionListener {
    private final EconomyService service;
    @Autowired
    public PlayerConnectionListener(EconomyService service) {
        this.service = service;
    }

    @EventListener
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (this.service.existByUID(player.getUniqueId())) {
            if (!Objects.equals(this.service.getByUID(player.getUniqueId()).getName(), player.getName()))
                this.service.getByUID(player.getUniqueId()).setName(player.getName());
        } else {
            this.service.load(player);
            System.out.println("load player");
        }
    }



    @EventListener
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.service.save(player);
    }

}
