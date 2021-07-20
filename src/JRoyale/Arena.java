import java.util.ArrayList;
import java.util.Iterator;
import javafx.geometry.Point2D;

/**
 * this class contains the whole arena that battle occures in and details
 * @version 1.0
 */
public class Arena
{
    /**
     * the of arena rows
     */
    private final int rows = 18;
    
    /**
     * the number of arena columns
     */
    private final int columns = 32;

    /**
     * the list arena creatures 
     */
    private ArrayList<Creature> creatures;
    
    /**
     * the king tower of the player
     */
    private Creature playerKing;

    /**
     * the king tower of the bot
     */
    private Creature botKing;

    /**
     * the top princess tower of the player
     */
    private Creature playerUpPrincess;

    /**
     * the down princess tower of the player
     */
    private Creature playerDownPrincess;

    /**
     * the top princess tower of the bot
     */
    private Creature botUpPrincess;

    /**
     * the down princess tower of the bot
     */
    private Creature botDownPrincess;

    /**
     * creates a new arena
     */
    public Arena() 
    {
        creatures = new ArrayList<>();
        King king = new King();
        king.loadImages();
        Princess princess = new Princess();
        princess.loadImages();
        playerKing = king.makeCreature(new Point2D(80, 360), 1).get(0);
        botKing = king.makeCreature(new Point2D(1200, 360), -1).get(0);
        playerUpPrincess = princess.makeCreature(new Point2D(220, 140), 1).get(0);
        playerDownPrincess = princess.makeCreature(new Point2D(220, 580), 1).get(0);
        botUpPrincess = princess.makeCreature(new Point2D(1060, 140), -1).get(0);
        botDownPrincess = princess.makeCreature(new Point2D(1060, 580), -1).get(0);
        creatures.add(playerKing);
        creatures.add(botKing);
        creatures.add(playerUpPrincess);
        creatures.add(playerDownPrincess);
        creatures.add(botUpPrincess);
        creatures.add(botDownPrincess);
    }

    /**
     * adds a new creature to the list of creatures
     * @param creature
     */
    public void addCreature(Creature creature)
    {
        creatures.add(creature);
    }

    /**
     * removes a creature from list of creatures
     * @param creature
     * @return the status of removing
     */
    public boolean removeCreature(Creature creature)
    {
        return creatures.remove(creature);
    }

    /**
     * the main loop that arena done every frame of the game 
     */
    public void step()
    {
        Iterator<Creature> it = creatures.iterator();
        while(it.hasNext())
        {
            Creature c = it.next();
            if(c.isEliminated() && !(c.getCard() instanceof Spell))
            {
                it.remove();
            }
        }
        it = creatures.iterator();

        while (it.hasNext())
        {
            Creature c = it.next();
            c.step();

            if(c.getCard() instanceof Spell)
                it.remove();
                c.getCard().playAttackSound();
        }
    }

    /**
     * @return list of creatures
     */
    public ArrayList<Creature> getCreatures() 
    {
        return creatures;
    }

    /**
     * @return king tower of player
     */
    public Creature getPlayerKing()
    {
        return playerKing;
    }

    /**
     * @return king tower of bot
     */
    public Creature getBotKing()
    {
        return botKing;
    }

    /**
     * @return top princess tower of player
     */
    public Creature getPlayerUpPrincess()
    {
        return playerUpPrincess;
    }

    /**
     * @return down princess of player
     */
    public Creature getPlayerDownPrincess()
    {
        return playerDownPrincess;
    }

    /**
     * @return top princess tower of bot
     */
    public Creature getBotUpPrincess()
    {
        return botUpPrincess;
    }

    /**
     * @return down princess tower of bot
     */
    public Creature getBotDownPrincess()
    {
        return botDownPrincess;
    }

    /**
     * @return number of arena rows
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * @return number of arena columns
     */
    public int getColumns()
    {
        return columns;
    }
}
