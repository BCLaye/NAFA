package modele.dao;

import modele.metiers.LangueMenus;

public interface IlangueMenus {
	
	public void save(LangueMenus langmenus);
	public void delete(LangueMenus langmenus);
	public LangueMenus getLangueMenus(String langue, String menus);
}
