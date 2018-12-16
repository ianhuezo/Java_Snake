import javax.swing.*;
import java.awt.*;

public class GameWindow {
    //private vars to input
    private JFrame window = new JFrame("Snake");
    private int screenHeight;
    private int screenWidth;
    private Board board;
    private int boardQuality;
    //the current board JPanel
    private SnakeScreen mainGame;
    ScoreBoard score = new ScoreBoard();
    GameWindow(){
        ImageIcon img = new ImageIcon("..\\resources\\snake.png");
        window.setIconImage(img.getImage());
    }
    public void initWindow(int screenWidth, int screenHeight, Board board, int boardQuality)
    {
        this.window.remove(score);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.board = board;
        this.boardQuality = boardQuality;
        this.mainGame = new SnakeScreen();
        this.mainGame.initScreen(this.screenWidth,this.screenHeight,this.board,this.boardQuality);
        this.window.add(this.mainGame);
        this.window.setSize(new Dimension(this.screenWidth,this.screenHeight));
        this.window.pack();
        this.window.setResizable(false);
        this.window.setVisible(true);
        this.window.setLocationRelativeTo(null);
    }
    public void updateBoard(Board newBoard)
    {
        this.board = newBoard;
        this.mainGame.setBoard(newBoard);
        this.mainGame.drawBoard();
        this.window.pack();
        this.window.repaint();
    }
    public JFrame getMainWindow()
    {
        return this.window;
    }

    public void scoreScreen()
    {
        this.score.removeLabel();
        this.window.remove(score);
        this.score.initScoreScreen(this.screenWidth,this.screenHeight,this.board,this.boardQuality);
        this.window.remove(this.mainGame);
        this.window.add(score);
        this.window.pack();
        this.window.repaint();
    }
}
