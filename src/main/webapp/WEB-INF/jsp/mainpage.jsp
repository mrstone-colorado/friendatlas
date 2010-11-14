<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>Friend Atlas Main</title>
</head>
<body>
 
<h1>Friend Atlas Main Page</h1>
 
<b>${headertop}</b>

Welcome, ${UserName}
 
<br /><b>Contacts</b><br />
<table>
   <tr>
       <th>Name</th>
       <th>Email Address</th>
   </tr>
   <c:forEach var="contact" items="${contacts}">
       <tr>
           <td><c:out value="${contact.name}"/></td>
           <td><c:out value="${contact.emailAddress}"/></td>
       </tr>
   </c:forEach>
</table>
 

<br /><b>Add a new contact</b><br />
<form action="<c:url value="/main"/>" method="post">
   <label for="name">Name:</label>
   <input id="name" type="text" name="name"/><br/>
   <label for="phone_number">Phone Number:</label>
   <input id="phone_number" type="text" name="phoneNumber"/><br/>
   <label for="email_address">Email Address:</label>
   <input id="email_address" type="text" name="emailAddress"/><br/>
   <input type="submit"/>
</form>

${logoutlink}
 
</body>
</html>