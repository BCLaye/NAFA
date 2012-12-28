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
import modele.dao.ILangueFonctionnalites;
import modele.dao.ILangueProfil;
import modele.dao.IProfil;
import modele.dao.IValidationFonctionnalites;
import modele.dao.LangueFonctionnalitesDAO;
import modele.dao.LangueProfilDAO;
import modele.dao.ProfilDAO;
import modele.dao.ValidationFonctionnalitesDAO;
import modele.metiers.FonctionnalitesProfil;
import modele.metiers.LangueFonctionnalites;
import modele.metiers.LangueProfil;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;
import modele.metiers.ValidationFonctionnalites;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FonctionnalitesProfilController extends MultiActionController {
	  
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
			
			ILangueProfil langProfilDAO = new LangueProfilDAO();
			modele.put("listProfil1", langProfilDAO.allLangueProfil(utilConnecte.getLangue().getCodeLangue()));
			modele.put("listProfil2", langProfilDAO.allLangueProfil(utilConnecte.getLangue().getCodeLangue()));
			
			ILangueFonctionnalites langFctionnaliteDAO = new LangueFonctionnalitesDAO();
			modele.put("listFctionnalites", langFctionnaliteDAO.allLangueFonctionnalite(utilConnecte.getLangue().getCodeLangue()));
			return new ModelAndView("FonctionnalitesProfil", modele);
	}
	  
//-----------------------------------------------------------------------------------------------------------------	  
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView selectProfil(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
		  FonctionnalitesProfil fctionnaliteProfil = new FonctionnalitesProfil();
		  if(request.getSession().getAttribute("fctionnaliteProfilCourant") != null)
			  fctionnaliteProfil = (FonctionnalitesProfil) request.getSession().getAttribute("fctionnaliteProfilCourant");
		  
		  ILangueProfil langProfilDAO = new LangueProfilDAO();
		  ArrayList<LangueProfil> listProfil1, listProfil2;
		  if(request.getSession().getAttribute("listProfil1") != null)
			  listProfil1 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil1");
		  else
			  listProfil1 = langProfilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
		  
		  if(request.getSession().getAttribute("listProfil2") != null)
			  listProfil2 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil2");
		  else
			  listProfil2 = langProfilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
		  
		  ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
		  ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
		  if(request.getSession().getAttribute("listFctionnalites") != null)
			  listFctionnalites = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listFctionnalites");
		  else
			  listFctionnalites = langFctDAO.allLangueFonctionnalite(utilisateur.getLangue().getCodeLangue());
		  
		  String profil = request.getParameter("profil");
		  if((profil == null) || (profil.trim().length() == 0)){
			  request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
			  modele.put("errorProfil", utilisateur.getLangue().getChampObligatoire());
			  modele.put("listProfil1", listProfil1);
			  modele.put("listProfil2", listProfil2);
			  modele.put("listFctionnalites", listFctionnalites);
			  return new ModelAndView("FonctionnalitesProfil", modele);
		  }
		  else{
			  
			  if((fctionnaliteProfil.getProfil() == null) || (!fctionnaliteProfil.getProfil().getCodeProfil().equals(profil))){
				  IProfil profilDAO = new ProfilDAO();
				  fctionnaliteProfil.setProfil(profilDAO.getProfil(profil));
			  }
			  
			  int cpt2 = 0;
			  while(cpt2 < listProfil1.size()){
				  if(listProfil1.get(cpt2).getProfil().getCodeProfil().equals(profil)){
					  request.getSession().setAttribute("profilSelect", listProfil1.get(cpt2));
					  listProfil1.remove(cpt2);
					  cpt2 = listProfil1.size();
				  }
				  cpt2 += 1;
			  }
			  
			  ArrayList<FonctionnalitesProfil> listFct = new ArrayList<FonctionnalitesProfil>();
			  listFct.addAll(fctionnaliteProfil.getProfil().getFonctionnalitesProfil());
			  if((listFct != null) && (listFct.size() > 0))
				  modele.put("thtab", "ok");
			  int cpt = 0;
			  while(cpt < listFctionnalites.size()){
				  int cpt1 = 0;
				  while(cpt1 < listFct.size()){
					  if(listFctionnalites.get(cpt).getFonctionnalite().getCodeFonctionnalite().equals(listFct.get(cpt1).getFonctionnalite().getCodeFonctionnalite())){
						  listFctionnalites.remove(cpt);
						  cpt -= 1; cpt1 = listFct.size();
					  }
					  cpt1 += 1;
				  }
				  cpt += 1;
			  }
			  request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
			  modele.put("listProfil1", listProfil1);
			  modele.put("listProfil2", listProfil2);
			  modele.put("listFctionnalites", listFctionnalites);
			  return new ModelAndView("FonctionnalitesProfil", modele); 
	  }
	 }
	  
