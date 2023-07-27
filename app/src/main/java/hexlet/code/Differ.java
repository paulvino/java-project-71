package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {

    // returns difference between two JSON files
    public static String generate(String filePath1, String filePath2) throws IOException {
        String fileContent1 = getFileContent(filePath1);
        String fileContent2 = getFileContent(filePath2);

        String result = Parcer.parce(fileContent1, fileContent2);

        return result;
    }

    // returns Path to files (type Path)
    public static Path getAbsolutePath(String filePath) {
        Path pathToFile = Paths.get(filePath);
        return pathToFile.toAbsolutePath().normalize();
    }

    // return content from file in String type
    public static String getFileContent(String filePath) throws IOException {
        return Files.readString(getAbsolutePath(filePath));
    }
}
