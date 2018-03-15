<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Goal Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Goal Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDGoal:"/>
                    <h:outputText value="#{goal.goal.IDGoal}" title="IDGoal" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{goal.goal.name}" title="Name" />
                    <h:outputText value="Directory:"/>
                    <h:outputText value="#{goal.goal.directory}" title="Directory" />
                    <h:outputText value="IDPersonage:"/>
                    <h:panelGroup>
                        <h:outputText value="#{goal.goal.IDPersonage}"/>
                        <h:panelGroup rendered="#{goal.goal.IDPersonage != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{personage.detailSetup}">
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal][goal.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal.IDPersonage][personage.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goal"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{personage.editSetup}">
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal][goal.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal.IDPersonage][personage.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goal"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{personage.destroy}">
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal][goal.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal.IDPersonage][personage.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goal"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>

                    <h:outputText value="GoalUserCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty goal.goal.goalUserCollection}" value="(No Items)"/>
                        <h:dataTable value="#{goal.goal.goalUserCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty goal.goal.goalUserCollection}">
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
                                    <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal][goal.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="goal" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{goalUser.editSetup}">
                                    <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal][goal.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="goal" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{goalUser.destroy}">
                                    <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal][goal.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="goal" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{goal.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal][goal.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{goal.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goal.goal][goal.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{goal.createSetup}" value="New Goal" />
                <br />
                <h:commandLink action="#{goal.listSetup}" value="Show All Goal Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
