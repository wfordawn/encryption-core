package com.dawn.encryption.market.service;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.market.domain.CandlesDomain;
import com.dawn.encryption.market.domain.CandlesQuery;

import java.util.List;

public interface IMarketService {

    List<CandlesDomain> candles(CandlesQuery query);

    String findNowPrice(Ccy ccy);
}
