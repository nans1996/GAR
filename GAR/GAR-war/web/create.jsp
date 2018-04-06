<%-- 
    Document   : criate
    Created on : 19.11.2017, 19:24:10
    Author     : Vasilisa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page1</title>
        </head>
        <br>
        <h:outputText value="Количество посещений страниц: #{namedBean.count} "/>
        <br>
        <body>
            <h:form>
                <table border="1">           
                    <thead>
                        <tr>
                            <th>ID_user</th>
                            <th>login</th>
                            <th>pass</th>
                            <th>email</th>    
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Patronymic</th>
                            <th>Date</th> 
                        </tr>
                    </thead>
                    <tr>
                        <th>  <h:inputText value="#{userBean.user.ID_user}" required="true" id = "IDInput" validatorMessage="Ошибка" requiredMessage="Ошибка"> <f:validateLongRange minimum = "1" maximum = "500"/> </h:inputText> </th> 
                        <th>  <h:inputText value="#{userBean.user.login}" required="true" id = "login"  validatorMessage="Ошибка" requiredMessage="Ошибка"> <f:validateLength maximum="20" minimum="1"/> </h:inputText></th> 
                        <th>  <h:inputText value="#{userBean.user.pass}"  required="true" id = "pass"  validatorMessage="Ошибка" requiredMessage="Ошибка"> <f:validateLength maximum="20" minimum="5"/> <f:validateRegex pattern="[a-zA-Z0-9_]+" /> </h:inputText></th> 
                      <th>   <h:inputText value="#{userBean.user.email}"  required="true" id = "email"  validatorMessage="Ошибка" requiredMessage="Ошибка"> <f:validateRegex pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$" /></h:inputText></th> 
                       <th>  <h:inputText value="#{userBean.user.surname}"  required="true"id = "surname"  validatorMessage="Ошибка" requiredMessage="Ошибка"><f:validateLength maximum="20" minimum="2"/> <f:validateRegex pattern="[a-zA-Z]+" /></h:inputText></th> 
                        <th>  <h:inputText  value="#{userBean.user.name}"  required="true" id = "name"  validatorMessage="Ошибка" requiredMessage="Ошибка"> <f:validateLength maximum="20" minimum="2"/><f:validateRegex pattern="[a-zA-Z]+" /></h:inputText></th> 
                        <th>  <h:inputText value="#{userBean.user.patronymic}"  required="true"  id = "patronymic" validatorMessage="Ошибка"requiredMessage="Ошибка"><f:validateLength maximum="20" minimum="2"/> <f:validateRegex pattern="[a-zA-Z0-9]+" /></h:inputText></th> 
                        <th>  <h:inputText  value="#{userBean.user.date}"   required="true" id = "date" converterMessage="Ошибка" requiredMessage="Ошибка"><f:convertDateTime type="date" pattern="yyyy-MM-dd"/></h:inputText></th> 
                    </tr>
                </table>     
                <br />
                        <h:commandButton  action="#{userBean.create}" value="Добавить" />

                <br />    
                <h:message for = "IDInput" style = "color:red"/>
                <br>
                <h:message for = "login" style = "color:red" />
                 <br>
                <h:message for = "pass" style = "color:red" />
                <br>
                <h:message for = "email" style = "color:red" />
                <br>
                <h:message for = "surname" style = "color:red" />
                <br>
                <h:message for = "name" style = "color:red" />
                <br>
                <h:message for = "patronymic" style = "color:red" />
                <br>
                <h:message for = "date" style = "color:red" />

            </h:form>

        </body>
    </html>
</f:view>
