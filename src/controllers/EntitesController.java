package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.EntiteDAO;
import modele.dao.IEntite;
import modele.dao.ILangue;
import modele.dao.ILangueEntite;
import modele.dao.LangueDAO;
import modele.dao.LangueEntiteDAO;
import modele.metiers.Entite;
import modele.metiers.Langue;
import modele.metiers.LangueEntite;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class EntitesController extends MultiActionController {
	
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
		ILangueEntite langEntite = new LangueEntiteDAO();
		modele.put("langueEntites", langEntite.allLangueEntite(utilConnecte.getLangue().getCodeLangue()));
		modele.put("langues", langueDAO.allLangue());
		modele.put("affLangue", "ok");
		return new ModelAndView("Entites",modele);
	}
	
//-----------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView ajouterNom(HttpServletRequest request, HttpServletResponse response){
		
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Entite entite = new Entite();
		if(request.getSession().getAttribute("entiteCourant") != null)
			entite = (Entite) request.getSession().getAttribute("entiteCourant");
		
		ArrayList<LangueEntite> listLibelle = new ArrayList<LangueEntite>();
		if(request.getSession().getAttribute("listLibelle") != null)
			listLibelle = (ArrayList<LangueEntite>) request.getSession().getAttribute("listLibelle");
		
		ArrayList<Langue>langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		else{
			ILangue langueDAO = new modele.dao.LangueDAO();
			langues = langueDAO.allLangue();
			}
		
		char utilRattach = request.getParameter("utilRattach").charAt(0);
		entite.setUtilRattach(utilRattach);
		
		String codeEntite = request.getParameter("codeEntite");
		if((codeEntite == null) || (codeEntite.trim().length() == 0)){
		    request.getSession().setAttribute("entiteCourant", entite);
			modele.put("errorCode",utilisateur.getLangue().getChampObligatoire());
			modele.put("affLangue", "ok");
			modele.put("langues", langues);
			modele.put("listLibelle", listLibelle);
			ILangueEntite langEntite = new LangueEntiteDAO();
			modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
			return new ModelAndView("Entites",modele);
		}
		else
			if(codeEntite.trim().length() > 5){
				request.getSession().setAttribute("entiteCourant", entite);
				modele.put("errorCode",utilisateur.getLangue().getTaillesup()+" "+ 5);
				modele.put("affLangue", "ok");
				modele.put("langues", langues);
				modele.put("listLibelle", listLibelle);
				ILangueEntite langEntite = new LangueEntiteDAO();
				modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
				return new ModelAndView("Entites",modele);
			}
		else{			
			if((entite.getCodeEntite() == null) || (!entite.getCodeEntite().equals(codeEntite)))
				entite.setCodeEntite(codeEntite);
			
			String niveauOr = request.getParameter("niveauOrg");
			if((niveauOr == null) || (niveauOr.trim().length() == 0)){
				request.getSession().setAttribute("entiteCourant", entite);
				modele.put("errorNbValid",utilisateur.getLangue().getChampObligatoire());
				modele.put("affLangue", "ok");
				modele.put("langues", langues);
				modele.put("listLibelle", listLibelle);
				ILangueEntite langEntite = new LangueEntiteDAO();
				modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
				return new ModelAndView("Entites",modele);
			}
			else{
				int niveauOrg = 0;
				try{
					niveauOrg = Integer.parseInt(niveauOr);
				}
				catch(NumberFormatException ex){
					request.getSession().setAttribute("entiteCourant", entite);
					modele.put("errorFormat",utilisateur.getLangue().getFormatInvalide());
					modele.put("affLangue", "ok");
					modele.put("langues", langues);
					modele.put("listLibelle", listLibelle);
					ILangueEntite langEntite = new LangueEntiteDAO();
					modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
					return new ModelAndView("Entites",modele);
					}
				entite.setNiveauOrg(niveauOrg);
					
				String entiteRattach = request.getParameter("entiteRattach");
				if((entiteRattach != null) && (entiteRattach.trim().length()> 0)){
					ILangueEntite langEntite = new LangueEntiteDAO();
					if(entiteRattach.equals(entite.getCodeEntite())){
						request.getSession().setAttribute("entiteCourant", entite);
						modele.put("errorEntiteRattach",utilisateur.getLangue().getRelationImpossible());	
						modele.put("affLangue", "ok");
						modele.put("langues", langues);		
						modele.put("listLibelle", listLibelle);
						modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
						return new ModelAndView("Entites",modele);
					}
						IEntite entiteDAO = new EntiteDAO();
						entite.setEntiteRattach(entiteDAO.getEntite(entiteRattach));
						modele.put("entiteRattach", langEntite.getLangueEntite(utilisateur.getLangue().getCodeLangue(), entiteRattach));
				}
				else
					entite.setEntiteRattach(null);	
				
				
				String libelle = request.getParameter("libelle");
				if((libelle != null) && (libelle.trim().length() > 0)){
					ILangue langDAO = new LangueDAO();
					Langue langSelect = langDAO.getLangue(request.getParameter("langue"));
					LangueEntite nouvoLib = new LangueEntite(langSelect, entite, libelle);
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
					request.getSession().setAttribute("entiteCourant", entite);
					request.getSession().setAttribute("listLibelle",listLibelle);
					request.getSession().setAttribute("langues",langues);
					modele.put("listLibelle", listLibelle);
					if(langues.size()>0)		
						modele.put("affLangue", "ok");
					return new ModelAndView("Entites",modele);
					
				}
			}
			
			}
		request.getSession().setAttribute("entiteCourant", entite);	
		if(langues.size()>0)	
			modele.put("affLangue", "ok");
		modele.put("listLibelle", listLibelle);
		ILangueEntite langEntite = new LangueEntiteDAO();
		modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
		return new ModelAndView("Entites",modele);
	}
	