//--------------------------------------------------------------------------------------------------------------- 
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView nombreValidation(HttpServletRequest request, HttpServletResponse response){
		  
		  if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
		  FonctionnalitesProfil fctionnaliteProfil = new FonctionnalitesProfil();
		  if(request.getSession().getAttribute("fctionnaliteProfilCourant") != null)
			  fctionnaliteProfil = (FonctionnalitesProfil) request.getSession().getAttribute("fctionnaliteProfilCourant");
		  
		  ILangueProfil langProfilDAO = new LangueProfilDAO();
		  ArrayList<LangueProfil> listProfil1, listProfil2;
		  if(request.getSession().getAttribute("listProfil1") != null)
			  listProfil1 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil1");
		  else
			  listProfil1 = langProfilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
		  
		  if(request.getSession().getAttribute("listProfil2") != null)
			  listProfil2 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil2");
		  else
			  listProfil2 = langProfilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue());
		  
		  ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
		  ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
		  if(request.getSession().getAttribute("listFctionnalites") != null)
			  listFctionnalites = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listFctionnalites");
		  else
			  listFctionnalites = langFctDAO.allLangueFonctionnalite(utilisateur.getLangue().getCodeLangue());
		  
		  String profil = request.getParameter("profil");
		  if((profil == null) || (profil.trim().length() == 0)){
			  request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
			  modele.put("errorProfil", utilisateur.getLangue().getChampObligatoire());
			  modele.put("listProfil1", listProfil1);
			  modele.put("listProfil2", listProfil2);
			  modele.put("listFctionnalites", listFctionnalites);
			  return new ModelAndView("FonctionnalitesProfil", modele);
		  }
		  else{
			  
			  if((fctionnaliteProfil.getProfil() == null) || (!fctionnaliteProfil.getProfil().getCodeProfil().equals(profil))){
				  IProfil profilDAO = new ProfilDAO();
				  fctionnaliteProfil.setProfil(profilDAO.getProfil(profil));
			  }
			  int cpt = 0;
			  while(cpt < listProfil1.size()){
				  if(listProfil1.get(cpt).getProfil().getCodeProfil().equals(profil)){
					  request.getSession().setAttribute("profilSelect", listProfil1.get(cpt));
					  listProfil1.remove(cpt);
					  cpt = listProfil1.size();
				  }
				  cpt += 1;
			  }
			  
			  ArrayList<FonctionnalitesProfil> listFct = new ArrayList<FonctionnalitesProfil>();
			  listFct.addAll(fctionnaliteProfil.getProfil().getFonctionnalitesProfil());
			  int bc = 0;
			  while(bc < listFctionnalites.size()){
				  int bc2 = 0;
				  while(bc2 < listFct.size()){
					  if(listFctionnalites.get(bc).getFonctionnalite().getCodeFonctionnalite().equals(listFct.get(bc2).getFonctionnalite().getCodeFonctionnalite())){
						  listFctionnalites.remove(bc);
						  bc -= 1; bc2 = listFct.size();
					  }
					  bc2 += 1;
				  }
				  bc +=1;
			  }
			  request.getSession().setAttribute("listFctionnalites", listFctionnalites);
			  
			  String fction = request.getParameter("fction");
			  if((fction == null) || (fction.trim().length() == 0)){
				  request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
				  modele.put("errorFctionnalite", utilisateur.getLangue().getChampObligatoire());
				  modele.put("listProfil1", listProfil1);
				  modele.put("listProfil2", listProfil2);
				  modele.put("listFctionnalites", listFctionnalites);
				  return new ModelAndView("FonctionnalitesProfil", modele);
			  }
			  else{
				  
				  if((fctionnaliteProfil.getFonctionnalite() == null) || (!fctionnaliteProfil.getFonctionnalite().getCodeFonctionnalite().equals(fction))){
					  IFonctionnalites fctionnaliteDA0 = new FonctionnalitesDAO();
					  fctionnaliteProfil.setFonctionnalite(fctionnaliteDA0.getFonctionnalite(fction));
				  }
				  int cpt2 = 0;
				  while(cpt2 < listFctionnalites.size()){
					  if(listFctionnalites.get(cpt2).getFonctionnalite().getCodeFonctionnalite().equals(fction)){
						  request.getSession().setAttribute("fctionnaliteSelect", listFctionnalites.get(cpt2));
						  listFctionnalites.remove(cpt2);
						  cpt2 = listFctionnalites.size();
					  }
					  cpt2 += 1;
				  }
				  
				  String nbValid = request.getParameter("nbValidReq");
				  if((nbValid == null) || (nbValid.trim().length() == 0)){
					  request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
					  modele.put("errorNbValid", utilisateur.getLangue().getChampObligatoire());
					  modele.put("listProfil1", listProfil1);
					  modele.put("listProfil2", listProfil2);
					  modele.put("listFctionnalites", listFctionnalites);
					  return new ModelAndView("FonctionnalitesProfil", modele);
				  }
				  else{
					  int nbValidReq = 0;
					  try{
						  nbValidReq = Integer.parseInt(nbValid);
					  }
					  catch(NumberFormatException ex){
						  request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
						  modele.put("errorFormat",utilisateur.getLangue().getFormatInvalide());
						  modele.put("listProfil1", listProfil1);
						  modele.put("listProfil2", listProfil2);
						  modele.put("listFctionnalites", listFctionnalites);
						  return new ModelAndView("FonctionnalitesProfil", modele);
					  }
					  if(nbValidReq < 2){
						  request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
						  modele.put("errorNbValidReq",utilisateur.getLangue().getNbInsuffisant());
						  modele.put("listProfil1", listProfil1);
						  modele.put("listProfil2", listProfil2);
						  modele.put("listFctionnalites", listFctionnalites);
						  return new ModelAndView("FonctionnalitesProfil", modele);
					  }
					  fctionnaliteProfil.setNbValidReq(nbValidReq);
					  fctionnaliteProfil.setNbValidEffect(0);
					  fctionnaliteProfil.setUtilCree(utilisateur);
					  fctionnaliteProfil.setDateCree(new Date());
					  fctionnaliteProfil.setFonctionnaliteEffective(0);
					  fctionnaliteProfil.setId(fctionnaliteProfil.getFonctionnalite(), fctionnaliteProfil.getProfil());
					  
					 // fctionnaliteProfil.getProfil().getFonctionnalitesProfil().add(fctionnaliteProfil);
					  
					  request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
					  request.getSession().setAttribute("rangValidation", 1);
					  request.getSession().setAttribute("listProfil1", listProfil1);
					  request.getSession().setAttribute("listProfil2", listProfil2);
					  request.getSession().setAttribute("listFctionnalites", listFctionnalites);
					  modele.put("affichTab", "ok");
					  modele.put("ajouterValid", "ok");
					  return new ModelAndView("FonctionnalitesProfil", modele);
				  }
			  }
		  }
		  
	  }
	  
