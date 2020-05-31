package com.star.amlakonline.ApiConnection;

import org.json.JSONException;

public interface AsyncResponse {
    void processFinish(String output) throws JSONException;
}
