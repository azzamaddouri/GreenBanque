package application;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
public class CompteEpargne extends Compte {
	   private LocalDate date_dernier_versement;
	   private double montant_cumule;

	public CompteEpargne(String rib,Client c, String solde, Agence a, Date datecreation,Date date_dernier_versement, double montant_cumule) throws SoldeNegatif {
		super(rib, c, solde, a, datecreation);
		this.date_dernier_versement = date_dernier_versement.toLocalDate();
		this.montant_cumule = montant_cumule;
		
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
		public boolean versement(double montant) {
			boolean valide = false;
			LocalDate date_jour = LocalDate.now();
			Period p = Period.between(date_jour,getDate_dernier_versement());
			if(p.getYears()==0 && p.getMonths()==0 ) {
				solde+=montant;
				this.getA().crediter(montant);
				date_dernier_versement=date_jour;
				montant_cumule+=montant;
			}
			else 
				 if(p.getYears()==0 && p.getMonths()==1){
				    if(montant_cumule>=20) {
					valide=true;
					montant_cumule=montant;
					solde+=montant;
					this.getA().crediter(montant);
					date_dernier_versement=date_jour;
					
				     }
				 else {
					System.out.println(" Echec de versement ! ");
					valide=false;
				}
							
			}
			    else {
				System.out.println(" Echec de versement ! ");
			        valide=false;
			}
			return valide;
			
		}
		@Override
		public boolean retrait(double montant) {
			boolean valide=false;
			LocalDate date_jour = LocalDate.now();
			Period p = Period.between( this.getDatecreation(),date_jour);
			if (this.solde-montant>0) {
			   if ( (p.getYears() >= 1) && (this.solde > 2000)) {
				this.solde -= montant;
				this.getA().debiter(montant);
				valide=true;
			}
			     else {
				System.out.println(" Retrait impossible !");
			    valide=false;
			}
			}
			else {
				System.out.println(" Retrait impossible,le montant superieur au solde existant !");
			    valide=false;
			}
			
			
			return valide;
		}
		
		
		@Override
		public boolean virement(double montant, Compte c) {
				System.out.println("On ne peut pas faire un virement a partir d'un compte Epargne");
				return false;
		}


		@Override
		public String toString() {
			return "CompteEpargne [date_dernier_versement=" + date_dernier_versement + ", montant_cumule="
					+ montant_cumule + ", getC()=" + getC() + ", getSolde()=" + getSolde() + ", getA()=" + getA()
					+ ", getRib()=" + getRib() + ", getDatecreation()=" + getDatecreation() + "]";
		}

		
		
}
