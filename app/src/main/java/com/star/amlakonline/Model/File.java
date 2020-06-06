package com.star.amlakonline.Model;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.star.amlakonline.ApiConnection.FileConnection;
import com.star.amlakonline.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;

public class File {
    public static String[] BuldingType = {
        "ویلایی",
        "آپارتمان",
        "اداری و تجاری",
        "زمین و کلنگی"
    };
    public static String[] region_map = {
                 "منطقه ی یک",
                 "منطقه ی دو",
                 "منطقه ی سه",
                 "منطقه ی چهار",
                 "منطقه ی پنج",
                 "منطقه ی شش",
                 "منطقه ی هفت",
                 "منطقه ی هشت",
                 "منطقه ی نه",
                 "منطقه ی ده",
                 "منطقه ی یازده",
                 "منطقه ی دوازده",
                 "منطقه ی ثامن"
    };
    public String getPrice(double price){
        String[] num = new String[2];
        String doubleAsString = String.valueOf(price);
        int indexOfDecimal = doubleAsString.indexOf(".");
        num[0] = doubleAsString.substring(0, indexOfDecimal);
        num[1] = doubleAsString.substring(indexOfDecimal+1);
        int real = Integer.parseInt(num[0]);
        int ff = Integer.parseInt(num[1]);
        if(ff!=0){
            int ffloat = ff;
            String text = "";
            if( (real+"").toCharArray().length > 3){
                int mil = Math.round(real/1000);
                if ((""+ffloat).toCharArray().length == 1){
                    if((real-mil*1000) == 0)
                        text = mil +" میلیارد و "  + ((int)ffloat * 100)  + " هزار تومان ";
                    else
                    text = mil +" میلیارد و "  + (real-mil*1000)  + " میلیون و "  + ((int)ffloat * 100)  + " هزار تومان ";
                }
                if ((ffloat+"").toCharArray().length == 2){
                    if((real-mil*1000) == 0)
                        text = mil +" میلیارد و "  + (ffloat * 10)  + " هزار تومان ";
                    else
                    text = mil +" میلیارد و "  + (real-mil*1000)  + " میلیون و "  + ((int)ffloat * 10)  + " هزار تومان ";
                }
                if ((ffloat+"").toCharArray().length == 3){
                    if((real-mil*1000) == 0)
                        text = mil +" میلیارد و "  + (ffloat)  + " هزار تومان ";
                    else
                    text = mil +" میلیارد و "  + (real-mil*1000)  + " میلیون و "  + (ffloat)  + " هزار تومان ";
                }
            }
            else if( real != 0) {
                if ((ffloat+"").toCharArray().length == 1)
                    text = real  + " میلیون و "  + (ffloat * 100)  + " هزار تومان ";
                if ((ffloat+"").toCharArray().length == 2)
                    text = real  + " میلیون و "  + ((int)ffloat * 10)  + " هزار تومان ";
                if ((ffloat+"").toCharArray().length == 3)
                    text = real  + " میلیون و "  + ffloat  + " هزار تومان ";
            }else{
                if ((ffloat+"").toCharArray().length == 1)
                    text =(ffloat * 100)  + " هزار تومان ";
                if ((ffloat+"").toCharArray().length == 2)
                    text =(ffloat * 10)  + " هزار تومان ";
                if ((ffloat+"").toCharArray().length == 3)
                    text =ffloat  + " هزار تومان ";
            }
            return text;
        }else{
            if( (""+real).toCharArray().length > 3){
                int mil = ((int)(real/1000));
                if((real-mil*1000) == 0)
                    return mil +" میلیارد تومان ";
                else
                return mil +" میلیارد و "  + (real-mil*1000)  + " میلیون تومان ";
            }
            return real +" میلیون تومان";
        }
    }
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
    public String getTitle(){
        return "  "+File.BuldingType[this.buldingType-1]+" "+this.area+" متری  ";
    }
    public static JSONObject obj = null;
    static void  resive() throws InterruptedException {
        obj = null;
        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {
                FileConnection.Builder(page);
            }
        });
        t.start();
    }
    public static List<File> fetch(){
        List<File> files = new ArrayList<>();

        JSONArray data = null;
        try {
            data = obj.getJSONArray("data");
            for (int i = 0; i < obj.getInt("per_page"); i++) {
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
                file.description = data.getJSONObject(i).isNull("description") ? "" : data.getJSONObject(i).getString("description");
                file.floorCount = data.getJSONObject(i).isNull("floorCount") ? 1 : data.getJSONObject(i).getInt("floorCount");
                file.floor = data.getJSONObject(i).isNull("floor") ? 1 : data.getJSONObject(i).getInt("floor");
                file.age = data.getJSONObject(i).isNull("age") ? 0 : data.getJSONObject(i).getInt("age");
                file.unit = data.getJSONObject(i).isNull("unit") ? 1 : data.getJSONObject(i).getInt("unit");
                file.bedroom = data.getJSONObject(i).isNull("bedroom") ? 1 : data.getJSONObject(i).getInt("bedroom");
                file.floorCovering = data.getJSONObject(i).getInt("floorCovering");
                file.wallCovering = data.getJSONObject(i).getInt("wallCovering");
                file.cabinet = data.getJSONObject(i).getInt("cabinet");
                file.direction = data.getJSONObject(i).getInt("direction");
                file.heating = data.getJSONObject(i).getInt("heating");
                file.cooling = data.getJSONObject(i).getInt("cooling");
                file.view = data.getJSONObject(i).getInt("view");
                file.document = data.getJSONObject(i).getInt("document");
                file.parking = data.getJSONObject(i).getInt("parking") == 1;
                file.asansor = data.getJSONObject(i).getInt("asansor") == 1;
                file.iphone = data.getJSONObject(i).getInt("iphone") == 1;
                file.trace = data.getJSONObject(i).getInt("trace") == 1;
                file.anbary = data.getJSONObject(i).getInt("anbary") == 1;
                file.edoor = data.getJSONObject(i).getInt("edoor") == 1;
                file.wc = data.getJSONObject(i).getInt("wc") == 1;
                file.hamam = data.getJSONObject(i).getInt("hamam") == 1;
                file.komod = data.getJSONObject(i).getInt("komod") == 1;
                file.gas = data.getJSONObject(i).getInt("gas") == 1;
                files.add(file);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        page++;
        return files;
    }
    public static void loadMore() throws InterruptedException {
        resive();
    }
}
