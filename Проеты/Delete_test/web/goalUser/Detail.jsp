<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>GoalUser Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>GoalUser Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDGoaluser:"/>
                    <h:outputText value="#{goalUser.goalUser.IDGoaluser}" title="IDGoaluser" />
                    <h:outputText value="IDLevel:"/>
                    <h:panelGroup>
                        <h:outputText value="#{goalUser.goalUser.IDLevel}"/>
                        <h:panelGroup rendered="#{goalUser.goalUser.IDLevel != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{level.detailSetup}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDLevel][level.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{level.editSetup}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDLevel][level.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{level.destroy}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDLevel][level.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IDGoal:"/>
                    <h:panelGroup>
                        <h:outputText value="#{goalUser.goalUser.IDGoal}"/>
                        <h:panelGroup rendered="#{goalUser.goalUser.IDGoal != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{goal.detailSetup}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDGoal][goal.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{goal.editSetup}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDGoal][goal.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{goal.destroy}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDGoal][goal.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IDClient:"/>
                    <h:panelGroup>
                        <h:outputText value="#{goalUser.goalUser.IDClient}"/>
                        <h:panelGroup rendered="#{goalUser.goalUser.IDClient != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{client.detailSetup}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDClient][client.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{client.editSetup}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDClient][client.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{client.destroy}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser.IDClient][client.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="goalUser"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.GoalUserController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{goalUser.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{goalUser.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][goalUser.goalUser][goalUser.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{goalUser.createSetup}" value="New GoalUser" />
                <br />
                <h:commandLink action="#{goalUser.listSetup}" value="Show All GoalUser Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
