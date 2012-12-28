package modele.dao;

import java.util.ArrayList;
import modele.metiers.Preferences;

public interface IPreferences {
	public void save(ArrayList<Preferences> prefs);
	public void delete(Preferences pref);
	public Preferences getPreferences();
	public Preferences getPreferences(String codeUtil);
	public Preferences getPreferences(String theme, String couleur);
	public ArrayList<Preferences> allPreferences();

}
