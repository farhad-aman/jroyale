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


    @FXML
    void loginButtonClicked(MouseEvent event) 
    {
        
    }

    @FXML
    void loginButtonPressed(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/login/click1.mp3").toURI().toString()));
                    mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
                    mediaPlayer.play();
                }
                
            });
            loginButton.setImage(new Image("resources/login/loginButtonPressed.png"));
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
        }
    }

    @FXML
    void loginButtonReleased(MouseEvent event) 
    {
        try 
        {
            loginButton.setImage(new Image("resources/login/loginButton.png"));
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
        }
    }

    @FXML
    void signUpButtonClicked(MouseEvent event) 
    {
        
    }

    @FXML
    void signUpButtonPressed(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/login/click1.mp3").toURI().toString()));
                    mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
                    mediaPlayer.play();
                }
                
            });
            signUpButton.setImage(new Image("resources/login/signUpButtonPressed.png"));
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
        }
    }

    @FXML
    void signUpButtonReleased(MouseEvent event) 
    {
        try 
        {
            signUpButton.setImage(new Image("resources/login/signUpButton.png"));
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
        }
    }

    @FXML
    void textFieldPressed(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/login/click1.mp3").toURI().toString()));
                    mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
                    mediaPlayer.play();
                }
                
            });
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
        }
    }

    @FXML
    void initialize()
    {
        
    }

}

