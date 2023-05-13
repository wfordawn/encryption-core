package com.dawn.encryption.rsi.service;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.enums.Period;

public interface RsiService {

    String findNowRsi(Ccy ccy, Period period);

}
