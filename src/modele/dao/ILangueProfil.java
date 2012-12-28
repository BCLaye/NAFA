package modele.dao;

import java.util.ArrayList;

import modele.metiers.LangueProfil;

public interface ILangueProfil {
	public void save(LangueProfil lp);
	public void delete(LangueProfil lp);
	public LangueProfil getLangueProfil(String lg, String pr);
	public ArrayList<LangueProfil> allLangueProfil();
	public ArrayList<LangueProfil> allLangueProfil(String codeLangue);
}
