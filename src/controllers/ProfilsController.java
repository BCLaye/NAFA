package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.ILangue;
import modele.dao.ILangueFonctionnalites;
import modele.dao.ILangueProfil;
import modele.dao.INomenclatureMDP;
import modele.dao.IProfil;
import modele.dao.LangueDAO;
import modele.dao.LangueFonctionnalitesDAO;
import modele.dao.LangueProfilDAO;
import modele.dao.NomenclatureMDPDAO;
import modele.dao.ProfilDAO;
import modele.metiers.Langue;
import modele.metiers.LangueProfil;
import modele.metiers.Menus;
import modele.metiers.NomenclatureMDP;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ProfilsController extends MultiActionController {
	
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
		
		INomenclatureMDP nomenclatDAO = new NomenclatureMDPDAO();
		modele.put("nomenclatureMDPs", nomenclatDAO.allNomenclatureMDP());
		
		ILangueFonctionnalites fctionnalitesDAO = new LangueFonctionnalitesDAO();
		modele.put("fonctionnalites", fctionnalitesDAO.allLangueFonctionnalite(utilConnecte.getLangue().getCodeLangue()));
		
		ILangue langDAO = new LangueDAO();
		modele.put("langues", langDAO.allLangue());
		modele.put("affLangue", "ok");
		return new ModelAndView("Profils",modele);
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
		
		// le profil courant
		Profil profil = new Profil();
		if(request.getSession().getAttribute("profilCourant") != null)
			profil = (Profil) request.getSession().getAttribute("profilCourant");
		
		// la liste des langues pr la selection
		ArrayList<Langue> langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		else{
			ILangue langueDAO = new modele.dao.LangueDAO();
			langues = langueDAO.allLangue();
			}
		
		// la liste des libelles déja choisis
		ArrayList<LangueProfil> listLibelle = new ArrayList<LangueProfil>();
		if(request.getSession().getAttribute("listLibelle") != null)
			listLibelle = (ArrayList<LangueProfil>) request.getSession().getAttribute("listLibelle");
		
		// la liste de nomenclatures de mot de pass pr la selection
		INomenclatureMDP nomenclatDAO = new NomenclatureMDPDAO();
		ArrayList<NomenclatureMDP> listNomenclature = nomenclatDAO.allNomenclatureMDP();
		
		if(langues.size() == 0){
			request.getSession().setAttribute("profilCourant",profil);
			modele.put("nomenclatureMDPs", listNomenclature);
			modele.put("langues", langues);
			modele.put("listLibelle", listLibelle);
			return new ModelAndView("Profils", modele);
		}
		
		String codeProfil = request.getParameter("codeProfil");
		if((codeProfil == null) || (codeProfil.trim().length() == 0)){
			request.getSession().setAttribute("profilCourant",profil);
			modele.put("errorCode",utilisateur.getLangue().getChampObligatoire());
			modele.put("nomenclatureMDPs", listNomenclature);
			modele.put("affLangue", "ok");
			modele.put("langues", langues);
			modele.put("listLibelle", listLibelle);
			return new ModelAndView("Profils", modele);
		}
		else{
			profil.setCodeProfil(codeProfil);
			
			String nomenclatureMDP = request.getParameter("nomenclatureMDP");
			if((nomenclatureMDP == null) || (nomenclatureMDP.trim().length() == 0)){
				request.getSession().setAttribute("profilCourant",profil);
				modele.put("errorNomenclature",utilisateur.getLangue().getChampObligatoire());
				modele.put("nomenclatureMDPs", listNomenclature);
				modele.put("affLangue", "ok");
				modele.put("langues", langues);
				modele.put("listLibelle", listLibelle);
				return new ModelAndView("Profils", modele);
			}
			else{
				int cpt = 0;
				while(cpt < listNomenclature.size()){
					if(listNomenclature.get(cpt).getId().equals(nomenclatureMDP))
						listNomenclature.remove(cpt);
					cpt += 1;
				}
				profil.setNomenclatureMDP(nomenclatDAO.getNomenclatureMDP(nomenclatureMDP));
				
				String libelle = request.getParameter("libelle");
				if((libelle == null) || (libelle.trim().length() == 0)){
					request.getSession().setAttribute("profilCourant",profil);
					modele.put("errorNom",utilisateur.getLangue().getChampObligatoire());
					modele.put("nomenclatureMDPs", listNomenclature);
				    modele.put("affLangue", "ok");
					modele.put("langues", langues);
					modele.put("listLibelle", listLibelle);
					return new ModelAndView("Profils", modele);
					
				}
				else{
					ILangue langDAO = new LangueDAO();
					Langue langSelect = langDAO.getLangue(request.getParameter("langue"));
					LangueProfil nouvoLib = new LangueProfil(langSelect, profil, libelle);
					listLibelle.add(nouvoLib);
					
					int cpt2 = 0;
					while(cpt2 < langues.size()){
						if(langues.get(cpt2).getCodeLangue().equals(langSelect.getCodeLangue())){
							langues.remove(cpt2);
							cpt2 = langues.size();
						}						
						cpt2 +=1;
					}
					request.getSession().setAttribute("profilCourant",profil);
					request.getSession().setAttribute("langues",langues);
					request.getSession().setAttribute("listLibelle",listLibelle);
					if(langues.size()>0)
						modele.put("affLangue", "ok");
					return new ModelAndView("Profils", modele);
				}
				
			}
		}
	}
	
