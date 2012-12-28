<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="modele.metiers.Utilisateur" %>
<%@ page import="modele.metiers.Profil" %>
<%@ page import="modele.metiers.Menus" %>

<script type="text/javascript">
	function preferences(){
	fen=window.showModalDialog("../NAFA/Preferences.html", this, "dialogHeight:500px;dialogWidth:700px;");
	}
</script>

   <ul class="n1">
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
              <li class="sm">
                  <a>
                    <c:forEach items="${menus.libelles}" var="libelle">
                      <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                        ${libelle.libMenus}
                      </c:if>
                    </c:forEach>
                  </a>
                  <ul class="n2">
                     <c:forEach items="${menus.listmenus}" var="menus">
                     
                         <c:if test="${menus.fonctionnalite != null}">
                           <c:forEach items="${menus.libelles}" var="libelle">
                            <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                             <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                            </c:if>
                          </c:forEach>
                         </c:if> 
                         <c:if test="${menus.fonctionnalite == null}">
                            <li class="sm">
                              <c:forEach items="${menus.libelles}" var="libelle">
                                 <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                    <a>${libelle.libMenus}</a>
                                 </c:if>
                              </c:forEach>
                              <ul class="n3">
                                 <c:forEach items="${menus.listmenus}" var="menus">
                                    <c:if test="${menus.fonctionnalite != null}">
                                      <c:forEach items="${menus.libelles}" var="libelle">
                                       <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                         <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                       </c:if>
                                     </c:forEach>
                                    </c:if>
                                    
                                    <c:if test="${menus.fonctionnalite == null}">
                                       <li class="sm">
                                         <c:forEach items="${menus.libelles}" var="libelle">
                                           <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                             <a>${libelle.libMenus}</a>
                                           </c:if>
                                        </c:forEach>
                                          <ul class="n4">
                                           <c:forEach items="${menus.listmenus}" var="menus">
                                             <c:if test="${menus.fonctionnalite != null}">
                                               <c:forEach items="${menus.libelles}" var="libelle">
                                                 <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                    <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                 </c:if>
                                               </c:forEach>
                                             </c:if>
                                             
                                             <c:if test="${menus.fonctionnalite == null}">
                                               <li class="sm">
                                                 <c:forEach items="${menus.libelles}" var="libelle">
                                                    <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                      <a>${libelle.libMenus}</a>
                                                    </c:if>
                                                 </c:forEach>
                                                 <ul class="n5">
                                                   <c:forEach items="${menus.listmenus}" var="menus">
                                                     <c:if test="${menus.fonctionnalite != null}">
                                                      <c:forEach items="${menus.libelles}" var="libelle">
                                                        <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                           <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                        </c:if>
                                                      </c:forEach>
                                                     </c:if>
                                                     
                                                     <c:if test="${menus.fonctionnalite == null}">
                                                        <li class="sm">
                                                          <c:forEach items="${menus.libelles}" var="libelle">
                                                             <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                <a>${libelle.libMenus}</a>
                                                             </c:if>
                                                          </c:forEach>
                                                          <ul class="6">
                                                            <c:forEach items="${menus.listmenus}" var="menus">
                                                              <c:if test="${menus.fonctionnalite != null}">
                                                                 <c:forEach items="${menus.libelles}" var="libelle">
                                                                   <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                      <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                                   </c:if>
                                                                 </c:forEach>
                                                               </c:if>
                                                               
                                                               <c:if test="${menus.fonctionnalite == null}">
                                                                  <li class="sm">
                                                                    <c:forEach items="${menus.libelles}" var="libelle">
                                                                      <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                        <a>${libelle.libMenus}</a>
                                                                      </c:if>
                                                                    </c:forEach>
                                                                    <ul class="n7">
                                                                      <c:forEach items="${menus.listmenus}" var="menus">
                                                                        <c:if test="${menus.fonctionnalite != null}">
                                                                          <c:forEach items="${menus.libelles}" var="libelle">
                                                                            <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                               <li><a href="${menus.fonctionnalite.programme.codeProgramme}.html">${libelle.libMenus}</a></li>
                                                                            </c:if>
                                                                          </c:forEach>
                                                                         </c:if>
                                                                         
                                                                         <c:if test="${menus.fonctionnalite == null}">
                                                                          <li class="sm">
                                                                            <c:forEach items="${menus.libelles}" var="libelle">
                                                                              <c:if test="${libelle.langue.codeLangue == utilisateur.langue.codeLangue}">
                                                                                <a>${libelle.libMenus}</a>
                                                                              </c:if>
                                                                            </c:forEach>
                                                                            <ul class="n8">
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
   
   
   <li><a href="Deconnexion.html">${utilisateur.langue.deconnexion}</a></li>  
   <li><a class="pref" onclick='preferences()'>${utilisateur.langue.preferences}</a></li>
   </ul>