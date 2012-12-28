package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.dao.FonctionnalitesDAO;
import modele.dao.IFonctionnalites;
import modele.dao.ILangue;
import modele.dao.ILangueFonctionnalites;
import modele.dao.IModule;
import modele.dao.IProgramme;
import modele.dao.LangueDAO;
import modele.dao.LangueFonctionnalitesDAO;
import modele.dao.ModuleDAO;
import modele.dao.ProgrammeDAO;
import modele.metiers.Fonctionnalites;
import modele.metiers.Langue;
import modele.metiers.LangueFonctionnalites;
import modele.metiers.Module;
import modele.metiers.Profil;
import modele.metiers.Programme;
import modele.metiers.Utilisateur;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FonctionnalitesController extends MultiActionController {	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilConnecte = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
		request.getSession().invalidate();
		request.getSession().setAttribute("utilisateur",utilConnecte);
		request.getSession().setAttribute("profilConnecte",profilConnecte);
		ILangue langueDAO = new LangueDAO();
		IModule modulDAO = new ModuleDAO();
		IProgramme progDAO = new ProgrammeDAO();
		modele.put("programmes", progDAO.allProgramme());
		modele.put("modules", modulDAO.allModule());
		modele.put("langues", langueDAO.allLangue());
		modele.put("affLangue", "ok");
		return new ModelAndView("Fonctionnalites",modele);
	}
	
//-------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView ajouterNom(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");		
		
		Fonctionnalites fctionnalite = new Fonctionnalites();
		if(request.getSession().getAttribute("fonctionnaliteCourant") != null)
			fctionnalite = (Fonctionnalites) request.getSession().getAttribute("fonctionnaliteCourant");
		
		ArrayList<LangueFonctionnalites> listLibelle = new ArrayList<LangueFonctionnalites>();		
		ArrayList<Langue>langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		else{
			ILangue langueDAO = new modele.dao.LangueDAO();
			langues = langueDAO.allLangue();
			}
		
		String codeFonctionnalite = request.getParameter("codeFonctionnalite");
		if((codeFonctionnalite == null) || (codeFonctionnalite.trim().length()==0))
			modele.put("errorCode",utilisateur.getLangue().getChampObligatoire());
		else
			if(codeFonctionnalite.trim().length() > 15)
				modele.put("errorCode",utilisateur.getLangue().getTaillesup()+" "+ 15);
		else{
		
		if((fctionnalite.getCodeFonctionnalite() == null) || (!fctionnalite.getCodeFonctionnalite().equals(codeFonctionnalite)))
			fctionnalite.setCodeFonctionnalite(codeFonctionnalite);
		
		String nbValid = request.getParameter("nbValidReq");
		if((nbValid == null) || (nbValid.trim().length() == 0))
			modele.put("errorNbValid",utilisateur.getLangue().getChampObligatoire());
		else{
			int nbValidReq = 0;
			try{
				nbValidReq = Integer.parseInt(nbValid);
			}
			catch(NumberFormatException ex){
				modele.put("errorFormat",utilisateur.getLangue().getFormatInvalide());
				nbValidReq = -1;
				}
			if(nbValidReq != -1){
				fctionnalite.setNbValidReq(nbValidReq);
				
				String codeModule  = request.getParameter("module");
				if((codeModule != null) && (codeModule.trim().length() >0)){
					IModule modulDAO = new ModuleDAO();
					fctionnalite.setModule(modulDAO.getModule(codeModule));
				}
				
				String codeprogramme = request.getParameter("programme");
				if((codeprogramme != null) && (codeprogramme.trim().length()>0)){
					IProgramme progDAO = new ProgrammeDAO();
					fctionnalite.setProgramme(progDAO.getProgramme(codeprogramme));
				}
		
		
		
		if(request.getSession().getAttribute("listLibelle") != null)
			listLibelle = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listLibelle");
		
		String libelle = request.getParameter("libelle");
		if((libelle != null) && (libelle.trim().length() > 0)){
			ILangue langDAO = new LangueDAO();
			Langue langSelect = langDAO.getLangue(request.getParameter("langue"));
			LangueFonctionnalites nouvoLib = new LangueFonctionnalites(langSelect, fctionnalite, libelle);
			listLibelle.add(nouvoLib);
			int cpt = 0;
			while(cpt < langues.size()){
				if(langues.get(cpt).getCodeLangue().equals(langSelect.getCodeLangue())){
					langues.remove(cpt);
					cpt = langues.size();
				}
				else
					cpt += 1;
			}
		}
		}	}}
		request.getSession().setAttribute("fonctionnaliteCourant", fctionnalite);
		request.getSession().setAttribute("listLibelle",listLibelle);
		request.getSession().setAttribute("langues",langues);
		if(langues.size()>0)		
			modele.put("affLangue", "ok");
		IModule modulDAO = new ModuleDAO();
		IProgramme progDAO = new ProgrammeDAO();
		modele.put("programmes", progDAO.allProgramme());
		modele.put("modules", modulDAO.allModule());
		return new ModelAndView("Fonctionnalites",modele);
	}
	
