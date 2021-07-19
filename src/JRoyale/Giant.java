import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Giant extends Troop
{

    public Giant() 
    {
        super("ground",
                "Giant",
              5, 
              1500, 
              1, 
              "building", 
              100,
              1, 
              false, 
              1, 
              new int[] {0, 2000, 2200, 2420, 2660, 2920}, 
              new int[] {0, 126, 138, 152, 167, 183},
              new MediaPlayer(new Media(new File("resources/cards/giant/giantAttack.mp3").toURI().toString())),
              new MediaPlayer(new Media(new File("resources/cards/giant/giantDeath.mp3").toURI().toString())));
    }
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/giant/giant150.png"));
        pics.put(-1, new Image("resources/cards/giant/giant150wb.jpg"));
        pics.put(1, new Image("resources/cards/giant/giantWalkForward.gif"));
        pics.put(2, new Image("resources/cards/giant/giantWalkBackward.gif"));
        pics.put(3, new Image("resources/cards/giant/giantAttackForward.gif"));
        pics.put(4, new Image("resources/cards/giant/giantAttackBackward.gif"));
        pics.put(5, new Image("resources/cards/giant/giantDeathForward.gif"));
        pics.put(6, new Image("resources/cards/giant/giantDeathBackward.gif"));
    }
}
