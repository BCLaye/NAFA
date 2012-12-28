<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${utilisateur.langue.labelProfils}</title>
    
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
    with(document.formProfils){
					action.value=code;
					submit();
					}
	}
	
   function confirmation(){
    with(document.formProfils){
                   rep = confirm(message1.value);
                   if(rep == true){
                     action.value="updateProfils";
					 submit();
                   }
					
		}
	}
	
	function confirmerSuppression(){
	with(document.formProfils){
                   rep = confirm(message2.value);
                   if(rep == true){
                     action.value="supprimer";
					 submit();
                   }
					
		}	 
	}
    
    function editerLibelle(code){
    with(document.formProfils){
                    libelleEdit.value = code;
					action.value="editerLibelle";
					submit();
					}
	} 
	
	function deleteLibelle(code){
    with(document.formProfils){
                    rep = confirm(message2.value);
                    if(rep == true){
                     libelleEdit.value = code;
					action.value="deleteLibelle";
					submit();
                    }
				}
	}
	
	function deleteMenus(code){
    with(document.formProfils){
                    rep = confirm(message2.value);
                    if(rep == true){
                     libelleEdit.value = code;
					action.value="deleteMenus";
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
				<div id="contentTitle"><i class="nafa-home"></i>${utilisateur.langue.labelProfils}</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
<!-- ---------------------------------début contenu de la Page-------------------------------------------------- -->
	<form action="profils.html" method="post" name="formProfils">
       <fieldset>
           <legend>${utilisateur.langue.labelProfils}</legend>
           <table>
               <tr>
                  <td colspan="2" align="center">
                    <jsp:include page="/WEB-INF/vues/menu.jsp"></jsp:include>
                  </td>
               </tr>
               <tr>
                  <td>${utilisateur.langue.codeProfil}</td>
                  <td>
                    <input type="text" name="codeProfil" value="${profilCourant.codeProfil}"/><font color="red">${errorCode}</font>
                  </td>
               </tr>
               <tr>
                  <td>${utilisateur.langue.labelNomenclatureMDP}</td>
                  <td>
                    <select name="nomenclatureMDP">
                      <c:if test="${profilCourant.nomenclatureMDP != null}">
                        <option value="${profilCourant.nomenclatureMDP.id}">${profilCourant.nomenclatureMDP.id}</option>
                      </c:if>
                      <c:if test="${profilCourant.nomenclatureMDP == null}">
                      <option value="">--------------------------------</option>
                      </c:if>
                      <c:forEach items="${nomenclatureMDPs}" var="nomenMDP">
                         <option value="${nomenMDP.id}">${nomenMDP.id}</option>
                      </c:forEach>
                    </select> <font color="red">${errorNomenclature}</font>
                  </td>
               </tr>
               <tr>
                  <td>${utilisateur.langue.nbValidEff}</td>
                  <td>
                    <input type="text" name="${nomenMDP.id}"/>
                  </td>
               </tr>
               <c:if test="${recherche != null}">
               <tr>
                  <td>${utilisateur.langue.codeUtilCree}</td>
                  <td>
                     <input type="text" value="${profilCourant.utilCree.codeUtil}" disabled="disabled"/> &nbsp;&nbsp;&nbsp;<input type="text" value="${profilCourant.utilCree.nomUtil}" disabled="disabled"/>
                  </td>
               </tr>
               <tr>
                  <td>${utilisateur.langue.dateCree}</td>
                  <td>
                    <input type="text" value="${profilCourant.dateCree}" disabled="disabled"/>
                  </td>
               </tr>
               <tr>
                  <td>${utilisateur.langue.codeUtilModif}</td>
                  <td>
                     <input type="text" value="${profilCourant.utilModif.codeUtil}" disabled="disabled"/> &nbsp;&nbsp;&nbsp;<input type="text" value="${profilCourant.utilModif.nomUtil}" disabled="disabled"/>
                  </td>
               </tr>
               <tr>
                  <td>${utilisateur.langue.dateModif}</td>
                  <td>
                    <input type="text" value="${profilCourant.dateModif}" disabled="disabled"/>
                  </td>
               </tr>
               
               <tr>
                <td>${utilisateur.langue.menus} &nbsp;&nbsp;&nbsp;<input type="button" id="ajouter"/></td>
                <td></td>
                </tr>
                <c:forEach items="${profilCourant.menus}" var="menus">
                   <c:if test="${menus.profilEffectif == 1}"> 
                      <c:forEach items="${menus.libelles}" var="libelle" >
                        <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                          <tr>
                            <td></td>
                            <td><input type="text" disabled="disabled" value="${libelle.libMenus}"/>&nbsp;&nbsp;&nbsp;<input type="button" id="deleteLib" onclick='deleteMenus("${menus.codeMenus}")'/></td>
                          </tr>
                        </c:if>
                      </c:forEach>
                  </c:if>
                </c:forEach>
                <tr>
                  <td></td>
                  <td>
                    <select name="menus" onchange='envoyerAction("ajouterMenus")'>
                       <option value="">--------------------------------</option>
                       <c:forEach items="${profilCourant.menus}" var="menus">
                         <c:if test="${menus.profilEffectif == 0}"> 
                          <c:if test="${fn:length(menus.menus) == 0}">
                            <c:forEach items="${menus.libelles}" var="libelle" >
                               <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                               <option value="${menus.codeMenus}">${libelle.libMenus}</option>
                               </c:if>
                            </c:forEach>
                           </c:if>
                         </c:if>
                      </c:forEach>
                    </select>
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
                              <c:forEach items="${listLibelle}" var="libelle">
                               <tr>
                                  <td><input type="text" value="${libelle.langue.nomLangue}" disabled="disabled"/></td>
                                  <td><input type="text" value="${libelle.libelle}" disabled="disabled"/>&nbsp;&nbsp;&nbsp;<input type="button" id="editFct" onclick='editerLibelle("${libelle.langue.codeLangue}")'/>&nbsp;&nbsp;&nbsp;<input type="button" id="deleteLib" onclick='deleteLibelle("${libelle.langue.codeLangue}")'/></td>
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
                                       <input type="text" name="libelle" value="${libEdit.libelle}"/>
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
               <tr>
                  <td colspan="2" align="center">
                     <jsp:include page="/WEB-INF/vues/Boutons.jsp"></jsp:include>
                  </td>
               </tr>
           </table>
       </fieldset>
           <input type="hidden" name="action"/>
           <input type="hidden" name="libelleEdit"/>
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
