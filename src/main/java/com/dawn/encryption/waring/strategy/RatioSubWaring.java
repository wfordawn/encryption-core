package com.dawn.encryption.waring.strategy;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.ratio.domain.LongSortRatioDomain;
import com.dawn.encryption.ratio.service.LongSortRatioService;
import com.dawn.encryption.waring.domain.WaringDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("ratioSub")
public class RatioSubWaring extends WaringStrategy {

    @Autowired
    LongSortRatioService longSortRatioService;

    @Override
    public String findTargetVal(WaringDomain waringDomain) {
        List<LongSortRatioDomain> last10 = longSortRatioService.findLast10(Ccy.eth);
        if (last10.size() < 4) {
            return "0";
        }
        float sub1 = BigDecimal.valueOf(last10.get(0).getRatio()).subtract(BigDecimal.valueOf(last10.get(2).getRatio())).floatValue();
        float sub2 = BigDecimal.valueOf(last10.get(0).getRatio()).subtract(BigDecimal.valueOf(last10.get(3).getRatio())).floatValue();
        float max = Math.max(Math.abs(sub1), Math.abs(sub2));
        return Float.toString(max);
    }
}
