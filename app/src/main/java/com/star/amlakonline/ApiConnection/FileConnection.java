package com.star.amlakonline.ApiConnection;

import android.util.Log;

import com.star.amlakonline.MainActivity;
import com.star.amlakonline.Model.File;

import org.json.JSONException;
import org.json.JSONObject;

public class FileConnection implements AsyncResponse {

    public static FileConnection Builder(int page){
        return new FileConnection(page);
    }

    private FileConnection(int page){
        ApiConnection apiConnection = new ApiConnection();
        try {
            apiConnection.delegate = this;
            apiConnection.execute("http://amlakonlin.ir/api/base","page="+page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output){
        try {
            File.obj =  new JSONObject(output);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MainActivity.fileAdapter.addFiles(File.fetch());
    }
}
