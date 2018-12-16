import java.util.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

public class SnakeGame{
    private Board board = new Board();
    private int boardQuality;
    private char currentKey;
    private int screenHeight;
    private int screenWidth;
    private Timer timer = new Timer();
    private GameWindow window = new GameWindow();
    boolean timerRunning = false;
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if(board.boardInPlay)
                processKeyboardRepeat(currentKey);
            else{
                timer.cancel();
                timer.purge();
                window.scoreScreen();
            }
        }
    };


    SnakeGame(int screenWidth, int screenHeight, int boardQuality)
    {
        //The higher the quality, the tinier the board appears
        this.boardQuality = boardQuality;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        board.BOARD_SIZE = this.boardQuality;
        //create the board
        board.initBoard();
        //visualize the board
        window.initWindow(screenWidth,screenHeight,board,boardQuality);

    }
    public void moveDown()
    {
        board.movePlayer('D');
        board.updateBoard();
        window.updateBoard(this.board);
    }
    public void moveUp()
    {
        board.movePlayer('U');
        board.updateBoard();
        window.updateBoard(this.board);
    }
    public void moveRight()
    {
        board.movePlayer('R');
        board.updateBoard();
        window.updateBoard(this.board);
    }
    public void moveLeft()
    {
        board.movePlayer('L');
        board.updateBoard();
        window.updateBoard(this.board);
    }
    public void killGame()
    {
        board.boardInPlay = false;
        board.updateBoard();
        window.updateBoard(this.board);
    }
    public void keyboardAction()
    {
        window.getMainWindow().addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }
            public void keyPressed(KeyEvent e) {
                if(board.boardInPlay) {
                    processKeyboard(e);
                    if (timerRunning == false) {
                        timer.scheduleAtFixedRate(task, 0, 50);
                        timerRunning = true;
                    }
                }
                else if(!board.boardInPlay)
                {
                    if(e.getKeyCode() == KeyEvent.VK_R)
                    {
                        //create the board
                        board.initBoard();
                        //visualize the board
                        window.initWindow(screenWidth,screenHeight,board,boardQuality);
                        timerRunning = false;
                        timer = new Timer();
                        task = new TimerTask()
                        {
                            @Override
                            public void run() {
                                if(board.boardInPlay)
                                    processKeyboardRepeat(currentKey);
                                else{
                                    timer.cancel();
                                    timer.purge();
                                    window.scoreScreen();
                                }
                            }
                        };
                    }
                }
            }
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void processKeyboard(KeyEvent e){
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if(currentKey != 'U') {
                    moveUp();
                    currentKey = 'U';
                    System.out.println("Moving Up");
                }
                break;
            case KeyEvent.VK_DOWN:
                if(currentKey != 'D') {
                    moveDown();
                    currentKey = 'D';
                    System.out.println("Moving Down");
                }
                break;
            case KeyEvent.VK_LEFT:
                if(currentKey != 'L') {
                    moveLeft();
                    currentKey = 'L';
                    System.out.println("Moving Left");
                }
                break;
            case KeyEvent.VK_RIGHT :
                if(currentKey != 'R') {
                    moveRight();
                    currentKey = 'R';
                    System.out.println("Moving Right");
                }
                break;
        }
    }
    public void processKeyboardRepeat(char key){
        switch(key)
        {
            case 'U':
                moveUp();
                break;
            case 'D':
                moveDown();
                break;
            case 'L':
                moveLeft();
                break;
            case 'R':
                moveRight();
                break;
            case 'k':
                killGame();
                break;
            default:
                moveRight();
                break;
        }
    }
}
