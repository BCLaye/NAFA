package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.dao.FonctionnalitesDAO;
import modele.dao.FonctionnalitesProfilDAO;
import modele.dao.IFonctionnalites;
import modele.dao.IFonctionnalitesProfil;
import modele.dao.ILangue;
import modele.dao.ILangueFonctionnalites;
import modele.dao.ILangueProfil;
import modele.dao.IMenus;
import modele.dao.IProfil;
import modele.dao.IlangueMenus;
import modele.dao.LangueDAO;
import modele.dao.LangueFonctionnalitesDAO;
import modele.dao.LangueMenusDAO;
import modele.dao.LangueProfilDAO;
import modele.dao.MenusDAO;
import modele.dao.ProfilDAO;
import modele.metiers.Fonctionnalites;
import modele.metiers.FonctionnalitesProfil;
import modele.metiers.Langue;
import modele.metiers.LangueFonctionnalites;
import modele.metiers.LangueMenus;
import modele.metiers.LangueProfil;
import modele.metiers.Menus;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class MenusController extends MultiActionController {
	
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
		
		ILangueProfil profilDAO = new LangueProfilDAO();
		ILangue langDAO = new LangueDAO();
		
		modele.put("affLangue", "ok");
		request.getSession().setAttribute("langues", langDAO.allLangue());
		modele.put("listProfil", profilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue()));
		modele.put("AfficheFCtionnalte","ok");
		modele.put("Affichemenus","ok");
		return new ModelAndView("Menus",modele);
	}
	
//---------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked"})
	public ModelAndView selectProfil(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
		
		Menus menuCourant = new Menus();
		if(request.getSession().getAttribute("menusCourant") != null)
			menuCourant = (Menus) request.getSession().getAttribute("menusCourant");
		
		ILangueProfil profilDAO = new LangueProfilDAO();
		ArrayList<LangueProfil> listProfil = profilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
		ILangue langDAO = new LangueDAO();
		
		String profil = request.getParameter("profil");
		int cpt = 0;
		while(cpt < listProfil.size()){
			if(listProfil.get(cpt).getProfil().getCodeProfil().equals(profil)){
				menuCourant.setProfil(listProfil.get(cpt).getProfil());
				request.getSession().setAttribute("profilSelect", listProfil.get(cpt));
				listProfil.remove(cpt);
				cpt = listProfil.size();
			}
			cpt += 1;
		}
		
		ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
		ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
		
		IFonctionnalitesProfil fctProfilDAO = new FonctionnalitesProfilDAO();
		ArrayList<Fonctionnalites> listFct = fctProfilDAO.AllFonctionnalitesLibres(menuCourant.getProfil().getCodeProfil());
		
		IlangueMenus langMenusDAO = new LangueMenusDAO();
		ArrayList<LangueMenus> listMenus = new ArrayList<LangueMenus>();
		IMenus menusDAO = new MenusDAO();
		ArrayList<Menus> menusLibre = menusDAO.allMenusLibres(profil); 
		cpt = 0;
		while(cpt < menusLibre.size()){
			LangueMenus nouvoLangMenus = new LangueMenus();
			nouvoLangMenus = langMenusDAO.getLangueMenus(utilisateur.getLangue().getCodeLangue(),menusLibre.get(cpt).getCodeMenus());
			listMenus.add(nouvoLangMenus);
			cpt += 1;
		}
		
		
		cpt = 0;
		while(cpt < listFct.size()){
			listFctionnalites.add(langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), listFct.get(cpt).getCodeFonctionnalite()));
			cpt ++;
		}
		
		if(menuCourant.getFonctionnalite() != null)
			modele.put("AfficheFCtionnalte","ok");
		if(menuCourant.getListmenus() != null)
			modele.put("Affichemenus","ok");	
		if((menuCourant.getListmenus() == null) && (menuCourant.getFonctionnalite() == null)){
			modele.put("Affichemenus","ok");
			modele.put("AfficheFCtionnalte","ok");			
		}
		
		ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		if(langues.size()>0)
			modele.put("affLangue", "ok");
		
		
		request.getSession().setAttribute("listMenus", listMenus);
		request.getSession().setAttribute("menusCourant", menuCourant);
		request.getSession().setAttribute("listFctionnalites", listFctionnalites);
		request.getSession().setAttribute("langues",langues);
		request.getSession().setAttribute("listProfil", listProfil);
		return new ModelAndView("Menus",modele);
	}
	
