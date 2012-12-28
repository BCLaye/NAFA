package modele.dao;

import java.util.ArrayList;

import modele.metiers.Profil;

public interface IProfil {
	public void save(Profil prof);
	public void delete(Profil prof);
	public Profil getProfil(String cod);
	public ArrayList<Profil> allProfil();
}
