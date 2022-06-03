package application;
import java.sql.Date;
import java.time.LocalDate;
public abstract class Compte {
	 protected Client c ;
	 protected double solde ;
	 private Agence a ;
	 private long rib ;
	 private LocalDate datecreation;
	 public Compte() {
		super();
	} 
	public Compte(String rib, Client c, String solde, Agence a,Date datecreation) throws SoldeNegatif {
		super();
		this.c = c;
		if (Double.parseDouble(solde)<0)
			throw new SoldeNegatif("Attention ! Le solde ne doit pas etre negatif ");
		else 
		    this.solde = Double.parseDouble(solde);
		this.a= a;
		this.rib = Long.parseLong(rib);
		this.datecreation = datecreation.toLocalDate();
		
	}
	


	public Client getC() {
		return c;
	}

	public void setC(Client c) {
		this.c = c;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Agence getA() {
		return a;
	}

	public void setA(Agence a) {
		this.a = a;
	}

	public long getRib() {
		return rib;
	}

	public void setRib(long rib) {
		this.rib = rib;
	}

	public LocalDate getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(LocalDate datecreation) {
		this.datecreation = datecreation;
	}
	 public abstract boolean  versement(double montant);
	 public abstract boolean retrait (double montant) throws SoldeNegatif, SoldeEnRouge;
	 public abstract boolean  virement (double montant,Compte c) throws SoldeNegatif, SoldeEnRouge;
	 
	 public static int compare_solde(Compte c1, Compte c2)
	    {
	    	if(c1.getSolde()> c2.getSolde())
	    		return 1;
	    	else 
	    		if (c1.getSolde()<c2.getSolde())
	    		return -1;
	    	else 
	    		return 0;
	    }
	 public int compare_solde(Compte c)
	    {
	    	if(this.solde> c.getSolde())
	    		return 1;
	    	else 
	    		if (this.solde<c.getSolde())
	    		return -1;
	    	else 
	    		return 0;
	    }
	 public static void tri_comptes(Compte[] listeCompte ,int n) {
		 int i =0;
	    	while(i<n-1) {
	  
	    			if(Compte.compare_solde(listeCompte[i],listeCompte[i+1])==1) {
	    				{
	    					Compte aux;
	    					aux=listeCompte[i];
	    					listeCompte[i]=listeCompte[i+1];
	    					listeCompte[i+1]=aux;
	    				}
	    				i++;
	    		}
	    	 }
}
	    public double soldeTotalParCompte(Client c,Compte [] ListeCompte){
	    	double Somme=0;
	    	for(int i=0;i<ListeCompte.length-1;i++) {	
	    		if (ListeCompte[i].getC()==c) {
	    			Somme += ListeCompte[i].getSolde();
	    	}
	    		
	    	}
			return Somme;
	
}
		@Override
		public String toString() {
			return "Compte [c=" + c + ", solde=" + solde + ", a=" + a + ", rib=" + rib + ", datecreation="
					+ datecreation + "]";
		}
	    
}