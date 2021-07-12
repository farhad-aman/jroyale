import javafx.scene.image.Image;

public class Giant extends Troop
{

    public Giant() 
    {
        super("Giant", 
              5, 
              1500, 
              1, 
              "building", 
              1, 
              false, 
              1, 
              new int[] {0, 2000, 2200, 2420, 2660, 2920}, 
              new int[] {0, 126, 138, 152, 167, 183});
    }
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages(){
        pics.put(0, new Image("resources/cards/giant/giant150.jpg"));
        pics.put(1, new Image("resources/cards/giant/giantWalkForward.gif"));
        pics.put(2, new Image("resources/cards/giant/giantWalkBackward.gif"));
        pics.put(3, new Image("resources/cards/giant/giantAttackForward.gif"));
        pics.put(4, new Image("resources/cards/giant/giantAttackBackward.gif"));
        pics.put(5, new Image("resources/cards/giant/giantDeath.gif"));
        pics.put(6, new Image("resources/cards/giant/giantDeath.gif"));
    }
}