//----------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		// le profil courant
		Profil profil = new Profil();
		if(request.getSession().getAttribute("profilCourant") != null)
			profil = (Profil) request.getSession().getAttribute("profilCourant");
		
		// la liste des langues pr la selection
		ArrayList<Langue> langues = new ArrayList<Langue>();
		if(request.getSession().getAttribute("langues") != null)
			langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
		else{
			ILangue langueDAO = new modele.dao.LangueDAO();
			langues = langueDAO.allLangue();
			}
		
		// la liste des libelles déja choisis
		ArrayList<LangueProfil> listLibelle = new ArrayList<LangueProfil>();
		if(request.getSession().getAttribute("listLibelle") != null)
			listLibelle = (ArrayList<LangueProfil>) request.getSession().getAttribute("listLibelle");
		
		// la liste de nomenclatures de mot de pass pr la selection
		INomenclatureMDP nomenclatDAO = new NomenclatureMDPDAO();
		ArrayList<NomenclatureMDP> listNomenclature = nomenclatDAO.allNomenclatureMDP();
		
		String codeProfil = request.getParameter("codeProfil");
		if((codeProfil == null) || (codeProfil.trim().length() == 0)){
			request.getSession().setAttribute("profilCourant",profil);
			modele.put("errorCode",utilisateur.getLangue().getChampObligatoire());
			modele.put("nomenclatureMDPs", listNomenclature);
			modele.put("affLangue", "ok");
			modele.put("langues", langues);
			modele.put("listLibelle", listLibelle);
			return new ModelAndView("Profils", modele);
		}
		else{
			profil.setCodeProfil(codeProfil);
			
			String nomenclatureMDP = request.getParameter("nomenclatureMDP");
			if((nomenclatureMDP == null) || (nomenclatureMDP.trim().length() == 0)){
				request.getSession().setAttribute("profilCourant",profil);
				modele.put("errorNomenclature",utilisateur.getLangue().getChampObligatoire());
				modele.put("nomenclatureMDPs", listNomenclature);
				modele.put("affLangue", "ok");
				modele.put("langues", langues);
				modele.put("listLibelle", listLibelle);
				return new ModelAndView("Profils", modele);
			}
			else{
				int cpt = 0;
				while(cpt < listNomenclature.size()){
					if(listNomenclature.get(cpt).getId().equals(nomenclatureMDP))
						listNomenclature.remove(cpt);
					cpt += 1;
				}
				profil.setNomenclatureMDP(nomenclatDAO.getNomenclatureMDP(nomenclatureMDP));
				
				String libelle = request.getParameter("libelle");
				if(((libelle == null) || (libelle.trim().length() == 0)) && (listLibelle.size() == 0)){
					request.getSession().setAttribute("profilCourant",profil);
					modele.put("errorNom",utilisateur.getLangue().getChampObligatoire());
					modele.put("nomenclatureMDPs", listNomenclature);
				    modele.put("affLangue", "ok");
					modele.put("langues", langues);
					modele.put("listLibelle", listLibelle);
					return new ModelAndView("Profils", modele);
					
				}
				
				if((libelle != null) && (libelle.trim().length() > 0)){
					ILangue langDAO = new LangueDAO();
					Langue langSelect = langDAO.getLangue(request.getParameter("langue"));
					LangueProfil nouvoLib = new LangueProfil(langSelect, profil, libelle);
					listLibelle.add(nouvoLib);
					
					int cpt2 = 0;
					while(cpt2 < langues.size()){
						if(langues.get(cpt2).getCodeLangue().equals(langSelect.getCodeLangue())){
							langues.remove(cpt2);
							cpt2 = langues.size();
						}						
						cpt2 +=1;
					}
					}
					
					IProfil profilDAO = new ProfilDAO();
					Profil ancienProfil = profilDAO.getProfil(codeProfil);
					if(ancienProfil == null){
						profil.setUtilCree(utilisateur);
						profil.setDateCree(new Date());
						profilDAO.save(profil);
						
						int cpt3 = 0;
						ILangueProfil langProfDAO = new LangueProfilDAO();
						while(cpt3 < listLibelle.size()){
							langProfDAO.save(listLibelle.get(cpt3));
							cpt3 += 1;
						} 
						return init(request,response);
					}
					else{
						if(!codeProfil.equals("AdminUser")){
							profil.setUtilCree(ancienProfil.getUtilCree());
							profil.setDateCree(ancienProfil.getDateCree());
							profil.setUtilModif(utilisateur);
							profil.setDateModif(new Date());
							
							request.getSession().setAttribute("profilCourant",profil);
							request.getSession().setAttribute("listLibelle",listLibelle);
							modele.put("confirmer", "ok");
							return new ModelAndView("Profils", modele);
							}
						else
							return init(request,response);
					}					
				}
				
			}
		}
	
	
