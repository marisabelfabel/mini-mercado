
public class CrudSequencia {

    private static final String NOME_ARQUIVO = "sequencias.csv";

    private Sequencia[] sequencias;

    public CrudSequencia() {
        Sequencia[] sequenciasAtuais = CsvUtil.ler(NOME_ARQUIVO, Sequencia.class);

        if (sequenciasAtuais == null) {
            sequencias = new Sequencia[0];
        } else {
            sequencias = sequenciasAtuais;
        }
    }

    public void cadastrar(Sequencia sequencia) {
        Sequencia[] novosSequencias = new Sequencia[sequencias.length + 1];

        for (int i = 0; i < sequencias.length; i++) {
            novosSequencias[i] = sequencias[i];
        }

        novosSequencias[sequencias.length] = sequencia;

        sequencias = novosSequencias;

        CsvUtil.salvar(novosSequencias, NOME_ARQUIVO);
    }

    public Sequencia buscar(String nome) {
        for (Sequencia sequencia : sequencias) {
            if (sequencia.getNome().equals(nome)) {
                return sequencia;
            }
        }

        Sequencia novaSequencia = new Sequencia(nome, 0);

        cadastrar(novaSequencia);

        return novaSequencia;
    }

    public boolean atualizar(Sequencia sequencia) {
        boolean encontrado = false;

        for (int i = 0; i < sequencias.length; i++) {
            if (sequencias[i].getNome().equals(sequencia.getNome())) {
                sequencias[i] = sequencia;
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            CsvUtil.salvar(sequencias, NOME_ARQUIVO);
        }

        return encontrado;
    }

    public int proximo(String nome) {
        Sequencia sequencia = buscar(nome);

        int valor = sequencia.proximo();

        atualizar(sequencia);

        return valor;
    }
}
