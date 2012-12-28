package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.EntiteDAO;
import modele.dao.FonctionsDAO;
import modele.dao.IEntite;
import modele.dao.IFonctions;
import modele.dao.ILangue;
import modele.dao.ILangueEntite;
import modele.dao.ILangueFonctions;
import modele.dao.LangueDAO;
import modele.dao.LangueEntiteDAO;
import modele.dao.LangueFonctionsDAO;
import modele.metiers.Entite;
import modele.metiers.Fonctions;
import modele.metiers.Langue;
import modele.metiers.LangueEntite;
import modele.metiers.LangueFonctions;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FonctionsController extends MultiActionController {
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		
		request.getSession().setAttribute("listEntites",listEntites);
		ILangue langueDAO = new LangueDAO();
		request.getSession().setAttribute("langues", langueDAO.allLangue());
		modele.put("affLangue", "ok");	
		
		return new ModelAndView("Fonctions",modele);
	}
	
//---------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView ajouterNom(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Fonctions fction = new Fonctions();
		if(request.getSession().getAttribute("fonctionCourant") != null)
			fction = (Fonctions) request.getSession().getAttribute("fonctionCourant");
		
		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
		if(request.getSession().getAttribute("listEntites") != null)
			listEntites = (ArrayList<LangueEntite>) request.getSession().getAttribute("listEntites");
		
		ArrayList<Langue>langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
			
			String code = request.getParameter("code");
			if((code == null) || (code.trim().length() == 0)){
				modele.put("errorCode", utilisateur.getLangue().getChampObligatoire());
				request.getSession().setAttribute("fonctionCourant",fction);
				if(langues.size() > 0)
					modele.put("affLangue", "ok");
				return new ModelAndView("Fonctions",modele);
			}
			else{
				fction.setCode_fonction(code);
				
				String libelle = request.getParameter("libelle");
				if((libelle == null) || (libelle.trim().length() == 0)){
					request.getSession().setAttribute("fonctionCourant",fction);
					if(langues.size() > 0)
						modele.put("affLangue", "ok");
					return new ModelAndView("Fonctions",modele);
				}
				else{
					String langue = request.getParameter("langue");
					ILangue langDAO = new LangueDAO();
					Langue lang = langDAO.getLangue(langue);
					if(fction.getLibelleFonctions() != null){
						fction.getLibelleFonctions().add(new LangueFonctions(lang,fction,libelle));
					}
					else{
						ArrayList<LangueFonctions> listLib = new ArrayList<LangueFonctions>();
						listLib.add(new LangueFonctions(lang,fction,libelle));
						fction.setLibelleFonctions(listLib);
					}
					
					int cpt = 0;
					while(cpt < langues.size()){
						if(langues.get(cpt).getCodeLangue().equals(langue)){
							langues.remove(cpt);
							cpt = langues.size();
						}
						cpt += 1;
					}
					
					String entite = request.getParameter("entite");
					if((entite != null) && (entite.trim().length() > 0)){
						IEntite entiteDAO = new EntiteDAO();
						
						if(fction.getEntite() != null)
							fction.getEntite().add(entiteDAO.getEntite(entite));
						else{
							ArrayList<Entite> listEnt = new ArrayList<Entite>();
							listEnt.add(entiteDAO.getEntite(entite));
							fction.setEntite(listEnt);
						}
						int bc = 0;
						while(bc < listEntites.size()){
							if(listEntites.get(bc).getEntite().getCodeEntite().equals(entite)){
								listEntites.remove(bc);
								bc = listEntites.size();
							}
							bc += 1;
						}
					}	
					
					request.getSession().setAttribute("fonctionCourant",fction);
					request.getSession().setAttribute("langues",langues);
					request.getSession().setAttribute("listEntites",listEntites);
					if(langues.size() > 0)
						modele.put("affLangue", "ok");
					return new ModelAndView("Fonctions",modele);
					
				}
				
			}
	}
	
