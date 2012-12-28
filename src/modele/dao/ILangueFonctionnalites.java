package modele.dao;

import java.util.ArrayList;

import modele.metiers.LangueFonctionnalites;

public interface ILangueFonctionnalites {
	public void save(LangueFonctionnalites lgf);
	public void delete(LangueFonctionnalites lgf);
	public void delete(String fct);
	public LangueFonctionnalites getLangueFonctionnalite(String ld, String fct);
	public ArrayList<LangueFonctionnalites> allLangueFonctionnalite();
	public ArrayList<LangueFonctionnalites> allLangueFonctionnalite(String codeLangue);
}
