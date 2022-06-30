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
import javax.servlet.annotation.MultipartConfig; 
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
        //response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            //Arreglo, en caso de que venga Null cae en Exception ya que equalsIgnoreCase no puede comparar con nulls
            //si que se inicializaron con "" y valida para llenar estos campos.
            String H_action = "";
            if (request.getHeader("action") != null){
                H_action = request.getHeader("action");
            }
            
            //borrar User
            if (H_action.equalsIgnoreCase("delete")) {
                if(!request.getHeader("del_id").equals("")) {
                    //get_params
                    int id = Integer.parseInt(request.getHeader("del_id"));
                    //set_response
                    response.setContentType("application/json;charset=UTF-8");
                    String json_ok = "{\"data\" : [{\"status\":\"200\", \"msg\" : \"Registro eliminado con exito :)\"}]}";
                    String json_error = "{\"data\" : [{\"status\":\"500\", \"msg\" : \"Error eliminando el regitro :(\"}]}";
                   
                    if (uDao.delete(id)) {
                            out.println(json_ok);
                    }else{
                            out.println(json_error);
                    }
                }else{
                    response.setContentType("application/json;charset=UTF-8");
                    String json_error = "{\"data\" : [{\"status\":\"400\", \"msg\" : \"Faltan datos :(\"}]}";
                    out.println(json_error);
                }
            }
            
            //editar User
            if (H_action.equalsIgnoreCase("edit")) {
                if(!request.getHeader("ed_id").equals("") && !request.getHeader("ed_email").equals("") && !request.getHeader("ed_password").equals("")) {
                    //get_params
                    String ed_email = request.getHeader("ed_email");
                    String password = request.getHeader("ed_password");
                    int id = Integer.parseInt(request.getHeader("ed_id"));
                    //set_response
                    response.setContentType("application/json;charset=UTF-8");
                    String json_ok = "{\"data\" : [{\"status\":\"200\", \"msg\" : \"Registro editado con exito :)\"}]}";
                    String json_error = "{\"data\" : [{\"status\":\"500\", \"msg\" : \"Error editando el regitro :(\"}]}";
                   
                    if (uDao.edit(id,ed_email,password)) {
                            out.println(json_ok);
                    }else{
                            out.println(json_error);
                    }
                }else{
                    response.setContentType("application/json;charset=UTF-8");
                    String json_error = "{\"data\" : [{\"status\":\"400\", \"msg\" : \"Faltan datos :(\"}]}";
                    out.println(json_error);
                }
            }
        }catch(Exception e){
            System.out.println(e);
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
