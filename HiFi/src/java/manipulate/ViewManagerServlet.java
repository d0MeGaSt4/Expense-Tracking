/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulate;

import hibersession.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

/**
 *
 * @author blue
 */
public class ViewManagerServlet extends HttpServlet {

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

            HttpSession sess = request.getSession();
            Integer eid = (Integer) sess.getAttribute("employeeid");
            String name = (String) sess.getAttribute("employeename");
            Session session = HibernateUtil.getSessionFactory().openSession();

            List claims = session.createQuery("from Claims where mgrname = :mgrname and status = :status ").setParameter("mgrname", name).setParameter("status", "Sent to manager for review").list();

            if (claims.size() >= 1) {
                sess.setAttribute("ClaimsList", claims);
                request.getRequestDispatcher("ViewManagerClaims.jsp").forward(request, response);
            } else {
                String message = "You have no submitted  claims against your name as yet.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("success.jsp").forward(request, response);
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
