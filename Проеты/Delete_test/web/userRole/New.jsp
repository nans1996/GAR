<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New UserRole</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New UserRole</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{userRole.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Role:"/>
                    <h:inputText id="role" value="#{userRole.userRole.userRolePK.role}" title="Role" required="true" requiredMessage="The role field is required." />
                    <h:outputText value="User:"/>
                    <h:selectOneMenu id="user" value="#{userRole.userRole.user}" title="User" required="true" requiredMessage="The user field is required." >
                        <f:selectItems value="#{user.userItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{userRole.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{userRole.listSetup}" value="Show All UserRole Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