//-------------------------------------------------------------------------------------------------------
		@SuppressWarnings("unchecked")
		public ModelAndView updateProfils(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			IProfil profilDAO = new ProfilDAO();
			Profil profil = (Profil) request.getSession().getAttribute("profilCourant");
			
			ILangueProfil langProfilDAO = new LangueProfilDAO();
			profilDAO.save(profil);
			
			int cpt = 0;
			ArrayList<LangueProfil> listLibelle = (ArrayList<LangueProfil>) request.getSession().getAttribute("listLibelle");
			while(cpt < listLibelle.size()){
				langProfilDAO.save(listLibelle.get(cpt));
				cpt += 1;
			}
			
			return init(request,response);
		}
		
//---------------------------------------------------------------------------------------------------		
		public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
			
			return init(request,response);
		}
		
//----------------------------------------------------------------------------------------------------		
		public ModelAndView supprimer(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			String codeProfil = request.getParameter("codeProfil");
			if((codeProfil != null) && (codeProfil.trim().length() > 0) && (!codeProfil.equals("AdminUser"))){
				IProfil profilDAO = new ProfilDAO();
				Profil profil = profilDAO.getProfil(codeProfil);
				profilDAO.delete(profil);
			}
			return init(request,response);
		}
		
//---------------------------------------------------------------------------------------------------		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView rechercher(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			//Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			IProfil profilDAO = new ProfilDAO();
			ArrayList<Profil> listProfils = profilDAO.allProfil();
			
			String codeProfil = request.getParameter("codeProfil");
			if((codeProfil != null) && (codeProfil.trim().length() > 0)){
				int cpt = 0;
				ArrayList<Profil> listChercher = new ArrayList<Profil>();
				while(cpt < listProfils.size()){
					if(listProfils.get(cpt).getCodeProfil().equals(codeProfil))
						listChercher.add(listProfils.get(cpt));
					cpt += 1;
				}
				listProfils = listChercher;
			}
			
			String nomenclatureMDP = request.getParameter("nomenclatureMDP");
			if((nomenclatureMDP != null) && (nomenclatureMDP.trim().length()>0)){
				int cpt = 0;
				ArrayList<Profil> listChercher = new ArrayList<Profil>();
				while(cpt < listProfils.size()){
					if(listProfils.get(cpt).getNomenclatureMDP().getId().equals(nomenclatureMDP))
						listChercher.add(listProfils.get(cpt));
					cpt += 1;
				}
				listProfils = listChercher;
			}
			
			String libelle = request.getParameter("libelle");
			if((libelle != null) && (libelle.trim().length() > 0)){
				String langue = request.getParameter("langue");
				int cpt = 0;
				ArrayList<Profil> listChercher = new ArrayList<Profil>();
				while(cpt < listProfils.size()){
					ArrayList<LangueProfil> listlibelle = new ArrayList<LangueProfil>();
					listlibelle.addAll(listProfils.get(cpt).getLibelleProfil());
					int cpt2 = 0;
					while(cpt2 < listlibelle.size()){
						if((listlibelle.get(cpt2).getLangue().getCodeLangue().equals(langue)) && (listlibelle.get(cpt2).getLibelle().equals(libelle))){
							listChercher.add(listProfils.get(cpt));
							cpt2 = listlibelle.size();
						}
						cpt2 += 1;
					}
					cpt += 1;
				}
				listProfils = listChercher;				
			}
			Map modele = new HashMap();
			if(listProfils.size() > 0){
			request.getSession().setAttribute("listProfils", listProfils);
			request.getSession().setAttribute("indiceCourant", 0);
			modele.put("profilCourant", listProfils.get(0));
			
			ArrayList<LangueProfil> libelleProfilCourant = new ArrayList<LangueProfil>();
			libelleProfilCourant.addAll(listProfils.get(0).getLibelleProfil());
			request.getSession().setAttribute("listLibelle", libelleProfilCourant);
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			int bc = 0;
			while(bc < langues.size()){
				int bc2 = 0;
				while(bc2 < libelleProfilCourant.size()){
					if(libelleProfilCourant.get(bc2).getLangue().getCodeLangue().equals(langues.get(bc).getCodeLangue())){
						langues.remove(bc);
						bc -= 1; bc2 = libelleProfilCourant.size();
					}
					bc2 += 1;
				}
				bc +=1;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			request.getSession().setAttribute("langues",langues);
			
			INomenclatureMDP listNomenclature = new NomenclatureMDPDAO();
			ArrayList<NomenclatureMDP> nomenclatureMDPs = listNomenclature.allNomenclatureMDP();
			int cpt = 0;
			while(cpt < nomenclatureMDPs.size()){
				if(nomenclatureMDPs.get(cpt).getId().equals(listProfils.get(0).getNomenclatureMDP().getId()))
					nomenclatureMDPs.remove(cpt);
				cpt +=1;
			}
			modele.put("nomenclatureMDPs",nomenclatureMDPs );
			modele.put("recherche", "ok");
			request.getSession().setAttribute("listMenus",null);
			return new ModelAndView("Profils", modele);
			}
			
			return init(request,response);
		}
		