//----------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView selectFonctionnalite(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Menus menuCourant = new Menus();
		if(request.getSession().getAttribute("menusCourant") != null)
			menuCourant = (Menus) request.getSession().getAttribute("menusCourant");
		
		ILangueProfil profilDAO = new LangueProfilDAO();
		ArrayList<LangueProfil> listProfil = profilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
		
		String codeMenus = request.getParameter("codeMenus");
		if((codeMenus != null) && (codeMenus.trim().length()>0)){	
			if(request.getSession().getAttribute("menusCourant") == null){
				IMenus menusDAO = new MenusDAO();
				Menus ancienMenus = menusDAO.getMenus(codeMenus);
				if(ancienMenus != null){
					ArrayList<LangueMenus> listLib = new ArrayList<LangueMenus>();
					listLib.addAll(ancienMenus.getLibelles());
					menuCourant.setLibelles(listLib);
				}
			}
			menuCourant.setCodeMenus(codeMenus);
		}
		
		String profil = request.getParameter("profil");
		int cpt = 0;
		while(cpt < listProfil.size()){
			if(listProfil.get(cpt).getProfil().getCodeProfil().equals(profil)){
				menuCourant.setProfil(listProfil.get(cpt).getProfil());
				request.getSession().setAttribute("profilSelect", listProfil.get(cpt));
				listProfil.remove(cpt);
				cpt = listProfil.size();
			}
			cpt += 1;
		}
		
		ArrayList<LangueFonctionnalites> listFctionnalites = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listFctionnalites");
		String fction = request.getParameter("fction");
	    cpt = 0;
		while(cpt < listFctionnalites.size()){
			if(listFctionnalites.get(cpt).getFonctionnalite().getCodeFonctionnalite().equals(fction)){
				menuCourant.setFonctionnalite(listFctionnalites.get(cpt).getFonctionnalite());
				request.getSession().setAttribute("fctionnaliteSelect", listFctionnalites.get(cpt));
				listFctionnalites.remove(cpt);
				cpt = listFctionnalites.size();
			}
			cpt += 1;
		}
		
		ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		if(langues.size()>0)
			modele.put("affLangue", "ok");
		
		modele.put("AfficheFCtionnalte","ok");
	    request.getSession().setAttribute("menusCourant", menuCourant);
		modele.put("listFctionnalites", listFctionnalites);
		return new ModelAndView("Menus",modele);
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView ajouterMenus(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();		
		Menus menuCourant = new Menus();
		if(request.getSession().getAttribute("menusCourant") != null)
			menuCourant = (Menus) request.getSession().getAttribute("menusCourant");
		
		String codeMenus = request.getParameter("codeMenus");
		if((codeMenus != null) && (codeMenus.trim().length()>0))
			menuCourant.setCodeMenus(codeMenus);
		
		ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		if(langues.size()>0)
			modele.put("affLangue", "ok");
		modele.put("Affichemenus", "ok");
		
		ArrayList<LangueMenus> listMenus = (ArrayList<LangueMenus>) request.getSession().getAttribute("listMenus");
		String menus = request.getParameter("menus");
		if((menus == null) || (menus.trim().length() == 0)){
			request.getSession().setAttribute("menusCourant", menuCourant);			
			return 	new ModelAndView("Menus",modele);
		}
		else{
			IMenus menusDAO = new MenusDAO();
			Menus menusSelect = menusDAO.getMenus(menus);
			
			if(menuCourant.getListmenus() == null){
				ArrayList<Menus> listM = new ArrayList<Menus>();
				listM.add(menusSelect);
				menuCourant.setListmenus(listM);
			}
			else
				menuCourant.getListmenus().add(menusSelect);
			
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getMenus().getCodeMenus().equals(menus)){
					listMenus.remove(cpt);
					cpt = listMenus.size();
				}
				cpt += 1;
			}
			return 	new ModelAndView("Menus",modele);
		}
	}
	
//---------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView ajouterNom(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Menus menuCourant = new Menus();
		if(request.getSession().getAttribute("menusCourant") != null)
			menuCourant = (Menus) request.getSession().getAttribute("menusCourant");
		
		String codeMenus = request.getParameter("codeMenus");
		if((codeMenus != null) && (codeMenus.trim().length()>0))
			menuCourant.setCodeMenus(codeMenus);
		
		ArrayList<LangueMenus> listMenus = (ArrayList<LangueMenus>) request.getSession().getAttribute("listMenus");
		String menus = request.getParameter("menus");
		if((menus != null) && (menus.trim().length() != 0)){
			IMenus menusDAO = new MenusDAO();
			Menus menusSelect = menusDAO.getMenus(menus);
			
			if(menuCourant.getListmenus() == null){
				ArrayList<Menus> listM = new ArrayList<Menus>();
				listM.add(menusSelect);
				menuCourant.setListmenus(listM);
			}
			else
				menuCourant.getListmenus().add(menusSelect);
			
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getMenus().getCodeMenus().equals(menus)){
					listMenus.remove(cpt);
					cpt = listMenus.size();
				}
				cpt += 1;
			}
		}
		
		ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		String libelle = request.getParameter("libelle");
		ILangue langDAO = new LangueDAO();
		if((libelle != null) && (libelle.trim().length() > 0)){
			if(menuCourant.getLibelles() == null){
				ArrayList<LangueMenus> langMenus = new ArrayList<LangueMenus>();
				langMenus.add(new LangueMenus(langDAO.getLangue(request.getParameter("langue")), menuCourant, libelle));
				menuCourant.setLibelles(langMenus);
			}
			else
				menuCourant.getLibelles().add(new LangueMenus(langDAO.getLangue(request.getParameter("langue")), menuCourant, libelle));
			
			int cpt = 0;
			while(cpt < langues.size()){
				if(langues.get(cpt).getCodeLangue().equals(request.getParameter("langue"))){
					langues.remove(cpt);
					cpt = langues.size();
				}
				cpt += 1;
			}
		}
		
		if(menuCourant.getFonctionnalite() != null)
			modele.put("AfficheFCtionnalte","ok");
		if(menuCourant.getListmenus() != null)
			modele.put("Affichemenus","ok");	
		if((menuCourant.getListmenus() == null) && (menuCourant.getFonctionnalite() == null)){
			modele.put("Affichemenus","ok");
			modele.put("AfficheFCtionnalte","ok");			
		}
		
		request.getSession().setAttribute("menusCourant", menuCourant);
		request.getSession().setAttribute("langues",langues);
		if(langues.size()>0)
			modele.put("affLangue", "ok");
	return 	new ModelAndView("Menus",modele);
	}
	
