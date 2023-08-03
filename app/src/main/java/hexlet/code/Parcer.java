package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parcer {
    public static Map<String, Object> parce(String filePath, String fileFormat) throws IOException {
        switch (fileFormat) {
            case "json":
                return getDataJson(filePath);
            case "yml", "yaml":
                return getDataYaml(filePath);
            default:
                throw new IOException("Unknown file extension! -> " + fileFormat);
        }
    }

    private static Path getAbsolutePath(String filePath) {
        Path pathToFile = Paths.get(filePath);

        return pathToFile.toAbsolutePath().normalize();
    }

    private static Map<String, Object> getDataJson(String filePath) throws IOException {
        String fileContent = parceFile(filePath);
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> fileContentMap = om.readValue(fileContent, new TypeReference<>() { });

        return fileContentMap;
    }

    private static Map<String, Object> getDataYaml(String filePath) throws IOException {
        String fileContent = parceFile(filePath);
        ObjectMapper om = new YAMLMapper();
        Map<String, Object> fileContentMap = om.readValue(fileContent, new TypeReference<>() { });

        return fileContentMap;
    }

    private static String parceFile(String filePath) throws IOException {
        String fileContent = Files.readString(getAbsolutePath(filePath));

        return fileContent;
    }
}
