<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New GoalUser</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New GoalUser</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{goalUser.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="IDLevel:"/>
                    <h:selectOneMenu id="IDLevel" value="#{goalUser.goalUser.IDLevel}" title="IDLevel" required="true" requiredMessage="The IDLevel field is required." >
                        <f:selectItems value="#{level.levelItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="IDGoal:"/>
                    <h:selectOneMenu id="IDGoal" value="#{goalUser.goalUser.IDGoal}" title="IDGoal" required="true" requiredMessage="The IDGoal field is required." >
                        <f:selectItems value="#{goal.goalItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="IDClient:"/>
                    <h:selectOneMenu id="IDClient" value="#{goalUser.goalUser.IDClient}" title="IDClient" required="true" requiredMessage="The IDClient field is required." >
                        <f:selectItems value="#{client.clientItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{goalUser.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{goalUser.listSetup}" value="Show All GoalUser Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
