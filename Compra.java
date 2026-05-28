
import java.util.Date;

public class Compra {

    private int id;
    private String cliente;
    private double total;
    private int quantidade;
    private Date data;

    public Compra() {
    }

    public Compra(CrudSequencia crudSequencia, Cliente cliente) {
        this.id = crudSequencia.proximo("compra");
        this.cliente = cliente.getCpf();
        this.total = 0;
        this.quantidade = 0;
        this.data = new Date();
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void imprime() {
        Tela.println("Código: " + id + " Cliente: " + cliente + " Quantidade: " + quantidade + " Total: " + total + " Data: " + data);
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        total += produto.getPreco() * quantidade;
        this.quantidade += quantidade;
    }
}
