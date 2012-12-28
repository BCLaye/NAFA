<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Création des Administrateurs</title>
   
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <link rel="stylesheet" href="css/jquery.ui.all.css" />
    	
	<script src="javascript/jquery-1.8.0.js" type="text/javascript"></script>
	<script src="javascript/jquery.ui.core.js" type="text/javascript"></script>
	<script src="javascript/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="javascript/jquery.ui.datepicker.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="css/demos.css" />
	
	<script type="text/javascript">   
   $(function() {
		$( "#datepicker" ).datepicker();
	});
	
	 $(function() {
		$( "#datepicker2" ).datepicker();
	});   

   function enregistrerAdmin(){
    with(document.formAdminUser){
					action.value="enregistrerAdmin";
					submit();
					}   
   } 
</script>

  </head>
  
  <body>
  
   <div id="header">
	  <jsp:include page="/WEB-INF/vues/Header1.jsp"></jsp:include>
  </div>
  <div id="page">
    
    <div id="content" align="center">
    <form action="acceuil.html" method="post" name="formAdminUser">
       <fieldset>
          <legend>${langue.labelAdminUser}</legend>
          <table>
            <tbody>   
                  
               <c:if test="${nbAdmin == null}"> 
                  <tr>
                   <td>${langue.nbrAdminUser}</td>
                    <td>
                      <select name="nbAdminUser">
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">8</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                      </select>    
                   </td>
                </tr>  
            </c:if>
                  
              <tr>
                   <td>${langue.codeUser}</td>
                   <td><input type="text" name="codeUser"/><font color="red">${errorCode}</font></td>
              </tr>
              <tr>
                <td>${langue.nom}</td>
                <td><input type="text" name="nom"/><font color="red">${errorNom}</font></td>
              </tr>
              <tr>
                <td>${langue.langue}</td>
                <td>
                   <select name="langue">
                        <option></option>
                        <c:forEach items="${listLangue}" var="langue">
                          <option value="${langue.codeLangue}">${langue.nomLangue}</option>
                        </c:forEach>
                    </select>
                </td>
              </tr>
              <tr>
                <td>${langue.motPass}</td>
                <td><input type="text" name="motPass"/><font color="red">${errorMDP}</font></td>
              </tr>
              <tr>
                <td>${langue.dateDebValid}</td>
                <td><input type="text" name="dateDebValid" id="datepicker"/><font color="red">${errorDateDeb}</font></td>
              </tr>
              <tr>
                <td>${langue.dateFinValid}</td>
                <td><input type="text" name="dateFinValid" id="datepicker2"/><font color="red">${errorDateFin}</font></td>
              </tr> 
               <tr>
                  <td align="center" colspan="2">
                    <input type="button" value="${langue.valider}" onclick='enregistrerAdmin()'/>
                    <input type="button" value="${langue.annuler}"/>
                  </td>
               </tr>    
              
            </tbody>
          </table>
       </fieldset>
       <input type="hidden" name="action"/>
    </form>
   </div>
   </div>
  </body>
</html>
