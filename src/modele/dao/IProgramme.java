package modele.dao;

import java.util.ArrayList;

import modele.metiers.Programme;

public interface IProgramme {
	public void save(Programme prog);
	public void save(ArrayList<Programme> prog);
	public void delete(Programme prog);
	public Programme getProgramme(String id);
	public ArrayList<Programme> allProgramme();

}
