import javafx.fxml.FXML;
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
    private TextField signUpUsernameTextField;

    @FXML
    private PasswordField signUpPasswordTextField;

    @FXML
    private ImageView signUpButton;

    @FXML
    private PasswordField signUpConfirmTextField;

    @FXML
    void loginButtonClicked(MouseEvent event) 
    {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("<path to music file>").toURI().toString()));
        mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
        mediaPlayer.play();
    }

    @FXML
    void loginButtonPressed(MouseEvent event) 
    {
        loginButton.setImage(new Image("resources/login/loginButtonPressed.png"));
    }

    @FXML
    void loginButtonReleased(MouseEvent event) 
    {
        loginButton.setImage(new Image("resources/login/loginButton.png"));
    }

    @FXML
    void signUpButtonClicked(MouseEvent event) 
    {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("<path to music file>").toURI().toString()));
        mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
        mediaPlayer.play();
    }

    @FXML
    void signUpButtonPressed(MouseEvent event) 
    {
        signUpButton.setImage(new Image("resources/login/signUpButtonPressed.png"));
    }

    @FXML
    void signUpButtonReleased(MouseEvent event) 
    {
        signUpButton.setImage(new Image("resources/login/signUpButton.png"));
    }

    @FXML
    void initialize()
    {
        
    }

}

