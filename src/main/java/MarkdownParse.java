import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            if (openBracket > 0 && markdown.charAt(openBracket - 1) == '!') break;

            int openParen = markdown.indexOf("](", openBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if (currentIndex > closeParen) break;
            else if (currentIndex < closeParen) currentIndex = closeParen + 1;

            if (openParen > 0){
                String substring = markdown.substring(openParen + 2, closeParen);
                if (!substring.contains(" ")) toReturn.add(substring);
            } else break;
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
