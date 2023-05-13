package com.dawn.encryption.waring.strategy;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.enums.Period;
import com.dawn.encryption.rsi.service.RsiService;
import com.dawn.encryption.waring.domain.WaringDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("rsi")
public class RsiWaring extends WaringStrategy {

    @Autowired
    RsiService rsiService;

    @Override
    String findTargetVal(WaringDomain waringDomain) {
        return rsiService.findNowRsi(Ccy.valueOf(waringDomain.getCcy()), Period.minute);
    }
}
