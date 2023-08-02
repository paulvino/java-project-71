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
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        String actual = Differ.generate(FILEPATH_1_JSON, FILEPATH_2_JSON);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferGenerateYAML() throws IOException {
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
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