//-------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		
		Menus menuCourant = new Menus();
		if(request.getSession().getAttribute("menusCourant") != null)
			menuCourant = (Menus) request.getSession().getAttribute("menusCourant");
		
		String profil = request.getParameter("profil");
		if((profil == null) || (profil.trim().length() == 0)){
			modele.put("errorProfil", utilisateur.getLangue().getChampObligatoire());
			request.getSession().setAttribute("menusCourant", menuCourant);
			return 	new ModelAndView("Menus",modele);
		}
		else{
			IProfil profDAO = new ProfilDAO();
			menuCourant.setProfil(profDAO.getProfil(profil));
			
			String codeMenus = request.getParameter("codeMenus");
			if((codeMenus == null) || (codeMenus.trim().length() == 0)){
				modele.put("errorCode", utilisateur.getLangue().getChampObligatoire());
				request.getSession().setAttribute("menusCourant", menuCourant);
				return 	new ModelAndView("Menus",modele);
			}
			else{
				menuCourant.setCodeMenus(codeMenus);
				
				String fction = request.getParameter("fction");
				String menus = request.getParameter("menus");
				if((fction == null) || (fction.trim().length()==0))
					if(((menus == null) || (menus.trim().length()== 0)) && (menuCourant.getListmenus() == null)){
						modele.put("Affichemenus","ok");
						modele.put("AfficheFCtionnalte","ok");
						modele.put("errorUrl", utilisateur.getLangue().getChampObligatoire());
						request.getSession().setAttribute("menusCourant", menuCourant);
						return 	new ModelAndView("Menus",modele);
					}
				if((menus != null) && (menus.trim().length()>0)){
					IMenus menusDAO = new MenusDAO();
					Menus menusSelect = menusDAO.getMenus(menus);
					if(menuCourant.getLibelles() == null){
						ArrayList<Menus> list = new ArrayList<Menus>();						
						list.add(menusSelect);
						menuCourant.setListmenus(list);
					}
					else
						menuCourant.getListmenus().add(menusSelect);
				}
				
				if((fction != null) && (fction.trim().length()> 0)){
					IFonctionnalites fct = new FonctionnalitesDAO();
					menuCourant.setFonctionnalite(fct.getFonctionnalite(fction));
				}
				
				String libelle = request.getParameter("libelle");
				if(((libelle == null) || (libelle.trim().length() == 0)) &&(menuCourant.getLibelles() == null)){
					modele.put("errorNom", utilisateur.getLangue().getChampObligatoire());
					request.getSession().setAttribute("menusCourant", menuCourant);
					return 	new ModelAndView("Menus",modele);
				}
				if((libelle != null) && (libelle.trim().length() > 0)){
					ILangue langDAO = new LangueDAO();
					if(menuCourant.getLibelles() == null){
						ArrayList<LangueMenus> langMenus = new ArrayList<LangueMenus>();
						langMenus.add(new LangueMenus(langDAO.getLangue(request.getParameter("langue")), menuCourant, libelle));
						menuCourant.setLibelles(langMenus);
					}
					else
						menuCourant.getLibelles().add(new LangueMenus(langDAO.getLangue(request.getParameter("langue")), menuCourant, libelle));
					
					int bc = 0;
					while(bc < langues.size()){
						if(langues.get(bc).getCodeLangue().equals(request.getParameter("langue"))){
							langues.remove(bc);
							bc = langues.size();
						}
						bc ++;
					}
				}
				
				IMenus menusDAO = new MenusDAO();
				Menus ancienMenus = menusDAO.getMenus(menuCourant.getCodeMenus());
				if(ancienMenus == null){
					menuCourant.setDateCree(new Date());
					menuCourant.setProfilEffectif(0);
					menuCourant.setUtilCree(utilisateur);
					
					if(menuCourant.getFonctionnalite() != null){
						IFonctionnalitesProfil fctProfDAO = new FonctionnalitesProfilDAO();
						FonctionnalitesProfil nouvoFctProf = fctProfDAO.getFonctionnalitesProfil(fction, profil);
						nouvoFctProf.setFonctionnaliteEffective(1);
						fctProfDAO.save(nouvoFctProf);
					}
					
					menusDAO.save(menuCourant);
					return init(request,response);
				}
				else{
					menuCourant.setDateDernierModif(new Date());
					menuCourant.setUtilModif(utilisateur);
					menuCourant.setUtilCree(ancienMenus.getUtilCree());
					menuCourant.setDateCree(ancienMenus.getDateCree());
					
					if((ancienMenus.getFonctionnalite() != null) && (menuCourant.getFonctionnalite() != null) && (!ancienMenus.getFonctionnalite().getCodeFonctionnalite().equals(menuCourant.getFonctionnalite().getCodeFonctionnalite()))){
						IFonctionnalitesProfil fctProfDAO = new FonctionnalitesProfilDAO();
						FonctionnalitesProfil fctProf = fctProfDAO.getFonctionnalitesProfil(ancienMenus.getFonctionnalite().getCodeFonctionnalite(), ancienMenus.getProfil().getCodeProfil());
						fctProf.setFonctionnaliteEffective(0);
						fctProfDAO.save(fctProf);
					}
					request.getSession().setAttribute("menusCourant", menuCourant);
					
					if(menuCourant.getFonctionnalite()!= null)
						modele.put("AfficheFCtionnalte", "ok");
					if(menuCourant.getListmenus()!= null)
						modele.put("Affichemenus", "ok");
					
					request.getSession().setAttribute("langues",langues);
					if(langues.size()>0)
						modele.put("affLangue", "ok");
					
					modele.put("confirmer", "ok");
					return 	new ModelAndView("Menus",modele);
				}
				
			}
		}		
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------	
	public ModelAndView updateMenus(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Menus menuCourant = (Menus) request.getSession().getAttribute("menusCourant");
		IMenus menusDAO = new MenusDAO();
		if(menuCourant.getFonctionnalite() != null){
			IFonctionnalitesProfil fctProfDAO = new FonctionnalitesProfilDAO();
			FonctionnalitesProfil nouvoFctProf = fctProfDAO.getFonctionnalitesProfil(menuCourant.getFonctionnalite().getCodeFonctionnalite(), menuCourant.getProfil().getCodeProfil());
			nouvoFctProf.setFonctionnaliteEffective(1);
			fctProfDAO.save(nouvoFctProf);
		}
		menusDAO.save(menuCourant);		
		
		return init(request,response);
	}
	
