package obfuscator;

public class Obfuscator {
    public String obfuscate(String code) {
        code = VariableRenamer.rename(code);
        code = DeadCodeInserter.insert(code);
        code = ControlFlowFlattener.flatten(code);
        return code;
    }
}
