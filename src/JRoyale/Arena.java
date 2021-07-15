import java.util.ArrayList;
import java.util.Iterator;

public class Arena
{
    private final int rows = 18;
    
    private final int columns = 32;

    private ArrayList<Creature> creatures;

    public Arena() 
    {
        creatures = new ArrayList<>();
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
            if(it.next().isEliminated())
            {
                it.remove();
            }
        }
        for(Creature c : creatures)
        {
            c.step();
        }
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }
}
