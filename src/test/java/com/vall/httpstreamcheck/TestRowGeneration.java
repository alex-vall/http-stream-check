package com.vall.httpstreamcheck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * Created by Vall on 15.01.2018.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestRowGeneration {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSyncGeneration() throws Exception {

        mockMvc.perform(get("/regular/generate")
                .param("count", "2")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "[" +
                                "{\"id\":1000,\"payload\":\"Fake payload for 1000\"}," +
                                "{\"id\":1001,\"payload\":\"Fake payload for 1001\"}" +
                                "]"
                ));

        mockMvc.perform(get("/regular/generate")
                .param("count", "3")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "[" +
                                "{\"id\":1002,\"payload\":\"Fake payload for 1002\"}," +
                                "{\"id\":1003,\"payload\":\"Fake payload for 1003\"}," +
                                "{\"id\":1004,\"payload\":\"Fake payload for 1004\"}" +
                                "]"
                ));
    }

    @Test
    public void testAsyncStream() throws Exception {

        final MvcResult mvcResult = mockMvc.perform(get("/regular/generateStream").param("count", "3"))
                .andExpect(status().isOk())
                .andExpect(request().asyncStarted())
                .andExpect(header().string("Content-Type", "text/event-stream;charset=UTF-8"))
                .andReturn();

        mvcResult.getRequest().getAsyncContext().setTimeout(10000);
        mvcResult.getAsyncResult();

        //TODO: need to refactor
        assertEquals("data:{\"id\":1000,\"payload\":\"Fake payload for 1000\"}\n" +
                "\n" +
                "data:{\"id\":1001,\"payload\":\"Fake payload for 1001\"}\n" +
                "\n" +
                "data:{\"id\":1002,\"payload\":\"Fake payload for 1002\"}\n\n", mvcResult.getResponse().getContentAsString());

    }


}
