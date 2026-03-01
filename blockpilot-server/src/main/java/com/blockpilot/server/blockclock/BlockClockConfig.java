package com.blockpilot.server.blockclock;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
class BlockClockConfig {

    @Bean
    RestClient blockClockRestClient(
            @Value("${blockclock.base-url}") String baseUrl,
            @Value("${blockclock.password:}") String password) {

        var builder = RestClient.builder().baseUrl(baseUrl);

        if (!password.isEmpty()) {
            var credentials = new BasicCredentialsProvider();
            credentials.setCredentials(
                new AuthScope(null, -1),
                new UsernamePasswordCredentials("", password.toCharArray())
            );
            var httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credentials)
                .build();
            builder.requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        }

        return builder.build();
    }
}
