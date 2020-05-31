package com.star.amlakonline.ApiConnection;


import android.util.Log;

import com.star.amlakonline.Model.File;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class FileConnection implements AsyncResponse {
    private JSONObject result;

    public JSONObject getResult() {
        return result;
    }
    public static FileConnection Builder(int page){
        return new FileConnection(page);
    }

    private FileConnection(int page){
        ApiConnection apiConnection = new ApiConnection();
        try {
            apiConnection.setDelegate(FileConnection.this);
            this.processFinish(apiConnection.execute("http://amlakonlin.ir/api/base","page="+page).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output){
        try {
            this.result = new JSONObject(output);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