//--------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		ArrayList<LangueFonctionnalites> listLibelle = new ArrayList<LangueFonctionnalites>();
		if(request.getSession().getAttribute("listLibelle") != null)
			listLibelle = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listLibelle");
		
		ArrayList<Langue>langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		else{
			ILangue langueDAO = new modele.dao.LangueDAO();
			langues = langueDAO.allLangue();
			}
		
		Fonctionnalites fctionnalite = new Fonctionnalites();
		
		String codeFonctionnalite = request.getParameter("codeFonctionnalite");
		if((codeFonctionnalite == null) || (codeFonctionnalite.trim().length()==0)){
			request.getSession().setAttribute("fonctionnaliteCourant", fctionnalite);
			modele.put("errorCode",utilisateur.getLangue().getChampObligatoire());
			modele.put("affLangue", "ok");
			modele.put("langues", langues);
			return new ModelAndView("Fonctionnalites",modele);
		}
		else
			if(codeFonctionnalite.trim().length() > 15){
				modele.put("errorCode",utilisateur.getLangue().getTaillesup()+" "+ 15);
				request.getSession().setAttribute("fonctionnaliteCourant", fctionnalite);
				modele.put("affLangue", "ok");
				modele.put("langues", langues);
				return new ModelAndView("Fonctionnalites",modele);
				}
		else{
			if(request.getSession().getAttribute("fonctionnaliteCourant") != null){
				fctionnalite = (Fonctionnalites) request.getSession().getAttribute("fonctionnaliteCourant");
				if((fctionnalite.getCodeFonctionnalite()== null) ||(!fctionnalite.getCodeFonctionnalite().equals(codeFonctionnalite)))
					fctionnalite.setCodeFonctionnalite(codeFonctionnalite);
			}
			else
				fctionnalite.setCodeFonctionnalite(codeFonctionnalite);
			
			String libelle = request.getParameter("libelle");
			if((libelle != null) && (libelle.trim().length() > 0)){
				ILangue langDAO = new LangueDAO();
				Langue langSelect = langDAO.getLangue(request.getParameter("langue"));
				LangueFonctionnalites nouvoLib = new LangueFonctionnalites(langSelect, fctionnalite, libelle);
				listLibelle.add(nouvoLib);
				int cpt = 0;
				while(cpt < langues.size()){
					if(langues.get(cpt).getCodeLangue().equals(langSelect.getCodeLangue())){
						langues.remove(cpt);
						cpt = langues.size();
					}
					else
						cpt += 1;
				}
				request.getSession().setAttribute("langues", langues);
			}
			if(listLibelle.size() == 0){
				request.getSession().setAttribute("fonctionnaliteCourant",fctionnalite);
				modele.put("errorNom",utilisateur.getLangue().getChampObligatoire());
				modele.put("affLangue", "ok");
				modele.put("langues", langues);
				return new ModelAndView("Fonctionnalites",modele);
			}
			else{
				String nbValid = request.getParameter("nbValidReq");
				if((nbValid == null) || (nbValid.trim().length() == 0)){
					request.getSession().setAttribute("fonctionnaliteCourant",fctionnalite);
					modele.put("errorNbValid",utilisateur.getLangue().getChampObligatoire());
					modele.put("affLangue", "ok");
					modele.put("langues", langues);
					return new ModelAndView("Fonctionnalites",modele);
				}
				else{
					int nbValidReq = 0;
					try{
						nbValidReq = Integer.parseInt(nbValid);
					}
					catch(NumberFormatException ex){
						request.getSession().setAttribute("fonctionnaliteCourant",fctionnalite);
						modele.put("errorFormat",utilisateur.getLangue().getFormatInvalide());
						modele.put("affLangue", "ok");
						modele.put("langues", langues);
						return new ModelAndView("Fonctionnalites",modele);
						}
					fctionnalite.setNbValidReq(nbValidReq);
					
					String codeModule  = request.getParameter("module");
					if((codeModule != null) && (codeModule.trim().length() >0)){
						IModule modulDAO = new ModuleDAO();
						fctionnalite.setModule(modulDAO.getModule(codeModule));
					}
					
					String codeprogramme = request.getParameter("programme");
					if((codeprogramme != null) && (codeprogramme.trim().length()>0)){
						IProgramme progDAO = new ProgrammeDAO();
						fctionnalite.setProgramme(progDAO.getProgramme(codeprogramme));
					}
					
					IFonctionnalites fctionnaliteDAO = new FonctionnalitesDAO();
					Fonctionnalites ancienFctionnalite = fctionnaliteDAO.getFonctionnalite(fctionnalite.getCodeFonctionnalite());
					if( ancienFctionnalite == null){
						fctionnalite.setUitlCree(utilisateur);
						fctionnalite.setDateCree(new Date());
						fctionnaliteDAO.save(fctionnalite);
						
						int cpt = 0;
						ILangueFonctionnalites libDAO = new LangueFonctionnalitesDAO();
						while(cpt < listLibelle.size()){
							libDAO.save(listLibelle.get(cpt));
							cpt += 1;
						}
						return init(request,response);
					}
					else{
						fctionnalite.setUitlCree(ancienFctionnalite.getUitlCree());
						fctionnalite.setDateCree(ancienFctionnalite.getDateCree());
						request.getSession().setAttribute("fonctionnaliteCourant",fctionnalite);
						request.getSession().setAttribute("listLibelle",listLibelle);
						modele.put("confirmer", "ok");
						return new ModelAndView("Fonctionnalites",modele);
					}					
					
				}
			}
				
		}
	}
	
