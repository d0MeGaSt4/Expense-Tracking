
<%@page contentType="text/html" pageEncoding="UTF-8" import=" javax.naming.InitialContext,javax.naming.Context
        ,java.util.Properties,javax.naming.NamingException,javax.rmi.PortableRemoteObject"%>
<!DOCTYPE html>
<%!    
    String ename, link, claim;
    Integer eid, claimid;
    Integer etype, retVal;

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manger Update Comment</title>
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
                <a href="index.html">Expense Reimbursement System</a>
            </li>
            <li><a href="http://www8.hp.com/uk/en/home.html">Parent Site</a></li>
            <li><a href="http://www8.hp.com/uk/en/contact-hp/contact.html"> Contact Us</a></li>
            <li><a href="http://www8.hp.com/us/en/hp-information/about-hp/index.html">About</a></li>
        </ul>

        <%
            ename = (String) session.getAttribute("employeename");
            eid = (Integer) session.getAttribute("employeeid");
            etype = (Integer) session.getAttribute("role");
            claim = request.getParameter("claimid");
            claimid = Integer.parseInt(claim);
            

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
        <form name="frmLogin" action="RatifyClaimComment?claimid=<%=claimid%>" method="post"  >
            <fieldset>
                <div class="text-center">
                    <label class="text-center" for="txtComment">Enter your comments  </label>
                    <div class="text-center">
                        <textarea  row ="3" cols="10" id="txtComment" name ="txtComment" placeholder="Additional Comments"></textarea>
                    </div>
                    <div class="text-center">
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Update Comment </button>
                        </div>
                    </div>
                </div>
            </fieldset>
        </form>

    </body>
</html>