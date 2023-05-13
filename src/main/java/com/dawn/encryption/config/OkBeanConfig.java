package com.dawn.encryption.config;

import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.marketData.MarketDataAPIService;
import com.okex.open.api.service.marketData.impl.MarketDataAPIServiceImpl;
import com.okex.open.api.service.rubik.RubikAPIService;
import com.okex.open.api.service.rubik.impl.RubikAPIServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkBeanConfig {

    @Bean
    public APIConfiguration okApiConfig() {
        APIConfiguration config = new APIConfiguration();
        config.setDomain("https://www.okx.com/");
        config.setApiKey("cfefb022-f4f9-4cc1-94c5-79f3436c0262");
        config.setSecretKey("7AC57DD6AA514E607B5D040660A809E4");
        config.setPassphrase("Passphrase_1");
        config.setxSimulatedTrading("0");
        config.setPrint(true);
        return config;
    }

    @Bean
    public MarketDataAPIService marketDataAPIService(APIConfiguration config) {
        return new MarketDataAPIServiceImpl(config);
    }

    @Bean
    public RubikAPIService rubikAPIService(APIConfiguration configuration) {
        return new RubikAPIServiceImpl(configuration);
    }
}
