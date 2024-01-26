package net.mangoland.economy.repository;

import com.hakan.spinjection.database.annotations.Query;
import com.hakan.spinjection.database.annotations.QueryParam;
import com.hakan.spinjection.database.annotations.Repository;
import com.hakan.spinjection.database.repositories.JpaRepository;
import net.mangoland.economy.model.EconomyUser;

import java.util.UUID;

@Repository
public interface EconomyRepository extends JpaRepository<Integer, EconomyUser> {

    @Query("select u from EconomyUser u where u.uid = :uuid")
    EconomyUser findByUID(@QueryParam("uuid") UUID uid);
}
