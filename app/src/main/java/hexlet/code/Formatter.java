package hexlet.code;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String constructFormat(Map<String, Object> map1, Map<String, Object> map2,
                                       Map<String, String> diffMap, String format) throws IOException {
        return switch (format) {
            case "stylish" -> formatStylish(map1, map2, diffMap);
            default -> throw new IOException("Unknown format! => " + format);
        };
    }

    public static String formatStylish(Map<String, Object> map1, Map<String, Object> map2,
                                     Map<String, String> diffMap) {
        StringBuilder result = new StringBuilder("{\n");

        for (String key: diffMap.keySet()) {
            String keyStatus = diffMap.get(key);

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            switch (keyStatus) {
                case "unchanged":
                    result.append("    ").append(key).append(": ").append(value1).append("\n");
                    break;
                case "changed":
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                    break;
                case "deleted":
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    break;
                case "added":
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                    break;
                default:
                    break;
            }
        }

        result.append("}");

        return result.toString();
    }
}
