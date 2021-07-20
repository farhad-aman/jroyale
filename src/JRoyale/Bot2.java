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
                ArrayList<Creature> creatures = chosenCard.makeCreature(towerStatus(chosenCard, ArmiesAndTowers(chosenCard)), -1);
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
    /**
     * @return the best place to create army considering towers and number of enemy armies
     * */
    private int ArmiesAndTowers(Card chosenCard){
        return 0;
    }
    /**
     * @param status situation to create army////1->enemy up princess//2->enemy down princess//3->enemy king//4->up right//5->down right//6-> near to bot king
     * @return the appropriate position to create
     * */
    private Point2D createPoint(int status){
        Point2D position = new Point2D(640, 360);

        if(status == 1){

        }
        return position;
    }
    /**
     * @param card the chosen card to create
     * @param where place to create //0->arbitrary//1->enemy up princess//2->enemy down princess//3->enemy king//4->up right//5->down right//6-> near to bot king
     * */
    private Point2D towerStatus(Card card, int where){
        boolean upDestroyed = GameManager.getInstance().getBattle().getArena().getPlayerUpPrincess().isEliminated();
        boolean downDestroyed = GameManager.getInstance().getBattle().getArena().getPlayerDownPrincess().isEliminated();
        Random random = new Random();
        int x = 0, y = 0;

        if(upDestroyed && downDestroyed){

        }
//        if(up && down){
//            if(where == 0){
//                x = random.nextInt(160) + 440;
//
//                y = random.nextInt(700) + 10;
//            }
//        }
//        else if(!up && down){
//            x = random.nextInt(240) + 440;
//
//            if(x > 600){
//                y = random.nextInt(80) + 541;
//            }
//            else
//                y = random.nextInt(300) + 400;
//        }
//        else if(up && !down){
//            x = random.nextInt(240) + 440;
//
//            if(x > 600){
//                y = random.nextInt(80) + 101;
//            }
//            else
//                y = random.nextInt(140) + 40;
//        }
//        else{
//            x = random.nextInt(280) + 680;
//            y = random.nextInt(2) == 0 ? random.nextInt(260) + 40 : random.nextInt(260) + 440;
//        }
        if(card instanceof Building && (y < 10 || y > 1110) || ((x <= 700 || x >= 580) && ((y <= 180 && y >= 100) || (y <= 620 && y >= 540)))){
            return towerStatus(card, where);
        }
        return new Point2D(x, y);
    }
}