package application;
public class Client {
	private String nom ;
	private String prenom;
	private String cin;
	protected String typeclient;
	public Client(String cin,String nom,String prenom,String typeclient) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.setTypeclient(typeclient);
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + "]";
	}
	public String getTypeclient() {
		return typeclient;
	}
	public void setTypeclient(String typeclient) {
		this.typeclient = typeclient;
	}

}