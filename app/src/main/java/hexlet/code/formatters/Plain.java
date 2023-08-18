package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String formatPlain(List<Map<String, Object>> diffList) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> el: diffList) {

            Object status = el.get("status");
            Object value1 = stringify(el.get("value1"));

            String resultString = "";

            if (status.equals("changed")) {
                resultString = "' was updated. From " + value1 + " to " + stringify(el.get("value2")) + "\n";
            } else if (status.equals("added")) {
                resultString = "' was added with value: " + value1 + "\n";
            } else if (status.equals("deleted")) {
                resultString = "' was removed" + "\n";
            } else {
                continue;
            }

            Object name = el.get("name");
            result.append("Property '").append(name).append(resultString);
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }

    private static String stringify(Object value) {
        boolean isSimpleValue = value instanceof Integer || value instanceof Boolean;

        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return isSimpleValue ? value.toString() : "[complex value]";
        }
    }
}
