package com.vall.httpstreamcheck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        final String responseContent = mvcResult.getResponse().getContentAsString();

        final List<String> jsonList = Arrays.stream(responseContent.split("\n\n"))
                .map(i -> i.replace("data:", ""))
                .collect(Collectors.toList());

        assertEquals(3, jsonList.size());

        new JsonPathExpectationsHelper("$.id").assertValue(jsonList.get(0), "1000");
        new JsonPathExpectationsHelper("$.payload").assertValue(jsonList.get(0), "Fake payload for 1000");
        new JsonPathExpectationsHelper("$.id").assertValue(jsonList.get(1), "1001");
        new JsonPathExpectationsHelper("$.payload").assertValue(jsonList.get(1), "Fake payload for 1001");
        new JsonPathExpectationsHelper("$.id").assertValue(jsonList.get(2), "1002");
        new JsonPathExpectationsHelper("$.payload").assertValue(jsonList.get(2), "Fake payload for 1002");
    }

}
