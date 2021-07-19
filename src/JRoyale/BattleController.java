import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.*;

public class BattleController 
{
    /**
     * the timer for handling game loop
     */
    private Timer timer;

    /**
     * the main model object that use singleton
     */
    private GameManager gameManager = GameManager.getInstance();

    /**
     * the arena view element in view 
     */
    private ArenaView arenaView = new ArenaView();

    private Card chosenCard;

    private int chosenCardNumber;

    private MediaPlayer battleBackgroundMusic = new MediaPlayer(new Media(new File("resources/battle/battleBackgroundMusic.mp3").toURI().toString()));
    @FXML
    private AnchorPane arenaPane;

    @FXML
    private Line leftUpLine;

    @FXML
    private Line upMiddleLine;

    @FXML
    private Line downMiddleLine;

    @FXML
    private Line middleLine;

    @FXML
    private Line downRightLine;

    @FXML
    private Line upRightLine;

    @FXML
    private Line rightLine;

    @FXML
    private Line leftDownLine;

    @FXML
    private Line upLeftLine;

    @FXML
    private Line downLeftLine;

    @FXML
    private ImageView nextCardImageView;
    
    @FXML
    private ImageView card1ImageView;
    
    @FXML
    private ImageView card2ImageView;
    
    @FXML
    private ImageView card3ImageView;

    @FXML
    private ImageView card4ImageView;
    
    @FXML
    private Label battleTimerLabel;
    
    @FXML
    private ProgressBar elixirBarProgressBar;
    
    @FXML
    private Label elixirBarLabel;
    
    @FXML
    private ImageView playerScoreImageView;
    
    @FXML
    private ImageView botScoreImageView;
    
    @FXML
    private Label playerUsernameLabel;
    
    @FXML
    private Label botUsernameLabel;
    
    @FXML
    private Rectangle card1Border;
    
    @FXML
    private Rectangle card2Border;
    
    @FXML
    private Rectangle card3Border;
    
    @FXML
    private Rectangle card4Border;
    
    @FXML
    void card1Pressed(MouseEvent event) 
    {
        if(gameManager.getBattle().getPlayerCardsQueue().get(4).getCost() <= gameManager.getBattle().getPlayerElixirBar().getElixir())
        {
            chosenCard = gameManager.getBattle().getPlayerCardsQueue().get(4);
            chosenCardNumber = 1;
        }
    }
    
    @FXML
    void card2Pressed(MouseEvent event) 
    {
        if(gameManager.getBattle().getPlayerCardsQueue().get(5).getCost() <= gameManager.getBattle().getPlayerElixirBar().getElixir())
        {
            chosenCard = gameManager.getBattle().getPlayerCardsQueue().get(5);
            chosenCardNumber = 2;
        }
    }
    
    @FXML
    void card3Pressed(MouseEvent event) 
    {
        if(gameManager.getBattle().getPlayerCardsQueue().get(6).getCost() <= gameManager.getBattle().getPlayerElixirBar().getElixir())
        {
            chosenCard = gameManager.getBattle().getPlayerCardsQueue().get(6);
            chosenCardNumber = 3;
        }
    }

    @FXML
    void card4Pressed(MouseEvent event) 
    {
        if(gameManager.getBattle().getPlayerCardsQueue().get(7).getCost() <= gameManager.getBattle().getPlayerElixirBar().getElixir())
        {
            chosenCard = gameManager.getBattle().getPlayerCardsQueue().get(7);
            chosenCardNumber = 4;
        }
    }
    
