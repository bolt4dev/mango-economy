package net.mangoland.economy.module;

import com.hakan.basicdi.annotations.Provide;
import com.hakan.spinjection.config.utils.FileUtils;
import com.hakan.spinjection.database.connection.credential.DbCredential;
import com.hakan.spinjection.database.connection.properties.DbProperties;
import com.hakan.spinjection.module.PluginModule;
import net.mangoland.economy.config.EconomyConfig;

public class RepositoryModule extends PluginModule {
    @Provide
    public DbCredential dbCredential(EconomyConfig config) {
        System.out.println("a1");
        return DbCredential.of(
                config.databaseUrl(),
                "",
                config.databaseUsername(),
                config.databasePassword()
        );
    }

    @Provide
    public DbProperties dbProperties(EconomyConfig config) {
        System.out.println("a2");
        DbProperties properties = new DbProperties();
        properties.set("hibernate.hbm2ddl.auto", "update");
        properties.set("hibernate.connection.autocommit", "true");

        if (config.databaseUrl().contains("jdbc:h2:")) {
            properties.set("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            properties.set("hibernate.connection.driver_class", "org.h2.Driver");
        } else if (config.databaseUrl().contains("jdbc:sqlite:")) {
            properties.set("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");
            properties.set("hibernate.connection.driver_class", "org.sqlite.JDBC");
            FileUtils.createFile(config.databaseUrl().replace("jdbc:sqlite:", ""));
        } else if (config.databaseUrl().contains("jdbc:mysql:")) {
            properties.set("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.set("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        } else if (config.databaseUrl().contains("jdbc:mariadb:")) {
            properties.set("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
            properties.set("hibernate.connection.driver_class", "org.mariadb.jdbc.Driver");
        } else if (config.databaseUrl().contains("jdbc:postgresql:")) {
            properties.set("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.set("hibernate.connection.driver_class", "org.postgresql.Driver");
        }

        return properties;
    }




}
