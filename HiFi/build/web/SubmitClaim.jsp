
<%@page contentType="text/html" pageEncoding="UTF-8" import="javax.naming.InitialContext,javax.naming.Context
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
        <title>Submit Claims</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script language="javascript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script language="javascript" type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/jquery.validate.min.js"></script>
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
        </style>
        <script type="text/javascript">
            $(function() {

                $('#claim-form').validate(
                        {
                            rules: {
                                txtEmpid: {
                                    required: true,
                                    number: true
                                },
                                txtManager: {
                                    required: true,
                                    string: true
                                },
                                txtOvertime: {
                                    number: true,
                                    required: true
                                },
                                txtStartdate: {
                                    required: true,
                                    date: true
                                },
                                txtEnddate: {
                                    required: true,
                                    date: true
                                }
                            },
                            errorPlacement: function(error, element) {
                                element.css('background', '#ffdddd');
                            }
                        });
            });
        </script>
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
        <a href="index.html"><button class="btn btn-primary ">Logout</button></a>
        <form NAME="frmLogin" ACTION="SubmitServlet" METHOD="post" id="claim-form" >
            <fieldset>
                <div class="text-center">
                    <label class="text-center" for="txtEmpid">Employee Id </label>
                    <div class="text-center">
                        <input type="text" id="txtEmpid" name ="txtEmpid" value=<%=eid%> readonly>
                    </div>
                </div>
                <div class="text-center">
                    <label class="text-center" for="txtManager">Manager Name</label>
                    <div class="text-center">
                        <input type="text" id="txtManager" name ="txtManager" placeholder="Manager Name">
                    </div>
                </div>
                <div class="text-center">
                    <label class="text-center" for="txtOvertime">Overtime Hours </label>
                    <div class="text-center">
                        <input type="text" id="txtOvertime" name ="txtOvertime" placeholder="Number of overtime hours">
                    </div>
                </div>
                <div class="text-center">
                    <label class="text-center" for="txtStartdate">Start date  </label>
                    <div class="text-center">
                        <input type="text" id="txtStartdate" name ="txtStartdate" placeholder="Date in YYYY/MM/DD format">
                    </div>
                </div>
                <div class="text-center">
                    <label class="text-center" for="txtEnddate">End date  </label>
                    <div class="text-center">
                        <input type="text" id="txtEnddate" name ="txtEnddate" placeholder="Date in YYYY/MM/DD format">
                    </div>
                </div>
                <div class="text-center">
                    <label class="text-center" for="txtAddcomments">Additional Comments  </label>
                    <div class="text-center">
                        <textarea  row ="3" cols="10" id="txtAddcomments" name ="txtAddcomments" placeholder="Additional Comments"></textarea>
                    </div>
                </div>
                <br/>
                <div class="text-center">
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Submit Claim</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </body>
</html>
