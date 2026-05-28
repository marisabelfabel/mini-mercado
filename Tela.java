
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

public final class Tela {

    private static Scanner entrada;

    public static void iniciar() {
        entrada = criarScanner();
    }

    public static Scanner criarScanner() {
        return new Scanner(new InputStreamReader(System.in, charsetEntrada()));
    }

    public static Charset charsetEntrada() {
        java.io.Console console = System.console();
        if (console != null) {
            return console.charset();
        }

        String stdin = System.getProperty("stdin.encoding");
        if (stdin != null && !stdin.isEmpty()) {
            return Charset.forName(stdin);
        }

        String nativo = System.getProperty("native.encoding");
        if (nativo != null && !nativo.isEmpty()) {
            return Charset.forName(nativo);
        }

        return Charset.defaultCharset();
    }

    public static void println(String texto) {
        System.out.println(texto);
    }

    public static void println() {
        System.out.println();
    }

    public static String lerLinha() {
        return entrada.nextLine().trim();
    }

    public static int lerInt() {
        while (true) {
            String linha = lerLinha();
            if (linha.isEmpty()) {
                println("Digite um número e pressione Enter.");
                continue;
            }
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public static double lerDouble() {
        while (true) {
            String linha = lerLinha();
            if (linha.isEmpty()) {
                println("Digite um número e pressione Enter.");
                continue;
            }
            try {
                return Double.parseDouble(linha.replace(',', '.'));
            } catch (NumberFormatException e) {
                println("Valor inválido. Digite um número.");
            }
        }
    }

    public static void aguardarEnter() {
        entrada.nextLine();
    }

    public static void clear() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("nux") || os.contains("nix") || os.contains("aix")) {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else {
                println("Outro sistema operacional: " + os);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
