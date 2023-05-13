package com.dawn.encryption.rsi.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.dawn.encryption.adapter.ok.OkAdapter;
import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.enums.Period;
import com.dawn.encryption.common.utils.RsiCalculator;
import com.dawn.encryption.market.domain.CandlesDomain;
import com.dawn.encryption.market.domain.CandlesQuery;
import com.dawn.encryption.rsi.domain.RsiDomain;
import com.dawn.encryption.rsi.service.RsiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class RsiServiceImpl implements RsiService {

    @Autowired
    private OkAdapter okAdapter;



    @Override
    public String findNowRsi(Ccy ccy, Period period) {
        List<RsiDomain> rsi = findRsi(ccy, period);
        return rsi.get(rsi.size()-1).getRsi();
    }

    private List<RsiDomain> findRsi(Ccy ccy, Period period) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        CandlesQuery query = CandlesQuery.builder().ccy(ccy).period(period).build();
        List<CandlesDomain> candles = okAdapter.getCandles(query);
        Collections.reverse(candles);
        if (CollUtil.isEmpty(candles)) {
            throw new RuntimeException("findNowRsi error.");

        }
        double[] closeVal = candles.stream().map(item -> Double.valueOf(item.getClose())).mapToDouble(Double::doubleValue).toArray();
        double[] rsiVal = RsiCalculator.calculate(closeVal);
        List<RsiDomain> res = new ArrayList<>();

        for (int idx = 0; idx < rsiVal.length; idx++) {
            RsiDomain rsiDomain = new RsiDomain();
            rsiDomain.setCcy(ccy.name());
            rsiDomain.setPeriod(period.name());
            rsiDomain.setRsi(decimalFormat.format(rsiVal[idx]));
            DateTime dateTime = DateUtil.parse(candles.get(idx).getTime(), DatePattern.NORM_DATETIME_MINUTE_FORMAT);
            rsiDomain.setRsiTime((int) (dateTime.getTime() / 1000));
            rsiDomain.setConfirm(candles.get(idx).getConfirm());
            res.add(rsiDomain);
        }
        return res;
    }
}
