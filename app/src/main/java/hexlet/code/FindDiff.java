package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class FindDiff {

    public static List<Map<String, Object>> getDiff(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> sortedKeys = getSortedKeys(file1, file2);

        List<Map<String, Object>> result = new ArrayList<>();

        for (String key: sortedKeys) {
            Object value1 = file1.getOrDefault(key, "Not contains the value.");
            Object value2 = file2.getOrDefault(key, "Not contains the value.");

            Map<String, Object> mapOfResults;

            if (!file1.containsKey(key)) {
                mapOfResults = getDiffMap("added", key, value2);
            } else if (!file2.containsKey(key)) {
                mapOfResults = getDiffMap("deleted", key, value1);
            } else if (Objects.equals(file1.get(key), file2.get(key))) {
                mapOfResults = getDiffMap("unchanged", key, value1);
            } else {
                mapOfResults = getDiffMap("changed", key, value1, value2);
            }

            result.add(mapOfResults);
        }

        return result;
    }

    public static Map<String, Object> getDiffMap(String status, String name, Object value) {
        Map<String, Object> diffMap = new LinkedHashMap<>();

        diffMap.put("status", status);
        diffMap.put("name", name);

        if (value == null) {
            diffMap.put("value1", null);
        } else {
            diffMap.put("value1", value);
        }

        return diffMap;
    }

    public static Map<String, Object> getDiffMap(String status, String name, Object value1, Object value2) {
        Map<String, Object> diffMap = getDiffMap(status, name, value1);

        if (value2 == null) {
            diffMap.put("value2", null);
        } else {
            diffMap.put("value2", value2);
        }

        return diffMap;
    }

    private static Set<String> getSortedKeys(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> sortedKeys = new TreeSet<>();

        sortedKeys.addAll(map1.keySet());
        sortedKeys.addAll(map2.keySet());

        return sortedKeys;
    }
}
