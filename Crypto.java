import java.util.Scanner;

public class Crypto {

    public static String Code(String word, int value) {

        int size = word.length();
        char letter;
        String result = "";

        for (int i = 0; i < size; i++) {

            letter = word.charAt(i);
            int j = 0;
            if (letter != ' ') {
                if (value > 0) {
                    while (j < value) {
                        if (letter != 'z' && letter != 'Z') {
                            letter += 1;
                        } else {
                            if (letter == 'z') {
                                letter = 'a';
                            } else {
                                letter = 'A';
                            }
                        }
                        j++;
                    }
                } else {
                    while (j > value) {
                        if (letter != 'a' && letter != 'A') {
                            letter -= 1;
                        } else {
                            if (letter == 'a') {
                                letter = 'z';
                            } else {
                                letter = 'Z';
                            }
                        }
                        j--;
                    }
                }
            }

            result = result.concat(Character.toString(letter));

        }
        return result;
    }

    public static void main(String[] args) {
        String word, crypto = "";
        int value, panel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a palavra:");
        word = scanner.nextLine();
        System.out.println("Digite a chave:");
        value = scanner.nextInt();
        System.out.println("Digite 1 para criptografar e 2 para descriptografar:");
        panel = scanner.nextInt();
        if (panel == 1) {
            crypto = Code(word, value);
        } else if (panel == 2) {
            value *= -1;
            crypto = Code(word, value);
        } else {
            System.out.println("Valor inválido");
        }

        System.out.println("Resultado:");
        System.out.println(crypto);
        System.out.println("Original:");
        crypto = Code(crypto, value * -1);
        System.out.println(crypto);

    }
}
