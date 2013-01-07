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
    
    <title>${utilisateur.langue.labelUtilisateur}</title> 
    
	
	<link rel="stylesheet" href="css/demos.css" /> 
	
<script type="text/javascript">   
   $(function() {
		$( "#datepicker" ).datepicker();
	});
	
	 $(function() {
		$( "#datepicker2" ).datepicker();
	}); 
	
	$(function() {
		$( "#datepicker3" ).datepicker();
	});
	
	 $(function() {
		$( "#datepicker4" ).datepicker();
	});   
	
   function envoyerAction(code){
    with(document.formUtilisateur){
					action.value=code;
					submit();
					}
	}
	
	function editerProfil(code){
	fen=window.showModalDialog("../NAFA/UtilisateurProfil.html?profil="+code+"", this, "dialogHeight:500px;dialogWidth:700px;");
	}
	
	function confirmation(){
    with(document.formUtilisateur){
                   rep = confirm(message1.value);
                   if(rep == true){
                     action.value="updateUtilisateur";
					 submit();
                   }
					
		}
	} 
	
	function confirmerSuppression(){
	with(document.formUtilisateur){
                   rep = confirm(message2.value);
                   if(rep == true){
                     action.value="supprimer";
					 submit();
                   }
					
		}	 
	}
	
	function deleteProfil(code){
	  with(document.formUtilisateur){
                   rep = confirm(message2.value);
                   if(rep == true){
                     action.value="deleteUtilProfil";
                     utilProfil.value = code;
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
				<div id="contentTitle"><i class="nafa-home"></i>${utilisateur.langue.labelUtilisateur}</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
<!-- ---------------------------------début contenu de la Page-------------------------------------------------- -->
	<form action="utilisateurs.html" method="post" name="formUtilisateur" id="formUtilisateur">
       <fieldset>
          <legend>${utilisateur.langue.labelUtilisateur}</legend>
    <table>
        <tr>
           <td colspan="2" align="center">
              <jsp:include page="/WEB-INF/vues/menu.jsp"></jsp:include>
           </td>
         </tr>  
         <tr>  
         
        <td> <!-- ___________________________________Début table 1_________________________________ -->
          <table>
            <tbody>   
             <tr>
               <td>${utilisateur.langue.entite}</td>
               <td>
                  <select name="entite" onchange='envoyerAction("selectionEntite")'>
                     <c:if test="${utilisateurCourant.entite != null}">
                       <option value="${utilisateurCourant.entite.codeEntite}">${entiteSelect}</option>
                     </c:if>
                     <c:if test="${utilisateurCourant.entite == null}">
                     <option value="">--------------------------------</option>
                     </c:if>
                     <c:forEach items="${listEntites}" var="Libentite">
                       <option value="${Libentite.entite.codeEntite}">${Libentite.libEntite}</option>
                     </c:forEach>
                  </select>
               </td>
             </tr>
             <tr>
               <td>${utilisateur.langue.fonction}</td>
               <td>
                  <select name="fonction">
                    <c:if test="${utilisateurCourant.fonction != null}">
                     <option value="${utilisateurCourant.fonction.code_fonction}">${fonctionSelect}</option>
                    </c:if>
                    <c:if test="${utilisateurCourant.fonction == null}">
                     <option value="">--------------------------------</option> 
                    </c:if> 
                    <c:forEach items="${listFonctions}" var="Libfction">
                      <option value="${Libfction.fonction.code_fonction}">${Libfction.libFonctions}</option>
                    </c:forEach>
                  </select>
               </td>
             </tr>                
              <tr>
                 <td>${utilisateur.langue.codeUser}</td>
                 <c:if test="${recherche == null}">
                 <td><input type="text" name="codeUser" value="${utilisateurCourant.codeUtil}"/><font color="red">${errorCode}</font></td>
                 </c:if>
                 <c:if test="${recherche != null}">
                 <td><input type="text" value="${utilisateurCourant.codeUtil}" disabled="disabled"/><input type="hidden" name="codeUser" value="${utilisateurCourant.codeUtil}"/></td>
                 </c:if>
              </tr>
              <tr>
                <td>${utilisateur.langue.nom}</td>
                <td><input type="text" name="nom" value="${utilisateurCourant.nomUtil}"/><font color="red">${errorNom}</font></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.langue}</td>
                <td>
                   <select name="langue">
                        <c:if test="${utilisateurCourant.langue != null}">
                        <option value="${utilisateurCourant.langue.codeLangue}">${utilisateurCourant.langue.nomLangue}</option>
                        </c:if>
                        <c:if test="${utilisateurCourant.langue == null}">
                        <option value="">--------------------------------</option>
                        </c:if>
                        <c:forEach items="${langues}" var="langue">
                          <option value="${langue.codeLangue}">${langue.nomLangue}</option>
                        </c:forEach>
                    </select><font color="red">${errorlangue}</font>
                </td>
              </tr>
              <c:if test="${recherche == null }"> 
              <tr>
                <td>${utilisateur.langue.motPass}</td>
                <td><input type="text" name="motPass" value="${motPass}"/><font color="red">${errorMDP}</font></td>
              </tr>
              </c:if>
              <tr>
               <td>${utilisateur.langue.nivPriv}</td>
               <td>
                  <select name="nivPriv">
                     <c:if test="${utilisateurCourant.niveauPrivileg != null }">
                      <option value="${utilisateurCourant.niveauPrivileg}">${utilisateurCourant.niveauPrivileg}</option>
                     </c:if>
                     <c:if test="${utilisateurCourant.niveauPrivileg == null }">
                     <option value="">--------------------------------</option>
                     </c:if>
                     <option value="0">0</option>
                     <option value="1">1</option>
                     <option value="2">2</option>
                     <option value="3">3</option>
                     <option value="4">4</option>
                     <option value="5">5</option>
                     <option value="6">6</option>
                     <option value="7">7</option>
                     <option value="8">8</option>
                     <option value="9">9</option>
                  </select><font color="red">${errornivPriv}</font>
               </td>
             </tr>
              <tr>
                <td>${utilisateur.langue.dateDebValid}</td>
                <td><input type="text" name="dateDebValid" value="${dateDeb}" id="datepicker"/><font color="red">${errorDateDeb}</font></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.dateFinValid}</td> 
                <td><input type="text" name="dateFinValid" value="${dateFin}" id="datepicker2"/><font color="red">${errorDateFin}</font></td>
              </tr>    
              <tr>
                 <td>${utilisateur.langue.profils}<button class="btn btn-link nafa-plus nafa-large" onclick='envoyerAction("ajouterProfil")'></button></td>
                 <td></td>
              </tr>
              <c:if test="${utilisateurCourant.profils != null}">
               <c:forEach items="${utilisateurCourant.profils}" var="profil">
                <tr>                  
                    <td></td>
                    <td>
                      <input type="text" value="${profil.profil.codeProfil}" disabled="disabled"/>
                      <button class="btn btn-link nafa-pencil nafa-large" onclick='editerProfil("${profil.profil.codeProfil}")'></button><button class="btn btn-link nafa-trashcan nafa-large" onclick='deleteProfil("${profil.profil.codeProfil}")'></button>
                    </td>
                </tr>
                </c:forEach>
              </c:if>
              <tr> 
                <td></td>  
                 <td>
                    <select name="profil" onchange='envoyerAction("selectionProfil")'>
                       <c:if test="${profilSelect != null }">
                         <option value="${profilSelect.profil.codeProfil}">${profilSelect.libelle}</option>
                       </c:if>
                       <c:if test="${profilSelect == null }">
                       <option value="">--------------------------------</option>
                       </c:if>
                       <c:forEach items="${listProfil}" var="langProfil">
                        <option value="${langProfil.profil.codeProfil}">${langProfil.libelle}</option>
                       </c:forEach>
                    </select><font color="red">${errorProfil}</font>
                    
                    <c:if test="${selectionProfil != null}">
                       <table>
                           <tr>
                              <th>${utilisateur.langue.dateDebValid}</th>
                              <th>${utilisateur.langue.dateFinValid}</th>
                           </tr>
                           <tr>
                             <td><input type="text" name="dateDebValProf" id="datepicker3"/><font color="red">${errorDateDebProf}</font></td>
                             <td><input type="text" name="dateFinValProf" id="datepicker4"/><font color="red">${errorDateFinProf}</font></td>
                           </tr>
                       </table>
                    </c:if>
                    
                 </td>
              </tr>                    
            </tbody>
          </table>           
        </td> <!-- ____________________________Fin table 1 _________________________________ -->
                
        <td>  <!-- ____________________________Debut table 2 _________________________________ -->
           <c:if test="${recherche != null }"> 
             <table>
              <tr>
                <td>${utilisateur.langue.etat}</td>
                <td>
                
                  <c:if test="${noModifiable != null }">
                   <select name="etat" disabled="disabled">
                     <option value="">Inactif</option>
                   </select>
                  </c:if>
                  
                  <c:if test="${noModifiable == null }">
                  <c:if test="${utilisateurCourant.etatutil == 'Inactif'}">
                   <select name="etat">
                     <option value="Inactif">Inactif</option>
                     <option value="Actif">Actif</option>
                     <option value="Suspendu">Suspendu</option>
                   </select>                   
                  </c:if>
                  <c:if test="${utilisateurCourant.etatutil == 'Actif'}">
                   <select name="etat">
                     <option value="Actif">Actif</option>
                     <option value="Suspendu">Suspendu</option>
                   </select>                   
                  </c:if>
                  <c:if test="${utilisateurCourant.etatutil == 'Suspendu'}">
                   <select name="etat">
                     <option value="Suspendu">Suspendu</option>
                     <option value="Actif">Actif</option>
                   </select>                   
                  </c:if>
                  <c:if test="${utilisateurCourant.etatutil == 'Supprime'}">
                   <select name="etat" disabled="disabled">
                     <option value="Supprime">Supprime</option>
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
                <td>${utilisateur.langue.dateDernierConnex}</td>
                <td><input type="text" value="${dateDernierConnex}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.heureDernierConnex}</td>
                <td><input type="text" value="${utilisateurCourant.heureDernierConnexion}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.adressIPDC}</td>
                <td><input type="text" value="${utilisateurCourant.adressIPDernierConnexion}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.dateDernierDeconnex}</td>
                <td><input type="text" value="${dateDernierDeconnexion}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.heureDernierDeconnex}</td>
                <td><input type="text" value="${utilisateurCourant.heureDernierDeconnexion}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.dateDernierModifMDP}</td>
                <td><input type="text" value="${dateDernierModifMDP}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.heureDernierModifMDP}</td>
                <td><input type="text" value="${utilisateurCourant.heureDernierModifMDP}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.nbValidEff}</td>
                <td><input type="text" value="${utilisateurCourant.nbValidEffect}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.codeUtilCree}</td>
                <td>
                  <input type="text" value="${utilisateurCourant.utilCree.codeUtil}" disabled="disabled"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="text" value="${utilisateurCourant.utilCree.nomUtil}" disabled="disabled"/>
                </td>
              </tr>
              <tr>
                <td>${utilisateur.langue.dateCree}</td>
                <td><input type="text" value="${dateCree}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.heureCree}</td>
                <td><input type="text" value="${utilisateurCourant.heureCree}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.codeUtilModif}</td>
                <td>
                   <input type="text" value="${utilisateurCourant.utilModif.codeUtil}" disabled="disabled"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="text" value="${utilisateurCourant.utilModif.nomUtil}" disabled="disabled"/>
                </td>
              </tr>
              <tr>
                <td>${utilisateur.langue.dateModif}</td>
                <td><input type="text"  value="${dateModif}" disabled="disabled"/></td>
              </tr>
              <tr>
                <td>${utilisateur.langue.heureModif}</td>
                <td><input type="text" value="${utilisateurCourant.heureDernierModif}" disabled="disabled"/></td>
              </tr>
             </table> 
            </c:if>        
        </td>  <!-- ________________________________Fin table 2__________________________________ -->
         </tr>
         <tr>
           <td colspan="2" align="center">
               <jsp:include page="/WEB-INF/vues/Boutons.jsp"></jsp:include>
           </td>
        </tr>
    </table>   
     </fieldset>
       <input type="hidden" name="action"/>
       <input type="hidden" name="utilProfil"/>
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
