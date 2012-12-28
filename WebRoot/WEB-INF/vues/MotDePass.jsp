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
    
    <title>${utilisateur.langue.labelMDP}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function envoyerAction(code){
    with(document.formChangeMDP){
					action.value=code;
					submit();
					}
	}
function fermenFenetre(){
    this.window.close();
	}
</script>

  </head>
 <c:if test="${close != null }">
  <body onload='fermenFenetre()'>
 </c:if>  
 <c:if test="${close == null }">
  <body>
 </c:if>
     <form action="ChangerMotDPasse.html" method="post" name="formChangeMDP">
       <fieldset>
          <legend>${utilisateur.langue.labelMDP}</legend>
          <table>
            <tr>
               <td>${utilisateur.langue.motDPassActuel}</td>
               <td><input type="password" name="motDPActuel"/><font color="red">${errorMotDPActuel}</font></td>
            </tr>
            <tr>
               <td>${utilisateur.langue.nouvoMDP}</td>
               <td><input type="password" name="nouvoMDP"/><font color="red">${errorNouvoMDP}</font></td>
            </tr>
            <tr>
               <td>${utilisateur.langue.confirmNouvoMDP}</td>
               <td><input type="password" name="confirmMDP"/><font color="red">${errorConfirmMDP}</font></td>
            </tr>
            <tr>
               <td colspan="2" align="center">
                 <input type="button" value="${utilisateur.langue.valider}" onclick='envoyerAction("changerMDP")'/>
                 <input type="button" value="${utilisateur.langue.annuler}" onclick='envoyerAction("annulerChangerMDP")'/>
               </td>
            </tr>
          </table>
       </fieldset>
       <input type="hidden" name="action"/> 
     </form>
  </body>
</html>
