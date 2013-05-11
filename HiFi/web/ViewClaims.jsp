
<%@page import="ntuproj.Claims"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import=" javax.naming.InitialContext,javax.naming.Context
        ,java.util.Properties,javax.naming.NamingException,javax.rmi.PortableRemoteObject"%>
<!DOCTYPE html>
<%!    
    String ename, link;
    Integer eid;
    Integer etype, retVal;

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Submitted Claims</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script language="javascript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script language="javascript" type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/jquery.validate.min.js"></script>
        <script>
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
        <style>
            body {background-image:url('bootstrap/img/hp.jpg');background-repeat: no-repeat;background-position:right top;}
            .center{
                position: relative;
                height: 200px;
                width: 400px;
                margin: -100px 0 0 -200px;
                top: 50%;
                left: 50%;

            }
            .button-container form,
            .button-container form div {
                display: inline;
            }

            .button-container button {
                display: inline;
                vertical-align: middle;
            }
        </style>

    </head>
    <body>   
        <br/>
        <br/>
        <ul class="nav nav-pills ">
            <li class="active">
                <a href="index.html">Expense Reimbursement System</a>
            </li>
            <li><a href="http://www8.hp.com/uk/en/home.html">Parent Site</a></li>
            <li><a href="http://www8.hp.com/uk/en/contact-hp/contact.html"> Contact Us</a></li>
            <li><a href="http://www8.hp.com/us/en/hp-information/about-hp/index.html">About</a></li>
        </ul>

        <%
            List l = (List) session.getAttribute("ClaimsList");
            ename = (String) session.getAttribute("employeename");
            eid = (Integer) session.getAttribute("employeeid");
            etype = (Integer) session.getAttribute("role");
            retVal = l.size();
            if (etype == 1) {
                link = "emphome.jsp";
            } else if (etype == 2) {
                link = "managerhome.jsp";
            } else {
                link = "finhome.jsp";
            }


        %>
        <a href="<%=link%>"><button class="btn btn-primary ">Home</button></a>
        <a href="index.html"><button class="btn btn-primary ">Logout</button></a>
        <br/>
        <p> <strong color=" red" > Ordered by latest </strong></p>
        <%
            if (retVal != 0) {
        %>
        <%
            ListIterator<Claims> iterator = l.listIterator(l.size());
        %>
        <% while (iterator.hasPrevious()) {
                Claims c = iterator.previous();
        %>


        <dl class="dl-horizontal">
            <dt><span class="label label-warning">Claim Id</span></dt>
            <dd><%=c.getClaimid()%></dd>
            <dt><span class="label label-warning">Employee Id</span></dt>
            <dd><%=c.getEmpid()%></dd>
            <dt><span class="label label-warning">Manager Name</span></dt>
            <dd><%=c.getMgrname()%></dd>
            <dt><span class="label label-warning">Overtime Hours</span></dt>
            <dd><%=c.getOvertimehours()%></dd>
            <dt><span class="label label-warning">Comments</span></dt>
            <dd><%=c.getComments()%></dd>
            <dt><span class="label label-warning">Status</span></dt>
            <dd><strong><%=c.getStatus()%></strong></dd>
            <div class="center">

                <div class="button-container">         
                    <form  ACTION="UpdateClaim.jsp?claimid=<%=c.getClaimid()%>" METHOD="post">
                        <div>
                            <button  type="submit" class="btn btn-inverse ">Update Claim</button>
                        </div>
                    </form>
                    <form  ACTION="DeleteServlet?claimid=<%=c.getClaimid()%>" METHOD="post">
                        <div>
                            <button  type="submit" class="btn btn-danger ">Delete Claim</button>
                        </div>
                    </form>
                </div>

            </div>
        </dl>
        <%}%>
        <% } else {
        %> 
        <br/>
        <br/>
        <p> <strong>You have not submitted any claims as yet . There are no details to be seen ! </strong></p>
        <%}
        %>
    </body>
</html>