//------------------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView ajouterEntite(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Fonctions fction = new Fonctions();
		if(request.getSession().getAttribute("fonctionCourant") != null)
			fction = (Fonctions) request.getSession().getAttribute("fonctionCourant");
		
		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
		if(request.getSession().getAttribute("listEntites") != null)
			listEntites = (ArrayList<LangueEntite>) request.getSession().getAttribute("listEntites");
		
		ArrayList<Langue>langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
			
			String code = request.getParameter("code");
			if((code == null) || (code.trim().length() == 0)){
				modele.put("errorCode", utilisateur.getLangue().getChampObligatoire());
				request.getSession().setAttribute("fonctionCourant",fction);
				if(langues.size() > 0)
					modele.put("affLangue", "ok");
				return new ModelAndView("Fonctions",modele);
			}
			else{
				fction.setCode_fonction(code);
				
				if(request.getSession().getAttribute("libelles") != null)
					fction.setLibelleFonctions((Collection<LangueFonctions>) request.getSession().getAttribute("libelles"));
				String libelle = request.getParameter("libelle");
				if((libelle != null) && (libelle.trim().length() > 0)){
					String langue = request.getParameter("langue");
					ILangue langDAO = new LangueDAO();
					Langue lang = langDAO.getLangue(langue);
					if(fction.getLibelleFonctions() != null){
						fction.getLibelleFonctions().add(new LangueFonctions(lang,fction,libelle));
					}
					else{
						ArrayList<LangueFonctions> listLib = new ArrayList<LangueFonctions>();
						listLib.add(new LangueFonctions(lang,fction,libelle));
						fction.setLibelleFonctions(listLib);
					}
					
					int cpt = 0;
					while(cpt < langues.size()){
						if(langues.get(cpt).getCodeLangue().equals(langue)){
							langues.remove(cpt);
							cpt = langues.size();
						}
						cpt += 1;
					}
				}
				   if(request.getSession().getAttribute("entites")!= null)
					   fction.setEntite((Collection<Entite>) request.getSession().getAttribute("entites"));
					String entite = request.getParameter("entite");
					if((entite != null) && (entite.trim().length() > 0)){
						IEntite entiteDAO = new EntiteDAO();
						
						if(fction.getEntite() != null)
							fction.getEntite().add(entiteDAO.getEntite(entite));
						else{
							ArrayList<Entite> listEnt = new ArrayList<Entite>();
							listEnt.add(entiteDAO.getEntite(entite));
							fction.setEntite(listEnt);
						}
						int bc = 0;
						while(bc < listEntites.size()){
							if(listEntites.get(bc).getEntite().getCodeEntite().equals(entite)){
								listEntites.remove(bc);
								bc = listEntites.size();
							}
							bc += 1;
						}
					}		
			
					request.getSession().setAttribute("fonctionCourant",fction);
					request.getSession().setAttribute("langues",langues);
					request.getSession().setAttribute("listEntites",listEntites);
					if(langues.size() > 0)
						modele.put("affLangue", "ok");
					return new ModelAndView("Fonctions",modele);
			}
	}
	
	
