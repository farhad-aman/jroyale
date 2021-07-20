import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * this class contains the inferno card in the game
 * @version 1.0
 */
public class Inferno extends Building
{
    /**
     * the temp target that inferno use it
     */
    private Creature tempTarget;

    /**
     * 
     */
    private int tempTargetTime;

    /**
     * the minimum damage of the inferno
     */
    private final int[] minDamage = {0, 20, 22, 24, 26, 29};

    /**
     * the maximum damage of the inferno
     */
    private final int[] maxDamage = {0, 400, 440, 484, 532, 584};

    /**
     * creates a new inferno
     */
    public Inferno() 
    {
        super("building", 
              "Inferno", 
              5, 
              400, 
              "both", 
              100, 
              6, 
              40000, 
              new int[] {0, 800, 880, 968, 1064, 1168},
              new MediaPlayer(new Media(new File("resources/cards/inferno/infernoAttack.mp3").toURI().toString())),
              new MediaPlayer(new Media(new File("resources/cards/inferno/infernoDeath.mp3").toURI().toString())));
        tempTarget = null;
        tempTargetTime = 0;
    }

    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/inferno/inferno150.png"));
        pics.put(-1, new Image("resources/cards/inferno/inferno150wb.jpg"));
        pics.put(1, new Image("resources/cards/inferno/inferno.gif"));
    }

    /**
     * calculates new damage of the inferno
     * @param level
     * @param underRage
     * @return
     */
    public double calculateInfernoDamage(int level, boolean underRage)
    {
        if(tempTarget == null)
            return 0;
        return (((maxDamage[level] - minDamage[level]) / 10.0) *  ((Math.min(tempTargetTime, 10000.0)) / 1000.0) + minDamage[level]) * (underRage ? 1.4 : 1);
    }

    /**
     * calculates time that inferno attacks to increase damage
     * @param tempTarget
     */
    public void addTempTargetTime(Creature tempTarget) 
    {
        if(tempTarget == this.tempTarget)
            this.tempTargetTime += 1000 / GameManager.FPS;
        else {
            this.tempTarget = tempTarget;
            tempTargetTime = 0;
        }
    }

    @Override
    public int getDamage(int level) 
    {
        return 0;//junk
    }

    public Creature getTempTarget()
    {
        return tempTarget;
    }
}
