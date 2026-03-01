package com.blockpilot.server.blockclock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class HttpBlockClockClientTest {

    private MockRestServiceServer server;
    private HttpBlockClockClient client;

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder();
        server = MockRestServiceServer.bindTo(builder).build();
        client = new HttpBlockClockClient(builder.baseUrl("http://test-device").build());
    }

    @Test
    void setColor_sendsGetRequestToLightsEndpoint() {
        String color = "FF0000";

        server.expect(requestTo("http://test-device/api/lights/" + color))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess());

        client.setColor(color);

        server.verify();
    }
}
