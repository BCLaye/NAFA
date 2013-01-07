package controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.IPreferences;
import modele.dao.IUtilisateur;
import modele.dao.PreferencesDAO;
import modele.dao.UtilisateurDAO;
import modele.metiers.Preferences;
import modele.metiers.Utilisateur;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class PreferencesController extends MultiActionController {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
		
		Map modele = new HashMap();		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			modele.put("close","ok");
			return new ModelAndView("Preferences",modele);
		}
		return new ModelAndView("Preferences",modele);
	}
	
//-----------------------------------------------------------------------------------------------------------------	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView enregister(HttpServletRequest request, HttpServletResponse response){
		
		Map modele = new HashMap();
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			modele.put("close", "ok");
		}
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		IUtilisateur utilDAO = new UtilisateurDAO();
		Utilisateur util = utilDAO.getUtilisateur(utilisateur.getCodeUtil());
		
		Preferences ancienPref = utilisateur.getPreference();
		String theme = ancienPref.getTheme();
		String couleur = ancienPref.getCouleur();
		
		String them = request.getParameter("theme");
		if((them != null) && (them.trim().length() != 0))
			theme = them;
		
		String coul = request.getParameter("couleur");
		if((coul != null) && (coul.trim().length() != 0))
			couleur = coul;
		
		IPreferences prefDAO = new PreferencesDAO();
		Preferences pref = prefDAO.getPreferences(theme, couleur);
		utilisateur.setPreference(pref);
		util.setPreference(pref);
		
		String name = request.getParameter("nomUtil");
		System.out.println("--------------"+name);
		if(!name.trim().equals(util.getNomUtil())){
			utilisateur.setNomUtil(name);
			util.setNomUtil(name);
		}
		
		utilDAO.save(util);
		
		request.getSession().setAttribute("utilisateur", utilisateur);
		modele.put("close", "ok");
		return new ModelAndView("Preferences",modele);
	}
	
//-------------------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView annuler(HttpServletRequest request, HttpServletResponse response){
		
		Map modele = new HashMap();
		modele.put("close", "ok");
		return new ModelAndView("Preferences",modele);
	}

}
