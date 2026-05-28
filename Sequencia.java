
public class Sequencia {

    private String nome;
    private int valor;

    public Sequencia() {
    }

    public Sequencia(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    public int proximo() {
        return ++valor;
    }
}
