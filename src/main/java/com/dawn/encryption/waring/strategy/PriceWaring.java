package com.dawn.encryption.waring.strategy;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.market.service.IMarketService;
import com.dawn.encryption.waring.domain.WaringDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("price")
public class PriceWaring extends WaringStrategy {

    @Autowired
    IMarketService marketService;

    @Override
    public String findTargetVal(WaringDomain waringDomain) {
        return marketService.findNowPrice(Ccy.valueOf(waringDomain.getCcy()));
    }
}
