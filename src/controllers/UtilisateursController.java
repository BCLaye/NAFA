package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.EntiteDAO;
import modele.dao.IEntite;
import modele.dao.ILangue;
import modele.dao.ILangueEntite;
import modele.dao.ILangueFonctions;
import modele.dao.ILangueProfil;
import modele.dao.IPreferences;
import modele.dao.IUtilisateur;
import modele.dao.IUtilisateurProfil;
import modele.dao.LangueDAO;
import modele.dao.LangueEntiteDAO;
import modele.dao.LangueFonctionsDAO;
import modele.dao.LangueProfilDAO;
import modele.dao.PreferencesDAO;
import modele.dao.UtilisateurDAO;
import modele.dao.UtilisateurProfilDAO;
import modele.metiers.Entite;
import modele.metiers.Fonctions;
import modele.metiers.Langue;
import modele.metiers.LangueEntite;
import modele.metiers.LangueFonctions;
import modele.metiers.LangueProfil;
import modele.metiers.Preferences;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;
import modele.metiers.UtilisateurProfil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UtilisateursController extends MultiActionController {
	
@SuppressWarnings({ "unchecked", "rawtypes" })
public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
		request.getSession().invalidate();
		request.getSession().setAttribute("utilisateur",utilisateur);
		request.getSession().setAttribute("profilConnecte",profilConnecte);
		
		// Liste des entites
		ILangueEntite langEntDAO = new LangueEntiteDAO();
		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
		modele.put("listEntites",listEntites);
		
		//Liste des fonctions
		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
		ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
		modele.put("listFonctions", listFct);
		
		//liste des Profil
		ILangueProfil langProfDAO = new LangueProfilDAO();
		ArrayList<LangueProfil> listProfil = langProfDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
		request.getSession().setAttribute("listProfil", listProfil);
		
		//liste des langues
		ILangue langueDAO = new LangueDAO();
		request.getSession().setAttribute("langues", langueDAO.allLangue());
		
		return new ModelAndView("Utilisateurs",modele);
	}

//----------------------------------------------------------------------------------------------------------------------
      @SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView selectionEntite(HttpServletRequest request, HttpServletResponse response){
    		
    		if(request.getSession().getAttribute("utilisateur") == null){
    			request.getSession().invalidate();
    			return new ModelAndView("Connexion",null);
    		}
    		Map modele = new HashMap();
    		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
    		
    		Utilisateur utilCourant = new Utilisateur();
    		if(request.getSession().getAttribute("utilisateurCourant") != null)
    			utilCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
    		
    		String entite = request.getParameter("entite");
      		ILangueEntite langEntDAO = new LangueEntiteDAO();
    		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
    		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
      		
      			IEntite entDAO = new EntiteDAO();
      			Entite entSelect = entDAO.getEntite(entite);
      			int cpt = 0;
      			while(cpt < listEntites.size()){
      				if(listEntites.get(cpt).getEntite().getCodeEntite().equals(entite)){
      					utilCourant.setEntite(listEntites.get(cpt).getEntite());
      					request.getSession().setAttribute("entiteSelect", listEntites.get(cpt).getLibEntite());
      					listEntites.remove(cpt); 
      					cpt = listEntites.size();
      				}
      				cpt += 1;
      			}
      			
      			ILangueFonctions langFctDAO = new LangueFonctionsDAO();
    			ArrayList<LangueFonctions> list2 = new ArrayList<LangueFonctions>(),listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
          		ArrayList<Fonctions> listFctCourant = new ArrayList<Fonctions>();
          		listFctCourant.addAll(entSelect.getFonctions());
          		cpt = 0;
          		while(cpt < listFctCourant.size()){
          			int cpt1 = 0;
          			while(cpt1 < listFct.size()){
          				if(listFct.get(cpt1).getFonction().getCode_fonction().equals(listFctCourant.get(cpt).getCode_fonction())){
          					list2.add(listFct.get(cpt1));
          				}
          				cpt1 += 1;
          			}
          			cpt += 1;
          		}
          	request.getSession().setAttribute("utilisateurCourant", utilCourant);	
      		request.getSession().setAttribute("listEntites",listEntites);
      		request.getSession().setAttribute("listFonctions", list2);
    		
      		return new ModelAndView("Utilisateurs",modele);     
      }


