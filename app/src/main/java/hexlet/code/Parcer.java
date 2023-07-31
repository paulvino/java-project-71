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
    public static Map<String, Object> parce(String filePath) throws IOException {
        if (filePath.endsWith(".json")) {
            return getDataJson(filePath);
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return getDataYaml(filePath);
        } else {
            throw new IOException("Unknown file format.");
        }
    }

    // returns Path to files (type Path)
    public static Path getAbsolutePath(String filePath) {
        Path pathToFile = Paths.get(filePath);
        return pathToFile.toAbsolutePath().normalize();
    }

    // returns Map from file-content JSON file using ObjectMapper
    public static Map<String, Object> getDataJson(String filePath) throws IOException {
        String fileContent = parceFile(filePath);
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> fileContentMap = om.readValue(fileContent, new TypeReference<>() { });

        return fileContentMap;
    }

    // returns Map from file-content YAML file using YAMLMapper
    public static Map<String, Object> getDataYaml(String filePath) throws IOException {
        String fileContent = parceFile(filePath);
        ObjectMapper om = new YAMLMapper();
        Map<String, Object> fileContentMap = om.readValue(fileContent, new TypeReference<>() { });

        return fileContentMap;
    }

    // returns file content
    public static String parceFile(String filePath) throws IOException {
        String fileContent = Files.readString(getAbsolutePath(filePath));
        return fileContent;
    }
}