//-----------------------------------------------------------------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView ajouterValidation(HttpServletRequest request, HttpServletResponse response){
		  
		  if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			ArrayList<ValidationFonctionnalites> listValidation = new ArrayList<ValidationFonctionnalites>();
			if(request.getSession().getAttribute("listValidation") != null)
				listValidation = (ArrayList<ValidationFonctionnalites>) request.getSession().getAttribute("listValidation");
			
			FonctionnalitesProfil fctionnaliteProfil = (FonctionnalitesProfil) request.getSession().getAttribute("fctionnaliteProfilCourant");
			int rangValidation = (Integer) request.getSession().getAttribute("rangValidation");
			
			 ArrayList<LangueProfil> listProfil1 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil1");
			 ArrayList<LangueProfil> listProfil2 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil2");
			
			ValidationFonctionnalites validFctionnalite = new ValidationFonctionnalites();
			
			String codeProfValid = request.getParameter("profilValid");
			int cpt = 0;
			while(cpt < listProfil2.size()){
				if(listProfil2.get(cpt).getProfil().getCodeProfil().equals(codeProfValid)){
					listProfil2.remove(cpt);
				    cpt = listProfil2.size();	
				}
				cpt += 1;
			}
			
			validFctionnalite.setProfil(fctionnaliteProfil.getProfil());
			validFctionnalite.setFonctionnalite(fctionnaliteProfil.getFonctionnalite());
			
			IProfil profilDAO = new ProfilDAO();
			Profil profilValid = profilDAO.getProfil(codeProfValid);
			validFctionnalite.setProfilValidation(profilValid);
			
			validFctionnalite.setRangProfilValid(rangValidation);
			validFctionnalite.setNbValidEffect(0);
			validFctionnalite.setId(validFctionnalite.getProfil(), validFctionnalite.getFonctionnalite(), validFctionnalite.getProfilValidation());
			 
			listValidation.add(validFctionnalite);
			
			fctionnaliteProfil.getProfil().getFonctionnalitesValide().add(validFctionnalite);
			if(rangValidation < fctionnaliteProfil.getNbValidReq()){
				rangValidation += 1;
				request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
				request.getSession().setAttribute("rangValidation", rangValidation);
			    modele.put("listValidation", listValidation);
				modele.put("listProfil1", listProfil1);
				request.getSession().setAttribute("listProfil2", listProfil2);
				request.getSession().setAttribute("listValidation",listValidation);
				modele.put("affichTab", "ok");
				modele.put("ajouterValid", "ok");
				  return new ModelAndView("FonctionnalitesProfil", modele);
				}
			else{
				request.getSession().setAttribute("ajouterFonctionnalite", "ok");
				modele.put("affichTab", "ok");
				return new ModelAndView("FonctionnalitesProfil", modele);
			}
	  }
	