//-------------------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Fonctions fction = new Fonctions();
		if(request.getSession().getAttribute("fonctionCourant") != null)
			fction = (Fonctions) request.getSession().getAttribute("fonctionCourant");
		
		ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
		if(request.getSession().getAttribute("listEntites") != null)
			listEntites = (ArrayList<LangueEntite>) request.getSession().getAttribute("listEntites");
		
		ArrayList<Langue>langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
			
			String code = request.getParameter("code");
			if((code == null) || (code.trim().length() == 0)){
				modele.put("errorCode", utilisateur.getLangue().getChampObligatoire());
				request.getSession().setAttribute("fonctionCourant",fction);
				if(langues.size() > 0)
					modele.put("affLangue", "ok");
				return new ModelAndView("Fonctions",modele);
			}
			else{				
				fction.setCode_fonction(code);
				
				if(request.getSession().getAttribute("libelles") != null)
					fction.setLibelleFonctions((Collection<LangueFonctions>) request.getSession().getAttribute("libelles"));
				String libelle = request.getParameter("libelle");
				if(((libelle == null) || (libelle.trim().length() == 0)) && (fction.getLibelleFonctions() == null)){
					modele.put("errorNom", utilisateur.getLangue().getChampObligatoire());
					request.getSession().setAttribute("fonctionCourant",fction);
					if(langues.size() > 0)
						modele.put("affLangue", "ok");
					return new ModelAndView("Fonctions",modele);
				}
				if( (libelle != null) && (libelle.trim().length() > 0)){
					String langue = request.getParameter("langue");
					ILangue langDAO = new LangueDAO();
					Langue lang = langDAO.getLangue(langue);
					if(fction.getLibelleFonctions() != null){
						fction.getLibelleFonctions().add(new LangueFonctions(lang,fction,libelle));
					}
					else{
						ArrayList<LangueFonctions> listLib = new ArrayList<LangueFonctions>();
						listLib.add(new LangueFonctions(lang,fction,libelle));
						fction.setLibelleFonctions(listLib);
					}
					
					int cpt = 0;
					while(cpt < langues.size()){
						if(langues.get(cpt).getCodeLangue().equals(langue)){
							langues.remove(cpt);
							cpt = langues.size();
						}
						cpt += 1;
					}
				}
					if(request.getSession().getAttribute("entites")!= null)
						fction.setEntite((Collection<Entite>) request.getSession().getAttribute("entites"));
					String entite = request.getParameter("entite");
					if((entite != null) && (entite.trim().length() > 0)){
						IEntite entiteDAO = new EntiteDAO();						
						if(fction.getEntite() != null){
							fction.getEntite().add(entiteDAO.getEntite(entite));							
						}
						else{
							ArrayList<Entite> listEnt = new ArrayList<Entite>();
							listEnt.add(entiteDAO.getEntite(entite));
							fction.setEntite(listEnt);
						}
						int bc = 0;
						while(bc < listEntites.size()){
							if(listEntites.get(bc).getEntite().getCodeEntite().equals(entite)){
								listEntites.remove(bc);
								bc = listEntites.size();
							}
							bc += 1;
						}
					}						
					
					IFonctions fctionDA0 = new FonctionsDAO();
					Fonctions ancenneFct = fctionDA0.getFonction(code);
					if(ancenneFct == null){
						fction.setUtilCree(utilisateur);
						fction.setDateCree(new Date());
						fctionDA0.save(fction);
						return init(request,response);
					}
					fction.setUtilCree(ancenneFct.getUtilCree());
					fction.setDateCree(ancenneFct.getDateCree());
					fction.setUtilModif(utilisateur);
					fction.setDateDernierModif(new Date());
					modele.put("confirmer", "ok");
					request.getSession().setAttribute("fonctionCourant",fction);
					request.getSession().setAttribute("langues",langues);
					request.getSession().setAttribute("listEntites",listEntites);
					if(langues.size() > 0)
						modele.put("affLangue", "ok");
					return new ModelAndView("Fonctions",modele);
			}
	}
	
//-------------------------------------------------------------------------------------------------------------------------	
	public ModelAndView updateFonctionns(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Fonctions fction = new Fonctions();
		if(request.getSession().getAttribute("fonctionCourant") != null)
			fction = (Fonctions) request.getSession().getAttribute("fonctionCourant");
		IFonctions fctionDAO = new FonctionsDAO();
		fctionDAO.save(fction);		
		return init(request,response); 
	}
	
	
//-------------------------------------------------------------------------------------------------------------------------	
		public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
			
			return init(request,response);
		}
		
		
//-------------------------------------------------------------------------------------------------------------------------	
		public ModelAndView supprimer(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			String code = request.getParameter("code");
			if((code != null) && (code.trim().length() > 0)){
				IFonctions fctionDAO = new FonctionsDAO();
				Fonctions fction = fctionDAO.getFonction(code);
				if(fction != null)
					fctionDAO.delete(fction);
			}	
			return init(request,response); 
		}
		