//---------------------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public ModelAndView selectionProfil(HttpServletRequest request, HttpServletResponse response){
  		
  		if(request.getSession().getAttribute("utilisateur") == null){
  			request.getSession().invalidate();
  			return new ModelAndView("Connexion",null);
  		}
  		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Utilisateur utilCourant = new Utilisateur();
		if(request.getSession().getAttribute("utilisateurCourant") != null)
			utilCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
  		
  		String entite = request.getParameter("entite");
  		ILangueEntite langEntDAO = new LangueEntiteDAO();
		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
  		if((entite != null) && (entite.trim().length() >0)){
  			int cpt = 0;
  			while(cpt < listEntites.size()){
  				if(listEntites.get(cpt).getEntite().getCodeEntite().equals(entite)){
  					utilCourant.setEntite(listEntites.get(cpt).getEntite());
  					request.getSession().setAttribute("entiteSelect", listEntites.get(cpt).getLibEntite());
  					listEntites.remove(cpt); 
  					cpt = listEntites.size();
  				}
  				cpt += 1;
  			}
  		}
  		request.getSession().setAttribute("listEntites",listEntites);
  		
  		
  		String fonction = request.getParameter("fonction");
  		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
			ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
  		if((fonction != null) && (fonction.trim().length() >0)){  			
  			int cpt = 0;
  			while(cpt < listFct.size()){
  				if(listFct.get(cpt).getFonction().getCode_fonction().equals(fonction)){
  					utilCourant.setFonction(listFct.get(cpt).getFonction());
  					request.getSession().setAttribute("fonctionSelect", listFct.get(cpt).getLibFonctions());
  					listFct.remove(cpt);
  					cpt = listFct.size();
  				}
  				cpt += 1;
  			}
  		}
  		request.getSession().setAttribute("listFonctions", listFct);
  		
  		if(request.getSession().getAttribute("motPass") != null)
  			request.getSession().setAttribute("motPass", request.getSession().getAttribute("motPass"));
  		
  		String codeUser = request.getParameter("codeUser");
  		if((codeUser == null) || (codeUser.trim().length()== 0)){
  			request.getSession().setAttribute("utilisateurCourant", utilCourant);
  			modele.put("errorCode", utilisateur.getLangue().getChampObligatoire());
  			return new ModelAndView("Utilisateurs",modele);
  		}
  		else
  			if(codeUser.length() > 10){
  				request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  			modele.put("errorCode",utilisateur.getLangue().getTaillesup()+" "+ 10);
  	  			return new ModelAndView("Utilisateurs",modele);
  			}
  		else{
  			utilCourant.setCodeUtil(codeUser);
  			
  			String etat = request.getParameter("etat");
  			if((etat != null) && (etat.trim().length() > 0)){
  				IUtilisateur utilDAO = new UtilisateurDAO(); 
  				Utilisateur ancienUtil = utilDAO.getUtilisateur(codeUser);
  				if(!ancienUtil.getEtatutil().equals(etat)){
  					utilCourant.setEtatutil(etat); 
  				    utilCourant.setDateDernierEtat(new Date());
  				}
  			}
  			
  			String nom = request.getParameter("nom");
  			if((nom == null) || (nom.trim().length() == 0)){
  				request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  			modele.put("errorNom", utilisateur.getLangue().getChampObligatoire());
  	  			return new ModelAndView("Utilisateurs",modele);
  			}
  			else
  				if(nom.length() > 50){
  					request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  	  			modele.put("errorNom",utilisateur.getLangue().getTaillesup()+" "+ 10);
  	  	  			return new ModelAndView("Utilisateurs",modele);
  				}
  				else{
  					utilCourant.setNomUtil(nom);
  					
  					String langue = request.getParameter("langue");
  					if((langue == null) || (langue.trim().length() == 0)){
  						request.getSession().setAttribute("utilisateurCourant", utilCourant);
  		  	  			modele.put("errorlangue", utilisateur.getLangue().getChampObligatoire());
  		  	  			return new ModelAndView("Utilisateurs",modele);
  					}
  					else{
  						ILangue langDAO = new LangueDAO();
  						ArrayList<Langue> langues = langDAO.allLangue();
  						int cpt = 0;
  						while(cpt < langues.size()){
  							if(langues.get(cpt).getCodeLangue().equals(langue)){
  								utilCourant.setLangue(langues.get(cpt));
  								langues.remove(cpt);
  								cpt = langues.size();
  							}
  							cpt += 1;
  						}
  						request.getSession().setAttribute("langues",langues);
  						
  						String motPass = request.getParameter("motPass");
  						if ((utilCourant.getMotDPass() == null) &&  ((motPass == null) || (motPass.trim().length() == 0))){
  							request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  		  	  			modele.put("errorMDP", utilisateur.getLangue().getChampObligatoire());
  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  						}
  						else{
  							
  							if(utilCourant.getMotDPass() == null){
  								request.getSession().setAttribute("motPass", motPass);
  								utilCourant.setMotDPass(motPass);
  								}
  							
  							String nivPr = request.getParameter("nivPriv");
  							if(nivPr == null){
  								request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  	  		  	  			modele.put("errornivPriv", utilisateur.getLangue().getChampObligatoire());
  	  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  							}
  							else{
  								int nivPriv = 0;
  								try{
  									nivPriv = Integer.parseInt(nivPr);
  								}
  								catch(NumberFormatException ex){}
  								
  								utilCourant.setNiveauPrivileg(nivPriv);
  								
  								String dateDeb = request.getParameter("dateDebValid");
  								if((dateDeb == null) || (dateDeb.trim().length() == 0)){
  									request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  	  	  		  	  			modele.put("errorDateDeb", utilisateur.getLangue().getChampObligatoire());
  	  	  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  								}
  								else{
  									Date dateDebValid = new Date();
  									if(dateDeb.trim().length() == 10){
  										dateDebValid = new Date(dateDeb);
  										request.getSession().setAttribute("dateDeb",dateDeb);
  									}
  									else
  										dateDebValid = new Date((String) request.getSession().getAttribute("dateDeb"));
  										
  									utilCourant.setDateDbutValid(dateDebValid);
  									
  									String dateFin = request.getParameter("dateFinValid");
  									if((dateFin == null) || (dateFin.trim().length() == 0)){
  										request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  	  	  	  		  	  			modele.put("errorDateFin", utilisateur.getLangue().getChampObligatoire());
  	  	  	  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  									}
  									else{
  										Date dateFinValid = new Date();
  										if(dateFin.trim().length() == 10){
  											dateFinValid = new Date(dateFin);
  											request.getSession().setAttribute("dateFin",dateFin);
  										}
  										else
  											dateFinValid = new Date((String) request.getSession().getAttribute("dateFin"));
  										
  										utilCourant.setDateFinValid(dateFinValid);
  										
  										String profil = request.getParameter("profil");
  										if((profil == null) || (profil.trim().length() == 0)){
  											request.getSession().setAttribute("utilisateurCourant", utilCourant);
  											modele.put("errorProfil", utilisateur.getLangue().getChampObligatoire());
  	  	  	  	  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  										}
  										else{
  											ArrayList<LangueProfil> listProfil = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil");
  											  											
  											int bc = 0;
  											UtilisateurProfil utilProfil = new UtilisateurProfil();
  											utilProfil.setUtilisateur(utilCourant);
  											while(bc < listProfil.size()){
  												if(listProfil.get(bc).getProfil().getCodeProfil().equals(profil)){
  													utilProfil.setProfil(listProfil.get(bc).getProfil());
  													request.getSession().setAttribute("profilSelect", listProfil.get(bc));
  													listProfil.remove(bc);
  													bc = listProfil.size();
  												}
  												bc += 1;
  											}
  											
  											modele.put("selectionProfil","ok" );
  											request.getSession().setAttribute("utilProfil", utilProfil);
  											request.getSession().setAttribute("utilisateurCourant", utilCourant);
  											request.getSession().setAttribute("listProfil", listProfil);
  											return new ModelAndView("Utilisateurs",modele);
  										}
  									}
  								}
  							}
  						}
  					}
  				}
  		   }
  		
      }
      
 //-------------------------------------------------------------------------------------------------------------------     
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public ModelAndView ajouterProfil(HttpServletRequest request, HttpServletResponse response){
  		
  		if(request.getSession().getAttribute("utilisateur") == null){
  			request.getSession().invalidate();
  			return new ModelAndView("Connexion",null);
  		}
  		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Utilisateur utilCourant = new Utilisateur();
		if(request.getSession().getAttribute("utilisateurCourant") != null)
			utilCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
		
		if(request.getSession().getAttribute("utilProfil") == null)
			return selectionProfil(request,response);
		
		else{
			
			UtilisateurProfil utilProfil = (UtilisateurProfil) request.getSession().getAttribute("utilProfil");	
			
			String dateDebValProf = request.getParameter("dateDebValProf");
			if((dateDebValProf == null) || (dateDebValProf.trim().length() == 0)){
				modele.put("errorDateDebProf", utilisateur.getLangue().getChampObligatoire());
  		  	  	return new ModelAndView("Utilisateurs",modele);
			}
			else{				
				utilProfil.setDateDebAff(new Date(dateDebValProf));
				
				String dateFinValProf = request.getParameter("dateFinValProf");
				if((dateFinValProf == null) || (dateFinValProf.trim().length() == 0)){
					modele.put("errorDateFinProf", utilisateur.getLangue().getChampObligatoire());
	  		  	  	return new ModelAndView("Utilisateurs",modele);
				}
				else{
					utilProfil.setDateFinAff(new Date(dateFinValProf));
					utilProfil.setEtat("Inactif");
					utilProfil.setNbValidEffect(0);
					utilProfil.setUtilCree(utilisateur);
					utilProfil.setDateCree(new Date());
					
					if(utilCourant.getProfils() == null){
							Collection<UtilisateurProfil> utilprof = new ArrayList<UtilisateurProfil>();
							utilprof.add(utilProfil);
							utilCourant.setProfils(utilprof);
						}
						else
							utilCourant.getProfils().add(utilProfil);
					request.getSession().setAttribute("profilSelect",null);
					return new ModelAndView("Utilisateurs",modele);
				}
			}
		}
  		
      }
    
    
