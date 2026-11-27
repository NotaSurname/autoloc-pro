package app;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        data.load();

        MenuPrincipal menu = new MenuPrincipal(data.getAgence());
        menu.start();
    }
}
