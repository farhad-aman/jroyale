import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.sql.*;

public class LoginController 
{
    @FXML
    private TextField loginUsernameTextField;

    @FXML
    private PasswordField loginPasswordTextField;

    @FXML
    private ImageView loginButton;

    @FXML
    private Label loginUsernameLabel;

    @FXML
    private Label loginPasswordLabel;

    @FXML
    private TextField signUpUsernameTextField;

    @FXML
    private PasswordField signUpPasswordTextField;

    @FXML
    private PasswordField signUpConfirmTextField;

    @FXML
    private ImageView signUpButton;

    @FXML
    private Label signUpUsernameLabel;

    @FXML
    private Label signUpPasswordLabel;

    @FXML
    private Label signUpConfirmLabel;


//    @FXML
//    void loginButtonClicked(MouseEvent event)
//    {
//        logIn();
//    }

    @FXML
    void loginButtonPressed(MouseEvent event) 
    {
//        try
//        {
//            Platform.runLater(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/login/click1.mp3").toURI().toString()));
//                    mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
//                    mediaPlayer.play();
//                }
//            });
//            loginButton.setImage(new Image("resources/login/loginButtonPressed.png"));
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            //TODO: handle exception
//        }
    }

    @FXML
    void loginButtonReleased(MouseEvent event) 
    {
//        try
//        {
//            loginButton.setImage(new Image("resources/login/loginButton.png"));
//        }
//        catch (Exception e)
//        {
//            //TODO: handle exception
//        }
        logIn();
    }
    /**
     * asks GameManager.getInstance().login() for login and based on the given status interacts with the user//-1->username does not exist//0->password does not match//1->information is accurate
     * */
    private void logIn(){
        int status = GameManager.getInstance().login(loginUsernameTextField.getText(), loginPasswordTextField.getText());

        if(status == -1){
            try {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        loginUsernameLabel.setText("Wrong username, try again");
//                        try {
//                            Thread.sleep(4000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        loginUsernameLabel.setText("");
//                    }
//                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (status == 0){
            try {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        loginUsernameLabel.setText("Wrong password, try again");
//                        try {
//                            Thread.sleep(4000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        loginUsernameLabel.setText("");
//                    }
//                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else
            openMenu();
    }

//    @FXML
//    void signUpButtonClicked(MouseEvent event)
//    {
//        System.out.println("loginController line 144 started");
//        signUp();
//    }

    @FXML
    void signUpButtonPressed(MouseEvent event) 
    {
//        try
//        {
//            Platform.runLater(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/login/click1.mp3").toURI().toString()));
//                    mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
//                    mediaPlayer.play();
//                }
//
//            });
//            signUpButton.setImage(new Image("resources/login/signUpButtonPressed.png"));
//        }
//        catch (Exception e)
//        {
//            //TODO: handle exception
//        }
    }

    @FXML
    void signUpButtonReleased(MouseEvent event) 
    {
//        try
//        {
//            signUpButton.setImage(new Image("resources/login/signUpButton.png"));
//        }
//        catch (Exception e)
//        {
//            //TODO: handle exception
//        }
        signUp();
    }
    /**
     * asks GameManager.getInstance().signUp() for sign up and based on the given status interacts with the user//status->-2 : passwords are not the same//-1->information is not accurate//0->username is occupied//1-> player is added
     * */
    private void signUp() {
        int status = GameManager.getInstance().signUp(signUpUsernameTextField.getText(), signUpPasswordTextField.getText(), signUpConfirmTextField.getText());

        if(status == -2){
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    signUpConfirmLabel.setText("Wrong password, try again");
//                    try {
//                        Thread.sleep(4000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    signUpConfirmLabel.setText("");
//                }
//            });
        }
        else if(status == -1){
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    signUpUsernameLabel.setText("Inappropriate username, try again");
//                    signUpPasswordLabel.setText("Inappropriate password, try again");
//                    try {
//                        Thread.sleep(4000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    signUpUsernameLabel.setText("");
//                    signUpPasswordLabel.setText("");
//                }
//            });
        }
        else if(status == 0){
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    signUpUsernameLabel.setText("Username already exists, try again");
//                    try {
//                        Thread.sleep(4000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    signUpUsernameLabel.setText("");
//                }
//            });
        }
        else
            openMenu();
    }

    @FXML
    void textFieldPressed(MouseEvent event) 
    {
//        try
//        {
//            Platform.runLater(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/login/click1.mp3").toURI().toString()));
//                    mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
//                    mediaPlayer.play();
//                }
//
//            });
//        }
//        catch (Exception e)
//        {
//            //TODO: handle exception
//        }
    }

    private void openMenu() {
        System.out.println("Menu opened!?!?!?!?!");
    }

    @FXML
    void initialize()
    {
        
    }

}

