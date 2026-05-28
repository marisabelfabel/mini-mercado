
public class Produto {

    private int codigo;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto() {
    }

    public Produto(int codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void imprime() {
        Tela.println("Código: " + codigo + " Nome: " + nome + " Preco: " + preco + " Quantidade: " + quantidade);
    }

    public void lerCodigo() {
        codigo = 0;

        do {
            if (codigo != 0) {
                Tela.println("Código inválido. Tente novamente.");
            }

            Tela.println("Digite o Código: ");
            codigo = Tela.lerInt();
        } while (codigo <= 0);
    }

    public void lerNome() {
        nome = null;

        do {
            if (nome != null) {
                Tela.println("Nome inválido. Tente novamente.");
            }

            Tela.println("Digite o Nome: ");
            nome = Tela.lerLinha();
        } while (nome.isEmpty());

        nome = nome.trim();
    }

    public void lerPreco() {
        preco = -1;

        do {
            if (preco != -1) {
                Tela.println("Preço inválido. Tente novamente.");
            }

            Tela.println("Digite o Preço: ");
            preco = Tela.lerDouble();
        } while (preco <= 0);
    }

    public void lerQuantidade() {
        quantidade = -1;

        do {
            if (quantidade != -1) {
                Tela.println("Quantidade inválida. Tente novamente.");
            }

            Tela.println("Digite a Quantidade: ");
            quantidade = Tela.lerInt();
        } while (quantidade < 0);
    }

    public boolean possuiEstoque(int quantidade) {
        return quantidade <= this.quantidade;
    }

    public void removerEstoque(int quantidade) {
        this.quantidade -= quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }
}
