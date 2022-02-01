public class Main {
    public static void main(String[] args) {
        FileManager manager = new FileManager();
        Player newPlayer = new Player("");
        Game newGame = new Game();

        GuiManager gui = new GuiManager(newGame, newPlayer, manager);
        gui.showMenu();



        System.out.println(manager.checkData(newPlayer.getName()));
        System.out.println(manager.getTempSave());
        System.out.println(newPlayer.getName());

    }
}
