<!DOCTYPE html>
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
	
<div id="page-wrap">

  	
<div id="FBLogo" onClick="populate()"></div>
<div id="FALogo" onClick="doFunction()"></div>
<div id="map_canvas"></div>
<div id="scrollDown"></div>
<div id="addresBook">
<form action="/main">
<h1>Add a contact</h1>
<p><label for="name">first</label> <input type="text" id="first" /></p>
<p><label for="name">last</label> <input type="text" id="last" /></p>
<p><label for="e-mail">e-mail</label> <input type="text" id="e-mail" /><br /></p>
<p><label for="e-mail">address</label> <input type="text" id="address1" /><br /></p>
<p><label for="name">city</label> <input type="text" id="city" /></p>
<p><label for="name">sate</label> <input type="text" id="state" /></p>
<p><label for="name">zip</label> <input type="text" id="zip" /></p>
<p class="submit"><input type="submit" value="Submit" /></p>
</form> 


</div>
</div>
${logoutlink}
</body>
</html>