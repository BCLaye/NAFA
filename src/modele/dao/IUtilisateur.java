package modele.dao;

import java.util.ArrayList;

import modele.metiers.Utilisateur;

public interface IUtilisateur {
	public void save(Utilisateur util);
	public void delete(Utilisateur util);
	public Utilisateur getUtilisateur(String id);
	public ArrayList<Utilisateur> allUtilisateur();
	public ArrayList<Utilisateur> allUtilisateurNoAdminUser();
}
