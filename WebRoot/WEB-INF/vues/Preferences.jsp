<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>

	<base href="<%=basePath%>">
	<title>${utilisateur.langue.labelPreferences}</title>
	<script type="text/javascript">
		function OnCustomColorChanged(selectedColor, selectedColorTitle, colorPickerIndex) {
			document.getElementById("couleur").value=selectedColorTitle.toLowerCase();
		};
    </script>

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
	<form class="form-horizontal" action="Preferences.html" method="post" name="formPreferences" id="preferences">
		<fieldset><legend>${utilisateur.langue.preferences}</legend></fieldset>
       <div class="container-fluid">
	       <div class="row-fluid">
	       		<div class="span2">
	       			<label>${utilisateur.langue.modelAffichageMenu}</label>
			       	<label class="radio">
			       		<input type="radio" name="theme" id="theme1" value="theme1" checked>${utilisateur.langue.horizontal}       
			       	</label>
			       	<label class="radio">
			       		<input type="radio" name="theme" id="theme2" value="theme2">${utilisateur.langue.vertical}
			       	</label>
	       		</div>
	       		<div class="span5">
	       			<label>${utilisateur.langue.context}</label>  
	       			<span class="colorpicker">
			          	<span class="colorbox">
			          		<b class="selected" style="background:rgb(73, 175, 205);" title="Bleu"></b>
			          		<b style="background:rgb(91, 183, 91)" title="Vert"></b>
			          		<b style="background-color:rgb(218, 79, 73);" title="Rouge"></b>
			          		<b style="background-color:rgb(54, 54, 54);" title="Noir"></b>
			          	</span>
			          	<span class="bgbox"></span>
			          	<span class="hexbox"></span>
		       		</span>
	       		</div>
	       </div>
       </div>
       <fieldset><legend>Identit&eacute;</legend></fieldset>
       <div class="control-group">
       		<label class="control-label">Nom &agrave; afficher</label>
       		<div class="controls">
       			<input type="text" value="Papa Cheikh Cisse" />
       		</div>
       </div>
       <div class="control-group">
       		<label class="control-label">Role</label>
       		<div class="controls">
       			<input type="text" value="Administrateur" />
       		</div>
       </div>
       <div class="control-group">
       		<label class="control-label">Image de profil</label>
       		    <div class="btn-group">
				    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
				    Action
				    <span class="caret"></span>
				    </a>
				    <ul class="dropdown-menu">
				    <!-- dropdown menu links -->
				    </ul>
			    </div>
       </div>
       

       <!-- <a class="btn" onclick="document.getElementsByName('formPreferences').submit();">Enregistrer les modifications</a> -->
       <input type="button" value="${utilisateur.langue.valider}" onclick='envoyerAction("enregister")'/>
       <input type="hidden" name="couleur" id="couleur" value="" />     
       <input type="hidden" name="action"/>

     </form>
 <!-- ---------------------------------Fin contenu de la Page---------------------------------------------------- -->
				</div>
			</div>
		<c:if test='${utilisateur.preference.theme == "theme1"}'>
		</div>
		</c:if>
	
	</div>
	
	<div id="footer"></div>
  </body>
</html>
