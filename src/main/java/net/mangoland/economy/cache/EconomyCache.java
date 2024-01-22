package net.mangoland.economy.cache;

import com.hakan.basicdi.annotations.Component;
import net.mangoland.economy.model.user.EconomyUser;

import java.util.*;

@Component
public class EconomyCache {

    private final Map<UUID, EconomyUser> users = new HashMap<>();

    public Map<UUID, EconomyUser> getContentSafe() {
        return new HashMap<>(users);
    }

    public Map<UUID, EconomyUser> getContent() {
        return users;
    }

    public Collection<EconomyUser> getValuesSafe() {
        return new ArrayList<>(users.values());
    }

    public Collection<EconomyUser> getValues() {
        return users.values();
    }

    public Optional<EconomyUser> findByUID(UUID uid) {
        return Optional.ofNullable(users.get(uid));
    }

    public EconomyUser getByID(Integer id) {
        return findByID(id).orElseThrow(() -> new NullPointerException("there is no economy data with (" + id + ")"));
    }

    public Optional<EconomyUser> findByID(Integer id) {
        return Optional.of(users.values().stream().filter(user -> user.getId().equals(id)).findFirst().get());
    }

    public EconomyUser getByUID(UUID uid) {
        return findByUID(uid).orElseThrow(() -> new NullPointerException("there is no economy data with (" + uid + ")"));
    }

    public boolean existByUID(UUID uid) {
        return users.containsKey(uid);
    }

    public boolean existByID(Integer id) {
        return users.values().stream().anyMatch(user -> user.getId().equals(id));
    }

    public void add(EconomyUser user) {
        this.users.put(user.getUid(), user);
    }

    public void remove(EconomyUser user) {
        this.users.remove(user.getUid());
    }
}
