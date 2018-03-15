<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing UserRole Items</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing UserRole Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No UserRole Items Found)<br />" rendered="#{userRole.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{userRole.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{userRole.pagingInfo.firstItem + 1}..#{userRole.pagingInfo.lastItem} of #{userRole.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{userRole.prev}" value="Previous #{userRole.pagingInfo.batchSize}" rendered="#{userRole.pagingInfo.firstItem >= userRole.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{userRole.next}" value="Next #{userRole.pagingInfo.batchSize}" rendered="#{userRole.pagingInfo.lastItem + userRole.pagingInfo.batchSize <= userRole.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{userRole.next}" value="Remaining #{userRole.pagingInfo.itemCount - userRole.pagingInfo.lastItem}"
                                   rendered="#{userRole.pagingInfo.lastItem < userRole.pagingInfo.itemCount && userRole.pagingInfo.lastItem + userRole.pagingInfo.batchSize > userRole.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{userRole.userRoleItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Role"/>
                            </f:facet>
                            <h:outputText value="#{item.userRolePK.role}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="User"/>
                            </f:facet>
                            <h:outputText value="#{item.user}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{userRole.detailSetup}">
                                <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userRole.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{userRole.editSetup}">
                                <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userRole.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{userRole.remove}">
                                <f:param name="jsfcrud.currentUserRole" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userRole.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{userRole.createSetup}" value="New UserRole"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
