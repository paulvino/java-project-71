package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    @Test
    public void differTest1() throws IOException {
        String expected = "{\n"
                + " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        String actual = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json");
        assertThat(actual).isEqualTo(expected);
    }
}