//----------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Entite entite = new Entite();
		if(request.getSession().getAttribute("entiteCourant") != null)
			entite = (Entite) request.getSession().getAttribute("entiteCourant");
		
		ArrayList<LangueEntite> listLibelle = new ArrayList<LangueEntite>();
		ArrayList<Langue>langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		else{
			ILangue langueDAO = new modele.dao.LangueDAO();
			langues = langueDAO.allLangue();
			}
		
		char utilRattach = request.getParameter("utilRattach").charAt(0);
		entite.setUtilRattach(utilRattach);
		
		String codeEntite = request.getParameter("codeEntite");
		if((codeEntite == null) || (codeEntite.trim().length() == 0)){
		    request.getSession().setAttribute("entiteCourant", entite);
			modele.put("errorCode",utilisateur.getLangue().getChampObligatoire());
			modele.put("affLangue", "ok");
			modele.put("langues", langues);
			ILangueEntite langEntite = new LangueEntiteDAO();
			modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
			return new ModelAndView("Entites",modele);
		}
		else
			if(codeEntite.trim().length() > 5){
				request.getSession().setAttribute("entiteCourant", entite);
				modele.put("errorCode",utilisateur.getLangue().getTaillesup()+" "+ 5);
				modele.put("affLangue", "ok");
				modele.put("langues", langues);
				modele.put("listLibelle", listLibelle);
				ILangueEntite langEntite = new LangueEntiteDAO();
				modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
				return new ModelAndView("Entites",modele);
			}
		else{			
			if((entite.getCodeEntite() == null) || (!entite.getCodeEntite().equals(codeEntite)))
				entite.setCodeEntite(codeEntite);
			
			String niveauOr = request.getParameter("niveauOrg"); 
			if((niveauOr == null) || (niveauOr.trim().length() == 0)){
				request.getSession().setAttribute("entiteCourant", entite);
				modele.put("errorNbValid",utilisateur.getLangue().getChampObligatoire());
				modele.put("affLangue", "ok");
				modele.put("langues", langues);
				ILangueEntite langEntite = new LangueEntiteDAO();
				modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
				return new ModelAndView("Entites",modele);
			}
			else{
				int niveauOrg = 0;
				try{
					niveauOrg = Integer.parseInt(niveauOr);
				}
				catch(NumberFormatException ex){
					request.getSession().setAttribute("entiteCourant", entite);
					modele.put("errorFormat",utilisateur.getLangue().getFormatInvalide());
					modele.put("affLangue", "ok");
					modele.put("langues", langues);
					ILangueEntite langEntite = new LangueEntiteDAO();
					modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
					return new ModelAndView("Entites",modele);
					}
				entite.setNiveauOrg(niveauOrg);
					
				String entiteRattach = request.getParameter("entiteRattach");
				if((entiteRattach != null) && (entiteRattach.trim().length()> 0)){
					ILangueEntite langEntite = new LangueEntiteDAO();
					if(entiteRattach.equals(entite.getCodeEntite())){
						request.getSession().setAttribute("entiteCourant", entite);
						modele.put("errorEntiteRattach",utilisateur.getLangue().getRelationImpossible()); 	
						modele.put("affLangue", "ok");
						modele.put("langues", langues);						
						modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
						return new ModelAndView("Entites",modele);
					}
						IEntite entiteDAO = new EntiteDAO();
						entite.setEntiteRattach(entiteDAO.getEntite(entiteRattach));
						modele.put("entiteRattach", langEntite.getLangueEntite(utilisateur.getLangue().getCodeLangue(), codeEntite));
					}
					else
						entite.setEntiteRattach(null);
					
					if(request.getSession().getAttribute("listLibelle") != null)
						listLibelle = (ArrayList<LangueEntite>) request.getSession().getAttribute("listLibelle");
					
					String libelle = request.getParameter("libelle");
					if((libelle != null) && (libelle.trim().length() > 0)){
						ILangue langDAO = new LangueDAO();
						Langue langSelect = langDAO.getLangue(request.getParameter("langue"));
						LangueEntite nouvoLib = new LangueEntite(langSelect, entite, libelle);
						listLibelle.add(nouvoLib);
						}
					
					if(listLibelle.size() == 0){
						request.getSession().setAttribute("entiteCourant", entite);
						modele.put("errorNom",utilisateur.getLangue().getChampObligatoire());
						modele.put("affLangue", "ok");
						modele.put("langues", langues);
						ILangueEntite langEntite = new LangueEntiteDAO();
						modele.put("langueEntites", langEntite.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
						return new ModelAndView("Entites",modele);
					}
						
						
						IEntite entiteDAO = new EntiteDAO();
						Entite ancienneEntite = entiteDAO.getEntite(entite.getCodeEntite());
						if(ancienneEntite == null){
							entite.setUtilCree(utilisateur);
							entite.setDateCree(new Date());
							entiteDAO.save(entite);							
							
							int cpt = 0;
							ILangueEntite langEntiteDAO = new LangueEntiteDAO();
							while( cpt < listLibelle.size()){
								langEntiteDAO.save(listLibelle.get(cpt));
								cpt += 1;
							}
							return init(request,response);
						}
						else{
							entite.setUtilCree(ancienneEntite.getUtilCree());
							entite.setDateCree(ancienneEntite.getDateCree());
							request.getSession().setAttribute("entiteCourant",entite);
							request.getSession().setAttribute("listLibelle",listLibelle);
							modele.put("confirmer", "ok");
							return new ModelAndView("Entites",modele);
						}			
			}				
		}
	}
	
