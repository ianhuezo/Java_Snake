import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Scanner;


public class SnakeApp {

    public static void main(String[] args)
    {
        int WIDTH = 800;
        int HEIGHT = 800;
        SnakeGame snake = new SnakeGame(WIDTH,HEIGHT,32);
        snake.keyboardAction();

    }
}
