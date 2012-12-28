package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.ILangue;
import modele.dao.INomenclatureMDP;
import modele.dao.IPreferences;
import modele.dao.IProfil;
import modele.dao.IUtilisateur;
import modele.dao.IUtilisateurProfil;
import modele.dao.LangueDAO;
import modele.dao.LangueProfilDAO;
import modele.dao.NomenclatureMDPDAO;
import modele.dao.ParametresGenereaux;
import modele.dao.PreferencesDAO;
import modele.dao.ProfilDAO;
import modele.dao.UtilisateurDAO;
import modele.dao.UtilisateurProfilDAO;
import modele.metiers.Langue;
import modele.metiers.LangueProfil;
import modele.metiers.NomenclatureMDP;
import modele.metiers.Preferences;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;
import modele.metiers.UtilisateurProfil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class IndexController extends MultiActionController {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
		
		Map modele = new HashMap();		
		IUtilisateur utilDAO = new UtilisateurDAO();
		try{
			if ( (utilDAO.allUtilisateur() == null)  || (utilDAO.allUtilisateur().size() == 0)){
				ParametresGenereaux param = new ParametresGenereaux();
				param.langues();
				ILangue langueDAO = new LangueDAO();
				modele.put("langue",langueDAO.getLangue("100") );
				modele.put("listLangue",langueDAO.allLangue());
				return new ModelAndView("NomenclatureMDPAdmin",modele);
			}
		}
		catch(NullPointerException e){
			ParametresGenereaux param = new ParametresGenereaux();
			param.langues();	
			ILangue langueDAO = new LangueDAO();
			modele.put("langue",langueDAO.getLangue("100") );
			modele.put("listLangue",langueDAO.allLangue());
			return new ModelAndView("NomenclatureMDPAdmin",modele);			
		}
		return new ModelAndView("Connexion", null);
	}
	
//---------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView selectionLangue(HttpServletRequest request, HttpServletResponse response){
		
		String codeLangue = request.getParameter("langueDemar");
		Map modele = new HashMap();
		if(codeLangue != null){
			ILangue langDAO = new LangueDAO();
			if(langDAO.getLangue(codeLangue) != null)
				request.getSession().setAttribute("langue",langDAO.getLangue(codeLangue));
			modele.put("listLangue",langDAO.allLangue());
			modele.put("langAdmin", "ok");
			modele.put("creation", "ok");
		}
		return new ModelAndView("NomenclatureMDPAdmin",modele);
	}	