//-----------------------------------------------------------------------------------------------------	
	@SuppressWarnings("unchecked")
	public ModelAndView updateEntites(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Entite entite = (Entite) request.getSession().getAttribute("entiteCourant");
		
		ILangueEntite langEntiteDAO = new LangueEntiteDAO();
		
		entite.setUtilModif(utilisateur);
		entite.setDateDernierModif(new Date());
		IEntite entiteDAO = new EntiteDAO();
		entiteDAO.save(entite);
		
		int cpt = 0;
		ArrayList<LangueEntite> listLibelle = (ArrayList<LangueEntite>) request.getSession().getAttribute("listLibelle");
		while(cpt < listLibelle.size()){
			langEntiteDAO.save(listLibelle.get(cpt));
			cpt += 1;
		}
		return init(request,response);
	}
	
	
//-----------------------------------------------------------------------------------------------------	
	public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
		
		return init(request,response);
	}
	
//------------------------------------------------------------------------------------------------------	
	public ModelAndView supprimer(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		String codeEntite = request.getParameter("codeEntite");
		if((codeEntite != null) && (codeEntite.trim().length()> 0)){
			IEntite entDAO = new EntiteDAO();
			Entite entite = entDAO.getEntite(codeEntite);						
			entDAO.delete(entite);
		}
		return init(request,response);
	}
	
