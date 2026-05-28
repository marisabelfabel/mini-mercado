
public class Carrinho {

    private Produto[] produtos;
    private int[] quantidades;
    private Compra compra;

    public Carrinho(Compra compra) {
        this.compra = compra;
        this.produtos = new Produto[0];
        this.quantidades = new int[0];
    }

    public boolean adicionarProduto(Produto produto, int quantidade) {
        Produto[] novosProdutos = new Produto[produtos.length + 1];
        int[] novasQuantidades = new int[quantidades.length + 1];

        for (int i = 0; i < produtos.length; i++) {
            novosProdutos[i] = produtos[i];
            novasQuantidades[i] = quantidades[i];

            if (produtos[i].getCodigo() == produto.getCodigo()) {

                if (!produtos[i].possuiEstoque(quantidade + quantidades[i])) {
                    return false;
                }

                quantidades[i] += quantidade;
                compra.adicionarProduto(produto, quantidade);

                return true;
            }
        }

        if (!produto.possuiEstoque(quantidade)) {
            return false;
        }

        novosProdutos[produtos.length] = produto;
        novasQuantidades[quantidades.length] = quantidade;

        produtos = novosProdutos;
        quantidades = novasQuantidades;

        compra.adicionarProduto(produto, quantidade);

        return true;
    }

    public CompraProduto[] listarProdutos() {
        CompraProduto[] compraProdutos = new CompraProduto[produtos.length];
        for (int i = 0; i < produtos.length; i++) {
            compraProdutos[i] = new CompraProduto(compra, produtos[i], quantidades[i]);
        }
        return compraProdutos;
    }
}
