import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

