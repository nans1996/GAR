<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing UserRole</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing UserRole</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Role:"/>
                    <h:outputText value="#{userRole.userRole.userRolePK.role}" title="Role" />
                    <h:outputText value="User:"/>
                    <h:outputText value=" #{userRole.userRole.user}" title="User" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{userRole.edit}" value="Save">
                    <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole][userRole.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{userRole.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole][userRole.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{userRole.listSetup}" value="Show All UserRole Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