//------------------------------------------------------------------------------------------------------------------------------	
	public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
		
		return init(request,response);
	}
	
//----------------------------------------------------------------------------------------------------------------------	
	public ModelAndView supprimer(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		String codeMenus = request.getParameter("codeMenus");
		if((codeMenus != null) && (codeMenus.trim().length() != 0)){
			IMenus menusDAO = new MenusDAO();
			Menus menuCourant = menusDAO.getMenus(codeMenus);
			if(menuCourant.getFonctionnalite() != null){
				IFonctionnalitesProfil fctProfDAO = new FonctionnalitesProfilDAO();
				FonctionnalitesProfil fctProf = fctProfDAO.getFonctionnalitesProfil(menuCourant.getFonctionnalite().getCodeFonctionnalite(), menuCourant.getProfil().getCodeProfil());
				fctProf.setFonctionnaliteEffective(0);
				fctProfDAO.save(fctProf);
			}
			menusDAO.delete(menuCourant);
		}		
		
		return init(request,response);
	}
	
//--------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView rechercher(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		IMenus menusDAO = new MenusDAO();
		ArrayList<Menus> listMenus = menusDAO.allMenus();
		
		String profil = request.getParameter("profil");
		if((profil != null) && (profil.trim().length()!= 0)){
			ArrayList<Menus> listChercher = new ArrayList<Menus>();
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getProfil().getCodeProfil().equals(profil))
					listChercher.add(listMenus.get(cpt));
				cpt ++;
			}
			listMenus = listChercher;
		}
		
		String codeMenus = request.getParameter("codeMenus");
		if((codeMenus!= null) && (codeMenus.trim().length()!=0)){
			ArrayList<Menus> listChercher = new ArrayList<Menus>();
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getCodeMenus().equals(codeMenus))
					listChercher.add(listMenus.get(cpt));
				cpt ++;
			}
			listMenus = listChercher;
		}
		
		String fction = request.getParameter("fction");
		if((fction!= null) && (fction.trim().length()!=0)){
			ArrayList<Menus> listChercher = new ArrayList<Menus>();
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getFonctionnalite().getCodeFonctionnalite().equals(fction))
					listChercher.add(listMenus.get(cpt));
				cpt ++;
			}
			listMenus = listChercher;
		}
		
		String menus = request.getParameter("menus");
		if((menus !=null) && (menus.trim().length()!=0)){
			ArrayList<Menus> listChercher = new ArrayList<Menus>();
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getListmenus()!= null){
					ArrayList<Menus> listMnus = new ArrayList<Menus>();
					listMnus.addAll(listMenus.get(cpt).getListmenus());
					int cpt2 = 0;
					while(cpt2 < listMnus.size()){
						if(listMnus.get(cpt2).getClass().equals(menus)){
							listChercher.add(listMenus.get(cpt));
							cpt2 = listMnus.size();
						}
						cpt2 ++;
					}
				}
				cpt ++;
			}
			listMenus = listChercher;
		}
		
		String libelle = request.getParameter("libelle");
		if((libelle!= null) && (libelle.trim().length()!=0)){
			ArrayList<Menus> listChercher = new ArrayList<Menus>();
			int cpt = 0;
			while(cpt < listMenus.size()){
				ArrayList<LangueMenus> listLib = new ArrayList<LangueMenus>();
				listLib.addAll(listMenus.get(cpt).getLibelles());
				int cpt2 = 0;
				while(cpt2 < listLib.size()){
					if((listLib.get(cpt2).getLibMenus().equals(libelle)) && (listLib.get(cpt2).getLangue().getCodeLangue().equals(request.getParameter("langue")))){
						listChercher.add(listMenus.get(cpt));
						cpt2 = listLib.size();
					}
					cpt2 ++;
				}
				cpt ++;
			}
			listMenus = listChercher;
		}
		
		if(listMenus.size() != 0){
			Menus menuCourant = listMenus.get(0);

			ILangueProfil profilDAO = new LangueProfilDAO();
			ArrayList<LangueProfil> listProfil = profilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
			
			int cpt = 0;
			while(cpt < listProfil.size()){
				if(listProfil.get(cpt).getProfil().getCodeProfil().equals(menuCourant.getProfil().getCodeProfil())){
					request.getSession().setAttribute("profilSelect", listProfil.get(cpt));
					listProfil.remove(cpt);
					cpt = listProfil.size();
				}
				cpt += 1;
			}
			
			if(menuCourant.getFonctionnalite() != null){
				modele.put("AfficheFCtionnalte", "ok");				
				IFonctionnalitesProfil fctProfilDAO = new FonctionnalitesProfilDAO();
				ArrayList<Fonctionnalites> listFct = fctProfilDAO.AllFonctionnalitesLibres(menuCourant.getProfil().getCodeProfil());
				ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
				ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
				cpt = 0;
				while(cpt < listFct.size()){
					listFctionnalites.add(langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), listFct.get(cpt).getCodeFonctionnalite()));
					cpt ++;
				}
				modele.put("fctionnaliteSelect",langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), menuCourant.getFonctionnalite().getCodeFonctionnalite()));
				request.getSession().setAttribute("listFctionnalites",listFctionnalites );
			}
			
			if((menuCourant.getListmenus() != null) && (menuCourant.getListmenus().size() != 0)){
				modele.put("Affichemenus", "ok");			
				IlangueMenus langMenusDAO = new LangueMenusDAO();
				ArrayList<LangueMenus> listMenus2 = new ArrayList<LangueMenus>();
				ArrayList<Menus> menusLibre = menusDAO.allMenusLibres(menuCourant.getProfil().getCodeProfil()); 
				cpt = 0;
				while(cpt < menusLibre.size()){
					if(menusLibre.get(cpt).getCodeMenus().equals(menuCourant.getCodeMenus())){
						menusLibre.remove(cpt);
						cpt = menusLibre.size();
					}
					cpt += 1;
				}
				
				cpt = 0;
				while(cpt < menusLibre.size()){
					LangueMenus nouvoLangMenus = new LangueMenus();
					nouvoLangMenus = langMenusDAO.getLangueMenus(utilisateur.getLangue().getCodeLangue(),menusLibre.get(cpt).getCodeMenus());
					listMenus2.add(nouvoLangMenus);
					cpt += 1;
				}
				request.getSession().setAttribute("listMenus",listMenus2);
			}
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			ArrayList<LangueMenus> libMenus = new ArrayList<LangueMenus>();
			libMenus.addAll(menuCourant.getLibelles());
			cpt = 0;
			while(cpt < libMenus.size()){
				int bc = 0;
				while(bc < langues.size()){
					if(langues.get(bc).getCodeLangue().equals(libMenus.get(cpt).getLangue().getCodeLangue())){
						langues.remove(bc);
						bc = langues.size();
					}
					bc ++;
				}
				cpt ++;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			
			modele.put("recherche", "ok");
			modele.put("menusCourant", menuCourant);
			request.getSession().setAttribute("indiceCourant",0 );
			request.getSession().setAttribute("listMenusRecherche",listMenus );
			request.getSession().setAttribute("listProfil",listProfil );
			request.getSession().setAttribute("langues",langues);
		    return new ModelAndView("Menus",modele);
		}
		
		
		return init(request,response);
	}
	
