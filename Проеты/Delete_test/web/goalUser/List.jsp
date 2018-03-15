<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing GoalUser Items</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing GoalUser Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No GoalUser Items Found)<br />" rendered="#{goalUser.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{goalUser.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{goalUser.pagingInfo.firstItem + 1}..#{goalUser.pagingInfo.lastItem} of #{goalUser.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{goalUser.prev}" value="Previous #{goalUser.pagingInfo.batchSize}" rendered="#{goalUser.pagingInfo.firstItem >= goalUser.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{goalUser.next}" value="Next #{goalUser.pagingInfo.batchSize}" rendered="#{goalUser.pagingInfo.lastItem + goalUser.pagingInfo.batchSize <= goalUser.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{goalUser.next}" value="Remaining #{goalUser.pagingInfo.itemCount - goalUser.pagingInfo.lastItem}"
                                   rendered="#{goalUser.pagingInfo.lastItem < goalUser.pagingInfo.itemCount && goalUser.pagingInfo.lastItem + goalUser.pagingInfo.batchSize > goalUser.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{goalUser.goalUserItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{goalUser.editSetup}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{goalUser.remove}">
                                <f:param name="jsfcrud.currentGoalUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goalUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{goalUser.createSetup}" value="New GoalUser"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
