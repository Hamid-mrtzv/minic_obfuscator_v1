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
                String deadCode = generateDeadCode(counter++, rand);
                result.append("\n").append(deadCode).append("\n");
            }
        }

        return result.toString();
    }

    private static String generateDeadCode(int index, Random rand) {
        int val = 1000 + rand.nextInt(1000);
        char dummyChar = (char) ('a' + (index % 26));
        String varPrefix = "_deadVar" + index;

        return String.format(
                "    int %s = %d;\n" +
                        "    char %s_c = '%c';\n" +
                        "    if ((%s %% 2 == 0) && (%s * %s != %d)) {\n" +
                        "        printf(\"Confusing unreachable logic %%c %%d\\n\", %s_c, %s);\n" +
                        "        %s = %s + 1;\n" +
                        "    }\n",
                varPrefix, val,
                varPrefix, dummyChar,
                varPrefix, varPrefix, varPrefix, val * 2,
                varPrefix, varPrefix,
                varPrefix, varPrefix
        );
    }
}
