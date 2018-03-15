<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Goal</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Goal</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{goal.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{goal.goal.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Directory:"/>
                    <h:inputText id="directory" value="#{goal.goal.directory}" title="Directory" required="true" requiredMessage="The directory field is required." />
                    <h:outputText value="IDPersonage:"/>
                    <h:selectOneMenu id="IDPersonage" value="#{goal.goal.IDPersonage}" title="IDPersonage" required="true" requiredMessage="The IDPersonage field is required." >
                        <f:selectItems value="#{personage.personageItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="GoalUserCollection:"/>
                    <h:selectManyListbox id="goalUserCollection" value="#{goal.goal.jsfcrud_transform[jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.arrayToList].goalUserCollection}" title="GoalUserCollection" size="6" converter="#{goalUser.converter}" >
                        <f:selectItems value="#{goalUser.goalUserItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{goal.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{goal.listSetup}" value="Show All Goal Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
