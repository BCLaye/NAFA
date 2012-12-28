package modele.dao;

import java.util.ArrayList;
import java.util.Collection;

import modele.metiers.Fonctionnalites;
import modele.metiers.Profil;
import modele.metiers.ValidationFonctionnalites;

public interface IValidationFonctionnalites {
	public void save(ValidationFonctionnalites validfct);
	public void save(Collection<ValidationFonctionnalites> validfct);
	public void delete(ValidationFonctionnalites valifct);
	public void delete(Collection<ValidationFonctionnalites>  valifct);
	public ValidationFonctionnalites getValidationFonctionnalites(String codeFct, String codeProf, String codeProfVal);
	public ArrayList<ValidationFonctionnalites> allValidationFonctionnalites();
	
	public ArrayList<Profil> allProfil();
	public ArrayList<Fonctionnalites> allFonctionnalites( String profil);
	public ArrayList<Profil> allProfilValid( String profil, String fct);
	public ArrayList<ValidationFonctionnalites> allValidation(String profil, String fctionnalite);
	// la liste des validation dont le rang est inférieur à randInf et qui sont tjrs invalides
	public Boolean testValidation(String profil, String fctionnalite, int randInf);

}
