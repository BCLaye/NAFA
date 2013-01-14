<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/theme2.css">
<link rel="stylesheet" type="text/css" href="css/${utilisateur.preference.couleur}.css">

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

      <div id="logo">
			<div id="logoImg">
				<img alt="Enterprise's Logo" src="images/logo_45.png" width="112" height="84"/>
			</div>
			<div id="logoDescription">
				<h3>NAFA MICROFINANCE 0.1</h3>Quam ob rem cave Catoni anteponas ne istum quidem ipsum, quem Apollo.
			</div>
		</div>
		<div id="userBox">
			<div id="userPicture">
				<img alt="User Picture" src="images/me.jpg" />
			</div>
			<div id="uiao">
				<div id="userInfos">
					<span class="userName">${utilisateur.nomUtil}</span>
					<span class="userFunction">Administrateur</span>
				</div>
				<div id="userOperations">
			        <a class="btn nafa-tools btn-small" href="Preferences.html">${utilisateur.langue.preferences}</a>
			        <a class="btn nafa-switch-2 btn-small" href="Deconnexion.html">${utilisateur.langue.deconnexion}</a>
		       </div>
			</div>
		</div>