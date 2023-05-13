package com.dawn.encryption.market.controller;

import com.dawn.encryption.market.domain.CandlesQuery;
import com.dawn.encryption.market.service.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {


    @Autowired
    private IMarketService marketService;

    @RequestMapping("/candles")
    public Object candles(@RequestBody CandlesQuery query) {
        return marketService.candles(query);
    }
}
