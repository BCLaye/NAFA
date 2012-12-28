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
    
    <title>${utilisateur.langue.labelFonctionnalitesProfil}</title>
    
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
    with(document.formFctionnalitesProfil){
					action.value=code;
					submit();
					}
	}
	
   function confirmation(){
    with(document.formFctionnalitesProfil){
                   rep = confirm(message1.value);
                   if(rep == true){
                     action.value="updateEntites";
					 submit();
                   }
					
		}
	}
	
	function confirmerSuppression(){
	with(document.formFctionnalitesProfil){
                   rep = confirm(message2.value);
                   if(rep == true){
                     action.value="supprimer";
					 submit();
                   }
					
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
				<div id="contentTitle"><i class="nafa-home"></i>${utilisateur.langue.labelFonctionnalitesProfil}</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
<!-- ---------------------------------début contenu de la Page-------------------------------------------------- -->
	<form action="FonctionnalitesProfil.html" method="post" name="formFctionnalitesProfil">
       <fieldset>
          <legend>${utilisateur.langue.labelFonctionnalitesProfil}</legend>
          <table>
             <tr>
               <td align="center" colspan="2">
                  <jsp:include page="/WEB-INF/vues/menu.jsp"></jsp:include>
               </td>
             </tr>
             <tr>
                <td>${utilisateur.langue.profil}</td>
                <td>
                   <select name="profil" onchange='envoyerAction("selectProfil")'>
                     <c:if test="${fctionnaliteProfilCourant.profil == null }">
                      <option value="">--------------------------------</option>
                     </c:if>
                     <c:if test="${fctionnaliteProfilCourant.profil != null }">
                      <option value="${fctionnaliteProfilCourant.profil.codeProfil}">${profilSelect.libelle}</option>
                     </c:if>
                      <c:forEach items ="${listProfil1}" var="langProfil">
                        <option value="${langProfil.profil.codeProfil}">${langProfil.libelle}</option>
                      </c:forEach>
                   </select> <font color="red">${errorProfil}</font>
                </td>
             </tr>
             <tr>
                <td>${utilisateur.langue.fonctionnalites}&nbsp;&nbsp;&nbsp;<input type="button" id="ajouter" onclick='envoyerAction("ajouterFonctionnalite")'/></td>
                <td></td>
             </tr>
             <tr>
                <td></td>
                <td>
                    
                    <table>
                       <c:if test="${fctionnaliteProfilCourant.profil != null}">
                       <c:if test="${thtab != null}">
                        <tr>
                           <th>${utilisateur.langue.fonctionnalites}</th>
                           <th>${utilisateur.langue.nbValidReq}</th>
                        </tr>
                        </c:if> 
                        <c:forEach items="${fctionnaliteProfilCourant.profil.fonctionnalitesProfil}" var="fonctionnalitesProfil">
                         <tr>
                            <td><input type="text" value="${fonctionnalitesProfil.fonctionnalite.codeFonctionnalite}" disabled="disabled" /></td>
                            <td><input type="text" value="${fonctionnalitesProfil.nbValidReq}" disabled="disabled" />&nbsp;&nbsp;&nbsp;<input type="button" id="deleteLib"/>&nbsp;&nbsp;&nbsp;<input type="button" id="editFct"/></td>
                         </tr>
                        </c:forEach>
                       </c:if>
                       
                         <tr>
                            <td>${utilisateur.langue.fonctionnalite}</td>
                            <td>
                               <select name="fction">
                                  <c:if test="${fctionnaliteProfilCourant.fonctionnalite == null }">
                                     <option value="">--------------------------------</option>
                                  </c:if>
                                  <c:if test="${fctionnaliteProfilCourant.fonctionnalite != null }">
                                      <option value="${fctionnaliteProfilCourant.fonctionnalite.codeFonctionnalite}">${fctionnaliteSelect.libFonctionnalie}</option>
                                  </c:if>
                                  <c:forEach items="${listFctionnalites}" var="langueFctionnalite">
                                    <option value="${langueFctionnalite.fonctionnalite.codeFonctionnalite}">${langueFctionnalite.libFonctionnalie}</option>
                                  </c:forEach>
                               </select>  <font color="red">${errorFctionnalite}</font>
                            </td>
                         </tr>
                         <tr>
                           <td>${utilisateur.langue.nbValidReq}</td>
                           <td><input type="text" name="nbValidReq" value="${fctionnaliteProfilCourant.nbValidReq}" onblur='envoyerAction("nombreValidation")'/>   <font color="red">${errorNbValid} ${errorFormat} ${errorNbValidReq}</font></td>
                         </tr>
                         
                         <tr>
                            <td></td>
                            <td>
                               <table>
                                  <c:if test="${affichTab != null }">
                                   <tr>
                                      <th>${utilisateur.langue.rangValidation}</th>
                                      <th>${utilisateur.langue.profilValidation}</th>
                                   </tr>
                                  </c:if>
                                  <c:forEach items="${listValidation}" var="validation">
                                    <tr>
                                       <td align="center"><input type="text" value="${validation.rangProfilValid}" disabled="disabled" style="width:30;"/></td>
                                       <td><input type="text" value="${validation.profilValidation.codeProfil}" disabled="disabled"/></td>
                                    </tr>
                                  </c:forEach>
                                  <c:if test="${ajouterValid != null }">
                                   <tr>
                                     <td align="center">
                                         <input type="text" name="rangValidation" value="${rangValidation}" disabled="disabled" style="width:30;"/>
                                      </td>
                                      <td align="center">
                                        <select name="profilValid" onchange='envoyerAction("ajouterValidation")'>
                                          <option value="">--------------------------------</option>
                                          <c:forEach items="${listProfil2}" var="langProfil">
                                             <option value="${langProfil.profil.codeProfil}">${langProfil.libelle}</option>
                                          </c:forEach>
                                        </select></td>
                                   </tr>
                                  </c:if>
                               </table>
                            </td>
                         </tr>
                         
                   </table>
                    
                </td>
             </tr>
             <tr>
               <td align="center" colspan="2">
                  <jsp:include page="/WEB-INF/vues/Boutons.jsp"></jsp:include>
               </td>
             </tr>
          </table>
       </fieldset>
       <input type="hidden" name="action"/>
       <input type="hidden" name="message1" value="${utilisateur.langue.confirmationEnregistrer}"/>
       <input type="hidden" name="message2" value="${utilisateur.langue.confirmationSupprimer}"/>
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
