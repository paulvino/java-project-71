package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    private static final int STATUS_INDEX = 0;
    private static final int FIRST_VALUE_INDEX = 1;
    private static final int SECOND_VALUE_INDEX = 2;

    public static String formatPlain(Map<String, List<Object>> diffMap) {
        StringBuilder result = new StringBuilder();

        for (String key: diffMap.keySet()) {
            List<Object> statusAndValuesList = diffMap.get(key);

            Object status = statusAndValuesList.get(STATUS_INDEX);
            String value = isValueComplex(statusAndValuesList.get(FIRST_VALUE_INDEX));

            String valueForPaste = "";

            if (status.equals("changed")) {
                valueForPaste = getValueForChanged(statusAndValuesList);
            } else if (status.equals("added")) {
                valueForPaste = "' was added with value: " + value + "\n";
            } else if (status.equals("deleted")) {
                valueForPaste = "' was removed" + "\n";
            } else {
                continue;
            }

            result.append("Property '").append(key).append(valueForPaste);
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }

    private static String isValueComplex(Object value) {
        boolean isSimpleValue = value instanceof Integer || value instanceof Boolean;

        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return isSimpleValue ? value.toString() : "[complex value]";
        }
    }

    private static String getValueForChanged(List<Object> statusAndValuesList) {
        String value1 = isValueComplex(statusAndValuesList.get(FIRST_VALUE_INDEX));
        String value2 = isValueComplex(statusAndValuesList.get(SECOND_VALUE_INDEX));

        return "' was updated. From " + value1 + " to " + value2 + "\n";
    }
}
