package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.FonctionnalitesProfilDAO;
import modele.dao.IFonctionnalitesProfil;
import modele.dao.ILangueFonctionnalites;
import modele.dao.ILangueProfil;
import modele.dao.IValidationFonctionnalites;
import modele.dao.LangueFonctionnalitesDAO;
import modele.dao.LangueProfilDAO;
import modele.dao.ValidationFonctionnalitesDAO;
import modele.metiers.Fonctionnalites;
import modele.metiers.FonctionnalitesProfil;
import modele.metiers.LangueFonctionnalites;
import modele.metiers.LangueProfil;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;
import modele.metiers.ValidationFonctionnalites;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ValidationControlleur extends MultiActionController {
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
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
		
		IValidationFonctionnalites validationDAO = new ValidationFonctionnalitesDAO();
		ArrayList<Profil> listProf = validationDAO.allProfil();
		
		int cpt = 0;
		ILangueProfil langProfDAO = new LangueProfilDAO();
		ArrayList<LangueProfil> listProfil = new ArrayList<LangueProfil>();
		while(cpt < listProf.size()){
			if(langProfDAO.getLangueProfil(utilConnecte.getLangue().getCodeLangue(), listProf.get(cpt).getCodeProfil()) != null)
				listProfil.add(langProfDAO.getLangueProfil(utilConnecte.getLangue().getCodeLangue(), listProf.get(cpt).getCodeProfil()));
			cpt += 1;
		}
		
		request.getSession().setAttribute("listProfil",listProfil);
		return new ModelAndView("Validations",modele);
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView selectProfil(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		String codeprof = request.getParameter("profil");
		IValidationFonctionnalites validationDAO = new ValidationFonctionnalitesDAO();
		ArrayList<Fonctionnalites> listFct = validationDAO.allFonctionnalites(codeprof);
		
		ValidationFonctionnalites validationCourant = new ValidationFonctionnalites();
		
		ArrayList<Profil> listProf = validationDAO.allProfil();
		
		int cpt1 = 0;
		ILangueProfil langProfDAO = new LangueProfilDAO();
		ArrayList<LangueProfil> listProfil = new ArrayList<LangueProfil>();
		while(cpt1 < listProf.size()){
			if(langProfDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(), listProf.get(cpt1).getCodeProfil()) != null)
				listProfil.add(langProfDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(), listProf.get(cpt1).getCodeProfil()));
			cpt1 += 1;
		}
		int bc = 0;
		while(bc < listProfil.size()){
			if(listProfil.get(bc).getProfil().getCodeProfil().equals(codeprof)){
				validationCourant.setProfil(listProfil.get(bc).getProfil());
				request.getSession().setAttribute("profilSelect", listProfil.get(bc).getLibelle());
				listProfil.remove(bc);
				bc = listProfil.size();
			}
			bc += 1;
		}
		
		int cpt = 0;
		ILangueFonctionnalites langFctDAO = new LangueFonctionnalitesDAO();
		ArrayList<LangueFonctionnalites> listFctionnalites = new ArrayList<LangueFonctionnalites>();
		while(cpt < listFct.size()){
			if(langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), listFct.get(cpt).getCodeFonctionnalite()) != null)
				listFctionnalites.add(langFctDAO.getLangueFonctionnalite(utilisateur.getLangue().getCodeLangue(), listFct.get(cpt).getCodeFonctionnalite()));
			cpt += 1;
		}
		request.getSession().setAttribute("validationCourant", validationCourant);
		request.getSession().setAttribute("listProfil",listProfil);
		request.getSession().setAttribute("listFctionnalites",listFctionnalites);
		request.getSession().setAttribute("FctSelect", null);
		return new ModelAndView("Validations",modele);
	}
	
