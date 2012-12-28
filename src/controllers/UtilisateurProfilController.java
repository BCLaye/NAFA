package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.ILangueProfil;
import modele.dao.IUtilisateurProfil;
import modele.dao.LangueProfilDAO;
import modele.dao.UtilisateurProfilDAO;
import modele.metiers.LangueProfil;
import modele.metiers.Utilisateur;
import modele.metiers.UtilisateurProfil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UtilisateurProfilController extends MultiActionController {
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			//Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
			Utilisateur utilCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
			
			String profil = request.getParameter("profil");
			IUtilisateurProfil utilProfDAO = new UtilisateurProfilDAO();
			UtilisateurProfil utilprofil = utilProfDAO.getUtilisateurProfil(utilCourant.getCodeUtil(), profil);
			ILangueProfil langProfDAO = new LangueProfilDAO();
			LangueProfil profilselect = langProfDAO.getLangueProfil(utilisateur.getLangue().getCodeLangue(),profil);
			
			if((utilCourant.getEtatutil().equals("Inactif")) || ((utilCourant.getEtatutil().equals("Actif")) && (utilprofil.getEtat().equals("Inactif")) && (utilprofil.getUtilCree().getCodeUtil().equals(utilisateur.getCodeUtil()))))
				modele.put("noModifiable", "ok");
			
			request.getSession().setAttribute("utilisateurProfilCourant", utilprofil);
			
			SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
  			String dateDebAff = formater.format(utilprofil.getDateDebAff());
  			String dateFinAff = formater.format(utilprofil.getDateFinAff());
  			String dateDernierEtat = null;
  			if(utilprofil.getDateDernierEtat() != null)
  				dateDernierEtat = formater.format(utilprofil.getDateDernierEtat());
  			String dateCree = formater.format(utilprofil.getDateCree());
  			String dateModif = null;
  			if(utilprofil.getDateDernierModif() != null)
  				dateModif = formater.format(utilprofil.getDateDernierModif());
  			modele.put("dateDebAff", dateDebAff);
  			modele.put("dateFinAff", dateFinAff);
  			modele.put("dateDernierEtat", dateDernierEtat);
  			modele.put("dateCree", dateCree);
  			modele.put("dateModif", dateModif);
			modele.put("profilselect", profilselect);
			
			return new ModelAndView("UtilisateurProfil",modele);
	 }
	 
//--------------------------------------------------------------------------------------------------------------------	 
	 @SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("utilisateur") == null){
				request.getSession().invalidate();
				return new ModelAndView("Connexion",null);
			}
			
			Map modele = new HashMap();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			UtilisateurProfil utilprofil = (UtilisateurProfil) request.getSession().getAttribute("utilisateurProfilCourant");
			
			String dateDebAf = request.getParameter("dateDebAff");
			if((dateDebAf != null) && (dateDebAf.trim().length() > 0)){
				Date dateDebAff = new Date(dateDebAf);
				if(utilprofil.getDateDebAff().getTime() - dateDebAff.getTime() == 0){
					utilprofil.setDateDebAff(dateDebAff);
					utilprofil.setDateDernierModif(new Date());
					utilprofil.setUtilModif(utilisateur);
				}
			}
			
			String dateFinAf = request.getParameter("dateFinAff");
			if((dateFinAf != null) && (dateFinAf.trim().length() >0)){
				Date dateFinAff = new Date(dateFinAf);
				if(utilprofil.getDateFinAff().getTime() - dateFinAff.getTime() == 0){
					utilprofil.setDateFinAff(dateFinAff);
					utilprofil.setDateDernierModif(new Date());
					utilprofil.setUtilModif(utilisateur);
				}
			}
			String etat = request.getParameter("etat");
			if((etat!=null) && (etat.trim().length() >0))
				if(!utilprofil.getEtat().equals(etat)){
					utilprofil.setEtat(etat);
					utilprofil.setDateDernierEtat(new Date());
					utilprofil.setDateDernierModif(new Date());
					utilprofil.setUtilModif(utilisateur);
				}
			IUtilisateurProfil utilProfDAO = new UtilisateurProfilDAO();
			utilProfDAO.save(utilprofil);
			
			modele.put("close", "ok");
			return new ModelAndView("UtilisateurProfil",modele);
	 }
	 
//----------------------------------------------------------------------------------------------------------------------	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
		 
		 Map modele = new HashMap();
		 modele.put("close", "ok");
		return new ModelAndView("UtilisateurProfil",modele);
	  	}

}