//------------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ModelAndView premier(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			ArrayList<Profil> listProfils = (ArrayList<Profil>) request.getSession().getAttribute("listProfils");
			if((listProfils != null) && (listProfils.size() > 0)){
			request.getSession().setAttribute("listProfils", listProfils);
			request.getSession().setAttribute("indiceCourant", 0);
			modele.put("profilCourant", listProfils.get(0));
			
			ArrayList<LangueProfil> libelleProfilCourant = new ArrayList<LangueProfil>();
			libelleProfilCourant.addAll(listProfils.get(0).getLibelleProfil());
			request.getSession().setAttribute("listLibelle", libelleProfilCourant);
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			int bc = 0;
			while(bc < langues.size()){
				int bc2 = 0;
				while(bc2 < libelleProfilCourant.size()){
					if(libelleProfilCourant.get(bc2).getLangue().getCodeLangue().equals(langues.get(bc).getCodeLangue())){
						langues.remove(bc);
						bc -= 1; bc2 = libelleProfilCourant.size();
					}
					bc2 += 1;
				}
				bc +=1;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			request.getSession().setAttribute("langues",langues);
			
			INomenclatureMDP listNomenclature = new NomenclatureMDPDAO();
			ArrayList<NomenclatureMDP> nomenclatureMDPs = listNomenclature.allNomenclatureMDP();
			int cpt = 0;
			while(cpt < nomenclatureMDPs.size()){
				if(nomenclatureMDPs.get(cpt).getId().equals(listProfils.get(0).getNomenclatureMDP().getId()))
					nomenclatureMDPs.remove(cpt);
				cpt +=1;
			}
			modele.put("nomenclatureMDPs",nomenclatureMDPs );
			modele.put("recherche", "ok");
			request.getSession().setAttribute("listMenus",null);
			return new ModelAndView("Profils", modele);
			}
			
			return init(request,response);
		}
		
		
