package com.json.gson;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Pierreluo on 2017/5/26.
 */
public class GsonTest {
    public static void main(String[] args) {
        String jsontext0 = "{\"asd\":[{\"barAge\":-1090113522,\"barDate\":1322062233062,\"barName\":\"sss_0.83562374\"},{\"barAge\":478603194,\"barDate\":1322062233062,\"barName\":\"sss_0.59483266\"},{\"barAge\":1118357669,\"barDate\":1322062233062,\"barName\":\"sss_0.9961642\"}]}";
        String jsontext1 = "{\"avBar\":[{\"barAge\":174398800,\"barDate\":\"2011-11-23 23:30:33\",\"barName\":\"sss_0.62378174\"},{\"barAge\":38938962,\"barDate\":\"2011-11-23 23:30:33\",\"barName\":\"sss_0.36014742\"}],\"avString\":[\"aaa\",\"bbb\",\"ccc\"],\"avboolean\":[true,false,true,true],\"avint\":[1,2,3,4],\"bar\":{\"barAge\":1601495948,\"barDate\":\"2011-11-23 23:30:33\",\"barName\":\"sss_0.46644872\"},\"dddd\":\"2011-11-23 23:30:33\",\"listBar\":[{\"barAge\":-1090113522,\"barDate\":\"2011-11-23 23:30:33\",\"barName\":\"sss_0.83562374\"},{\"barAge\":478603194,\"barDate\":\"2011-11-23 23:30:33\",\"barName\":\"sss_0.59483266\"},{\"barAge\":1118357669,\"barDate\":\"2011-11-23 23:30:33\",\"barName\":\"sss_0.9961642\"}],\"listString\":[\"listString1\",\"listString2\",\"listString3\"],\"map\":{\"x\":\"s11111x\",\"y\":\"s22222y\",\"z\":\"s33333z\"},\"v_Date\":\"2011-11-23 23:30:33\",\"vboolean\":false,\"vbyte\":64,\"vchar\":\"x\",\"vdouble\":22.203,\"vfloat\":12.1,\"vint\":65535,\"vlong\":9999999,\"vshort\":128}";
        Gson gson = new Gson();
        Map<String,String> map = gson.fromJson(jsontext0,Map.class);
        try {
            String mapname = map.get("asd").getClass().getName();

           // System.out.println(mapname.getClass().getName());
            System.out.println(mapname);

        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(map.toString());
    }
}
