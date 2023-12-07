import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SpacePersonConverter {
    private static final Map<Character, Character> letterMapping = new HashMap<>();
    private static final Map<Character, Character> specialCharacterMapping = new HashMap<>();

    static {
        // Populate the letter mapping
        letterMapping.put('a', 'o');
        letterMapping.put('b', 'x');
        letterMapping.put('c', 'k');
        letterMapping.put('d', 'c');
        letterMapping.put('e', 'e');
        letterMapping.put('f', 'w');
        letterMapping.put('g', 's');
        letterMapping.put('h', 'i');
        letterMapping.put('i', 'l');
        letterMapping.put('j', 'm');
        letterMapping.put('k', 'n');
        letterMapping.put('l', 'b');
        letterMapping.put('m', 'v');
        letterMapping.put('n', 'z');
        letterMapping.put('o', 'a');
        letterMapping.put('p', 'u');
        letterMapping.put('q', 'y');
        letterMapping.put('r', 'q');
        letterMapping.put('s', 'f');
        letterMapping.put('t', 'd');
        letterMapping.put('u', 'r');
        letterMapping.put('v', 'g');
        letterMapping.put('w', 'h');
        letterMapping.put('x', 'j');
        letterMapping.put('y', 'p');
        letterMapping.put('z', 't');

        // Populate the special character mapping
        specialCharacterMapping.put('a', '!');
        specialCharacterMapping.put('b', '@');
        specialCharacterMapping.put('c', '#');
        specialCharacterMapping.put('d', '$');
        specialCharacterMapping.put('e', '%');
        specialCharacterMapping.put('f', '^');
        specialCharacterMapping.put('g', '&');
        specialCharacterMapping.put('h', '*');
        specialCharacterMapping.put('i', '(');
        specialCharacterMapping.put('j', ')');
        specialCharacterMapping.put('k', '-');
        specialCharacterMapping.put('l', '=');
        specialCharacterMapping.put('m', '_');
        specialCharacterMapping.put('n', '+');
        specialCharacterMapping.put('o', '[');
        specialCharacterMapping.put('p', ']');
        specialCharacterMapping.put('q', '{');
        specialCharacterMapping.put('r', '}');
        specialCharacterMapping.put('s', '`');
        specialCharacterMapping.put('t', '/');
        specialCharacterMapping.put('u', '?');
        specialCharacterMapping.put('v', '<');
        specialCharacterMapping.put('w', '>');
        specialCharacterMapping.put('x', '.');
        specialCharacterMapping.put('y', ',');
        specialCharacterMapping.put('z', '~');
    }

    public static String spacePersonStringConversion(String inputString, Map<Character, Character> mapping) {
        StringBuilder spacePersonString = new StringBuilder();
        for (char ch : inputString.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                spacePersonString.append(mapping.getOrDefault(ch, ch));
            } else {
                spacePersonString.append(ch);
            }
        }
        return spacePersonString.toString();
    }

    public static String sha256Hash(String inputString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(inputString.getBytes());
        StringBuilder hashStringBuilder = new StringBuilder();

        for (byte b : hashBytes) {
            hashStringBuilder.append(String.format("%02x", b));
        }

        return hashStringBuilder.toString();
    }

    public static String caesarCipher(String inputString, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : inputString.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                result.append((char) (((ch - base + shift) % 26) + base));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void bruteForceCaesarCipher(String inputString) {
        for (int shift = 0; shift < 26; shift++) {
            String decryptedString = caesarCipher(inputString, shift);
            System.out.println("Shift " + shift + ": " + decryptedString);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter the English string: ");
        String englishString = scanner.nextLine();

        // Convert to space person string using special characters
        String spacePersonString = spacePersonStringConversion(englishString, specialCharacterMapping);
        System.out.println("Space Person String with Special Characters: " + spacePersonString);

        // Compute SHA256 hash
        try {
            String hashValue = sha256Hash(spacePersonString);
            System.out.println("SHA256 Hash: " + hashValue);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found.");
        }

        // Caesar cipher with a 5-character shift
        String caesarResult = caesarCipher(englishString, 5);
        System.out.println("Caesar Cipher (5-character shift): " + caesarResult);

        // Brute force Caesar cipher
        System.out.println("\nBrute Force Caesar Cipher:");
        bruteForceCaesarCipher(englishString);
    }
}
