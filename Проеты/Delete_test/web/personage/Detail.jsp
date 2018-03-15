<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Personage Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Personage Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDPersonage:"/>
                    <h:outputText value="#{personage.personage.IDPersonage}" title="IDPersonage" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{personage.personage.name}" title="Name" />
                    <h:outputText value="Price:"/>
                    <h:outputText value="#{personage.personage.price}" title="Price" />

                    <h:outputText value="GoalCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty personage.personage.goalCollection}" value="(No Items)"/>
                        <h:dataTable value="#{personage.personage.goalCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty personage.personage.goalCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDGoal"/>
                                </f:facet>
                                <h:outputText value="#{item.IDGoal}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value="#{item.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Directory"/>
                                </f:facet>
                                <h:outputText value="#{item.directory}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IDPersonage"/>
                                </f:facet>
                                <h:outputText value="#{item.IDPersonage}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{goal.detailSetup}">
                                    <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][personage.personage][personage.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goal.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="personage" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.PersonageController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{goal.editSetup}">
                                    <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][personage.personage][personage.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goal.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="personage" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.PersonageController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{goal.destroy}">
                                    <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][personage.personage][personage.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goal.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="personage" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.PersonageController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{personage.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][personage.personage][personage.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{personage.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][personage.personage][personage.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{personage.createSetup}" value="New Personage" />
                <br />
                <h:commandLink action="#{personage.listSetup}" value="Show All Personage Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
