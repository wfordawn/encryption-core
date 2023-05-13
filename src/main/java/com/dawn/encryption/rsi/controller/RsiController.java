package com.dawn.encryption.rsi.controller;

import cn.hutool.core.map.MapUtil;
import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.enums.Period;
import com.dawn.encryption.rsi.service.RsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rsi")
public class RsiController {

    @Autowired
    private RsiService rsiService;

    @RequestMapping("/find")
    public Object rsi(@RequestBody Map<String, Object> body) {
        String ccy = MapUtil.getStr(body, "ccy");
        String period = MapUtil.getStr(body, "period");
        return rsiService.findNowRsi(Ccy.valueOf(ccy), Period.valueOf(period));
    }
}
