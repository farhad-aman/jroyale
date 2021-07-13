import javafx.scene.image.Image;

public class Cannon extends Building
{ 
    public final int damage[] = {0, 60, 66, 72, 79, 87};

    public Cannon() 
    {
        super("Cannon", 3, 800, "ground", 5.5, 30000, new int[] {0, 380, 418, 459, 505, 554});
    }

    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/cannon/cannon150.jpg"));
        pics.put(3, new Image("resources/cards/cannon/cannonShootRight.gif"));
        pics.put(4, new Image("resources/cards/cannon/cannonShootLeft.gif"));
        pics.put(7, new Image("resources/cards/cannon/cannonball.gif"));
        pics.put(8, new Image("resources/cards/cannon/cannonTurnRight.gif"));
        pics.put(9, new Image("resources/cards/cannon/cannonTurnLeft.gif"));
    }
}