//------------------------------------------------------------------------------------------------------		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ModelAndView dernier(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			ArrayList<Profil> listProfils = (ArrayList<Profil>) request.getSession().getAttribute("listProfils");
			if((listProfils != null) && (listProfils.size() > 0)){
			request.getSession().setAttribute("listProfils", listProfils);
			request.getSession().setAttribute("indiceCourant",listProfils.size()-1);
			modele.put("profilCourant", listProfils.get(listProfils.size()-1));
			
			ArrayList<LangueProfil> libelleProfilCourant = new ArrayList<LangueProfil>();
			libelleProfilCourant.addAll(listProfils.get(listProfils.size()-1).getLibelleProfil());
			request.getSession().setAttribute("listLibelle", libelleProfilCourant);
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			int bc = 0;
			while(bc < langues.size()){
				int bc2 = 0;
				while(bc2 < libelleProfilCourant.size()){
					if(libelleProfilCourant.get(bc2).getLangue().getCodeLangue().equals(langues.get(bc).getCodeLangue())){
						langues.remove(bc);
						bc -= 1; bc2 = libelleProfilCourant.size();
					}
					bc2 += 1;
				}
				bc +=1;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			request.getSession().setAttribute("langues",langues);
			
			INomenclatureMDP listNomenclature = new NomenclatureMDPDAO();
			ArrayList<NomenclatureMDP> nomenclatureMDPs = listNomenclature.allNomenclatureMDP();
			int cpt = 0;
			while(cpt < nomenclatureMDPs.size()){
				if(nomenclatureMDPs.get(cpt).getId().equals(listProfils.get(listProfils.size()-1).getNomenclatureMDP().getId()))
					nomenclatureMDPs.remove(cpt);
				cpt +=1;
			}
			modele.put("nomenclatureMDPs",nomenclatureMDPs );
			modele.put("recherche", "ok");
			request.getSession().setAttribute("listMenus",null);
			return new ModelAndView("Profils", modele);
			}
			
			return init(request,response);
		}
		
		
