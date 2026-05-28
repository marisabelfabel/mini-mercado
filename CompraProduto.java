
public class CompraProduto {

    private int compra;
    private int produto;
    private int quantidade;
    private double precoUnitario;
    private double total;

    public CompraProduto() {
    }

    public CompraProduto(Compra compra, Produto produto, int quantidade) {
        this.compra = compra.getId();
        this.produto = produto.getCodigo();
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
        this.total = this.precoUnitario * this.quantidade;
    }

    public int getCompra() {
        return compra;
    }

    public int getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void imprime(CrudProduto crudProduto) {
        Tela.println(produto + " - " + crudProduto.buscar(produto).getNome() + " Quantidade: " + quantidade + " Preço Unitário: " + precoUnitario + " Total: " + total);
    }
}
