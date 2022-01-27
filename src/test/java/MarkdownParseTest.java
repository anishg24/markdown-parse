import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MarkdownParseTest {
    @Test
    public void getLinks() throws IOException {
        List<Path> mdFiles = Files.walk(Path.of("src/test/resources/markdown"))
                .filter(f -> f.toString().endsWith(".md")).sorted().toList();

        ArrayList[] expectedResults = new ArrayList[]{
                new ArrayList<>(List.of("https://something.com", "some-page.html")),
                new ArrayList<>(List.of("https://www.google.com")),
                new ArrayList<>(List.of("https://something.com", "some-page.html")),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
        };

        for (int i = 0; i < mdFiles.size(); i++) {
            Path file = mdFiles.get(i);
            String markdown = Files.readString(file);
            ArrayList<String> output = MarkdownParse.getLinks(markdown);
            assertEquals("Testing correct output for " + file.getFileName(), expectedResults[i], output);
        }
    }
}
