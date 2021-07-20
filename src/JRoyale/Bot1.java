import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Point2D;

public class Bot1 extends Bot
{
    
    public Bot1() 
    {
        super("CPU Easy", GameManager.getRandomDeck(1), 1);
    }

    @Override
    public void step() 
    {
        Battle battle = GameManager.getInstance().getBattle();
        Random random = new Random();
        int cardNumber;
        cardNumber = random.nextInt(4);
        cardNumber += 4;
        Card chosenCard = battle.getBotCardsQueue().get(cardNumber);
        if(chosenCard.getCost() <= battle.getBotElixirBar().getElixir())
        {
            if(chosenCard instanceof Spell)
            {
                int x = random.nextInt(1260);
                int y = random.nextInt(700);
                x += 10;
                y += 10;
                Creature creature = chosenCard.makeCreature(new Point2D(x, y), -1).get(0);
                battle.getArena().getCreatures().add(creature);
                battle.getBotCardsQueue().remove(cardNumber);
                battle.getBotCardsQueue().add(0, chosenCard);
                battle.getBotElixirBar().takeExir(chosenCard.getCost());
            }
            else
            {
                int x = random.nextInt(300);
                int y = random.nextInt(700);
                x += 700;
                y += 10;
                ArrayList<Creature> cretures = chosenCard.makeCreature(new Point2D(x, y), -1);
                for(Creature c : cretures)
                {
                    battle.getArena().getCreatures().add(c);
                }
                battle.getBotCardsQueue().remove(cardNumber);
                battle.getBotCardsQueue().add(0, chosenCard);
                battle.getBotElixirBar().takeExir(chosenCard.getCost());

            }
        }
    }


}
