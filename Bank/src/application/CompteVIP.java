package application;
import java.sql.Date;
public class CompteVIP extends Compte{
	
	public CompteVIP() {
		super();
	}

	public CompteVIP(String rib,Client c, String solde, Agence a, Date datecreation) throws SoldeNegatif {
		super(rib, c, solde, a, datecreation);
	}

	@Override
	public boolean versement(double montant) {
		solde+=montant;
		this.getA().debiter(montant);
		return true;
	}

	@Override
public boolean retrait(double montant) throws SoldeNegatif,SoldeEnRouge{
		if (solde-montant<-0.005*((ClientVIP)this.c).getChiffreAffaires() ) {
			throw new SoldeEnRouge("Alerte! Vous avez depass� la somme de rouge");
		}
		else if(solde-montant<0) {
			solde-=montant;
			this.getA().debiter(montant);
		throw new SoldeNegatif("Alerte ! Vous avez entr� dans la zone rouge !");

		}
		solde-=montant;
		this.getA().debiter(montant);
		return true;
	}

	@Override
	public boolean virement(double montant, Compte c) {
		try {
			if(this.retrait(montant))
			{

				c.versement(montant);
				return true;
			}
		} catch (SoldeNegatif e) {
			System.out.println(e.getMessage());
			c.versement(montant);
			return true;
		} catch (SoldeEnRouge e) {
		System.out.println(e.getMessage());
	   System.exit(0);
		}
		return false;
	}
    public boolean emprunter(double montant,int periodicite) {
    	solde+=0.3*((ClientVIP) c).getChiffreAffaires();
    	return true;
    	
    }
	@Override
	public String toString() {
		return "CompteVIP [c=" + c + ", solde=" + solde + ", getC()=" + getC() + ", getSolde()=" + getSolde()
				+ ", getA()=" + getA() + ", getRib()=" + getRib() + ", getDatecreation()=" + getDatecreation() + "]";
	}
	
}