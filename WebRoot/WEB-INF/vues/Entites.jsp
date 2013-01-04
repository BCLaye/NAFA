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
    
    <title>${utilisateur.langue.labelEntites}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="javascript/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="javascript/bootstrap.js"></script>
<script type="text/javascript">    
    function envoyerAction(code){
    with(document.formEntites){
					action.value=code;
					submit();
					}
	}
	
   function confirmation(){
    with(document.formEntites){
                   rep = confirm(message1.value);
                   if(rep == true){
                     action.value="updateEntites";
					 submit();
                   }
					
		}
	}
	
	function confirmerSuppression(){
	with(document.formEntites){
                   rep = confirm(message2.value);
                   if(rep == true){
                     action.value="supprimer";
					 submit();
                   }
					
		}	 
	}
	
	function editerLibelle(code){
    with(document.formEntites){
                    libelleEdit.value = code;
					action.value="editerLibelle";
					submit();
					}
	} 
	
	function deleteLibelle(code){
    with(document.formEntites){
                    rep = confirm(message2.value);
                    if(rep == true){
                     libelleEdit.value = code;
					action.value="deleteLibelle";
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
				<div id="contentTitle"><i class="nafa-home"></i>${utilisateur.langue.labelEntites}</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
				 <!-- ---------------------Début contenu Page  ------------------------------------------------- -->
				    
    <form action="entites.html" method="post" name="formEntites">
        <fieldset>
           <legend>${utilisateur.langue.labelEntites}</legend>
           <table>
              <tr>
                 <td colspan="2" align="center">
                    <jsp:include page="/WEB-INF/vues/menu.jsp"></jsp:include>
                 </td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.codeEntite}</td>
                 <td>
                   <input type="text" name="codeEntite" value="${entiteCourant.codeEntite}"/><font color="red">${errorCode}</font>
                 </td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.niveauOrg}</td>
                 <td><input type="text" name="niveauOrg" value="${entiteCourant.niveauOrg}"/><font color="red">${errorNbValid} ${errorFormat}</font></td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.utilRattach}</td>
                 <td>
                   <select name="utilRattach">
                     <c:if test="${entiteCourant.utilRattach == 'O'}">
                       <option value="O">${utilisateur.langue.oui}</option>
                       <option value="N">${utilisateur.langue.non}</option>
                     </c:if>
                     <c:if test="${entiteCourant.utilRattach == 'N'}">
                       <option value="N">${utilisateur.langue.non}</option>
                       <option value="O">${utilisateur.langue.oui}</option>                       
                     </c:if>
                     <c:if test="${entiteCourant.utilRattach == null}">
                       <option value="N">${utilisateur.langue.non}</option>
                       <option value="O">${utilisateur.langue.oui}</option>                       
                     </c:if>
                   </select>
                 </td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.entiteRattach}</td>
                 <td>
                    <select name="entiteRattach">
                       <c:if test="${entiteCourant.entiteRattach != null}">
                         <option value="${entiteCourant.entiteRattach.codeEntite}">${entiteRattach.libEntite}</option>
                       </c:if>
                       <c:if test="${entiteCourant.entiteRattach == null}">
                        <option value="">--------------------------------</option>
                       </c:if> 
                        <c:forEach items="${langueEntites}" var="langueEntite">
                         <option value="${langueEntite.entite.codeEntite}">${langueEntite.libEntite}</option>
                        </c:forEach>
                    </select>
                    <font color="red"> ${errorEntiteRattach}</font>
                 </td>
              </tr>
            <c:if test="${recherche != null}"> 
              <tr>
                 <td>${utilisateur.langue.codeUtilCree}</td>
                 <td><input type="text" disabled="disabled" value="${entiteCourant.utilCree.codeUtil}"/> &nbsp;&nbsp;<input type="text" disabled="disabled" value="${entiteCourant.utilCree.nomUtil}"/></td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.dateCree}</td>
                 <td><input type="text" disabled="disabled" value="${entiteCourant.dateCree}"/></td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.codeUtilModif}</td>
                 <td><input type="text" disabled="disabled" value="${entiteCourant.utilModif.codeUtil}"/> &nbsp;&nbsp;<input type="text" disabled="disabled" value="${entiteCourant.utilModif.nomUtil}"/></td>
              </tr>
              <tr>
                 <td>${utilisateur.langue.dateModif}</td>
                 <td><input type="text" disabled="disabled" value="${entiteCourant.dateDernierModif}"/></td>
              </tr>
           </c:if>   
              <tr>
                 <td>${utilisateur.langue.nom} &nbsp;&nbsp;&nbsp;<button class="btn btn-link nafa-plus nafa-large" onclick='envoyerAction("ajouterNom")'></button></td>
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
                                  <td><input type="text" value="${libelle.libEntite}" disabled="disabled"/>&nbsp;&nbsp;&nbsp;<input type="button" id="editFct" onclick='editerLibelle("${libelle.langue.codeLangue}")'/>&nbsp;&nbsp;&nbsp;<input type="button" id="deleteLib" onclick='deleteLibelle("${libelle.langue.codeLangue}")'/></td>
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
                                         <input type="text" name="libelle" value="${libEdit.libEntite}"/>
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
           <input type="hidden" name="action"/>
           <input type="hidden" name="libelleEdit"/>
           <input type="hidden" name="message1" value="${utilisateur.langue.confirmationEnregistrer}"/>
           <input type="hidden" name="message2" value="${utilisateur.langue.confirmationSupprimer}"/>
        </fieldset>
    </form>
				 <!-- ---------------------Début contenu Page  ------------------------------------------------- -->
				</div>
			</div>
		<c:if test='${utilisateur.preference.theme == "theme1"}'>
		</div>
		</c:if>
	
	</div>
	
	<div id="footer"></div>
 
  </body>
</html>
