package hexlet.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Differ {

    // returns difference between two JSON / YAML files
    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> fileContent1 = Parcer.parce(filePath1);
        Map<String, Object> fileContent2 = Parcer.parce(filePath2);

        String result = getDifference(fileContent1, fileContent2);

        return result;
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
    public static String getDifference(Map<String, Object> map1, Map<String, Object> map2) {
        List<String> sortedKeys = getSortedKeys(map1, map2);
        Map<String, Object> differencesMap = new LinkedHashMap<>();

        for (String key: sortedKeys) {
            String keyStatus = checkKeys(map1, map2, key);

            switch (keyStatus) {
                case "both and equals":
                    String newKey = "  " + key;
                    differencesMap.put(newKey, map1.get(key));
                    break;
                case "both not equal":
                    String newKeyFirst = "- " + key;
                    String newKeySecond = "+ " + key;
                    differencesMap.put(newKeyFirst, map1.get(key));
                    differencesMap.put(newKeySecond, map2.get(key));
                    break;
                default:
                    String prefix = map1.containsKey(key) ? "- " : "+ ";
                    String newKeyDef = prefix + key;
                    Object value = map1.containsKey(key) ? map1.get(key) : map2.get(key);
                    differencesMap.put(newKeyDef, value);
                    break;
            }
        }

        String differenceString = getDifferenceString(differencesMap);

        return differenceString;
    }

    // returns keys status in two maps:
    public static String checkKeys(Map<String, Object> map1, Map<String, Object> map2, String key) {
        boolean firstHasKey = map1.containsKey(key);
        boolean secondHasKey = map2.containsKey(key);
        boolean bothHaveKey = firstHasKey && secondHasKey;

        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

        if (bothHaveKey && value1.equals(value2)) {
            return "both and equals";
        } else if (bothHaveKey && !(value1.equals(value2))) {
            return "both not equal";
        } else {
            return "default";
        }
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
