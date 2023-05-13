package com.dawn.encryption.market.service.impl;

import com.dawn.encryption.adapter.ok.OkAdapter;
import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.enums.Period;
import com.dawn.encryption.market.domain.CandlesDomain;
import com.dawn.encryption.market.domain.CandlesQuery;
import com.dawn.encryption.market.service.IMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MarketServiceImpl implements IMarketService {

    @Autowired
    private OkAdapter okAdapter;

    @Override
    public List<CandlesDomain> candles(CandlesQuery query) {
        return okAdapter.getCandles(query);
    }

    @Override
    public String findNowPrice(Ccy ccy) {
        CandlesQuery query = CandlesQuery.builder().ccy(ccy)
                .period(Period.minute)
                .build();
        List<CandlesDomain> candles = candles(query);
        CandlesDomain candlesDomain = candles.get(0);
        return candlesDomain.getClose();
    }
}