//---------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView premier(HttpServletRequest request, HttpServletResponse response){
   		
   		if(request.getSession().getAttribute("utilisateur") == null){
   			request.getSession().invalidate();
   			return new ModelAndView("Connexion",null);
   		}
   		
   		Map modele = new HashMap();
   		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
   		
   		if(request.getSession().getAttribute("listMenusRecherche") != null){
   			ArrayList<Menus> listMenus = (ArrayList<Menus>) request.getSession().getAttribute("listMenusRecherche");
   			int indice = 0;
   			Menus menuCourant = listMenus.get(indice);

			ILangueProfil profilDAO = new LangueProfilDAO();
			ArrayList<LangueProfil> listProfil = profilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
			
			int cpt = 0;
			while(cpt < listProfil.size()){
				if(listProfil.get(cpt).getProfil().getCodeProfil().equals(menuCourant.getProfil().getCodeProfil())){
					request.getSession().setAttribute("profilSelect", listProfil.get(cpt));
					listProfil.remove(cpt);
					cpt = listProfil.size();
				}
				cpt += 1;
			}
			
			if(menuCourant.getFonctionnalite() != null){
				modele.put("AfficheFCtionnalte", "ok");				
				IFonctionnalitesProfil fctProfilDAO = new FonctionnalitesProfilDAO();
				ArrayList<Fonctionnalites> listFct = fctProfilDAO.AllFonctionnalitesLibres(menuCourant.getProfil().getCodeProfil());
				ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
				ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
				cpt = 0;
				while(cpt < listFct.size()){
					listFctionnalites.add(langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), listFct.get(cpt).getCodeFonctionnalite()));
					cpt ++;
				}
				modele.put("fctionnaliteSelect",langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), menuCourant.getFonctionnalite().getCodeFonctionnalite()));
				request.getSession().setAttribute("listFctionnalites",listFctionnalites );
			}
			
			if((menuCourant.getListmenus() != null) && (menuCourant.getListmenus().size() != 0)){
				modele.put("Affichemenus", "ok");			
				IlangueMenus langMenusDAO = new LangueMenusDAO();
				ArrayList<LangueMenus> listMenus2 = new ArrayList<LangueMenus>();
				IMenus menusDAO = new MenusDAO();
				ArrayList<Menus> menusLibre = menusDAO.allMenusLibres(menuCourant.getProfil().getCodeProfil()); 
				cpt = 0;
				while(cpt < menusLibre.size()){
					if(menusLibre.get(cpt).getCodeMenus().equals(menuCourant.getCodeMenus())){
						menusLibre.remove(cpt);
						cpt = menusLibre.size();
					}
					cpt += 1;
				}
				
				cpt = 0;
				while(cpt < menusLibre.size()){
					LangueMenus nouvoLangMenus = new LangueMenus();
					nouvoLangMenus = langMenusDAO.getLangueMenus(utilisateur.getLangue().getCodeLangue(),menusLibre.get(cpt).getCodeMenus());
					listMenus2.add(nouvoLangMenus);
					cpt += 1;
				}
				request.getSession().setAttribute("listMenus",listMenus2);
			}
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			ArrayList<LangueMenus> libMenus = new ArrayList<LangueMenus>();
			libMenus.addAll(menuCourant.getLibelles());
			cpt = 0;
			while(cpt < libMenus.size()){
				int bc = 0;
				while(bc < langues.size()){
					if(langues.get(bc).getCodeLangue().equals(libMenus.get(cpt).getLangue().getCodeLangue())){
						langues.remove(bc);
						bc = langues.size();
					}
					bc ++;
				}
				cpt ++;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			
			modele.put("recherche", "ok");
			modele.put("menusCourant", menuCourant);
			request.getSession().setAttribute("indiceCourant",0 );
			request.getSession().setAttribute("listMenusRecherche",listMenus );
			request.getSession().setAttribute("listProfil",listProfil );
			request.getSession().setAttribute("langues",langues);
		    return new ModelAndView("Menus",modele); 			
   		}
   		
   		return init(request, response);
	}
	
