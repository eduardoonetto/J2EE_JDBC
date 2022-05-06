/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.UserDao;

/**
 *
 * @author alumnosre
 */
public class AdminUser extends HttpServlet {
    
    UserDao uDao= new UserDao();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            if (request.getParameter("action").equalsIgnoreCase("delete")) {
                
                        System.out.println("ENTRE");
                if (!request.getParameter("id").equals("")) {
                    System.out.println("id= "+ request.getParameter("id"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    if (uDao.delete(id)) {
                        System.out.println("MIQUERY");
                            request.getRequestDispatcher("listar.jsp").forward(request, response);
                    }
                }
            }
            if (request.getParameter("action").equalsIgnoreCase("edit")) {
                //if (!request.getParameter("id").equals("")) {
                    //int id = Integer.parseInt(request.getParameter("email"));
                    String ed_email = request.getParameter("email");

                    System.out.println("EMAIL= " + ed_email);
                    //if (uDao.delete(id)) {
                    //        request.getRequestDispatcher("listar.jsp").forward(request, response);
                    //}
                //}
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminUser</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUser at aa" + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
