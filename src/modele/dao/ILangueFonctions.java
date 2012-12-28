package modele.dao;

import java.util.ArrayList;

import modele.metiers.LangueFonctions;

public interface ILangueFonctions {
	public void save(LangueFonctions lf);
	public void delete(LangueFonctions lf);
	public LangueFonctions getLangueFonctions(String lg, String fct);
	public ArrayList<LangueFonctions> allLangueFonctions();
	public ArrayList<LangueFonctions> allLangueFonctions(String codeLangue);
}
