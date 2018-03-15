<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Topic</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Topic</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDTopic:"/>
                    <h:outputText value="#{topic.topic.IDTopic}" title="IDTopic" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{topic.topic.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Date (MM/dd/yyyy):"/>
                    <h:inputText id="date" value="#{topic.topic.date}" title="Date" required="true" requiredMessage="The date field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="IDUser:"/>
                    <h:inputText id="IDUser" value="#{topic.topic.IDUser}" title="IDUser" required="true" requiredMessage="The IDUser field is required." />
                    <h:outputText value="MessageCollection:"/>
                    <h:selectManyListbox id="messageCollection" value="#{topic.topic.jsfcrud_transform[jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.arrayToList].messageCollection}" title="MessageCollection" size="6" converter="#{message.converter}" >
                        <f:selectItems value="#{message.messageItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{topic.edit}" value="Save">
                    <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][topic.topic][topic.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{topic.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][topic.topic][topic.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{topic.listSetup}" value="Show All Topic Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
