package api.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    public static String getCurrentTimeMillisText(String postfix){
        long timestamp = System.currentTimeMillis();
        return Long.toString(timestamp) + postfix;
    }

    public static String objectToJsonString(Object obj){
        String jsonString;
        ObjectMapper om = new ObjectMapper();

        try {
            jsonString = om.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return jsonString;
    }
}
