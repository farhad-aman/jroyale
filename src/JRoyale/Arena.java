import java.util.ArrayList;
import java.util.Iterator;
import javafx.geometry.Point2D;

public class Arena
{
    private final int rows = 18;
    
    private final int columns = 32;

    private ArrayList<Creature> creatures;
    
    private Creature playerKing;

    private Creature botKing;

    private Creature playerUpPrincess;

    private Creature playerDownPrincess;

    private Creature botUpPrincess;

    private Creature botDownPrincess;

    public Arena() 
    {
        creatures = new ArrayList<>();
        King king = new King();
        Princess princess = new Princess();
        playerKing = king.makeCreature(new Point2D(80, 360), 1).get(0);
        botKing = king.makeCreature(new Point2D(1200, 360), -1).get(0);
        playerUpPrincess = princess.makeCreature(new Point2D(220, 140), 1).get(0);
        playerDownPrincess = princess.makeCreature(new Point2D(220, 580), 1).get(0);
        botUpPrincess = princess.makeCreature(new Point2D(1060, 140), -1).get(0);
        botDownPrincess = princess.makeCreature(new Point2D(106, 580), -1).get(0);
        creatures.add(playerKing);
        creatures.add(botKing);
        creatures.add(playerUpPrincess);
        creatures.add(playerDownPrincess);
        creatures.add(botUpPrincess);
        creatures.add(botDownPrincess);
    }

    public void addCreature(Creature creature)
    {
        creatures.add(creature);
    }

    public boolean removeCreature(Creature creature)
    {
        return creatures.remove(creature);
    }

    public void step()
    {
        Iterator<Creature> it = creatures.iterator();
        while(it.hasNext())
        {
            Creature c = it.next();
            if(c.isEliminated())
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
        }
    }

    public ArrayList<Creature> getCreatures() 
    {
        return creatures;
    }

    public Creature getPlayerKing()
    {
        return playerKing;
    }

    public Creature getBotKing()
    {
        return botKing;
    }

    public Creature getPlayerUpPrincess()
    {
        return playerUpPrincess;
    }

    public Creature getPlayerDownPrincess()
    {
        return playerDownPrincess;
    }

    public Creature getBotUpPrincess()
    {
        return botUpPrincess;
    }

    public Creature getBotDownPrincess()
    {
        return botDownPrincess;
    }

    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }
}
