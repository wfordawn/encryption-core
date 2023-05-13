package com.dawn.encryption.enums;

public enum Ccy {

    eth, btc, arb;

    public static String getCcyName(Ccy ccy) {
        String res = "";
        switch (ccy) {
            case eth -> res = "ETH";
            case btc -> res = "BTC";
            case arb -> res = "ARB";
        }
        return res;
    }

    public static String getInstId(Ccy ccy) {
        String res = "";
        switch (ccy) {
            case eth -> res = "ETH-USD-SWAP";
            case btc -> res = "BTC-USD-SWAP";
        }
        return res;
    }
}
