package company.java.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

public class GameController {
    final int size = 600, dot_size = 10, up = 1, right = 2, down = 3, left = 4;
    int delay, length, dir;
    GraphicsContext gc;
    int x[] = new int[size * size];
    int y[] = new int[size * size];
    Thread game;
    boolean lost;


    @FXML
    Canvas canvas;

    @FXML
    public void keyPressed(KeyEvent k){
        KeyCode key = k.getCode();
        if (key.equals(KeyCode.UP)) dir = up;
        if (key.equals(KeyCode.DOWN)) dir = down;
        if (key.equals(KeyCode.LEFT)) dir = left;
        if (key.equals(KeyCode.RIGHT)) dir = right;
    }


    public void initialize(){
        delay = 100; length = 3; dir = 2;
        lost = false;

        gc = canvas.getGraphicsContext2D();

        startGame();
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, size, size);
        if (!lost) {
            gc.setFill(Paint.valueOf("red"));
            gc.fillOval(x[0], y[0], dot_size, dot_size);
            gc.setFill(Paint.valueOf("orange"));
            for (int i = 1; i < length; i++) {
                gc.fillOval(x[i], y[i], dot_size, dot_size);
            }

        } else {
            gc.setFill(Paint.valueOf("black"));
            gc.fillText("Game Over", size / 2 - 50, size / 2 - 15);
            game.stop();
        }
    }


    private void startGame() {
        length = 3;
        for (int i = 0; i < length; i++) {
            x[i] = 50 - i * dot_size;
            y[i] = 50;
        }
        game = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!lost) {
                        checkCollision();
                        move();
                    }
                    draw(gc);
                    try {
                        Thread.sleep(delay);
                    } catch (Exception e) {
                    }
                    ;
                }
            }


        });
        game.start();
    }

    private void checkCollision() {
        if (x[0] >= size) lost = true;
        if (y[0] >= size) lost = true;
        if (x[0] < 0) lost = true;
        if (y[0] < 0) lost = true;
        for (int i = 3; i < length; i++)
            if (x[0] == x[i] && y[0] == y[i]) lost = true;
    }

    private void move() {
        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (dir == up) y[0] -= dot_size;
        if (dir == down) y[0] += dot_size;
        if (dir == right) x[0] += dot_size;
        if (dir == left) x[0] -= dot_size;
    }
}