<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Topic Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Topic Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDTopic:"/>
                    <h:outputText value="#{topic.topic.IDTopic}" title="IDTopic" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{topic.topic.name}" title="Name" />
                    <h:outputText value="Date:"/>
                    <h:outputText value="#{topic.topic.date}" title="Date" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="IDUser:"/>
                    <h:outputText value="#{topic.topic.IDUser}" title="IDUser" />

                    <h:outputText value="MessageCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty topic.topic.messageCollection}" value="(No Items)"/>
                        <h:dataTable value="#{topic.topic.messageCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty topic.topic.messageCollection}">
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
                                    <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][topic.topic][topic.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="topic" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.TopicController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{message.editSetup}">
                                    <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][topic.topic][topic.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="topic" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.TopicController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{message.destroy}">
                                    <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][topic.topic][topic.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][message.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="topic" />
                                    <f:param name="jsfcrud.relatedControllerType" value="classJSF.TopicController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{topic.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][topic.topic][topic.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{topic.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][topic.topic][topic.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{topic.createSetup}" value="New Topic" />
                <br />
                <h:commandLink action="#{topic.listSetup}" value="Show All Topic Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
