package com.star.amlakonline.ApiConnection;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginConnection implements AsyncResponse {

    public LoginConnection(String username,String password) {
        String token;
        ApiConnection apiConnection = new ApiConnection();
        try {
            apiConnection.delegate = this;
            JSONObject account = new JSONObject();
            account.put("username",username);
            account.put("password",password);
            apiConnection.execute("http://amlakonlin.ir/api/login","account="+account.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {
        switch (output)
        {
            case "username":
                break;
            case "password":
                break;
            case "max":
                break;
            default:
                try {
                    JSONObject object = new JSONObject(output);
                    object.getString("apiToken");
                }catch (JSONException e){
                    e.printStackTrace();
                }
        }
    }
}
