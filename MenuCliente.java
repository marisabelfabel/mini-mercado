
public class MenuCliente {

    private CrudCliente crudCliente;

    public MenuCliente(CrudCliente crudCliente) {
        this.crudCliente = crudCliente;
    }

    public void menu() {
        int opcao;

        do {

            Tela.clear();

            Tela.println("========================");
            Tela.println("Clientes");
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
        Tela.println("Listagem de Clientes");
        Tela.println("========================");

        Cliente[] clientes = crudCliente.listar();
        for (int i = 0; i < clientes.length; i++) {
            clientes[i].imprime();
        }

        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }

    public Cliente cadastrar() {
        Tela.clear();

        Tela.println("========================");
        Tela.println("Cadastro de Cliente");
        Tela.println("========================");

        Cliente cliente = new Cliente();

        do {
            if (cliente.getCpf() != null) {
                Tela.println("Cliente já cadastrado. Tente novamente.");
            }

            cliente.lerCpf();
        } while (crudCliente.buscar(cliente.getCpf()) != null);

        cliente.lerNome();
        cliente.lerEmail();
        cliente.lerTelefone();

        crudCliente.adicionar(cliente);

        Tela.println("Cliente cadastrado com sucesso!");
        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();

        return cliente;
    }

    public void excluir() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Exclusão de Cliente");
        Tela.println("========================");

        String cpf;

        do {
            Tela.println("Digite o CPF (ou Enter para sair):");
            cpf = Tela.lerLinha();

            if (cpf.isEmpty()) {
                break;
            }

            if (Validadores.cpfValido(cpf)) {
                cpf = Validadores.apenasNumeros(cpf);
                if (crudCliente.excluir(cpf)) {
                    Tela.println("Cliente excluído com sucesso!");
                    Tela.println("Pressione Enter para voltar...");
                    Tela.aguardarEnter();
                    break;
                } else {
                    Tela.println("Cliente não encontrado. Tente novamente.");
                }
            } else {
                Tela.println("CPF inválido. Tente novamente.");
            }

        } while (true);
    }

    public void atualizar() {
        Tela.clear();
        Tela.println("========================");
        Tela.println("Atualização de Cliente");
        Tela.println("========================");

        String cpf = null;

        Cliente cliente = null;

        do {
            if (cpf != null) {
                Tela.println("Cliente não encontrado. Tente novamente.");
            }

            Tela.println("Digite o CPF (ou Enter para sair):");
            cpf = Tela.lerLinha();

            if (cpf.isEmpty()) {
                return;
            }

            if (Validadores.cpfValido(cpf)) {
                cpf = Validadores.apenasNumeros(cpf);

                cliente = crudCliente.buscar(cpf);
            } else {
                cpf = null;
                Tela.println("CPF inválido. Tente novamente.");
            }
        } while (cliente == null);

        cliente.lerNome();
        cliente.lerEmail();
        cliente.lerTelefone();

        crudCliente.atualizar(cliente);

        Tela.println("Cliente atualizado com sucesso!");
        Tela.println("Pressione Enter para voltar...");
        Tela.aguardarEnter();
    }
}
