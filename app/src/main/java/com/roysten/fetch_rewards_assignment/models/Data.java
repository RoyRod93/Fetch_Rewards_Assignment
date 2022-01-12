package com.roysten.fetch_rewards_assignment.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Parcel
public class Data {
    private int id;
    private int listId;
    private String name;

    private static List<Data> dataList;
    private static Map<Integer, List<String>> map;


    // empty constructor needed by the Parceler library
    public Data() {

    }

    public Data(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        listId = jsonObject.getInt("listId");
        name = jsonObject.getString("name");
    }

    public static List<Data> fromJsonArray(JSONArray dataJsonArray) throws JSONException {
        dataList = new ArrayList<>();

        for (int i = 0; i < dataJsonArray.length(); i++) {
            if ((!dataJsonArray.getJSONObject(i).getString("name").equals("null")) &&
                    (!dataJsonArray.getJSONObject(i).getString("name").isEmpty()) &&
                    (!dataJsonArray.getJSONObject(i).getString("name").equals(""))) {
                dataList.add(new Data(dataJsonArray.getJSONObject(i)));
            }
        }

        return dataList;
    }

    public static Map<Integer, List<String>> getMap(List<Data> dataList) {
        if(Data.dataList != null)
            dataList = Data.dataList;

        map = new HashMap<>();

        for(int i = 0; i < dataList.size(); i++) {
            int listIdKey = dataList.get(i).getListId();
            String nameValue = dataList.get(i).getName();

            if (!map.containsKey(listIdKey)) {
                map.putIfAbsent(listIdKey, new ArrayList<>());
            }
            map.get(listIdKey).add(nameValue);
        }

        return map;

    }


    public int getId() {
        return id;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }


}
