<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New User</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New User</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{user.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Login:"/>
                    <h:inputText id="login" value="#{user.user.login}" title="Login" required="true" requiredMessage="The login field is required." />
                    <h:outputText value="Pass:"/>
                    <h:inputText id="pass" value="#{user.user.pass}" title="Pass" required="true" requiredMessage="The pass field is required." />
                    <h:outputText value="Surname:"/>
                    <h:inputText id="surname" value="#{user.user.surname}" title="Surname" required="true" requiredMessage="The surname field is required." />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{user.user.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Phone:"/>
                    <h:inputText id="phone" value="#{user.user.phone}" title="Phone" required="true" requiredMessage="The phone field is required." />
                    <h:outputText value="Email:"/>
                    <h:inputText id="email" value="#{user.user.email}" title="Email" />
                    <h:outputText value="UserRoleCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][user.user.userRoleCollection == null ? jsfcrud_null : user.user.userRoleCollection].jsfcrud_invoke}" title="UserRoleCollection" />
                    <h:outputText value="ClientCollection:"/>
                    <h:selectManyListbox id="clientCollection" value="#{user.user.jsfcrud_transform[jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.arrayToList].clientCollection}" title="ClientCollection" size="6" converter="#{client.converter}" >
                        <f:selectItems value="#{client.clientItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="MessageCollection:"/>
                    <h:selectManyListbox id="messageCollection" value="#{user.user.jsfcrud_transform[jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method.arrayToList].messageCollection}" title="MessageCollection" size="6" converter="#{message.converter}" >
                        <f:selectItems value="#{message.messageItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{user.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{user.listSetup}" value="Show All User Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