//-------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView dernier(HttpServletRequest request, HttpServletResponse response){
   		
   		if(request.getSession().getAttribute("utilisateur") == null){
   			request.getSession().invalidate();
   			return new ModelAndView("Connexion",null);
   		}
   		
   		Map modele = new HashMap();
   		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
   		
   		if(request.getSession().getAttribute("listMenusRecherche") != null){
   			ArrayList<Menus> listMenus = (ArrayList<Menus>) request.getSession().getAttribute("listMenusRecherche");
   			int indice = listMenus.size()-1;
   			Menus menuCourant = listMenus.get(indice);

			ILangueProfil profilDAO = new LangueProfilDAO();
			ArrayList<LangueProfil> listProfil = profilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
			
			int cpt = 0;
			while(cpt < listProfil.size()){
				if(listProfil.get(cpt).getProfil().getCodeProfil().equals(menuCourant.getProfil().getCodeProfil())){
					request.getSession().setAttribute("profilSelect", listProfil.get(cpt));
					listProfil.remove(cpt);
					cpt = listProfil.size();
				}
				cpt += 1;
			}
			
			if(menuCourant.getFonctionnalite() != null){
				modele.put("AfficheFCtionnalte", "ok");				
				IFonctionnalitesProfil fctProfilDAO = new FonctionnalitesProfilDAO();
				ArrayList<Fonctionnalites> listFct = fctProfilDAO.AllFonctionnalitesLibres(menuCourant.getProfil().getCodeProfil());
				ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
				ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
				cpt = 0;
				while(cpt < listFct.size()){
					listFctionnalites.add(langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), listFct.get(cpt).getCodeFonctionnalite()));
					cpt ++;
				}
				modele.put("fctionnaliteSelect",langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), menuCourant.getFonctionnalite().getCodeFonctionnalite()));
				request.getSession().setAttribute("listFctionnalites",listFctionnalites );
			}
			
			if((menuCourant.getListmenus() != null) && (menuCourant.getListmenus().size() != 0)){
				modele.put("Affichemenus", "ok");			
				IlangueMenus langMenusDAO = new LangueMenusDAO();
				ArrayList<LangueMenus> listMenus2 = new ArrayList<LangueMenus>();
				IMenus menusDAO = new MenusDAO();
				ArrayList<Menus> menusLibre = menusDAO.allMenusLibres(menuCourant.getProfil().getCodeProfil()); 
				cpt = 0;
				while(cpt < menusLibre.size()){
					if(menusLibre.get(cpt).getCodeMenus().equals(menuCourant.getCodeMenus())){
						menusLibre.remove(cpt);
						cpt = menusLibre.size();
					}
					cpt += 1;
				}
				
				cpt = 0;
				while(cpt < menusLibre.size()){
					LangueMenus nouvoLangMenus = new LangueMenus();
					nouvoLangMenus = langMenusDAO.getLangueMenus(utilisateur.getLangue().getCodeLangue(),menusLibre.get(cpt).getCodeMenus());
					listMenus2.add(nouvoLangMenus);
					cpt += 1;
				}
				request.getSession().setAttribute("listMenus",listMenus2);
			}
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			ArrayList<LangueMenus> libMenus = new ArrayList<LangueMenus>();
			libMenus.addAll(menuCourant.getLibelles());
			cpt = 0;
			while(cpt < libMenus.size()){
				int bc = 0;
				while(bc < langues.size()){
					if(langues.get(bc).getCodeLangue().equals(libMenus.get(cpt).getLangue().getCodeLangue())){
						langues.remove(bc);
						bc = langues.size();
					}
					bc ++;
				}
				cpt ++;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			
			modele.put("recherche", "ok");
			modele.put("menusCourant", menuCourant);
			request.getSession().setAttribute("indiceCourant",0 );
			request.getSession().setAttribute("listMenusRecherche",listMenus );
			request.getSession().setAttribute("listProfil",listProfil );
			request.getSession().setAttribute("langues",langues);
		    return new ModelAndView("Menus",modele); 			
   		}
   		
   		return init(request, response);
	}
	
