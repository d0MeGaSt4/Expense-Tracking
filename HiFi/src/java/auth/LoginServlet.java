/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import hibersession.HibernateUtil;
import org.hibernate.HibernateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ntuproj.Employee;
import org.hibernate.Session;

/**
 *
 * @author blue
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int empid;
        int role = 0;
        String name = "", type;
        PrintWriter out = response.getWriter();
        try {

            System.out.println("start of process request, just before calling login bean:::");

            String employeeId = request.getParameter("txtEmpid");
            empid = Integer.parseInt(employeeId);
            String password = request.getParameter("txtPassword");

            Session session = HibernateUtil.getSessionFactory().openSession();

            List employee = session.createQuery("from Employee where empid = :empid and password = :password").setParameter("empid", empid).setParameter("password", password).list();

            if (employee.size() == 1) {
                Iterator i = employee.iterator();
                Employee e = (Employee) i.next();
                role = e.getEmptype();
                name = e.getFname();

                if (role == 1) {
                    type = "Employee";
                } else if (role == 2) {
                    type = "Manager";
                } else {
                    type = "Finance";
                }
                HttpSession sess = request.getSession();
                sess.setAttribute("employeename", name);
                sess.setAttribute("employeeid", empid);
                sess.setAttribute("role", role);

                if (role == 1) {
                    request.getRequestDispatcher("emphome.jsp").forward(request, response);
                } else if (role == 2) {
                    request.getRequestDispatcher("managerhome.jsp").forward(request, response);
                } else if (role == 3) {
                    request.getRequestDispatcher("finhome.jsp").forward(request, response);
                }

            } else {
                String message = "Invalid Username/password.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Exception caught " + e.getMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
