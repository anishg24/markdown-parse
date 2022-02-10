import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.*;

public class MarkdownParseTest {

    @Test
    public void getLinks() throws IOException {
        List<Path> mdFiles = Files.walk(Path.of("")).filter(f -> f.toString().endsWith(".md") && f.toString().startsWith("test-file")).sorted().collect(Collectors.toList());
        ArrayList[] expectedResults = new ArrayList[]{
                new ArrayList<>(List.of("https://something.com", "some-page.html")),
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

        System.out.println("I am a small change");
    }

}