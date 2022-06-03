package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class AuthentificationController implements Initializable {
	Connection con;
	PreparedStatement stat=null;
	ResultSet res=null;
	@FXML
	private AnchorPane ScenePane;
	@FXML
	private Button ex;
	@FXML
	  private TextField txtf;
	@FXML
	  private PasswordField textf;
    @FXML
    public void onAdminClick(ActionEvent event) throws SQLException{
    	con=dbConnection.connect();
    	String prenom = txtf.getText();
    	String motdepasse = textf.getText();
    	if(prenom.isEmpty()==false && motdepasse.isEmpty()==false ) {
    		String sql="SELECT * FROM utilisateur where prenom = '" +prenom+ "' AND motdepasse = '" +motdepasse+"'";
    		try {
    			stat=con.prepareStatement(sql); 
    			res=stat.executeQuery();
    			if (res.next()) {
    				FXMLLoader loader= new FXMLLoader(getClass().getResource("Administration.fxml"));
    				Parent root=(Parent)loader.load();
    				Stage operation=new Stage();
    				AdministrationController controller= loader.getController();
    				Scene scene=new Scene(root);
    				operation.setScene(scene);
    				operation.show();
    				((Stage)(((Button)event.getSource()).getScene().getWindow())).close();	
    			}
    			else {
    				Alert alerte = new Alert(AlertType.NONE,"Cet utilisateur n'existe pas !",ButtonType.OK);
                           alerte.show();
    			}
    				
    		}catch(Exception e) {
    			e.getStackTrace();
    		}
    	}
    	else{
			Alert alerte = new Alert(AlertType.NONE,"Les champs doivent être remplis !",ButtonType.OK);
            alerte.show();
	}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}	
}