//-------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	public ModelAndView enregistrerNomenclature(HttpServletRequest request, HttpServletResponse response){
		
		Map modele = new HashMap();
		modele.put("langAdmin", "ok");
		modele.put("creation", "ok");
		Langue langue = (Langue) request.getSession().getAttribute("langue");
		NomenclatureMDP nomenclature = new NomenclatureMDP("AdminUser-NomenclatureMDP");
		
		String longMinMDP = request.getParameter("longMinMDP");
		if((longMinMDP == null) || (longMinMDP.trim().length() == 0)){
			modele.put("errorlongMinMDP", langue.getChampObligatoire());			
			return new ModelAndView("NomenclatureMDPAdmin",modele);			
		}
		else
		{
			int longMin = 0;
			try{
				longMin = Integer.parseInt(longMinMDP);
			}
			catch(NumberFormatException ex){
				modele.put("errorFormat1", langue.getFormatInvalide());
				return new ModelAndView("NomenclatureMDPAdmin",modele);
			}
			nomenclature.setLongMinMDP(longMin);
			String nbMinMinusc = request.getParameter("nbMinMinusc");
			if((nbMinMinusc == null) || (nbMinMinusc.trim().length() == 0)){
				modele.put("errorMinMinus", langue.getChampObligatoire());			
				return new ModelAndView("NomenclatureMDPAdmin",modele);
			}
			else{
				int minMinus = 0;
				try{
					minMinus = Integer.parseInt(nbMinMinusc);
				}
				catch(NumberFormatException ex){
					modele.put("errorFormat2",langue.getFormatInvalide());
					return new ModelAndView("NomenclatureMDPAdmin",modele);
				}
				nomenclature.setNbMinMinusc(minMinus);
				String nbMinMajusc = request.getParameter("nbMinMajusc");
				if((nbMinMajusc == null) || (nbMinMajusc.trim().length() == 0)){
					modele.put("errorMinMaj",langue.getChampObligatoire());			
					return new ModelAndView("NomenclatureMDPAdmin",modele);
				}
				else{
					int minMaj = 0;
					try{
						minMaj = Integer.parseInt(nbMinMajusc);
					}
					catch(NumberFormatException ex){
						modele.put("errorFormat3",langue.getFormatInvalide());
						return new ModelAndView("NomenclatureMDPAdmin",modele);
					}
					nomenclature.setNbMinMajusc(minMaj);
					String nbMinChiff = request.getParameter("nbMinChiff");
					if((nbMinChiff == null) || (nbMinChiff.trim().length() == 0)){
						modele.put("errorMinChiff",langue.getChampObligatoire());			
						return new ModelAndView("NomenclatureMDPAdmin",modele);
					}
					else{
						int minChiff = 0;
						try{
							minChiff = Integer.parseInt(nbMinChiff);
						}
						catch(NumberFormatException ex){
							modele.put("errorFormat4",langue.getFormatInvalide());
							return new ModelAndView("NomenclatureMDPAdmin",modele);
						}
						nomenclature.setNbMinChiff(minChiff);
						String nbMaxRepCar = request.getParameter("nbMaxRepCar");
						if((nbMaxRepCar == null) || (nbMaxRepCar.trim().length() == 0)){
							modele.put("errorMaxrep", langue.getChampObligatoire());			
							return new ModelAndView("NomenclatureMDPAdmin",modele);
						}
						else{
							int maxRep = 0;
							try{
								maxRep = Integer.parseInt(nbMaxRepCar);
							}
							catch(NumberFormatException ex){
								modele.put("errorFormat5",langue.getFormatInvalide());
								return new ModelAndView("NomenclatureMDPAdmin",modele);
							}
							nomenclature.setNbMaxRepCar(maxRep);
							String nbMinCarSpec = request.getParameter("nbMinCarSpec");
							if((nbMinCarSpec == null) || (nbMinCarSpec.trim().length() == 0)){
								modele.put("errorMinCar", langue.getChampObligatoire());			
								return new ModelAndView("NomenclatureMDPAdmin",modele);
							}
							else{
								int minCar = 0;
								try{
									minCar = Integer.parseInt(nbMinCarSpec);
								}
								catch(NumberFormatException ex){
									modele.put("errorFormat6",langue.getFormatInvalide());
									return new ModelAndView("NomenclatureMDPAdmin",modele);
								}
								nomenclature.setNbMincarSpec(minCar);
								String mDPdefaut = request.getParameter("mDPdefaut");
								if((mDPdefaut == null) || (mDPdefaut.trim().length() == 0)){
									modele.put("errorMDP",langue.getChampObligatoire());			
									return new ModelAndView("NomenclatureMDPAdmin",modele);
								}
								nomenclature.setmDPdefaut(mDPdefaut);
								
							}
						}
					}
				}
			}
		}
		INomenclatureMDP nomenclatureDAO = new NomenclatureMDPDAO();
		nomenclatureDAO.save(nomenclature);
		ILangue langDAO = new LangueDAO();
		
	// -----------Creation du profil AdminUser ------------------------------
		Profil adminUser = new Profil();
		adminUser.setCodeProfil("AdminUser");
		adminUser.setNomenclatureMDP(nomenclature);
		adminUser.setNbvalidEff(0);
		adminUser.setDateCree(new Date());
		IProfil profDAO = new ProfilDAO();
		profDAO.save(adminUser);
		
		LangueProfil libelleFr = new LangueProfil(langDAO.getLangue("100"),profDAO.getProfil("AdminUser"),"Administrateur Utilisateur");
		LangueProfil libelleAngl = new LangueProfil(langDAO.getLangue("200"),profDAO.getProfil("AdminUser"),"Administrator of users");
		LangueProfilDAO libDAO = new LangueProfilDAO();
		libDAO.save(libelleFr);
		libDAO.save(libelleAngl);
		
		ArrayList<LangueProfil> libsAdmin = new ArrayList<LangueProfil>();
		libsAdmin.add(libelleFr);
		libsAdmin.add(libelleAngl);
		adminUser.setLibelleProfil(libsAdmin);
		profDAO.save(adminUser);
		
	//-----------------------------------------------------------------------
		//ILangue langDAO = new LangueDAO();
		modele.put("listLangue",langDAO.allLangue());
		return new ModelAndView("CreationAdmin",modele);
	}
	
