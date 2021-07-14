import javafx.scene.image.Image;

public class Pekka extends Troop
{

    public Pekka()
    {
        super("Pekka", 
              4, 
              1800, 
              3, 
              "ground", 
              1, 
              false, 
              1, 
              new int[] {0, 600, 660, 726, 798, 876}, 
              new int[] {0, 325, 357, 393, 432, 474});
    }
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages(){
        pics.put(0, new Image("resources/cards/pekka/pekka150.jpg"));
        pics.put(1, new Image("resources/cards/pekka/pekkaRunForward.gif"));
        pics.put(2, new Image("resources/cards/pekka/pekkaRunBackward.gif"));
        pics.put(3, new Image("resources/cards/pekka/pekkaAttackForward.gif"));
        pics.put(4, new Image("resources/cards/pekka/pekkaAttackBackward.gif"));
        pics.put(5, new Image("resources/cards/pekka/pekkaDeathForward.gif"));
        pics.put(6, new Image("resources/cards/pekka/pekkaDeathBackward.gif"));
    }
}
