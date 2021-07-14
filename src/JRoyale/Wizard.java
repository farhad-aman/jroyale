import javafx.scene.image.Image;

public class Wizard extends Troop
{

    public Wizard() 
    {
        super("Wizard",
              5, 
              1700, 
              2, 
              "both", 
              5, 
              true, 
              1, 
              new int[] {0, 340, 374, 411, 452, 496}, 
              new int[] {0, 130, 143, 157, 172, 189});
    }
    
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/wizard/wizard150.jpg"));
        pics.put(-1, new Image("resources/cards/wizard/wizard150wb.jpg"));
        pics.put(1, new Image("resources/cards/wizard/wizardRunForward.gif"));
        pics.put(2, new Image("resources/cards/wizard/wizardRunBackward.gif"));
        pics.put(3, new Image("resources/cards/wizard/wizardAttackForward.gif"));
        pics.put(4, new Image("resources/cards/wizard/wizardAttackBackward.gif"));
        pics.put(5, new Image("resources/cards/wizard/wizardDeathForward.gif"));
        pics.put(6, new Image("resources/cards/wizard/wizardDeathBackward.gif"));
    }
}