//-----------------------------------------------------------------------------------------------------------------------------    
    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Utilisateur utilCourant = new Utilisateur();
		if(request.getSession().getAttribute("utilisateurCourant") != null)
			utilCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
		
		String entite = request.getParameter("entite");
  		ILangueEntite langEntDAO = new LangueEntiteDAO();
		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
  		if((entite != null) && (entite.trim().length() >0)){
  			int cpt = 0;
  			while(cpt < listEntites.size()){
  				if(listEntites.get(cpt).getEntite().getCodeEntite().equals(entite)){
  					utilCourant.setEntite(listEntites.get(cpt).getEntite());
  					request.getSession().setAttribute("entiteSelect", listEntites.get(cpt).getLibEntite());
  					listEntites.remove(cpt); 
  					cpt = listEntites.size();
  				}
  				cpt += 1;
  			}
  		}
  		request.getSession().setAttribute("listEntites",listEntites);
  		
  		
  		String fonction = request.getParameter("fonction");
  		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
			ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
  		if((fonction != null) && (fonction.trim().length() >0)){  			
  			int cpt = 0;
  			while(cpt < listFct.size()){
  				if(listFct.get(cpt).getFonction().getCode_fonction().equals(fonction)){
  					utilCourant.setFonction(listFct.get(cpt).getFonction());
  					request.getSession().setAttribute("fonctionSelect", listFct.get(cpt).getLibFonctions());
  					listFct.remove(cpt);
  					cpt = listFct.size();
  				}
  				cpt += 1;
  			}
  		}
  		request.getSession().setAttribute("listFonctions", listFct);
  		
  		if(request.getSession().getAttribute("motPass") != null)
  			request.getSession().setAttribute("motPass", request.getSession().getAttribute("motPass"));
  		
  		String codeUser = request.getParameter("codeUser");
  		if((codeUser == null) || (codeUser.trim().length()== 0)){
  			request.getSession().setAttribute("utilisateurCourant", utilCourant);
  			modele.put("errorCode", utilisateur.getLangue().getChampObligatoire());
  			return new ModelAndView("Utilisateurs",modele);
  		}
  		else
  			if(codeUser.length() > 10){
  				request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  			modele.put("errorCode",utilisateur.getLangue().getTaillesup()+" "+ 10);
  	  			return new ModelAndView("Utilisateurs",modele);
  			}
  		else{
  			utilCourant.setCodeUtil(codeUser);
  			
  			String etat = request.getParameter("etat");
  			if((etat != null) && (etat.trim().length() > 0)){
  				IUtilisateur utilDAO = new UtilisateurDAO(); 
  				Utilisateur ancienUtil = utilDAO.getUtilisateur(codeUser);
  				if(!ancienUtil.getEtatutil().equals(etat)){
  					utilCourant.setEtatutil(etat); 
  				    utilCourant.setDateDernierEtat(new Date());
  				}
  			}
  			
  			String nom = request.getParameter("nom");
  			if((nom == null) || (nom.trim().length() == 0)){
  				request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  			modele.put("errorNom", utilisateur.getLangue().getChampObligatoire());
  	  			return new ModelAndView("Utilisateurs",modele);
  			}
  			else
  				if(nom.length() > 50){
  					request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  	  			modele.put("errorNom",utilisateur.getLangue().getTaillesup()+" "+ 10);
  	  	  			return new ModelAndView("Utilisateurs",modele);
  				}
  				else{
  					utilCourant.setNomUtil(nom);
  					
  					String langue = request.getParameter("langue");
  					if((langue == null) || (langue.trim().length() == 0)){
  						request.getSession().setAttribute("utilisateurCourant", utilCourant);
  		  	  			modele.put("errorlangue", utilisateur.getLangue().getChampObligatoire());
  		  	  			return new ModelAndView("Utilisateurs",modele);
  					}
  					else{
  						ILangue langDAO = new LangueDAO();
  						ArrayList<Langue> langues = langDAO.allLangue();
  						int cpt = 0;
  						while(cpt < langues.size()){
  							if(langues.get(cpt).getCodeLangue().equals(langue)){
  								utilCourant.setLangue(langues.get(cpt));
  								langues.remove(cpt);
  								cpt = langues.size();
  							}
  							cpt += 1;
  						}
  						request.getSession().setAttribute("langues",langues);
  						
  						String motPass = request.getParameter("motPass");
  						if( (utilCourant.getMotDPass() == null) && ((motPass == null) || (motPass.trim().length() == 0))){
  							request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  		  	  			modele.put("errorMDP", utilisateur.getLangue().getChampObligatoire());
  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  						}
  						else{
  							
  							if(utilCourant.getMotDPass() == null){
  								request.getSession().setAttribute("motPass", motPass);
  								utilCourant.setMotDPass(motPass);
  								}
  							
  							String nivPr = request.getParameter("nivPriv");
  							if(nivPr == null){
  								request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  	  		  	  			modele.put("errornivPriv", utilisateur.getLangue().getChampObligatoire());
  	  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  							}
  							else{
  								int nivPriv = 0;
  								try{
  									nivPriv = Integer.parseInt(nivPr);
  								}
  								catch(NumberFormatException ex){}
  								
  								utilCourant.setNiveauPrivileg(nivPriv);
  								
  								String dateDeb = request.getParameter("dateDebValid");
  								if((dateDeb == null) || (dateDeb.trim().length() == 0)){
  									request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  	  	  		  	  			modele.put("errorDateDeb", utilisateur.getLangue().getChampObligatoire());
  	  	  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  								}
  								else{
  									Date dateDebValid = new Date();
  									if(dateDeb.trim().length() == 10){
  										dateDebValid = new Date(dateDeb);
  										request.getSession().setAttribute("dateDeb",dateDeb);
  									}
  									else
  										dateDebValid = new Date((String) request.getSession().getAttribute("dateDeb"));
  										
  									utilCourant.setDateDbutValid(dateDebValid);
  									
  									String dateFin = request.getParameter("dateFinValid");
  									if((dateFin == null) || (dateFin.trim().length() == 0)){
  										request.getSession().setAttribute("utilisateurCourant", utilCourant);
  	  	  	  	  		  	  			modele.put("errorDateFin", utilisateur.getLangue().getChampObligatoire());
  	  	  	  	  		  	  			return new ModelAndView("Utilisateurs",modele);
  									}
  									else{
  										Date dateFinValid = new Date();
  										if(dateFin.trim().length() == 10){
  											dateFinValid = new Date(dateFin);
  											request.getSession().setAttribute("dateFin",dateFin);
  										}
  										else
  											dateFinValid = new Date((String) request.getSession().getAttribute("dateFin"));
  										
  										utilCourant.setDateFinValid(dateFinValid);
										
										     if((request.getSession().getAttribute("utilProfil") == null) && (utilCourant.getProfils() == null))
			                                 return selectionProfil(request,response);

											 if(request.getSession().getAttribute("utilProfil") != null){
											 UtilisateurProfil utilProfil = (UtilisateurProfil) request.getSession().getAttribute("utilProfil");
											 modele.put("selectionProfil","ok" );	

											 String dateDebValProf = request.getParameter("dateDebValProf");
											 if((dateDebValProf == null) || (dateDebValProf.trim().length() == 0)){
											 modele.put("errorDateDebProf", utilisateur.getLangue().getChampObligatoire());
											 return new ModelAndView("Utilisateurs",modele);
											 }
											 else{				
											 utilProfil.setDateDebAff(new Date(dateDebValProf));

											 String dateFinValProf = request.getParameter("dateFinValProf");
											 if(((dateFinValProf == null) || (dateFinValProf.trim().length() == 0)) && (utilCourant.getProfils() == null)){
											 modele.put("errorDateFinProf", utilisateur.getLangue().getChampObligatoire());
											 return new ModelAndView("Utilisateurs",modele);
											 }
											 if((dateFinValProf != null) && (dateFinValProf.trim().length() > 0)){					
											 utilProfil.setDateFinAff(new Date(dateFinValProf));
											 utilProfil.setEtat("Inactif");
											 utilProfil.setNbValidEffect(0);
											 utilProfil.setUtilCree(utilisateur);
											 utilProfil.setDateCree(new Date());

											 if(utilCourant.getProfils() == null){
												 ArrayList<UtilisateurProfil> utilprof = new ArrayList<UtilisateurProfil>();
											     utilprof.add(utilProfil);
											     utilCourant.setProfils(utilprof);
											 }
											 else
											 utilCourant.getProfils().add(utilProfil);
											 }
										   }
										}

										IUtilisateur utilDAO = new UtilisateurDAO();
										Utilisateur ancienUtil = utilDAO.getUtilisateur(codeUser);
									
									if( ancienUtil != null){
										utilCourant.setUtilModif(utilisateur);
										utilCourant.setDateDernierModif(new Date());
										java.util.GregorianCalendar calendar = new GregorianCalendar();
										java.util.Date heurDernierModif = calendar.getTime();
										utilCourant.setHeureDernierModif(heurDernierModif); 
										
										request.getSession().setAttribute("utilisateurCourant",utilCourant);
										modele.put("confirmer", "ok");
										return new ModelAndView("Utilisateurs",modele);
										} 
										utilCourant.setEtatutil("Inactif");
										utilCourant.setNbValidEffect(0);
										utilCourant.setUtilCree(utilisateur);
										utilCourant.setDateCree(new Date());
										java.util.GregorianCalendar calendar = new GregorianCalendar();
										java.util.Date heurCree = calendar.getTime();
										utilCourant.setHeureCree(heurCree);
										
										IPreferences prefDAO = new PreferencesDAO();
										Preferences defaltPref = prefDAO.getPreferences();
										utilCourant.setPreference(defaltPref);
										utilDAO.save(utilCourant);
										return init(request,response); 
										
  									}
  								}
  							}
  						}
  					}
  				}
  		   }
		
		
    }
    
//---------------------------------------------------------------------------------------------------------------------------    
    public ModelAndView updateUtilisateur(HttpServletRequest request, HttpServletResponse response){
  		
    	Utilisateur utilCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
    	IUtilisateur utilDAO = new UtilisateurDAO();
    	
    	ArrayList<UtilisateurProfil> profilUtilCourant = new ArrayList<UtilisateurProfil>();
		profilUtilCourant.addAll(utilCourant.getProfils());
		utilCourant.setProfils(null);
		utilDAO.save(utilCourant);
		
		IUtilisateurProfil utilProfilDAO = new UtilisateurProfilDAO();
		utilProfilDAO.save(profilUtilCourant);
  		return init(request,response);
  	}
    
//-----------------------------------------------------------------------------------------------------------------------------      
      public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
  		
  		return init(request,response);
  	}
      
