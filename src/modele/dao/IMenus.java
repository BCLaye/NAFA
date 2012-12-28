package modele.dao;

import java.util.ArrayList;
import modele.metiers.Menus;

public interface IMenus{
	
	public void save(Menus menus);
	public void delete(Menus menus);
	public Menus getMenus(String menus);
	public ArrayList<Menus> allMenus();
	//les menus du profil  ne snt pas rattaché o profil et à aucun otre menus
	public ArrayList<Menus> allMenusLibres(String prof);

}
