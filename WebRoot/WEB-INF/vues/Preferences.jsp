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
    
    <title>${utilisateur.langue.labelPreferences}</title>
    
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
    with(document.formPreferences){
					action.value=code;
					submit();
					}
	}
	
	function fermerFenetre(){
    this.window.close();
	}
</script>

  </head>
  
  
   <c:if test="${close != null }">
     <body onload='fermerFenetre()'>
  </c:if>  
  <c:if test="${close == null }">
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
				<div id="contentTitle"><i class="nafa-home"></i>${utilisateur.langue.labelPreferences}</div>
				<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
				<div id="contentBox">
<!-- ---------------------------------début contenu de la Page-------------------------------------------------- -->
	<form action="Preferences.html" method="post" name="formPreferences" id="preferences">
       <fieldset>
          <legend>${utilisateur.langue.labelPreferences}</legend>
          <table>
            <tr>
              <td>${utilisateur.langue.modelAffichageMenu}</td>
              <td></td>
             <tr>
                <td></td>
                <td><input type="radio" name="theme" value="theme1"/>Theme1<br/></td>
             </tr>
             <tr>
                <td></td>   
                <td><input type="radio" name="theme" value="theme2"/>Theme2<br/></td>
            </tr>
            
            <tr>
              <td>Couleur</td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td><input type="radio" name="couleur" value="bleu"/><span style="background-color: blue; padding: 0px 8px;"></span></td>
            </tr>
            <tr>
              <td></td>
              <td><input type="radio" name="couleur" value="rouge"/><span style="background-color: red; padding: 0px 8px;"></span></td>
            </tr>
            <tr>
              <td></td>
              <td><input type="radio" name="couleur" value="verte"/><span style="background-color: green; padding: 0px 8px;"></span></td>
            </tr>
            <tr>
              <td></td>
              <td><input type="radio" name="couleur" value="noir"/><span style="background-color: black; padding: 0px 8px;"></span></td>
            </tr>
            
            <tr>
              <td align="center" colspan="2">
                <jsp:include page="/WEB-INF/vues/Boutons.jsp"></jsp:include>
              </td>
            </tr>
          </table>
       </fieldset>
       <input type="hidden" name="action"/>
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
