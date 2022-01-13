package com.roysten.fetch_rewards_assignment.models;

import com.roysten.fetch_rewards_assignment.comparator.AlphanumericSortComparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    private static List<Data> dataList;
    private static Map<Integer, List<String>> map;
    private int id;
    private int listId;
    private String name;


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
        if (Data.dataList != null)
            dataList = Data.dataList;

        map = new HashMap<>();

        for (int i = 0; i < dataList.size(); i++) {
            int listIdKey = dataList.get(i).getListId();
            String nameValue = dataList.get(i).getName();

            if (!map.containsKey(listIdKey)) {
                map.putIfAbsent(listIdKey, new ArrayList<>());
            }
            map.get(listIdKey).add(nameValue);
        }


        Comparator<String> comp = AlphanumericSortComparator.NUMERICAL_ORDER;

        for (Integer i : map.keySet()) {
            Collections.sort(map.get(i), comp);
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