//----------------------------------------------------------------------------------------------------------------------------      
      public ModelAndView supprimer(HttpServletRequest request, HttpServletResponse response){
  		
  		if(request.getSession().getAttribute("utilisateur") == null){
  			request.getSession().invalidate();
  			return new ModelAndView("Connexion",null);
  		}
  		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
  		
  		String codeUser = request.getParameter("codeUser");
  		if((codeUser != null) && (codeUser.trim().length() > 0)){
  			IUtilisateur utilDAO = new UtilisateurDAO();
  			Utilisateur utilCourant = utilDAO.getUtilisateur(codeUser);
  			if(utilCourant != null){
  				utilCourant.setEtatutil("Supprime");
  				utilCourant.setUtilModif(utilisateur);
  				utilCourant.setDateDernierModif(new Date());
  				utilCourant.setDateDernierEtat(new Date());
  				java.util.GregorianCalendar calendar = new GregorianCalendar();
  				java.util.Date heurDernierModif = calendar.getTime();
  				utilCourant.setHeureDernierModif(heurDernierModif);
  				
  				utilDAO.save(utilCourant);
  			}
  		}
  		return init(request,response);
  		}
      
//---------------------------------------------------------------------------------------------------------------------------------      
      @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public ModelAndView rechercher(HttpServletRequest request, HttpServletResponse response){
  		
  		if(request.getSession().getAttribute("utilisateur") == null){
  			request.getSession().invalidate();
  			return new ModelAndView("Connexion",null);
  		}
  		Map modele = new HashMap();
  		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
  		
  		IUtilisateur utilDAO = new UtilisateurDAO();
  		ArrayList<Utilisateur> listUtilisateur = utilDAO.allUtilisateurNoAdminUser();
  		
  	    // Liste des entites
  		ILangueEntite langEntDAO = new LangueEntiteDAO();
  		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
  		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
  		
  		//Liste des fonctions
  		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
  		ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
  		
  		//liste des Profil
  		ILangueProfil langProfDAO = new LangueProfilDAO();
  		ArrayList<LangueProfil> listProfil = langProfDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
  		
  		//liste des langues
  		ILangue langueDAO = new LangueDAO();
  		ArrayList<Langue> listlangues = langueDAO.allLangue();
  		
  		String entite = request.getParameter("entite");
  		if((entite != null) && (entite.trim().length() >0)){
  			ArrayList<Utilisateur> listChercher = new ArrayList<Utilisateur>();
  			int cpt = 0;
  			while(cpt < listUtilisateur.size()){
  				if(listUtilisateur.get(cpt).getEntite().getCodeEntite().equals(entite))
  					listChercher.add(listUtilisateur.get(cpt));
  				cpt += 1;
  			}
  			listUtilisateur = listChercher;
  		}
  		
  		String fonction = request.getParameter("fonction");
  		if((fonction != null) && (fonction.trim().length() >0)){
  			ArrayList<Utilisateur> listChercher = new ArrayList<Utilisateur>();
  			int cpt = 0;
  			while(cpt < listUtilisateur.size()){
  				if(listUtilisateur.get(cpt).getFonction().getCode_fonction().equals(fonction))
  					listChercher.add(listUtilisateur.get(cpt));
  				cpt += 1;
  			}
  			listUtilisateur = listChercher;
  		}
  		
  		String codeUser = request.getParameter("codeUser");
  		if((codeUser != null) && (codeUser.trim().length() >0)){
  			ArrayList<Utilisateur> listChercher = new ArrayList<Utilisateur>();
  			int cpt = 0;
  			while(cpt < listUtilisateur.size()){
  				if(listUtilisateur.get(cpt).getCodeUtil().equals(codeUser))
  					listChercher.add(listUtilisateur.get(cpt));
  				cpt += 1;
  			}
  			listUtilisateur = listChercher;
  		}
  		
  		String nom = request.getParameter("nom");
  		if((nom != null) && (nom.trim().length() >0)){
  			ArrayList<Utilisateur> listChercher = new ArrayList<Utilisateur>();
  			int cpt = 0;
  			while(cpt < listUtilisateur.size()){
  				if(listUtilisateur.get(cpt).getNomUtil().equals(nom))
  					listChercher.add(listUtilisateur.get(cpt));
  				cpt += 1;
  			}
  			listUtilisateur = listChercher;
  		}
  		
  		String langue = request.getParameter("langue");
  		if((langue != null) && (langue.trim().length() > 0)){
  			ArrayList<Utilisateur> listChercher = new ArrayList<Utilisateur>();
  			int cpt = 0;
  			while(cpt < listUtilisateur.size()){
  				if(listUtilisateur.get(cpt).getLangue().getCodeLangue().equals(langue))
  					listChercher.add(listUtilisateur.get(cpt));
  				cpt += 1;
  			}
  			listUtilisateur = listChercher;
  		}
  		
  		String nivPr = request.getParameter("nivPriv");
  		if((nivPr != null) && (nivPr.trim().length() >0)){
  			int nivPriv = 10;
  			try{
  				nivPriv = Integer.parseInt(nivPr);
  			}
  			catch(NumberFormatException ex){}
  			ArrayList<Utilisateur> listChercher = new ArrayList<Utilisateur>();
  			int cpt = 0;
  			while(cpt < listUtilisateur.size()){
  				if(listUtilisateur.get(cpt).getNiveauPrivileg() == nivPriv)
  					listChercher.add(listUtilisateur.get(cpt));
  				cpt += 1;
  			}
  			listUtilisateur = listChercher;
  		}
  		
  		String dateDebVal = request.getParameter("dateDebValid");
  		if((dateDebVal != null) && (dateDebVal.trim().length() > 0)){
  			Date dateDebValid = new Date(dateDebVal);
  			ArrayList<Utilisateur> listChercher = new ArrayList<Utilisateur>();
  			int cpt = 0;
  			while(cpt < listUtilisateur.size()){
  				if(listUtilisateur.get(cpt).getDateDbutValid().getTime()== dateDebValid.getTime() )
  					listChercher.add(listUtilisateur.get(cpt));
  				cpt += 1;
  			}
  			listUtilisateur = listChercher;
  		}
  		
  		String dateFinVal = request.getParameter("dateFinValid");
  		if((dateFinVal != null) && (dateFinVal.trim().length() >0)){
  			Date dateFinValid = new Date(dateFinVal);
  			ArrayList<Utilisateur> listChercher = new ArrayList<Utilisateur>();
  			int cpt = 0;
  			while(cpt < listUtilisateur.size()){
  				if(listUtilisateur.get(cpt).getDateFinValid().getTime()== dateFinValid.getTime() )
  					listChercher.add(listUtilisateur.get(cpt));
  				cpt += 1;
  			}
  			listUtilisateur = listChercher;
  		}
  		
  		if(listUtilisateur.size() > 0){
  			request.getSession().setAttribute("listUtilisateur", listUtilisateur);
  			request.getSession().setAttribute("indiceCourant", 0);
  			
  			Utilisateur utilCourant = listUtilisateur.get(0);
  			request.getSession().setAttribute("utilisateurCourant",utilCourant);
  			
  			if(utilCourant.getEntite() != null){
  				int bc = 0;
  				while(bc < listEntites.size()){
  					if(listEntites.get(bc).getEntite().getCodeEntite().equals(utilCourant.getEntite().getCodeEntite())){
  						request.getSession().setAttribute("entiteSelect",listEntites.get(bc).getLibEntite());
  						listEntites.remove(bc);
  						bc = listEntites.size();
  					}
  					bc += 1;
  				}
  			}
  			
  			if(utilCourant.getFonction() != null){
  				int bc = 0;
  				while(bc < listFct.size()){
  					if(listFct.get(bc).getFonction().getCode_fonction().equals(utilCourant.getFonction().getCode_fonction())){
  						request.getSession().setAttribute("fonctionSelect", listFct.get(bc).getLibFonctions());
  						listFct.remove(bc);
  						bc = listFct.size();
  					}
  					bc +=1;
  				}
  			}
  			
  			//liste des langues
  			int bc1 = 0;
  			while(bc1 < listlangues.size()){
  				if(listlangues.get(bc1).getCodeLangue().equals(utilCourant.getLangue().getCodeLangue())){
  					listlangues.remove(bc1);
  					bc1 = listlangues.size(); 
  				}
  				bc1 += 1;
  			}
  			
  			int bc2 = 0;
  			ArrayList<UtilisateurProfil> listProfUtilCourant = new ArrayList<UtilisateurProfil>();
  			listProfUtilCourant.addAll(utilCourant.getProfils());
  			while(bc2 < listProfil.size()){
  				int bc3 = 0;
  				while(bc3 < listProfUtilCourant.size()){
  					if(listProfUtilCourant.get(bc3).getProfil().getCodeProfil().equals(listProfil.get(bc2).getProfil().getCodeProfil())){
  						listProfil.remove(bc2);
  						bc2 -= 1; bc3 = listProfUtilCourant.size();
  					}
  					bc3 += 1;
  				}
  				bc2 += 1;
  			}
  			SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
  			String dateDeb = formater.format(utilCourant.getDateDbutValid());
  			modele.put("dateDeb", dateDeb);
  			
  			String dateFin = formater.format(utilCourant.getDateFinValid());
  			modele.put("dateFin",dateFin);
  			
  			if(utilCourant.getDateDernierEtat() != null){
  				String dateDernierEtat = formater.format(utilCourant.getDateDernierEtat());
  				modele.put("dateDernierEtat",dateDernierEtat);
  			}
  			
  			if(utilCourant.getDateDernierConnexion() != null){
  				String dateDernierConnex = formater.format(utilCourant.getDateDernierConnexion());
  				modele.put("dateDernierConnex",dateDernierConnex);
  			}
  			
  			if(utilCourant.getDateDernierDeconnexion() != null){
  				String dateDernierDeconnexion = formater.format(utilCourant.getDateDernierDeconnexion());
  				modele.put("dateDernierDeconnexion", dateDernierDeconnexion);
  			}
  			
  			if(utilCourant.getDateDernierModifMDP() != null){
  				String dateDernierModifMDP = formater.format(utilCourant.getDateDernierModifMDP());
  				modele.put("dateDernierModifMDP", dateDernierModifMDP);
  			}
  			
  			if(utilCourant.getDateCree() != null){
  				String dateCree = formater.format(utilCourant.getDateCree());
  				modele.put("dateCree", dateCree);
  			}
  			
  			if(utilCourant.getDateDernierModif() != null){
  				String dateModif = formater.format(utilCourant.getDateDernierModif());
  				modele.put("dateModif",dateModif);
  			}
  			
  			
  			request.getSession().setAttribute("recherche", "ok");
  			
  			if((utilCourant.getEtatutil().equals("Inactif") && (utilCourant.getUtilCree().getCodeUtil().equals(utilisateur.getCodeUtil()))))
  				modele.put("noModifiable", "ok");
  		}
  		
  		modele.put("listEntites",listEntites);
  		modele.put("listFonctions", listFct);
  		request.getSession().setAttribute("listProfil", listProfil);
  		request.getSession().setAttribute("langues",listlangues);
  		return new ModelAndView("Utilisateurs",modele);
      }
      
