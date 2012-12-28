package controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.IProfil;
import modele.dao.IUtilisateur;
import modele.dao.ProfilDAO;
import modele.dao.UtilisateurDAO;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;
import modele.metiers.UtilisateurProfil;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


@SuppressWarnings("deprecation")
public class ConnexionController extends SimpleFormController {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ModelAndView onSubmit(HttpServletRequest request,	HttpServletResponse response, Object command,
			BindException errors){
		
		Map modele = new HashMap();
		
		if(request.getParameter("profilConnecte").trim().length() != 0){
			String codeProfil = request.getParameter("profilConnecte");
			IProfil profilDAO = new ProfilDAO();
			Profil profilConnecte = profilDAO.getProfil(codeProfil);			
			request.getSession().setAttribute("profilConnecte",profilConnecte);
			return new ModelAndView("Acceuil",modele);
		}
		
		Utilisateur util = (Utilisateur) command;
		if((util.getCodeUtil() == null) || (util.getCodeUtil().trim().length()== 0)){
			modele.put("errorCodeUtil", "champ obligatoire");
			return new ModelAndView("Connexion",modele);
		}
		String codeUtil = util.getCodeUtil();
		
		
		if((request.getParameter("motDPass") == null) || (request.getParameter("motDPass").trim().length()== 0)){
			modele.put("errorMDP", "champ obligatoire");
			return new ModelAndView("Connexion",modele);
		}
		String motDPass = util.cryptageMDP(request.getParameter("motDPass"));
		
		
		IUtilisateur utilDAO = new UtilisateurDAO();
		Date today = new Date();
		Utilisateur utilConnecte = utilDAO.getUtilisateur(codeUtil);
		
		if((utilConnecte == null) ||( !utilConnecte.getMotDPass().equals(motDPass) ) || (!utilConnecte.getEtatutil().equals("Actif")) || (today.getTime() - utilConnecte.getDateDbutValid().getTime() < 0)  || (today.getTime() - utilConnecte.getDateFinValid().getTime() > 0) ){
			modele.put("errorUtil", "Utilisateur Inexistant");
			return new ModelAndView("Connexion",modele);
		}
		
		/*/ ------------------- Utilisateur déja connecté -----------------------------------------------------------
		if((utilConnecte.getDateDernierConnexion()!= null) && (utilConnecte.getDateDernierDeconnexion() == null)) {
			modele.put("errorUtil", "Utilisateur déja Connecté");
			return new ModelAndView("Connexion",modele);
		}
		else
			if((utilConnecte.getHeureDernierDeconnexion() != null) && (utilConnecte.getHeureDernierConnexion() != null))
				if(utilConnecte.getHeureDernierDeconnexion().getTime() - utilConnecte.getHeureDernierConnexion().getTime()<0){
					modele.put("errorUtil", "Utilisateur déja Connecté");
					return new ModelAndView("Connexion",modele);
					}
		*/// ---------------------------------------------------------------------------------------------------------
		if(utilConnecte.getDateDernierConnexion() == null)
			modele.put("changePass", "Ok");
		utilConnecte.setDateDernierConnexion(today);
		
		java.util.GregorianCalendar calendar = new GregorianCalendar();
		java.util.Date heurDernierConnex = calendar.getTime();
		utilConnecte.setHeureDernierConnexion(heurDernierConnex);
		
		
		InetAddress adressIP = null;
		try {
			adressIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String adress = adressIP.toString();
		String adress = adressIP.getHostAddress();
		
		utilConnecte.setAdressIPDernierConnexion(adress);
		utilDAO.save(utilConnecte);
				
		ArrayList<UtilisateurProfil> listProf =  new ArrayList<UtilisateurProfil>(), listProfil = new ArrayList<UtilisateurProfil>();
		listProf.addAll(utilConnecte.getProfils());
		if(listProf != null){
			int cpt = 0;
			while(cpt < listProf.size()){
				if( (listProf.get(cpt).getEtat().equals("Actif")) && (today.getTime()-listProf.get(cpt).getDateDebAff().getTime()>0) && (today.getTime()-listProf.get(cpt).getDateFinAff().getTime()<0))
					listProfil.add(listProf.get(cpt));
				cpt +=1;
			}
		} 
		
		modele.put("codeUtil",codeUtil);
		modele.put("motDPass", request.getParameter("motDPass"));
		request.getSession().setAttribute("utilisateur",utilConnecte);
		
		
		modele.put("listProfil",listProfil);		
		
		return new ModelAndView("Connexion",modele);
	}

}
