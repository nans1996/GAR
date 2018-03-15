<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Topic Items</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Topic Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Topic Items Found)<br />" rendered="#{topic.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{topic.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{topic.pagingInfo.firstItem + 1}..#{topic.pagingInfo.lastItem} of #{topic.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{topic.prev}" value="Previous #{topic.pagingInfo.batchSize}" rendered="#{topic.pagingInfo.firstItem >= topic.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{topic.next}" value="Next #{topic.pagingInfo.batchSize}" rendered="#{topic.pagingInfo.lastItem + topic.pagingInfo.batchSize <= topic.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{topic.next}" value="Remaining #{topic.pagingInfo.itemCount - topic.pagingInfo.lastItem}"
                                   rendered="#{topic.pagingInfo.lastItem < topic.pagingInfo.itemCount && topic.pagingInfo.lastItem + topic.pagingInfo.batchSize > topic.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{topic.topicItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDTopic"/>
                            </f:facet>
                            <h:outputText value="#{item.IDTopic}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value="#{item.name}"/>
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
                                <h:outputText value="IDUser"/>
                            </f:facet>
                            <h:outputText value="#{item.IDUser}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{topic.detailSetup}">
                                <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][topic.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{topic.editSetup}">
                                <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][topic.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{topic.remove}">
                                <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][topic.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{topic.createSetup}" value="New Topic"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
