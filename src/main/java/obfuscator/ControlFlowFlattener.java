package obfuscator;

import java.util.regex.*;
import java.util.*;

public class ControlFlowFlattener {

    public static String flatten(String code) {
        // Match the entire main function body
        Pattern mainPattern = Pattern.compile("int\\s+main\\s*\\(\\s*\\)\\s*\\{([\\s\\S]*?)\\}");
        Matcher matcher = mainPattern.matcher(code);

        // If main is not found, return original code
        if (!matcher.find()) return code;

        String mainBody = matcher.group(1).trim();

        // Split main body into individual statements or block ends
        String[] lines = mainBody.split("(?<=;|\\{|\\})\\s*\\n");

        StringBuilder flattened = new StringBuilder();
        StringBuilder switchBody = new StringBuilder();

        // Start new obfuscated main method
        flattened.append("int main() {\n");
        flattened.append("    int _pc = 0;\n");
        flattened.append("    while (1) {\n");
        flattened.append("    switch (_pc) {\n");

        int caseCounter = 0;

        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.isEmpty()) continue;

            switchBody.append("            case ").append(caseCounter).append(":\n");

            // If the line is a return statement
            if (line.matches("return\\s+\\d+\\s*;")) {
                switchBody.append("                // original: ").append(line).append("\n");
                String returnValue = line.replaceAll("return\\s+", "").replace(";", "").trim();
                switchBody.append("                return ").append(returnValue).append(";\n");
            } else {
                switchBody.append("                ").append(line).append("\n");
                switchBody.append("                _pc = ").append(caseCounter + 1).append(";\n");
                switchBody.append("                break;\n");
            }

            caseCounter++;
        }

        // Default case
        switchBody.append("            default:\n");
        switchBody.append("                return 0;\n");

        // Close all brackets
        flattened.append(switchBody);
        flattened.append("        }\n"); // close switch
        flattened.append("    }\n");     // close while
        flattened.append("}\n");         // close main

        // Replace original main function with obfuscated version
        return matcher.replaceFirst(Matcher.quoteReplacement(flattened.toString()));
    }
}
