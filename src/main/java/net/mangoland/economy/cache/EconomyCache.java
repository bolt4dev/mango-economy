package net.mangoland.economy.cache;

import com.hakan.basicdi.annotations.Component;
import net.mangoland.economy.model.EconomyUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class EconomyCache {

    private final Map<UUID, EconomyUser> users = new HashMap<>();

    public Map<UUID, EconomyUser> getContentSafe() {
        return new HashMap<>(this.users);
    }

    public Map<UUID, EconomyUser> getContent() {
        return this.users;
    }

    public Collection<EconomyUser> getValuesSafe() {
        return new ArrayList<>(this.users.values());
    }

    public Collection<EconomyUser> getValues() {
        return this.users.values();
    }


    public boolean existByID(Integer id) {
        return this.users.values().stream().anyMatch(user -> user.getId().equals(id));
    }

    public boolean existByUID(UUID uid) {
        return this.users.containsKey(uid);
    }

    public EconomyUser getByID(Integer id) {
        return this.findByID(id).orElseThrow(() -> new NullPointerException("there is no economy data with (" + id + ")"));
    }

    public EconomyUser getByUID(UUID uid) {
        return this.findByUID(uid).orElseThrow(() -> new NullPointerException("there is no economy data with (" + uid + ")"));
    }

    public Optional<EconomyUser> findByID(Integer id) {
        return this.users.values().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public Optional<EconomyUser> findByUID(UUID uid) {
        return Optional.ofNullable(this.users.get(uid));
    }

    public void add(EconomyUser user) {
        this.users.put(user.getUid(), user);
    }

    public void remove(EconomyUser user) {
        this.users.remove(user.getUid());
    }
}
