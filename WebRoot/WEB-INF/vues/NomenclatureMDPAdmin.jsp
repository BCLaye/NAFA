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
    
    <title>Démarrage</title>
    <script type="text/javascript">   
   function selectionLangue(){
    with(document.formNomMDP){
					action.value="selectionLangue";
					submit();
					}   
   }
   function enregistrerNomenclature(){
    with(document.formNomMDP){
					action.value="enregistrerNomenclature";
					submit();
					}   
   } 
</script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  
  <div id="header">
	  <jsp:include page="/WEB-INF/vues/Header1.jsp"></jsp:include>
  </div>
  <div id="page">
    
    <div id="content">
      <div id="contentBox" align="center">
          <form action="acceuil.html" method="post" name="formNomMDP">
      <fieldset>
          <legend>${langue.labelNomenclatureMDP}</legend>
          <table>
            <tbody>
            
               <c:if test="${langAdmin == null }">  
                  <tr>
                    <td>${langue.langue}</td>
                    <td>
                       <select name="langueDemar" onchange='selectionLangue()'>
                        <option></option>
                        <c:forEach items="${listLangue}" var="langue">
                          <option value="${langue.codeLangue}">${langue.nomLangue}</option>
                        </c:forEach>
                       </select>
                    </td>
                 </tr>
              </c:if>
              
              <c:if test="${creation != null}">
                <tr>
                  <td>${langue.longMinMDP}</td>
                  <td><input type="text" name="longMinMDP"/><font color="red">${errorlongMinMDP} ${errorFormat1}</font></td>    
                </tr>
                <tr>
                  <td>${langue.nbMinMinusc}</td>
                  <td><input type="text" name="nbMinMinusc"/><font color="red">${errorMinMinus} ${errorFormat2}</font></td>    
                </tr>
                <tr>
                  <td>${langue.nbMinMajusc}</td>
                  <td><input type="text" name="nbMinMajusc"/><font color="red">${errorMinMaj} ${errorFormat3}</font></td>    
                </tr>
                <tr>
                  <td>${langue.nbMinChiff}</td>
                  <td><input type="text" name="nbMinChiff"/><font color="red">${errorMinChiff} ${errorFormat4}</font></td>    
                </tr>
                <tr>
                  <td>${langue.nbMaxRepCar}</td>
                  <td><input type="text" name="nbMaxRepCar"/><font color="red">${errorMaxrep} ${errorFormat5}</font></td>    
                </tr>
                <tr>
                  <td>${langue.nbMincarSpec}</td>
                  <td><input type="text" name="nbMinCarSpec"/><font color="red">${errorMinCar} ${errorFormat6}</font></td>    
                </tr>
                <tr>
                  <td>${langue.mDPdefaut}</td>
                  <td><input type="text" name="mDPdefaut"/><font color="red">${errorMDP}</font></td>    
                </tr>
                <tr>
                  <td align="center" colspan="2">
                    <input type="button" value="${langue.valider}" onclick='enregistrerNomenclature()'/>
                    <input type="button" value="${langue.annuler}"/>
                  </td>
               </tr>
             </c:if> 
                
            </tbody>
          </table>
      </fieldset>
      <input type="hidden" name="action"/>
    </form>      
      </div>
    </div>
   
   </div>
  </body>
</html>