//-------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public ModelAndView updateFonctionnalite(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Fonctionnalites fctionnalite = (Fonctionnalites) request.getSession().getAttribute("fonctionnaliteCourant");
		IFonctionnalites fctionnaliteDAO = new FonctionnalitesDAO();
		
		
		
		ILangueFonctionnalites libDAO = new LangueFonctionnalitesDAO();
		libDAO.delete(fctionnalite.getCodeFonctionnalite());
		
		fctionnalite.setUitlModif(utilisateur);
		fctionnalite.setDateModif(new Date());
		fctionnaliteDAO.save(fctionnalite);
		
		int cpt = 0;
		ArrayList<LangueFonctionnalites> listLibelle = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listLibelle");
		while(cpt < listLibelle.size()){
			libDAO.save(listLibelle.get(cpt));
			cpt += 1;
		}
		return init(request,response);
	}
	
//-------------------------------------------------------------------------------------------------------------	
	public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		return init(request,response);
	}
	
//------------------------------------------------------------------------------------------------------------	
	public ModelAndView supprimer(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		String codeFonctionnalite = request.getParameter("codeFonctionnalite");
		if((codeFonctionnalite != null) && (codeFonctionnalite.trim().length() > 0)){
			IFonctionnalites fctionnaliteDAO = new FonctionnalitesDAO();
			Fonctionnalites fctionnalite = fctionnaliteDAO.getFonctionnalite(codeFonctionnalite);
			
			ILangueFonctionnalites langfctionnaliteDAO = new LangueFonctionnalitesDAO();
			langfctionnaliteDAO.delete(codeFonctionnalite);
			fctionnaliteDAO.delete(fctionnalite);			
		}
		return init(request,response);
	}
	
