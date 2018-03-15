<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Goal Items</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Goal Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Goal Items Found)<br />" rendered="#{goal.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{goal.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{goal.pagingInfo.firstItem + 1}..#{goal.pagingInfo.lastItem} of #{goal.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{goal.prev}" value="Previous #{goal.pagingInfo.batchSize}" rendered="#{goal.pagingInfo.firstItem >= goal.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{goal.next}" value="Next #{goal.pagingInfo.batchSize}" rendered="#{goal.pagingInfo.lastItem + goal.pagingInfo.batchSize <= goal.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{goal.next}" value="Remaining #{goal.pagingInfo.itemCount - goal.pagingInfo.lastItem}"
                                   rendered="#{goal.pagingInfo.lastItem < goal.pagingInfo.itemCount && goal.pagingInfo.lastItem + goal.pagingInfo.batchSize > goal.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{goal.goalItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goal.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{goal.editSetup}">
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goal.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{goal.remove}">
                                <f:param name="jsfcrud.currentGoal" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][goal.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{goal.createSetup}" value="New Goal"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
