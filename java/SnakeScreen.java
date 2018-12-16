import javax.swing.*;
import java.awt.*;

public class SnakeScreen extends JPanel {
    //Screen inputs to know
    private int screenHeight;
    private int screenWidth;
    private int tileLength;
    private Board board;
    private int boardQuality;
    /////////////////////////

    //Init the screen
    public void initScreen(int screenWidth, int screenHeight, Board board, int boardQuality)
    {
        //assign to the private vars the inputs and anything else to do with screen inputs
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.board = board;
        this.boardQuality = boardQuality;
        this.tileLength = (int)(Math.sqrt(this.screenHeight * this.screenWidth)/(this.boardQuality));
        setPreferredSize(new Dimension(this.tileLength*this.board.BOARD_SIZE,this.tileLength*this.board.BOARD_SIZE));

        /////////////////
        drawBoard();
    }
    public void drawBoard()
    {
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        createBoard(g);
    }
    private void createBoard(Graphics g)
    {
        for(int i = 0; i < this.board.BOARD_SIZE; i++)
        {
            for(int j = 0; j < board.BOARD_SIZE; j++)
            {
                g.setColor(tileColor(this.board.getBoard()[i][j]));
                g.fillRect((j*this.tileLength),(i*this.tileLength), this.tileLength, this.tileLength);
            }
        }
    }
    public void setBoard(Board newBoard)
    {
        this.board = newBoard;
    }
    private Color tileColor(String boardPiece)
    {
        Color tile = null;
        if(boardPiece == "x") {
            tile = Color.DARK_GRAY;
        }
        else if(boardPiece == "o") {
            tile =  Color.WHITE;
        }
        else if(boardPiece == "-") {
            tile = Color.BLACK;
        }
        else if(boardPiece == "A") {
            tile = Color.RED;
        }
        return tile;
    }
}