//------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView rechercher(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		IEntite entiteDAO = new EntiteDAO();
		ArrayList<Entite> listEntites = entiteDAO.allEntite();
		
		String codeEntite = request.getParameter("codeEntite");
		if((codeEntite != null) && (codeEntite.trim().length() >0)){
			int cpt = 0;
			ArrayList<Entite> listChercher = new ArrayList<Entite>();
			while(cpt < listEntites.size()){
				if(listEntites.get(cpt).getCodeEntite().equals(codeEntite))
					listChercher.add(listEntites.get(cpt));
				cpt +=1;
			}
			listEntites = listChercher;
		}
		
		String niveauOr = request.getParameter("niveauOrg");
		if((niveauOr != null) && (niveauOr.trim().length() > 0)){
			int niveauOrg = 0;
			try{
				niveauOrg = Integer.parseInt(niveauOr);
			}
			catch(NumberFormatException ex){
				init(request,response);
				}
		int cpt = 0;
		ArrayList<Entite> listChercher = new ArrayList<Entite>();
		while(cpt < listEntites.size()){
			if(listEntites.get(cpt).getNiveauOrg() == niveauOrg)
				listChercher.add(listEntites.get(cpt));
			cpt +=1;
		}
		listEntites = listChercher;
		}
		
		/*if(request.getParameter("utilRattach") != null){
		char utilRattach = request.getParameter("utilRattach").charAt(0);
		int cpt = 0;
		ArrayList<Entite> listChercher = new ArrayList<Entite>();
		while(cpt < listEntites.size()){
			if(listEntites.get(cpt).getUtilRattach() == utilRattach)
				listChercher.add(listEntites.get(cpt));
			cpt +=1;
		}
		listEntites = listChercher;
		}*/
		
		String entiteRattach = request.getParameter("entiteRattach");
		if((entiteRattach != null) && (entiteRattach.trim().length()> 0)){
			int cpt = 0;
			ArrayList<Entite> listChercher = new ArrayList<Entite>();
			while(cpt < listEntites.size()){
				if(listEntites.get(cpt).getEntiteRattach().getCodeEntite().equals(entiteRattach))
					listChercher.add(listEntites.get(cpt));
				cpt +=1;
			}
			listEntites = listChercher;
		}
		
		String libelle = request.getParameter("libelle");
		if((libelle != null) && (libelle.trim().length() > 0)){
			String langue = request.getParameter("langue");
			int cpt = 0;
			ArrayList<Entite> listChercher = new ArrayList<Entite>();
			while(cpt < listEntites.size()){
				ArrayList<LangueEntite> listlibelle = new ArrayList<LangueEntite>();
				listlibelle.addAll(listEntites.get(cpt).getLibEntite());
				int cpt2 = 0;
				while(cpt2 < listlibelle.size()){
					if( (listlibelle.get(cpt2).getLangue().getCodeLangue().equals(langue)) && (listlibelle.get(cpt2).getLibEntite().equals(libelle))){
						listChercher.add(listEntites.get(cpt));
						cpt2 = listlibelle.size();
					}
					cpt2 +=1;
				}
				cpt +=1;
			}
			listEntites = listChercher;
		}
		
		
		Map modele = new HashMap();
		if((listEntites != null) && (listEntites.size()>0)){
			request.getSession().setAttribute("listEntites",listEntites);
			request.getSession().setAttribute("indiceCourant", 0);
			modele.put("entiteCourant", listEntites.get(0));
			
			ILangueEntite langEntiteDAO = new LangueEntiteDAO();
			ArrayList<LangueEntite> langueEntites =  langEntiteDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue());
			if(listEntites.get(0).getEntiteRattach() != null){
				ILangueEntite langEntDAO = new LangueEntiteDAO();
				modele.put("entiteRattach", langEntDAO.getLangueEntite(utilisateur.getLangue().getCodeLangue(), listEntites.get(0).getEntiteRattach().getCodeEntite()));
				int bc = 0;
				while(bc < langueEntites.size()){
					if(langueEntites.get(bc).getEntite().getCodeEntite().equals(listEntites.get(0).getEntiteRattach().getCodeEntite()))
						langueEntites.remove(bc);
					bc += 1;
				}
			}
			
			modele.put("langueEntites", langueEntites);
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			
			ArrayList<LangueEntite> libelleEntiteCourant = new ArrayList<LangueEntite>();
			if(listEntites.get(0).getLibEntite()!= null){
				modele.put("recherche", "ok");
				libelleEntiteCourant.addAll(listEntites.get(0).getLibEntite());
				request.getSession().setAttribute("listLibelle",libelleEntiteCourant);
				int cpt = 0;
				while(cpt < langues.size()){
					int cpt2 = 0;
					while(cpt2 < libelleEntiteCourant.size()){
						if(langues.get(cpt).getCodeLangue().equals(libelleEntiteCourant.get(cpt2).getLangue().getCodeLangue())){
							langues.remove(cpt);
							cpt -=1; cpt2 = libelleEntiteCourant.size();
						}
						cpt2 +=1;
					}
					cpt +=1;
				}
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			request.getSession().setAttribute("langues",langues);
			return new ModelAndView("Entites",modele);
		}
		return init(request,response);
	}
	
