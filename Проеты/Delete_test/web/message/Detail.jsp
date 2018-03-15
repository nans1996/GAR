<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Message Detail</title>
            <link rel="stylesheet" type="text/css" href="/Delete_test/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Message Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IDMessage:"/>
                    <h:outputText value="#{message.message.IDMessage}" title="IDMessage" />
                    <h:outputText value="Subject:"/>
                    <h:outputText value="#{message.message.subject}" title="Subject" />
                    <h:outputText value="Date:"/>
                    <h:outputText value="#{message.message.date}" title="Date" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="Content:"/>
                    <h:outputText value="#{message.message.content}" title="Content" />
                    <h:outputText value="IDUser:"/>
                    <h:panelGroup>
                        <h:outputText value="#{message.message.IDUser}"/>
                        <h:panelGroup rendered="#{message.message.IDUser != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{user.detailSetup}">
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message][message.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message.IDUser][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="message"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.MessageController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{user.editSetup}">
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message][message.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message.IDUser][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="message"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.MessageController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{user.destroy}">
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message][message.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message.IDUser][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="message"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.MessageController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IDTopic:"/>
                    <h:panelGroup>
                        <h:outputText value="#{message.message.IDTopic}"/>
                        <h:panelGroup rendered="#{message.message.IDTopic != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{topic.detailSetup}">
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message][message.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message.IDTopic][topic.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="message"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.MessageController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{topic.editSetup}">
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message][message.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message.IDTopic][topic.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="message"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.MessageController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{topic.destroy}">
                                <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message][message.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTopic" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message.IDTopic][topic.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="message"/>
                                <f:param name="jsfcrud.relatedControllerType" value="classJSF.MessageController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{message.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message][message.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{message.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentMessage" value="#{jsfcrud_class['classJSF.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][message.message][message.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{message.createSetup}" value="New Message" />
                <br />
                <h:commandLink action="#{message.listSetup}" value="Show All Message Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
