<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Level Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Level Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDLevel:"/>
                    <h:outputText value="#{level.level.IDLevel}" title="IDLevel" />
                    <h:outputText value="Date:"/>
                    <h:outputText value="#{level.level.date}" title="Date" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="Leveldate:"/>
                    <h:outputText value="#{level.level.leveldate}" title="Leveldate" />

                    <h:outputText value="GoalUserCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty level.level.goalUserCollection}" value="(No Items)"/>
                        <h:dataTable value="#{level.level.goalUserCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty level.level.goalUserCollection}">
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
                                    <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][level.level][level.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="level" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.LevelController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{goalUser.editSetup}">
                                    <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][level.level][level.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="level" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.LevelController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{goalUser.destroy}">
                                    <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][level.level][level.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="level" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.LevelController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{level.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][level.level][level.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{level.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][level.level][level.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{level.createSetup}" value="New Level" />
                <br />
                <h:commandLink action="#{level.listSetup}" value="Show All Level Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
