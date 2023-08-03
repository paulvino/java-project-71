package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class FindDiff {

    public static Map<String, List<Object>> getDifference(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> sortedKeys = getSortedKeys(map1, map2);

        Map<String, List<Object>> result = new LinkedHashMap<>();

        for (String key: sortedKeys) {
            List<Object> list;

            if (!map1.containsKey(key)) {
                list = getList("added", map2.get(key));
            } else if (!map2.containsKey(key)) {
                list = getList("deleted", map1.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                list = getList("unchanged", map1.get(key));
            } else {
                list = getList("changed", map1.get(key), map2.get(key));
            }

            result.put(key, list);
        }

        return result;
    }

    private static Set<String> getSortedKeys(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> sortedKeys = new TreeSet<>();

        sortedKeys.addAll(map1.keySet());
        sortedKeys.addAll(map2.keySet());

        return sortedKeys;
    }

    private static List<Object> getList(String status, Object value1, Object value2) {
        List<Object> resultList = new ArrayList<>();

        resultList.add(status);
        resultList.add(value1);
        resultList.add(value2);

        return resultList;
    }

    private static List<Object> getList(String status, Object value) {
        List<Object> resultList = new ArrayList<>();

        resultList.add(status);
        resultList.add(value);

        return resultList;
    }
}