//--------------------------------------------------------------------------------------------------------------------------      
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView dernier(HttpServletRequest request, HttpServletResponse response){
  		
  		if(request.getSession().getAttribute("utilisateur") == null){
  			request.getSession().invalidate();
  			return new ModelAndView("Connexion",null);
  		}
  		
  		Map modele = new HashMap();
  		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
  		  		
  	    // Liste des entites
  		ILangueEntite langEntDAO = new LangueEntiteDAO();
  		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
  		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
  		
  		//Liste des fonctions
  		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
  		ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
  		
  		//liste des Profil
  		ILangueProfil langProfDAO = new LangueProfilDAO();
  		ArrayList<LangueProfil> listProfil = langProfDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
  		
  		//liste des langues
  		ILangue langueDAO = new LangueDAO();
  		ArrayList<Langue> listlangues = langueDAO.allLangue();
  		
  		if(request.getSession().getAttribute("listUtilisateur") != null){
  			ArrayList<Utilisateur> listUtilisateur = (ArrayList<Utilisateur>) request.getSession().getAttribute("listUtilisateur");
  			if(listUtilisateur.size() > 0){
  	  			request.getSession().setAttribute("listUtilisateur", listUtilisateur);
  	  			int indice = listUtilisateur.size()-1;
  	  			request.getSession().setAttribute("indiceCourant", indice);
  	  			
  	  			Utilisateur utilCourant = listUtilisateur.get(indice);
  	  			request.getSession().setAttribute("utilisateurCourant",utilCourant);
  	  			
  	  			if(utilCourant.getEntite() != null){
  	  				int bc = 0;
  	  				while(bc < listEntites.size()){
  	  					if(listEntites.get(bc).getEntite().getCodeEntite().equals(utilCourant.getEntite().getCodeEntite())){
  	  						request.getSession().setAttribute("entiteSelect",listEntites.get(bc).getLibEntite());
  	  						listEntites.remove(bc);
  	  						bc = listEntites.size();
  	  					}
  	  					bc += 1;
  	  				}
  	  			}
  	  			
  	  			if(utilCourant.getFonction() != null){
  	  				int bc = 0;
  	  				while(bc < listFct.size()){
  	  					if(listFct.get(bc).getFonction().getCode_fonction().equals(utilCourant.getFonction().getCode_fonction())){
  	  						request.getSession().setAttribute("fonctionSelect", listFct.get(bc).getLibFonctions());
  	  						listFct.remove(bc);
  	  						bc = listFct.size();
  	  					}
  	  					bc +=1;
  	  				}
  	  			}
  	  			
  	  			//liste des langues
  	  			int bc1 = 0;
  	  			while(bc1 < listlangues.size()){
  	  				if(listlangues.get(bc1).getCodeLangue().equals(utilCourant.getLangue().getCodeLangue())){
  	  					listlangues.remove(bc1);
  	  					bc1 = listlangues.size(); 
  	  				}
  	  				bc1 += 1;
  	  			}
  	  			
  	  			int bc2 = 0;
  	  			ArrayList<UtilisateurProfil> listProfUtilCourant = new ArrayList<UtilisateurProfil>();
  	  			listProfUtilCourant.addAll(utilCourant.getProfils());
  	  			while(bc2 < listProfil.size()){
  	  				int bc3 = 0;
  	  				while(bc3 < listProfUtilCourant.size()){
  	  					if(listProfUtilCourant.get(bc3).getProfil().getCodeProfil().equals(listProfil.get(bc2).getProfil().getCodeProfil())){
  	  						listProfil.remove(bc2);
  	  						bc2 -= 1; bc3 = listProfUtilCourant.size();
  	  					}
  	  					bc3 += 1;
  	  				}
  	  				bc2 += 1;
  	  			}
  	  			SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
  	  			String dateDeb = formater.format(utilCourant.getDateDbutValid());
  	  			modele.put("dateDeb", dateDeb);
  	  			
  	  			String dateFin = formater.format(utilCourant.getDateFinValid());
  	  			modele.put("dateFin",dateFin);
  	  			
  	  			if(utilCourant.getDateDernierEtat() != null){
  	  				String dateDernierEtat = formater.format(utilCourant.getDateDernierEtat());
  	  				modele.put("dateDernierEtat",dateDernierEtat);
  	  			}
  	  			
  	  			if(utilCourant.getDateDernierConnexion() != null){
  	  				String dateDernierConnex = formater.format(utilCourant.getDateDernierConnexion());
  	  				modele.put("dateDernierConnex",dateDernierConnex);
  	  			}
  	  			
  	  			if(utilCourant.getDateDernierDeconnexion() != null){
  	  				String dateDernierDeconnexion = formater.format(utilCourant.getDateDernierDeconnexion());
  	  				modele.put("dateDernierDeconnexion", dateDernierDeconnexion);
  	  			}
  	  			
  	  			if(utilCourant.getDateDernierModifMDP() != null){
  	  				String dateDernierModifMDP = formater.format(utilCourant.getDateDernierModifMDP());
  	  				modele.put("dateDernierModifMDP", dateDernierModifMDP);
  	  			}
  	  			
  	  			if(utilCourant.getDateCree() != null){
  	  				String dateCree = formater.format(utilCourant.getDateCree());
  	  				modele.put("dateCree", dateCree);
  	  			}
  	  			
  	  			if(utilCourant.getDateDernierModif() != null){
  	  				String dateModif = formater.format(utilCourant.getDateDernierModif());
  	  				modele.put("dateModif",dateModif);
  	  			}
  	  			
  	  			
  	  			request.getSession().setAttribute("recherche", "ok");
  	  			
  	  			if((utilCourant.getEtatutil().equals("Inactif") && (utilCourant.getUtilCree().getCodeUtil().equals(utilisateur.getCodeUtil()))))
  	  				modele.put("noModifiable", "ok");
  	  		}
  		}
  		modele.put("listEntites",listEntites);
  		modele.put("listFonctions", listFct);
  		request.getSession().setAttribute("listProfil", listProfil);
  		request.getSession().setAttribute("langues",listlangues);
  		return new ModelAndView("Utilisateurs",modele);
      }
    
