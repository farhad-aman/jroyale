import javafx.scene.image.Image;

public class Archer extends Troop
{

    public Archer() 
    {
        super("Archer", 
              3, 
              1200, 
              2, 
              "both", 
              5, 
              false, 
              2, 
              new int[] {0, 125, 127, 151, 166, 182}, 
              new int[] {0, 33, 44, 48, 53, 58});
    }
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages(){
        pics.put(0, new Image("resources/cards/archer/archer150.jpg"));
        pics.put(1, new Image("resources/cards/archer/archerRunForward.gif"));
        pics.put(2, new Image("resources/cards/archer/archerRunBackward.gif"));
        pics.put(3, new Image("resources/cards/archer/archerAttackForward.gif"));
        pics.put(4, new Image("resources/cards/archer/archerAttackBackward.gif"));
        pics.put(5, new Image("resources/cards/archer/archerDeathForward.gif"));
        pics.put(6, new Image("resources/cards/archer/archerDeathBackward.gif"));
    }
}
