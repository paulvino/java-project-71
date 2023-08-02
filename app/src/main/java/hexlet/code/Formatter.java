package hexlet.code;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.formatters.Plain.formatPlain;
import static hexlet.code.formatters.Stylish.formatStylish;

public class Formatter {
    public static String constructFormat(Map<String, Object> map1, Map<String, Object> map2,
                                       Map<String, String> diffMap, String format) throws IOException {
        return switch (format) {
            case "stylish" -> formatStylish(map1, map2, diffMap);
            case "plain" -> formatPlain(map1, map2, diffMap);
            default -> throw new IOException("Unknown format! => " + format);
        };
    }
}