//---------------------------------------------------------------------------------------------------------------------------		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView rechercher(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			IFonctions fctionDAO = new FonctionsDAO();
			ArrayList<Fonctions> listFction = fctionDAO.allFonctions();
			
			String code = request.getParameter("code");
			if((code != null) && (code.trim().length() > 0)){
				ArrayList<Fonctions> listChercher = new ArrayList<Fonctions>();
				int cpt = 0;
				while(cpt <listChercher.size()){
					if(listFction.get(cpt).getCode_fonction().equals(code))
						listChercher.add(listFction.get(cpt));
					cpt += 1;
				}
				listFction = listChercher;
			}
			
			String libelle = request.getParameter("libelle");
			if((libelle != null) && (libelle.trim().length() > 0)){
				String langue = request.getParameter("langue");
				ArrayList<Fonctions> listChercher = new ArrayList<Fonctions>();
				int cpt = 0;
				ILangueFonctions langFction = new LangueFonctionsDAO();
				while(cpt <listChercher.size()){
					if(langFction.getLangueFonctions(langue, listFction.get(cpt).getCode_fonction()).getLibFonctions().equals(libelle))
						listChercher.add(listFction.get(cpt));
					cpt += 1;
				}
				listFction = listChercher;
			}
			
			String entite = request.getParameter("entite");
			if((entite != null) && (entite.trim().length() > 0)){
				ArrayList<Fonctions> listChercher = new ArrayList<Fonctions>();
				int cpt = 0;
				while(cpt <listChercher.size()){
					ArrayList<Entite> listEntite = new ArrayList<Entite>();
					listEntite.addAll(listFction.get(cpt).getEntite());
					int bc = 0;
					while(bc < listEntite.size()){
						if(listEntite.get(bc).getCodeEntite().equals(entite)){
							listChercher.add(listFction.get(cpt));
							bc += listEntite.size();
						}	
						bc += 1;					
					}
					cpt += 1;
				}
				listFction = listChercher;
			}
			
			if(listFction.size() > 0){
				request.getSession().setAttribute("listFonctions",listFction);
				request.getSession().setAttribute("indiceCourant",0);
				Fonctions fonctionCourant = listFction.get(0);
				modele.put("fonctionCourant",fonctionCourant);
				
				ILangueEntite langEntDAO = new LangueEntiteDAO();
				ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
				listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
				
				ILangue langDAO = new LangueDAO();
				ArrayList<Langue> langues = langDAO.allLangue();
				int cpt = 0;
				while(cpt < langues.size()){
					ArrayList<LangueFonctions> langFct = new ArrayList<LangueFonctions>();
					langFct.addAll(fonctionCourant.getLibelleFonctions());
					int cpt1 = 0;
					while(cpt1 < langFct.size()){
						if(langFct.get(cpt1).getLangue().getCodeLangue().equals(langues.get(cpt).getCodeLangue())){
							langues.remove(cpt);
							cpt -= 1; cpt1 = langFct.size();
						}
						cpt1 += 1;						
					}
					cpt += 1;
				}
				request.getSession().setAttribute("langues",langues);
				if(fonctionCourant.getLibelleFonctions() != null){
					ArrayList<LangueFonctions> libelles = new ArrayList<LangueFonctions>();
					libelles.addAll(fonctionCourant.getLibelleFonctions());
					request.getSession().setAttribute("libelles",libelles);
				}		
				
				if(fonctionCourant.getEntite() != null){
					ArrayList<Entite> entites = new ArrayList<Entite>();
					entites.addAll(fonctionCourant.getEntite());
					request.getSession().setAttribute("entites",entites);
					
					int bcl = 0;
					while(bcl < entites.size()){						
						int bcl2 = 0;	
						while(bcl2 < listEntites.size()){
							if(listEntites.get(bcl2).getEntite().getCodeEntite().equals(entites.get(bcl).getCodeEntite())){
								listEntites.remove(bcl2);
								bcl2 = listEntites.size();
							}
							bcl2 += 1;
						}
						bcl += 1;
					}					
				}
				request.getSession().setAttribute("listEntites",listEntites);
				if(langues.size() > 0)
					modele.put("affLangue", "ok");
				
				return new ModelAndView("Fonctions",modele);
			}
			
			return init(request,response); 
		}
		
//-----------------------------------------------------------------------------------------------------------------------		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView premier(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur"); 
			
			ArrayList<Fonctions> listFction = (ArrayList<Fonctions>) request.getSession().getAttribute("listFonctions");
					if(listFction.size() > 0){
						request.getSession().setAttribute("listFonctions",listFction);
						request.getSession().setAttribute("indiceCourant",0);
						Fonctions fonctionCourant = listFction.get(0);
						modele.put("fonctionCourant",fonctionCourant);
						
						ILangueEntite langEntDAO = new LangueEntiteDAO();
						ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
						listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
						
						ILangue langDAO = new LangueDAO();
						ArrayList<Langue> langues = langDAO.allLangue();
						int cpt = 0;
						while(cpt < langues.size()){
							ArrayList<LangueFonctions> langFct = new ArrayList<LangueFonctions>();
							langFct.addAll(fonctionCourant.getLibelleFonctions());
							int cpt1 = 0;
							while(cpt1 < langFct.size()){
								if(langFct.get(cpt1).getLangue().getCodeLangue().equals(langues.get(cpt).getCodeLangue())){
									langues.remove(cpt);
									cpt -= 1; cpt1 = langFct.size();
								}
								cpt1 += 1;						
							}
							cpt += 1;
						}
						request.getSession().setAttribute("langues",langues);
						if(fonctionCourant.getLibelleFonctions() != null){
							ArrayList<LangueFonctions> libelles = new ArrayList<LangueFonctions>();
							libelles.addAll(fonctionCourant.getLibelleFonctions());
							request.getSession().setAttribute("libelles",libelles);
						}		
						
						if(fonctionCourant.getEntite() != null){
							ArrayList<Entite> entites = new ArrayList<Entite>();
							entites.addAll(fonctionCourant.getEntite());
							request.getSession().setAttribute("entites",entites);
							
							int bcl = 0;
							while(bcl < entites.size()){						
								int bcl2 = 0;	
								while(bcl2 < listEntites.size()){
									if(listEntites.get(bcl2).getEntite().getCodeEntite().equals(entites.get(bcl).getCodeEntite())){
										listEntites.remove(bcl2);
										bcl2 = listEntites.size();
									}
									bcl2 += 1;
								}
								bcl += 1;
							}					
						}
						request.getSession().setAttribute("listEntites",listEntites);
						if(langues.size() > 0)
							modele.put("affLangue", "ok");
						
						return new ModelAndView("Fonctions",modele);
					}
					
					return init(request,response);
			
		}	
		
