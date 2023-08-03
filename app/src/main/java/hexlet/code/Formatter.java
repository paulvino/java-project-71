package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String constructFormat(Map<String, List<Object>> diffMap,  String format) throws IOException {
        return switch (format) {
            case "stylish" -> Stylish.formatStylish(diffMap);
            case "plain" -> Plain.formatPlain(diffMap);
            case "json" -> Json.formatJson(diffMap);
            default -> throw new IOException("Unknown format! => " + format);
        };
    }
}
