<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${utilisateur.langue.labelUtilisateurProfil}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/jquery.ui.all.css" />
    <link href='css/fonts.css' rel='stylesheet' type='text/css'>
    <link href="css/bootstrap.css" rel="stylesheet" type='text/css'>
	<script src="javascript/jquery-1.8.0.js" type="text/javascript"></script>
	<script src="javascript/jquery.ui.core.js" type="text/javascript"></script>
	<script src="javascript/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="javascript/jquery.ui.datepicker.js" type="text/javascript"></script>
	
	
	<link rel="stylesheet" href="css/demos.css" />
	<link rel="stylesheet" type="text/css" href="css/design.css">
	
<script type="text/javascript">   
   $(function() {
		$( "#datepicker" ).datepicker();
	});
	
	 $(function() {
		$( "#datepicker2" ).datepicker();
	});   
	
   function envoyerAction(code){
    with(document.formUtilisateurProfil){
					action.value=code;
					submit();
					}
	}
	
	function fermenFenetre(){
    this.window.close();
	}
</script>

  </head>
  
 <c:if test="${close != null }">
  <body onload='fermenFenetre()'>
 </c:if>  
 <c:if test="${close == null }">
  <body>
 </c:if>
 
 
    <form action="UtilisateurProfil.html" method="post" name="formUtilisateurProfil">
       <fieldset>
         <legend>${utilisateur.langue.labelUtilisateurProfil}</legend>
         <table>
            <tr>
                <td>${utilisateur.langue.utilisateur}</td>
                <td><input type="text" value="${utilisateurCourant.nomUtil}" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>${utilisateur.langue.profil}</td>
                <td><input type="text" value="${profilselect.libelle}" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>${utilisateur.langue.dateDebAff}</td>
                <td><input type="text" name="dateDebAff" value="${dateDebAff}" id="datepicker"/></td>
            </tr>
            <tr>
                <td>${utilisateur.langue.dateFinAff}</td>
                <td><input type="text" name="dateFinAff" value="${dateFinAff}" id="datepicker2"/></td>
            </tr>
            <tr>
                <td>${utilisateur.langue.etat}</td>
                <td>
                
                <c:if test="${noModifiable != null }">
                   <select name="etat" disabled="disabled">
                     <option value="">Inactif</option>
                   </select>
                  </c:if>
                  
                  <c:if test="${noModifiable == null }">
                  <c:if test="${utilisateurProfilCourant.etat == 'Inactif'}">
                   <select name="etat">
                     <option value="Inactif">Inactif</option>
                     <option value="Actif">Actif</option>
                   </select>                   
                  </c:if>
                  <c:if test="${utilisateurProfilCourant.etat == 'Actif'}">
                   <select name="etat">
                     <option value="Actif">Actif</option>
                     <option value="Inactif">Inactif</option>
                   </select>                   
                  </c:if>
                  </c:if>
                
                </td>
            </tr>
            <tr>
                <td>${utilisateur.langue.dateDernierEtat}</td>
                <td><input type="text" value="${dateDernierEtat}" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>${utilisateur.langue.nbValidEffect}</td>
                <td><input type="text" value="${utilisateurProfilCourant.nbValidEffect}" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>${utilisateur.langue.codeUtilCree}</td>
                <td>
                   <input type="text" value="${utilisateurProfilCourant.utilCree.codeUtil}" disabled="disabled"/>&nbsp;&nbsp;&nbsp;
                   <input type="text" value="${utilisateurProfilCourant.utilCree.nomUtil}" disabled="disabled"/>
                </td>
            </tr>
            <tr>
                <td>${utilisateur.langue.dateCree}</td>
                <td><input type="text" value="${dateCree}" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>${utilisateur.langue.codeUtilModif}</td>
                <td>
                   <input type="text" value="${utilisateurProfilCourant.utilModif.codeUtil}" disabled="disabled"/>&nbsp;&nbsp;&nbsp;
                   <input type="text" value="${utilisateurProfilCourant.utilModif.nomUtil}" disabled="disabled"/>
                </td>
            </tr>
            <tr>
                <td>${utilisateur.langue.dateModif}</td>
                <td><input type="text" value="${dateModif}" disabled="disabled"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                   <jsp:include page="/WEB-INF/vues/Boutons.jsp"></jsp:include>                
                </td>
            </tr>
         </table>
       </fieldset>
       <input type="hidden" name="action"/>
    </form>
  </body>
</html>
