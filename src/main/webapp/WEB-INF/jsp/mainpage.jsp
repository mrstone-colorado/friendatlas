
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" %>
<%@ page import="csci3308.friendatlas.*" %>
<%@ page import="java.util.*" %>
<c:set var="space" value=" "/>
<html>
<head>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript"
            src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript" src="script.js"></script>
    <script type="text/javascript">
        function popFriends() {
        <c:forEach var="friend" items="${userFriends}">
             <c:set var="friendAddress" value="${friend.address}${space}${friend.city}${space}${friend.state}"></c:set>
            pop('<c:out value="${friendAddress}"/>');
        </c:forEach>
        }
    </script>
</head>

<body onload="initialize();popFriends();">

Welcome, ${UserName}

<br />${mysqlresponse}

<div id="page-wrap">


<div id="FBLogo" onClick="showAddress()"></div>
<div id="FALogo" onClick="codeAddress()"></div>
<div id="map_canvas"></div>
<div id="scrollDown">
    <table>
        <tr><th>Contacts</th></tr>
        <c:forEach var="friend" items="${userFriends}">
            <c:set var="friendAddress" value="${friend.address}${space}${friend.city}${space}${friend.state}"></c:set>
            <tr><td onclick><a color=#FF0000 style="text-decoration:none" href="javascript:codeAddress('<c:out value="${friendAddress}"/>')"><c:out value="${friend.firstName}${space}${friend.lastName}"/></a></td></tr>
        </c:forEach>
    </table>
</div>
<div id="addresBook">

    <form action="<c:url value="/main"/>" method=POST>

        <p><label for="first">first name</label><input type="text" name="first" id="first" /></p>
        <p><label for="last">last</label> <input type="text" name="last" id="last" /></p>
        <p><label for="e-mail">e-mail</label> <input type="text" name="email" id="email" /><br /></p>
        <p><label for="address">address</label> <input type="text" name="address" id="address1" /><br /></p>
        <p><label for="city">city</label> <input type="text" name="city" id="city" /></p>
        <p><label for="state">state</label> <input type="text" name="state" id="state" /></p>
        <p><label for="zip">zip</label> <input type="text" name="zip" id="zip" /></p>
        <p class="submit"><input type="submit" name="submit" value="Submit" /></p>

    </form>

</div>
</div>


${LogoutLink}
</body>
</html>