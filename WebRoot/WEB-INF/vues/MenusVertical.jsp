<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="modele.metiers.Utilisateur" %>
<%@ page import="modele.metiers.Profil" %>
<%@ page import="modele.metiers.Menus" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/jquery.treeview.css" />
	
	<script src="javascript/jquery.js" type="text/javascript"></script>
	<script src="javascript/jquery.cookie.js" type="text/javascript"></script>
	<script src="javascript/jquery.treeview.js" type="text/javascript"></script>
	 
<script type="text/javascript">
		$(function() {
			$("#tree").treeview({
				collapsed: true,
				animated: "fast",
				control:"#sidetreecontrol",
				prerendered: true,
				persist: "location"
			});
		});
		
		function preferences(){
	fen=window.showModalDialog("../NAFA/Preferences.html", this, "dialogHeight:500px;dialogWidth:700px;");
	}
</script>

  </head>
  
  <body>
  
  <div id="sidetree">
  <div class="treeheader">&nbsp;</div>
  <div id="sidetreecontrol"> <a href="?#">${utilisateur.langue.reduirTs}</a> | <a href="?#">${utilisateur.langue.developTs}</a> </div>
  
  

  
    <ul class="treeview" id="tree">
    
    <li><a href="Nafa.html">${utilisateur.langue.acceuil}</a></li>
       
     <c:if test="${profilConnecte != null}">
     <c:choose>
     <c:when test="${profilConnecte.codeProfil == 'AdminUser'}">
      <li><a href="utilisateurs.html">${utilisateur.langue.utilisateur}s</a></li>
      <li><a href="profils.html">${utilisateur.langue.profils}</a></li>
      <li><a href="fonctionnalites.html">${utilisateur.langue.fonctionnalites}</a></li>
      <li><a href="FonctionnalitesProfil.html">${utilisateur.langue.fonctionnalites}_${utilisateur.langue.profils}</a></li>
      <li><a href="Menus.html">${utilisateur.langue.menus}</a></li>
     </c:when>
     
     <c:otherwise>
      <c:set var="compteur" value="0" scope="page"></c:set>
       <c:forEach items="${profilConnecte.menus}" var="menus">
        <c:if test="${menus.profilEffectif == 1}">
        
         <c:set var="compteur" value="1" scope="page"></c:set>
         
          <c:if test="${menus.fonctionnalite != null}">
            <c:forEach items="${menus.libelles}" var="libelle">
              <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
               <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
              </c:if>
            </c:forEach>
          </c:if>
          <c:if test="${menus.fonctionnalite == null}">
              <li class="expandable"><div class="hitarea expandable-hitarea"></div>
                  <span>
                    <c:forEach items="${menus.libelles}" var="libelle">
                      <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                        ${libelle.libMenus}
                      </c:if>
                    </c:forEach>
                  </span>
                  <ul style="display: none;">
                     <c:forEach items="${menus.listmenus}" var="menus">
                     
                         <c:if test="${menus.fonctionnalite != null}">
                           <c:forEach items="${menus.libelles}" var="libelle">
                            <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                             <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                            </c:if>
                          </c:forEach>
                         </c:if> 
                         <c:if test="${menus.fonctionnalite == null}">
                            <li class="expandable"><div class="hitarea expandable-hitarea"></div>
                              <c:forEach items="${menus.libelles}" var="libelle">
                                 <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                    <a>${libelle.libMenus}</a>
                                 </c:if>
                              </c:forEach>
                              <ul style="display: none;">
                                 <c:forEach items="${menus.listmenus}" var="menus">
                                    <c:if test="${menus.fonctionnalite != null}">
                                      <c:forEach items="${menus.libelles}" var="libelle">
                                       <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                         <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                       </c:if>
                                     </c:forEach>
                                    </c:if>
                                    
                                    <c:if test="${menus.fonctionnalite == null}">
                                       <li class="expandable"><div class="hitarea expandable-hitarea"></div>
                                         <c:forEach items="${menus.libelles}" var="libelle">
                                           <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                             <a>${libelle.libMenus}</a>
                                           </c:if>
                                        </c:forEach>
                                          <ul style="display: none;">
                                           <c:forEach items="${menus.listmenus}" var="menus">
                                             <c:if test="${menus.fonctionnalite != null}">
                                               <c:forEach items="${menus.libelles}" var="libelle">
                                                 <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                    <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                 </c:if>
                                               </c:forEach>
                                             </c:if>
                                             
                                             <c:if test="${menus.fonctionnalite == null}">
                                               <li class="expandable"><div class="hitarea expandable-hitarea"></div>
                                                 <c:forEach items="${menus.libelles}" var="libelle">
                                                    <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                      <a>${libelle.libMenus}</a>
                                                    </c:if>
                                                 </c:forEach>
                                                 <ul style="display: none;">
                                                   <c:forEach items="${menus.listmenus}" var="menus">
                                                     <c:if test="${menus.fonctionnalite != null}">
                                                      <c:forEach items="${menus.libelles}" var="libelle">
                                                        <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                           <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                        </c:if>
                                                      </c:forEach>
                                                     </c:if>
                                                     
                                                     <c:if test="${menus.fonctionnalite == null}">
                                                        <li class="expandable"><div class="hitarea expandable-hitarea"></div>
                                                          <c:forEach items="${menus.libelles}" var="libelle">
                                                             <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                <a>${libelle.libMenus}</a>
                                                             </c:if>
                                                          </c:forEach>
                                                          <ul style="display: none;">
                                                            <c:forEach items="${menus.listmenus}" var="menus">
                                                              <c:if test="${menus.fonctionnalite != null}">
                                                                 <c:forEach items="${menus.libelles}" var="libelle">
                                                                   <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                      <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                                   </c:if>
                                                                 </c:forEach>
                                                               </c:if>
                                                               
                                                               <c:if test="${menus.fonctionnalite == null}">
                                                                  <li class="expandable"><div class="hitarea expandable-hitarea"></div>
                                                                    <c:forEach items="${menus.libelles}" var="libelle">
                                                                      <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                        <a>${libelle.libMenus}</a>
                                                                      </c:if>
                                                                    </c:forEach>
                                                                    <ul style="display: none;">
                                                                      <c:forEach items="${menus.listmenus}" var="menus">
                                                                        <c:if test="${menus.fonctionnalite != null}">
                                                                          <c:forEach items="${menus.libelles}" var="libelle">
                                                                            <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                               <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                                            </c:if>
                                                                          </c:forEach>
                                                                         </c:if>
                                                                         
                                                                         <c:if test="${menus.fonctionnalite == null}">
                                                                          <li class="expandable"><div class="hitarea expandable-hitarea"></div>
                                                                            <c:forEach items="${menus.libelles}" var="libelle">
                                                                              <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                                <a>${libelle.libMenus}</a>
                                                                              </c:if>
                                                                            </c:forEach>
                                                                            <ul style="display: none;">
                                                                              <c:forEach items="${menus.listmenus}" var="menus">
                                                                                <c:if test="${menus.fonctionnalite != null}">
                                                                                  <c:forEach items="${menus.libelles}" var="libelle">
                                                                                    <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                                      <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                                                    </c:if>
                                                                                  </c:forEach>
                                                                                 </c:if>
                                                                                 
                                                                                 
                                                                              </c:forEach>
                                                                            </ul>
                                                                            
                                                                          </li>
                                                                         </c:if>
                                                                      </c:forEach>
                                                                    </ul>
                                                                  </li>
                                                               </c:if>
                                                            
                                                            </c:forEach>
                                                          </ul>
                                                        </li>
                                                     </c:if>
                                             
                                             
                                                   </c:forEach>
                                                 </ul>
                                               </li>
                                             </c:if>
                                           
                                           </c:forEach>
                                          </ul>
                                       </li>
                                    </c:if>
                                 </c:forEach>
                              </ul>
                            </li>
                         </c:if>
                     </c:forEach>
                  </ul>
              </li>
          </c:if> 
         
          
         </c:if>      
      </c:forEach>
      <c:if test="${compteur == 0}">
        <li><a href="validation.html">${utilisateur.langue.validation}</a></li>
      </c:if>
      </c:otherwise>
      
      </c:choose>
   </c:if>   
   
   <li><a onclick='preferences()'>${utilisateur.langue.preferences}</a></li>
   <li class="last"><a href="Deconnexion.html">${utilisateur.langue.deconnexion}</a></li>  
   
   </ul>
 </div>
 
  </body>
</html>
