package application;

public class ClientVIP extends Client {
	private String nomEntreprise;
	private double chiffreAffaires;
	private int nbEmployes;
	public ClientVIP(String nom, String prenom, String cin,String typeclient ,String nomEntreprise, String chiffreAffaires, String nbEmployes) {
		super(nom, prenom, cin,typeclient);
		this.nomEntreprise = nomEntreprise;
		this.chiffreAffaires = Double.parseDouble(chiffreAffaires);
		this.nbEmployes = Integer.parseInt(nbEmployes);
	}
	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}
	public double getChiffreAffaires() {
		return chiffreAffaires;
	}
	public void setChiffreAffaires(double chiffreAffaires) {
		this.chiffreAffaires = chiffreAffaires;
	}
	public int getNbEmployes() {
		return nbEmployes;
	}
	public void setNbEmployes(int nbEmployes) {
		this.nbEmployes = nbEmployes;
	}
	@Override
	public String toString() {
		return "ClientVIP [nomEntreprise=" + nomEntreprise + ", chiffreAffaires=" + chiffreAffaires + ", nbEmployes="
				+ nbEmployes + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getCin()=" + getCin()
				+ "]";
	}
	
	
	
		
}