//----------------------------------------------------------------------------------------------------------------------------------    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView premier(HttpServletRequest request, HttpServletResponse response){
  		
  		if(request.getSession().getAttribute("utilisateur") == null){
  			request.getSession().invalidate();
  			return new ModelAndView("Connexion",null);
  		}
  		
  		Map modele = new HashMap();
  		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
  		  		
  	    // Liste des entites
  		ILangueEntite langEntDAO = new LangueEntiteDAO();
  		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
  		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
  		
  		//Liste des fonctions
  		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
  		ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
  		
  		//liste des Profil
  		ILangueProfil langProfDAO = new LangueProfilDAO();
  		ArrayList<LangueProfil> listProfil = langProfDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
  		
  		//liste des langues
  		ILangue langueDAO = new LangueDAO();
  		ArrayList<Langue> listlangues = langueDAO.allLangue();
  		
  		if(request.getSession().getAttribute("listUtilisateur") != null){
  			ArrayList<Utilisateur> listUtilisateur = (ArrayList<Utilisateur>) request.getSession().getAttribute("listUtilisateur");
  			if(listUtilisateur.size() > 0){
  	  			request.getSession().setAttribute("listUtilisateur", listUtilisateur);
  	  			int indice = 0;
  	  			request.getSession().setAttribute("indiceCourant", indice);
  	  			
  	  			Utilisateur utilCourant = listUtilisateur.get(indice);
  	  			request.getSession().setAttribute("utilisateurCourant",utilCourant);
  	  			
  	  			if(utilCourant.getEntite() != null){
  	  				int bc = 0;
  	  				while(bc < listEntites.size()){
  	  					if(listEntites.get(bc).getEntite().getCodeEntite().equals(utilCourant.getEntite().getCodeEntite())){
  	  						request.getSession().setAttribute("entiteSelect",listEntites.get(bc).getLibEntite());
  	  						listEntites.remove(bc);
  	  						bc = listEntites.size();
  	  					}
  	  					bc += 1;
  	  				}
  	  			}
  	  			
  	  			if(utilCourant.getFonction() != null){
  	  				int bc = 0;
  	  				while(bc < listFct.size()){
  	  					if(listFct.get(bc).getFonction().getCode_fonction().equals(utilCourant.getFonction().getCode_fonction())){
  	  						request.getSession().setAttribute("fonctionSelect", listFct.get(bc).getLibFonctions());
  	  						listFct.remove(bc);
  	  						bc = listFct.size();
  	  					}
  	  					bc +=1;
  	  				}
  	  			}
  	  			
  	  			//liste des langues
  	  			int bc1 = 0;
  	  			while(bc1 < listlangues.size()){
  	  				if(listlangues.get(bc1).getCodeLangue().equals(utilCourant.getLangue().getCodeLangue())){
  	  					listlangues.remove(bc1);
  	  					bc1 = listlangues.size(); 
  	  				}
  	  				bc1 += 1;
  	  			}
  	  			
  	  			int bc2 = 0;
  	  			ArrayList<UtilisateurProfil> listProfUtilCourant = new ArrayList<UtilisateurProfil>();
  	  			listProfUtilCourant.addAll(utilCourant.getProfils());
  	  			while(bc2 < listProfil.size()){
  	  				int bc3 = 0;
  	  				while(bc3 < listProfUtilCourant.size()){
  	  					if(listProfUtilCourant.get(bc3).getProfil().getCodeProfil().equals(listProfil.get(bc2).getProfil().getCodeProfil())){
  	  						listProfil.remove(bc2);
  	  						bc2 -= 1; bc3 = listProfUtilCourant.size();
  	  					}
  	  					bc3 += 1;
  	  				}
  	  				bc2 += 1;
  	  			}
  	  			SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
  	  			String dateDeb = formater.format(utilCourant.getDateDbutValid());
  	  			modele.put("dateDeb", dateDeb);
  	  			
  	  			String dateFin = formater.format(utilCourant.getDateFinValid());
  	  			modele.put("dateFin",dateFin);
  	  			
  	  			if(utilCourant.getDateDernierEtat() != null){
  	  				String dateDernierEtat = formater.format(utilCourant.getDateDernierEtat());
  	  				modele.put("dateDernierEtat",dateDernierEtat);
  	  			}
  	  			
  	  			if(utilCourant.getDateDernierConnexion() != null){
  	  				String dateDernierConnex = formater.format(utilCourant.getDateDernierConnexion());
  	  				modele.put("dateDernierConnex",dateDernierConnex);
  	  			}
  	  			
  	  			if(utilCourant.getDateDernierDeconnexion() != null){
  	  				String dateDernierDeconnexion = formater.format(utilCourant.getDateDernierDeconnexion());
  	  				modele.put("dateDernierDeconnexion", dateDernierDeconnexion);
  	  			}
  	  			
  	  			if(utilCourant.getDateDernierModifMDP() != null){
  	  				String dateDernierModifMDP = formater.format(utilCourant.getDateDernierModifMDP());
  	  				modele.put("dateDernierModifMDP", dateDernierModifMDP);
  	  			}
  	  			
  	  			if(utilCourant.getDateCree() != null){
  	  				String dateCree = formater.format(utilCourant.getDateCree());
  	  				modele.put("dateCree", dateCree);
  	  			}
  	  			
  	  			if(utilCourant.getDateDernierModif() != null){
  	  				String dateModif = formater.format(utilCourant.getDateDernierModif());
  	  				modele.put("dateModif",dateModif);
  	  			}
  	  			
  	  			
  	  			request.getSession().setAttribute("recherche", "ok");
  	  			
  	  			if((utilCourant.getEtatutil().equals("Inactif") && (utilCourant.getUtilCree().getCodeUtil().equals(utilisateur.getCodeUtil()))))
  	  				modele.put("noModifiable", "ok");
  	  		}
  		}
  		modele.put("listEntites",listEntites);
  		modele.put("listFonctions", listFct);
  		request.getSession().setAttribute("listProfil", listProfil);
  		request.getSession().setAttribute("langues",listlangues);
  		return new ModelAndView("Utilisateurs",modele);
      }
    
    
