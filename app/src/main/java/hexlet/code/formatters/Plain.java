package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    public static String formatPlain(Map<String, Object> map1, Map<String, Object> map2,
                                     Map<String, String> diffMap) {
        StringBuilder result = new StringBuilder();

        for (String key: diffMap.keySet()) {
            String keyStatus = diffMap.get(key);

            Object value1 = map1.get(key);
            String verifiedValue1 = isValueComplex(value1);

            Object value2 = map2.get(key);
            String verifiedValue2 = isValueComplex(value2);

            switch (keyStatus) {
                case "changed":
                    result.append("Property ")
                            .append("'")
                            .append(key)
                            .append("'")
                            .append(" was updated. From ")
                            .append(verifiedValue1)
                            .append(" to ")
                            .append(verifiedValue2)
                            .append("\n");
                    break;
                case "added":
                    result.append("Property ")
                            .append("'")
                            .append(key)
                            .append("'")
                            .append(" was added with value: ")
                            .append(verifiedValue2)
                            .append("\n");
                    break;
                case "deleted":
                    result.append("Property ")
                            .append("'")
                            .append(key)
                            .append("'")
                            .append(" was removed")
                            .append("\n");
                default:
                    break;
            }
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public static String isValueComplex(Object value) {
        boolean isInstanceOfString = value instanceof String;
        boolean isInstanceOfInteger = value instanceof Integer;
        boolean isInstanceOfBoolean = value instanceof Boolean;

        boolean isSimpleValue = isInstanceOfInteger || isInstanceOfBoolean;

        boolean isNull = value == null;

        String complexityCheckResult;

        if (isNull) {
            complexityCheckResult = "null";
        } else if (isInstanceOfString) {
            complexityCheckResult = "'" + value + "'";
        } else {
            complexityCheckResult = isSimpleValue ? value.toString() : "[complex value]";
        }

        return complexityCheckResult;
    }
}
