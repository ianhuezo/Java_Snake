import javax.swing.*;
import java.awt.*;


public class ScoreBoard extends JPanel {
    //Screen inputs to know
    private int screenHeight;
    private int screenWidth;
    private int tileLength;
    private Board board;
    private int boardQuality;
    /////////////////////////
    JLabel scoreLabel = new JLabel();
    public void initScoreScreen(int screenWidth, int screenHeight, Board board, int boardQuality)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.board = board;
        this.boardQuality = boardQuality;
        this.tileLength = (int)(Math.sqrt(this.screenHeight * this.screenWidth)/(this.boardQuality));
        setPreferredSize(new Dimension(this.tileLength*this.board.BOARD_SIZE,this.tileLength*this.board.BOARD_SIZE));
        setBackground(Color.BLACK);
        ///JLabel
        String scoreText = "You ate " + this.board.snakeScore + " apples!";
        scoreLabel.setText("<html><center>" + scoreText +"</center><br>" + "Press r to retry for a better score!</html>");
        scoreLabel.setForeground(Color.WHITE);
        int midX =  ((this.screenWidth - getWidth())/2);
        int midY =  ((this.screenHeight + getHeight())/2);
        Font words = new Font("Courier New", Font.BOLD, 20);
        scoreLabel.setLocation(midX, midY);
        scoreLabel.setFont(words);
        add(scoreLabel);
    }
    public void removeLabel()
    {
        remove(scoreLabel);
    }
}
