package modele.dao;

import java.util.ArrayList;

import modele.metiers.LangueEntite;

public interface ILangueEntite {
	public void save(LangueEntite lge);
	public void delete(LangueEntite lge);
	public LangueEntite getLangueEntite(String codeLg, String codeEnt);
	public ArrayList<LangueEntite> allLangueEntite();
	public ArrayList<LangueEntite> allLangueEntite(String codelangue);
}