//------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView rechercher(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		IFonctionnalites fctionnaliteDAO = new FonctionnalitesDAO();
		ArrayList<Fonctionnalites> listFctionnalites = fctionnaliteDAO.allFonctionnalites();
		
		IModule modulDAO = new ModuleDAO();
		IProgramme progDAO = new ProgrammeDAO();
		ArrayList<Programme> programmes =progDAO.allProgramme();
		ArrayList<Module> modules = modulDAO.allModule();
		
		
		String codeFonctionnalite = request.getParameter("codeFonctionnalite");
		if((codeFonctionnalite != null) && (codeFonctionnalite.trim().length() > 0)){
			int cpt = 0;
			ArrayList<Fonctionnalites> listChercher = new ArrayList<Fonctionnalites>();
			while(cpt < listFctionnalites.size()){
				if(listFctionnalites.get(cpt).getCodeFonctionnalite().equals(codeFonctionnalite))
					listChercher.add(listFctionnalites.get(cpt));
				cpt += 1;
			}
			
			listFctionnalites = listChercher;
		}
		
		String libelle = request.getParameter("libelle");
		if((libelle != null) && (libelle.trim().length() > 0)){
			String langSelect = request.getParameter("langue");
			int cpt = 0;
			ArrayList<Fonctionnalites> listChercher = new ArrayList<Fonctionnalites>();
			while(cpt < listFctionnalites.size()){
				ArrayList<LangueFonctionnalites> libelles= (ArrayList<LangueFonctionnalites>) listFctionnalites.get(cpt).getLibellefonctionnalite();
				int cpt2 = 0, trouve = 0;
				while(cpt2 < libelles.size()){
					if((libelles.get(cpt2).getLibFonctionnalie().equals(libelles)) && (libelles.get(cpt2).getLangue().getCodeLangue().equals(langSelect))){
						trouve = 1;
						cpt2 = libelles.size();
					}
					cpt2 += 1;
				}
				if(trouve == 1)
					listChercher.add(listFctionnalites.get(cpt));
				cpt += 1;
			}
			
			listFctionnalites = listChercher;
		}
		
		String nbValid = request.getParameter("nbValidReq");
		if((nbValid != null) && (nbValid.trim().length() > 0)){
			int nbValidReq = 0;
			try{
				nbValidReq = Integer.parseInt(nbValid);
			}
			catch(NumberFormatException ex){
				return init(request,response);
				}
			int cpt = 0;
			ArrayList<Fonctionnalites> listChercher = new ArrayList<Fonctionnalites>();
			while(cpt < listFctionnalites.size()){
				if(listFctionnalites.get(cpt).getNbValidReq() == nbValidReq)
					listChercher.add(listFctionnalites.get(cpt));
				cpt += 1;
			}
			
			listFctionnalites = listChercher;
		}
		
		String codeModule  = request.getParameter("module");
		if((codeModule != null) && (codeModule.trim().length() > 0)){
			int cpt = 0;
			ArrayList<Fonctionnalites> listChercher = new ArrayList<Fonctionnalites>();
			while(cpt < listFctionnalites.size()){
				if(listFctionnalites.get(cpt).getModule().getCodeModule().equals(codeModule))
					listChercher.add(listFctionnalites.get(cpt));
				cpt += 1;
			}
			
			listFctionnalites = listChercher;
		}
		
		String codeprogramme = request.getParameter("programme");
		if((codeprogramme != null) && (codeprogramme.trim().length()>0)){
			int cpt = 0;
			ArrayList<Fonctionnalites> listChercher = new ArrayList<Fonctionnalites>();
			while(cpt < listFctionnalites.size()){
				if(listFctionnalites.get(cpt).getProgramme().getCodeProgramme().equals(codeprogramme))
					listChercher.add(listFctionnalites.get(cpt));
				cpt += 1;
			}
			
			listFctionnalites = listChercher;
		}
		Map modele = new HashMap();
		modele.put("recherche", "ok");
		if(listFctionnalites.size() > 0){
			request.getSession().setAttribute("listFctionnalites",listFctionnalites);
			request.getSession().setAttribute("indiceCourant", 0);
			modele.put("fonctionnaliteCourant", listFctionnalites.get(0));
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			
			ArrayList<LangueFonctionnalites> libellesFctCourant = new ArrayList<LangueFonctionnalites>();
			if(listFctionnalites.get(0).getLibellefonctionnalite() != null){
				libellesFctCourant.addAll(listFctionnalites.get(0).getLibellefonctionnalite());
				request.getSession().setAttribute("listLibelle",libellesFctCourant);
				int cpt = 0;
				while(cpt < langues.size()){
					int cpt2 = 0;
					while(cpt2 <libellesFctCourant.size()){
						if(langues.get(cpt).getCodeLangue().equals( libellesFctCourant.get(cpt2).getLangue().getCodeLangue())){
							langues.remove(cpt);
							cpt -=1; cpt2 = libellesFctCourant.size();
						}
						cpt2 +=1;
					}
					cpt +=1;
				}
			}	
			request.getSession().setAttribute("langues", langues);
			if(langues.size()>0){
				modele.put("affLangue", "ok");
			}
			
			int cpt = 0;
			if(listFctionnalites.get(0).getProgramme() != null)
			while(cpt < programmes.size()){
				if(programmes.get(cpt).getCodeProgramme().equals(listFctionnalites.get(0).getProgramme().getCodeProgramme())){
					programmes.remove(cpt);
					cpt = programmes.size();
				}
				cpt += 1;
					
			}
			
			cpt = 0;
			if(listFctionnalites.get(0).getModule() != null)
			while(cpt < modules.size()){
				if(modules.get(cpt).getCodeModule().equals(listFctionnalites.get(0).getModule().getCodeModule())){
					modules.remove(cpt);
					cpt = modules.size();
				}
				cpt += 1;
					
			}
			modele.put("programmes",programmes );
			modele.put("modules",modules);
			return new ModelAndView("Fonctionnalites",modele);
		}
		
		return init(request, response);
	}
	
