package obfuscator;

import java.util.regex.*;
import java.util.*;

public class VariableRenamer {
    public static String rename(String code) {
        Map<String, String> map = new HashMap<>();
        Matcher m = Pattern.compile("\\b(int|char|bool)\\s+(\\w+)").matcher(code);
        int counter = 0;
        while (m.find()) {
            String var = m.group(2);
            map.putIfAbsent(var, "v" + counter++);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            code = code.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
        }
        return code;
    }
}