//-----------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView premier(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		if(request.getSession().getAttribute("listEntites") != null){
			ArrayList<Entite> listEntites = (ArrayList<Entite>) request.getSession().getAttribute("listEntites");
			if(listEntites.size()>0){
				Map modele = new HashMap();
				modele.put("recherche", "ok");
				request.getSession().setAttribute("indiceCourant",0 );
				modele.put("entiteCourant", listEntites.get(0));
				
				ILangueEntite langEntiteDAO = new LangueEntiteDAO();
				ArrayList<LangueEntite> langueEntites =  langEntiteDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue());
				
				if(listEntites.get(0).getEntiteRattach() != null){
					ILangueEntite langEntDAO = new LangueEntiteDAO();
					modele.put("entiteRattach", langEntDAO.getLangueEntite(utilisateur.getLangue().getCodeLangue(), listEntites.get(0).getEntiteRattach().getCodeEntite()));
					int bc = 0;
					while(bc < langueEntites.size()){
						if(langueEntites.get(bc).getEntite().getCodeEntite().equals(listEntites.get(0).getEntiteRattach().getCodeEntite()))
							langueEntites.remove(bc);
						bc += 1;
					}
				}
				
				modele.put("langueEntites", langueEntites);
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				
				ArrayList<LangueEntite> libelleEntiteCourant = new ArrayList<LangueEntite>();
				if(listEntites.get(0).getLibEntite()!= null){
					modele.put("recherche", "ok");
					libelleEntiteCourant.addAll(listEntites.get(0).getLibEntite());
					request.getSession().setAttribute("listLibelle",libelleEntiteCourant);
					int cpt = 0;
					while(cpt < langues.size()){
						int cpt2 = 0;
						while(cpt2 < libelleEntiteCourant.size()){
							if(langues.get(cpt).getCodeLangue().equals(libelleEntiteCourant.get(cpt2).getLangue().getCodeLangue())){
								langues.remove(cpt);
								cpt -=1; cpt2 = libelleEntiteCourant.size();
							}
							cpt2 +=1;
						}
						cpt +=1;
					}
				}
				if(langues.size()>0)
					modele.put("affLangue", "ok");
				request.getSession().setAttribute("langues",langues);
				return new ModelAndView("Entites",modele);				
			}
		}
		
		return init(request,response);
	}
	
