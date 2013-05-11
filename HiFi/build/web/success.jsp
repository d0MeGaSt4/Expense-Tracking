<%@ page language="java" import="java.util.*"%>
<HTML>
    <%!    String ename, link;
        Integer eid;
        Integer etype, retVal;

    %>
    <head>
        <meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
        <title>Success </title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    </head>

    <body >
        <!-- Initializing a form object. --> 
        <script src="bootstrap/js/bootstrap.min.js"></script>
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
        <br/>
        <br/>
        <%
            ename = (String) session.getAttribute("employeename");
            eid = (Integer) session.getAttribute("employeeid");
            etype = (Integer) session.getAttribute("role");

            if (etype == 1) {
                link = "emphome.jsp";
            } else if (etype == 2) {
                link = "managerhome.jsp";
            } else {
                link = "finhome.jsp";
            }

        %>
        <a href="<%=link%>"><button class="btn btn-primary ">Home</button></a>
        <br/>
        <br/>
        <span class="label label-important">Operation Result </span>
        <br/>   
        <H4><%= request.getAttribute("message")%> </H4>
        <br/>
    </body>
</html>