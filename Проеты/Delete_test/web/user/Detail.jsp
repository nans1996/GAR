<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>User Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>User Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDUser:"/>
                    <h:outputText value="#{user.user.IDUser}" title="IDUser" />
                    <h:outputText value="Login:"/>
                    <h:outputText value="#{user.user.login}" title="Login" />
                    <h:outputText value="Pass:"/>
                    <h:outputText value="#{user.user.pass}" title="Pass" />
                    <h:outputText value="Surname:"/>
                    <h:outputText value="#{user.user.surname}" title="Surname" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{user.user.name}" title="Name" />
                    <h:outputText value="Phone:"/>
                    <h:outputText value="#{user.user.phone}" title="Phone" />
                    <h:outputText value="Email:"/>
                    <h:outputText value="#{user.user.email}" title="Email" />

                    <h:outputText value="UserRoleCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty user.user.userRoleCollection}" value="(No Items)"/>
                        <h:dataTable value="#{user.user.userRoleCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty user.user.userRoleCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Role"/>
                                </f:facet>
                                <h:outputText value="#{item.userRolePK.role}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User"/>
                                </f:facet>
                                <h:outputText value="#{item.user}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{userRole.detailSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userRole.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{userRole.editSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userRole.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{userRole.destroy}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userRole.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="ClientCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty user.user.clientCollection}" value="(No Items)"/>
                        <h:dataTable value="#{user.user.clientCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty user.user.clientCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDClient"/>
                                </f:facet>
                                <h:outputText value="#{item.IDClient}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="DateBirth"/>
                                </f:facet>
                                <h:outputText value="#{item.dateBirth}">
                                    <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Money"/>
                                </f:facet>
                                <h:outputText value="#{item.money}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Interests"/>
                                </f:facet>
                                <h:outputText value="#{item.interests}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDUser"/>
                                </f:facet>
                                <h:outputText value="#{item.IDUser}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{client.detailSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][client.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{client.editSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][client.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{client.destroy}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][client.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="MessageCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty user.user.messageCollection}" value="(No Items)"/>
                        <h:dataTable value="#{user.user.messageCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty user.user.messageCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDMessage"/>
                                </f:facet>
                                <h:outputText value="#{item.IDMessage}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Subject"/>
                                </f:facet>
                                <h:outputText value="#{item.subject}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Date"/>
                                </f:facet>
                                <h:outputText value="#{item.date}">
                                    <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Content"/>
                                </f:facet>
                                <h:outputText value="#{item.content}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDUser"/>
                                </f:facet>
                                <h:outputText value="#{item.IDUser}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDTopic"/>
                                </f:facet>
                                <h:outputText value="#{item.IDTopic}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{message.detailSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{message.editSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{message.destroy}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.UserController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{user.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{user.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{user.createSetup}" value="New User" />
                <br />
                <h:commandLink action="#{user.listSetup}" value="Show All User Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
