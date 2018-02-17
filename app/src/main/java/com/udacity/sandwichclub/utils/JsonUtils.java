package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    //key values to parse JSON.
    private static final String SANDWICH_NAME = "name";
    private static final String SANDWICH_MAINNAME = "mainName";
    private static final String SANDWICH_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String SANDWICH_DESCRIPTION = "description";
    private static final String SANDWICH_IMAGE = "image";
    private static final String SANDWICH_INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) throws JSONException {
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();

        try {
            JSONObject JSONSandwich = new JSONObject(json);
            JSONObject JSONName = JSONSandwich.getJSONObject(SANDWICH_NAME);
            mainName = JSONName.optString(SANDWICH_MAINNAME);
            placeOfOrigin = JSONSandwich.optString(SANDWICH_PLACE_OF_ORIGIN);
            description = JSONSandwich.optString(SANDWICH_DESCRIPTION);
            image = JSONSandwich.optString(SANDWICH_IMAGE);

            alsoKnownAs = parseJSONArray(JSONName.getJSONArray(SANDWICH_ALSO_KNOWN_AS));
            ingredients = parseJSONArray(JSONSandwich.getJSONArray(SANDWICH_INGREDIENTS));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }

    private static List<String> parseJSONArray(JSONArray array){
        List<String> stringList = new ArrayList<>(0);

        if(array != null){
            for(int i = 0; i < array.length(); i++){
                try{
                    stringList.add(array.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringList;
    }
}
