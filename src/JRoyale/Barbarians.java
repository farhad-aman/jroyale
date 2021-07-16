import javafx.scene.image.Image;

public class Barbarians extends Troop
{

    public Barbarians() 
    {
        super("Barbarians", 
              5, 
              1500, 
              2, 
              "ground", 
              1, 
              false, 
              4, 
              new int[] {0, 300, 330, 363, 438, 480}, 
              new int[] {0, 75, 82, 90, 99, 109});
    }
    
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/barbarians/barbarians150.jpg"));
        pics.put(-1, new Image("resources/cards/barbarians/barbarians150wb.jpg"));
        pics.put(1, new Image("resources/cards/barbarians/barbariansRunForward.gif"));
        pics.put(2, new Image("resources/cards/barbarians/barbariansRunBackward.gif"));
        pics.put(3, new Image("resources/cards/barbarians/barbariansAttackForward.gif"));
        pics.put(4, new Image("resources/cards/barbarians/barbariansAttackBackward.gif"));
        pics.put(5, new Image("resources/cards/barbarians/barbariansDeathForward.gif"));
        pics.put(6, new Image("resources/cards/barbarians/barbariansDeathBackward.gif"));
    }
}