//----------------------------------------------------------------------------------------------------------------------------------    
     @SuppressWarnings({ "unchecked", "rawtypes" })
 	public ModelAndView precedant(HttpServletRequest request, HttpServletResponse response){
   		
   		if(request.getSession().getAttribute("utilisateur") == null){
   			request.getSession().invalidate();
   			return new ModelAndView("Connexion",null);
   		}
   		
   		Map modele = new HashMap();
   		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
   		  		
   	    // Liste des entites
   		ILangueEntite langEntDAO = new LangueEntiteDAO();
   		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
   		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
   		
   		//Liste des fonctions
   		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
   		ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
   		
   		//liste des Profil
   		ILangueProfil langProfDAO = new LangueProfilDAO();
   		ArrayList<LangueProfil> listProfil = langProfDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
   		
   		//liste des langues
   		ILangue langueDAO = new LangueDAO();
   		ArrayList<Langue> listlangues = langueDAO.allLangue();
   		
   		if(request.getSession().getAttribute("listUtilisateur") != null){
   			ArrayList<Utilisateur> listUtilisateur = (ArrayList<Utilisateur>) request.getSession().getAttribute("listUtilisateur");
   			if(listUtilisateur.size() > 0){
   	  			request.getSession().setAttribute("listUtilisateur", listUtilisateur);
   	  			int indice = (Integer) request.getSession().getAttribute("indiceCourant");
   	  			if(indice > 0)
   	  				indice -= 1;
   	  			request.getSession().setAttribute("indiceCourant", indice);
   	  			
   	  			Utilisateur utilCourant = listUtilisateur.get(indice);
   	  			request.getSession().setAttribute("utilisateurCourant",utilCourant);
   	  			
   	  			if(utilCourant.getEntite() != null){
   	  				int bc = 0;
   	  				while(bc < listEntites.size()){
   	  					if(listEntites.get(bc).getEntite().getCodeEntite().equals(utilCourant.getEntite().getCodeEntite())){
   	  						request.getSession().setAttribute("entiteSelect",listEntites.get(bc).getLibEntite());
   	  						listEntites.remove(bc);
   	  						bc = listEntites.size();
   	  					}
   	  					bc += 1;
   	  				}
   	  			}
   	  			
   	  			if(utilCourant.getFonction() != null){
   	  				int bc = 0;
   	  				while(bc < listFct.size()){
   	  					if(listFct.get(bc).getFonction().getCode_fonction().equals(utilCourant.getFonction().getCode_fonction())){
   	  						request.getSession().setAttribute("fonctionSelect", listFct.get(bc).getLibFonctions());
   	  						listFct.remove(bc);
   	  						bc = listFct.size();
   	  					}
   	  					bc +=1;
   	  				}
   	  			}
   	  			
   	  			//liste des langues
   	  			int bc1 = 0;
   	  			while(bc1 < listlangues.size()){
   	  				if(listlangues.get(bc1).getCodeLangue().equals(utilCourant.getLangue().getCodeLangue())){
   	  					listlangues.remove(bc1);
   	  					bc1 = listlangues.size(); 
   	  				}
   	  				bc1 += 1;
   	  			}
   	  			
   	  			int bc2 = 0;
   	  			ArrayList<UtilisateurProfil> listProfUtilCourant = new ArrayList<UtilisateurProfil>();
   	  			listProfUtilCourant.addAll(utilCourant.getProfils());
   	  			while(bc2 < listProfil.size()){
   	  				int bc3 = 0;
   	  				while(bc3 < listProfUtilCourant.size()){
   	  					if(listProfUtilCourant.get(bc3).getProfil().getCodeProfil().equals(listProfil.get(bc2).getProfil().getCodeProfil())){
   	  						listProfil.remove(bc2);
   	  						bc2 -= 1; bc3 = listProfUtilCourant.size();
   	  					}
   	  					bc3 += 1;
   	  				}
   	  				bc2 += 1;
   	  			}
   	  			SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
   	  			String dateDeb = formater.format(utilCourant.getDateDbutValid());
   	  			modele.put("dateDeb", dateDeb);
   	  			
   	  			String dateFin = formater.format(utilCourant.getDateFinValid());
   	  			modele.put("dateFin",dateFin);
   	  			
   	  			if(utilCourant.getDateDernierEtat() != null){
   	  				String dateDernierEtat = formater.format(utilCourant.getDateDernierEtat());
   	  				modele.put("dateDernierEtat",dateDernierEtat);
   	  			}
   	  			
   	  			if(utilCourant.getDateDernierConnexion() != null){
   	  				String dateDernierConnex = formater.format(utilCourant.getDateDernierConnexion());
   	  				modele.put("dateDernierConnex",dateDernierConnex);
   	  			}
   	  			
   	  			if(utilCourant.getDateDernierDeconnexion() != null){
   	  				String dateDernierDeconnexion = formater.format(utilCourant.getDateDernierDeconnexion());
   	  				modele.put("dateDernierDeconnexion", dateDernierDeconnexion);
   	  			}
   	  			
   	  			if(utilCourant.getDateDernierModifMDP() != null){
   	  				String dateDernierModifMDP = formater.format(utilCourant.getDateDernierModifMDP());
   	  				modele.put("dateDernierModifMDP", dateDernierModifMDP);
   	  			}
   	  			
   	  			if(utilCourant.getDateCree() != null){
   	  				String dateCree = formater.format(utilCourant.getDateCree());
   	  				modele.put("dateCree", dateCree);
   	  			}
   	  			
   	  			if(utilCourant.getDateDernierModif() != null){
   	  				String dateModif = formater.format(utilCourant.getDateDernierModif());
   	  				modele.put("dateModif",dateModif);
   	  			}
   	  			
   	  			
   	  			request.getSession().setAttribute("recherche", "ok");
   	  			
   	  			if((utilCourant.getEtatutil().equals("Inactif") && (utilCourant.getUtilCree().getCodeUtil().equals(utilisateur.getCodeUtil()))))
   	  				modele.put("noModifiable", "ok");
   	  		}
   		}
   		modele.put("listEntites",listEntites);
   		modele.put("listFonctions", listFct);
   		request.getSession().setAttribute("listProfil", listProfil);
   		request.getSession().setAttribute("langues",listlangues);
   		return new ModelAndView("Utilisateurs",modele);
       }
     
     
   //----------------------------------------------------------------------------------------------------------------------------------    
     @SuppressWarnings({ "unchecked", "rawtypes" })
 	public ModelAndView suivant(HttpServletRequest request, HttpServletResponse response){
   		
   		if(request.getSession().getAttribute("utilisateur") == null){
   			request.getSession().invalidate();
   			return new ModelAndView("Connexion",null);
   		}
   		
   		Map modele = new HashMap();
   		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
   		  		
   	    // Liste des entites
   		ILangueEntite langEntDAO = new LangueEntiteDAO();
   		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
   		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
   		
   		//Liste des fonctions
   		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
   		ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
   		
   		//liste des Profil
   		ILangueProfil langProfDAO = new LangueProfilDAO();
   		ArrayList<LangueProfil> listProfil = langProfDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
   		
   		//liste des langues
   		ILangue langueDAO = new LangueDAO();
   		ArrayList<Langue> listlangues = langueDAO.allLangue();
   		
   		if(request.getSession().getAttribute("listUtilisateur") != null){
   			ArrayList<Utilisateur> listUtilisateur = (ArrayList<Utilisateur>) request.getSession().getAttribute("listUtilisateur");
   			if(listUtilisateur.size() > 0){
   	  			request.getSession().setAttribute("listUtilisateur", listUtilisateur);
   	  			int indice = (Integer) request.getSession().getAttribute("indiceCourant");
   	  			if(indice < listUtilisateur.size()-1)
   	  				indice += 1;
   	  			request.getSession().setAttribute("indiceCourant", indice);
   	  			
   	  			Utilisateur utilCourant = listUtilisateur.get(indice);
   	  			request.getSession().setAttribute("utilisateurCourant",utilCourant);
   	  			
   	  			if(utilCourant.getEntite() != null){
   	  				int bc = 0;
   	  				while(bc < listEntites.size()){
   	  					if(listEntites.get(bc).getEntite().getCodeEntite().equals(utilCourant.getEntite().getCodeEntite())){
   	  						request.getSession().setAttribute("entiteSelect",listEntites.get(bc).getLibEntite());
   	  						listEntites.remove(bc);
   	  						bc = listEntites.size();
   	  					}
   	  					bc += 1;
   	  				}
   	  			}
   	  			
   	  			if(utilCourant.getFonction() != null){
   	  				int bc = 0;
   	  				while(bc < listFct.size()){
   	  					if(listFct.get(bc).getFonction().getCode_fonction().equals(utilCourant.getFonction().getCode_fonction())){
   	  						request.getSession().setAttribute("fonctionSelect", listFct.get(bc).getLibFonctions());
   	  						listFct.remove(bc);
   	  						bc = listFct.size();
   	  					}
   	  					bc +=1;
   	  				}
   	  			}
   	  			
   	  			//liste des langues
   	  			int bc1 = 0;
   	  			while(bc1 < listlangues.size()){
   	  				if(listlangues.get(bc1).getCodeLangue().equals(utilCourant.getLangue().getCodeLangue())){
   	  					listlangues.remove(bc1);
   	  					bc1 = listlangues.size(); 
   	  				}
   	  				bc1 += 1;
   	  			}
   	  			
   	  			int bc2 = 0;
   	  			ArrayList<UtilisateurProfil> listProfUtilCourant = new ArrayList<UtilisateurProfil>();
   	  			listProfUtilCourant.addAll(utilCourant.getProfils());
   	  			while(bc2 < listProfil.size()){
   	  				int bc3 = 0;
   	  				while(bc3 < listProfUtilCourant.size()){
   	  					if(listProfUtilCourant.get(bc3).getProfil().getCodeProfil().equals(listProfil.get(bc2).getProfil().getCodeProfil())){
   	  						listProfil.remove(bc2);
   	  						bc2 -= 1; bc3 = listProfUtilCourant.size();
   	  					}
   	  					bc3 += 1;
   	  				}
   	  				bc2 += 1;
   	  			}
   	  			SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
   	  			String dateDeb = formater.format(utilCourant.getDateDbutValid());
   	  			modele.put("dateDeb", dateDeb);
   	  			
   	  			String dateFin = formater.format(utilCourant.getDateFinValid());
   	  			modele.put("dateFin",dateFin);
   	  			
   	  			if(utilCourant.getDateDernierEtat() != null){
   	  				String dateDernierEtat = formater.format(utilCourant.getDateDernierEtat());
   	  				modele.put("dateDernierEtat",dateDernierEtat);
   	  			}
   	  			
   	  			if(utilCourant.getDateDernierConnexion() != null){
   	  				String dateDernierConnex = formater.format(utilCourant.getDateDernierConnexion());
   	  				modele.put("dateDernierConnex",dateDernierConnex);
   	  			}
   	  			
   	  			if(utilCourant.getDateDernierDeconnexion() != null){
   	  				String dateDernierDeconnexion = formater.format(utilCourant.getDateDernierDeconnexion());
   	  				modele.put("dateDernierDeconnexion", dateDernierDeconnexion);
   	  			}
   	  			
   	  			if(utilCourant.getDateDernierModifMDP() != null){
   	  				String dateDernierModifMDP = formater.format(utilCourant.getDateDernierModifMDP());
   	  				modele.put("dateDernierModifMDP", dateDernierModifMDP);
   	  			}
   	  			
   	  			if(utilCourant.getDateCree() != null){
   	  				String dateCree = formater.format(utilCourant.getDateCree());
   	  				modele.put("dateCree", dateCree);
   	  			}
   	  			
   	  			if(utilCourant.getDateDernierModif() != null){
   	  				String dateModif = formater.format(utilCourant.getDateDernierModif());
   	  				modele.put("dateModif",dateModif);
   	  			}
   	  			
   	  			
   	  			request.getSession().setAttribute("recherche", "ok");
   	  			
   	  			if((utilCourant.getEtatutil().equals("Inactif") && (utilCourant.getUtilCree().getCodeUtil().equals(utilisateur.getCodeUtil()))))
   	  				modele.put("noModifiable", "ok");
   	  		}
   		}
   		modele.put("listEntites",listEntites);
   		modele.put("listFonctions", listFct);
   		request.getSession().setAttribute("listProfil", listProfil);
   		request.getSession().setAttribute("langues",listlangues);
   		return new ModelAndView("Utilisateurs",modele);
       }
     
