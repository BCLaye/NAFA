package modele.dao;

import java.util.ArrayList;
import java.util.Collection;

import modele.metiers.Fonctionnalites;
import modele.metiers.FonctionnalitesProfil;
import modele.metiers.Profil;

public interface IFonctionnalitesProfil {
	public void save(FonctionnalitesProfil fctPr);
	public void save(Collection<FonctionnalitesProfil> listFctProfil);
	public void delete(FonctionnalitesProfil fctPr);
	public void delete(Collection<FonctionnalitesProfil> listFctProfil);
	public FonctionnalitesProfil getFonctionnalitesProfil(String idF, String idP);
	public ArrayList<Profil> AllProfil();
	public ArrayList<FonctionnalitesProfil> AllFonctionnalitesProfil();
	public ArrayList<Fonctionnalites> AllFonctionnalitesLibres(String profil);
	}
