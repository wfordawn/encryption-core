package com.dawn.encryption.waring.strategy;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.market.service.IMarketService;
import com.dawn.encryption.ratio.service.LongSortRatioService;
import com.dawn.encryption.waring.domain.WaringDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ration")
public class RatioWaring extends WaringStrategy {

    @Autowired
    LongSortRatioService longSortRatioService;

    @Override
    public String findTargetVal(WaringDomain waringDomain) {
        return longSortRatioService.findLastRatio(Ccy.valueOf(waringDomain.getCcy())).toString();
    }
}