//----------------------------------------------------------------------------------------------------------------
	 @SuppressWarnings({"rawtypes", "unchecked" })
	public ModelAndView ajouterFonctionnalite(HttpServletRequest request, HttpServletResponse response){
		  
		  if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			if(request.getSession().getAttribute("ajouterFonctionnalite") == null)
				return nombreValidation(request,response);
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			FonctionnalitesProfil fctionnaliteProfil = new FonctionnalitesProfil(), ancienfct = (FonctionnalitesProfil) request.getSession().getAttribute("fctionnaliteProfilCourant");
			fctionnaliteProfil.setProfil(ancienfct.getProfil());
			fctionnaliteProfil.getProfil().getFonctionnalitesProfil().add(ancienfct);
			modele.put("thtab", "ok");
			request.getSession().setAttribute("listValidation", null);
			request.getSession().setAttribute("fctionnaliteProfilCourant",fctionnaliteProfil);
			
			ILangueProfil langProfilDAO = new LangueProfilDAO();
			request.getSession().setAttribute("listProfil2", langProfilDAO.allLangueProfil(utilisateur.getLangue().getCodeLangue()));
			return new ModelAndView("FonctionnalitesProfil", modele);
			}
	 
//--------------------------------------------------------------------------------------------------------------------------------
	 //@SuppressWarnings("rawtypes")
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			if(request.getSession().getAttribute("ajouterFonctionnalite") == null)
				return nombreValidation(request,response);
			
			FonctionnalitesProfil fctionnaliteProfil = new FonctionnalitesProfil(), ancienfct = (FonctionnalitesProfil) request.getSession().getAttribute("fctionnaliteProfilCourant");
			fctionnaliteProfil.setProfil(ancienfct.getProfil());
			fctionnaliteProfil.getProfil().getFonctionnalitesProfil().add(ancienfct);
			
			IFonctionnalitesProfil fctProfilDAO = new FonctionnalitesProfilDAO();
			fctProfilDAO.save(fctionnaliteProfil.getProfil().getFonctionnalitesProfil());
			
			IValidationFonctionnalites validDAO = new ValidationFonctionnalitesDAO();
			validDAO.save(fctionnaliteProfil.getProfil().getFonctionnalitesValide());
	   return init(request,response);		
	 }
	 