    @FXML
    void arenaPanePressed(MouseEvent event) 
    {
        double x = event.getX();
        double y = event.getY();
        if(chosenCard != null)
        {
            if(chosenCard instanceof Spell)
            {
                ArrayList<Creature> creatures = chosenCard.makeCreature(new Point2D(x, y), 1);
                Creature c = creatures.get(0);

                gameManager.getBattle().getArena().getCreatures().add(c);
                gameManager.getBattle().getPlayerCardsQueue().remove(chosenCardNumber + 3);
                gameManager.getBattle().getPlayerCardsQueue().add(0, chosenCard);
                gameManager.getBattle().getPlayerElixirBar().takeExir(chosenCard.getCost());
                chosenCard = null;
                chosenCardNumber = 0;
            }
            else
            {
                if(isAppropriate(new Point2D(x, y)) && checkTowerStatus(x, y))
                {
                    ArrayList<Creature> creatures = chosenCard.makeCreature(new Point2D(x, y), 1);
                    int count = 0;
                    while (count < creatures.size())
                    {
                        ArrayList<Point2D> positions = findPositions(x, y, creatures.size());//System.out.println("line 159");
                        for (int i = 0; i < creatures.size(); i++) {//System.out.println("line 160");
                            if (checkTowerStatus(positions.get(i).getX(), positions.get(i).getY()) && count < creatures.size()) {
                                Creature c = creatures.get(count);
                                c.setPosition(positions.get(i));
                                gameManager.getBattle().getArena().getCreatures().add(c);
         //                       System.out.println("******creature  "+ (count + 1) + "  created");
                                count++;
                            }
                        }
                    }
                    gameManager.getBattle().getPlayerCardsQueue().remove(chosenCardNumber + 3);
                    gameManager.getBattle().getPlayerCardsQueue().add(0, chosenCard);
                    gameManager.getBattle().getPlayerElixirBar().takeExir(chosenCard.getCost());
                    chosenCard = null;
                    chosenCardNumber = 0;
                }
                else if(!checkTowerStatus(x, y))
                    showBorders(x, y);
            }
        }
    }

    private void showBorders(double x, double y) {
        if(!gameManager.getBattle().getArena().getBotDownPrincess().isEliminated() && !gameManager.getBattle().getArena().getBotUpPrincess().isEliminated()){
            setVisibility(rightLine, downRightLine, downLeftLine, upLeftLine, upRightLine, leftDownLine, leftUpLine);
        }else if(!gameManager.getBattle().getArena().getBotDownPrincess().isEliminated() && gameManager.getBattle().getArena().getBotUpPrincess().isEliminated()){
            setVisibility(upRightLine, upMiddleLine, middleLine, leftDownLine, downRightLine, downLeftLine, rightLine);
        }else if(gameManager.getBattle().getArena().getBotDownPrincess().isEliminated() && gameManager.getBattle().getArena().getBotUpPrincess().isEliminated()){
            setVisibility(rightLine, upRightLine, downRightLine, downMiddleLine, upMiddleLine);
        }else if(gameManager.getBattle().getArena().getBotDownPrincess().isEliminated() && !gameManager.getBattle().getArena().getBotUpPrincess().isEliminated()){
            setVisibility(rightLine, upRightLine, upLeftLine, leftUpLine, middleLine, downMiddleLine,  downRightLine);
        }
    }

    private void setVisibility(Line... lines) {
        Runnable task = new Runnable()
        {
            @Override
            public void run()
            {
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for(Line l : lines)
                            l.setVisible(true);
                    }
                });