//-----------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView dernier(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		if(request.getSession().getAttribute("listEntites") != null){
			ArrayList<Entite> listEntites = (ArrayList<Entite>) request.getSession().getAttribute("listEntites");
			if(listEntites.size()>0){
				Map modele = new HashMap();
				modele.put("recherche", "ok");
				request.getSession().setAttribute("indiceCourant",listEntites.size()-1);
				modele.put("entiteCourant", listEntites.get(listEntites.size()-1));
				
				ILangueEntite langEntiteDAO = new LangueEntiteDAO();
				ArrayList<LangueEntite> langueEntites =  langEntiteDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue());
				
				if(listEntites.get(listEntites.size()-1).getEntiteRattach() != null){
					ILangueEntite langEntDAO = new LangueEntiteDAO();
					modele.put("entiteRattach", langEntDAO.getLangueEntite(utilisateur.getLangue().getCodeLangue(), listEntites.get(listEntites.size()-1).getEntiteRattach().getCodeEntite()));
					int bc = 0;
					while(bc < langueEntites.size()){
						if(langueEntites.get(bc).getEntite().getCodeEntite().equals(listEntites.get(listEntites.size()-1).getEntiteRattach().getCodeEntite()))
							langueEntites.remove(bc);
						bc += 1;
					}
				}
				
				modele.put("langueEntites", langueEntites);
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				
				ArrayList<LangueEntite> libelleEntiteCourant = new ArrayList<LangueEntite>();
				if(listEntites.get(listEntites.size()-1).getLibEntite()!= null){
					modele.put("recherche", "ok");
					libelleEntiteCourant.addAll(listEntites.get(listEntites.size()-1).getLibEntite());
					request.getSession().setAttribute("listLibelle",libelleEntiteCourant);
					int cpt = 0;
					while(cpt < langues.size()){
						int cpt2 = 0;
						while(cpt2 < libelleEntiteCourant.size()){
							if(langues.get(cpt).getCodeLangue().equals(libelleEntiteCourant.get(cpt2).getLangue().getCodeLangue())){
								langues.remove(cpt);
								cpt -=1; cpt2 = libelleEntiteCourant.size();
							}
							cpt2 +=1;
						}
						cpt +=1;
					}
				}
				if(langues.size()>0)
					modele.put("affLangue", "ok");
				request.getSession().setAttribute("langues",langues);
				return new ModelAndView("Entites",modele);				
			}
		}
		
		return init(request,response);
	}
	
