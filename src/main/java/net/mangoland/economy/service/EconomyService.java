package net.mangoland.economy.service;

import com.hakan.basicdi.annotations.Autowired;
import com.hakan.basicdi.annotations.Service;
import net.mangoland.economy.cache.EconomyCache;
import net.mangoland.economy.config.EconomyConfig;
import net.mangoland.economy.model.user.EconomyUser;
import net.mangoland.economy.repository.EconomyRepository;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

@Service
public class EconomyService {
    private final EconomyRepository repository;
    private final EconomyCache cache;
    private final EconomyConfig config;

    @Autowired
    public EconomyService(EconomyRepository repository,
                          EconomyCache cache,
                          EconomyConfig config) {
        this.repository = repository;
        this.cache = cache;
        this.config = config;
    }

    public EconomyUser getByID(Integer id) {
        return this.cache.getByID(id);
    }
    public EconomyUser getByUID(UUID uid) {
        return this.cache.getByUID(uid);
    }


    public Optional<EconomyUser> findByUID(UUID uid) {
        return this.cache.findByUID(uid);
    }
    public boolean existByUID(UUID uid) {
        return this.cache.existByUID(uid);
    }

    public boolean existByID(Integer id) {
        return this.cache.existByID(id);
    }

    public void add(EconomyUser user) {
        this.cache.add(user);
    }

    public void remove(EconomyUser user) {
        this.cache.remove(user);
    }

    public EconomyUser load(Player player) {
        for (EconomyUser user : this.repository.findAll()) {
            if (user.getUid().equals(player.getUniqueId())) {
                System.out.println(user.getBalance() + "founddddd");
                add(user);
                return user;
            }
        }
        System.out.println("new player");
        EconomyUser user = new EconomyUser(player.getUniqueId(), player.getName(), config.currencyInitialAmount());
        add(user);
        return user;
    }



    public void save(Player player) {
        EconomyUser user = getByUID(player.getUniqueId());
        this.repository.save(user);
        remove(user);
    }
}
