package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String formatStylish(List<Map<String, Object>> diffList) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> el: diffList) {

            Object status = el.get("status");
            Object name = el.get("name");
            Object value1 = el.get("value1");

            if (status.equals("unchanged")) {
                result.append("    ").append(name).append(": ").append(value1).append("\n");
            } else if (status.equals("changed")) {
                result.append("  - ").append(name).append(": ").append(value1).append("\n");
                result.append("  + ").append(name).append(": ").append(el.get("value2"))
                        .append("\n");
            } else if (status.equals("deleted")) {
                result.append("  - ").append(name).append(": ").append(value1).append("\n");
            } else if (status.equals("added")) {
                result.append("  + ").append(name).append(": ").append(value1).append("\n");
            }
        }

        result.append("}");

        return result.toString();
    }
}
