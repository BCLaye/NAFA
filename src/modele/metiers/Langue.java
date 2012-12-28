package modele.metiers;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Langue")
public class Langue {
	
	@Id
	@Column(name="Code_langue")
	private String codeLangue;
	
	@Column(name="nom_langue")
	private String nomLangue;
	
	//information sur l utilisateur
	@Column
	private String labelUtilisateur;
	@Column
	private String entite;
	@Column
	private String fonction;
	@Column
	private String codeUser;
	@Column
	private String nom;
	@Column
	private String nivPriv;
	@Column
	private String langue;
	@Column
	private String motPass;
	@Column
	private String dateDebValid;
	@Column
	private String dateFinValid;
	@Column
	private String etat;
	@Column
	private String dateDernierEtat;
	@Column
	private String dateDernierConnex;
	@Column
	private String heureDernierConnex;
	@Column
	private String adressIPDC;
	@Column
	private String dateDernierDeconnex;
	@Column
	private String heureDernierDeconnex;
	@Column
	private String dateDernierModifMDP;
	@Column
	private String heureDernierModifMDP;
	@Column
	private String nbValidEff;
	@Column
	private String codeUtilCree;
	@Column
	private String dateCree;
	@Column
	private String heureCree;
	@Column
	private String codeUtilModif;
	@Column
	private String dateModif;
	@Column
	private String heureModif;
	@Column
	private String profils;
	@Column
	private String preference;
	
	// creation des adminUsers
	@Column
	private String labelAdminUser;
	@Column
	private String nbrAdminUser;
	//  Les boutons valider et annuler
	@Column
	private String valider;
	@Column
	private String annuler;
	@Column
	private String supprimer;
	@Column
	private String rechercher;
	@Column
	private String premier;
	@Column
	private String dernier;
	@Column
	private String precedant;
	@Column
	private String suivant;
	
	//--- Information sur la Nomenclarure du mot de Pass
	@Column
	private String labelNomenclatureMDP;
	@Column
	private String longMinMDP;	
	@Column
	private String nbMinMinusc;
	@Column
	private String nbMinMajusc;	
	@Column
	private String nbMinChiff;
	@Column
	private String nbMaxRepCar;  	
	@Column
	private String nbMincarSpec; 	
	@Column
	private String mDPdefaut;
	
	// Information pour les messages d'ERROR
	@Column
	private String champObligatoire;
	@Column
	private String formatInvalide;
	@Column
	private String relationImpossible;
	@Column
	private String nbInsuffisant;
	@Column
	private String taillesup;
	@Column
	private String passIncorect;
	
	// Informations du formulaire des fonctionnalité
	@Column
	private String labelFonctionnalite;
	@Column
	private String codeFonctionnalite;
	@Column
	private String nbValidReq;
	@Column
	private String module;
	@Column
	private String programme; 
	
	//   Message de Confirmation
	@Column
	private String confirmationEnregistrer;
	@Column
	private String confirmationSupprimer;
	
	// Information sur la formulaire Entites
	@Column
	private String labelEntites;
	@Column
	private String codeEntite;
	@Column
	private String niveauOrg;
	@Column
	private String utilRattach;
	@Column
	private String entiteRattach;
	
	// selection Oui/Non
	@Column
	private String oui;
	@Column
	private String non;
	
	// Informations sur le formulaire Profils
	@Column
	private String labelProfils;
	@Column
	private String codeProfil;
	
	// Formulaire FonctionnalitesProfil
	@Column
	private String labelFonctionnalitesProfil;
	@Column
	private String profil;
	@Column
	private String fonctionnalites;
	@Column
	private String fonctionnalite;
	
	// Formulaire ValidationFonctionnalitesProfil
	@Column
	private String validation;
	@Column
	private String labelValidation;
	@Column
	private String profilValidation;
	@Column
	private String rangValidation;
	@Column
	private String nbValidEffect;
	@Column
	private String utilValid;
	@Column
	private String dateValid;
	@Column
	private String valide;
	@Column
	private String invalide;
	
	//  Formulaire MotDePass 
	@Column
	private String labelMDP;
	@Column
	private String motDPassActuel;
	@Column
	private String nouvoMDP;
	@Column
	private String confirmNouvoMDP;
	
	//  Formulaire Fonctions
	@Column
	private String labelFonction;
	@Column
	private String codefonction;
	
	//Formulaire UtilisateurProfil
	@Column
	private String labelUtilisateurProfil;
	@Column
	private String utilisateur;
	@Column
	private String dateDebAff;
	@Column
	private String dateFinAff;
	
