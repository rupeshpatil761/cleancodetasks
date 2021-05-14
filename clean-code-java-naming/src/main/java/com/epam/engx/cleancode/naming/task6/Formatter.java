package com.epam.engx.cleancode.naming.task6;

public class Formatter {

    private static final String EDGE_CHARACTER = "+";
    private static final String CONTENT_EDGE_CHARACTER = "|";
    private static final String EDGE_CHARACTER_DELIMITER = "-";
    private static final String CONTENT_WORDS_DELIMITER = " _ ";

    public String formatContent(String word1, String word2) {
        String content = word1 + CONTENT_WORDS_DELIMITER + word2;
        String minuses = addSeperator(EDGE_CHARACTER_DELIMITER, content.length());
        return EDGE_CHARACTER +  minuses + EDGE_CHARACTER + "\n"
                + CONTENT_EDGE_CHARACTER + content + CONTENT_EDGE_CHARACTER + "\n"
                + EDGE_CHARACTER + minuses + EDGE_CHARACTER + "\n";
    }

    private String addSeperator(String symbol, int times) {
        String result = "";
        for (int i = 0; i < times; i++)
            result+=symbol;
        return result;
    }
}
