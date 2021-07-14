import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

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
        card1ImageView.setImage(cards.get(4).getImage(0));
        card2ImageView.setImage(cards.get(5).getImage(0));
        card3ImageView.setImage(cards.get(6).getImage(0));
        card4ImageView.setImage(cards.get(7).getImage(0));
    }

    /**
     * updates battle timer in view based on battle timer model
     */
    private void updateBattleTimerView()
    {
        int time = gameManager.getBattle().getBattleTimer().getTime();
        int minutes = time / 60;
        int seconds = time % 60;
        battleTimerLabel.setText(minutes + ":" + seconds); 
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
        startTimer();
    }
}
