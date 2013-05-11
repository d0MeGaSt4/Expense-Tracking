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
public class UpdateServlet extends HttpServlet {

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
            Integer hr;
            String cmt,newcomments;
            String emp = request.getParameter("txtEmpid");
            Integer empid = Integer.parseInt(emp);
            String cld = request.getParameter("txtClaimid");
            Integer claimid = Integer.parseInt(cld);
            String mgr = request.getParameter("txtManager");
            String hours = request.getParameter("txtOvertime");
            if (!"".equals(hours)) {
                hr = Integer.parseInt(hours);
            } else {
                hr = 0;
            }
            String sdate = request.getParameter("txtStartdate");
            String edate = request.getParameter("txtEnddate");
            String comments = request.getParameter("txtAddcomments");
            if (!"".equals(sdate)) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
                    startDate = dateFormat.parse(sdate);
                } catch (Exception e) {
                    System.out.println("Exception caught in coverting date" + e.getMessage());
                }
            } else {
                startDate = null;
            }
            if (!"".equals(edate)) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
                    endDate = dateFormat.parse(edate);
                } catch (Exception e) {
                    System.out.println("Exception caught in coverting date" + e.getMessage());
                }
            } else {
                endDate = null;
            }
            HttpSession sess = request.getSession();
            Integer eid = (Integer) sess.getAttribute("employeeid");

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            Integer id = null;
            try {
                transaction = session.beginTransaction();
                Claims o = (Claims) session.get(Claims.class, claimid);
                if (!"".equals(mgr)) {
                    o.setMgrname(mgr);
                }
                if (hr != 0) {
                    o.setOvertimehours(hr);
                }
                if (sdate != null) {
                    o.setStartdate(startDate);
                }
                if (edate != null) {
                    o.setEnddate(endDate);
                }
                if (!"".equals(comments)) {
                    cmt = o.getComments();
                    newcomments = cmt + " (Employee) " + comments;
                    o.setComments(newcomments);
                }
                o.setStatus("Sent to manager for review ");
                
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
                System.out.println("Hibernate Exception caught " + e.getMessage());
            }
            String message = "Your claim has been updated successfully .";
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
