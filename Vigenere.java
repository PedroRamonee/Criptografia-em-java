import java.util.Scanner;
import java.util.Vector;
import java.io.*;
import java.util.ArrayList;

public class Vigenere {

    public static void main(String[] args) {

        String word, key;
        int panel;
        Scanner scanner = new Scanner(System.in);

        Tabela tabela = new Tabela();
        System.out.println("Digite a palavra:");
        word = scanner.nextLine();
        System.out.println("Digite a chave:");
        key = scanner.nextLine();
        System.out.println("Digite 1 para criptografar e 2 para descriptografar:");
        panel = scanner.nextInt();
        String resultado = tabela.code(word, key, panel);
        System.out.println(resultado);
    }
}

class Tabela {

    private ArrayList<ArrayList<Character>> tabela = new ArrayList<>();
    private ArrayList<Character> findLetter = new ArrayList<>();

    public Tabela() {

        for (int i = 0; i < 26; i++) {
            char letra = 'a';
            ArrayList<Character> alfabeto = new ArrayList<>();
            letra += i;
            for (int j = 0; j < 26; j++) {
                if (letra != 'z') {
                    alfabeto.add(letra);
                    letra++;
                } else {
                    alfabeto.add(letra);
                    letra = 'a';
                }
            }
            tabela.add(alfabeto);
        }

        char letra = 'a';
        for (int i = 0; i < 26; i++) {
            findLetter.add(letra);
            letra += 1;
        }

    }

    public void imprimeTabela() {
        System.out.println(tabela);
    }

    public String code(String palavra, String chave, int panel) {
        String troca = "";
        int controle = 0;
        char letter;

        System.out.println("Palavra Original = " + palavra);

        for (int i = 0; i < palavra.length(); i++) {

            letter = palavra.charAt(i);
            if (letter != ' ') {
                if (controle == chave.length()) {
                    controle = 0;
                }

                letter = chave.charAt(controle);
                troca = troca.concat(Character.toString(letter));
                controle++;
            }

        }

        String aux = palavra;
        String result = "";

        if (panel == 1) {

            System.out.println("Chave cifrada = " + troca);

            for (int i = 0; i < palavra.length(); i++) {

                letter = palavra.charAt(i);

                if (letter != ' ') {
                    letter = tabela.get(getIndex(aux.charAt(i))).get(getIndex(troca.charAt(i)));
                }

                result = result.concat(Character.toString(letter));
            }
        } else if (panel == 2) {

            for (int i = 0; i < palavra.length(); i++) {

                letter = palavra.charAt(i);

                if (letter != ' ') {
                    letter = findChar(getIndex(troca.charAt(i)), aux.charAt(i));
                }

                result = result.concat(Character.toString(letter));
            }

        } else {
            System.out.println("Valor Invalido");
        }

        return result;
    }

    private int getIndex(char letra) {
        for (int i = 0; i < findLetter.size(); i++) {
            if (letra == findLetter.get(i)) {
                return i;
            }
        }
        return 0;
    }

    private char findChar(int index, char letra) {
        for (int i = 0; i < tabela.size(); i++) {
            if (tabela.get(i).get(index) == letra) {
                return tabela.get(0).get(i);
            }
        }

        return ' ';
    }
}