
public class MenuProduto {

    private CrudProduto crudProduto;

    public MenuProduto(CrudProduto crudProduto) {
        this.crudProduto = crudProduto;
    }

    public void menu() {
        int opcao;

        do {
            Tela.clear();
            Tela.println("========================");
            Tela.println("Menu de Produtos");
            Tela.println("========================");
            Tela.println("1 - Listar");
            Tela.println("2 - Cadastrar");
            Tela.println("3 - Excluir");
            Tela.println("4 - Atualizar");
            Tela.println("0 - Voltar");
            Tela.println();
            Tela.println("Digite a opção: ");
            opcao = Tela.lerInt();

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    cadastrar();
                    break;
                case 3:
                    excluir();
                    break;
                case 4:
                    atualizar();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    public void listar() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Listagem de Produtos");
        Tela.println("========================");

        Produto[] produtos = crudProduto.listar();

        for (int i = 0; i < produtos.length; i++) {
            produtos[i].imprime();
        }

        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }

    public void cadastrar() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Cadastro de Produto");
        Tela.println("========================");

        Produto produto = new Produto();

        do {
            if (produto.getCodigo() != 0) {
                Tela.println("Produto já cadastrado. Tente novamente.");
            }

            produto.lerCodigo();
        } while (crudProduto.buscar(produto.getCodigo()) != null);

        produto.lerNome();
        produto.lerPreco();
        produto.lerQuantidade();

        crudProduto.cadastrar(produto);

        Tela.println("Produto cadastrado com sucesso!");
        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }

    public void excluir() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Exclusão de Produto");
        Tela.println("========================");

        int codigo = 0;

        do {
            if (codigo != 0) {
                Tela.println("Produto não encontrado. Tente novamente.");
            }

            Tela.println("Digite o Código ou 0 para sair: ");
            codigo = Tela.lerInt();

            if (codigo == 0) {
                return;
            }

        } while (crudProduto.buscar(codigo) == null);

        crudProduto.excluir(codigo);

        Tela.println("Produto excluído com sucesso!");
        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }

    public void atualizar() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Atualização de Produto");
        Tela.println("========================");

        int codigo = 0;

        Produto produto = null;

        do {
            if (codigo != 0) {
                Tela.println("Produto não encontrado. Tente novamente.");
            }
            Tela.println("Digite o Código ou 0 para sair: ");

            codigo = Tela.lerInt();

            if (codigo == 0) {
                return;
            }

            produto = crudProduto.buscar(codigo);
        } while (produto == null);

        produto.lerNome();
        produto.lerPreco();
        produto.lerQuantidade();

        crudProduto.atualizar(produto);

        Tela.println("Produto atualizado com sucesso!");
        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }
}
