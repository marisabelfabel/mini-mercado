
public class CrudCliente {

    private static final String NOME_ARQUIVO = "clientes.csv";

    private Cliente[] clientes;

    public CrudCliente() {
        Cliente[] clientesAtuais = CsvUtil.ler(NOME_ARQUIVO, Cliente.class);

        if (clientesAtuais == null) {
            clientes = new Cliente[0];
        } else {
            clientes = clientesAtuais;
        }
    }

    public boolean adicionar(Cliente cliente) {
        Cliente[] novosClientes = new Cliente[clientes.length + 1];

        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getCpf().equals(cliente.getCpf())) {
                return false;
            }

            novosClientes[i] = clientes[i];
        }

        novosClientes[clientes.length] = cliente;
        clientes = novosClientes;

        CsvUtil.salvar(novosClientes, NOME_ARQUIVO);

        return true;
    }

    public Cliente[] listar() {
        return clientes;
    }

    public Cliente buscar(String cpf) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getCpf().equals(cpf)) {
                return clientes[i];
            }
        }

        return null;
    }

    public int indice(String cpf) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getCpf().equals(cpf)) {
                return i;
            }
        }
        return -1;
    }

    public boolean atualizar(Cliente cliente) {
        boolean encontrado = false;

        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getCpf().equals(cliente.getCpf())) {
                clientes[i] = cliente;
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            CsvUtil.salvar(clientes, NOME_ARQUIVO);
        }

        return encontrado;
    }

    public boolean excluir(String cpf) {
        int indice = indice(cpf);

        if (indice == -1) {
            return false;
        }

        Cliente[] novosClientes = new Cliente[clientes.length - 1];

        for (int i = 0; i < novosClientes.length; i++) {
            if (i < indice) {
                novosClientes[i] = clientes[i];
            } else {
                novosClientes[i] = clientes[i + 1];
            }
        }
        clientes = novosClientes;

        CsvUtil.salvar(clientes, NOME_ARQUIVO);

        return true;
    }
}
