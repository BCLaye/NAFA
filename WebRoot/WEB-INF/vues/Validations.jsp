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
    
    <title>${utilisateur.langue.labelValidation}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="javascript/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="javascript/bootstrap.js"></script>
	
	<link href='css/fonts.css' rel='stylesheet' type='text/css'>
	<!-- <link rel="stylesheet" href="style.css" type="text/css" /> -->
	<%-- <link rel="stylesheet" type="text/css" href="css/${utilisateur.preference.id}.css"> --%>
	<link rel="stylesheet" type="text/css" href="css/theme1.css">
	<link href="css/bootstrap.css" rel="stylesheet" type='text/css'>
	<link href="css/icomoon.css" rel="stylesheet" type='text/css'>
	<link href='css/boutons.css' rel='stylesheet' type='text/css'>
	
<script type="text/javascript">    
    function envoyerAction(code){
    with(document.formValidation){
					action.value=code;
					submit();
					}
	}
</script>	

  </head>
  <body>
 <div id="header">
    <c:if test='${utilisateur.preference.theme == "theme1"}'>
	  <jsp:include page="/WEB-INF/vues/Header1.jsp"></jsp:include>
	 </c:if>
	 <c:if test='${utilisateur.preference.theme == "theme2"}'>
	  <jsp:include page="/WEB-INF/vues/Header2.jsp"></jsp:include>
	 </c:if>
  </div>
	
	<c:if test='${utilisateur.preference.theme == "theme2"}'>
	  <jsp:include page="/WEB-INF/vues/MenuHorizontal2.jsp"></jsp:include>
   </c:if>
	
	<div id="page">
		<c:if test='${utilisateur.preference.theme == "theme1"}'>
		   <div id="leftPage">
		   	<jsp:include page="/WEB-INF/vues/MenuVertical.jsp"></jsp:include>
		   </div>
	   
	   	<div id="rightPage">
	 	</c:if>
			<div id="content">
				<div id="contentTitle"><i class="nafa-home"></i>Tableau de bord</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
<!-- ---------------------------------début contenu de la Page-------------------------------------------------- -->
	<form action="validation.html" method="post" name="formValidation">
       <fieldset>
          <legend>${utilisateur.langue.labelValidation}</legend>
          <table>
             <tr>
                <td align="center" colspan="2">
                  <jsp:include page="/WEB-INF/vues/menus2.jsp"></jsp:include>
                </td>
             </tr>
             <tr>
               <td>${utilisateur.langue.profil}</td>
               <td>
                  <select name="profil" onchange='envoyerAction("selectProfil")'>
                      <c:if test="${validationCourant == null}">
                        <option value="">--------------------------------</option>
                      </c:if>
                      <c:if test="${validationCourant != null}">
                        <option value="${validationCourant.profil.codeProfil}"> ${profilSelect} </option>
                      </c:if>
                      <c:forEach items="${listProfil}" var="langProfil">
                           <option value="${langProfil.profil.codeProfil}">${langProfil.libelle}</option>
                      </c:forEach>
                  </select>
               </td>
             </tr>
             <tr>
               <td>${utilisateur.langue.fonctionnalite}</td>
               <td>
                  <select name="fctionnalite" onchange='envoyerAction("selectFonctionnalite")'>
                      <c:if test="${validationCourant == null}">
                        <option value="">--------------------------------</option>
                      </c:if>
                      <c:if test="${validationCourant != null}">
                        <option value="${validationCourant.fonctionnalite.codeFonctionnalite}"> ${FctSelect} </option>
                      </c:if>
                      <c:forEach items="${listFctionnalites}" var="langFctionnalite">
                           <option value="${langFctionnalite.fonctionnalite.codeFonctionnalite}">${langFctionnalite.libFonctionnalie}</option>
                      </c:forEach>
                  </select>
               </td>
             </tr>
             <tr>
               <td>${utilisateur.langue.profilValidation}</td>
               <td><input type="text" value="${profilvalid}" disabled="disabled"/></td>
             </tr>
             <tr>
               <td>${utilisateur.langue.rangValidation}</td> 
               <td><input type="text" value="${validationCourant.rangProfilValid}" disabled="disabled"/></td>
             </tr>
             <tr>
               <td>${utilisateur.langue.etat}</td>
               <td>
               
                  <c:if test="${valider == null}">
                   <select disabled="disabled">
                      <c:if test="${validationCourant.nbValidEffect == 1}">
                        <option value="1">${utilisateur.langue.valide}</option>
                      </c:if>
                      <c:if test="${validationCourant.nbValidEffect == 0}">
                        <option value="0">${utilisateur.langue.invalide}</option>
                      </c:if>
                      <c:if test="${validationCourantt.nbValidEffect == null}">
                        <option>-----------------------</option>
                      </c:if>
                   </select>
                  </c:if>
                  
                  <c:if test="${valider != null}">
                   <select name="nbValidEffect">
                      <c:if test="${validationCourant.nbValidEffect == 1}">
                        <option value="1">${utilisateur.langue.valide}</option>
                        <option value="0">${utilisateur.langue.invalide}</option>
                      </c:if>
                      <c:if test="${validationCourant.nbValidEffect == 0}">
                        <option value="0">${utilisateur.langue.invalide}</option>
                        <option value="1">${utilisateur.langue.valide}</option>
                      </c:if>
                   </select>
                  </c:if>
                  
               </td>
             </tr>
              <tr>
                 <td>${utilisateur.langue.utilValid}</td>
                 <td><input type="text" disabled="disabled" value="${validationCourant.utilValid.codeUtil}"/> &nbsp;&nbsp;<input type="text" disabled="disabled" value="${validationCourant.utilValid.nomUtil}"/></td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.dateValid}</td>
                 <td><input type="text" disabled="disabled" value="${validationCourant.dateValid}"/></td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.codeUtilModif}</td>
                 <td><input type="text" disabled="disabled" value="${validationCourant.utilModif.codeUtil}"/> &nbsp;&nbsp;<input type="text" disabled="disabled" value="${validationCourant.utilModif.nomUtil}"/></td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.dateModif}</td>
                 <td><input type="text" disabled="disabled" value="${validationCourant.dateDernierModif}"/></td>
              </tr>
             <tr>
                <td align="center" colspan="2">
                   <jsp:include page="/WEB-INF/vues/Boutons.jsp"></jsp:include>
                </td>
             </tr>
          </table>
       </fieldset>
       <input type="hidden" name="action"/>
     </form>
<!-- ---------------------------------Fint contenu de la Page---------------------------------------------------- -->
				</div>
			</div>
		<c:if test='${utilisateur.preference.theme == "theme1"}'>
		</div>
		</c:if>
	
	</div>
	
	<div id="footer"></div>
  </body>
</html>