//--------------------------------------------------------------------------------------------------------------------
	 public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
			
			return init(request,response);
		}
	 
//--------------------------------------------------------------------------------------------------------------------	 
	 public ModelAndView supprimer(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			 String prof = request.getParameter("profil");
			  if((prof != null) && (prof.trim().length() > 0)){
				  IProfil profilDAO = new ProfilDAO();
				  Profil profil = profilDAO.getProfil(prof);
				  if(profil != null){
					  
					  IValidationFonctionnalites fctValid = new ValidationFonctionnalitesDAO();
					  fctValid.delete(profil.getFonctionnalitesValide());
					  
					  IFonctionnalitesProfil fctProfDAO = new FonctionnalitesProfilDAO();
					  fctProfDAO.delete(profil.getFonctionnalitesProfil());
				  }
			  }
			
			return init(request,response);
		}
	 
//-----------------------------------------------------------------------------------------------------------------	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView rechercher(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			IFonctionnalitesProfil fctProfilDAO = new FonctionnalitesProfilDAO();
			ArrayList<Profil> listProfil = fctProfilDAO.AllProfil();
			
			ILangueProfil langProfilDAO = new LangueProfilDAO();
			ArrayList<LangueProfil>  listProfil1 = new ArrayList<LangueProfil>();
			int cpt = 0;
			while(cpt < listProfil.size()){
				if(langProfilDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(),listProfil.get(cpt).getCodeProfil()) != null)
					listProfil1.add(langProfilDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(),listProfil.get(cpt).getCodeProfil()));
				cpt += 1;
			}
			
			ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
			ArrayList<LangueFonctionnalites> listFctionnalites = langFctDAO.allLangueFonctionnalite(utilisateur.getLangue().getCodeLangue());
			
			if(listProfil1.size() > 0){
				
				ArrayList<FonctionnalitesProfil> listFctProfil = new ArrayList<FonctionnalitesProfil>();
				listFctProfil.addAll(listProfil1.get(0).getProfil().getFonctionnalitesProfil());
				
				FonctionnalitesProfil fctProfil = new FonctionnalitesProfil();
				fctProfil.setProfil(listFctProfil.get(0).getProfil());
				
				int bc = 0;
				while(bc < listFctionnalites.size()){
					int bc2 = 0;
					while(bc2 < listFctProfil.size()){
						if(listFctionnalites.get(bc).getFonctionnalite().getCodeFonctionnalite().equals(listFctProfil.get(bc2).getFonctionnalite().getCodeFonctionnalite())){
							listFctionnalites.remove(bc);
							bc -= 1; bc2 = listFctProfil.size();
						}
						bc2 += 1;
					}
					bc += 1;
				}
				
				request.getSession().setAttribute("listProfil",listProfil1);
				request.getSession().setAttribute("listFctionnalites",listFctionnalites);
				request.getSession().setAttribute("indiceCourant",0);
				modele.put("profilSelect", listProfil1.get(0));
				if(fctProfil.getProfil().getFonctionnalitesProfil() != null)
					modele.put("thtab","ok");
				request.getSession().setAttribute("fctionnaliteProfilCourant",fctProfil); 
			}
	   return new ModelAndView("FonctionnalitesProfil", modele);		
	 }
	 
