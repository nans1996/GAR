<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Level</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Level</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDLevel:"/>
                    <h:outputText value="#{level.level.IDLevel}" title="IDLevel" />
                    <h:outputText value="Date (MM/dd/yyyy):"/>
                    <h:inputText id="date" value="#{level.level.date}" title="Date" required="true" requiredMessage="The date field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="Leveldate:"/>
                    <h:inputText id="leveldate" value="#{level.level.leveldate}" title="Leveldate" />
                    <h:outputText value="GoalUserCollection:"/>
                    <h:selectManyListbox id="goalUserCollection" value="#{level.level.jsfcrud_transform[jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.arrayToList].goalUserCollection}" title="GoalUserCollection" size="6" converter="#{goalUser.converter}" >
                        <f:selectItems value="#{goalUser.goalUserItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{level.edit}" value="Save">
                    <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][level.level][level.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{level.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][level.level][level.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{level.listSetup}" value="Show All Level Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
