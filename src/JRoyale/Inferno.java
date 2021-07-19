import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

public class Inferno extends Building
{
    private Creature tempTarget;

    private int tempTargetTime;

    private final int[] minDamage = {0, 20, 22, 24, 26, 29};

    private final int[] maxDamage = {0, 400, 440, 484, 532, 584};

    public Inferno() 
    {
        super("building", "Inferno", 5, 400, "both", 100, 6, 40000, new int[] {0, 800, 880, 968, 1064, 1168});
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

    public double calculateInfernoDamage(int level){
        if(tempTarget == null)
            return 0;
        double damage =  ((maxDamage[level] - minDamage[level]) / 10) *  ((Math.min(tempTargetTime, 10000)) / 1000) + minDamage[level];
        return damage;
    }

    public void addTempTargetTime(Creature tempTarget) {
        if(tempTarget == this.tempTarget)
            this.tempTargetTime += 1000 / GameManager.FPS;
        else {
            this.tempTarget = tempTarget;
            tempTargetTime = 0;
        }
    }

    @Override
    public int getDamage(int level) {
        return 0;//junk
    }
}
