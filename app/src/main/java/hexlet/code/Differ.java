package hexlet.code;

import java.io.IOException;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        String file1Format = getFileFormat(filePath1);
        String file2Format = getFileFormat(filePath2);

        Map<String, Object> map1 = Parcer.parce(filePath1, file1Format);
        Map<String, Object> map2 = Parcer.parce(filePath2, file2Format);

        Map<String, String> diffMap = FindDiff.getDifference(map1, map2);

        String result = Formatter.constructFormat(map1, map2, diffMap, formatName);

        return result;
    }

    private static String getFileFormat(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");

        return dotIndex > 0 ? filePath.substring(dotIndex + 1) : "";
    }
}
