package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class DifferTest {
    private static final String FILEPATH_1_JSON = "./src/test/resources/file1.json";
    private static final String FILEPATH_2_JSON = "./src/test/resources/file2.json";
    private static final String FILEPATH_1_YAML = "./src/test/resources/file1.yml";
    private static final String FILEPATH_2_YAML = "./src/test/resources/file2.yml";
    private static final String FILEPATH_1_SMALL_JSON = "./src/test/resources/file1small.json";
    private static final String FILEPATH_2_SMALL_YAML = "./src/test/resources/file2small.yml";
    private static final String INCORRECT_PATH = "123098";

    private static final String EXPECTED_STYLISH = "{\n"
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

    private static final String EXPECTED_PLAIN = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";

    private static final String EXPECTED_STYLISH_SMALL = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    @Test
    public void testDifferGenerateStylishJSON() throws IOException {
        String actual = Differ.generate(FILEPATH_1_JSON, FILEPATH_2_JSON);
        assertThat(actual).isEqualTo(EXPECTED_STYLISH);
    }

    @Test
    public void testDifferGenerateStylishYAML() throws IOException {
        String actual = Differ.generate(FILEPATH_1_YAML, FILEPATH_2_YAML);
        assertThat(actual).isEqualTo(EXPECTED_STYLISH);
    }

    @Test
    public void testDifferGeneratePlainJSON() throws IOException {
        String actual = Differ.generate(FILEPATH_1_JSON, FILEPATH_2_JSON, "plain");
        assertThat(actual).isEqualTo(EXPECTED_PLAIN);
    }

    @Test
    public void testDifferGeneratePlainYAML() throws IOException {
        String actual = Differ.generate(FILEPATH_1_YAML, FILEPATH_2_YAML, "plain");
        assertThat(actual).isEqualTo(EXPECTED_PLAIN);
    }

    @Test
    public void testDifferGenerateStylishSmall() throws IOException {
        String actual = Differ.generate(FILEPATH_1_SMALL_JSON, FILEPATH_2_SMALL_YAML);
        assertThat(actual).isEqualTo(EXPECTED_STYLISH_SMALL);
    }

    @Test
    public void testDifferGenerateIncorrectPath() {
        var thrown = catchThrowable(
                () -> Differ.generate(INCORRECT_PATH, INCORRECT_PATH)
        );

        assertThat(thrown).isInstanceOf(IOException.class);
    }
}
