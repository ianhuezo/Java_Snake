import java.util.*;

public class SnakePlayer {

    private List<BodyPart> parts = new ArrayList();
    private int tailX = -1;
    private int tailY = -1;
    private boolean snakeAte = false;
    //snake
    void addToEnd(BodyPart part)
    {
        parts.add(part);
    }
    void addToTail(BodyPart part) {parts.add(0,part);}

    //get the head
    BodyPart getHead()
    {
        return parts.get(parts.size() - 1);
    }
    int getTailX() { return tailX;}
    int getTailY() { return tailY;}
    //move function
    void moveSnake(char direction)
    {
        //get the current head of the list
        int headX = parts.get(parts.size() - 1).getX();
        int headY = parts.get(parts.size() - 1).getY();

        tailX = parts.get(0).getX();
        tailY = parts.get(0).getY();

        int nextHeadX;
        int nextHeadY;
        //remove the current tail
        parts.remove(0);
        snakeAte = false;
        //determine the next direction in the array
        switch(direction)
        {
            case 'L':
                nextHeadX = headX;
                nextHeadY = headY - 1;
                break;
            case 'U':
                nextHeadX = headX - 1;
                nextHeadY = headY;
                break;
            case 'R':
                nextHeadX = headX;
                nextHeadY = headY + 1;
                break;
            case 'D':
                nextHeadX = headX + 1;
                nextHeadY = headY;
                break;
            default:
                nextHeadX = headX;
                nextHeadY = headY;
                break;

        }
        BodyPart newPart = new BodyPart(nextHeadX, nextHeadY);
        parts.add(newPart);
    }
    void killSnake()
    {
         parts.removeAll(parts);
    }
}