//----------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ModelAndView precedant(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			ArrayList<Profil> listProfils = (ArrayList<Profil>) request.getSession().getAttribute("listProfils");
			if((listProfils != null) && (listProfils.size() > 0)){
			request.getSession().setAttribute("listProfils", listProfils);
			int indice = (Integer) request.getSession().getAttribute("indiceCourant");
			if(indice > 0)
				indice = indice -1;
			request.getSession().setAttribute("indiceCourant",indice);
			modele.put("profilCourant", listProfils.get(indice));
			
			ArrayList<LangueProfil> libelleProfilCourant = new ArrayList<LangueProfil>();
			libelleProfilCourant.addAll(listProfils.get(indice).getLibelleProfil());
			request.getSession().setAttribute("listLibelle", libelleProfilCourant);
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			int bc = 0;
			while(bc < langues.size()){
				int bc2 = 0;
				while(bc2 < libelleProfilCourant.size()){
					if(libelleProfilCourant.get(bc2).getLangue().getCodeLangue().equals(langues.get(bc).getCodeLangue())){
						langues.remove(bc);
						bc -= 1; bc2 = libelleProfilCourant.size();
					}
					bc2 += 1;
				}
				bc +=1;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			request.getSession().setAttribute("langues",langues);
			
			INomenclatureMDP listNomenclature = new NomenclatureMDPDAO();
			ArrayList<NomenclatureMDP> nomenclatureMDPs = listNomenclature.allNomenclatureMDP();
			int cpt = 0;
			while(cpt < nomenclatureMDPs.size()){
				if(nomenclatureMDPs.get(cpt).getId().equals(listProfils.get(indice).getNomenclatureMDP().getId()))
					nomenclatureMDPs.remove(cpt);
				cpt +=1;
			}
			modele.put("nomenclatureMDPs",nomenclatureMDPs );
			modele.put("recherche", "ok");
			request.getSession().setAttribute("listMenus",null);
			return new ModelAndView("Profils", modele);
			}
			
			return init(request,response);
		}
		
		
//-------------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ModelAndView suivant(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			ArrayList<Profil> listProfils = (ArrayList<Profil>) request.getSession().getAttribute("listProfils");
			if((listProfils != null) && (listProfils.size() > 0)){
			request.getSession().setAttribute("listProfils", listProfils);
			int indice = (Integer) request.getSession().getAttribute("indiceCourant");
			if(indice < listProfils.size() - 1)
				indice = indice + 1;
			request.getSession().setAttribute("indiceCourant",indice);
			modele.put("profilCourant", listProfils.get(indice));
			
			ArrayList<LangueProfil> libelleProfilCourant = new ArrayList<LangueProfil>();
			libelleProfilCourant.addAll(listProfils.get(indice).getLibelleProfil());
			request.getSession().setAttribute("listLibelle", libelleProfilCourant);
			
			ILangue langDAO = new LangueDAO();
			ArrayList<Langue> langues = langDAO.allLangue();
			int bc = 0;
			while(bc < langues.size()){
				int bc2 = 0;
				while(bc2 < libelleProfilCourant.size()){
					if(libelleProfilCourant.get(bc2).getLangue().getCodeLangue().equals(langues.get(bc).getCodeLangue())){
						langues.remove(bc);
						bc -= 1; bc2 = libelleProfilCourant.size();
					}
					bc2 += 1;
				}
				bc +=1;
			}
			if(langues.size()>0)
				modele.put("affLangue", "ok");
			request.getSession().setAttribute("langues",langues);
			
			INomenclatureMDP listNomenclature = new NomenclatureMDPDAO();
			ArrayList<NomenclatureMDP> nomenclatureMDPs = listNomenclature.allNomenclatureMDP();
			int cpt = 0;
			while(cpt < nomenclatureMDPs.size()){
				if(nomenclatureMDPs.get(cpt).getId().equals(listProfils.get(indice).getNomenclatureMDP().getId()))
					nomenclatureMDPs.remove(cpt);
				cpt +=1;
			}
			modele.put("nomenclatureMDPs",nomenclatureMDPs );
			modele.put("recherche", "ok");
			request.getSession().setAttribute("listMenus",null);
			return new ModelAndView("Profils", modele);
			}
			
			return init(request,response);
		}
		
