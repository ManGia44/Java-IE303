import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class Game extends JPanel implements ActionListener, KeyListener {
    Image backgroundImage, birdImage, bottomPipeImage, topPipeImage;
    Bird bird;
    ArrayList<Pipe> pipes = new ArrayList<>();
    Timer gameLoop;
    Random rand = new Random();

    int boardWidth = 360, boardHeight = 640;
    int gravity = 1, birdVelocity = 0, jumpStrength = -10;
    int pipeSpacing = 200, pipeWidth = 50;
    int pipeGap = 160;
    boolean isGameRunning = false;
    boolean isGameOver = false;
    int score = 0;

    class Bird {
        int x, y, width, height;
        Image image;

        Bird(Image image) {
            this.image = image;
            this.width = 34;
            this.height = 24;
            resetPosition();
        }

        void resetPosition() {
            this.x = 50;
            this.y = boardHeight / 2;
        }
    }

    class Pipe {
        int x, y, width, height;
        Image image;

        Pipe(Image image, int x, int y, int height) {
            this.image = image;
            this.width = pipeWidth;
            this.height = height;
            this.x = x;
            this.y = y;
        }

        void movePipe() {
            x -= 3;
        }
    }

    Game() {
        backgroundImage = new ImageIcon(getClass().getResource("/images/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("/images/flappybird.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("/images/bottompipe.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("/images/toppipe.png")).getImage();

        bird = new Bird(birdImage);
        generatePipes();

        addKeyListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startGame();
            }
        });

        setFocusable(true);
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    void startGame() {
        if (isGameOver) {
            resetGame();
        } else if (!isGameRunning) {
            isGameRunning = true;
            birdVelocity = jumpStrength;
        } else {
            birdJump();
        }
    }

    void birdJump() {
        birdVelocity = jumpStrength;
    }

    void moveBird() {
        if (isGameRunning) {
            birdVelocity += gravity;
            bird.y += birdVelocity;
            bird.y = Math.max(0, bird.y);
        }
    }

    void generatePipes() {
        pipes.clear();
        int x = boardWidth;
        for (int i = 0; i < 3; i++) {
            int topPipeHeight = 50 + rand.nextInt(400);
            int bottomPipeY = topPipeHeight + pipeGap;
            int bottomPipeHeight = boardHeight - bottomPipeY;

            pipes.add(new Pipe(topPipeImage, x, 0, topPipeHeight));
            pipes.add(new Pipe(bottomPipeImage, x, bottomPipeY, bottomPipeHeight));

            x += pipeSpacing;
        }
    }

    void movePipes() {
        if (!isGameRunning)
            return;

        for (Pipe pipe : pipes)
            pipe.movePipe();

        if (pipes.get(0).x + pipeWidth < 0) {
            pipes.remove(0);
            pipes.remove(0);
            score++;

            int newX = pipes.get(pipes.size() - 1).x + pipeSpacing;
            int topPipeHeight = 100 + rand.nextInt(150);
            int bottomPipeY = topPipeHeight + pipeGap;
            int bottomPipeHeight = boardHeight - bottomPipeY;

            pipes.add(new Pipe(topPipeImage, newX, 0, topPipeHeight));
            pipes.add(new Pipe(bottomPipeImage, newX, bottomPipeY, bottomPipeHeight));
        }
    }

    boolean checkCollision() {
        if (bird.y + bird.height >= boardHeight)
            return true;

        for (Pipe pipe : pipes) {
            if (bird.x < pipe.x + pipe.width &&
                    bird.x + bird.width > pipe.x &&
                    bird.y < pipe.y + pipe.height &&
                    bird.y + bird.height > pipe.y) {
                return true;
            }
        }
        return false;
    }

    void resetGame() {
        isGameRunning = false;
        isGameOver = false;
        bird.resetPosition();
        birdVelocity = 0;
        score = 0;
        generatePipes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning) {
            moveBird();
            movePipes();
            if (checkCollision()) {
                isGameOver = true;
                isGameRunning = false;
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bird.image, bird.x, bird.y, bird.width, bird.height, null);
        for (Pipe pipe : pipes)
            g.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 30);

        if (!isGameRunning) {
            if (isGameOver) {
                g.drawString("Game Over!", 120, boardHeight / 2 - 10);
                g.drawString("Press SPACE to Restart", 60, boardHeight / 2 + 20);
            } else {
                g.drawString("Press SPACE or ENTER to Start!", 25, boardHeight / 2);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
            startGame();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
