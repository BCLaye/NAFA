package controllers;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.dao.HistoriqueMDPDAO;
import modele.dao.IHistoriqueMPD;
import modele.dao.IUtilisateur;
import modele.dao.UtilisateurDAO;
import modele.metiers.HistoriqueMDP;
import modele.metiers.Profil;
import modele.metiers.Utilisateur;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ChangerMotDPasseController extends MultiActionController {
	
	//-----------------------------------------------------------------------------------------------------------------------     
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView changerMDP(HttpServletRequest request, HttpServletResponse response){
   		
   		if(request.getSession().getAttribute("utilisateur") == null){
   			request.getSession().invalidate();
   			return new ModelAndView("Connexion",null);
   		}
   		
   		Map modele = new HashMap();
   		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
   		
   		String motDPActuel = request.getParameter("motDPActuel");
   		if((motDPActuel == null) || (motDPActuel.trim().length() == 0)){
   			modele.put("errorMotDPActuel", utilisateur.getLangue().getChampObligatoire());
   			return new ModelAndView("MotDePass",modele);
   		}
   		else{
   			String pass = utilisateur.cryptageMDP(motDPActuel);
   			if(!utilisateur.getMotDPass().equals(pass)){
   				modele.put("errorMotDPActuel", utilisateur.getLangue().getPassIncorect());
       			return new ModelAndView("MotDePass",modele);
   			}
   			
   			String nouvoMDP = request.getParameter("nouvoMDP");
   			if((nouvoMDP == null) || (nouvoMDP.trim().length() == 0)){
   				modele.put("errorNouvoMDP", utilisateur.getLangue().getChampObligatoire());
       			return new ModelAndView("MotDePass",modele);
   			}
   			
   			String confirmMDP = request.getParameter("confirmMDP");
   			if((confirmMDP == null) || (confirmMDP.trim().length() == 0)){
   				modele.put("errorConfirmMDP", utilisateur.getLangue().getChampObligatoire());
       			return new ModelAndView("MotDePass",modele);
   			}
   			
   			if(!nouvoMDP.equals(confirmMDP)){
   				modele.put("errorConfirmMDP", utilisateur.getLangue().getPassIncorect());
       			return new ModelAndView("MotDePass",modele);
   			}
   			
   			IUtilisateur utilDAO = new UtilisateurDAO();
   			Utilisateur util = utilDAO.getUtilisateur(utilisateur.getCodeUtil());
   			util.setMotDPass(nouvoMDP);	
   			
   			java.util.GregorianCalendar calendar = new GregorianCalendar();
   			java.util.Date heurCree = calendar.getTime();
   			HistoriqueMDP histoMDP = new HistoriqueMDP(util, util.cryptageMDP(nouvoMDP), new Date(), heurCree);
   			IHistoriqueMPD histoMPDDAO = new HistoriqueMDPDAO();
   			histoMPDDAO.save(histoMDP);
   			
   			util.setDateDernierModifMDP(new Date());
   			util.setHeureDernierModifMDP(heurCree);
   			utilDAO.save(util);
   					
   			request.getSession().setAttribute("utilisateur",util);
   			modele.put("close", "ok");
   			return new ModelAndView("MotDePass",modele);
   		}
    }
    
    
//-----------------------------------------------------------------------------------------------------------------
    public ModelAndView annulerChangerMDP(HttpServletRequest request, HttpServletResponse response){
		
		if(request.getSession().getAttribute("utilisateur") == null){
			request.getSession().invalidate();
			return new ModelAndView("Connexion",null);
		}
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Profil profilConnecte = (Profil) request.getSession().getAttribute("profilConnecte");
		request.getSession().invalidate();
		request.getSession().setAttribute("utilisateur",utilisateur);
		request.getSession().setAttribute("profilConnecte",profilConnecte);
		
		return new ModelAndView("MotDePass",null);
		}

}
