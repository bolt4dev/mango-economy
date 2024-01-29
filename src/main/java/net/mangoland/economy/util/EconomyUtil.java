package net.mangoland.economy.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class EconomyUtil {
    public static String formatCurrency(double amount) {
            DecimalFormat format = new DecimalFormat();
            format.setRoundingMode(RoundingMode.HALF_EVEN);
            format.setGroupingUsed(true);
            format.setMinimumFractionDigits(0);
            return format.format(amount);
        }
    }
