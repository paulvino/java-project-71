package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    private static final int STATUS_INDEX = 0;
    private static final int FIRST_VALUE_INDEX = 1;
    private static final int SECOND_VALUE_INDEX = 2;

    public static String formatStylish(Map<String, List<Object>> diffMap) {
        StringBuilder result = new StringBuilder("{\n");

        for (String key: diffMap.keySet()) {
            List<Object> statusAndValuesList = diffMap.get(key);

            Object status = statusAndValuesList.get(STATUS_INDEX);
            Object value = statusAndValuesList.get(FIRST_VALUE_INDEX);

            if (status.equals("unchanged")) {
                result.append("    ").append(key).append(": ").append(value).append("\n");
            } else if (status.equals("changed")) {
                result.append("  - ").append(key).append(": ").append(value).append("\n");
                result.append("  + ").append(key).append(": ").append(statusAndValuesList.get(SECOND_VALUE_INDEX))
                        .append("\n");
            } else if (status.equals("deleted")) {
                result.append("  - ").append(key).append(": ").append(value).append("\n");
            } else if (status.equals("added")) {
                result.append("  + ").append(key).append(": ").append(value).append("\n");
            }
        }

        result.append("}");

        return result.toString();
    }
}
