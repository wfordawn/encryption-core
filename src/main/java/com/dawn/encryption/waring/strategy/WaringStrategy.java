package com.dawn.encryption.waring.strategy;

import com.alibaba.fastjson.JSON;
import com.dawn.encryption.common.cache.IGlobalCache;
import com.dawn.encryption.enums.Period;
import com.dawn.encryption.common.utils.EmailUtils;
import com.dawn.encryption.waring.domain.WaringDomain;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class WaringStrategy {

    @Autowired
    private IGlobalCache cache;

    String KEY = "wring::%d";

    public void execute(WaringDomain waring) {
        String targetVal = findTargetVal(waring);
        ConditionDetail conditionDetail = JSON.parseObject(waring.getDetail(), ConditionDetail.class);
        boolean condition = checkCondition(conditionDetail, Float.valueOf(targetVal));
        boolean frequency = checkFrequency(waring.getId(), waring.getFrequency());
        if (condition && frequency) {
            String waringMsg = "告警类型:" + waring.getCcy() + "_" + waring.getType() + "，目标：" + conditionDetail.getVal() + "，现在：" + targetVal;
            EmailUtils.sendTextEmail("37780328@qq.com", waring.getType(), waringMsg);
            update(waring.getId(), waring.getFrequency());
        }
    }

    abstract String findTargetVal(WaringDomain domain);


    public boolean checkCondition(ConditionDetail conditionDetail, Float checkVal) {
        float targetVal = Float.parseFloat(conditionDetail.getVal());
        String condition = conditionDetail.getOptional();
        boolean res = false;
        switch (condition) {
            case "gte" -> res = Float.compare(checkVal, targetVal) >= 0;
            case "lte" -> res = Float.compare(checkVal, targetVal) <= 0;
        }
        return res;
    }

    public boolean checkFrequency(Long id, String frequency) {
        if (frequency.equals("all")) {
            return true;
        }
        String key = String.format(KEY, id);
        return !cache.hasKey(key);
    }

    public void update(Long id, String frequency) {
        if (frequency.equals("all")) {
            return;
        }
        String key = String.format(KEY, id);
        Period period = Period.valueOf(frequency);
        switch (period) {
            case minute -> cache.set(key, 1, 15 * 60);
            case hour -> cache.set(key, 1, 60 * 60);
            case day -> cache.set(key, 1, 24 * 60 * 60);
        }
    }

    @Data
    static class ConditionDetail {

        private String optional;

        private String val;
    }
}
