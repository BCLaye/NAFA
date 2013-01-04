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
    
    <title>${utilisateur.langue.labelFonctionnalite}</title>
    
	
<script type="text/javascript">    
    function envoyerAction(code){
    with(document.formFonctionnalites){
					action.value=code;
					submit();
					}
	}
	
   function confirmation(){
    with(document.formFonctionnalites){
                   rep = confirm(message1.value);
                   if(rep == true){
                     action.value="updateFonctionnalite";
					 submit();
                   }
					
		}
	}
	
	function confirmerSuppression(){
	with(document.formFonctionnalites){
                   rep = confirm(message2.value);
                   if(rep == true){
                     action.value="supprimer";
					 submit();
                   }
					
		}	 
	}
	
	function EditerLibelle(code){
    with(document.formFonctionnalites){
                    libelle.value = code;
					action.value="editerLibelle";
					submit();
					}
	} 
	
	function deleteLibelle(code){
    with(document.formFonctionnalites){
                    rep = confirm(message2.value);
                    if(rep == true){
                     libelle.value = code;
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
				<div id="contentTitle"><i class="nafa-home"></i>${utilisateur.langue.labelFonctionnalite}</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
	<!-- ----------------------------Début du contenu de la Page -------------------------------------- -->
	<form action="fonctionnalites.html" method="post" name="formFonctionnalites">
       <fieldset>
            <legend>${utilisateur.langue.labelFonctionnalite}</legend>
            <table>
               <tbody>
                  <tr>
                     <td colspan="2" align="center">
                        <jsp:include page="/WEB-INF/vues/menu.jsp"></jsp:include>
                     </td>
                  </tr>
                  <tr>
                     <td>${utilisateur.langue.codeFonctionnalite}</td>
                        <td><input type="text" name="codeFonctionnalite" value="${fonctionnaliteCourant.codeFonctionnalite}"/><font color="red">${errorCode}</font></td>
                  </tr>            
                  <tr>
                     <td>${utilisateur.langue.nbValidReq}</td>
                     <td><input type="text" name="nbValidReq" value="${fonctionnaliteCourant.nbValidReq}"/><font color="red">${errorNbValid} ${errorFormat}</font></td>
                  </tr>
                  <tr>
                     <td>${utilisateur.langue.module}</td>
                     <td>
                       <select name="module">
                          <c:if test="${fonctionnaliteCourant.module != null}">
                            <option value="${fonctionnaliteCourant.module.codeModule}">${fonctionnaliteCourant.module.codeModule}</option>
                          </c:if>
                          <c:if test="${fonctionnaliteCourant.module == null}">
                          <option value="">--------------------------------</option>
                          </c:if>  
                           <c:forEach items="${modules}" var="module">
                            <option value="">${module.codeModule}</option>
                          </c:forEach>                                          
                       </select>
                     </td>
                  </tr>
                  <tr>
                     <td>${utilisateur.langue.programme}</td>
                     <td>
                        <select name="programme">
                           <c:if test="${fonctionnaliteCourant.programme != null}">
                            <option value="${fonctionnaliteCourant.programme.codeProgramme}">${fonctionnaliteCourant.programme.codeProgramme}</option>
                           </c:if>
                           <c:if test="${fonctionnaliteCourant.programme == null}">
                           <option value="">--------------------------------</option>
                           </c:if>
                           <c:forEach items="${programmes}" var="programme">
                            <option value="${programme.codeProgramme}">${programme.codeProgramme}</option>
                           </c:forEach>
                       </select>
                     </td>
                  </tr>
                 <c:if test="${recherche != null}">
                   <tr>
                     <td>${utilisateur.langue.codeUtilCree}</td>
                      <td>
                       <input type="text" value="${fonctionnaliteCourant.uitlCree.codeUtil}" disabled="disabled"/> &nbsp;&nbsp;&nbsp;<input type="text" value="${fonctionnaliteCourant.uitlCree.nomUtil}" disabled="disabled"/>
                     </td>
                    </tr>
                    <tr>
                      <td>${utilisateur.langue.dateCree}</td>
                      <td>
                        <input type="text" value="${fonctionnaliteCourant.dateCree}" disabled="disabled"/>
                      </td>
                   </tr>
                   <tr>
                     <td>${utilisateur.langue.codeUtilModif}</td>
                     <td>
                       <input type="text" value="${fonctionnaliteCourant.uitlModif.codeUtil}" disabled="disabled"/> &nbsp;&nbsp;&nbsp;<input type="text" value="${fonctionnaliteCourant.uitlModif.nomUtil}" disabled="disabled"/>
                    </td>
                  </tr>
                  <tr>
                    <td>${utilisateur.langue.dateModif}</td>
                    <td>
                       <input type="text" value="${fonctionnaliteCourant.dateModif}" disabled="disabled"/>
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
                                  <td><input type="text" value="${libelle.libFonctionnalie}" disabled="disabled"/>&nbsp;&nbsp;&nbsp;<input type="button" id="editFct" onclick='EditerLibelle("${libelle.langue.codeLangue}")'/>&nbsp;&nbsp;&nbsp;<input type="button" id="deleteLib" onclick='deleteLibelle("${libelle.langue.codeLangue}")'/></td>
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
                     <td colspan="2" align="center">
                        <jsp:include page="/WEB-INF/vues/Boutons.jsp"></jsp:include>
                     </td>
                  </tr>
               </tbody>
            </table>
            <input type="hidden" name="action"/>
            <input type="hidden" name="libelle"/>
            <input type="hidden" name="message2" value="${utilisateur.langue.confirmationSupprimer}"/>
            <c:if test="${confirmer != null }">
               <input type="hidden" name="message1" value="${utilisateur.langue.confirmationEnregistrer}"/>
            </c:if>
       </fieldset>
     </form>
 <!-- ----------------------------Fin du contenu de la Page -------------------------------------- -->
				</div>
			</div>
		<c:if test='${utilisateur.preference.theme == "theme1"}'>
		</div>
		</c:if>
	
	</div>
	
	<div id="footer"></div>
  <body>
</html>