	//Formulaire Menus
	@Column
	private String labelMenus;
	@Column
	private String codeMenus;
	@Column
	private String menus;
	
	//Formulaire MenusVertical
	@Column
	private String acceuil;
	@Column
	private String deconnexion;
	@Column
	private String reduirTs;
	@Column
	private String developTs;
	
	//formulaire Preferences
	@Column
	private String preferences;
	@Column
	private String labelPreferences;
	@Column
	private String codePreference;
	@Column
	private String modelAffichageMenu;
	@Column
	private String vertical;
	@Column
	private String horizontal;
	@Column
	private String context;
	
	//--------------------------------
	
	@OneToMany(mappedBy="langue")
	private Collection<LangueProfil> libelleProfil;
	
	@OneToMany(mappedBy="langue")
	private Collection<LangueFonctionnalites> libelleFonctionnalite;
	
	@OneToMany(mappedBy="langue")
	private Collection<LangueEntite> libelleEntite;
	
	@OneToMany(mappedBy="langue")
	private Collection<LangueFonctions> libelleFonction;
	
	@OneToMany(mappedBy="langue")
	private Collection<LangueMenus> libelleMenus;
	
//-----------------------------------------------------------------------------------------
	public Langue() {
		super();
	}
	
	public Langue(String codeLangue) {
		this.codeLangue = codeLangue;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(String code) {
		this.codeUser = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String non) {
		this.nom = non;
	}

	public String getNivPriv() {
		return nivPriv;
	}

	public void setNivPriv(String nivPriv) {
		this.nivPriv = nivPriv;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getMotPass() {
		return motPass;
	}

	public void setMotPass(String motPass) {
		this.motPass = motPass;
	}

	public String getDateDebValid() {
		return dateDebValid;
	}

	public void setDateDebValid(String dateDebValid) {
		this.dateDebValid = dateDebValid;
	}

	public String getDateFinValid() {
		return dateFinValid;
	}

	public void setDateFinValid(String dateFinValid) {
		this.dateFinValid = dateFinValid;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDateDernierEtat() {
		return dateDernierEtat;
	}

	public void setDateDernierEtat(String dateDernierEtat) {
		this.dateDernierEtat = dateDernierEtat;
	}

	public String getDateDernierConnex() {
		return dateDernierConnex;
	}

	public void setDateDernierConnex(String dateDernierConnex) {
		this.dateDernierConnex = dateDernierConnex;
	}

	public String getHeureDernierConnex() {
		return heureDernierConnex;
	}

	public void setHeureDernierConnex(String heureDernierConnex) {
		this.heureDernierConnex = heureDernierConnex;
	}

	public String getAdressIPDC() {
		return adressIPDC;
	}

	public void setAdressIPDC(String adressIPDC) {
		this.adressIPDC = adressIPDC;
	}

	public String getDateDernierDeconnex() {
		return dateDernierDeconnex;
	}

	public void setDateDernierDeconnex(String dateDernierDeconnex) {
		this.dateDernierDeconnex = dateDernierDeconnex;
	}

	public String getHeureDernierDeconnex() {
		return heureDernierDeconnex;
	}

	public void setHeureDernierDeconnex(String heureDernierDeconnex) {
		this.heureDernierDeconnex = heureDernierDeconnex;
	}

	public String getDateDernierModifMDP() {
		return dateDernierModifMDP;
	}

	public void setDateDernierModifMDP(String dateDernierModifMDP) {
		this.dateDernierModifMDP = dateDernierModifMDP;
	}

	public String getHeureDernierModifMDP() {
		return heureDernierModifMDP;
	}

	public void setHeureDernierModifMDP(String heureDernierModifMDP) {
		this.heureDernierModifMDP = heureDernierModifMDP;
	}

	public String getNbValidEff() {
		return nbValidEff;
	}

	public void setNbValidEff(String nbValidEff) {
		this.nbValidEff = nbValidEff;
	}

	public String getCodeUtilCree() {
		return codeUtilCree;
	}

	public void setCodeUtilCree(String codeUtilCree) {
		this.codeUtilCree = codeUtilCree;
	}

	public String getDateCree() {
		return dateCree;
	}

	public void setDateCree(String dateCree) {
		this.dateCree = dateCree;
	}

	public String getHeureCree() {
		return heureCree;
	}

	public void setHeureCree(String heureCree) {
		this.heureCree = heureCree;
	}

	public String getCodeUtilModif() {
		return codeUtilModif;
	}

	public void setCodeUtilModif(String codeUtilModif) {
		this.codeUtilModif = codeUtilModif;
	}

	public String getDateModif() {
		return dateModif;
	}

	public void setDateModif(String dateModif) {
		this.dateModif = dateModif;
	}

	public String getHeureModif() {
		return heureModif;
	}

	public void setHeureModif(String heureModif) {
		this.heureModif = heureModif;
	}

	public String getCodeLangue() {
		return codeLangue;
	}

	public void setCodeLangue(String codeLangue) {
		this.codeLangue = codeLangue;
	}

	public Collection<LangueProfil> getLibelleProfil() {
		return libelleProfil;
	}

	public void setLibelleProfil(Collection<LangueProfil> libelleProfil) {
		this.libelleProfil = libelleProfil;
	}

	public Collection<LangueFonctionnalites> getLibelleFonctionnalite() {
		return libelleFonctionnalite;
	}

	public void setLibelleFonctionnalite(
			Collection<LangueFonctionnalites> libelleFonctionnalite) {
		this.libelleFonctionnalite = libelleFonctionnalite;
	}

	public Collection<LangueEntite> getLibelleEntite() {
		return libelleEntite;
	}

	public void setLibelleEntite(Collection<LangueEntite> libelleEntite) {
		this.libelleEntite = libelleEntite;
	}

	public Collection<LangueFonctions> getLibelleFonction() {
		return libelleFonction;
	}

	public void setLibelleFonction(Collection<LangueFonctions> libelleFonction) {
		this.libelleFonction = libelleFonction;
	}

	public String getNomLangue() {
		return nomLangue;
	}

	public void setNomLangue(String nomLangue) {
		this.nomLangue = nomLangue;
	}

	public String getLabelAdminUser() {
		return labelAdminUser;
	}

	public void setLabelAdminUser(String labelAdminUser) {
		this.labelAdminUser = labelAdminUser;
	}

	public String getNbrAdminUser() {
		return nbrAdminUser;
	}

	public void setNbrAdminUser(String nbrAdminUser) {
		this.nbrAdminUser = nbrAdminUser;
	}

	public String getValider() {
		return valider;
	}

	public void setValider(String valider) {
		this.valider = valider;
	}

	public String getAnnuler() {
		return annuler;
	}

	public void setAnnuler(String annuler) {
		this.annuler = annuler;
	}

	public String getLongMinMDP() {
		return longMinMDP;
	}

	public void setLongMinMDP(String longMinMDP) {
		this.longMinMDP = longMinMDP;
	}

	public String getNbMinMinusc() {
		return nbMinMinusc;
	}

	public void setNbMinMinusc(String nbMinMinusc) {
		this.nbMinMinusc = nbMinMinusc;
	}

	public String getNbMinMajusc() {
		return nbMinMajusc;
	}

	public void setNbMinMajusc(String nbMinMajusc) {
		this.nbMinMajusc = nbMinMajusc;
	}

	public String getNbMinChiff() {
		return nbMinChiff;
	}

	public void setNbMinChiff(String nbMinChiff) {
		this.nbMinChiff = nbMinChiff;
	}

	public String getNbMaxRepCar() {
		return nbMaxRepCar;
	}

	public void setNbMaxRepCar(String nbMaxRepCar) {
		this.nbMaxRepCar = nbMaxRepCar;
	}

	

	public String getNbMincarSpec() {
		return nbMincarSpec;
	}

	public void setNbMincarSpec(String nbMincarSpec) {
		this.nbMincarSpec = nbMincarSpec;
	}

	public String getmDPdefaut() {
		return mDPdefaut;
	}

	public void setmDPdefaut(String mDPdefaut) {
		this.mDPdefaut = mDPdefaut;
	}

	public String getLabelNomenclatureMDP() {
		return labelNomenclatureMDP;
	}

	public void setLabelNomenclatureMDP(String labelNomenclatureMDP) {
		this.labelNomenclatureMDP = labelNomenclatureMDP;
	}

	public String getChampObligatoire() {
		return champObligatoire;
	}

	public void setChampObligatoire(String champObligatoire) {
		this.champObligatoire = champObligatoire;
	}

	public String getFormatInvalide() {
		return formatInvalide;
	}

	public void setFormatInvalide(String formatInvalide) {
		this.formatInvalide = formatInvalide;
	}

	public String getLabelFonctionnalite() {
		return labelFonctionnalite;
	}

	public void setLabelFonctionnalite(String labelFonctionnalite) {
		this.labelFonctionnalite = labelFonctionnalite;
	}

	public String getCodeFonctionnalite() {
		return codeFonctionnalite;
	}

	public void setCodeFonctionnalite(String codeFonctionnalite) {
		this.codeFonctionnalite = codeFonctionnalite;
	}

	public String getNbValidReq() {
		return nbValidReq;
	}

	public void setNbValidReq(String nbValidReq) {
		this.nbValidReq = nbValidReq;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String codeModule) {
		this.module = codeModule;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getConfirmationEnregistrer() {
		return confirmationEnregistrer;
	}

	public void setConfirmationEnregistrer(String confirmationEnregistrer) {
		this.confirmationEnregistrer = confirmationEnregistrer;
	}

	public String getConfirmationSupprimer() {
		return confirmationSupprimer;
	}

	public void setConfirmationSupprimer(String confirmationSupprimer) {
		this.confirmationSupprimer = confirmationSupprimer;
	}

	public String getLabelUtilisateur() {
		return labelUtilisateur;
	}

	public void setLabelUtilisateur(String labelUtilisateur) {
		this.labelUtilisateur = labelUtilisateur;
	}

	public String getProfils() {
		return profils;
	}

	public void setProfils(String profil) {
		this.profils = profil;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public String getCodeEntite() {
		return codeEntite;
	}

	public void setCodeEntite(String codeEntite) {
		this.codeEntite = codeEntite;
	}

	public String getNiveauOrg() {
		return niveauOrg;
	}

	public void setNiveauOrg(String niveauOrg) {
		this.niveauOrg = niveauOrg;
	}

	public String getUtilRattach() {
		return utilRattach;
	}

	public void setUtilRattach(String utilRattach) {
		this.utilRattach = utilRattach;
	}

	public String getEntiteRattach() {
		return entiteRattach;
	}

	public void setEntiteRattach(String entiteRattach) {
		this.entiteRattach = entiteRattach;
	}

	public String getLabelEntites() {
		return labelEntites;
	}

	public void setLabelEntites(String labelEntites) {
		this.labelEntites = labelEntites;
	}

	public String getOui() {
		return oui;
	}

	public void setOui(String oui) {
		this.oui = oui;
	}

	public String getNon() {
		return non;
	}

	public void setNon(String non) {
		this.non = non;
	}

	public String getSupprimer() {
		return supprimer;
	}

	public void setSupprimer(String supprimer) {
		this.supprimer = supprimer;
	}

	public String getRechercher() {
		return rechercher;
	}

	public void setRechercher(String rechercher) {
		this.rechercher = rechercher;
	}

	public String getPremier() {
		return premier;
	}

	public void setPremier(String premier) {
		this.premier = premier;
	}

	public String getDernier() {
		return dernier;
	}

	public void setDernier(String dernier) {
		this.dernier = dernier;
	}

	public String getPrecedant() {
		return precedant;
	}

	public void setPrecedant(String precedant) {
		this.precedant = precedant;
	}

	public String getSuivant() {
		return suivant;
	}

	public void setSuivant(String suivant) {
		this.suivant = suivant;
	}

	public String getRelationImpossible() {
		return relationImpossible;
	}

	public void setRelationImpossible(String relationImpossible) {
		this.relationImpossible = relationImpossible;
	}

	public String getLabelProfils() {
		return labelProfils;
	}

	public void setLabelProfils(String labelProfils) {
		this.labelProfils = labelProfils;
	}

	public String getCodeProfil() {
		return codeProfil;
	}

	public void setCodeProfil(String codeProfil) {
		this.codeProfil = codeProfil;
	}

	public String getFonctionnalites() {
		return fonctionnalites;
	}

	public void setFonctionnalites(String fonctionnalites) {
		this.fonctionnalites = fonctionnalites;
	}

	public String getLabelFonctionnalitesProfil() {
		return labelFonctionnalitesProfil;
	}

	public void setLabelFonctionnalitesProfil(String labelFonctionnalitesProfil) {
		this.labelFonctionnalitesProfil = labelFonctionnalitesProfil;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public String getFonctionnalite() {
		return fonctionnalite;
	}

	public void setFonctionnalite(String fonctionnalite) {
		this.fonctionnalite = fonctionnalite;
	}

	public String getLabelValidation() {
		return labelValidation;
	}

	public void setLabelValidation(String labelValidation) {
		this.labelValidation = labelValidation;
	}

	public String getProfilValidation() {
		return profilValidation;
	}

	public void setProfilValidation(String profilValidation) {
		this.profilValidation = profilValidation;
	}

	public String getRangValidation() {
		return rangValidation;
	}

	public void setRangValidation(String rangValidation) {
		this.rangValidation = rangValidation;
	}

	public String getUtilValid() {
		return utilValid;
	}

	public void setUtilValid(String utilValid) {
		this.utilValid = utilValid;
	}

	public String getDateValid() {
		return dateValid;
	}

	public void setDateValid(String dateValid) {
		this.dateValid = dateValid;
	}

	public String getNbInsuffisant() {
		return nbInsuffisant;
	}

	public void setNbInsuffisant(String nbInsuffisant) {
		this.nbInsuffisant = nbInsuffisant;
	}

	public String getTaillesup() {
		return taillesup;
	}

	public void setTaillesup(String taillesup) {
		this.taillesup = taillesup;
	}

	public String getNbValidEffect() {
		return nbValidEffect;
	}

	public void setNbValidEffect(String nbValidEffect) {
		this.nbValidEffect = nbValidEffect;
	}

	public String getValide() {
		return valide;
	}

	public void setValide(String valide) {
		this.valide = valide;
	}

	public String getInvalide() {
		return invalide;
	}

	public void setInvalide(String invalide) {
		this.invalide = invalide;
	}

	public String getMotDPassActuel() {
		return motDPassActuel;
	}

	public void setMotDPassActuel(String motDPassActuel) {
		this.motDPassActuel = motDPassActuel;
	}

	public String getNouvoMDP() {
		return nouvoMDP;
	}

	public void setNouvoMDP(String nouvoMDP) {
		this.nouvoMDP = nouvoMDP;
	}

	public String getConfirmNouvoMDP() {
		return confirmNouvoMDP;
	}

	public void setConfirmNouvoMDP(String confirmNouvoMDP) {
		this.confirmNouvoMDP = confirmNouvoMDP;
	}

	public String getLabelMDP() {
		return labelMDP;
	}

	public void setLabelMDP(String labelMDP) {
		this.labelMDP = labelMDP;
	}

	public String getPassIncorect() {
		return passIncorect;
	}

	public void setPassIncorect(String passIncorect) {
		this.passIncorect = passIncorect;
	}

	public String getLabelFonction() {
		return labelFonction;
	}

	public void setLabelFonction(String labelFonction) {
		this.labelFonction = labelFonction;
	}

	public String getCodefonction() {
		return codefonction;
	}

	public void setCodefonction(String codefonction) {
		this.codefonction = codefonction;
	}

	public String getLabelUtilisateurProfil() {
		return labelUtilisateurProfil;
	}

	public void setLabelUtilisateurProfil(String labelUtilisateurProfil) {
		this.labelUtilisateurProfil = labelUtilisateurProfil;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getDateDebAff() {
		return dateDebAff;
	}

	public void setDateDebAff(String dateDebAff) {
		this.dateDebAff = dateDebAff;
	}

	public String getDateFinAff() {
		return dateFinAff;
	}

	public void setDateFinAff(String dateFinAff) {
		this.dateFinAff = dateFinAff;
	}

	public Collection<LangueMenus> getLibelleMenus() {
		return libelleMenus;
	}

	public void setLibelleMenus(Collection<LangueMenus> libelleMenus) {
		this.libelleMenus = libelleMenus;
	}

	public String getLabelMenus() {
		return labelMenus;
	}

	public void setLabelMenus(String labelMenus) {
		this.labelMenus = labelMenus;
	}

	public String getCodeMenus() {
		return codeMenus;
	}

	public void setCodeMenus(String codeMenus) {
		this.codeMenus = codeMenus;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public String getAcceuil() {
		return acceuil;
	}

	public void setAcceuil(String acceuil) {
		this.acceuil = acceuil;
	}

	public String getDeconnexion() {
		return deconnexion;
	}

	public void setDeconnexion(String deconnexion) {
		this.deconnexion = deconnexion;
	}

	public String getReduirTs() {
		return reduirTs;
	}

	public void setReduirTs(String reduirTs) {
		this.reduirTs = reduirTs;
	}

	public String getDevelopTs() {
		return developTs;
	}

	public void setDevelopTs(String developTs) {
		this.developTs = developTs;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getLabelPreferences() {
		return labelPreferences;
	}

	public void setLabelPreferences(String labelPreferences) {
		this.labelPreferences = labelPreferences;
	}

	public String getCodePreference() {
		return codePreference;
	}

	public void setCodePreference(String codePreference) {
		this.codePreference = codePreference;
	}

	public String getModelAffichageMenu() {
		return modelAffichageMenu;
	}

	public void setModelAffichageMenu(String modelAffichageMenu) {
		this.modelAffichageMenu = modelAffichageMenu;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(String horizontal) {
		this.horizontal = horizontal;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}	
}
