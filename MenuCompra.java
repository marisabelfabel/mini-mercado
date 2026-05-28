
public class MenuCompra {

    private CrudCompra crudCompra;
    private CrudCliente crudCliente;
    private CrudSequencia crudSequencia;
    private CrudProduto crudProduto;
    private CrudCompraProduto crudCompraProduto;
    private MenuCliente menuCliente;

    public MenuCompra(CrudCliente crudCliente, CrudProduto crudProduto) {
        crudCompra = new CrudCompra();
        this.crudCliente = crudCliente;
        this.crudSequencia = new CrudSequencia();
        this.crudProduto = crudProduto;
        crudCompraProduto = new CrudCompraProduto();
        menuCliente = new MenuCliente(crudCliente);
    }

    public void menu() {
        int opcao;

        do {
            Tela.clear();
            Tela.println("========================");
            Tela.println("Menu de Compras");
            Tela.println("========================");
            Tela.println("1 - Cadastrar Compra");
            Tela.println("2 - Listar Compras");
            Tela.println("3 - Buscar Compra");
            Tela.println("4 - Excluir Compra");
            Tela.println("0 - Voltar");
            Tela.println();
            Tela.println("Digite a opção: ");

            opcao = Tela.lerInt();

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    excluir();
                    break;
                case 0:
                    return;
            }

        } while (true);
    }

    public void cadastrar() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Cadastro de Compra");
        Tela.println("========================");

        Tela.println("O cliente já está cadastrado? ([s]/n)");

        String resposta = Tela.lerLinha();

        Cliente cliente;

        if (resposta.equalsIgnoreCase("n")) {
            cliente = menuCliente.cadastrar();
        } else {
            do {
                Tela.println("Digite o CPF do cliente: ");

                String cpf = Tela.lerLinha();
                cliente = crudCliente.buscar(cpf);

                if (cliente == null) {
                    Tela.println("Cliente não encontrado. Tente novamente.");
                }
            } while (cliente == null);
        }

        Compra compra = new Compra(crudSequencia, cliente);
        Carrinho carrinho = new Carrinho(compra);

        int id = 0;

        Produto produto = null;
        boolean adicionado = false;

        do {
            Tela.clear();

            Tela.println("========================");
            Tela.println("Carrinho de Compras");
            Tela.println("========================");
            Tela.println("ID: " + compra.getId());
            Tela.println("Cliente: " + cliente.getNome());
            Tela.println("Quantidade: " + compra.getQuantidade());
            Tela.println("Total: " + compra.getTotal());
            Tela.println();
            Tela.println("0 - Finalizar Compra | -1 - Cancelar Compra");
            Tela.println();

            if (id != 0 && produto == null) {
                Tela.println("Produto não encontrado. Tente novamente.");
            }

            if (!adicionado && produto != null) {
                Tela.println("Produto sem estoque suficiente.");
            }

            Tela.println("Digite o código do produto: ");

            id = Tela.lerInt();

            if (id == -1) {
                return;
            }

            if (id == 0) {
                break;
            }

            produto = crudProduto.buscar(id);

            if (produto != null) {
                Tela.println("Produto: " + produto.getNome());
                Tela.println("Preço: " + produto.getPreco());
                Tela.println("Quantidade: ");

                int quantidade = Tela.lerInt();

                adicionado = carrinho.adicionarProduto(produto, quantidade);
            }

        } while (id != 0);

        crudCompra.adicionar(compra);

        CompraProduto[] compraProdutos = carrinho.listarProdutos();

        for (int i = 0; i < compraProdutos.length; i++) {
            crudProduto.removerEstoque(compraProdutos[i].getProduto(), compraProdutos[i].getQuantidade());
        }

        crudCompraProduto.adicionar(compraProdutos);

        Tela.println("Compra cadastrada com sucesso!");
        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }

    public void listar() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Listagem de Compras");
        Tela.println("========================");

        Compra[] compras = crudCompra.listar();

        for (int i = 0; i < compras.length; i++) {
            compras[i].imprime();
        }

        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }

    public void buscar() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Busca de Compra");
        Tela.println("========================");

        int id = 0;

        Compra compra = null;

        do {
            if (id != 0) {
                Tela.println("Compra não encontrada. Tente novamente.");
            }
            Tela.println("Digite o ID ou 0 para sair: ");

            id = Tela.lerInt();

            if (id == 0) {
                return;
            }

            compra = crudCompra.buscar(id);
        } while (compra == null);

        compra.imprime();

        CompraProduto[] compraProdutos = crudCompraProduto.listar(id);

        Tela.println();
        Tela.println("Produtos: ");
        Tela.println();

        for (int i = 0; i < compraProdutos.length; i++) {
            compraProdutos[i].imprime(crudProduto);
        }

        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }

    public void excluir() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Exclusão de Compra");
        Tela.println("========================");

        int id = 0;
        do {
            if (id != 0) {
                Tela.println("Compra não encontrada. Tente novamente.");
            }
            Tela.println("Digite o ID ou 0 para sair: ");
            id = Tela.lerInt();

            if (id == 0) {
                return;
            }

        } while (crudCompra.buscar(id) == null);

        CompraProduto[] compraProdutos = crudCompraProduto.listar(id);

        for (int i = 0; i < compraProdutos.length; i++) {
            crudProduto.adicionarEstoque(compraProdutos[i].getProduto(), compraProdutos[i].getQuantidade());
        }

        crudCompra.excluir(id);
        crudCompraProduto.excluir(id);

        Tela.println("Compra excluída com sucesso!");
        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }
}
