package application;

import java.sql.Date;
import java.time.LocalDate;

public class CompteEpargneBase extends CompteBase {
	private LocalDate date_dernier_versement;
	   private double montant_cumule;
	public CompteEpargneBase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompteEpargneBase(String rib, String solde, Date datecreation, Date date_dernier_versement,double montant_cumule) throws SoldeNegatif {
		super(rib, solde, datecreation);
		this.date_dernier_versement =  date_dernier_versement.toLocalDate();
		this.montant_cumule = montant_cumule;
	}

	@Override
	public boolean versement(double montant) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retrait(double montant) throws SoldeNegatif, SoldeEnRouge {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean virement(double montant, Compte c) throws SoldeNegatif, SoldeEnRouge {
		// TODO Auto-generated method stub
		return false;
	}
	
	public LocalDate getDate_dernier_versement() {
		return date_dernier_versement;
	}
	public void setDate_dernier_versement(LocalDate date_dernier_versement) {
		this.date_dernier_versement = date_dernier_versement;
	}
	public double getMontant_cumule() {
		return montant_cumule;
	}
	public void setMontant_cumule(double montant_cumule) {
		this.montant_cumule = montant_cumule;
	}
	@Override
	public String toString() {
		return "CompteEpargneBase [date_dernier_versement=" + date_dernier_versement + ", montant_cumule="
				+ montant_cumule + "]";
	}
	   

}
