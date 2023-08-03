package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String formatJson(Map<String, List<Object>> diffMap) throws JsonProcessingException {
        ObjectMapper mp = new ObjectMapper();
        return mp.writeValueAsString(diffMap);
    }
}