//--------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView precedant(HttpServletRequest request, HttpServletResponse response){
   		
   		if(request.getSession().getAttribute("utilisateur") == null){
   			request.getSession().invalidate();
   			return new ModelAndView("Connexion",null);
   		}
   		
   		Map modele = new HashMap();
   		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
   		
   		if(request.getSession().getAttribute("listMenusRecherche") != null){
   			ArrayList<Menus> listMenus = (ArrayList<Menus>) request.getSession().getAttribute("listMenusRecherche");
   			int indice = (Integer) request.getSession().getAttribute("indiceCourant");
   			if(indice > 0)
   				indice -= 1;
   			Menus menuCourant = listMenus.get(indice);

			ILangueProfil profilDAO = new LangueProfilDAO();
			ArrayList<LangueProfil> listProfil = profilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
			
			int cpt = 0;
			while(cpt < listProfil.size()){
				if(listProfil.get(cpt).getProfil().getCodeProfil().equals(menuCourant.getProfil().getCodeProfil())){
					request.getSession().setAttribute("profilSelect", listProfil.get(cpt));
					listProfil.remove(cpt);
					cpt = listProfil.size();
				}
				cpt += 1;
			}
			
			if(menuCourant.getFonctionnalite() != null){
				modele.put("AfficheFCtionnalte", "ok");				
				IFonctionnalitesProfil fctProfilDAO = new FonctionnalitesProfilDAO();
				ArrayList<Fonctionnalites> listFct = fctProfilDAO.AllFonctionnalitesLibres(menuCourant.getProfil().getCodeProfil());
				ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
				ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
				cpt = 0;
				while(cpt < listFct.size()){
					listFctionnalites.add(langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), listFct.get(cpt).getCodeFonctionnalite()));
					cpt ++;
				}
				modele.put("fctionnaliteSelect",langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), menuCourant.getFonctionnalite().getCodeFonctionnalite()));
				request.getSession().setAttribute("listFctionnalites",listFctionnalites );
			}
			
			if((menuCourant.getListmenus() != null) && (menuCourant.getListmenus().size() != 0)){
				modele.put("Affichemenus", "ok");			
				IlangueMenus langMenusDAO = new LangueMenusDAO();
				ArrayList<LangueMenus> listMenus2 = new ArrayList<LangueMenus>();
				IMenus menusDAO = new MenusDAO();
				ArrayList<Menus> menusLibre = menusDAO.allMenusLibres(menuCourant.getProfil().getCodeProfil()); 
				cpt = 0;
				while(cpt < menusLibre.size()){
					if(menusLibre.get(cpt).getCodeMenus().equals(menuCourant.getCodeMenus())){
						menusLibre.remove(cpt);
						cpt = menusLibre.size();
					}
					cpt += 1;
				}
				
				cpt = 0;
				while(cpt < menusLibre.size()){
					LangueMenus nouvoLangMenus = new LangueMenus();
					nouvoLangMenus = langMenusDAO.getLangueMenus(utilisateur.getLangue().getCodeLangue(),menusLibre.get(cpt).getCodeMenus());
					listMenus2.add(nouvoLangMenus);
					cpt += 1;
				}
				request.getSession().setAttribute("listMenus",listMenus2);
			}
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			ArrayList<LangueMenus> libMenus = new ArrayList<LangueMenus>();
			libMenus.addAll(menuCourant.getLibelles());
			cpt = 0;
			while(cpt < libMenus.size()){
				int bc = 0;
				while(bc < langues.size()){
					if(langues.get(bc).getCodeLangue().equals(libMenus.get(cpt).getLangue().getCodeLangue())){
						langues.remove(bc);
						bc = langues.size();
					}
					bc ++;
				}
				cpt ++;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			
			modele.put("recherche", "ok");
			modele.put("menusCourant", menuCourant);
			request.getSession().setAttribute("indiceCourant",indice );
			request.getSession().setAttribute("listMenusRecherche",listMenus );
			request.getSession().setAttribute("listProfil",listProfil );
			request.getSession().setAttribute("langues",langues);
		    return new ModelAndView("Menus",modele); 			
   		}
   		
   		return init(request, response);
	}
	
