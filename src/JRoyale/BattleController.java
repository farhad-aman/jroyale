import javafx.application.Platform;
import javafx.fxml.FXML;

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

    private ArenaView battleView = new ArenaView();
    /**
     * starts main process of a battle in the game
     */
    private void startTimer() 
    {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() 
        {
            @Override
            public void run() {
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
