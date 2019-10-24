package company.java.controllers;

import company.java.Menu;
import company.java.game.Snake;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameController {
    public static final int size = 600, dot_size = 10;
    private static GraphicsContext gc;
    public static int delay, food_x, food_y;

    private Snake snake;

    public static boolean lost;

    @FXML
    Canvas canvas;

    @FXML
    public void keyPressed(KeyEvent key) throws Exception {
        if (key.getCode().equals(KeyCode.UP)) snake.dir = 1;
        if (key.getCode().equals(KeyCode.DOWN)) snake.dir = 3;
        if (key.getCode().equals(KeyCode.LEFT)) snake.dir = 4;
        if (key.getCode().equals(KeyCode.RIGHT)) snake.dir = 2;
        if (key.getCode().equals(KeyCode.ESCAPE)){
            snake.game.stop();
            new Menu().start((Stage) canvas.getScene().getWindow());
        }
    }


    public void initialize(){
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        delay = 100;
        lost = false;

        snake = new Snake();
        snake.startGame();
    }


    public static void checkCollision(int[] x, int[] y, int length) {
        if (x[0] >= size) lost = true;
        if (y[0] >= size) lost = true;
        if (x[0] < 0) lost = true;
        if (y[0] < 0) lost = true;
        for (int i = 3; i < length; i++)
            if (x[0] == x[i] && y[0] == y[i]) lost = true;
    }

    public static void locateFood() {
        food_x = (int) (Math.random() * ((size / dot_size) - 1)) * dot_size;
        food_y = (int) (Math.random() * ((size / dot_size) - 1)) * dot_size;
    }

    public static void draw(Snake snake, int length, int[] x, int[] y) {
        gc.clearRect(0, 0, size, size);
        if (!lost) {
            gc.setFill(Paint.valueOf("green"));
            gc.fillOval(food_x, food_y, dot_size, dot_size);
            gc.setFill(Paint.valueOf("red"));
            gc.fillOval(x[0], y[0], dot_size, dot_size);
            gc.setFill(Paint.valueOf("orange"));
            for (int i = 1; i < length; i++) {
                gc.fillOval(x[i], y[i], dot_size, dot_size);
            }

        } else {
            gc.setFill(Paint.valueOf("white"));
            gc.setFont(Font.font(28));
            gc.fillText("Game Over", size / 2 - 100, size / 2 - 15);
            snake.game.stop();
        }
    }
}