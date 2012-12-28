package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class DeconnexionController extends MultiActionController {
public ModelAndView deconnexion(HttpServletRequest request, HttpServletResponse response){
		
	 request.getSession().invalidate();
	 return new ModelAndView("Connexion",null);
	}

}
