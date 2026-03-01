package com.blockpilot.server.display;

import com.blockpilot.server.blockclock.BlockClockClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/display")
public class DisplayController {

    private final BlockClockClient blockClockClient;

    public DisplayController(BlockClockClient blockClockClient) {
        this.blockClockClient = blockClockClient;
    }

    @PostMapping("/color")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setColor(@RequestBody ColorRequest request) {
        blockClockClient.setColor(request.color());
    }
}
