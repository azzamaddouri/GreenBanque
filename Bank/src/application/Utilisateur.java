package application;

public class Utilisateur {
	private String prenom;
	private String motdepasse;
	public Utilisateur(String prenom, String motdepasse) {
		super();
		this.setPrenom(prenom);
		this.setMotdepasse(motdepasse);
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

}
