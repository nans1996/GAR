<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Client</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Client</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{client.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="DateBirth (MM/dd/yyyy):"/>
                    <h:inputText id="dateBirth" value="#{client.client.dateBirth}" title="DateBirth" required="true" requiredMessage="The dateBirth field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="Money:"/>
                    <h:inputText id="money" value="#{client.client.money}" title="Money" required="true" requiredMessage="The money field is required." />
                    <h:outputText value="Interests:"/>
                    <h:inputTextarea rows="4" cols="30" id="interests" value="#{client.client.interests}" title="Interests" />
                    <h:outputText value="GoalUserCollection:"/>
                    <h:selectManyListbox id="goalUserCollection" value="#{client.client.jsfcrud_transform[jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.arrayToList].goalUserCollection}" title="GoalUserCollection" size="6" converter="#{goalUser.converter}" >
                        <f:selectItems value="#{goalUser.goalUserItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="IDUser:"/>
                    <h:selectOneMenu id="IDUser" value="#{client.client.IDUser}" title="IDUser" required="true" requiredMessage="The IDUser field is required." >
                        <f:selectItems value="#{user.userItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{client.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{client.listSetup}" value="Show All Client Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
