package net.mangoland.economy.service;

import com.hakan.basicdi.annotations.Autowired;
import com.hakan.basicdi.annotations.Service;
import net.mangoland.economy.cache.EconomyCache;
import net.mangoland.economy.config.EconomyConfig;
import net.mangoland.economy.model.EconomyUser;
import net.mangoland.economy.repository.EconomyRepository;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

@Service
public class EconomyService {

    private final EconomyCache cache;
    private final EconomyConfig config;
    private final EconomyRepository repository;

    @Autowired
    public EconomyService(EconomyCache cache,
                          EconomyConfig config,
                          EconomyRepository repository) {
        this.cache = cache;
        this.config = config;
        this.repository = repository;
    }

    public EconomyUser getByID(Integer id) {
        return this.cache.getByID(id);
    }

    public EconomyUser getByUID(UUID uid) {
        return this.cache.getByUID(uid);
    }


    public Optional<EconomyUser> findByID(Integer id) {
        return this.cache.findByID(id);
    }

    public Optional<EconomyUser> findByUID(UUID uid) {
        return this.cache.findByUID(uid);
    }


    public boolean existByID(Integer id) {
        return this.cache.existByID(id);
    }

    public boolean existByUID(UUID uid) {
        return this.cache.existByUID(uid);
    }

    public boolean hasAccount(Integer id) {
        return this.repository.findById(id) != null;
    }

    public boolean hasAccount(UUID uid) {
        return this.repository.findByUID(uid) != null;
    }


    public void load(Player player) {
        EconomyUser user = this.repository.findByUID(player.getUniqueId()) != null ?
                this.repository.findByUID(player.getUniqueId()) :
                new EconomyUser(player, this.config.currencyInitialAmount());
        this.cache.add(user);
    }

    public void save(Player player) {
        EconomyUser user = this.getByUID(player.getUniqueId());
        this.cache.remove(user);
        this.repository.save(user);
    }
}
