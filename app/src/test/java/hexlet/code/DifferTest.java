package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class DifferTest {

    private static String file1JsonPath;
    private static String file2JsonPath;
    private static String file1YmlPath;
    private static String file2YmlPath;
    private static String incorrectPath;

    private static Path getPath(String fileName) {
        return fileName.endsWith(".txt")
                ? Paths.get("src", "test", "resources", "results", fileName).toAbsolutePath().normalize()
                : Paths.get("src", "test", "resources", fileName).toAbsolutePath().normalize();
    }

    private static String getData(String fileName) throws Exception {
        Path filePath = getPath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() {
        file1JsonPath = getPath("file1.json").toString();
        file2JsonPath = getPath("file2.json").toString();
        file1YmlPath = getPath("file1.yml").toString();
        file2YmlPath = getPath("file2.yml").toString();
        incorrectPath = "123";
    }

    @Test
    public void testDifferGenerateStylishJSON() throws Exception {
        String expected = getData("stylish.txt");
        String actual = Differ.generate(file1JsonPath, file2JsonPath);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferGenerateStylishYAML() throws Exception {
        String expected = getData("stylish.txt");
        String actual = Differ.generate(file1YmlPath, file2YmlPath);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferGeneratePlainJSON() throws Exception {
        String expected = getData("plain.txt");
        String actual = Differ.generate(file1JsonPath, file2JsonPath, "plain");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferGeneratePlainYAML() throws Exception {
        String expected = getData("plain.txt");
        String actual = Differ.generate(file1YmlPath, file2YmlPath, "plain");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferGenerateJson() throws Exception {
        String expected = getData("json.txt");
        String actual = Differ.generate(file1JsonPath, file2JsonPath, "json");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferGenerateIncorrectPath() {
        var thrown = catchThrowable(
                () -> Differ.generate(incorrectPath, incorrectPath)
        );

        assertThat(thrown).isInstanceOf(Exception.class);
    }
}