//-----------------------------------------------------------------------------------------------------------------------		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView editerLibelle(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			ArrayList<LangueProfil> listLibelle = (ArrayList<LangueProfil>) request.getSession().getAttribute("listLibelle");
			
			String codeProfil = request.getParameter("codeProfil");
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
			
			ILangueProfil langProfDAO = new LangueProfilDAO();
			LangueProfil langProf = langProfDAO.getLangueProfil(langue, codeProfil);
			if(langProf != null)
				langProfDAO.delete(langProf);
			
			IProfil profDAO = new ProfilDAO();
			Profil profil = profDAO.getProfil(codeProfil);
			profil.setUtilModif(utilisateur);
			profil.setDateModif(new Date());
			profDAO.save(profil);
			
			ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
			request.getSession().setAttribute("langues",langues);
			request.getSession().setAttribute("listLibelle",listLibelle);
			modele.put("affLangue", "ok");
			modele.put("profilCourant", profil);
			
			return new ModelAndView("Profils", modele);
		}
		
//-----------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView deleteLibelle(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			ArrayList<LangueProfil> listLibelle = (ArrayList<LangueProfil>) request.getSession().getAttribute("listLibelle");
			
			String codeProfil = request.getParameter("codeProfil");
			String langue = request.getParameter("libelleEdit");
			
			if(listLibelle.size() == 1){
				langue = listLibelle.get(0).getLangue().getCodeLangue();
				listLibelle.remove(0);
			}
			else{
				int cpt = 0;
				while(cpt <listLibelle.size()){
					if(listLibelle.get(cpt).getLangue().getCodeLangue().equals(langue)){			
						listLibelle.remove(cpt);	
						cpt = listLibelle.size();
					}
					cpt += 1;
				}
			}
			
			request.getSession().setAttribute("listLibelle",listLibelle);
			
			ILangueProfil langProfDAO = new LangueProfilDAO();
			LangueProfil langProf = langProfDAO.getLangueProfil(langue, codeProfil);
			if(langProf != null)
				langProfDAO.delete(langProf);
			
			IProfil profDAO = new ProfilDAO();
			Profil profil = profDAO.getProfil(codeProfil);
			profil.setUtilModif(utilisateur);
			profil.setDateModif(new Date());
			profDAO.save(profil);
			
			ArrayList<Langue> langues = (ArrayList<Langue>) request.getSession().getAttribute("langues");
			ILangue langDAO = new LangueDAO();
			langues.add(langDAO.getLangue(langue));
			request.getSession().setAttribute("langues",langues);
			request.getSession().setAttribute("listLibelle",listLibelle);
			modele.put("affLangue", "ok");
			modele.put("profilCourant", profDAO.getProfil(codeProfil));
			
			return new ModelAndView("Profils", modele);
		}
		
//----------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView ajouterMenus(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			
			String codeProfil = request.getParameter("codeProfil");
			IProfil profDAO = new ProfilDAO();
			Profil profilCourant = profDAO.getProfil(codeProfil);
			
			ArrayList<Menus> listMenus = new ArrayList<Menus>();
			listMenus.addAll(profilCourant.getMenus());
				
			String menus = request.getParameter("menus");
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getCodeMenus().equals(menus)){
					listMenus.get(cpt).setProfilEffectif(1);
					profDAO.save(profilCourant);
					cpt = listMenus.size();
				}
				cpt ++;
			}
			
			profilCourant.setMenus(listMenus);
			modele.put("profilCourant", profilCourant);
			modele.put("recherche", "ok");
			return new ModelAndView("Profils", modele);
		}
		
//----------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView deleteMenus(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			
			String codeProfil = request.getParameter("codeProfil");
			IProfil profDAO = new ProfilDAO();
			Profil profilCourant = profDAO.getProfil(codeProfil);
			
			ArrayList<Menus> listMenus = new ArrayList<Menus>();
			listMenus.addAll(profilCourant.getMenus());
				
			String menus = request.getParameter("libelleEdit");
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getCodeMenus().equals(menus)){
					listMenus.get(cpt).setProfilEffectif(0);
					profDAO.save(profilCourant);
					cpt = listMenus.size();
				}
				cpt ++;
			}
			
			profilCourant.setMenus(listMenus);
			modele.put("profilCourant", profilCourant);
			modele.put("recherche", "ok");
			return new ModelAndView("Profils", modele);
		}
		
}
