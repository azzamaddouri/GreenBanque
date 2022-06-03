package application;
	public class Agence {
		private String nom,adresse,gouvernorat;
		private int nbclients;
		public double debit_globale_agence,credit_globale_agence;		
	    public Agence(String nom, String adresse, String gouvernorat, String nbclients, String debit_globale_agence,
				String credit_globale_agence) {
			super();
			this.nom = nom;
			this.adresse = adresse;
			this.gouvernorat = gouvernorat;
			this.nbclients = Integer.parseInt(nbclients);
			this.debit_globale_agence = Double.parseDouble(debit_globale_agence);
			this.credit_globale_agence =  Double.parseDouble(credit_globale_agence);
		}
		public Agence() {
			super();
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public int getNbclient() {
			return nbclients;
		}

		public void setNbclient(int nbclient) {
			this.nbclients = nbclient;
		}


		public double getDebit_globale_agence() {
			return debit_globale_agence;
		}

		public void setDebit_globale_agence(double debit_globale_agence) {
			this.debit_globale_agence = debit_globale_agence;
		}

		public double getCredit_globale_agence() {
			return credit_globale_agence;
		}

		public void setCredit_globale_agence(double credit_globale_agence) {
			this.credit_globale_agence = credit_globale_agence;
		}
		public String getGouvernorat() {
			return gouvernorat;
		}

		public void setGouvernorat(String gouvernorat) {
			this.gouvernorat = gouvernorat;
		}   

		boolean crediter(double montant)
	    {
	    	credit_globale_agence+=montant;
	    	return true;
	    }
	    boolean debiter(double montant)
	    {
	    	debit_globale_agence+=montant;
	    	return true;
	    }
		@Override
		public String toString() {
			return "Agence [nom=" + nom + ", adresse=" + adresse + ", gouvernorat=" + gouvernorat + ", nbclient="
					+ nbclients + ", debit_globale_agence=" + debit_globale_agence + ", credit_globale_agence="
					+ credit_globale_agence + "]";
		}

		
}