//------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView premier(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		modele.put("recherche", "ok");
		if(request.getSession().getAttribute("listFctionnalites") != null){;
			ArrayList<Fonctionnalites> listFctionnalites = (ArrayList<Fonctionnalites>) request.getSession().getAttribute("listFctionnalites");
			if(listFctionnalites.size()>0){
				request.getSession().setAttribute("indiceCourant",0 );
				modele.put("fonctionnaliteCourant", listFctionnalites.get(0));
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				
				IModule modulDAO = new ModuleDAO();
				IProgramme progDAO = new ProgrammeDAO();
				ArrayList<Programme> programmes =progDAO.allProgramme();
				ArrayList<Module> modules = modulDAO.allModule();
				
				ArrayList<LangueFonctionnalites> libellesFctCourant = new ArrayList<LangueFonctionnalites>();
				if(listFctionnalites.get(0).getLibellefonctionnalite() != null){
					libellesFctCourant.addAll(listFctionnalites.get(0).getLibellefonctionnalite());
					request.getSession().setAttribute("listLibelle",libellesFctCourant);
					int cpt = 0;
					while(cpt < langues.size()){
						int cpt2 = 0;
						while(cpt2 <libellesFctCourant.size()){
							if(langues.get(cpt).getCodeLangue().equals( libellesFctCourant.get(cpt2).getLangue().getCodeLangue())){
								langues.remove(cpt);
								cpt -=1; cpt2 = libellesFctCourant.size();
							}
							cpt2 +=1;
						}
						cpt +=1;
					}
				}	
				
				int cpt = 0;
				if(listFctionnalites.get(0).getProgramme() != null)
				while(cpt < programmes.size()){
					if(programmes.get(cpt).getCodeProgramme().equals(listFctionnalites.get(0).getProgramme().getCodeProgramme())){
						programmes.remove(cpt);
						cpt = programmes.size();
					}
					cpt += 1;
						
				}
				
				cpt = 0;
				if(listFctionnalites.get(0).getModule() != null)
				while(cpt < modules.size()){
					if(modules.get(cpt).getCodeModule().equals(listFctionnalites.get(0).getModule().getCodeModule())){
						modules.remove(cpt);
						cpt = modules.size();
					}
					cpt += 1;
						
				}
				modele.put("programmes",programmes );
				modele.put("modules",modules);
				request.getSession().setAttribute("langues", langues);
				if(langues.size()>0){
					modele.put("affLangue", "ok");
				}
			}
		}
		return new ModelAndView("Fonctionnalites",modele);
	}
	
