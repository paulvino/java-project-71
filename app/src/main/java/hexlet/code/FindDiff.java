package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class FindDiff {

    public static Map<String, String> getDifference(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> sortedKeys = getSortedKeys(map1, map2);
        Map<String, String> keyStatusMap = new LinkedHashMap<>();

        for (String key: sortedKeys) {
            boolean map1HasKey = map1.containsKey(key);
            boolean map2HasKey = map2.containsKey(key);

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (!map1HasKey) {
                keyStatusMap.put(key, "added");
            } else if (!map2HasKey) {
                keyStatusMap.put(key, "deleted");
            } else if (Objects.equals(value1, value2)) {
                keyStatusMap.put(key, "unchanged");
            } else {
                keyStatusMap.put(key, "changed");
            }
        }

        return keyStatusMap;
    }

    private static Set<String> getSortedKeys(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> sortedKeys = new TreeSet<>();

        sortedKeys.addAll(map1.keySet());
        sortedKeys.addAll(map2.keySet());

        return sortedKeys;
    }
}
