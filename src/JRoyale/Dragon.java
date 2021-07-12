import javafx.scene.image.Image;

public class Dragon extends Troop
{

    public Dragon() 
    {
        super("Dragon", 
              4, 
              1800, 
              3, 
              "both", 
              3.5, 
              true, 
              1, 
              new int[] {0, 800, 880, 968, 1064, 1168}, 
              new int[] {0, 100, 110, 121, 133, 146});
    }
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages(){
        pics.put(0, new Image("resources/cards/dragon/dragon150.jpg"));
        pics.put(1, new Image("resources/cards/dragon/dragonFlyForward.gif"));
        pics.put(2, new Image("resources/cards/dragon/dragonFlyBackward.gif"));
        pics.put(3, new Image("resources/cards/dragon/dragonAttackForward.gif"));
        pics.put(4, new Image("resources/cards/dragon/dragonAttackBackward.gif"));
        pics.put(5, new Image("resources/cards/dragon/dragonDeath.gif"));
        pics.put(6, new Image("resources/cards/dragon/dragonDeath.gif"));
    }
}
