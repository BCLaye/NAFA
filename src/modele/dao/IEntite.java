package modele.dao;

import java.util.ArrayList;

import modele.metiers.Entite;

public interface IEntite {
	public void save(Entite ent);
	public void delete(Entite ent);
	public Entite getEntite(String id);
	public ArrayList<Entite> allEntite();

}
