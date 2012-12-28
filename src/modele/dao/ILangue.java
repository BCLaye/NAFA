package modele.dao;

import java.util.ArrayList;

import modele.metiers.Langue;

public interface ILangue {
	public void save(Langue lg);
	public void delete(Langue lg);
	public Langue getLangue(String code);
	public ArrayList<Langue> allLangue();
}
