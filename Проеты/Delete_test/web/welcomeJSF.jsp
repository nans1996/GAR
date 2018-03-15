<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
<link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h1><h:outputText value="JavaServer Faces"/></h1>
                <h:form>
                    <h:commandLink action="#{userRole.listSetup}" value="Show All UserRole Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{user.listSetup}" value="Show All User Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{topic.listSetup}" value="Show All Topic Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{personage.listSetup}" value="Show All Personage Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{message.listSetup}" value="Show All Message Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{level.listSetup}" value="Show All Level Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{goalUser.listSetup}" value="Show All GoalUser Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{goal.listSetup}" value="Show All Goal Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{client.listSetup}" value="Show All Client Items"/>
                </h:form>

        </body>
    </html>
</f:view>
