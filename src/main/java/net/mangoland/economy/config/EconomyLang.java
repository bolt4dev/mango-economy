package net.mangoland.economy.config;

import com.hakan.spinjection.config.annotations.ConfigFile;
import com.hakan.spinjection.config.annotations.ConfigValue;
import com.hakan.spinjection.config.configuration.BaseConfiguration;

@ConfigFile(
        resource = "lang.yml",
        path = "plugins/MangoEconomy/lang.yml"
)
public interface EconomyLang extends BaseConfiguration {

    @ConfigValue("balance-message")
    String balanceMessage();

    @ConfigValue("balance-message-another")
    String balanceMessageAnother();


    @ConfigValue("transaction-success")
    String transactionSuccess();

    @ConfigValue("transaction-fail")
    String transactionFail();

    @ConfigValue("transaction-received")
    String transactionReceived();

    @ConfigValue("admin-set-balance")
    String adminSetBalance();

    @ConfigValue("admin-give-balance")
    String adminGiveBalance();

    @ConfigValue("admin-remove-balance")
    String adminRemoveBalance();


}
