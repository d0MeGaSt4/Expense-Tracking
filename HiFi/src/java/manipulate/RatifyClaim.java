/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulate;

import hibersession.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ntuproj.Claims;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author blue
 */
public class RatifyClaim extends HttpServlet {

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

        String newstatus;
        String claimtoupdate = request.getParameter("claimid");
        Integer claimid = Integer.parseInt(claimtoupdate);
        String gmode = request.getParameter("i");
        Integer mode = Integer.parseInt(gmode);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            Claims o = (Claims) session.get(Claims.class, claimid);

            if (mode == 1) {
                newstatus = "Claim approved by manager";
            } else {
                newstatus = " Claim rejected by manager";
            }
            o.setStatus(newstatus);

            transaction.commit();
            String message = "The claim was successfully updated . You may continue to ratify more claims";
            request.setAttribute("message", message);
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Exception caught " + e.getMessage());
            String message = "There was an error while performing the updation on the claim ";
            request.setAttribute("message", message);
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
