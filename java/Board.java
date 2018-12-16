import java.util.Random;

public class Board {
    public int BOARD_SIZE = 64;
    public boolean boardInPlay = true;
    //board and snake placeholders for new game
    private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
    private SnakePlayer snake = new SnakePlayer();
    private boolean[][] boardFilled = new boolean[BOARD_SIZE][BOARD_SIZE];
    //Determines what to do with the apple
    private boolean appleEaten = false;
    public int snakeScore = 0;

    void initBoard() {
        //create an empty board with a snake, apple, and boundaries
        snakeScore = 0;
        nullBoard();
        initSnake();
        placeApple();
        boardInPlay = true;
    }
    public void movePlayer(char direction)
    {
        //move player Up, Down, Left, Right
        switch(direction)
        {
            case 'D':
                moveSnake(direction);
                break;
            case 'U':
                moveSnake(direction);
                break;
            case 'L':
                moveSnake(direction);
                break;
            case 'R':
                moveSnake(direction);

        }
    }
    //sets the board back to nothing
    void boardOffSession()
    {
        nullBoard();
        //destroys the snake body
        snake.killSnake();
    }
    public String[][] getBoard()
    {
        return board;
    }
    void updateBoard()
    {
        //will check each iteration if the board is in play
        if(boardInPlay == false) {
            boardOffSession();
        }
    }
    protected void moveSnake(char direction)
    {
        //moves the snake each iteration
        snake.moveSnake(direction);
        //collision detection for snake
        hasCollided();
        //checks the status of the apple each iteration
        isAppleEaten();
        //if the apple is eaten, create a new apple and show on grid
        if(!appleEaten) {
            //will get rid of extra o's and update the snake's position
            board[snake.getTailX()][snake.getTailY()] = "-";
            boardFilled[snake.getTailX()][snake.getTailY()] = false;
        }
        else
        {
            //places a new apple if an apple was eaten
            placeApple();
            appleEaten = false;
            //update the players score
            snakeScore += 1;
        }

        board[snake.getHead().getX()][snake.getHead().getY()] = "o";
        boardFilled[snake.getHead().getX()][snake.getHead().getY()] = true;
    }
    protected void hasCollided()
    {
        int headX = snake.getHead().getX();
        int headY = snake.getHead().getY();
        //if o or x are found at the head, then the game loses
        if(board[headX][headY] == "o" || board[headX][headY] == "x")
        {
            boardInPlay = false;
        }
    }
    protected void isAppleEaten()
    {

        int tailX = snake.getTailX();
        int tailY = snake.getTailY();
        int headX = snake.getHead().getX();
        int headY = snake.getHead().getY();
        if(board[headX][headY] == "A")
        {
            //create new body part at tail to add onto snake each time an apple is eaten
            BodyPart newPart = new BodyPart(tailX, tailY);
            board[headX][headY] = "o";
            appleEaten = true;
            snake.addToTail(newPart);
        }
    }

    protected void nullBoard()
    {

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if((j == 0) || (i==0) || (i==BOARD_SIZE-1) || (j==BOARD_SIZE-1)){
                    //x represents the boundaries
                    board[i][j] = "x";
                    boardFilled[i][j] = true;
                }
                else
                {
                    board[i][j] = "-";
                    boardFilled[i][j] = false;
                }
            }
        }
    }
    public void initSnake()
    {
        for(int i = 0; i < 4; i++)
        {
            BodyPart newSnakePart = new BodyPart(BOARD_SIZE/2, (BOARD_SIZE/2) + i);
            snake.addToEnd(newSnakePart);
            board[snake.getHead().getX()][snake.getHead().getY()] = "o";
            boardFilled[snake.getHead().getX()][snake.getHead().getY()] = true;
        }
    }
    protected void placeApple()
    {
        //create an init random for x and y pos
        Random rand = new Random();
        int appleX = rand.nextInt(BOARD_SIZE);
        int appleY = rand.nextInt(BOARD_SIZE);
        //if the board is occupied find somewhere unoccupied and fill it
        while(boardFilled[appleX][appleY])
        {
            appleX = rand.nextInt(BOARD_SIZE);
            appleY = rand.nextInt(BOARD_SIZE);
        }
        //update the values for the apple
        boardFilled[appleX][appleY] = true;
        board[appleX][appleY] = "A";
    }
    protected void printBoard()
    {
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            for(int j = 0; j < BOARD_SIZE; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
