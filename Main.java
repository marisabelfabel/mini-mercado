
public class Main {

    public static void main(String[] args) {
        Tela.iniciar();
        Tela.clear();

        MenuPrincipal menuPrincipal = new MenuPrincipal();

        menuPrincipal.menu();

        Tela.println("Saindo do sistema...");
    }
}
