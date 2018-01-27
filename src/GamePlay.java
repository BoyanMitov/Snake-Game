import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
// Първо създадохме класа гаме плай , а след това в него имплецитирахме  интерфейсите KeyListener , ActionListener
public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeexlength = new int[750];
    private int[] snakeYlength = new int[750];

    //накъде върви змията - тяло:
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    //Накъде сочи главата на змията :
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private int lengthofsnake = 3;

    //Бързината на змията:
    private Timer timer;
    private int delay = 100;

    private int score = 0;

    private ImageIcon titleImage;

    //Конструкция на змията:
    private ImageIcon snakeimage;
    private int moves = 0;
    //Конструкция на deffault позициите за събиране
    private int[] enemyxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350,
            375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] enemyypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
    private ImageIcon enemyimage;
    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    //Метода съдържащ всичко необходимо в панела
    public void paint(Graphics g) {
        //deffault позицията на змията при започване на играта
        if (moves == 0) {
            snakeexlength[2] = 50;
            snakeexlength[1] = 75;
            snakeexlength[0] = 100;

            snakeYlength[2] = 100;
            snakeYlength[1] = 100;
            snakeYlength[0] = 100;
        }

        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        g.setColor(Color.white);
        g.setFont(new Font ("arial" , Font.PLAIN, 14));
        g.drawString("Score: " +score , 780 , 30 );

        g.setColor(Color.white);
        g.setFont(new Font ("arial" , Font.PLAIN, 14));
        g.drawString("Length: " +lengthofsnake , 780 , 50 );

        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakeexlength[0], snakeYlength[0]);

        for (int i = 0; i < lengthofsnake; i++) {
            if (i == 0 && right) {
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakeexlength[i], snakeYlength[i]);
            }
            if (i == 0 && left) {
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakeexlength[i], snakeYlength[i]);
            }
            if (i == 0 && down) {
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakeexlength[i], snakeYlength[i]);
            }
            if (i == 0 && up) {
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakeexlength[i], snakeYlength[i]);
            }
            if (i != 0) {
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakeexlength[i], snakeYlength[i]);
            }

        }
        enemyimage=new ImageIcon("enemy.png");
        if ((enemyxpos[xpos] == snakeexlength[0]) && (enemyypos[ypos] == snakeYlength[0])){
            score++;
            lengthofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyimage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);

        for (int b = 1; b < lengthofsnake; b++) {
            if (snakeexlength[b] == snakeexlength[0] && snakeYlength[b] == snakeYlength[0]){
                left=false;
                right=false;
                up=false;
                down=false;
                g.setColor(Color.white);
                g.setFont(new Font("arial" ,Font.BOLD , 50));
                g.drawString("Game Over Nooob !" , 300 , 300);

                g.setFont(new Font("arial" ,Font.BOLD , 30));
                g.drawString("Enter Space for reset Nooby" , 350 , 340);


            }

        }

        g.dispose();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        //Кой клавиш е натиснат и в коя посока ще се движи обекта
        if (right) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakeYlength[i + 1] = snakeYlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeexlength[i] = snakeexlength[i] + 25;
                } else {
                    snakeexlength[i] = snakeexlength[i - 1];
                }
                //Ако главата на змията докосне стената на конзолата , тя ще премине от другата страна
                if (snakeexlength[i] > 850) {
                    snakeexlength[i] = 25;
                }
            }
            repaint();

        }
        if (left) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakeYlength[i + 1] = snakeYlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeexlength[i] = snakeexlength[i] - 25;
                } else {
                    snakeexlength[i] = snakeexlength[i - 1];
                }
                if (snakeexlength[i] < 25) {
                    snakeexlength[i] = 850;
                }
            }
            repaint();
        }
        if (up) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakeexlength[i + 1] = snakeexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeYlength[i] = snakeYlength[i] - 25;
                } else {
                    snakeYlength[i] = snakeYlength[i - 1];
                }
                if (snakeYlength[i] < 75) {
                    snakeYlength[i] = 625;
                }
            }
            repaint();
        }
        if (down) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakeexlength[i + 1] = snakeexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeYlength[i] = snakeYlength[i] + 25;
                } else {
                    snakeYlength[i] = snakeYlength[i - 1];
                }
                if (snakeYlength[i] > 625) {
                    snakeYlength[i] = 75;
                }
            }
            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            moves = 0;
            score=0;
            lengthofsnake=3;
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            right = true;
            //Чек с който змията да не върви назад
            if (!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            left = true;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            up = true;
            if (!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            down = true;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
        requestFocusInWindow();


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