//------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView precedant(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		if(request.getSession().getAttribute("listEntites") != null){
			ArrayList<Entite> listEntites = (ArrayList<Entite>) request.getSession().getAttribute("listEntites");
			if(listEntites.size()>0){
				Map modele = new HashMap();
				modele.put("recherche", "ok");
				
				int indice = (Integer) request.getSession().getAttribute("indiceCourant");
				if(indice > 0)
					indice -= 1;				
				request.getSession().setAttribute("indiceCourant",indice);
				modele.put("entiteCourant", listEntites.get(indice));
				
				ILangueEntite langEntiteDAO = new LangueEntiteDAO();
				ArrayList<LangueEntite> langueEntites =  langEntiteDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue());
				
				if(listEntites.get(indice).getEntiteRattach() != null){
					ILangueEntite langEntDAO = new LangueEntiteDAO();
					modele.put("entiteRattach", langEntDAO.getLangueEntite(utilisateur.getLangue().getCodeLangue(), listEntites.get(indice).getEntiteRattach().getCodeEntite()));
					int bc = 0;
					while(bc < langueEntites.size()){
						if(langueEntites.get(bc).getEntite().getCodeEntite().equals(listEntites.get(indice).getEntiteRattach().getCodeEntite()))
							langueEntites.remove(bc);
						bc += 1;
					}
				}
				
				modele.put("langueEntites", langueEntites);
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				
				ArrayList<LangueEntite> libelleEntiteCourant = new ArrayList<LangueEntite>();
				if(listEntites.get(indice).getLibEntite()!= null){
					modele.put("recherche", "ok");
					libelleEntiteCourant.addAll(listEntites.get(indice).getLibEntite());
					request.getSession().setAttribute("listLibelle",libelleEntiteCourant);
					int cpt = 0;
					while(cpt < langues.size()){
						int cpt2 = 0;
						while(cpt2 < libelleEntiteCourant.size()){
							if(langues.get(cpt).getCodeLangue().equals(libelleEntiteCourant.get(cpt2).getLangue().getCodeLangue())){
								langues.remove(cpt);
								cpt -=1; cpt2 = libelleEntiteCourant.size();
							}
							cpt2 +=1;
						}
						cpt +=1;
					}
				}
				if(langues.size()>0)
					modele.put("affLangue", "ok");
				request.getSession().setAttribute("langues",langues);
				return new ModelAndView("Entites",modele);				
			}
		}
		
		return init(request,response);
	}
	
//--------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView suivant(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		if(request.getSession().getAttribute("listEntites") != null){
			ArrayList<Entite> listEntites = (ArrayList<Entite>) request.getSession().getAttribute("listEntites");
			if(listEntites.size()>0){
				Map modele = new HashMap();
				modele.put("recherche", "ok");
				
				int indice = (Integer) request.getSession().getAttribute("indiceCourant");
				if(indice < listEntites.size() - 1)
					indice += 1;				
				request.getSession().setAttribute("indiceCourant",indice);
				modele.put("entiteCourant", listEntites.get(indice));
				
				ILangueEntite langEntiteDAO = new LangueEntiteDAO();
				ArrayList<LangueEntite> langueEntites =  langEntiteDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue());
				
				if(listEntites.get(indice).getEntiteRattach() != null){
					ILangueEntite langEntDAO = new LangueEntiteDAO();
					modele.put("entiteRattach", langEntDAO.getLangueEntite(utilisateur.getLangue().getCodeLangue(), listEntites.get(indice).getEntiteRattach().getCodeEntite()));
					int bc = 0;
					while(bc < langueEntites.size()){
						if(langueEntites.get(bc).getEntite().getCodeEntite().equals(listEntites.get(indice).getEntiteRattach().getCodeEntite()))
							langueEntites.remove(bc);
						bc += 1;
					}
				}
				
				modele.put("langueEntites", langueEntites);
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				
				ArrayList<LangueEntite> libelleEntiteCourant = new ArrayList<LangueEntite>();
				if(listEntites.get(indice).getLibEntite()!= null){
					modele.put("recherche", "ok");
					libelleEntiteCourant.addAll(listEntites.get(indice).getLibEntite());
					request.getSession().setAttribute("listLibelle",libelleEntiteCourant);
					int cpt = 0;
					while(cpt < langues.size()){
						int cpt2 = 0;
						while(cpt2 < libelleEntiteCourant.size()){
							if(langues.get(cpt).getCodeLangue().equals(libelleEntiteCourant.get(cpt2).getLangue().getCodeLangue())){
								langues.remove(cpt);
								cpt -=1; cpt2 = libelleEntiteCourant.size();
							}
							cpt2 +=1;
						}
						cpt +=1;
					}
				}
				if(langues.size()>0)
					modele.put("affLangue", "ok");
				request.getSession().setAttribute("langues",langues);
				return new ModelAndView("Entites",modele);				
			}
		}
		
		return init(request,response);
	}
	
