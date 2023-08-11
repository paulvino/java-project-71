package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        String file1Format = getFileFormat(filePath1);
        String file2Format = getFileFormat(filePath2);

        String file1Content = getContent(filePath1);
        String file2Content = getContent(filePath2);

        Map<String, Object> map1 = Parser.parse(file1Content, file1Format);
        Map<String, Object> map2 = Parser.parse(file2Content, file2Format);

        Map<String, List<Object>> diffMap = FindDiff.getDifference(map1, map2);

        return Formatter.constructFormat(diffMap, formatName);
    }

    private static String getFileFormat(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");

        return dotIndex > 0 ? filePath.substring(dotIndex + 1) : "";
    }

    private static Path getAbsolutePath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static String getContent(String filePath) throws IOException {
        return Files.readString(getAbsolutePath(filePath));
    }
}