//-------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView dernier(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		modele.put("recherche", "ok");
		if(request.getSession().getAttribute("listFctionnalites") != null){
			ArrayList<Fonctionnalites> listFctionnalites = (ArrayList<Fonctionnalites>) request.getSession().getAttribute("listFctionnalites");
			if(listFctionnalites.size()>0){
				request.getSession().setAttribute("indiceCourant",listFctionnalites.size()-1);
				modele.put("fonctionnaliteCourant", listFctionnalites.get(listFctionnalites.size()-1));
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				
				IModule modulDAO = new ModuleDAO();
				IProgramme progDAO = new ProgrammeDAO();
				ArrayList<Programme> programmes =progDAO.allProgramme();
				ArrayList<Module> modules = modulDAO.allModule();
				
				ArrayList<LangueFonctionnalites> libellesFctCourant = new ArrayList<LangueFonctionnalites>();
				if(listFctionnalites.get(listFctionnalites.size()-1).getLibellefonctionnalite() != null){
					libellesFctCourant.addAll(listFctionnalites.get(listFctionnalites.size()-1).getLibellefonctionnalite());
					request.getSession().setAttribute("listLibelle",libellesFctCourant);
					int cpt = 0;
					while(cpt < langues.size()){
						int cpt2 = 0;
						while(cpt2 <libellesFctCourant.size()){
							if(langues.get(cpt).getCodeLangue().equals( libellesFctCourant.get(cpt2).getLangue().getCodeLangue())){
								langues.remove(cpt);
								cpt -=1; cpt2 = libellesFctCourant.size();
							}
							cpt2 +=1;
						}
						cpt +=1;
					}
				}	
				
				int cpt = 0;
				if(listFctionnalites.get(listFctionnalites.size()-1).getProgramme() != null)
				while(cpt < programmes.size()){
					if(programmes.get(cpt).getCodeProgramme().equals(listFctionnalites.get(listFctionnalites.size()-1).getProgramme().getCodeProgramme())){
						programmes.remove(cpt);
						cpt = programmes.size();
					}
					cpt += 1;
						
				}
				
				cpt = 0;
				if(listFctionnalites.get(listFctionnalites.size()-1).getModule() != null)
				while(cpt < modules.size()){
					if(modules.get(cpt).getCodeModule().equals(listFctionnalites.get(listFctionnalites.size()-1).getModule().getCodeModule())){
						modules.remove(cpt);
						cpt = modules.size();
					}
					cpt += 1;
						
				}
				modele.put("programmes",programmes );
				modele.put("modules",modules);
				request.getSession().setAttribute("langues", langues);
				if(langues.size()>0){
					modele.put("affLangue", "ok");
				}
			}
		}
		return new ModelAndView("Fonctionnalites",modele);
	}
	
