package obfuscator;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = Files.readString(Paths.get("src/resources/input.mc"));
        Obfuscator obfuscator = new Obfuscator();
        String output = obfuscator.obfuscate(input);
        System.out.println(" ");
        Files.writeString(Paths.get("src/resources/output.mc"), output);

        System.out.println("\nSaved to src/resources/output.mc ✅");
    }
}
