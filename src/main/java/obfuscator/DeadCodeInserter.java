package obfuscator;

public class DeadCodeInserter {
    public static String insert(String code) {
        String dead = "int _dead = 0;\nif (_dead == 1) {\nprintf(\"dead\\n\");\n}\n";
        return code.replace("{", "{\n" + dead);
    }
}
