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
                .filter(f -> f.toString().endsWith(".md")).sorted((p1, p2) -> {
                    String n1 = p1.getFileName().toString();
                    String n2 = p2.getFileName().toString();

                    int start1 = n1.indexOf("-", n1.indexOf("-") + 1);
                    int end1 = n1.indexOf(".md");
                    int val1 = Integer.parseInt(n1.substring(start1 + 1, end1));

                    int start2 = n2.indexOf("-", n2.indexOf("-") + 1);
                    int end2 = n2.indexOf(".md");
                    int val2 = Integer.parseInt(n2.substring(start2 + 1, end2));

                    return Integer.compare(val1, val2);
                }).toList();

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
                new ArrayList<>(List.of("https://www.google.com")),
        };

        for (int i = 0; i < mdFiles.size(); i++) {
            Path file = mdFiles.get(i);
            String markdown = Files.readString(file);
            ArrayList<String> output = MarkdownParse.getLinks(markdown);
            assertEquals("Testing correct output for " + file.getFileName(), expectedResults[i], output);
        }
    }
}
