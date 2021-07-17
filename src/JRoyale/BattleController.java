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
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
                for(Creature c : creatures)
                {
                    gameManager.getBattle().getArena().getCreatures().add(c);
                    gameManager.getBattle().getPlayerCardsQueue().remove(chosenCardNumber + 3);
                    gameManager.getBattle().getPlayerCardsQueue().add(0, chosenCard);
                    gameManager.getBattle().getPlayerElixirBar().takeExir(chosenCard.getCost());
                } 
            }
            else
            {
                if(GameManager.getInstance().getBattle().getArena().getBotUpPrincess().isEliminated() && GameManager.getInstance().getBattle().getArena().getBotDownPrincess().isEliminated())
                {
                    
                }
                else if(GameManager.getInstance().getBattle().getArena().getBotUpPrincess().isEliminated())
                {

                }
                else if(GameManager.getInstance().getBattle().getArena().getBotDownPrincess().isEliminated())
                {

                }
                else
                {

                }
            }
        }
                
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
                            finishBattle(status);
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

    @FXML
    public void initialize()
    {
        playerUsernameLabel.setText(gameManager.getCurrentPlayer().getUsername() + " : ");
        botUsernameLabel.setText(" : " + gameManager.getBattle().getBot().getUsername());
        
        MediaPlayer mediaPlayerGo = new MediaPlayer(new Media(new File("resources/battle/go.mp3").toURI().toString()));
        mediaPlayerGo.setVolume(0.5);//volume percentage 0 to 1
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
