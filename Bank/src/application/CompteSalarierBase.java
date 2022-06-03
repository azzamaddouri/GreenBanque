package application;

import java.sql.Date;

public class CompteSalarierBase extends CompteBase {

	public CompteSalarierBase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteSalarierBase(String rib, String solde, Date datecreation) throws SoldeNegatif {
		super(rib, solde, datecreation);
		// TODO Auto-generated constructor stub
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
	

}
