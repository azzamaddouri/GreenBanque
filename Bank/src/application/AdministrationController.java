package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
	public class AdministrationController implements Initializable {
		 @FXML
		    private TextField chercher;
		@FXML
	    private Button ajouteragence;
	    @FXML
	    private Button listeutilisateurs;
	    @FXML
	    private TableColumn<CompteSalarier, String> rib;
	    @FXML
	    private TableView<CompteSalarier> table2;
	    @FXML
	    private Button tout;
	    @FXML
	    private TableColumn<CompteSalarier, String> typecompte;
		@FXML
	    private TableColumn<Agence, String> creditglobal;

	    @FXML
	    private TableColumn<Agence,String> debitglobal;

    @FXML
    private TableColumn<Agence, String> adresse;

    @FXML
    private TableColumn<Agence, String> gouvernorat;

    @FXML
    private TableColumn<Agence, String> nbclients;

    @FXML
    private TableColumn<Agence, String> nomagence;

    @FXML
    private TableView<Agence> table1;
		 @FXML
		    private TableView<Client> table;
	    @FXML
	    private TableColumn<Client, String> cin;

	    @FXML
	    private TableColumn<Client, String> nom;

	    @FXML
	    private TableColumn<Client, String> prenom;
	    @FXML
	    public ObservableList<Agence> dataa=FXCollections.observableArrayList();
	    public ObservableList<Agence> agences=FXCollections.observableArrayList();
	    public ObservableList<CompteSalarier> datacs=FXCollections.observableArrayList();
	    public ObservableList<Client> data=FXCollections.observableArrayList();
	    public ObservableList<Client> clients=FXCollections.observableArrayList();
	    @FXML
	    private TableColumn<Client, String> typeclient;
	   
	    @FXML
	    void onShowClick(ActionEvent event) {
	    	try {
	    		Connection con= dbConnection.connect();
	    		String sql="SELECT * FROM client ";
	    		PreparedStatement stat = con.prepareStatement(sql);
	    		ResultSet res= stat.executeQuery();
	    		while(res.next()) {
	    			clients.add(new Client(res.getString(1),res.getString(2),res.getString(3),res.getString(4)));
	    		}
	    	}catch(Exception e) {
	    		e.getStackTrace();	
	    	}
	    	cin.setCellValueFactory(new PropertyValueFactory<Client,String>("cin"));
	    	nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
	    	prenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
	    	typeclient.setCellValueFactory(new PropertyValueFactory<Client,String>("typeclient"));
	    	table.setItems(clients);
	    	FilteredList<Client> filteredData = new FilteredList<>(clients,b->true);
	    	chercher.textProperty().addListener((observable,oldValue,newValue) -> {
	    	filteredData.setPredicate(clientSearchModel -> {
	    		if(newValue.isEmpty() || newValue.isBlank()) {
	    			return true;
	    		}
	    		String rechercher=newValue.toLowerCase();
	    		if(clientSearchModel.getNom().toLowerCase().indexOf(rechercher) > -1) {
	    			return true;
	    		}
	    		else if(clientSearchModel.getPrenom().toLowerCase().indexOf(rechercher) > -1) {
	    			return true;
	    		}
	    		else if(clientSearchModel.getCin().toLowerCase().indexOf(rechercher) > -1) {
	    			return true;
	    		}
	    		else if(clientSearchModel.getTypeclient().toLowerCase().indexOf(rechercher) > -1) {
	    			return true;
	    		}
	    		else
	    			return false;
	    		});
	    });
	    	SortedList<Client> sortedData = new SortedList <>(filteredData);
	    	sortedData.comparatorProperty().bind(table.comparatorProperty());
	    	table.setItems(sortedData);
	    }
		  @FXML
		    void OnCompteClick(ActionEvent event) throws IOException {
			  FXMLLoader loader= new FXMLLoader(getClass().getResource("Nouveau Compte.fxml"));
		    	Parent root=(Parent)loader.load();
		    	Stage operation=new Stage();
		    	NouveauCompteController controller= loader.getController();
		    	Scene scene=new Scene(root);
		    	operation.setScene(scene);
		    	operation.show();
		    }
		  @FXML
		    void OnshowAgenceClick(MouseEvent event) {
			  try {
		    		Connection con= dbConnection.connect();
		    		String sql="SELECT * FROM agence";
		    		PreparedStatement stat = con.prepareStatement(sql);
		    		ResultSet res= stat.executeQuery();
		    		while(res.next() ) {
		    	        agences.add(new Agence(res.getString(1),res.getString(2),res.getString(3),res.getString(4),
		    	        		res.getString(5),res.getString(6)));
		    		}
		    		con.close();
		    	}catch(Exception e) {
		    		e.getStackTrace();	
		    	}
		    	nomagence.setCellValueFactory(new PropertyValueFactory<Agence,String>("nom"));
		    	table1.setItems(agences);
		    }
		  
		    @FXML
		    void onClientsClick(ActionEvent event) throws IOException {
		    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Gestion des Clients.fxml"));
		    	Parent root=(Parent)loader.load();
		    	Stage operation=new Stage();
		    	GestionClientController controller= loader.getController();
		    	Scene scene=new Scene(root);
		    	operation.setScene(scene);
		    	operation.show();
		    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		    }
		    
		    @FXML
		    void onClientClick(ActionEvent event) throws IOException {
		    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Nouveau Client.fxml"));
		    	Parent root=(Parent)loader.load();
		    	Stage operation=new Stage();
		    	NouveauClientController controller= loader.getController();
		    	Scene scene=new Scene(root);
		    	operation.setScene(scene);
		    	operation.show();
		    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

		    }

		    @FXML
		    void onDeconnexionClick(ActionEvent event) throws IOException {
		    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Authentification.fxml"));
		    	Parent root=(Parent)loader.load();
		    	Stage operation=new Stage();
		    	AuthentificationController controller= loader.getController();
		    	Scene scene=new Scene(root);
		    	operation.setScene(scene);
		    	operation.show();
		    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		    }

		    @FXML
		    void onListeComptesClick(ActionEvent event) throws IOException {
		    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Gestion des Comptes.fxml"));
		    	Parent root=(Parent)loader.load();
		    	Stage operation=new Stage();
		    	GestionCompteController controller= loader.getController();
		    	Scene scene=new Scene(root);
		    	operation.setScene(scene);
		    	operation.show();
		    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

		    }

	    @FXML
	    void onOpClick(ActionEvent event) throws IOException {
	    	FXMLLoader loader= new FXMLLoader(getClass().getResource("OperationsBancaires.fxml"));
	    	Parent root=(Parent)loader.load();
	    	Stage operation=new Stage();
	    	OperationsBancairesController controller= loader.getController();
	    	Scene scene=new Scene(root);
	    	operation.setScene(scene);
	    	operation.show();

	    }
	    @FXML
	    void OnAddAgenceClick(ActionEvent event){
	    }
	   
	    @FXML
	    void OnshowTypeComptesClick(MouseEvent event) {
	    	try {
	    	Connection con= dbConnection.connect();
    		String sqla="SELECT * FROM agence";
    		String sqlc="SELECT * FROM client ";
    		String sqlco="SELECT * FROM compte ";
    		PreparedStatement stata = con.prepareStatement(sqla);
    		ResultSet resa= stata.executeQuery();
    		PreparedStatement statc = con.prepareStatement(sqlc);
    		ResultSet resc= statc.executeQuery();
    		PreparedStatement statco = con.prepareStatement(sqlco);
    		ResultSet resco= statco.executeQuery();
    		while(resa.next() ) {
    	        dataa.add(new Agence(resa.getString(1),resa.getString(2),resa.getString(3),resa.getString(4),
    	        		resa.getString(5),resa.getString(6)));
    		}
    		while(resc.next() ) {
    			data.add(new Client(resc.getString(1),resc.getString(2),resc.getString(3),resc.getString(4)));
    		}
    		        while(resco.next()) {
    		        		datacs.add(new CompteSalarier(resco.getString(1),resco.getString(2),data.get(0),resco.getString(4),dataa.get(0),resco.getDate(6)));
    		        	}
    			}catch(Exception e) {
    	    		e.getStackTrace();	
    	    	}
    	typecompte.setCellValueFactory(new PropertyValueFactory<CompteSalarier,String>("typecompte"));
    	table2.setItems(datacs);
	    }

	    @FXML
	    void onUtilisateursClick(ActionEvent event) throws IOException {
	    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Gestion des Utilisateurs.fxml"));
	    	Parent root=(Parent)loader.load();
	    	Stage operation=new Stage();
	    	GestionUtilisateurController controller= loader.getController();
	    	Scene scene=new Scene(root);
	    	operation.setScene(scene);
	    	operation.show();
	    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	
		}

	}
