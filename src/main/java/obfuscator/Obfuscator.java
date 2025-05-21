package obfuscator;

public class Obfuscator {
    public String obfuscate(String code) {
        code = ControlFlowFlattener.flatten(code);
        code = VariableRenamer.rename(code);
        code = DeadCodeInserter.insert(code);
        return code;
    }
}
