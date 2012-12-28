package modele.dao;

import java.util.ArrayList;

import modele.metiers.UtilisateurProfil;

public interface IUtilisateurProfil {
	public void save(UtilisateurProfil utilProf);
	public void save(ArrayList<UtilisateurProfil> utilProf);
	public void delete(UtilisateurProfil utilProf);
	public UtilisateurProfil getUtilisateurProfil(String codeUtil, String codeProf);
	public ArrayList<UtilisateurProfil> allUtilisateurProfil();

}
