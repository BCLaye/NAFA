<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Connexion</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	
<script type="text/javascript">
function changerMotDPass(){
    fen=window.showModalDialog("../NAFA/ChangerMotDPasse.html", this, "dialogHeight:200px;dialogWidth:400px;");
	}
</script>

  </head>
  <c:if test="${changePass != null}">
  <body onload='changerMotDPass()'>
  </c:if>
  <c:if test="${changePass == null}">
    <body>
  </c:if>
 <div id="header">
	  <jsp:include page="/WEB-INF/vues/Header1.jsp"></jsp:include>
  </div>   
  <div class="page">
    <div id="content" align="center">
     <form action="connexion.html" method="post" name="formConnexion" id="Connexion" style="height: 250px; width: 500px;"> 
  <fieldset> 
   <legend align="left"><img src="images/connexion.jpg" width="70px"/></legend>
   <table align="center">
      <tbody>
        <tr>
           <td colspan="2"><font color="red">${errorUtil}</font></td>
        </tr>
        <tr>
           <td>Nom Utilisateur:</td>
           <td><input type="text" name="codeUtil" value="${codeUtil}"/><font color="red">${errorCodeUtil}</font></td>
       </tr>
       <tr>
         <td>password:</td>
         <td><input type="password" name="motDPass" value="${motDPass}" onblur='document.formConnexion.submit()'/><font color="red">${errorMDP}</font></td>
        </tr>
       <tr>
         <td>Profils</td>
         <td>
            <select name="profilConnecte" onchange='document.formConnexion.submit()'>
                <option value="">------------------------------</option>
                <c:forEach items="${listProfil}" var="profils">
                   <option value="${profils.profil.codeProfil}">
                     <c:forEach items="${profils.profil.libelleProfil}" var="libelles">
                       <c:if test="${libelles.langue.codeLangue == utilisateur.langue.codeLangue}">
                             ${libelles.libelle}
                       </c:if>      
                     </c:forEach>
                   </option>
                </c:forEach>
            </select>
         </td>
       </tr>
     </tbody>
   </table>
   </fieldset>
 </form>
 </div>
 </div>
 </body>
</html>
