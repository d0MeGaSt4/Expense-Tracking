/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulate;

import hibersession.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ntuproj.Claims;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author blue
 */
public class SubmitServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {

            Date startDate = null, endDate = null;
            String emp = request.getParameter("txtEmpid");
            Integer empid = Integer.parseInt(emp);
            String mgr = request.getParameter("txtManager");
            String hours = request.getParameter("txtOvertime");
            Integer hr = Integer.parseInt(hours);
            String sdate = request.getParameter("txtStartdate");
            String edate = request.getParameter("txtEnddate");
            String comments = request.getParameter("txtAddcomments");
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
                startDate = dateFormat.parse(sdate);
                endDate = dateFormat.parse(edate);
            } catch (Exception e) {
                System.out.println("Execption in date conversion" + e.getMessage());
            }
            HttpSession sess = request.getSession();
            Integer eid = (Integer) sess.getAttribute("employeeid");

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            Integer id = null;
            try {
                transaction = session.beginTransaction();
                Claims o = new Claims();
                o.setEmpid(empid);
                o.setMgrname(mgr);
                o.setOvertimehours(hr);
                o.setStartdate(startDate);
                o.setEnddate(endDate);
                if ("".equals(comments)) {
                    comments += " No comments added ";
                }
                comments = "(Employee) " + comments;
                o.setComments(comments);
                o.setStatus("Sent to manager for review ");
                id =  (Integer) session.save(o);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
                System.out.println("Hibernate Exception caught " + e.getMessage());
            } 
            String message = "Your claim has been submitted successfully .";
            request.setAttribute("message", message);
            request.getRequestDispatcher("success.jsp").forward(request, response);
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