//----------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView dernier(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur"); 
			
			ArrayList<Fonctions> listFction = (ArrayList<Fonctions>) request.getSession().getAttribute("listFonctions");
					if(listFction.size() > 0){
						request.getSession().setAttribute("listFonctions",listFction);
						request.getSession().setAttribute("indiceCourant",listFction.size()-1);
						Fonctions fonctionCourant = listFction.get(listFction.size()-1);
						modele.put("fonctionCourant",fonctionCourant);
						
						ILangueEntite langEntDAO = new LangueEntiteDAO();
						ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
						listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
						
						ILangue langDAO = new LangueDAO();
						ArrayList<Langue> langues = langDAO.allLangue();
						int cpt = 0;
						while(cpt < langues.size()){
							ArrayList<LangueFonctions> langFct = new ArrayList<LangueFonctions>();
							langFct.addAll(fonctionCourant.getLibelleFonctions());
							int cpt1 = 0;
							while(cpt1 < langFct.size()){
								if(langFct.get(cpt1).getLangue().getCodeLangue().equals(langues.get(cpt).getCodeLangue())){
									langues.remove(cpt);
									cpt -= 1; cpt1 = langFct.size();
								}
								cpt1 += 1;						
							}
							cpt += 1;
						}
						request.getSession().setAttribute("langues",langues);
						if(fonctionCourant.getLibelleFonctions() != null){
							ArrayList<LangueFonctions> libelles = new ArrayList<LangueFonctions>();
							libelles.addAll(fonctionCourant.getLibelleFonctions());
							request.getSession().setAttribute("libelles",libelles);
						}		
						
						if(fonctionCourant.getEntite() != null){
							ArrayList<Entite> entites = new ArrayList<Entite>();
							entites.addAll(fonctionCourant.getEntite());
							request.getSession().setAttribute("entites",entites);
							
							int bcl = 0;
							while(bcl < entites.size()){						
								int bcl2 = 0;	
								while(bcl2 < listEntites.size()){
									if(listEntites.get(bcl2).getEntite().getCodeEntite().equals(entites.get(bcl).getCodeEntite())){
										listEntites.remove(bcl2);
										bcl2 = listEntites.size();
									}
									bcl2 += 1;
								}
								bcl += 1;
							}					
						}
						request.getSession().setAttribute("listEntites",listEntites);
						if(langues.size() > 0)
							modele.put("affLangue", "ok");
						
						return new ModelAndView("Fonctions",modele);
					}
					
					return init(request,response);
			
		}
		
