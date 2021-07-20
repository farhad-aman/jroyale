import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Random;

public class Bot2 extends Bot
{

    public Bot2() 
    {
        super("CPU Normal", GameManager.getRandomDeck(2), 2);
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
                int x = 0, y = 0;

                if(chosenCard instanceof Rage){
                    x = random.nextInt(440) + 680;
                }
                else{
                    x = random.nextInt(360) + 160;
                }
                y = random.nextInt(700);
                y += 10;
                Creature creature = chosenCard.makeCreature(new Point2D(x, y), -1).get(0);
                battle.getArena().getCreatures().add(creature);
            }
            else
            {
                ArrayList<Creature> creatures = chosenCard.makeCreature(towerStatus(chosenCard), -1);
                for(Creature c : creatures)
                {
                    battle.getArena().getCreatures().add(c);
                }
            }
            battle.getBotCardsQueue().remove(cardNumber);
            battle.getBotCardsQueue().add(0, chosenCard);
            battle.getBotElixirBar().takeExir(chosenCard.getCost());
        }
    }

    private Point2D towerStatus(Card card){
        boolean up = GameManager.getInstance().getBattle().getArena().getPlayerUpPrincess().isEliminated();
        boolean down = GameManager.getInstance().getBattle().getArena().getPlayerDownPrincess().isEliminated();
        Random random = new Random();
        int x = 0, y = 0;

        if(up && down){
            x = random.nextInt(160) + 440;

            y = random.nextInt(700) + 10;
        }
        else if(!up && down){
            x = random.nextInt(240) + 440;

            if(x > 600){
                y = random.nextInt(80) + 541;
            }
            else
                y = random.nextInt(300) + 400;
        }
        else if(up && !down){
            x = random.nextInt(240) + 440;

            if(x > 600){
                y = random.nextInt(80) + 101;
            }
            else
                y = random.nextInt(140) + 40;
        }
        else{
            x = random.nextInt(280) + 680;
            y = random.nextInt(2) == 0 ? random.nextInt(260) + 40 : random.nextInt(260) + 440;
        }
        if(card instanceof Building && !((y < 10 || y > 1110) || ((x <= 700 || x >= 580) && ((y <= 180 && y >= 100) || (y <= 620 && y >= 540))))){
            System.out.println("building handled");
            return towerStatus(card);
        }
        return new Point2D(x, y);
    }
}