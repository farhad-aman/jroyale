import javafx.scene.image.Image;

public class Inferno extends Building
{

    public Inferno() 
    {
        super("Inferno", 5, 6);
    }
    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages(){
        pics.put(0, new Image("resources/cards/inferno/inferno150.jpg"));
        pics.put(10, new Image("resources/cards/inferno/inferno.gif"));
    }
}
