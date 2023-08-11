package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String fileContent, String fileFormat) throws IOException {
        return switch (fileFormat) {
            case "json" -> getDataJson(fileContent);
            case "yml", "yaml" -> getDataYaml(fileContent);
            default -> throw new IOException("Unknown file extension! -> " + fileFormat);
        };
    }

    private static Map<String, Object> getDataJson(String fileContent) throws IOException {
        ObjectMapper jm = new JsonMapper();

        return jm.readValue(fileContent, new TypeReference<>() { });
    }

    private static Map<String, Object> getDataYaml(String fileContent) throws IOException {
        ObjectMapper ym = new YAMLMapper();

        return ym.readValue(fileContent, new TypeReference<>() { });
    }
}
