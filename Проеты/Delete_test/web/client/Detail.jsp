<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Client Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Client Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDClient:"/>
                    <h:outputText value="#{client.client.IDClient}" title="IDClient" />
                    <h:outputText value="DateBirth:"/>
                    <h:outputText value="#{client.client.dateBirth}" title="DateBirth" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="Money:"/>
                    <h:outputText value="#{client.client.money}" title="Money" />
                    <h:outputText value="Interests:"/>
                    <h:outputText value="#{client.client.interests}" title="Interests" />
                    <h:outputText value="IDUser:"/>
                    <h:panelGroup>
                        <h:outputText value="#{client.client.IDUser}"/>
                        <h:panelGroup rendered="#{client.client.IDUser != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{user.detailSetup}">
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client][client.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client.IDUser][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="client"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.ClientController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{user.editSetup}">
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client][client.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client.IDUser][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="client"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.ClientController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{user.destroy}">
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client][client.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client.IDUser][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="client"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.ClientController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>

                    <h:outputText value="GoalUserCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty client.client.goalUserCollection}" value="(No Items)"/>
                        <h:dataTable value="#{client.client.goalUserCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty client.client.goalUserCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDGoaluser"/>
                                </f:facet>
                                <h:outputText value="#{item.IDGoaluser}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDLevel"/>
                                </f:facet>
                                <h:outputText value="#{item.IDLevel}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDGoal"/>
                                </f:facet>
                                <h:outputText value="#{item.IDGoal}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDClient"/>
                                </f:facet>
                                <h:outputText value="#{item.IDClient}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{goalUser.detailSetup}">
                                    <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client][client.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="client" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.ClientController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{goalUser.editSetup}">
                                    <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client][client.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="client" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.ClientController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{goalUser.destroy}">
                                    <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client][client.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="client" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.ClientController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{client.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client][client.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{client.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][client.client][client.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{client.createSetup}" value="New Client" />
                <br />
                <h:commandLink action="#{client.listSetup}" value="Show All Client Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
