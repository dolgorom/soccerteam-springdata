<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Team</title>
    <link rel="stylesheet"
          type="text/css"
          href="/resources/style.css" />
</head>
<body>
<a href="/?mylocale=en">English</a> | <a href="/?mylocale=es">Spanish</a>
<h1><spring:message code="welcome.message"/></h1>

<a href="/player/register" ><spring:message code="create.player"/></a> |
<a href="/trainer/register" ><spring:message code="create.trainer"/></a> <br/>

<h2><spring:message code="rest.interface"/></h2>
<table>
    <tr>
        <td><a href="<c:url value="/playerRest/register" /> "><spring:message code="rest.create.player"/></a> </td>
        <td>|</td>
        <td><a href="<c:url value="/trainerRest/register" /> "><spring:message code="rest.create.trainer"/></a> </td>
    </tr>
    <tr>
        <td><a href="<c:url value="/playerRest/all" /> "><spring:message code="rest.player.getall"/></td>
        <td>|</td>
        <td>  <a href="<c:url value="/trainerRest/all" /> "><spring:message code="rest.trainer.getall"/></a></td>
    </tr>
    <tr>
        <td>
        <form action="" method="get">
            <a id='playerLink' href="/playerRest/firstname_secondname"><spring:message code="rest.player.getbyfirstandsecondname"/></a>
              <textarea id="playerFirstname" rows="1" columns="4" name="val" onload="updatePlayerLink()" onChange="updatePlayerLink()">firstname</textarea>
            <textarea id="playerSecondname" rows="1" columns="4" name="val"  onload="updatePlayerLink()" onChange="updatePlayerLink()">secondname</textarea>
        </form>
        </td>
    <td>|</td>
    <td>
        <form action="" method="get">
            <a id='trainerLink' href="/trainerRest/firstname_secondname"><spring:message code="rest.trainer.getbyfirstandsecondname"/></a>
            <textarea id="trainerFirstname" rows="1" columns="4" name="val" onload="updateTrainerLink()" onChange="updateTrainerLink()">firstname</textarea>
            <textarea id="trainerSecondname" rows="1" columns="4" name="val"  onload="updateTrainerLink()" onChange="updateTrainerLink()">secondname</textarea>
        </form>
    </tr>
</table>
<script>

function updatePlayerLink()
{
firstNameValue = document.getElementById('playerFirstname').value;
    secondNameValue = document.getElementById('playerSecondname').value;
document.getElementById('playerLink').href = 'playerRest/'+firstNameValue + "_" + secondNameValue;
}

function updateTrainerLink()
{
    firstNameValue = document.getElementById('trainerFirstname').value;
    secondNameValue = document.getElementById('trainerSecondname').value;
    document.getElementById('trainerLink').href = 'trainerRest/'+firstNameValue + "_" + secondNameValue;
}
</script>
</body>
</html>
