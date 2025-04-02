import javax.swing.*;

public class App {

    public static void main(String[] args) throws Exception {
        JFrame mainWindow = new JFrame();
        mainWindow.setTitle("Flappy Bird");

        // size of window (in pixels)
        int boardWitdth = 360;
        int boardHeight = 640;
        mainWindow.setSize(boardWitdth, boardHeight);

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);

        Game game = new Game();
        mainWindow.add(game);
        mainWindow.setVisible(true);
        game.requestFocus();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}