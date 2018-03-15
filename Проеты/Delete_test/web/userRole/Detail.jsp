<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>UserRole Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>UserRole Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Role:"/>
                    <h:outputText value="#{userRole.userRole.userRolePK.role}" title="Role" />
                    <h:outputText value="User:"/>
                    <h:panelGroup>
                        <h:outputText value="#{userRole.userRole.user}"/>
                        <h:panelGroup rendered="#{userRole.userRole.user != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{user.detailSetup}">
                                <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole][userRole.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userRole"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserRoleController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{user.editSetup}">
                                <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole][userRole.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userRole"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserRoleController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{user.destroy}">
                                <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole][userRole.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userRole"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserRoleController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{userRole.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole][userRole.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{userRole.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userRole.userRole][userRole.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{userRole.createSetup}" value="New UserRole" />
                <br />
                <h:commandLink action="#{userRole.listSetup}" value="Show All UserRole Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
