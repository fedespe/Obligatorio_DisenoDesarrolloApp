/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import VistasWeb.VistaMonitoreoWeb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "MonitoreoPartidas", urlPatterns = {"/MonitoreoPartidas"})
public class MonitoreoPartidas extends HttpServlet {

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
        
        String accion = request.getParameter("accion");
        System.out.println("ACCION=" + accion);
        if(accion.equals("new")){
            request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
            AsyncContext contexto = request.startAsync();
            contexto.getResponse().setContentType("text/event-stream"); 
            contexto.getResponse().setCharacterEncoding("UTF-8");
            contexto.setTimeout(0);
            request.getSession(true).setAttribute("vista",new VistaMonitoreoWeb(contexto));

        }else{
            HttpSession sesion = request.getSession();
            if(sesion==null){
                System.out.println("SE PERDIO LA SESION");
                return;
            }
            VistaMonitoreoWeb vista = (VistaMonitoreoWeb)sesion.getAttribute("vista");
            switch(accion){
//                case "agregar" : vista.agregar(request.getParameter("texto"),request.getParameter("duracion"));break;
//                case "crear" : vista.crear(request.getParameter("nombre"),request.getParameter("tipo"));break;
            }
        }
    }
    public MonitoreoPartidas() {
        //new VistaIngreso().setVisible(true);
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
