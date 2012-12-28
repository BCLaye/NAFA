package modele.dao;

import java.util.ArrayList;

import modele.metiers.Langue;
import modele.metiers.Preferences;
import modele.metiers.Programme;

public class ParametresGenereaux {
	
	public ParametresGenereaux() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void langues(){
		
/* ----------------------- Français -----------------------------------------------
		           Formulaire Utilisateur
		*/
		
		Langue langue = new Langue("100");
		
		langue.setLabelAdminUser("Création des Administrateurs");
		langue.setLabelUtilisateur("Gestion des Utilisateurs");
		langue.setNbrAdminUser("Nombre d'Administrateur");
		
		langue.setNomLangue("Français");
		langue.setEntite("Entité");
		langue.setFonction("Fonction");
		langue.setCodeUser("Code Utilisateur");
		langue.setNom("Nom");
		langue.setNivPriv("Niveau de privilège");
		langue.setLangue("Langue");
		langue.setMotPass("Mot de passe");
		langue.setDateDebValid("Date début validité");
		langue.setDateFinValid("Date fin de validité");
		langue.setEtat("Etat");
		langue.setDateDernierEtat("Date dernier état");
		langue.setDateDernierConnex("Date dernière  connexion");
		langue.setHeureDernierConnex("Heure dernière  connexion");
		langue.setAdressIPDC("Adresse IP dernière  connexion");
		langue.setDateDernierDeconnex("Date derniére déconnexion");
		langue.setHeureDernierDeconnex("Heure dernière  déconnexion");
		langue.setDateDernierModifMDP("Date dernière  modification du mot de pass");
		langue.setHeureDernierModifMDP("Heure dernière  modification du mot de pass");
		langue.setNbValidEff("Nombre de validations effectuées");
		langue.setCodeUtilCree("Utilisateur ayant crée");
		langue.setDateCree("Date de création");
		langue.setHeureCree("Heure de création");
		langue.setCodeUtilModif("Utilisateur ayant modifié");
		langue.setDateModif("Date derniére modification");
		langue.setHeureModif("Heure dernière  modification");
		langue.setProfils("Profils");
		langue.setPreference("Préférence");
		
		//     Formulaire Nomenclature mot de Pass
		langue.setLabelNomenclatureMDP("Nomenclature du mot de passe");
		langue.setLongMinMDP("Longueur minimale");
		langue.setNbMinMinusc("Nombre min de minuscule");
		langue.setNbMinMajusc("Nombre min de majuscule");
		langue.setNbMinChiff("Nombre min de chiffre");
		langue.setNbMaxRepCar("Nombre max de répétition");
		langue.setNbMincarSpec("Nombre min de caract spéciaux");
		langue.setmDPdefaut("Mot passe par défaut");
		
		
		
		//---------------------Boutons-------------------------------
		langue.setValider("Valider");
		langue.setAnnuler("Annuler");
		langue.setSupprimer("Supprimer");
		langue.setRechercher("Rechercher");
		langue.setPremier("Premier");
		langue.setDernier("Dernier");
		langue.setPrecedant("Précédent");
		langue.setSuivant("Suivant");
		
		//--------------------Messages d'erreur ---------------------
		langue.setChampObligatoire("Champ obligatoire");
		langue.setFormatInvalide("Un entier est attendu");
		langue.setRelationImpossible("Relation Impossible");
		langue.setNbInsuffisant("Nombre insuffisant");
		langue.setTaillesup("La taille est supéieur à");
		langue.setPassIncorect("Mot de pass incorrect");
		
		//-------------------Message de confirmation------------------------
		langue.setConfirmationEnregistrer("L'enregistrement existe déjà \n Voulez-vous enregister les modifications?");
		langue.setConfirmationSupprimer("Voulez-vous supprimer cette enregistrement?");
		
		//--------------------Formulaire fonctionnalites ---------------------
		langue.setLabelFonctionnalite("Gestion des Fonctionnalités");
		langue.setCodeFonctionnalite("Code Fonctionnalité");
		langue.setNbValidReq("Nombre de validation requis");
		langue.setModule("Module");
		langue.setProgramme("Programme");
		
		//------------------ Formulaire entites ---------------------------------
		langue.setLabelEntites("Gestion des entités");
		langue.setCodeEntite("Code entité"); 
		langue.setNiveauOrg("Niveau organisationnel");
		langue.setUtilRattach("Utilisateur rattaché");
		langue.setEntiteRattach("Entité rattaché");
		
		// ---------------------Informations sur le formulaire Profils--------------
		langue.setLabelProfils("Gestion des Profils");
		langue.setCodeProfil("Code profil");		
		
		// ---------------------Informations sur le formulaire Fonctionnalitesprofil--------------
		langue.setLabelFonctionnalitesProfil("Gestion des Associations Profils-Fonctionnalités");
		langue.setProfil("Profil");
		langue.setFonctionnalites("Fonctionnalités");
		langue.setFonctionnalite("Fonctionnalité");
		
		// --------------------Formulaire ValidationFonctionnalitesProfil ---------------------------
		langue.setValidation("Validations");
		langue.setLabelValidation("Validation Fonctionnalites-Profi");
		langue.setProfilValidation("Profil de validation");
		langue.setRangValidation("Rang de validation");
		langue.setNbValidEffect("Nombre de Validation effectuée");
		langue.setUtilValid("Utilisateur ayant valider");
		langue.setDateValid("Date de validation");
		langue.setValide("Validé");
		langue.setInvalide("Invalide");
		
		// ----------------------- Formulaire MotDePass -----------------------------------
		langue.setLabelMDP("Changer votre mot de passe");
		langue.setMotDPassActuel("Mot de passe actuel");
		langue.setNouvoMDP("Nouveau mot de passe");
		langue.setConfirmNouvoMDP("Confirmer le mot de passe");
		
	   // ------------------------- Formulaire Fonctions ---------------------------------
		langue.setLabelFonction("Gestion des Fonctions");
		langue.setCodefonction("Code fonction");
		
		//--------------------------Formulaire UtilisateurProfil -----------------------
		langue.setLabelUtilisateurProfil("Utilisateur-Profil");
		langue.setUtilisateur("Utilisateur");
		langue.setDateDebAff("Date début affectation");
		langue.setDateFinAff("Date fin affectation");
		
		//-------------------------- Formulaire Menus ---------------------------------
		langue.setLabelMenus("Gestion des Menus");
		langue.setCodeMenus("Code Menu");
		langue.setMenus("Menus");
		
		//------------------- Selection Oui/Non -----------------------------------
		langue.setOui("Oui");
		langue.setNon("Non");
		
		//-------------------Formulaire MenusVertical ----------------------------------
		langue.setAcceuil("Accueil");
		langue.setDeconnexion("Deconnexion");
		langue.setReduirTs("Réduir tous");
		langue.setDevelopTs("Développer tous");
		
		// ------------------ formulaire Preferences --------------------------------
		langue.setPreferences("Préférences");
		langue.setLabelPreferences("Gestion des Préférences");
		langue.setCodePreference("Code préférence");
		langue.setModelAffichageMenu("Model affichage menus");
		langue.setVertical("Vertical");
		langue.setHorizontal("Horizontal");
		langue.setContext("Contexte");
		
		ILangue lagueDAO = new LangueDAO();
		lagueDAO.save(langue);
		
/* ----------------------- Anglais --------------------------------------------------------------------------------
                              Formulaire Utilisateur  */
				Langue anglais = new Langue("200");
				
				anglais.setLabelAdminUser("Creation of Administrators");
				anglais.setLabelUtilisateur("Management of Users");
				anglais.setNbrAdminUser("Number of Administrator");
				
				
				anglais.setNomLangue("English");
				anglais.setEntite("Entity");
				anglais.setFonction("Function");
				anglais.setCodeUser("User Code");
				anglais.setNom("Name");
				anglais.setNivPriv("Privilege level");
				anglais.setLangue("Language");
				anglais.setMotPass("Pass word");
				anglais.setDateDebValid("Validity start date");
				anglais.setDateFinValid("Validity end date");
				anglais.setEtat("State");
				anglais.setDateDernierEtat("Date of last state");
				anglais.setDateDernierConnex("Date of last login");
				anglais.setHeureDernierConnex("Hour of last connection");
				anglais.setAdressIPDC("Last login IP Address");
				anglais.setDateDernierDeconnex("Date of last log out");
				anglais.setHeureDernierDeconnex("Hour of last logout");
				anglais.setDateDernierModifMDP("Date of last password change");
				anglais.setHeureDernierModifMDP("Hour of last password change");
				anglais.setNbValidEff("Number of validations");
				anglais.setCodeUtilCree("User having created");
				anglais.setDateCree("Date of creation");
				anglais.setHeureCree("Hour of creation");
				anglais.setCodeUtilModif("User having changed");
				anglais.setDateModif("Date of last change");
				anglais.setHeureModif("Hour of last change");
				anglais.setProfils("Profile");
				anglais.setPreference("Preference");
				
     //			     Formulaire Nomenclature mot de Pass
				anglais.setLabelNomenclatureMDP("Nomenclature of password");
				anglais.setLongMinMDP("Minimal length");
				anglais.setNbMinMinusc("Minimum number of small letter");
				anglais.setNbMinMajusc("Minimum number of capital letter");
				anglais.setNbMinChiff("Minimum number of numeral");
				anglais.setNbMaxRepCar("Maximum number of repetition ");
				anglais.setNbMincarSpec("Minimum number of pecial letter");
				anglais.setmDPdefaut("Default password");
				
				
				
				//---------------- Boutons -------------------------------
				anglais.setValider("Validate");
				anglais.setAnnuler("Cancel");	
				anglais.setSupprimer("Delete");
				anglais.setRechercher("Search");
				anglais.setPremier("First");
				anglais.setDernier("Last");
				anglais.setPrecedant("Previous");
				anglais.setSuivant("Follow");
				
				//--------------------Messages d'erreur ---------------------
				anglais.setChampObligatoire("Compulsory field");
				anglais.setFormatInvalide("An entirety is expected");
				anglais.setRelationImpossible("Impossible relation");
				anglais.setNbInsuffisant("Deficient number");
				anglais.setTaillesup("The size is supeior with ");
				anglais.setPassIncorect("Incorrect password");
				
				//-------------------Message de confirmation------------------------
				anglais.setConfirmationEnregistrer("The recording already exists.\n Do you want update the modifications?");
				anglais.setConfirmationSupprimer("Do you want to remove this recording?");
				
				//--------------------Formulaire fonctionnalites ---------------------
				anglais.setLabelFonctionnalite("Management of Functionalities");
				anglais.setCodeFonctionnalite("Functionality code");
				anglais.setNbValidReq("Number of necessary validation");
				anglais.setModule("Module");
				anglais.setProgramme("Program");
				
				//------------------ Formulaire entites ---------------------------------
				anglais.setLabelEntites("Management of entities");
				anglais.setCodeEntite("code entity");
				anglais.setNiveauOrg("Organisational level");
				anglais.setUtilRattach("Attached user");
				anglais.setEntiteRattach("Attached entity");
				
				// ---------------------Informations sur le formulaire Profils--------------
				anglais.setLabelProfils("Management of Profiles");
				anglais.setCodeProfil("Code profile");
				anglais.setFonctionnalites("Functionnalities");
				
				// ---------------------Informations sur le formulaire Fonctionnalitesprofil--------------
				anglais.setLabelFonctionnalitesProfil("Management of Associations Profile-Functionalities");
				anglais.setProfil("Profile");
				anglais.setFonctionnalites("Fonctionnalities");
				anglais.setFonctionnalite("Fonctionnality");
				
				// --------------------Formulaire ValidationFonctionnalitesProfil ---------------------------
				anglais.setValidation("Validations");
				anglais.setLabelValidation("Validation Fonctionnalites-Profi");
				anglais.setProfilValidation("Profile of validation");
				anglais.setRangValidation("Row of validation");
				anglais.setNbValidEffect("Number of Validation carried out");
				anglais.setUtilValid("user having to validate");
				anglais.setDateValid("Date of validation");
				anglais.setValide("Enable");
				anglais.setInvalide("Desable");
				
				// ----------------------- Formulaire MotDePass -----------------------------------
				anglais.setLabelMDP("Change your password");
				anglais.setMotDPassActuel("Current password");
				anglais.setNouvoMDP("New password");
				anglais.setConfirmNouvoMDP("Confirm password");
				
				// ------------------------- Formulaire Fonctions ---------------------------------
				anglais.setLabelFonction("Management of Functions");
				anglais.setCodefonction("Code function");
				
				//--------------------------Formulaire UtilisateurProfil -----------------------
				anglais.setLabelUtilisateurProfil("User-Profile");
				anglais.setUtilisateur("User");
				anglais.setDateDebAff("Beginning date of assignment ");
				anglais.setDateFinAff("Ending date of assignment");
				
				//-------------------------- Formulaire Menus ---------------------------------
				anglais.setLabelMenus("Mamagement of items");
				anglais.setCodeMenus("Item code");
				anglais.setMenus("Items");
				
				//-------------------Formulaire MenusVertical ----------------------------------
				anglais.setAcceuil("Welcome");
				anglais.setDeconnexion("Logout");
				anglais.setReduirTs("Decrease all");
				anglais.setDevelopTs("Develop all");
				
				// ------------------ formulaire Preferences --------------------------------
				anglais.setPreferences("Preferences");
				anglais.setLabelPreferences("Management of Preferences");
				anglais.setCodePreference("Code preference");
				anglais.setModelAffichageMenu("model of items positing");
				anglais.setVertical("Vertical");
				anglais.setHorizontal("Horizontal");
				anglais.setContext("Context");
				
				//------------------- Selection Oui/Non -----------------------------------
				anglais.setOui("Yes");
				anglais.setNon("No");
				
				lagueDAO.save(anglais);
				
				
//------------------------- Les programmes de l'application --------------------------------------------------
				ArrayList<Programme> listprogramme = new ArrayList<Programme>();
				
				listprogramme.add(new Programme("fonctionnalites"));
				listprogramme.add(new Programme("utilisateurs"));
				listprogramme.add(new Programme("entites"));
				listprogramme.add(new Programme("profils"));
				listprogramme.add(new Programme("FonctionnalitesProfil"));
				listprogramme.add(new Programme("validation"));
				listprogramme.add(new Programme("Fonctions"));
				listprogramme.add(new Programme("Menus"));
				
				IProgramme progDAO = new ProgrammeDAO();
				progDAO.save(listprogramme);
				
				
//-------------------- Preferences Styles ------------------------------------------------------------------------
				ArrayList<Preferences> listPref = new ArrayList<Preferences>();
				listPref.add(new Preferences("theme1B","theme1","bleu"));
				listPref.add(new Preferences("theme1R","theme1","rouge"));
				listPref.add(new Preferences("theme1V","theme1","verte"));
				listPref.add(new Preferences("theme1N","theme1","noir"));
				listPref.add(new Preferences("theme2B","theme2","bleu"));
				listPref.add(new Preferences("theme2R","theme2","rouge"));
				listPref.add(new Preferences("theme2V","theme2","verte"));
				listPref.add(new Preferences("theme2N","theme2","noir"));
				
				IPreferences prefDAO = new PreferencesDAO();
				prefDAO.save(listPref);
				
				
	}

}