//---------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView precedant(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		modele.put("recherche", "ok");
		if(request.getSession().getAttribute("listFctionnalites") != null){
			ArrayList<Fonctionnalites> listFctionnalites = (ArrayList<Fonctionnalites>) request.getSession().getAttribute("listFctionnalites");
			if(listFctionnalites.size()>0){
				int indice = (Integer) request.getSession().getAttribute("indiceCourant");
				if(indice > 0)
					indice -= 1;
				request.getSession().setAttribute("indiceCourant",indice);
				modele.put("fonctionnaliteCourant", listFctionnalites.get(indice));
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				
				IModule modulDAO = new ModuleDAO();
				IProgramme progDAO = new ProgrammeDAO();
				ArrayList<Programme> programmes =progDAO.allProgramme();
				ArrayList<Module> modules = modulDAO.allModule();
				
				ArrayList<LangueFonctionnalites> libellesFctCourant = new ArrayList<LangueFonctionnalites>();
				if(listFctionnalites.get(indice).getLibellefonctionnalite() != null){
					libellesFctCourant.addAll(listFctionnalites.get(indice).getLibellefonctionnalite());
					request.getSession().setAttribute("listLibelle",libellesFctCourant);
					int cpt = 0;
					while(cpt < langues.size()){
						int cpt2 = 0;
						while(cpt2 <libellesFctCourant.size()){
							if(langues.get(cpt).getCodeLangue().equals( libellesFctCourant.get(cpt2).getLangue().getCodeLangue())){
								langues.remove(cpt);
								cpt -=1; cpt2 = libellesFctCourant.size();
							}
							cpt2 +=1;
						}
						cpt +=1;
					}
				}	
				
				int cpt = 0;
				if(listFctionnalites.get(indice).getProgramme() != null)
				while(cpt < programmes.size()){
					if(programmes.get(cpt).getCodeProgramme().equals(listFctionnalites.get(indice).getProgramme().getCodeProgramme())){
						programmes.remove(cpt);
						cpt = programmes.size();
					}
					cpt += 1;
						
				}
				
				cpt = 0;
				if(listFctionnalites.get(indice).getModule() != null)
				while(cpt < modules.size()){
					if(modules.get(cpt).getCodeModule().equals(listFctionnalites.get(indice).getModule().getCodeModule())){
						modules.remove(cpt);
						cpt = modules.size();
					}
					cpt += 1;
						
				}
				modele.put("programmes",programmes );
				modele.put("modules",modules);
				request.getSession().setAttribute("langues", langues);
				if(langues.size()>0){
					modele.put("affLangue", "ok");
				}
			}
		}
		return new ModelAndView("Fonctionnalites",modele);
	}
	
