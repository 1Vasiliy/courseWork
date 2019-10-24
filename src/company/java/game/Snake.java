package company.java.game;

import company.java.controllers.GameController;

public class Snake {

    private int up, right, down, left, length;
    public int dir;

    private int[] x = new int[GameController.size * GameController.size];
    private int[] y = new int[GameController.size * GameController.size];
    public Thread game;

    public Snake() {
        up = 1; right = 2; down = 3; left = 4;
        dir = 2;
        length = 3;
    }

    public void startGame() {
        length = 3;
        for (int i = 0; i < length; i++) {
            x[i] = 50 - i * GameController.dot_size;
            y[i] = 50;
        }
        GameController.locateFood();
        game = new Thread(() -> {
            while (true) {
                if (!GameController.lost) {
                    checkFood();
                    GameController.checkCollision(x, y, length);
                    move();
                }
                GameController.draw(this, length, x, y);
                try {
                    Thread.sleep(GameController.delay);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });
        game.start();
    }


    private void checkFood() {
        if (x[0] == GameController.food_x && y[0] == GameController.food_y) {
            length++;
            GameController.locateFood();
        }
    }

    private void move() {
        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (dir == up) y[0] -= GameController.dot_size;
        if (dir == down) y[0] += GameController.dot_size;
        if (dir == right) x[0] += GameController.dot_size;
        if (dir == left) x[0] -= GameController.dot_size;
    }
}