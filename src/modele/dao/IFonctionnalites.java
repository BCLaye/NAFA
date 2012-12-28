package modele.dao;

import java.util.ArrayList;

import modele.metiers.Fonctionnalites;

public interface IFonctionnalites {
	public void save(Fonctionnalites fct);
	public void delete(Fonctionnalites fct);
	public Fonctionnalites getFonctionnalite(String id);
	public ArrayList<Fonctionnalites> allFonctionnalites();
}
