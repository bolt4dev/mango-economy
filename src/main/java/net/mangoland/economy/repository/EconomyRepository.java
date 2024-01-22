package net.mangoland.economy.repository;

import com.hakan.spinjection.database.annotations.Repository;
import com.hakan.spinjection.database.repositories.JpaRepository;
import net.mangoland.economy.model.user.EconomyUser;

@Repository
public interface EconomyRepository extends JpaRepository<Integer, EconomyUser> { }
