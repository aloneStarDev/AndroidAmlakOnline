package com.star.amlakonline.Model;

import android.os.StrictMode;
import android.provider.Telephony;
import android.util.Log;

import com.star.amlakonline.ApiConnection.FileConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class File {
    private static int page = 1;
    public int id;
    public int code;
    public double buy;
    public double rahn;
    public double ejare;
    public String name;
    public String lastName;
    public int buldingType;
    public int area;
    public int region;
    public String addressPu;
    public String addressPv;
    public String phoneNumber;
    public String description;
    public int floorCount;
    public int floor;
    public int age;
    public int unit;
    public int bedroom;
    public int floorCovering;
    public int wallCovering;
    public int cabinet;
    public int direction;
    public int heating;
    public int cooling;
    public int view;
    public int document;
    public boolean parking;
    public boolean asansor;
    public boolean iphone;
    public boolean trace;
    public boolean anbary;
    public boolean edoor;
    public boolean wc;
    public boolean hamam;
    public boolean komod;
    public boolean gas;
    public static List<File> loadMore() {
        List<File> files = new ArrayList<>();
        FileConnection fileConnection = FileConnection.Builder(page);
        JSONObject obj = fileConnection.getResult();
        JSONArray data = null;
        try {
            data = obj.getJSONArray("data");
            for(int i = 0;i<obj.getInt("per_page");i++){
                File file = new File();
                file.id = data.getJSONObject(i).getInt("id");
                file.code = data.getJSONObject(i).getInt("code");
                file.buy = !data.getJSONObject(i).isNull("buy") ? data.getJSONObject(i).getDouble("buy") : 0;
                file.rahn = !data.getJSONObject(i).isNull("rahn") ? data.getJSONObject(i).getDouble("rahn") : 0;
                file.ejare = !data.getJSONObject(i).isNull("ejare") ? data.getJSONObject(i).getDouble("ejare") : 0;
                file.name = data.getJSONObject(i).getString("name");
                file.lastName = data.getJSONObject(i).getString("lastname");
                file.buldingType = data.getJSONObject(i).getInt("buildingType");
                file.area = data.getJSONObject(i).getInt("area");
                file.region = data.getJSONObject(i).getInt("region");
                file.addressPu = data.getJSONObject(i).getString("addressPu");
                file.addressPv = data.getJSONObject(i).getString("addressPv");
                file.phoneNumber = data.getJSONObject(i).getString("phonenumber");
                file.description = data.getJSONObject(i).isNull("description")? "" : data.getJSONObject(i).getString("description");
                file.floorCount = data.getJSONObject(i).isNull("floorCount") ? 1 : data.getJSONObject(i).getInt("floorCount");
                file.floor = data.getJSONObject(i).isNull("floor") ? 1 : data.getJSONObject(i).getInt("floor");
                file.age = data.getJSONObject(i).isNull("age") ? 0 : data.getJSONObject(i).getInt("age");
                file.unit = data.getJSONObject(i).isNull("unit") ? 1 :data.getJSONObject(i).getInt("unit");
                file.bedroom = data.getJSONObject(i).isNull("bedroom") ? 1 : data.getJSONObject(i).getInt("bedroom");
                file.floorCovering = data.getJSONObject(i).getInt("floorCovering");
                file.wallCovering = data.getJSONObject(i).getInt("wallCovering");
                file.cabinet = data.getJSONObject(i).getInt("cabinet");
                file.direction = data.getJSONObject(i).getInt("direction");
                file.heating = data.getJSONObject(i).getInt("heating");
                file.cooling = data.getJSONObject(i).getInt("cooling");
                file.view = data.getJSONObject(i).getInt("view");
                file.document = data.getJSONObject(i).getInt("document");
                file.parking = data.getJSONObject(i).getInt("parking") == 1 ;
                file.asansor = data.getJSONObject(i).getInt("asansor") == 1 ;
                file.iphone = data.getJSONObject(i).getInt("iphone") == 1 ;
                file.trace = data.getJSONObject(i).getInt("trace") == 1 ;
                file.anbary = data.getJSONObject(i).getInt("anbary") == 1 ;
                file.edoor = data.getJSONObject(i).getInt("edoor") == 1 ;
                file.wc = data.getJSONObject(i).getInt("wc") == 1 ;
                file.hamam = data.getJSONObject(i).getInt("hamam") == 1 ;
                file.komod = data.getJSONObject(i).getInt("komod") == 1 ;
                file.gas = data.getJSONObject(i).getInt("gas") == 1 ;
                files.add(file);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        page++;
        return files;
    }
}
