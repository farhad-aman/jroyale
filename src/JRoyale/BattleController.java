import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class BattleController {
    Timer timer;
    private GameManager gm = GameManager.getInstance();
    Arena arena = new Arena();
    int FPS = 40;//frame per second

    public void initialize(){
        startTimer();
    }

    private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        move();
                    }
                });
            }
        };
        this.timer.schedule(timerTask, 0, 1000 / FPS);
    }

    private void move() {
        gm.stepAll();
        arena.updateView(gm.getBattle());

        int status = gm.getStatus();//-2->towers bonus must be calculated//-1->bot won//0->incomplete//1->player won

        if(status == 1){
            //player won
        }
        else if(status == -1){
            //bot won
        }
        else if (status == -2){
            //clarifies the winner
        }
    }

    private void finishGame(int status){
        timer.cancel();
        //the end -->show the winner
    }
}
