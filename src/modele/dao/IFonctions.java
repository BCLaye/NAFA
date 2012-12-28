package modele.dao;

import java.util.ArrayList;

import modele.metiers.Fonctions;

public interface IFonctions {
	public void save(Fonctions ft);
	public void delete(Fonctions ft);
	public Fonctions getFonction(String id);
	public ArrayList<Fonctions> allFonctions();
}
