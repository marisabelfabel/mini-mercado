
public class CrudCompra {

    private static final String NOME_ARQUIVO = "compras.csv";

    private Compra[] compras;

    public CrudCompra() {
        Compra[] comprasAtuais = CsvUtil.ler(NOME_ARQUIVO, Compra.class);

        if (comprasAtuais == null) {
            compras = new Compra[0];
        } else {
            compras = comprasAtuais;
        }
    }

    public boolean adicionar(Compra compra) {
        Compra[] novasCompras = new Compra[compras.length + 1];

        for (int i = 0; i < compras.length; i++) {
            if (compras[i].getId() == compra.getId()) {
                return false;
            }
            novasCompras[i] = compras[i];
        }

        novasCompras[compras.length] = compra;
        compras = novasCompras;

        CsvUtil.salvar(novasCompras, NOME_ARQUIVO);

        return true;
    }

    public Compra[] listar() {
        return compras;
    }

    public Compra buscar(int id) {
        for (int i = 0; i < compras.length; i++) {
            if (compras[i].getId() == id) {
                return compras[i];
            }
        }
        return null;
    }

    public int indice(int id) {
        for (int i = 0; i < compras.length; i++) {
            if (compras[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean atualizar(Compra compra) {
        int indice = indice(compra.getId());

        if (indice == -1) {
            return false;
        }

        compras[indice] = compra;

        CsvUtil.salvar(compras, NOME_ARQUIVO);

        return true;
    }

    public boolean excluir(int id) {
        int indice = indice(id);

        if (indice == -1) {
            return false;
        }

        Compra[] novasCompras = new Compra[compras.length - 1];

        for (int i = 0; i < novasCompras.length; i++) {
            if (i < indice) {
                novasCompras[i] = compras[i];
            } else {
                novasCompras[i] = compras[i + 1];
            }
        }

        compras = novasCompras;

        CsvUtil.salvar(novasCompras, NOME_ARQUIVO);

        return true;
    }
}
