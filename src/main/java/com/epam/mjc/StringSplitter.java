package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        int startIndex = 0;

        for (int i = 0; i < source.length(); i++) {
            for (String delimiter : delimiters) {
                if (source.startsWith(delimiter, i)) {
                    if (i > startIndex) {
                        result.add(source.substring(startIndex, i));
                    }
                    startIndex = i + delimiter.length();
                    i = startIndex - 1;
                    break;
                }
            }
        }

        if (startIndex < source.length()) {
            result.add(source.substring(startIndex));
        }

        return result;
    }
}
