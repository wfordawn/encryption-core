package com.dawn.encryption.ratio.controller;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.ratio.service.LongSortRatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("/ratio")
@RestController
public class RatioController {

    @Autowired
    private LongSortRatioService longSortRatioService;

    @RequestMapping("/findLastRation")
    public Object findLastRation() {
        return longSortRatioService.findLast10(Ccy.eth);
    }
}
