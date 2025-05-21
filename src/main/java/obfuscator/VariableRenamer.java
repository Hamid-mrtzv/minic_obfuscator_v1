package obfuscator;

import java.util.regex.*;
import java.util.*;

public class VariableRenamer {
    public static String rename(String code) {
        Map<String, String> map = new HashMap<>();
        Set<String> reserved = Set.of(
                "main", "printf", "scanf", "return", "if", "else", "while", "for",
                "true", "false" // add more reserved keywords if needed
        );

        Matcher m = Pattern.compile("\\b(int|char|bool)\\s+(\\w+)").matcher(code);
        int counter = 0;

        while (m.find()) {
            String var = m.group(2);
            if (!reserved.contains(var)) {
                map.putIfAbsent(var, "v" + counter++);
            }
        }

        // Safely replace all matching identifiers
        for (Map.Entry<String, String> entry : map.entrySet()) {
            code = code.replaceAll("\\b" + Pattern.quote(entry.getKey()) + "\\b", entry.getValue());
        }

        return code;
    }
}
