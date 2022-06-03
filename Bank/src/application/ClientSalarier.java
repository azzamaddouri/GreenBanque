package application;
public class ClientSalarier extends Client{
	private double salaire ;
	public ClientSalarier(String nom, String prenom, String cin,String typeclient ,String salaire) {
		super(nom, prenom, cin,typeclient);
		this.salaire=Double.parseDouble(salaire);
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	

}