//-------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public ModelAndView selectFonctionnalite(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
		
		String codeprof = request.getParameter("profil");
		String codeFct = request.getParameter("fctionnalite");
		ArrayList<LangueFonctionnalites> listFctionnalites = (ArrayList<LangueFonctionnalites>) request.getSession().getAttribute("listFctionnalites");
		int cpt = 0;
		while(cpt < listFctionnalites.size()){
			if(listFctionnalites.get(cpt).getFonctionnalite().getCodeFonctionnalite().equals(codeFct)){
				request.getSession().setAttribute("FctSelect", listFctionnalites.get(cpt).getLibFonctionnalie());
				listFctionnalites.remove(cpt);
				cpt = listFctionnalites.size();
			}
			cpt += 1;
		}
		request.getSession().setAttribute("listFctionnalites",listFctionnalites);
		
		IValidationFonctionnalites validationDAO = new ValidationFonctionnalitesDAO();
		ArrayList<ValidationFonctionnalites> listValidation = validationDAO.allValidation(codeprof, codeFct);
		
		if(listValidation.size() != 0){
			request.getSession().setAttribute("listValidation", listValidation);
			request.getSession().setAttribute("indiceCourant", 0);
			request.getSession().setAttribute("validationCourant", listValidation.get(0));
			
			//test à vérifier   caisse
			//if(listValidation.get(0).getProfilValidation().getCodeProfil().equals("caisse"))
			if(listValidation.get(0).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil()))
				modele.put("valider", "ok");
			ILangueProfil langProfDAO = new LangueProfilDAO();
			LangueProfil profilvalid = langProfDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(),listValidation.get(0).getProfilValidation().getCodeProfil());
			modele.put("profilvalid",profilvalid.getLibelle());
		}
		return new ModelAndView("Validations",modele);
	}
	
//------------------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView premier(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
		
		if(request.getSession().getAttribute("listValidation") != null){
			ArrayList<ValidationFonctionnalites> listValidation = (ArrayList<ValidationFonctionnalites>) request.getSession().getAttribute("listValidation");
			if(listValidation.size() != 0){
				request.getSession().setAttribute("listValidation", listValidation);
				request.getSession().setAttribute("indiceCourant", 0);
				request.getSession().setAttribute("validationCourant", listValidation.get(0));
				
				if( (listValidation.get(0).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil())) || ((listValidation.get(0).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil())) && (listValidation.get(0).getNbValidEffect() == 1) ) )
					modele.put("valider", "ok");
				ILangueProfil langProfDAO = new LangueProfilDAO();
				LangueProfil profilvalid = langProfDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(),listValidation.get(0).getProfilValidation().getCodeProfil());
				modele.put("profilvalid",profilvalid.getLibelle());
			}
		}
		
		return new ModelAndView("Validations",modele);
	}
	
