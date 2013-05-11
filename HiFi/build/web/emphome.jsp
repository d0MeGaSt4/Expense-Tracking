
<%@page contentType="text/html" pageEncoding="UTF-8" import="javax.naming.InitialContext,javax.naming.Context
        ,java.util.Properties,javax.naming.NamingException,javax.rmi.PortableRemoteObject"%>
<!DOCTYPE html>
<%!
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Claims Page</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <style>
            body {background-image:url('bootstrap/img/hp.jpg');background-repeat: no-repeat;background-position:right top;}
            .center{
                position: absolute;
                height: 200px;
                width: 400px;
                margin: -100px 0 0 -200px;
                top: 50%;
                left: 50%;

            }
            .left{
                position:relative;
                top:55%;
                left: 1%;
            }

        </style>
    </head>
    <body>
        <br/>
        <br/>

        <ul class="nav nav-pills ">
            <li class="active">
                <a href="index.html">Overtime Reimbursement System</a>
            </li>
            <li><a href="http://www8.hp.com/uk/en/home.html">Parent Site</a></li>
            <li><a href="http://www8.hp.com/uk/en/contact-hp/contact.html"> Contact Us</a></li>
            <li><a href="http://www8.hp.com/us/en/hp-information/about-hp/index.html">About</a></li>
        </ul>
        <%
            String name = (String) session.getAttribute("employeename");
            Integer eid = (Integer) session.getAttribute("employeeid");
            Integer role = (Integer) session.getAttribute("role");

        %>
        <span class="label label-important large badge">  Welcome <%=name%></span>
        <a href="index.html"><button class="btn btn-primary ">Logout</button></a>
        <br/>
        <br/>
        <br/>
        <br/>
        <div class="left" style="width:10%" >
            <p style="font-family:arial;color:green ;font-size:15px;"> Step 1  Submit claim to manager </p>
            <div class="progress progress-warning">
                <div class="bar" style="width: 33%" ></div>
            </div>
            <p style="font-family:arial;color:green ;font-size:15px;"> Step 2 Manager Approval </p>
            <div class="progress progress-warning">
                <div class="bar" style="width: 66%"></div>
            </div>
            <p style="font-family:arial;color:green ;font-size:15px;"> Step 3 Finance Approval </p>
            <div class="progress progress-warning">
                <div class="bar" style="width: 99%"></div>
            </div>
        </div>
        <div class="center" style="width:15%">
            <p class="button1">
                <a href="ViewServlet">  <button class="btn btn-large btn-primary " type="button">View Submitted Claims</button></a>
            </p>
            <p class="button2">
                <a href="SubmitClaim.jsp"> <button class="btn btn-large btn-primary " type="button">Submit New   Claims          </button></a>
            </p>
        </div>
    </body>
</html>
