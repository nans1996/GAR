<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Message</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Message</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{message.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Subject:"/>
                    <h:inputText id="subject" value="#{message.message.subject}" title="Subject" required="true" requiredMessage="The subject field is required." />
                    <h:outputText value="Date (MM/dd/yyyy):"/>
                    <h:inputText id="date" value="#{message.message.date}" title="Date" required="true" requiredMessage="The date field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="Content:"/>
                    <h:inputTextarea rows="4" cols="30" id="content" value="#{message.message.content}" title="Content" />
                    <h:outputText value="IDUser:"/>
                    <h:selectOneMenu id="IDUser" value="#{message.message.IDUser}" title="IDUser" required="true" requiredMessage="The IDUser field is required." >
                        <f:selectItems value="#{user.userItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="IDTopic:"/>
                    <h:selectOneMenu id="IDTopic" value="#{message.message.IDTopic}" title="IDTopic" required="true" requiredMessage="The IDTopic field is required." >
                        <f:selectItems value="#{topic.topicItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{message.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{message.listSetup}" value="Show All Message Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
