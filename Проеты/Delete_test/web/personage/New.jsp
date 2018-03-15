<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Personage</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Personage</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{personage.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{personage.personage.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Price:"/>
                    <h:inputText id="price" value="#{personage.personage.price}" title="Price" required="true" requiredMessage="The price field is required." />
                    <h:outputText value="GoalCollection:"/>
                    <h:selectManyListbox id="goalCollection" value="#{personage.personage.jsfcrud_transform[jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.arrayToList].goalCollection}" title="GoalCollection" size="6" converter="#{goal.converter}" >
                        <f:selectItems value="#{goal.goalItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{personage.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{personage.listSetup}" value="Show All Personage Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
