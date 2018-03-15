<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Level Items</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Level Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Level Items Found)<br />" rendered="#{level.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{level.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{level.pagingInfo.firstItem + 1}..#{level.pagingInfo.lastItem} of #{level.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{level.prev}" value="Previous #{level.pagingInfo.batchSize}" rendered="#{level.pagingInfo.firstItem >= level.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{level.next}" value="Next #{level.pagingInfo.batchSize}" rendered="#{level.pagingInfo.lastItem + level.pagingInfo.batchSize <= level.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{level.next}" value="Remaining #{level.pagingInfo.itemCount - level.pagingInfo.lastItem}"
                                   rendered="#{level.pagingInfo.lastItem < level.pagingInfo.itemCount && level.pagingInfo.lastItem + level.pagingInfo.batchSize > level.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{level.levelItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDLevel"/>
                            </f:facet>
                            <h:outputText value="#{item.IDLevel}"/>
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
                                <h:outputText value="Leveldate"/>
                            </f:facet>
                            <h:outputText value="#{item.leveldate}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{level.detailSetup}">
                                <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][level.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{level.editSetup}">
                                <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][level.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{level.remove}">
                                <f:param name="jsfcrud.currentLevel" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][level.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{level.createSetup}" value="New Level"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