                try
                {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for(Line l : lines)
                            l.setVisible(false);
                    }
                });
            }
        };
        Thread showThread = new Thread(task);
        showThread.start();
    }


    private boolean checkTowerStatus(double x, double y) {//System.out.println("line 181");
        if(gameManager.getBattle().getArena().getBotDownPrincess().isEliminated() && gameManager.getBattle().getArena().getBotUpPrincess().isEliminated()){
//            System.out.println("line 183");
            return x <= 840;
        }
        else if(!gameManager.getBattle().getArena().getBotDownPrincess().isEliminated() && !gameManager.getBattle().getArena().getBotUpPrincess().isEliminated()) {
//            System.out.println("line 186");
            return x <= 600;
        }
        else if(gameManager.getBattle().getArena().getBotDownPrincess().isEliminated() && !gameManager.getBattle().getArena().getBotUpPrincess().isEliminated()){
//            System.out.println("line 190");
            return x <= 600 || (x <= 840 && y >= 360);
        }
        else {//System.out.println("line 192");
            return x <= 600 || (x <= 840 && y <= 360);
        }
    }
    
    private ArrayList<Point2D> findPositions(double x, double y, int number) 
    {
        ArrayList<Point2D> points = new ArrayList<>();
        double newX = x;
        double newY = y;

        if(number == 1 && isAppropriate(new Point2D(x, y))) {
            points.add(new Point2D(x, y));
            return points;
        }
        //((x <= 300 || (x <= 980 && x >= 680))? i : -1 * i)
        //(y < 360 ? i : i * -1)
        int r = 40;
        while (r <= 60){
            if(isAppropriate(new Point2D(x - r, y - r)))
                points.add(new Point2D(x - r, y - r));
            if(isAppropriate(new Point2D(x + r, y + r)))
                points.add(new Point2D(x + r, y + r));
            if(isAppropriate(new Point2D(x + r, y - r)))
                points.add(new Point2D(x + r, y - r));
            if(isAppropriate(new Point2D(x - r, y + r)))
                points.add(new Point2D(x - r, y + r));

            r += 7;
        }
        Random rand = new Random();
        while (points.size() < number)
        {
            newY = rand.nextInt(80) + y;
            newX = rand.nextInt(80) + x;
//            System.out.println("line 216");
            if(isAppropriate(new Point2D(newX, newY)))
            {
                points.add(new Point2D(newX, newY));
            }
        }
        return points;
    }
    
    public boolean isAppropriate(Point2D point)
    {
        int borderDistance = chosenCard instanceof Building ? 40 : 10;
        double x = point.getX();
        double y = point.getY();

            if(y < (720 - borderDistance) && x < (1280 - borderDistance) && x > borderDistance && y > borderDistance)
            {
                //System.out.println("line 230");
                if(notInCreatures(point))
                {
                    if (chosenCard instanceof Building && (x < 720 && x > 560))
                        return false;
                    if (x < 680 && x > 600) {
                        if ((y >= 100 && y <= 180) || (y >= 540 && y <= 620))
                            return true;
                        else if(chosenCard instanceof Dragon)
                            return true;
                    }
                    else
                        return true;
                }
                else
                {
                    return true;
                }
            }
        return false;
    }
    
    private boolean notInCreatures(Point2D point) 
    {
        Iterator<Creature> it = gameManager.getBattle().getArena().getCreatures().iterator();

        while (it.hasNext()){//System.out.println("249");
            Creature c = it.next();
            //System.out.println("line 248");
            if(!(chosenCard instanceof Spell)){
                if (c.getPosition().distance(point) < 10 && ((!(chosenCard instanceof Dragon) && !(c.getCard() instanceof Dragon)) || ((chosenCard instanceof Dragon) && (c.getCard() instanceof Dragon)))) {
                    return false;
                } else if (c.getCard() instanceof Building && c.getPosition().distance(point) < 40 && !(chosenCard instanceof Dragon)) { //System.out.println("252");
                    return false;
                } else if (c.getCard() instanceof King && c.getPosition().distance(point) < 80 && !(chosenCard instanceof Dragon)) { //System.out.println("255");
                    return false;
                } else if (c.getCard() instanceof Princess && c.getPosition().distance(point) < 60 && !(chosenCard instanceof Dragon)) {// System.out.println("258");
                    return false;
                }
            }
        }// System.out.println("261");
        return true;
    }
    
    /**
     * starts main process of a battle in the game
     */
    private void startTimer() 
    {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() 
        {
            @Override
            public void run() 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        int status = gameManager.battleStep();
                        updateView();
                        
                        if(status != 0)
                        {
                            finishBattle(status);
                        }
                    }
                });
            }
        };
        this.timer.schedule(timerTask, 0, 1000 / GameManager.FPS);
    }
    
    /**
     * updates all battle view based on new situation of model
     */
    private void updateView()
    {
        updateScoreBoardView();
        updateElixirBarView();
        updateCardsQueueView();
        updateBattleTimerView();
        arenaView.updateView(gameManager.getBattle().getArena(), this);
    }
    
    /**
     * updates elixir bar in view based on elixir bar model
     */
    private void updateElixirBarView()
    {
        double elixir = gameManager.getBattle().getPlayerElixirBar().getElixir();
        elixirBarProgressBar.setProgress(elixir / 10);
        elixirBarLabel.setText(Integer.toString((int)Math.floor(elixir)));
    }
    
    /**
     * shows available cards and next card in view based on model 
     */
    private void updateCardsQueueView()
    {
        ArrayList<Card> cards = gameManager.getBattle().getPlayerCardsQueue();
        nextCardImageView.setImage(cards.get(3).getImage(0));
        
        if(gameManager.getBattle().getPlayerElixirBar().getElixir() >= cards.get(4).getCost())
        {
            card1ImageView.setImage(cards.get(4).getImage(0));
        }
        else
        {
            card1ImageView.setImage(cards.get(4).getImage(-1));
        }
        
        if(gameManager.getBattle().getPlayerElixirBar().getElixir() >= cards.get(5).getCost())
        {
            card2ImageView.setImage(cards.get(5).getImage(0));
        }
        else
        {
            card2ImageView.setImage(cards.get(5).getImage(-1));
        }
        
        if(gameManager.getBattle().getPlayerElixirBar().getElixir() >= cards.get(6).getCost())
        {
            card3ImageView.setImage(cards.get(6).getImage(0));
        }
        else
        {
            card3ImageView.setImage(cards.get(6).getImage(-1));
        }
        
        if(gameManager.getBattle().getPlayerElixirBar().getElixir() >= cards.get(7).getCost())
        {
            card4ImageView.setImage(cards.get(7).getImage(0));
        }
        else
        {
            card4ImageView.setImage(cards.get(7).getImage(-1));
        }
        
        if(chosenCardNumber == 1)
        {
            card1Border.setVisible(true);
            card2Border.setVisible(false);
            card3Border.setVisible(false);
            card4Border.setVisible(false);
        }
        else if(chosenCardNumber == 2)
        {
            card1Border.setVisible(false);
            card2Border.setVisible(true);
            card3Border.setVisible(false);
            card4Border.setVisible(false);
        }
        else if(chosenCardNumber == 3)
        {
            card1Border.setVisible(false);
            card2Border.setVisible(false);
            card3Border.setVisible(true);
            card4Border.setVisible(false);
        }
        else if(chosenCardNumber == 4)
        {
            card1Border.setVisible(false);
            card2Border.setVisible(false);
            card3Border.setVisible(false);
            card4Border.setVisible(true);
        }
        else
        {
            card1Border.setVisible(false);
            card2Border.setVisible(false);
            card3Border.setVisible(false);
            card4Border.setVisible(false);
        }
    }
    
    /**
     * updates battle timer in view based on battle timer model
     */
    private void updateBattleTimerView()
    {
        int time = gameManager.getBattle().getBattleTimer().getTime();
        if(time == 60)
        {
            battleTimerLabel.setStyle("-fx-text-fill: red");
        }
        int minutes = time / 60;
        int seconds = time % 60;
        if(seconds < 10)
        {
            battleTimerLabel.setText(minutes + ":0" + seconds);
        }
        else
        {
            battleTimerLabel.setText(minutes + ":" + seconds); 
        }
    }
    
    /**
     * updates view of score board based on model
     */
    private void updateScoreBoardView()
    {
        if(gameManager.getBattle().getScoreBoard().getPlayerStars() == 0)
        {   
            playerScoreImageView.setImage(new Image("resources/battle/0star.png"));
        }
        else if(gameManager.getBattle().getScoreBoard().getPlayerStars() == 1)
        {
            playerScoreImageView.setImage(new Image("resources/battle/1star.png"));
        }
        else if(gameManager.getBattle().getScoreBoard().getPlayerStars() == 2)
        {
            playerScoreImageView.setImage(new Image("resources/battle/2star.png"));
        }
        else if(gameManager.getBattle().getScoreBoard().getPlayerStars() == 3)
        {
            playerScoreImageView.setImage(new Image("resources/battle/3star.png"));
        }
        
        if(gameManager.getBattle().getScoreBoard().getBotStars() == 0)
        {   
            botScoreImageView.setImage(new Image("resources/battle/0star.png"));
        }
        else if(gameManager.getBattle().getScoreBoard().getBotStars() == 1)
        {
            botScoreImageView.setImage(new Image("resources/battle/1star.png"));
        }
        else if(gameManager.getBattle().getScoreBoard().getBotStars() == 2)
        {
            botScoreImageView.setImage(new Image("resources/battle/2star.png"));
        }
        else if(gameManager.getBattle().getScoreBoard().getBotStars() == 3)
        {
            botScoreImageView.setImage(new Image("resources/battle/3star.png"));
        }
    }
    
    /**
     * check the status from model to decide finish the game
     * @param status
     */
    private void finishBattle(int status)
    {
        timer.cancel();
        //the end -->show the winner and get back to the menu
    }
    
    public AnchorPane getArenaPane() 
    {
        return arenaPane;
    }
    
    @FXML
    public void initialize()
    {
        playerUsernameLabel.setText(gameManager.getCurrentPlayer().getUsername() + " : ");
        botUsernameLabel.setText(" : " + gameManager.getBattle().getBot().getUsername());
        
        MediaPlayer mediaPlayerGo = new MediaPlayer(new Media(new File("resources/battle/go.mp3").toURI().toString()));
        mediaPlayerGo.setVolume(0.2);//volume percentage 0 to 1
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run() 
            {
                mediaPlayerGo.play();
                try 
                {
                    Thread.sleep(3000);
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run() 
                    {
                        startTimer();
                    }
                });
                battleBackgroundMusic.setVolume(0.2);//volume percentage 0 to 1
                battleBackgroundMusic.play();
            }
        });
        thread.start();
    }
}
