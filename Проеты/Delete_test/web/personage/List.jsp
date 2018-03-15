<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Personage Items</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Personage Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Personage Items Found)<br />" rendered="#{personage.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{personage.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{personage.pagingInfo.firstItem + 1}..#{personage.pagingInfo.lastItem} of #{personage.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{personage.prev}" value="Previous #{personage.pagingInfo.batchSize}" rendered="#{personage.pagingInfo.firstItem >= personage.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{personage.next}" value="Next #{personage.pagingInfo.batchSize}" rendered="#{personage.pagingInfo.lastItem + personage.pagingInfo.batchSize <= personage.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{personage.next}" value="Remaining #{personage.pagingInfo.itemCount - personage.pagingInfo.lastItem}"
                                   rendered="#{personage.pagingInfo.lastItem < personage.pagingInfo.itemCount && personage.pagingInfo.lastItem + personage.pagingInfo.batchSize > personage.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{personage.personageItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDPersonage"/>
                            </f:facet>
                            <h:outputText value="#{item.IDPersonage}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value="#{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Price"/>
                            </f:facet>
                            <h:outputText value="#{item.price}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{personage.detailSetup}">
                                <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][personage.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{personage.editSetup}">
                                <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][personage.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{personage.remove}">
                                <f:param name="jsfcrud.currentPersonage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][personage.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{personage.createSetup}" value="New Personage"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