//-------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView suivant(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		modele.put("recherche", "ok");
		if(request.getSession().getAttribute("listFctionnalites") != null){
			ArrayList<Fonctionnalites> listFctionnalites = (ArrayList<Fonctionnalites>) request.getSession().getAttribute("listFctionnalites");
			if(listFctionnalites.size()>0){
				int indice = (Integer) request.getSession().getAttribute("indiceCourant");
				if(indice < listFctionnalites.size()-1)
					indice += 1;
				request.getSession().setAttribute("indiceCourant",indice);
				modele.put("fonctionnaliteCourant", listFctionnalites.get(indice));
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				
				IModule modulDAO = new ModuleDAO();
				IProgramme progDAO = new ProgrammeDAO();
				ArrayList<Programme> programmes =progDAO.allProgramme();
				ArrayList<Module> modules = modulDAO.allModule();
				
				ArrayList<LangueFonctionnalites> libellesFctCourant = new ArrayList<LangueFonctionnalites>();
				if(listFctionnalites.get(indice).getLibellefonctionnalite() != null){
					libellesFctCourant.addAll(listFctionnalites.get(indice).getLibellefonctionnalite());
					request.getSession().setAttribute("listLibelle",libellesFctCourant);
					int cpt = 0;
					while(cpt < langues.size()){
						int cpt2 = 0;
						while(cpt2 <libellesFctCourant.size()){
							if(langues.get(cpt).getCodeLangue().equals( libellesFctCourant.get(cpt2).getLangue().getCodeLangue())){
								langues.remove(cpt);
								cpt -=1; cpt2 = libellesFctCourant.size();
							}
							cpt2 +=1;
						}
						cpt +=1;
					}
				}	
				
				int cpt = 0;
				if(listFctionnalites.get(indice).getProgramme() != null)
				while(cpt < programmes.size()){
					if(programmes.get(cpt).getCodeProgramme().equals(listFctionnalites.get(indice).getProgramme().getCodeProgramme())){
						programmes.remove(cpt);
						cpt = programmes.size();
					}
					cpt += 1;
						
				}
				
				cpt = 0;
				if(listFctionnalites.get(indice).getModule() != null)
				while(cpt < modules.size()){
					if(modules.get(cpt).getCodeModule().equals(listFctionnalites.get(indice).getModule().getCodeModule())){
						modules.remove(cpt);
						cpt = modules.size();
					}
					cpt += 1;
						
				}
				modele.put("programmes",programmes );
				modele.put("modules",modules);
				request.getSession().setAttribute("langues", langues);
				if(langues.size()>0){
					modele.put("affLangue", "ok");
				}
			}
		}
		return new ModelAndView("Fonctionnalites",modele);
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView editerLibelle(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		ArrayList<LangueFonctionnalites> listLibelle = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listLibelle");
        
		String codeFonctionnalite = request.getParameter("codeFonctionnalite");
		String langue = request.getParameter("libelle");
		
		if(listLibelle.size() == 1){
			langue = listLibelle.get(0).getLangue().getCodeLangue();
			modele.put("libEdit",listLibelle.get(0));
			listLibelle.remove(0);
		}
		else{
			int cpt = 0;
			while(cpt <listLibelle.size()){
				if(listLibelle.get(cpt).getLangue().getCodeLangue().equals(langue)){
					modele.put("libEdit",listLibelle.get(cpt));				
					listLibelle.remove(cpt);	
					cpt = listLibelle.size();
				}
				cpt += 1;
			}
		}
		
		request.getSession().setAttribute("listLibelle",listLibelle );
		
		ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
		LangueFonctionnalites langFctionnalite = langFctDAO.getLangueFonctionnalite(langue, codeFonctionnalite);
		if(langFctionnalite != null)
		langFctDAO.delete(langFctionnalite);
		
		IFonctionnalites fctionnaliteDAO = new FonctionnalitesDAO();
		Fonctionnalites fctionnalite = fctionnaliteDAO.getFonctionnalite(codeFonctionnalite);
		fctionnalite.setUitlModif(utilisateur);
		fctionnalite.setDateModif(new Date());
		fctionnaliteDAO.save(fctionnalite);
		modele.put("fonctionnaliteCourant",fctionnalite);
		modele.put("affLangue", "ok");
		return new ModelAndView("Fonctionnalites",modele);
	}
	
//--------------------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView deleteLibelle(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		ArrayList<LangueFonctionnalites> listLibelle = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listLibelle");
		ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
        
		String codeFonctionnalite = request.getParameter("codeFonctionnalite");
		String langue = request.getParameter("libelle");
		
		if(listLibelle.size() == 1){
			langue = listLibelle.get(0).getLangue().getCodeLangue();
			langues.add(listLibelle.get(0).getLangue());
			listLibelle.remove(0);
		}
		else{
			int cpt = 0;
			while(cpt <listLibelle.size()){
				if(listLibelle.get(cpt).getLangue().getCodeLangue().equals(langue)){
					langues.add(listLibelle.get(cpt).getLangue());
					listLibelle.remove(cpt);	
					cpt = listLibelle.size();
				}
				cpt += 1;
			}
		}
		
		request.getSession().setAttribute("langues", langues);
		request.getSession().setAttribute("listLibelle",listLibelle );
		
		ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
		LangueFonctionnalites langFctionnalite = langFctDAO.getLangueFonctionnalite(langue, codeFonctionnalite);
		if(langFctionnalite != null)
		langFctDAO.delete(langFctionnalite);
		
		IFonctionnalites fctionnaliteDAO = new FonctionnalitesDAO();
		Fonctionnalites fctionnalite = fctionnaliteDAO.getFonctionnalite(codeFonctionnalite);
		fctionnalite.setUitlModif(utilisateur);
		fctionnalite.setDateModif(new Date());
		fctionnaliteDAO.save(fctionnalite);
		modele.put("fonctionnaliteCourant",fctionnalite);
		modele.put("affLangue", "ok");
		return new ModelAndView("Fonctionnalites",modele);
	}

}