//--------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({"rawtypes", "unchecked", "deprecation" })
	public ModelAndView enregistrerAdmin(HttpServletRequest request, HttpServletResponse response){
		
		Map modele = new HashMap();
		modele.put("langAdmin","ok" );
		modele.put("nbAdmin", "ok");
		Langue langueAd = (Langue) request.getSession().getAttribute("langue");
		ILangue langDAO = new LangueDAO();
		modele.put("listLangue", langDAO.allLangue());
		
		Utilisateur util = new Utilisateur();
		int nbAdminUser;
		if(request.getSession().getAttribute("nbAdminUser") != null)
			nbAdminUser = (Integer) request.getSession().getAttribute("nbAdminUser");
		else
			nbAdminUser = Integer.parseInt(request.getParameter("nbAdminUser"));
		request.getSession().setAttribute("nbAdminUser",nbAdminUser);
		
		String codeUser = request.getParameter("codeUser");
		if((codeUser == null) ||(codeUser.trim().length() == 0)){
			modele.put("errorCode",langueAd.getChampObligatoire());
			return new ModelAndView("CreationAdmin", modele);
			
		}
		else{
			util.setCodeUtil(codeUser);
			String nom = request.getParameter("nom");
			if((nom == null) || (nom.trim().length() == 0)){
				modele.put("errorNom",langueAd.getChampObligatoire());
				return new ModelAndView("CreationAdmin", modele);
			}
			else{
				util.setNomUtil(nom);
				String langue = request.getParameter("langue");
				if(langue == null){ 
					Langue langUtil = (Langue) request.getSession().getAttribute("langue");
					util.setLangue(langUtil);
				}
				else{
					ILangue langDA0 = new LangueDAO();
					Langue langUtil = langDA0.getLangue(langue);
					util.setLangue(langUtil);
				}
				
			String motPass = request.getParameter("motPass");
			  if((motPass ==  null) || (motPass.trim().length() == 0)){
				modele.put("errorMDP",langueAd.getChampObligatoire());
				return new ModelAndView("CreationAdmin", modele);
				}
			  else{
				  util.setMotDPass(motPass);
				  String dateDebVal = request.getParameter("dateDebValid");
				  if((dateDebVal == null) || (dateDebVal.trim().length() == 0)){
					  modele.put("errorDateDeb",langueAd.getChampObligatoire());
					  return new ModelAndView("CreationAdmin", modele);
				  }
				  else{
					  Date dateDebValid = new Date(dateDebVal);
					  util.setDateDbutValid(dateDebValid);
					  
					  String dateFinVal = request.getParameter("dateFinValid");
					  if((dateFinVal == null) || (dateFinVal.trim().length() == 0)){
						  modele.put("errorDateFin",langueAd.getChampObligatoire());
						  return new ModelAndView("CreationAdmin", modele);
					  }
					  else{
						  Date dateFinValid = new Date(dateFinVal);
						  util.setDateFinValid(dateFinValid);
						  
						  // ajouter les infos manquant
						  util.setEtatutil("Actif");
						  util.setNbValidEffect(0);
						  java.util.Date dateCree = new Date();
						  util.setDateCree(dateCree);						  
						  java.util.GregorianCalendar calendar = new GregorianCalendar();
						  java.util.Date heurCree = calendar.getTime();
						  util.setHeureCree(heurCree);
						  
						  IPreferences prefDAO = new PreferencesDAO();
						  Preferences defaltPref = prefDAO.getPreferences();
						  util.setPreference(defaltPref);
						  
						  IUtilisateur utilDAO = new UtilisateurDAO();
						  utilDAO.save(util);
						  
						  IProfil profDAO = new ProfilDAO();
						  Profil profil = profDAO.getProfil("AdminUser");
						  
						  IUtilisateur utiliDAO = new UtilisateurDAO();
						  Utilisateur utili = utiliDAO.getUtilisateur(codeUser);
						  
						  UtilisateurProfil utilProfil = new UtilisateurProfil(utili,profil);
						  utilProfil.setDateDebAff(new Date());
						  utilProfil.setDateFinAff(dateFinValid);
						  utilProfil.setEtat("Actif");
						  utilProfil.setDateCree(new Date());
						  IUtilisateurProfil utilProfDAO = new UtilisateurProfilDAO();
						  utilProfDAO.save(utilProfil);
						  if(nbAdminUser == 1)
							  return new ModelAndView("Connexion",null);
						  else{
							  request.getSession().setAttribute("nbAdminUser", nbAdminUser-1);
							  return new ModelAndView("CreationAdmin", modele);
						  }
					  }
				  }
			  }
			
			
			}
			
		}
	}
	
	
	//---------------------------------------------------------------------------------------------------------

}