//------------------------------------------------------------------------------------------------------------------	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView premier(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
			ArrayList<LangueFonctionnalites> listFctionnalites = langFctDAO.allLangueFonctionnalite(utilisateur.getLangue().getCodeLangue());
			
			if(request.getSession().getAttribute("listProfil") != null){
				ArrayList<LangueProfil> listProfil1 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil");
				if(listProfil1.size() > 0){
					ArrayList<FonctionnalitesProfil> listFctProfil = new ArrayList<FonctionnalitesProfil>();
					listFctProfil.addAll(listProfil1.get(0).getProfil().getFonctionnalitesProfil());
					
					FonctionnalitesProfil fctProfil = new FonctionnalitesProfil();
					fctProfil.setProfil(listFctProfil.get(0).getProfil());
					
					int bc = 0;
					while(bc < listFctionnalites.size()){
						int bc2 = 0;
						while(bc2 < listFctProfil.size()){
							if(listFctionnalites.get(bc).getFonctionnalite().getCodeFonctionnalite().equals(listFctProfil.get(bc2).getFonctionnalite().getCodeFonctionnalite())){
								listFctionnalites.remove(bc);
								bc -= 1; bc2 = listFctProfil.size();
							}
							bc2 += 1;
						}
						bc += 1;
					}
					
					request.getSession().setAttribute("listFctionnalites",listFctionnalites);
					request.getSession().setAttribute("indiceCourant",0);
					modele.put("profilSelect", listProfil1.get(0));
					if(fctProfil.getProfil().getFonctionnalitesProfil() != null)
						modele.put("thtab","ok");
					request.getSession().setAttribute("fctionnaliteProfilCourant",fctProfil);
				} 
			} 
			
			return new ModelAndView("FonctionnalitesProfil", modele);
	 }
	 

//----------------------------------------------------------------------------------------------------------------------------	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView dernier(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
			ArrayList<LangueFonctionnalites> listFctionnalites = langFctDAO.allLangueFonctionnalite(utilisateur.getLangue().getCodeLangue());
			
			if(request.getSession().getAttribute("listProfil") != null){
				ArrayList<LangueProfil> listProfil1 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil");
				if(listProfil1.size() > 0){
					int indice = listProfil1.size() - 1;
					ArrayList<FonctionnalitesProfil> listFctProfil = new ArrayList<FonctionnalitesProfil>();
					listFctProfil.addAll(listProfil1.get(indice).getProfil().getFonctionnalitesProfil());
					
					FonctionnalitesProfil fctProfil = new FonctionnalitesProfil();
					fctProfil.setProfil(listFctProfil.get(0).getProfil());
					
					int bc = 0;
					while(bc < listFctionnalites.size()){
						int bc2 = 0;
						while(bc2 < listFctProfil.size()){
							if(listFctionnalites.get(bc).getFonctionnalite().getCodeFonctionnalite().equals(listFctProfil.get(bc2).getFonctionnalite().getCodeFonctionnalite())){
								listFctionnalites.remove(bc);
								bc -= 1; bc2 = listFctProfil.size();
							}
							bc2 += 1;
						}
						bc += 1;
					}
					
					request.getSession().setAttribute("listFctionnalites",listFctionnalites);
					request.getSession().setAttribute("indiceCourant",indice);
					modele.put("profilSelect", listProfil1.get(indice));
					if(fctProfil.getProfil().getFonctionnalitesProfil() != null)
						modele.put("thtab","ok");
					request.getSession().setAttribute("fctionnaliteProfilCourant",fctProfil); 
					
				}
			}
			
			return new ModelAndView("FonctionnalitesProfil", modele);
	 }
	 