//-------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView suivant(HttpServletRequest request, HttpServletResponse response){
   		
   		if(request.getSession().getAttribute("utilisateur") == null){
   			request.getSession().invalidate();
   			return new ModelAndView("Connexion",null);
   		}
   		
   		Map modele = new HashMap();
   		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
   		
   		if(request.getSession().getAttribute("listMenusRecherche") != null){
   			ArrayList<Menus> listMenus = (ArrayList<Menus>) request.getSession().getAttribute("listMenusRecherche");
   			int indice = (Integer) request.getSession().getAttribute("indiceCourant");
   			if(indice < listMenus.size()-1)
   				indice += 1;
   			Menus menuCourant = listMenus.get(indice);

			ILangueProfil profilDAO = new LangueProfilDAO();
			ArrayList<LangueProfil> listProfil = profilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
			
			int cpt = 0;
			while(cpt < listProfil.size()){
				if(listProfil.get(cpt).getProfil().getCodeProfil().equals(menuCourant.getProfil().getCodeProfil())){
					request.getSession().setAttribute("profilSelect", listProfil.get(cpt));
					listProfil.remove(cpt);
					cpt = listProfil.size();
				}
				cpt += 1;
			}
			
			if(menuCourant.getFonctionnalite() != null){
				modele.put("AfficheFCtionnalte", "ok");				
				IFonctionnalitesProfil fctProfilDAO = new FonctionnalitesProfilDAO();
				ArrayList<Fonctionnalites> listFct = fctProfilDAO.AllFonctionnalitesLibres(menuCourant.getProfil().getCodeProfil());
				ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
				ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
				cpt = 0;
				while(cpt < listFct.size()){
					listFctionnalites.add(langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), listFct.get(cpt).getCodeFonctionnalite()));
					cpt ++;
				}
				modele.put("fctionnaliteSelect",langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), menuCourant.getFonctionnalite().getCodeFonctionnalite()));
				request.getSession().setAttribute("listFctionnalites",listFctionnalites );
			}
			
			if((menuCourant.getListmenus() != null) && (menuCourant.getListmenus().size() != 0)){
				modele.put("Affichemenus", "ok");			
				IlangueMenus langMenusDAO = new LangueMenusDAO();
				ArrayList<LangueMenus> listMenus2 = new ArrayList<LangueMenus>();
				IMenus menusDAO = new MenusDAO();
				ArrayList<Menus> menusLibre = menusDAO.allMenusLibres(menuCourant.getProfil().getCodeProfil()); 
				cpt = 0;
				while(cpt < menusLibre.size()){
					if(menusLibre.get(cpt).getCodeMenus().equals(menuCourant.getCodeMenus())){
						menusLibre.remove(cpt);
						cpt = menusLibre.size();
					}
					cpt += 1;
				}
				
				cpt = 0;
				while(cpt < menusLibre.size()){
					LangueMenus nouvoLangMenus = new LangueMenus();
					nouvoLangMenus = langMenusDAO.getLangueMenus(utilisateur.getLangue().getCodeLangue(),menusLibre.get(cpt).getCodeMenus());
					listMenus2.add(nouvoLangMenus);
					cpt += 1;
				}
				request.getSession().setAttribute("listMenus",listMenus2);
			}
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			ArrayList<LangueMenus> libMenus = new ArrayList<LangueMenus>();
			libMenus.addAll(menuCourant.getLibelles());
			cpt = 0;
			while(cpt < libMenus.size()){
				int bc = 0;
				while(bc < langues.size()){
					if(langues.get(bc).getCodeLangue().equals(libMenus.get(cpt).getLangue().getCodeLangue())){
						langues.remove(bc);
						bc = langues.size();
					}
					bc ++;
				}
				cpt ++;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			
			modele.put("recherche", "ok");
			modele.put("menusCourant", menuCourant);
			request.getSession().setAttribute("indiceCourant",indice );
			request.getSession().setAttribute("listMenusRecherche",listMenus );
			request.getSession().setAttribute("listProfil",listProfil );
			request.getSession().setAttribute("langues",langues);
		    return new ModelAndView("Menus",modele); 			
   		}
   		
   		return init(request, response);
	}

}
