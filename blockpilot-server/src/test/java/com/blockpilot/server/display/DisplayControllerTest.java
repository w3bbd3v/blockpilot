package com.blockpilot.server.display;

import com.blockpilot.server.blockclock.BlockClockClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DisplayControllerTest {

    private BlockClockClient blockClockClient;
    private DisplayController controller;

    @BeforeEach
    void setUp() {
        blockClockClient = mock(BlockClockClient.class);
        controller = new DisplayController(blockClockClient);
    }

    @Test
    void setColor_delegatesToClientWithHexColor() {
        controller.setColor(new ColorRequest("FF0000"));

        verify(blockClockClient).setColor("FF0000");
    }
}
