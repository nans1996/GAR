<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Client Items</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Client Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Client Items Found)<br />" rendered="#{client.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{client.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{client.pagingInfo.firstItem + 1}..#{client.pagingInfo.lastItem} of #{client.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{client.prev}" value="Previous #{client.pagingInfo.batchSize}" rendered="#{client.pagingInfo.firstItem >= client.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{client.next}" value="Next #{client.pagingInfo.batchSize}" rendered="#{client.pagingInfo.lastItem + client.pagingInfo.batchSize <= client.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{client.next}" value="Remaining #{client.pagingInfo.itemCount - client.pagingInfo.lastItem}"
                                   rendered="#{client.pagingInfo.lastItem < client.pagingInfo.itemCount && client.pagingInfo.lastItem + client.pagingInfo.batchSize > client.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{client.clientItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][client.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{client.editSetup}">
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][client.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{client.remove}">
                                <f:param name="jsfcrud.currentClient" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][client.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{client.createSetup}" value="New Client"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
