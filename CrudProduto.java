
public class CrudProduto {

    private static final String NOME_ARQUIVO = "produtos.csv";

    private Produto[] produtos;

    public CrudProduto() {
        Produto[] produtosAtuais = CsvUtil.ler(NOME_ARQUIVO, Produto.class);

        if (produtosAtuais == null) {
            produtos = new Produto[0];
        } else {
            produtos = produtosAtuais;
        }
    }

    public boolean cadastrar(Produto produto) {
        Produto[] novosProdutos = new Produto[produtos.length + 1];

        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].getCodigo() == produto.getCodigo()) {
                return false;
            }

            novosProdutos[i] = produtos[i];
        }

        novosProdutos[produtos.length] = produto;
        produtos = novosProdutos;

        CsvUtil.salvar(novosProdutos, NOME_ARQUIVO);

        return true;
    }

    public Produto[] listar() {
        return produtos;
    }

    public Produto buscar(int codigo) {
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].getCodigo() == codigo) {
                return produtos[i];
            }
        }

        return null;
    }

    public int indice(int codigo) {
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].getCodigo() == codigo) {
                return i;
            }
        }

        return -1;
    }

    public boolean atualizar(Produto produto) {
        boolean encontrado = false;

        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].getCodigo() == produto.getCodigo()) {
                produtos[i] = produto;
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            CsvUtil.salvar(produtos, NOME_ARQUIVO);
        }

        return encontrado;
    }

    public boolean excluir(int codigo) {
        int indice = indice(codigo);

        if (indice == -1) {
            return false;
        }

        Produto[] novosProdutos = new Produto[produtos.length - 1];

        for (int i = 0; i < novosProdutos.length; i++) {
            if (i < indice) {
                novosProdutos[i] = produtos[i];
            } else {
                novosProdutos[i] = produtos[i + 1];
            }
        }

        produtos = novosProdutos;

        CsvUtil.salvar(novosProdutos, NOME_ARQUIVO);

        return true;
    }

    public boolean adicionarEstoque(int codigo, int quantidade) {
        Produto produto = buscar(codigo);

        if (produto == null) {
            return false;
        }

        produto.adicionarEstoque(quantidade);

        return atualizar(produto);
    }

    public boolean removerEstoque(int codigo, int quantidade) {
        Produto produto = buscar(codigo);

        if (produto == null) {
            return false;
        }

        produto.removerEstoque(quantidade);

        return atualizar(produto);
    }
}
