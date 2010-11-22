<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript"
    src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="script.js"></script>

</head>
<body onload="initialize()">

<b>${headertop}</b>

Welcome, ${UserName}
<br />${mysqlresponse}
	
<div id="page-wrap">

  	
<div id="FBLogo" onClick="populate()"></div>
<div id="FALogo" onClick="doFunction()"></div>
<div id="map_canvas"></div>
<div id="scrollDown"></div>
<div id="addresBook">

<c:forEach var="contact" items="${contacts}">
<script type="text/javascript" language="JavaScript">
addElement('${contact}');
</script>
</c:forEach>


<form action="/main" method=POST>
<h1>Add a contact</h1>
<p><label for="name">first</label> <input type="text" name="name_first" id="first" /></p>
<p><label for="name">last</label> <input type="text" name="name_last" id="last" /></p>
<p><label for="e-mail">e-mail</label> <input type="text" name="email" id="email" /><br /></p>
<p><label for="e-mail">address</label> <input type="text" name="address" id="address1" /><br /></p>
<p><label for="name">city</label> <input type="text" name="city" id="city" /></p>
<p><label for="name">state</label> <input type="text" name="state" id="state" /></p>
<p><label for="name">zip</label> <input type="text" name="zip" id="zip" /></p>
<p class="submit"><input type="submit" name="submit" value="Submit" /></p>
</form> 


</div>
</div>
${logoutlink}
</body>
</html>