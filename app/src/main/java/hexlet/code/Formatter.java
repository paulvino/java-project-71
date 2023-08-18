package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String constructFormatFromMap(List<Map<String, Object>> diffList, String format) throws IOException {
        return switch (format) {
            case "stylish" -> Stylish.formatStylish(diffList);
            case "plain" -> Plain.formatPlain(diffList);
            case "json" -> Json.formatJson(diffList);
            default -> throw new IOException("Unknown format! => " + format);
        };
    }
}
