package com.madebyasshad.complainretriveapp;

import org.json.JSONObject;

public class getdata {

    private JSONObject jsonObject;
    public getdata(JSONObject jsn)
    {
        this.jsonObject=jsn;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
