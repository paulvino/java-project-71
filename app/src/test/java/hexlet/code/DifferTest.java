package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class DifferTest {
    private static final String FILEPATH_1_JSON = "./src/test/resources/file1.json";
    private static final String FILEPATH_2_JSON = "./src/test/resources/file2.json";
    private static final String FILEPATH_1_YAML = "./src/test/resources/file1.yml";
    private static final String FILEPATH_2_YAML = "./src/test/resources/file2.yml";
    private static final String INCORRECT_PATH = "123098";

    @BeforeAll
    public static void beforeAll() {

    }

    @Test
    public void testDifferGenerateJSON() throws IOException {
        String expected = "{\n"
                + " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        String actual = Differ.generate(FILEPATH_1_JSON, FILEPATH_2_JSON);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferGenerateYAML() throws IOException {
        String expected = "{\n"
                + " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        String actual = Differ.generate(FILEPATH_1_YAML, FILEPATH_2_YAML);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferGenerateIncorrectPath() {
        var thrown = catchThrowable(
                () -> Differ.generate(INCORRECT_PATH, INCORRECT_PATH)
        );

        assertThat(thrown).isInstanceOf(IOException.class);
    }
}
