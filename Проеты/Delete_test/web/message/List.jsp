<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Message Items</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Message Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Message Items Found)<br />" rendered="#{message.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{message.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{message.pagingInfo.firstItem + 1}..#{message.pagingInfo.lastItem} of #{message.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{message.prev}" value="Previous #{message.pagingInfo.batchSize}" rendered="#{message.pagingInfo.firstItem >= message.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{message.next}" value="Next #{message.pagingInfo.batchSize}" rendered="#{message.pagingInfo.lastItem + message.pagingInfo.batchSize <= message.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{message.next}" value="Remaining #{message.pagingInfo.itemCount - message.pagingInfo.lastItem}"
                                   rendered="#{message.pagingInfo.lastItem < message.pagingInfo.itemCount && message.pagingInfo.lastItem + message.pagingInfo.batchSize > message.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{message.messageItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{message.editSetup}">
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{message.remove}">
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{message.createSetup}" value="New Message"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
