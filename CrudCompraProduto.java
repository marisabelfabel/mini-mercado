
public class CrudCompraProduto {

    private static final String NOME_ARQUIVO = "compras_produtos.csv";

    private CompraProduto[] comprasProdutos;

    public CrudCompraProduto() {
        CompraProduto[] comprasProdutosAtuais = CsvUtil.ler(NOME_ARQUIVO, CompraProduto.class);

        if (comprasProdutosAtuais == null) {
            comprasProdutos = new CompraProduto[0];
        } else {
            comprasProdutos = comprasProdutosAtuais;
        }
    }

    public boolean adicionar(CompraProduto[] compraProdutos) {
        CompraProduto[] novosComprasProdutos = new CompraProduto[comprasProdutos.length + compraProdutos.length];

        int i = 0;

        for (; i < comprasProdutos.length; i++) {
            novosComprasProdutos[i] = comprasProdutos[i];
        }

        for (; i < novosComprasProdutos.length; i++) {
            novosComprasProdutos[i] = compraProdutos[i - comprasProdutos.length];
        }

        comprasProdutos = novosComprasProdutos;

        CsvUtil.salvar(novosComprasProdutos, NOME_ARQUIVO);

        return true;
    }

    public CompraProduto[] listar() {
        return comprasProdutos;
    }

    public CompraProduto[] listar(int compra) {
        CompraProduto[] comprasProdutosFiltrados = new CompraProduto[10];

        int j = 0;

        for (int i = 0; i < comprasProdutos.length; i++) {
            if (comprasProdutos[i].getCompra() == compra) {
                if (j >= comprasProdutosFiltrados.length) {
                    CompraProduto[] novosComprasProdutosFiltrados = new CompraProduto[comprasProdutosFiltrados.length * 2];

                    for (int k = 0; k < comprasProdutosFiltrados.length; k++) {
                        novosComprasProdutosFiltrados[k] = comprasProdutosFiltrados[k];
                    }

                    comprasProdutosFiltrados = novosComprasProdutosFiltrados;
                }

                comprasProdutosFiltrados[j] = comprasProdutos[i];
                j++;
            }
        }

        CompraProduto[] comprasProdutosFiltradosRetorno = new CompraProduto[j];

        for (int i = 0; i < j; i++) {
            comprasProdutosFiltradosRetorno[i] = comprasProdutosFiltrados[i];
        }

        return comprasProdutosFiltradosRetorno;
    }

    public boolean excluir(int compra) {
        CompraProduto[] novosComprasProdutos = new CompraProduto[comprasProdutos.length];

        int j = 0;

        for (int i = 0; i < comprasProdutos.length; i++) {
            if (comprasProdutos[i].getCompra() != compra) {
                novosComprasProdutos[j] = comprasProdutos[i];
                j++;
            }
        }

        comprasProdutos = new CompraProduto[j];

        for (int i = 0; i < j; i++) {
            comprasProdutos[i] = novosComprasProdutos[i];
        }

        CsvUtil.salvar(comprasProdutos, NOME_ARQUIVO);

        return true;
    }
}
