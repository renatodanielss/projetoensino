<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:p="http://primefaces.org/ui">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>

<h:form>
    <h:panelGrid columns="2">
        <h:outputText value="Username" />
        <h:inputText value="#{userForm.username}" />

        <h:outputText value="Password" />
        <h:inputSecret value="#{userForm.password}" />

        <h:panelGroup />
        <h:commandButton value="login" action="#{userForm.login}" />
    </h:panelGrid>
</h:form>

</body>
</html>