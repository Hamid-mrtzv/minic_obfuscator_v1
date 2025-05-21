package obfuscator;

public class ControlFlowFlattener {
    public static String flatten(String code) {
        if (!code.contains("main")) return code;
        String pattern = "int main\\(\\) \\{";
        String switchStart = "int main() {\nint _pc = 0;\nwhile(1) {\nswitch(_pc) {\ncase 0:\n";
        String switchEnd = "\nbreak;\ndefault: return 0;\n}\n}\n}";
        code = code.replaceFirst(pattern, switchStart);
        code = code.replaceAll("return 0;", "_pc = -1;\nbreak;");
        return code + switchEnd;
    }
}
