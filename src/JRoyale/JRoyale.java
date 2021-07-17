import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JRoyale extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        scene.setCursor(new ImageCursor(new Image("resources/cursor2.png")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("JRoyale");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event) 
            {
                System.exit(0);
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);    
    }
}