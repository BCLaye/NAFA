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
    
    <title>${utilisateur.langue.labelMenus}</title>
    
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
    with(document.formMenus){
					action.value=code;
					submit();
					}
	}
	
   function confirmation(){
    with(document.formMenus){
                   rep = confirm(message1.value);
                   if(rep == true){
                     action.value="updateMenus";
					 submit();
                   }
					
		}
	}
	
	function confirmerSuppression(){
	with(document.formMenus){
                   rep = confirm(message2.value);
                   if(rep == true){
                     action.value="supprimer";
					 submit();
                   }
					
		}	 
	}	
</script>

  </head>
  
 <c:if test="${confirmer != null }">
  <body onload="confirmation()">
 </c:if>
 <c:if test="${confirmer == null }">
  <body>
 </c:if>
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
				<div id="contentTitle"><i class="nafa-home"></i>${utilisateur.langue.labelMenus}</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
<!-- ---------------------------------début contenu de la Page-------------------------------------------------- -->
	 <form action="Menus.html" method="post" name="formMenus">
        <fieldset>
          <legend>${utilisateur.langue.labelMenus}</legend>
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
                     <c:if test="${menusCourant.profil == null }">
                      <option value="">--------------------------------</option>
                     </c:if>
                     <c:if test="${menusCourant.profil != null }">
                      <option value="${menusCourant.profil.codeProfil}">${profilSelect.libelle}</option>
                     </c:if>
                      <c:forEach items ="${listProfil}" var="langProfil">
                        <option value="${langProfil.profil.codeProfil}">${langProfil.libelle}</option>
                      </c:forEach>
                  </select> <font color="red">${errorProfil}</font>
                </td>
              </tr>
              <tr>
                <td>${utilisateur.langue.codeMenus}</td>
                <td><input type="text" name="codeMenus" value="${menusCourant.codeMenus}"/><font color="red">${errorCode}</font></td>
              </tr>
              <tr>
               <c:if test="${AfficheFCtionnalte != null }">
                <td>${utilisateur.langue.fonctionnalite}</td>
                <td>
                  <select name="fction" onchange='envoyerAction("selectFonctionnalite")'>
                     <c:if test="${menusCourant.fonctionnalite == null }">
                        <option value="">--------------------------------</option>
                     </c:if>
                     <c:if test="${menusCourant.fonctionnalite != null }">
                        <option value="${menusCourant.fonctionnalite.codeFonctionnalite}">${fctionnaliteSelect.libFonctionnalie}</option>
                     </c:if>
                     <c:forEach items="${listFctionnalites}" var="langueFctionnalite">
                        <option value="${langueFctionnalite.fonctionnalite.codeFonctionnalite}">${langueFctionnalite.libFonctionnalie}</option>
                     </c:forEach>
                  </select>  <font color="red">${errorUrl}</font>
                </td>
               </c:if> 
              </tr>
              
               <c:if test="${Affichemenus != null }">
               <tr>
                <td>${utilisateur.langue.menus} &nbsp;&nbsp;&nbsp;<input type="button" id="ajouter" onclick='envoyerAction("ajouterMenus")'/></td>
                <td></td>
                </tr>
               <c:forEach items="${menusCourant.listmenus}" var="mnus">
                 <tr>
                   <td></td>
                   <td><input type="text" disabled="disabled" value="${mnus.codeMenus}"/></td>
                 </tr>               
               </c:forEach>
                <tr>
                  <td></td>
                  <td>
                  <select name="menus">
                       <option value="">--------------------------------</option>
                     <c:forEach items="${listMenus}" var="langMenu">
                       <option value="${langMenu.menus.codeMenus}">${langMenu.libMenus}</option>
                     </c:forEach>
                  </select> <font color="red">${errorUrl}</font>
                </td>
                </tr>
               </c:if> 
           
              <tr>
                 <td>${utilisateur.langue.nom} &nbsp;&nbsp;&nbsp;<input type="button" id="ajouter" onclick='envoyerAction("ajouterNom")'/></td>
                 <td>                         
                    <table>
                         <thead>
                            <tr>
                               <th>${utilisateur.langue.langue}</th>
                               <th>${utilisateur.langue.nom}</th>
                            </tr>
                         </thead>
                         <tbody>
                          <c:forEach items="${menusCourant.libelles}" var="libelle">
                            <tr>
                              <td><input type="text" value="${libelle.langue.nomLangue}" disabled="disabled"/></td>
                              <td><input type="text" value="${libelle.libMenus}" disabled="disabled"/>&nbsp;&nbsp;&nbsp;<input type="button" id="editFct" onclick='EditerLibelle("${libelle.langue.codeLangue}")'/>&nbsp;&nbsp;&nbsp;<input type="button" id="deleteLib" onclick='deleteLibelle("${libelle.langue.codeLangue}")'/></td>
                            </tr>   
                            </c:forEach>
                            <c:if test="${affLangue != null}">
                             <tr>
                                 <td>
                                   <select name="langue">
                                     <c:if test="${libEdit != null}">
                                       <option value="${libEdit.langue.codeLangue}">${libEdit.langue.nomLangue}</option>
                                     </c:if>
                                      <c:forEach items="${langues}" var="langue">
                                        <option value="${langue.codeLangue}">${langue.nomLangue}</option>   
                                      </c:forEach>
                                   </select>
                                 </td>
                                 <td>
                                    <c:if test="${libEdit != null}">
                                       <input type="text" name="libelle" value="${libEdit.libFonctionnalie}"/>
                                     </c:if>
                                    <c:if test="${libEdit == null}">
                                       <input type="text" name="libelle"/><font color="red">${errorNom}</font>
                                     </c:if>
                                 </td>
                               </tr>
                              </c:if> 
                            </tbody>
                         </table>
                     </td>
                </tr>
                <c:if test="${recherche != null}">
               <tr>
                 <td>${utilisateur.langue.codeUtilCree}</td>
                 <td>
                   <input type="text" value="${menusCourant.utilCree.codeUtil}" disabled="disabled"/> &nbsp;&nbsp;&nbsp;<input type="text" value="${menusCourant.utilCree.nomUtil}" disabled="disabled"/>
                 </td>
               </tr>
               <tr>
                 <td>${utilisateur.langue.dateCree}</td>
                 <td>
                   <input type="text" value="${menusCourant.dateCree}" disabled="disabled"/>
                 </td>
               </tr>
               <tr>
                 <td>${utilisateur.langue.codeUtilModif}</td>
                 <td>
                   <input type="text" value="${menusCourant.utilModif.codeUtil}" disabled="disabled"/> &nbsp;&nbsp;&nbsp;<input type="text" value="${menusCourant.utilModif.nomUtil}" disabled="disabled"/>
                 </td>
              </tr>
              <tr>
                <td>${utilisateur.langue.dateModif}</td>
                <td>
                  <input type="text" value="${menusCourant.dateDernierModif}" disabled="disabled"/>
                </td>
              </tr>
             </c:if>
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
