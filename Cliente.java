
public class Cliente {

    private String cpf;
    private String nome;
    private String email;
    private String telefone;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void imprime() {
        Tela.println("CPF: " + cpf + " Nome: " + nome + " Email: " + email + " Telefone: " + telefone);
    }

    public void lerCpf() {
        cpf = null;

        do {
            if (cpf != null) {
                Tela.println("CPF inválido. Tente novamente.");
            }

            Tela.println("Digite o CPF: ");
            cpf = Tela.lerLinha();
        } while (!Validadores.cpfValido(cpf));

        cpf = Validadores.apenasNumeros(cpf);
    }

    public void lerNome() {
        nome = null;

        do {
            if (nome != null) {
                Tela.println("Nome inválido. Tente novamente.");
            }

            Tela.println("Digite o nome: ");
            nome = Tela.lerLinha();
        } while (nome.isEmpty());
    }

    public void lerEmail() {
        email = null;

        do {
            if (email != null) {
                Tela.println("Email inválido. Tente novamente.");
            }

            Tela.println("Digite o email: ");
            email = Tela.lerLinha();
        } while (!Validadores.emailValido(email));
    }

    public void lerTelefone() {
        telefone = null;

        do {
            if (telefone != null) {
                Tela.println("Telefone inválido. Tente novamente.");
            }

            Tela.println("Digite o telefone: ");
            telefone = Tela.lerLinha();
        } while (!Validadores.telefoneValido(telefone));

        telefone = Validadores.apenasNumeros(telefone);
    }
}
