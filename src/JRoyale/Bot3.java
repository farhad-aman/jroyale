import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Bot3 extends Bot
{
    /**
     * number of times that the bot has avoided choosing a card
     * */
    private int moveAvoided = 0;

    public Bot3() 
    {
        super("CPU Hard", GameManager.getRandomDeck(3), 3);
    }

    /**
     * manages the bot decisions in each frame
     * */
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
            Point2D position = findBestPoint(chosenCard);

            if(position != null) 
            {
                ArrayList<Creature> creatures = chosenCard.makeCreature(position, -1);
                for (Creature c : creatures) 
                {
                    battle.getArena().getCreatures().add(c);
                }
                moveAvoided = 0;
                battle.getBotCardsQueue().remove(cardNumber);
                battle.getBotCardsQueue().add(0, chosenCard);
                battle.getBotElixirBar().takeExir(chosenCard.getCost());
            }
        }
    }

    /**
     * finds the best point for the given card
     * @param chosenCard to find point for
     * @return the best point for the chosen ard
     * */
    private Point2D findBestPoint(Card chosenCard)
    {
        int populatedRegion = findPopulated(chosenCard);
        Point2D tempPosition = null;
        int count = 0, countLoop = 0;

        if(populatedRegion != -1 && !(chosenCard instanceof Spell))
            tempPosition = findPoint(chosenCard, populatedRegion);

        if (chosenCard instanceof Spell && populatedRegion != -1) 
        {
            while (count == 0 && countLoop <= 10)
            {
                tempPosition = findPoint(chosenCard, populatedRegion);

                int side = 1;

                if (chosenCard instanceof Rage)
                    side = -1;

                for (Creature c : GameManager.getInstance().getBattle().getArena().getCreatures()) 
                {
                    if (c.getSide() == side && c.getPosition().distance(tempPosition) <= ((c.getCard() instanceof King ? 80 : (c.getCard() instanceof Princess ? 60 : (c.getCard() instanceof Building ? 50 : 40))) * ((Spell) chosenCard).getRange()))
                        count++;
                }
                countLoop++;
            }
        }
        return tempPosition;
    }

    /**
     * finds the most populated place for the armies
     * @param chosenCard to decide
     * @return the most populated place
     * */
    private int findPopulated(Card chosenCard)
    {
        int most = 0, up = 0, middle = 0, down = 0, eUp = 0, eDown = 0, eMiddle = 0, downBridge = 0, upBridge = 0, side = 1;

        if(chosenCard instanceof Rage)
            side = -1;

        for(Creature c : GameManager.getInstance().getBattle().getArena().getCreatures())
        {
            double Y = c.getPosition().getY(), X = c.getPosition().getX();
            int effect = 1;

            if(c.getSide() == side && (chosenCard.canHit(chosenCard.getTarget(), c.getCard().getType()) || chosenCard instanceof Spell))
            {
                effect += considerEnemy(chosenCard, c.getCard());

                if (Y <= 240)
                {
                    if(X < 600)
                        eUp += effect;
                    else if(X >= 600 && X <= 680)
                        upBridge += effect;
                    else
                        up += effect;
                }
                else if (Y > 240 && Y < 480)
                {
                    if(X < 600)
                        eMiddle += effect;
                    else
                        middle += effect;
                }
                else
                {
                    if(X < 600)
                        eDown += effect;
                    else if(X >= 600 && X <= 680)
                        downBridge += effect;
                    else
                        down += effect;
                }
            }
        }
        boolean upDestroyed = GameManager.getInstance().getBattle().getArena().getPlayerUpPrincess().isEliminated();
        boolean downDestroyed = GameManager.getInstance().getBattle().getArena().getPlayerDownPrincess().isEliminated();

        if(!(chosenCard  instanceof Rage))
        {
            if (upDestroyed && !downDestroyed)
                eUp++;
            if (downDestroyed && !upDestroyed)
                eDown++;
            if (upDestroyed && downDestroyed)
                eMiddle += 2;
        }

        most = upBridge;
        if(most <= downBridge)
            most = downBridge;
        if(most <= eDown)
            most = eDown;
        if(most <= eUp)
            most = eUp;
        if(most <= eMiddle)
            most = eMiddle;
        if(most <= up)
            most = up;
        if(most <= down)
            most = down;
        if(most <= middle)
            most = middle;

        if(most < -1 && moveAvoided < 30) 
        {
            moveAvoided++;
            return -1;
        }
        else if(most == up)
            return 4;
        else if (most == middle)
            return 6;
        else if(most == down)
            return 5;
        else if(most == eUp)
        {
            if(upDestroyed || chosenCard instanceof Spell)
                return 1;
            else
                return 4;
        }
        else if(most == eDown)
        {
            if(downDestroyed || chosenCard instanceof Spell)
                return 2;
            else
                return 5;
        }
        else if(most == downBridge)
        {
            if((downDestroyed && !(chosenCard instanceof Building)) || chosenCard instanceof Spell)
                return 8;
            else if(downDestroyed)
                return 2;
            else
                return 5;
        }
        else if(most == upBridge)
        {
            if((upDestroyed && !(chosenCard instanceof Building)) || chosenCard instanceof Spell)
                return 7;
            else if(upDestroyed)
                return 1;
            else
                return 4;
        }
        else if(most == eMiddle)
        {
            if((upDestroyed && downDestroyed) || chosenCard instanceof Spell)
                return 3;
            else
                return 6;
        }
        return 0;
    }

    /**
     * checks the enemy type and effect
     * @param chosenCard to create
     * @param enemy to consider its type
     * */
    private int considerEnemy(Card chosenCard, Card enemy) 
    {
        int effect = 0;

        if(chosenCard instanceof Valkyrie && (enemy instanceof Barbarians || enemy instanceof Archer || enemy instanceof Giant))
            effect += 2;
        if((chosenCard instanceof Fireball || chosenCard instanceof Arrows) && (enemy instanceof Barbarians || enemy instanceof Archer))
            effect += 2;
        if(chosenCard instanceof Dragon && (enemy instanceof Barbarians || enemy instanceof Valkyrie || enemy instanceof Pekka || enemy instanceof Giant || enemy instanceof Cannon))
            effect += 3;
        if(chosenCard instanceof Giant &&  enemy instanceof Dragon)
            effect -= 1;
        if(chosenCard instanceof Archer && enemy instanceof Giant)
            effect += 2;
        if(chosenCard instanceof Barbarians && (enemy instanceof Giant || enemy instanceof Archer))
            effect += 1;
        if(chosenCard instanceof Pekka && (enemy instanceof Giant || enemy instanceof Pekka))
            effect += 1;
        if(chosenCard instanceof Rage && enemy instanceof Giant)
            effect += 1;
        if((chosenCard instanceof Barbarians || chosenCard instanceof Archer) && enemy instanceof Valkyrie)
            effect -= 1;
        if(enemy instanceof Dragon && (chosenCard instanceof Barbarians || chosenCard instanceof Valkyrie || chosenCard instanceof Pekka || chosenCard instanceof Giant || chosenCard instanceof Cannon))
            effect -= 1;
        if(enemy instanceof Barbarians && chosenCard instanceof Giant)
            effect -= 1;
        /*
        *
        * TODO: considering other type of the creatures
        *
         */
        return effect;
    }

    /**
     * creates a point for the given status
     * @param status situation to create army//-1->spell//0->arbitrary//1->enemy up princess//2->enemy down princess//3->enemy king//4->up right//5->down right//6-> near to bot king//7->up bridge//8->down bridge
     * @return the appropriate position to create
     * */
    private Point2D findPoint(Card  chosenCard, int status) 
    {
        int x = 0, y = 0;
        Random rand = new Random();

        if(status == -1)
        {
            x = rand.nextInt(280);
            y = 80 + rand.nextInt(1120);
        }
        else if(status == 0)
        {
            x = 720 + rand.nextInt(200);
            y = 20 + rand.nextInt(680);
        }
        else if(status == 1)
        {
            if(!(chosenCard instanceof Spell))
            {
                x = 440 + rand.nextInt(160);
                y = 40 + rand.nextInt(200);
            }
            else
            {
                x = 200 + rand.nextInt(240);
                y = 80 + rand.nextInt(120);
            }
        }
        else if(status == 2)
        {
            if(!(chosenCard instanceof Spell))
            {
                x = 440 + rand.nextInt(160);
                y = 480 + rand.nextInt(200);
            }
            else
            {
                x = 200 + rand.nextInt(240);
                y = 1080 + rand.nextInt(120);
            }
        }
        else if(status == 3)
        {
            if(!(chosenCard instanceof Spell))
            {
                x = 440 + rand.nextInt(160);
                y = 240 + rand.nextInt(240);
            }
            else
            {
                x = 120 + rand.nextInt(280);
                y = 280 + rand.nextInt(160);
            }
        }
        else if(status == 4)
        {
            x = 720 + rand.nextInt(200);
            y = 40 + rand.nextInt(200);
        }
        else if(status == 5)
        {
            x = 720 + rand.nextInt(200);
            y = 480 + rand.nextInt(200);
        }
        else if(status == 6)
        {
            x = 840 + rand.nextInt(320);
            y = 240 + rand.nextInt(240);
        }
        else if(status == 7)
        {
            x = 600 + rand.nextInt(80);
            y = 100 + rand.nextInt(80);
        }
        else if(status == 8)
        {
            x = 600 + rand.nextInt(80);
            y = 540 + rand.nextInt(80);
        }
        return new Point2D(x, y);
    }
}