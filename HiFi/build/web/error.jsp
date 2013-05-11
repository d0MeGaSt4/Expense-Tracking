<%@ page language="java" import="java.util.*"%>
<HTML>
    <HEAD>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
        <TITLE>Error </TITLE>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    </HEAD>

    <BODY >
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
        <span class="label label-important">Error Details</span>
        <br/>   
        <H4><%= request.getAttribute("message")%> </H4>
        <br/>
        Click <A HREF="index.html">here</A> to try again.
    </BODY>
</HTML>