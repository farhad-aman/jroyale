import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Barbarians extends Troop
{

    public Barbarians() 
    {
        super("ground",
                "Barbarians",
              5, 
              1500, 
              2, 
              "ground", 
              50,
              1, 
              false, 
              4, 
              new int[] {0, 300, 330, 363, 438, 480}, 
              new int[] {0, 75, 82, 90, 99, 109},
              new MediaPlayer(new Media(new File("resources/cards/barbarians/barbariansAttack.mp3").toURI().toString())),
              new MediaPlayer(new Media(new File("resources/cards/barbarians/barbariansDeath.mp3").toURI().toString())));
    }
    
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/barbarians/barbarians150.png"));
        pics.put(-1, new Image("resources/cards/barbarians/barbarians150wb.jpg"));
        pics.put(1, new Image("resources/cards/barbarians/barbariansRunForward.gif"));
        pics.put(2, new Image("resources/cards/barbarians/barbariansRunBackward.gif"));
        pics.put(3, new Image("resources/cards/barbarians/barbariansAttackForward.gif"));
        pics.put(4, new Image("resources/cards/barbarians/barbariansAttackBackward.gif"));
        pics.put(5, new Image("resources/cards/barbarians/barbariansDeathForward.gif"));
        pics.put(6, new Image("resources/cards/barbarians/barbariansDeathBackward.gif"));
    }

    @Override
    public ArrayList<Creature> makeCreature(Point2D center, int side) 
    {
        Point2D c1 = new Point2D(center.getX() - 20, center.getY() + 20);
        Point2D c2 = new Point2D(center.getX() + 20, center.getY() + 20);
        Point2D c3 = new Point2D(center.getX() - 20, center.getY() - 20);
        Point2D c4 = new Point2D(center.getX() + 20, center.getY() - 20);
        ArrayList<Creature> creatures = new ArrayList<>();
        if(side == -1)
        {
            if(GameManager.getInstance().getBattle().getBot() instanceof Bot1)
            {
                creatures.add(new Creature(this, 1, c1, -1));
                creatures.add(new Creature(this, 1, c2, -1));
                creatures.add(new Creature(this, 1, c3, -1));
                creatures.add(new Creature(this, 1, c4, -1));
            }
            else if(GameManager.getInstance().getBattle().getBot() instanceof Bot2)
            {
                creatures.add(new Creature(this, 3, c1, -1));
                creatures.add(new Creature(this, 3, c2, -1));
                creatures.add(new Creature(this, 3, c3, -1));
                creatures.add(new Creature(this, 3, c4, -1));
            }
            else
            {
                creatures.add(new Creature(this, 5, c1, -1));
                creatures.add(new Creature(this, 5, c2, -1));
                creatures.add(new Creature(this, 5, c3, -1));
                creatures.add(new Creature(this, 5, c4, -1));
            }
            
        }
        else
        {
            creatures.add(new Creature(this, GameManager.getInstance().getCurrentPlayer().getLevel(), c1, 1));
            creatures.add(new Creature(this, GameManager.getInstance().getCurrentPlayer().getLevel(), c2, 1));
            creatures.add(new Creature(this, GameManager.getInstance().getCurrentPlayer().getLevel(), c3, 1));
            creatures.add(new Creature(this, GameManager.getInstance().getCurrentPlayer().getLevel(), c4, 1));
        }
        return creatures;
    }
}
