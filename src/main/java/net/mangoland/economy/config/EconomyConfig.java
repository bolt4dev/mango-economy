package net.mangoland.economy.config;

import com.hakan.spinjection.config.annotations.ConfigFile;
import com.hakan.spinjection.config.annotations.ConfigValue;
import com.hakan.spinjection.config.configuration.BaseConfiguration;

@ConfigFile(
        resource = "config.yml",
        path = "plugins/MangoEconomy/config.yml"
)
public interface EconomyConfig extends BaseConfiguration {

    // CURRENCY
    @ConfigValue("currency.singular-name")
    String currencySingularName();

    @ConfigValue("currency.plural-name")
    String currencyPluralName();

    @ConfigValue("currency.separator")
    String currencySeparator();

    @ConfigValue("currency.display-format")
    String currencyDisplayFormat();

    @ConfigValue("currency.initial-amount")
    double currencyInitialAmount();

    @ConfigValue("currency.tax.all")
    double taxAll();

    @ConfigValue("currency.tax.transactions")
    double taxTransactions();



    // BAL-TOP
    @ConfigValue("settings.bal-top.interval")
    int balTopInterval();

    // LOGGING
    @ConfigValue("settings.logging.enabled")
    boolean logEnabled();

    @ConfigValue("settings.logging.format")
    String logFormat();



    // DATABASE
    @ConfigValue("settings.database.auth.url")
    String databaseUrl();

    @ConfigValue("settings.database.auth.username")
    String databaseUsername();

    @ConfigValue("settings.database.auth.password")
    String databasePassword();
}
