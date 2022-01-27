// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
//    public static ArrayList<String> getLinks(String markdown) {
//        ArrayList<String> toReturn = new ArrayList<>();
//        int index = 0;
//
//        while (index < markdown.length()) {
//            int startTarget = markdown.indexOf(")[", index);
//            int endTarget = markdown.indexOf(")", startTarget);
//            toReturn.add(markdown.substring(startTarget + 1, endTarget));
//            index = endTarget + 1;
//        }
//        return toReturn;
//    }

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
                if (!substring.contains(" ")) toReturn.add(markdown.substring(openParen + 2, closeParen));
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
