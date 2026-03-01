package com.blockpilot.server.blockclock;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HttpBlockClockClient implements BlockClockClient {

    private final RestClient restClient;

    public HttpBlockClockClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void setColor(String rgbHex) {
        restClient.get()
            .uri("/api/lights/{color}", rgbHex)
            .retrieve()
            .toBodilessEntity();
    }
}