//-----------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView precedant(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur"); 
			
			ArrayList<Fonctions> listFction = (ArrayList<Fonctions>) request.getSession().getAttribute("listFonctions");
					if(listFction.size() > 0){
						request.getSession().setAttribute("listFonctions",listFction);
						
						int indice = (Integer) request.getSession().getAttribute("indiceCourant");
						if(indice > 0)
							indice -= 1;
						request.getSession().setAttribute("indiceCourant",indice);
						Fonctions fonctionCourant = listFction.get(indice);
						modele.put("fonctionCourant",fonctionCourant);
						
						ILangueEntite langEntDAO = new LangueEntiteDAO();
						ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
						listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
						
						ILangue langDAO = new LangueDAO();
						ArrayList<Langue> langues = langDAO.allLangue();
						int cpt = 0;
						while(cpt < langues.size()){
							ArrayList<LangueFonctions> langFct = new ArrayList<LangueFonctions>();
							langFct.addAll(fonctionCourant.getLibelleFonctions());
							int cpt1 = 0;
							while(cpt1 < langFct.size()){
								if(langFct.get(cpt1).getLangue().getCodeLangue().equals(langues.get(cpt).getCodeLangue())){
									langues.remove(cpt);
									cpt -= 1; cpt1 = langFct.size();
								}
								cpt1 += 1;						
							}
							cpt += 1;
						}
						request.getSession().setAttribute("langues",langues);
						if(fonctionCourant.getLibelleFonctions() != null){
							ArrayList<LangueFonctions> libelles = new ArrayList<LangueFonctions>();
							libelles.addAll(fonctionCourant.getLibelleFonctions());
							request.getSession().setAttribute("libelles",libelles);
						}		
						
						if(fonctionCourant.getEntite() != null){
							ArrayList<Entite> entites = new ArrayList<Entite>();
							entites.addAll(fonctionCourant.getEntite());
							request.getSession().setAttribute("entites",entites);
							
							int bcl = 0;
							while(bcl < entites.size()){						
								int bcl2 = 0;	
								while(bcl2 < listEntites.size()){
									if(listEntites.get(bcl2).getEntite().getCodeEntite().equals(entites.get(bcl).getCodeEntite())){
										listEntites.remove(bcl2);
										bcl2 = listEntites.size();
									}
									bcl2 += 1;
								}
								bcl += 1;
							}					
						}
						request.getSession().setAttribute("listEntites",listEntites);
						if(langues.size() > 0)
							modele.put("affLangue", "ok");
						
						return new ModelAndView("Fonctions",modele);
					}
					
					return init(request,response);
			
		}
		
//-----------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView suivant(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur"); 
			
			ArrayList<Fonctions> listFction = (ArrayList<Fonctions>) request.getSession().getAttribute("listFonctions");
					if(listFction.size() > 0){
						request.getSession().setAttribute("listFonctions",listFction);
						
						int indice = (Integer) request.getSession().getAttribute("indiceCourant");
						if(indice < listFction.size()-1)
							indice += 1;
						request.getSession().setAttribute("indiceCourant",indice);
						Fonctions fonctionCourant = listFction.get(indice);
						modele.put("fonctionCourant",fonctionCourant);
						
						ILangueEntite langEntDAO = new LangueEntiteDAO();
						ArrayList<LangueEntite> listEntites = new ArrayList<LangueEntite>();
						listEntites.addAll(langEntDAO.allLangueEntite(utilisateur.getLangue().getCodeLangue()));
						
						ILangue langDAO = new LangueDAO();
						ArrayList<Langue> langues = langDAO.allLangue();
						int cpt = 0;
						while(cpt < langues.size()){
							ArrayList<LangueFonctions> langFct = new ArrayList<LangueFonctions>();
							langFct.addAll(fonctionCourant.getLibelleFonctions());
							int cpt1 = 0;
							while(cpt1 < langFct.size()){
								if(langFct.get(cpt1).getLangue().getCodeLangue().equals(langues.get(cpt).getCodeLangue())){
									langues.remove(cpt);
									cpt -= 1; cpt1 = langFct.size();
								}
								cpt1 += 1;						
							}
							cpt += 1;
						}
						request.getSession().setAttribute("langues",langues);
						if(fonctionCourant.getLibelleFonctions() != null){
							ArrayList<LangueFonctions> libelles = new ArrayList<LangueFonctions>();
							libelles.addAll(fonctionCourant.getLibelleFonctions());
							request.getSession().setAttribute("libelles",libelles);
						}		
						
						if(fonctionCourant.getEntite() != null){
							ArrayList<Entite> entites = new ArrayList<Entite>();
							entites.addAll(fonctionCourant.getEntite());
							request.getSession().setAttribute("entites",entites);
							
							int bcl = 0;
							while(bcl < entites.size()){						
								int bcl2 = 0;	
								while(bcl2 < listEntites.size()){
									if(listEntites.get(bcl2).getEntite().getCodeEntite().equals(entites.get(bcl).getCodeEntite())){
										listEntites.remove(bcl2);
										bcl2 = listEntites.size();
									}
									bcl2 += 1;
								}
								bcl += 1;
							}					
						}
						request.getSession().setAttribute("listEntites",listEntites);
						if(langues.size() > 0)
							modele.put("affLangue", "ok");
						
						return new ModelAndView("Fonctions",modele);
					}
					
					return init(request,response);
			
		}
		

}
