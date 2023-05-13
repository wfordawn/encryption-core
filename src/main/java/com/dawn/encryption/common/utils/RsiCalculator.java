package com.dawn.encryption.common.utils;

import java.util.ArrayList;
import java.util.List;

public class RsiCalculator {

    private static final int DEFAULT_PERIOD = 14;

    public static double[] calculate(double[] prices, int period) {
        double[] rsi = new double[prices.length];

        List<Double> changes = new ArrayList<>();
        for (int i = 1; i < prices.length; i++) {
            changes.add(prices[i] - prices[i - 1]);
        }

        double gainSum = 0;
        double lossSum = 0;
        for (int i = 0; i < period; i++) {
            double change = changes.get(i);
            if (change > 0) {
                gainSum += change;
            } else {
                lossSum += Math.abs(change);
            }
        }
        double avgGain = gainSum / period;
        double avgLoss = lossSum / period;
        double rs = avgGain / avgLoss;
        rsi[period] = 100 - (100 / (1 + rs));

        for (int i = period + 1; i < prices.length; i++) {
            double change = changes.get(i - 1);
            if (change > 0) {
                avgGain = ((avgGain * (period - 1)) + change) / period;
                avgLoss = (avgLoss * (period - 1)) / period;
            } else {
                avgGain = (avgGain * (period - 1)) / period;
                avgLoss = ((avgLoss * (period - 1)) + Math.abs(change)) / period;
            }
            rs = avgGain / avgLoss;
            rsi[i] = 100 - (100 / (1 + rs));
        }

        return rsi;
    }

    public static double[] calculate(double[] prices) {
        return calculate(prices, DEFAULT_PERIOD);
    }
}
