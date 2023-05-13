package com.dawn.encryption.adapter.ok;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.market.domain.CandlesDomain;
import com.dawn.encryption.market.domain.CandlesQuery;
import com.dawn.encryption.ratio.domain.LongSortRatioDomain;
import com.dawn.encryption.enums.Period;
import com.okex.open.api.service.marketData.MarketDataAPIService;
import com.okex.open.api.service.rubik.RubikAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OkAdapter {

    @Autowired
    private RubikAPIService rubikAPIService;

    @Autowired
    private MarketDataAPIService marketDataAPIService;


    public List<LongSortRatioDomain> getLongShortAccountRatio(Ccy ccy, Date beginTime, Date endTime, Period period) {
        String begin = "";
        String end = "";
        if (beginTime != null) {
            begin = String.valueOf(beginTime.getTime());
        }
        if (endTime != null) {
            end = String.valueOf(endTime.getTime());
        }
        JSONObject longShortAccountRatio = rubikAPIService.getLongShortAccountRatio(Ccy.getCcyName(ccy), begin, end, getLongSortPeriod(period));
        List<LongSortRatioDomain> res = new ArrayList<>();
        JSONArray data = longShortAccountRatio.getJSONArray("data");
        for (Object datum : data) {
            List<String> parseArray = JSON.parseArray(datum.toString(), String.class);
            LongSortRatioDomain domain = new LongSortRatioDomain();
            domain.setRatioTime(Long.parseLong(parseArray.get(0)) / 1000);
            domain.setRatio(Float.valueOf(parseArray.get(1)));
            domain.setCcy(ccy.name());
            domain.setPeriod(period.name());
            res.add(domain);
        }
        return res;
    }

    public List<CandlesDomain> getCandles(CandlesQuery query) {
        String instId = Ccy.getInstId(query.getCcy());
        String period = getCandlesPeriod(query.getPeriod());
        JSONObject candlesticks = marketDataAPIService.getCandlesticks(instId, null, null, period, null);
        List<CandlesDomain> res = new ArrayList<>();
        JSONArray data = candlesticks.getJSONArray("data");
        for (Object datum : data) {
            List<String> parseArray = JSON.parseArray(datum.toString(), String.class);
            CandlesDomain domain = CandlesDomain.builder().time(DateUtil.date(Long.parseLong(parseArray.get(0))).toString())
                    .open(parseArray.get(1)).high(parseArray.get(2)).low(parseArray.get(3)).close(parseArray.get(4))
                    .vol(parseArray.get(5)).confirm(parseArray.get(8)).build();
            res.add(domain);
        }
        return res;
    }


    private String getLongSortPeriod(Period period) {
        String res = "";
        switch (period) {
            case hour -> res = "4H";
            case day -> res = "1D";
            default -> res = "5m";
        }
        return res;
    }

    private String getCandlesPeriod(Period period) {
        String res = "";
        switch (period) {
            case hour -> res = "4H";
            case day -> res = "1D";
            default -> res = "15m";
        }
        return res;
    }

}
