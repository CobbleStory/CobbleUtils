package com.kingpixel.cobbleutils.util.economys;

import com.kingpixel.cobbleutils.CobbleUtils;
import gg.levely.cobblestory.economy.api.Economy;
import gg.levely.cobblestory.economy.api.PlayerEconomy;
import gg.levely.cobblestory.economy.api.PlayerEconomyProvider;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Carlos Varas Alonso - 29/01/2025 4:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CobbleEconomy extends EconomyAbstract {

    public static final String IDENTIFY = "COBBLEECONOMY";
    public static PlayerEconomyProvider economyProvider;

    public CobbleEconomy() {

    }

    @Override
    public String getIdentify() {
        return IDENTIFY;
    }

    @Override
    public boolean isPresent() {
        economyProvider = PlayerEconomyProvider.getInstance();
        return true;
    }

    @Override
    public boolean deposit(UUID playerUuid, BigDecimal money, String currency) {
        PlayerEconomy playerEconomy = economyProvider.getEconomy(playerUuid);
        if (playerEconomy == null) return false;

        playerEconomy.addBalance(Economy.fromName(currency), money);
        return true;
    }

    @Override
    public boolean withdraw(UUID playerUuid, BigDecimal money, String currency) {
        PlayerEconomy playerEconomy = economyProvider.getEconomy(playerUuid);
        if (playerEconomy == null) return false;

        playerEconomy.subtractBalance(Economy.fromName(currency), money);
        return true;
    }

    @Override
    public BigDecimal getBalance(UUID playerUuid, String currency) {
        PlayerEconomy playerEconomy = economyProvider.getEconomy(playerUuid);
        if (playerEconomy == null) return BigDecimal.ZERO;

        return playerEconomy.getBalance(Economy.fromName(currency));
    }

    @Override
    public String format(BigDecimal money, String currency) {
        return CobbleUtils.language.getDefaultSymbol() + " " + money.doubleValue();
    }

    @Override
    public boolean setBalance(UUID playerUuid, BigDecimal money, String currency) {
        PlayerEconomy playerEconomy = economyProvider.getEconomy(playerUuid);
        if (playerEconomy == null) return false;

        playerEconomy.setBalance(Economy.fromName(currency), money);
        return true;
    }

    @Override
    public int getDecimals(String currency) {
        return 5;
    }
}
