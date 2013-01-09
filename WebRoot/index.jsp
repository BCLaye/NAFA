<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Téléchargement</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style1H.css">
	<link href='css/fonts.css' rel='stylesheet' type='text/css'>
<link href="css/bootstrap.css" rel="stylesheet" type='text/css'>
<link href="css/icomoon.css" rel="stylesheet" type='text/css'>
<link href='css/boutons.css' rel='stylesheet' type='text/css'>  
<link href="css/customcolorpicker.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="javascript/jquery-1.8.0.js"></script> 
<script src="javascript/jquery.ui.core.js" type="text/javascript"></script>
<script src="javascript/jquery.ui.widget.js" type="text/javascript"></script>
<script src="javascript/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/bootstrap.js"></script>
<script src="javascript/customColorPicker.js" type="text/javascript"></script> 
  </head>
  
  <body onload="formIndex.submit();">
    Téléchargement, veuillez patienter ...
    <form action="acceuil.html" name="formIndex" method="post">
    </form>
  </body>
</html>
