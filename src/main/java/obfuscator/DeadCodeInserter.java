package obfuscator;

import java.util.Random;

public class DeadCodeInserter {

    public static String insert(String code) {
        StringBuilder result = new StringBuilder();
        Random rand = new Random();
        int counter = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            result.append(c);

            if (c == '{') {
                String deadCode = generateRandomDeadCode(counter++, rand);
                result.append("\n").append(deadCode).append("\n");
            }
        }

        return beautify(result.toString());
    }

    private static String generateRandomDeadCode(int index, Random rand) {
        int strategy = rand.nextInt(2); // 0, 1, or 2
        switch (strategy) {
            case 0:
                return deadCodeStrategy1(index, rand);
//            case 1:
//                return deadCodeStrategy2(index, rand);
            case 1:
                return deadCodeStrategy3(index, rand);
            default:
                return deadCodeStrategy1(index, rand); // fallback
        }
    }

    private static String deadCodeStrategy1(int index, Random rand) {
        int val = 1000 + rand.nextInt(1000);
        char dummyChar = (char) ('a' + (index % 26));
        String varPrefix = "_deadVar" + index;

        return String.format(
                "    int %s = %d;\n" +
                        "    char %s_c = '%c';\n" +
                        "    if ((%s %% 2 == 0) && (%s * %s != %d)) {\n" +
                        "        printf(\"Confusing unreachable logic %%c %%d\\n\", %s_c, %s);\n" +
                        "        %s = %s + 1;\n" +
                        "    }",
                varPrefix, val,
                varPrefix, dummyChar,
                varPrefix, varPrefix, varPrefix, val * 2,
                varPrefix, varPrefix,
                varPrefix, varPrefix
        );
    }

//    private static String deadCodeStrategy2(int index, Random rand) {
//        String varPrefix = "_junkVar" + index;
//        int a = rand.nextInt(500);
//        int b = rand.nextInt(500);
//
//        return String.format(
//                "    int %s = %d;\n" +
//                        "    int %s2 = %d;\n" +
//                        "    if (%s > %s2) {\n" +
//                        "        %s = %s - %s2;\n" +
//                        "        %s2 = %s + %s;\n" +
//                        "    }",
//                varPrefix, a,
//                varPrefix, b,
//                varPrefix, varPrefix + "2",
//                varPrefix, varPrefix, varPrefix + "2",
//                varPrefix + "2", varPrefix, varPrefix + "2"
//        );
//    }

    private static String deadCodeStrategy3(int index, Random rand) {
        String varPrefix = "_confuse" + index;
        int val = 1 + rand.nextInt(10);

        return String.format(
                "    int %s = %d;\n" +
                        "    while (%s < %d) {\n" +
                        "        %s++;\n" +
                        "    }",
                varPrefix, val,
                varPrefix, val + 5,
                varPrefix
        );
    }

    public static String beautify(String code) {
        StringBuilder formatted = new StringBuilder();
        int indentLevel = 0;
        String indent = "    "; // 4 spaces
        boolean lastLineEmpty = false;

        // Split lines by \n, keep blank lines too
        String[] lines = code.split("\\n");

        for (String line : lines) {
            // Remove *all* leading/trailing spaces to start fresh
            line = line.trim();

            // Skip multiple consecutive empty lines
            if (line.isEmpty()) {
                if (!lastLineEmpty) {
                    formatted.append("\n");
                    lastLineEmpty = true;
                }
                continue;
            }

            lastLineEmpty = false;

            // Decrease indent before lines starting with }
            if (line.startsWith("}")) {
                indentLevel = Math.max(0, indentLevel - 1);
            }

            // Add indentation
            for (int i = 0; i < indentLevel; i++) {
                formatted.append(indent);
            }

            formatted.append(line).append("\n");

            // Increase indent after lines ending with {
            if (line.endsWith("{")) {
                indentLevel++;
            }
        }

        return formatted.toString();
    }
}