//------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView dernier(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
		
		if(request.getSession().getAttribute("listValidation") != null){
			ArrayList<ValidationFonctionnalites> listValidation = (ArrayList<ValidationFonctionnalites>) request.getSession().getAttribute("listValidation");
			if(listValidation.size() != 0){
				int indice = listValidation.size() - 1;
				request.getSession().setAttribute("listValidation", listValidation);
				request.getSession().setAttribute("indiceCourant", indice);
				request.getSession().setAttribute("validationCourant", listValidation.get(indice));
				
				IValidationFonctionnalites validationDAO = new ValidationFonctionnalitesDAO();	
				Boolean resteValidation = validationDAO.testValidation(listValidation.get(indice).getProfil().getCodeProfil(),listValidation.get(indice).getFonctionnalite().getCodeFonctionnalite(), listValidation.get(indice).getRangProfilValid());
				if( ((listValidation.get(indice).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil())) && (resteValidation == false) ) || ((listValidation.get(indice).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil())) && (listValidation.get(indice).getNbValidEffect() == 1) ) )
					modele.put("valider", "ok");
				ILangueProfil langProfDAO = new LangueProfilDAO();
				LangueProfil profilvalid = langProfDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(),listValidation.get(indice).getProfilValidation().getCodeProfil());
				modele.put("profilvalid",profilvalid.getLibelle());
			}
		}
		
		return new ModelAndView("Validations",modele);
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView precedant(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		
		Map modele = new HashMap();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
		
		if(request.getSession().getAttribute("listValidation") != null){
			ArrayList<ValidationFonctionnalites> listValidation = (ArrayList<ValidationFonctionnalites>) request.getSession().getAttribute("listValidation");
			if(listValidation.size() != 0){
				int indice = (Integer) request.getSession().getAttribute("indiceCourant");
				if(indice > 0)
					indice -= 1;
				request.getSession().setAttribute("listValidation", listValidation);
				request.getSession().setAttribute("indiceCourant", indice);
				request.getSession().setAttribute("validationCourant", listValidation.get(indice));
				
				IValidationFonctionnalites validationDAO = new ValidationFonctionnalitesDAO();	
				Boolean resteValidation = validationDAO.testValidation(listValidation.get(indice).getProfil().getCodeProfil(),listValidation.get(indice).getFonctionnalite().getCodeFonctionnalite(), listValidation.get(indice).getRangProfilValid());
				if( ((listValidation.get(indice).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil())) && (resteValidation == false) ) || ((listValidation.get(indice).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil())) && (listValidation.get(indice).getNbValidEffect() == 1) ) )
					modele.put("valider", "ok");
				ILangueProfil langProfDAO = new LangueProfilDAO();
				LangueProfil profilvalid = langProfDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(),listValidation.get(indice).getProfilValidation().getCodeProfil());
				modele.put("profilvalid",profilvalid.getLibelle());
			}
		}
		
		return new ModelAndView("Validations",modele);
	}
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------------	
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModelAndView suivant(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
			
			if(request.getSession().getAttribute("listValidation") != null){
				ArrayList<ValidationFonctionnalites> listValidation = (ArrayList<ValidationFonctionnalites>) request.getSession().getAttribute("listValidation");
				if(listValidation.size() != 0){
					int indice = (Integer) request.getSession().getAttribute("indiceCourant");
					if(indice < listValidation.size()-1)
						indice += 1;
					request.getSession().setAttribute("listValidation", listValidation);
					request.getSession().setAttribute("indiceCourant", indice);
					request.getSession().setAttribute("validationCourant", listValidation.get(indice));
					
					IValidationFonctionnalites validationDAO = new ValidationFonctionnalitesDAO();	
					Boolean resteValidation = validationDAO.testValidation(listValidation.get(indice).getProfil().getCodeProfil(),listValidation.get(indice).getFonctionnalite().getCodeFonctionnalite(), listValidation.get(indice).getRangProfilValid());
					if( ((listValidation.get(indice).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil())) && (resteValidation == false) ) || ((listValidation.get(indice).getProfilValidation().getCodeProfil().equals(profilConnecte.getCodeProfil())) && (listValidation.get(indice).getNbValidEffect() == 1) ) )
						modele.put("valider", "ok");
					ILangueProfil langProfDAO = new LangueProfilDAO();
					LangueProfil profilvalid = langProfDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(),listValidation.get(indice).getProfilValidation().getCodeProfil());
					modele.put("profilvalid",profilvalid.getLibelle());
				}
			}
			
			return new ModelAndView("Validations",modele);
		}
		
		
//---------------------------------------------------------------------------------------------------------------------------------------------------------------		
		public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			String nbValidEf = request.getParameter("nbValidEffect");
			if(nbValidEf != null){
				int nbValidEffect = 0;
				try{
					nbValidEffect = Integer.parseInt(nbValidEf);
				}
				catch(NumberFormatException ex){}
				
				ValidationFonctionnalites validation = (ValidationFonctionnalites) request.getSession().getAttribute("validationCourant");
				IFonctionnalitesProfil fctionnaliteProfDAO = new FonctionnalitesProfilDAO();
				FonctionnalitesProfil fctionnaliteProfil = new FonctionnalitesProfil();
						if(fctionnaliteProfDAO.getFonctionnalitesProfil(validation.getFonctionnalite().getCodeFonctionnalite(), validation.getProfil().getCodeProfil()) != null)
							fctionnaliteProfil = fctionnaliteProfDAO.getFonctionnalitesProfil(validation.getFonctionnalite().getCodeFonctionnalite(), validation.getProfil().getCodeProfil());
				int nbValidEffect2 = fctionnaliteProfil.getNbValidEffect();
				IValidationFonctionnalites validationDAO = new ValidationFonctionnalitesDAO();
				if(nbValidEffect == 1){
					validation.setNbValidEffect(nbValidEffect);
					validation.setUtilValid(utilisateur);
					validation.setDateValid(new Date());
					nbValidEffect2 += 1;
				}
				else{
					validation.setNbValidEffect(nbValidEffect);
					validation.setUtilModif(utilisateur);
					validation.setDateDernierModif(new Date());
					nbValidEffect2 -= 1;
				}
				
				validationDAO.save(validation);
				fctionnaliteProfil.setNbValidEffect(nbValidEffect2);
				fctionnaliteProfDAO.save(fctionnaliteProfil);
				
			}
			
			return init(request,response);
		}
		
//-------------------------------------------------------------------------------------------------------------------------
		public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
			
			return init(request,response);
		}
	
	

}
