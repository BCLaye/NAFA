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
    
    <title>${utilisateur.langue.labelFonction}</title>
    
	
<script type="text/javascript">    
    function envoyerAction(mess){
    with(document.formFonction){
					action.value=mess;
					submit();
					}
	}
	
   function confirmation(){
    with(document.formFonction){
                   rep = confirm(message1.value);
                   if(rep == true){
                     action.value="updateFonctionns";
					 submit();
                   }
					
		}
	}
	
	function confirmerSuppression(){
	with(document.formFonction){
                   rep = confirm(message2.value);
                   if(rep == true){
                     action.value="supprimer";
					 submit();
                   }
					
		}	 
	}
	
	function EditerLibelle(mess){
    with(document.formFonction){
                    libelle.value = mess;
					action.value="editerLibelle";
					submit();
					}
	} 
	
	function deleteLibelle(mess){
    with(document.formFonction){
                    rep = confirm(message2.value);
                    if(rep == true){
                     libelle.value = mess;
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
				<div id="contentTitle"><i class="nafa-home"></i>${utilisateur.langue.labelFonction}</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
<!-- ---------------------------------début contenu de la Page-------------------------------------------------- -->
	<form action="Fonctions.html" name="formFonction" method="post">
      <fieldset>
         <legend>${utilisateur.langue.labelFonction}</legend>
       <table>
          <tr>
            <td colspan="2" align="center">
               <jsp:include page="/WEB-INF/vues/menu.jsp"></jsp:include>
            </td>
          </tr>      
          <tr>
            <td>${utilisateur.langue.codefonction}</td>
            <td><input type="text" name="code" value="${fonctionCourant.code_fonction}"/><font color="red">${errorCode}</font></td>
          </tr>
         <c:if test="${recherche != null}">
          <tr>
            <td>${utilisateur.langue.codeUtilCree}</td>
            <td></td>
          </tr>
          <tr>
            <td>${utilisateur.langue.dateCree}</td>
            <td></td>
          </tr>
          <tr>
            <td>${utilisateur.langue.codeUtilModif}</td>
            <td></td>
          </tr>
          <tr>
            <td>${utilisateur.langue.dateModif}</td>
            <td></td>
          </tr>
         </c:if> 
         <tr>
            <td>${utilisateur.langue.nom} &nbsp;&nbsp;&nbsp;<input type="button" id="ajouter" onclick='envoyerAction("ajouterNom")'/>
            <td>
             
               <table>
                    <thead>
                          <tr>
                             <th>${utilisateur.langue.langue}</th>
                             <th>${utilisateur.langue.nom}</th>
                           </tr>
                     </thead>
                         <tbody>
                          <c:forEach items="${fonctionCourant.libelleFonctions}" var="libelle">
                            <tr>
                               <td><input type="text" value="${libelle.langue.nomLangue}" disabled="disabled"/></td>
                               <td><input type="text" value="${libelle.libFonctions}" disabled="disabled"/>&nbsp;&nbsp;&nbsp;<input type="button" id="editFct" onclick='EditerLibelle("${libelle.langue.codeLangue}")'/>&nbsp;&nbsp;&nbsp;<input type="button" id="deleteLib" onclick='deleteLibelle("${libelle.langue.codeLangue}")'/></td>
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
          <tr>
            <td>${utilisateur.langue.entite}</td>
            <td></td>
           </tr>
         <c:forEach items="${fonctionCourant.entite}" var="entite">
           <tr>
               <td></td>
               <td><input type="text" disabled="disabled" value="${entite.codeEntite}"/>&nbsp;&nbsp;&nbsp;<input type="button" id="deleteLib"/></td>
           </tr>
         </c:forEach>
           <tr>
             <td></td>
             <td> 
             <select name="entite" onchange='envoyerAction("ajouterEntite")'>
                     <option value="">--------------------------------</option>
                     <c:forEach items="${listEntites}" var="Libentite">
                       <option value="${Libentite.entite.codeEntite}">${Libentite.libEntite}</option>
                     </c:forEach>
              </select> <font color="red">${errorEntite}</font>
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