//---------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView editerLibelle(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		ArrayList<LangueEntite> listLibelle = (ArrayList<LangueEntite>) request.getSession().getAttribute("listLibelle");
		
		String codeEntite = request.getParameter("codeEntite");
		String langue = request.getParameter("libelleEdit");
		
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
		request.getSession().setAttribute("listLibelle",listLibelle);
		
		ILangueEntite langEntDAO = new LangueEntiteDAO();
		LangueEntite langEnt = langEntDAO.getLangueEntite(langue, codeEntite);
		if(langEnt != null)
		langEntDAO.delete(langEnt);
		
		IEntite entDAO = new EntiteDAO();
		Entite entiteCourant = entDAO.getEntite(codeEntite);
		entiteCourant.setUtilModif(utilisateur);
		entiteCourant.setDateDernierModif(new Date());
		entDAO.save(entiteCourant);
		
		ILangueEntite langEntiteDAO = new LangueEntiteDAO();
		ArrayList<LangueEntite> langueEntites =  langEntiteDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue());
		if(entiteCourant.getEntiteRattach() != null){
			modele.put("entiteRattach", langEntDAO.getLangueEntite(utilisateur.getLangue().getCodeLangue(), entiteCourant.getEntiteRattach().getCodeEntite()));
			int bc = 0;
			while(bc < langueEntites.size()){
				if(langueEntites.get(bc).getEntite().getCodeEntite().equals(entiteCourant.getEntiteRattach().getCodeEntite()))
					langueEntites.remove(bc);
				bc += 1;
			}
		}
		modele.put("langueEntites", langueEntites);
		modele.put("entiteCourant", entiteCourant);
		modele.put("affLangue", "ok");
		
		return new ModelAndView("Entites",modele);
	}
	
//----------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView deleteLibelle(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		ArrayList<LangueEntite> listLibelle = (ArrayList<LangueEntite>) request.getSession().getAttribute("listLibelle");
		
		String codeEntite = request.getParameter("codeEntite");
		String langue = request.getParameter("libelleEdit");
		ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
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
		request.getSession().setAttribute("listLibelle",listLibelle);
		
		ILangueEntite langEntDAO = new LangueEntiteDAO();
		LangueEntite langEnt = langEntDAO.getLangueEntite(langue, codeEntite);
		if(langEnt != null)
		langEntDAO.delete(langEnt);
		
		IEntite entDAO = new EntiteDAO();
		Entite entiteCourant = entDAO.getEntite(codeEntite);
		entiteCourant.setUtilModif(utilisateur);
		entiteCourant.setDateDernierModif(new Date());
		entDAO.save(entiteCourant);
		
		ILangueEntite langEntiteDAO = new LangueEntiteDAO();
		ArrayList<LangueEntite> langueEntites =  langEntiteDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue());
		if(entiteCourant.getEntiteRattach() != null){
			modele.put("entiteRattach", langEntDAO.getLangueEntite(utilisateur.getLangue().getCodeLangue(), entiteCourant.getEntiteRattach().getCodeEntite()));
			int bc = 0;
			while(bc < langueEntites.size()){
				if(langueEntites.get(bc).getEntite().getCodeEntite().equals(entiteCourant.getEntiteRattach().getCodeEntite()))
					langueEntites.remove(bc);
				bc += 1;
			}
		}
		
		request.getSession().setAttribute("langues",langues);
		modele.put("langueEntites", langueEntites);
		modele.put("entiteCourant", entiteCourant);
		modele.put("affLangue", "ok");
		
		return new ModelAndView("Entites",modele);
	}
	

}
