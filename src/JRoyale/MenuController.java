import java.io.File;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuController 
{
    private GameManager gameManager = GameManager.getInstance();

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ImageView changePasswordButton;

    @FXML
    private Label usernameValue;

    @FXML
    private Label xpValue;

    @FXML
    private ImageView levelImageView;

    @FXML
    private ImageView archerImageView;

    @FXML
    private ImageView barbariansImageView;

    @FXML
    private ImageView wizardImageView;

    @FXML
    private ImageView dragonImageView;

    @FXML
    private ImageView giantImageView;

    @FXML
    private ImageView pekkaImageView;

    @FXML
    private ImageView cannonImageView;

    @FXML
    private ImageView valkyrieImageView;

    @FXML
    private ImageView fireballImageView;

    @FXML
    private ImageView arrowsImageView;

    @FXML
    private ImageView infernoImageView;

    @FXML
    private ImageView rageImageView;

    @FXML
    private ImageView card1;

    @FXML
    private ImageView card5;

    @FXML
    private ImageView card2;

    @FXML
    private ImageView card6;

    @FXML
    private ImageView card3;

    @FXML
    private ImageView card7;

    @FXML
    private ImageView card4;

    @FXML
    private ImageView card8;

    @FXML
    private CheckBox archerCheckBox;

    @FXML
    private CheckBox barbariansCheckBox;

    @FXML
    private CheckBox dragonCheckBox;

    @FXML
    private CheckBox wizardCheckBox;

    @FXML
    private CheckBox cannonCheckBox;

    @FXML
    private CheckBox valkyrieCheckBox;

    @FXML
    private CheckBox pekkaCheckBox;

    @FXML
    private CheckBox giantCheckBox;

    @FXML
    private CheckBox fireballCheckBox;

    @FXML
    private CheckBox arrowsCheckBox;

    @FXML
    private CheckBox rageCheckBox;

    @FXML
    private CheckBox infernoCheckBox;

    @FXML
    private ImageView saveDeckButton;

    @FXML
    private TableView<?> historyTable;

    @FXML
    private TableColumn<?, ?> botDifficultyColumn;

    @FXML
    private TableColumn<?, ?> playerStarsColumn;

    @FXML
    private TableColumn<?, ?> botStarsColumn;

    @FXML
    private TableColumn<?, ?> winnerColumn;

    @FXML
    private ImageView easyButton;

    @FXML
    private ImageView normalButton;

    @FXML
    private ImageView hardButton;

    @FXML
    private Label oldPasswordLabel;

    @FXML
    private Label newPasswordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Label saveDeckLabel;

    @FXML
    void changePasswordButtonPressed(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    changePasswordButton.setImage(new Image("resources/menu/changePasswordButtonPressed.png"));
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/menu/click1.mp3").toURI().toString()));
                    mediaPlayer.setVolume(0.5);//volume percentage 0 to 1
                    mediaPlayer.play();
                }
            });    
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
        }
        
        int status = gameManager.getCurrentPlayer().setPassword(oldPasswordField.getText(), newPasswordField.getText(), confirmPasswordField.getText());
        if(status == 0)
        {
            oldPasswordLabel.setText("Old Password Is Invalid");
            try 
            {
                Thread.sleep(5000);   
            } 
            catch (Exception e) 
            {
                //TODO: handle exception
            }
            oldPasswordLabel.setText("");
        }
        else if(status == 1)
        {
            confirmPasswordLabel.setText("New Password Is Not Equals To Confirm Password");
            try 
            {
                Thread.sleep(5000);   
            } 
            catch (Exception e) 
            {
                //TODO: handle exception
            }
            confirmPasswordLabel.setText("");
        }
        else if(status == 2)
        {
            newPasswordLabel.setText("New Password Lenght Is Not Between 6 & 32");
            try 
            {
                Thread.sleep(5000);   
            } 
            catch (Exception e) 
            {
                //TODO: handle exception
            }
            newPasswordLabel.setText("");
        }
        else if(status == 3)
        {
            oldPasswordLabel.setText("Password Successfully Changed");
            oldPasswordField.setText("");
            newPasswordField.setText("");
            confirmPasswordField.setText("");
            try 
            {
                Thread.sleep(5000);   
            } 
            catch (Exception e) 
            {
                //TODO: handle exception
            }
            oldPasswordLabel.setText("");
        }
    }

    @FXML
    void changePasswordButtonReleased(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    changePasswordButton.setImage(new Image("resources/menu/changePasswordButton.png"));
                }
            });    
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
        }
    }

    @FXML
    void confirmPasswordFieldPressed(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/menu/click1.mp3").toURI().toString()));
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
    void newPasswordFieldPressed(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/menu/click1.mp3").toURI().toString()));
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
    void oldPasswordFieldPressed(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/menu/click1.mp3").toURI().toString()));
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
    void easyButtonPressed(MouseEvent event) 
    {
        /*gameManager.createBattle(1);
        openBattle();*/
    }
    
    @FXML
    void normalButtonPressed(MouseEvent event) 
    {
        /*gameManager.createBattle(2);
        openBattle();*/
    }

    @FXML
    void hardButtonPressed(MouseEvent event) 
    {
        /*gameManager.createBattle(3);
        openBattle();*/
    }

    @FXML
    void saveDeckButtonPressed(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    saveDeckButton.setImage(new Image("resources/menu/saveDeckButtonPressed.png"));
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/menu/click1.mp3").toURI().toString()));
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
    void saveDeckButtonReleased(MouseEvent event) 
    {
        try 
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run() 
                {
                    saveDeckButton.setImage(new Image("resources/menu/saveDeckButton.png"));
                }
            });    
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
        }
    }

    private void openBattle()
    {

    }

    @FXML
    void initialize()
    {
        usernameValue.setText(gameManager.getCurrentPlayer().getUsername());
        xpValue.setText(Integer.toString(gameManager.getCurrentPlayer().getXp()));
        if(gameManager.getCurrentPlayer().getLevel() == 1)
        {
            levelImageView.setImage(new Image("resources/menu/level1.png"));
        }
        else if(gameManager.getCurrentPlayer().getLevel() == 2)
        {
            levelImageView.setImage(new Image("resources/menu/level2.png"));
        }
        else if(gameManager.getCurrentPlayer().getLevel() == 3)
        {
            levelImageView.setImage(new Image("resources/menu/level3.png"));
        }
        else if(gameManager.getCurrentPlayer().getLevel() == 4)
        {
            levelImageView.setImage(new Image("resources/menu/level4.png"));
        }
        else if(gameManager.getCurrentPlayer().getLevel() == 5)
        {
            levelImageView.setImage(new Image("resources/menu/level5.png"));
        }
        ArrayList<Card> deck = new ArrayList<>();
        for(Card c : gameManager.getCurrentPlayer().getDeck().getCards())
        {
            deck.add(c);
            c.loadImages();
        }
        card1.setImage(deck.get(0).getImage(0));
        card2.setImage(deck.get(1).getImage(0));
        card3.setImage(deck.get(2).getImage(0));
        card4.setImage(deck.get(3).getImage(0));
        card5.setImage(deck.get(4).getImage(0));
        card6.setImage(deck.get(5).getImage(0));
        card7.setImage(deck.get(6).getImage(0));
        card8.setImage(deck.get(7).getImage(0));
    }
}