//-------------------------------------------------------------------------------------------------------------------------------	 
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView precedant(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
			ArrayList<LangueFonctionnalites> listFctionnalites = langFctDAO.allLangueFonctionnalite(utilisateur.getLangue().getCodeLangue());
			
			if(request.getSession().getAttribute("listProfil") != null){
				ArrayList<LangueProfil> listProfil1 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil");
				if(listProfil1.size() > 0){
					int indice = (Integer) request.getSession().getAttribute("indiceCourant");
					if(indice > 0)
						indice -= 1;
					ArrayList<FonctionnalitesProfil> listFctProfil = new ArrayList<FonctionnalitesProfil>();
					listFctProfil.addAll(listProfil1.get(indice).getProfil().getFonctionnalitesProfil());
					
					FonctionnalitesProfil fctProfil = new FonctionnalitesProfil();
					fctProfil.setProfil(listFctProfil.get(0).getProfil());
					
					int bc = 0;
					while(bc < listFctionnalites.size()){
						int bc2 = 0;
						while(bc2 < listFctProfil.size()){
							if(listFctionnalites.get(bc).getFonctionnalite().getCodeFonctionnalite().equals(listFctProfil.get(bc2).getFonctionnalite().getCodeFonctionnalite())){
								listFctionnalites.remove(bc);
								bc -= 1; bc2 = listFctProfil.size();
							}
							bc2 += 1;
						}
						bc += 1;
					}
					
					request.getSession().setAttribute("listFctionnalites",listFctionnalites);
					request.getSession().setAttribute("indiceCourant",indice);
					modele.put("profilSelect", listProfil1.get(indice));
					if(fctProfil.getProfil().getFonctionnalitesProfil() != null)
						modele.put("thtab","ok");
					request.getSession().setAttribute("fctionnaliteProfilCourant",fctProfil); 
					
				}
			}
			
			return new ModelAndView("FonctionnalitesProfil", modele);
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
			
			ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
			ArrayList<LangueFonctionnalites> listFctionnalites = langFctDAO.allLangueFonctionnalite(utilisateur.getLangue().getCodeLangue());
			
			if(request.getSession().getAttribute("listProfil") != null){
				ArrayList<LangueProfil> listProfil1 = (ArrayList<LangueProfil>) request.getSession().getAttribute("listProfil");
				if(listProfil1.size() > 0){
					int indice = (Integer) request.getSession().getAttribute("indiceCourant");
					if(indice < listProfil1.size() -1)
						indice += 1;
					ArrayList<FonctionnalitesProfil> listFctProfil = new ArrayList<FonctionnalitesProfil>();
					listFctProfil.addAll(listProfil1.get(indice).getProfil().getFonctionnalitesProfil());
					
					FonctionnalitesProfil fctProfil = new FonctionnalitesProfil();
					fctProfil.setProfil(listFctProfil.get(0).getProfil());
					
					int bc = 0;
					while(bc < listFctionnalites.size()){
						int bc2 = 0;
						while(bc2 < listFctProfil.size()){
							if(listFctionnalites.get(bc).getFonctionnalite().getCodeFonctionnalite().equals(listFctProfil.get(bc2).getFonctionnalite().getCodeFonctionnalite())){
								listFctionnalites.remove(bc);
								bc -= 1; bc2 = listFctProfil.size();
							}
							bc2 += 1;
						}
						bc += 1;
					}
					
					request.getSession().setAttribute("listFctionnalites",listFctionnalites);
					request.getSession().setAttribute("indiceCourant",indice);
					modele.put("profilSelect", listProfil1.get(indice));
					if(fctProfil.getProfil().getFonctionnalitesProfil() != null)
						modele.put("thtab","ok");
					request.getSession().setAttribute("fctionnaliteProfilCourant",fctProfil); 
					
				}
			}
			
			return new ModelAndView("FonctionnalitesProfil", modele);
	 }

}
