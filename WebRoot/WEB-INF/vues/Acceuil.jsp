<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>${utilisateur.langue.acceuil}</title>
	
	
	
</head>

<body>

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
		   		<div id="notifBar">
					<a href="#" id="leave" class="nafa-switch-2 nafa-larger"></a>
					<a href="#" id="chat" class="nafa-chat nafa-larger"><span class="flat">23</span></a>
					<a href="#" id="inbox" class="nafa-envelope nafa-larger"><span class="flat">7</span></a>
					<div id="search"><i class="nafa-search nafa-larger"></i><input type="text" placeholder="Rechercher" /> </div>
				</div>
	 	</c:if>
				<div id="content">
					<div id="contentTitle"><i class="nafa-home"></i>Tableau de bord</div>
					<div id="contentDescription">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a venenatis.</div>
					<div id="contentBox">contentBox</div>
				</div>
		<c:if test='${utilisateur.preference.theme == "theme1"}'>
			</div>
		</c:if>
	
	</div>
	
	<div id="footer"></div>
</body>
</html>
