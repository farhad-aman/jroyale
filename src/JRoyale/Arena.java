import java.util.ArrayList;

public class Arena
{
    private final int rows = 20;//not precise
    private final int columns = 40;//not precise
    private ArrayList<Creature> playerCreatures;
    private ArrayList<Creature> botCreatures;

    public Arena() {
        playerCreatures = new ArrayList<>();
        botCreatures = new ArrayList<>();
    }

    public void addPlayerCreature(Creature creature){playerCreatures.add(creature);}

    public void addBotCreature(Creature creature){botCreatures.add(creature);}

    public boolean removePlayerCreature(Creature creature){
        return playerCreatures.remove(creature);
    }

    public boolean removeBotCreature(Creature creature){
        return botCreatures.remove(creature);
    }

    public void step(){
        for(Creature creature : playerCreatures)
            creature.step(this, 2);

        for(Creature creature : botCreatures)
            creature.step(this, 1);
    }
}
