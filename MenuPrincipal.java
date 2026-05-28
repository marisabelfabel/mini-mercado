
public class MenuPrincipal {

    private MenuCliente menuCliente;
    private MenuProduto menuProduto;
    private MenuCompra menuCompra;
    private CrudCliente crudCliente;
    private CrudProduto crudProduto;

    public MenuPrincipal() {
        crudCliente = new CrudCliente();
        crudProduto = new CrudProduto();
        menuCliente = new MenuCliente(crudCliente);
        menuProduto = new MenuProduto(crudProduto);
        menuCompra = new MenuCompra(crudCliente, crudProduto);
    }

    public void menu() {
        int opcao;

        do {
            Tela.clear();
            Tela.println("==================================");
            Tela.println("Sistema de Gerenciamento de Vendas");
            Tela.println("==================================");
            Tela.println("1 - Clientes");
            Tela.println("2 - Produtos");
            Tela.println("3 - Compras");
            Tela.println("0 - Sair");
            Tela.println();
            Tela.println("Digite a opção: ");

            opcao = Tela.lerInt();

            switch (opcao) {
                case 1:
                    menuCliente.menu();
                    break;
                case 2:
                    menuProduto.menu();
                    break;
                case 3:
                    menuCompra.menu();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }
}
