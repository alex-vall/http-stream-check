package com.vall.httpstreamcheck.model;

/**
 * Created by Vall on 02.01.2018.
 */
public class RestResponse {

    private final long id;
    private final String payload;

    public RestResponse(long id, String payload) {
        this.id = id;
        this.payload = payload;
    }


    public long getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

}
