package com.rmit.sept.bk_marketplacemicroservices.config;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PayPalConfig {

    //    @Value("${paypal.client.id}")
    private String clientId = "Aajp4np_B0-mQ2sgCprZTr99AilnxjQDorg8tnsEJ-cqiv1rB-iIfzWsiuQJzHpPcyUyXVuh7eXW4kaC";
    //    @Value("${paypal.client.secret}")
    private String clientSecret = "EA-8dCF68Isc6sLKJByr-gWMKV5NnO0G_la_0CUKtUgMnLMidSVC6sLwAa43HWNvm3EfXuAYzg5409kf";
    //    @Value("${paypal.mode}")
    private String mode = "sandbox";

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }

}
