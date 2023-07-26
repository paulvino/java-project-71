package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Parcer {
    public static String parce(String fileContent1, String fileContent2) throws JsonProcessingException {
        Map<String, Object> map1 = getData(fileContent1);
        Map<String, Object> map2 = getData(fileContent2);

        Map<String, Object> resultMap = getDifference(map1, map2);
        String result = getDifferenceString(resultMap);

        return result;
    }

    // returns Map from file-content using ObjectMapper
    public static Map<String, Object> getData(String fileContent) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> fileContentMap = om.readValue(fileContent, new TypeReference<>() { });

        return fileContentMap;
    }

    // returns sorted List of keys from two Maps
    public static List<String> getSortedKeys(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> allKeys = new HashMap<>();
        allKeys.putAll(map1);
        allKeys.putAll(map2);

        return allKeys.keySet().stream()
                .sorted()
                .toList();
    }

    // returns Map contains differences between two maps:
    public static Map<String, Object> getDifference(Map<String, Object> map1, Map<String, Object> map2) {
        List<String> allSortedKeys = getSortedKeys(map1, map2);

        Map<String, Object> differencesMap = new LinkedHashMap<>();

        for (String key: allSortedKeys) {
            boolean firstMapHasKey = map1.containsKey(key);
            boolean secondMapHasKey = map2.containsKey(key);
            boolean bothMapsHaveKey = firstMapHasKey && secondMapHasKey;

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (bothMapsHaveKey && value1.equals(value2)) {
                String newKey = " " + " " + key;
                differencesMap.put(newKey, value1);
            } else if (bothMapsHaveKey && !(value1.equals(value2))) {
                String newKey1 = "-" + " " + key;
                String newKey2 = "+" + " " + key;
                differencesMap.put(newKey1, value1);
                differencesMap.put(newKey2, value2);
            } else {
                String prefix = firstMapHasKey ? "-" : "+";
                String newKey = prefix + " " + key;
                Object value = firstMapHasKey ? value1 : value2;
                differencesMap.put(newKey, value);
            }
        }

        return differencesMap;
    }

    // returns valid differences in String type
    public static String getDifferenceString(Map<String, Object> differenceMap) {
        StringJoiner sj = new StringJoiner("\n ", "{\n ", "\n}");

        for (Map.Entry<String, Object> entry: differenceMap.entrySet()) {
            sj.add(entry.getKey() + ": " + entry.getValue());
        }

        return sj.toString();
    }
}