//------------------------------------------------------------------------------------------------------------------------
      @SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView deleteUtilProfil(HttpServletRequest request, HttpServletResponse response){
     		
     		if(request.getSession().getAttribute("utilisateur") == null){
     			request.getSession().invalidate();
     			return new ModelAndView("Connexion",null);
     		}
     		Map modele = new HashMap();
     		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
     		
     	// Liste des entites
       		ILangueEntite langEntDAO = new LangueEntiteDAO();
       		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
       		listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
       		
       	//Liste des fonctions
       		ILangueFonctions langFctDAO = new LangueFonctionsDAO();
       		ArrayList<LangueFonctions> listFct = langFctDAO.allLangueFonctions(utilisateur.getLangue().getCodeLangue());
     		
       	//liste des Profil
       		ILangueProfil langProfDAO = new LangueProfilDAO();
       		ArrayList<LangueProfil> listProfil = langProfDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
       		
       	//liste des langues
       		ILangue langueDAO = new LangueDAO();
       		ArrayList<Langue> listlangues = langueDAO.allLangue();
       		
     		String utilProfil = request.getParameter("utilProfil");
     		
     		String codeUser = request.getParameter("codeUser");
     		IUtilisateur utilDAO = new UtilisateurDAO();
     		Utilisateur utilCourant = utilDAO.getUtilisateur(codeUser);
    		//if(request.getSession().getAttribute("utilisateurCourant") != null)
    			//utilCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
     		
     		IUtilisateurProfil utilProfDAO = new UtilisateurProfilDAO();
     		UtilisateurProfil utilProf = utilProfDAO.getUtilisateurProfil(utilCourant.getCodeUtil(),utilProfil);
     		utilProfDAO.delete(utilProf);
     		
     		utilCourant.setUtilModif(utilisateur);
     		java.util.GregorianCalendar calendar = new GregorianCalendar();
			java.util.Date heurDernierModif = calendar.getTime();
     		utilCourant.setHeureDernierModif(heurDernierModif);
     		
     		utilDAO.save(utilCourant); 
     		
     		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
	  			String dateDeb = formater.format(utilCourant.getDateDbutValid());
	  			modele.put("dateDeb", dateDeb);
	  			
	  			String dateFin = formater.format(utilCourant.getDateFinValid());
	  			modele.put("dateFin",dateFin);
	  			
	  			if(utilCourant.getDateDernierEtat() != null){
	  				String dateDernierEtat = formater.format(utilCourant.getDateDernierEtat());
	  				modele.put("dateDernierEtat",dateDernierEtat);
	  			}
	  			
	  			if(utilCourant.getDateDernierConnexion() != null){
	  				String dateDernierConnex = formater.format(utilCourant.getDateDernierConnexion());
	  				modele.put("dateDernierConnex",dateDernierConnex);
	  			}
	  			
	  			if(utilCourant.getDateDernierDeconnexion() != null){
	  				String dateDernierDeconnexion = formater.format(utilCourant.getDateDernierDeconnexion());
	  				modele.put("dateDernierDeconnexion", dateDernierDeconnexion);
	  			}
	  			
	  			if(utilCourant.getDateDernierModifMDP() != null){
	  				String dateDernierModifMDP = formater.format(utilCourant.getDateDernierModifMDP());
	  				modele.put("dateDernierModifMDP", dateDernierModifMDP);
	  			}
	  			
	  			if(utilCourant.getDateCree() != null){
	  				String dateCree = formater.format(utilCourant.getDateCree());
	  				modele.put("dateCree", dateCree);
	  			}
	  			
	  			if(utilCourant.getDateDernierModif() != null){
	  				String dateModif = formater.format(utilCourant.getDateDernierModif());
	  				modele.put("dateModif",dateModif);
	  			}
     		
     		if(utilCourant.getEntite() != null){
	  				int bc = 0;
	  				while(bc < listEntites.size()){
	  					if(listEntites.get(bc).getEntite().getCodeEntite().equals(utilCourant.getEntite().getCodeEntite())){
	  						request.getSession().setAttribute("entiteSelect",listEntites.get(bc).getLibEntite());
	  						listEntites.remove(bc);
	  						bc = listEntites.size();
	  					}
	  					bc += 1;
	  				}
	  			}
     		
     		if(utilCourant.getFonction() != null){
	  				int bc = 0;
	  				while(bc < listFct.size()){
	  					if(listFct.get(bc).getFonction().getCode_fonction().equals(utilCourant.getFonction().getCode_fonction())){
	  						request.getSession().setAttribute("fonctionSelect", listFct.get(bc).getLibFonctions());
	  						listFct.remove(bc);
	  						bc = listFct.size();
	  					}
	  					bc +=1;
	  				}
	  			}
     		
     		int bc2 = 0;
	  			ArrayList<UtilisateurProfil> listProfUtilCourant = new ArrayList<UtilisateurProfil>();
	  			listProfUtilCourant.addAll(utilCourant.getProfils());
	  			while(bc2 < listProfil.size()){
	  				int bc3 = 0;
	  				while(bc3 < listProfUtilCourant.size()){
	  					if(listProfUtilCourant.get(bc3).getProfil().getCodeProfil().equals(listProfil.get(bc2).getProfil().getCodeProfil())){
	  						listProfil.remove(bc2);
	  						bc2 -= 1; bc3 = listProfUtilCourant.size();
	  					}
	  					bc3 += 1;
	  				}
	  				bc2 += 1;
	  			}
	  			
	  			int bc1 = 0;
   	  			while(bc1 < listlangues.size()){
   	  				if(listlangues.get(bc1).getCodeLangue().equals(utilCourant.getLangue().getCodeLangue())){
   	  					listlangues.remove(bc1);
   	  					bc1 = listlangues.size(); 
   	  				}
   	  				bc1 += 1;
   	  			}		
   	  			
   	  		request.getSession().setAttribute("utilisateurCourant",utilCourant);	
   	  		modele.put("listEntites",listEntites);
   	   		modele.put("listFonctions", listFct);
   	  		request.getSession().setAttribute("listProfil", listProfil);
   	   		request.getSession().setAttribute("langues",listlangues);
     		return new ModelAndView("Utilisateurs",modele);
      }
//------------------------------------------------------------------------------------------------------------------------      
}
