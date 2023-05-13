package com.dawn.encryption.waring.task;

import com.dawn.encryption.waring.domain.WaringDomain;
import com.dawn.encryption.waring.service.IWaringService;
import com.dawn.encryption.waring.strategy.WaringStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MainTask {

    @Autowired
    private IWaringService waringService;

    @Autowired
    Map<String, WaringStrategy> waringStrategyMap;

    @Scheduled(cron = "0 0/15 * * * ?")
    public void doWaring() {
        List<WaringDomain> all = waringService.findAll();
        for (WaringDomain waringDomain : all) {
            String type = waringDomain.getType();
            WaringStrategy waringStrategy = waringStrategyMap.get(type);
            waringStrategy.execute(waringDomain);
        }
    }
}
