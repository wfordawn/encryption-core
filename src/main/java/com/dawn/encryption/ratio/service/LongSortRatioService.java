package com.dawn.encryption.ratio.service;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.ratio.domain.LongSortRatioDomain;

import java.util.List;

public interface LongSortRatioService {

    void save();

    Float findLastRatio(Ccy ccy);

    List<LongSortRatioDomain> findLast10(Ccy ccy);
}
