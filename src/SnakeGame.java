

import javax.swing.*;
import java.awt.*;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame object = new JFrame();
        GamePlay gamePlay = new GamePlay();

        object.setBounds(10, 10, 905, 700);
        object.setBackground(Color.DARK_GRAY);
        object.setResizable(false);
        object.setVisible(true);
        object.add(gamePlay);
        object.setFocusable(true);
        object.requestFocusInWindow();
        object.setFocusTraversalKeysEnabled(false);



    }
}
