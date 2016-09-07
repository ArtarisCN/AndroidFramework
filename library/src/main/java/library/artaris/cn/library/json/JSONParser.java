package library.artaris.cn.library.json;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

public class JSONParser {
        private static JSONParser instance;
        private ObjectMapper mapper;

        private JSONParser(){
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static JSONParser getInstance(){
        if (instance == null)
            instance = new JSONParser();

        return instance;
    }
    public <T> T parserJSONObject(@NonNull JSONObject jobj, Class<T> clazz){
        if (jobj == null)
            return null;

        try {
            return mapper.readValue(jobj.toString(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}