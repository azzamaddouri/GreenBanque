package application;
import java.sql.Date;
public class CompteSalarier extends Compte {
	public String typecompte;
		public CompteSalarier(String typecompte,String rib, Client c, String solde, Agence a,Date datecreation) throws SoldeNegatif {
		super(rib, c, solde, a, datecreation);
		this.typecompte=typecompte;
	}

		public CompteSalarier() {
			super();
		}
		

	public String getTypecompte() {
			return typecompte;
		}

		public void setTypecompte(String typecompte) {
			this.typecompte = typecompte;
		}

	@Override
	public boolean versement(double montant) {
		solde+=montant;
		this.getA().crediter(montant);
		return true;
	}

	public boolean retraitSalarier(double montant) {
		solde-=montant;
		this.getA().debiter(montant);
		return true;
	}

	@Override
	public boolean retrait(double montant) throws SoldeNegatif, SoldeEnRouge{
		if(this.solde-montant<-((ClientSalarier) this.c).getSalaire()*0.05)
		{
		throw new SoldeEnRouge("Alerte! Vous avez depasse la somme de rouge ");
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
	public boolean virement(double montant, Compte c) throws SoldeNegatif ,SoldeEnRouge{
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
}
