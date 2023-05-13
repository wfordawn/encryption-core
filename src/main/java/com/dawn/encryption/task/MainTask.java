package com.dawn.encryption.task;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.enums.Period;
import com.dawn.encryption.common.utils.EmailUtils;
import com.dawn.encryption.ratio.domain.LongSortRatioDomain;
import com.dawn.encryption.ratio.service.LongSortRatioService;
import com.dawn.encryption.rsi.service.RsiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class MainTask {

    @Autowired
    LongSortRatioService longSortRatioService;

    @Scheduled(cron = "0 0/7 * * * ?")
    public void save() {
        log.debug("longSortRatioService save begin.");
        longSortRatioService.save();
    }


}
