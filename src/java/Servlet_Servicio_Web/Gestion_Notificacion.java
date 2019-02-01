/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet_Servicio_Web;

import Servicios.Busqueda_Preferencias;
import Servicios.ConnectionServer;
import Servicios.Gestion_Ontologia_Dispositivo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Macbook
 */
@WebServlet(name = "Gestion_Notificacion", urlPatterns = {"/Gestion_Notificacion"})
public class Gestion_Notificacion extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Gestion_Notificacion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Notificaciones Dispositivo</h1>");
            out.println("</body>");
            out.println("</html>");
            String titulo = request.getParameter("titulo");
            String mensaje = request.getParameter("mensaje");
            String condicion = request.getParameter("condicion");
            
            //lenguaje
            String espaniol = request.getParameter("espaniol");
            String ingles = request.getParameter("ingles");
            String lenguaje = espaniol + "," + ingles ;
            
            //interior
            String juegosmesa = request.getParameter("juegosmesa");
            String cine = request.getParameter("cine");
            String museo = request.getParameter("museo");
            String literatura = request.getParameter("literatura");
            String videojuegos = request.getParameter("videojuegos");
            String restaurante = request.getParameter("restaurante");
            String interior = juegosmesa + "," + cine + "," + museo + "," + literatura + "," + videojuegos + "," + restaurante;
            
            //exterior
            String picnic = request.getParameter("picnic");
            String parque = request.getParameter("parque");
            String deporte = request.getParameter("deporte");
            String turismo = request.getParameter("turismo");
            String arte = request.getParameter("arte");
            String caminar = request.getParameter("caminar");
            String exterior = picnic + "," + parque + "," + deporte + "," + turismo + "," + arte + "," + caminar;
                
            //areas interes
            String salud = request.getParameter("salud");
            String computacion = request.getParameter("computacion");
            String tecnologia = request.getParameter("tecnologia");
            String naturaleza = request.getParameter("naturaleza");
            String sociales = request.getParameter("sociales");
            String humanidades = request.getParameter("humanidades");
            String economicas = request.getParameter("economicas");
            String admin = request.getParameter("admin");
            String artes = request.getParameter("artes");
            String exactas = request.getParameter("exactas");
            String areas = salud + "," + computacion + "," + tecnologia + "," + naturaleza + "," + sociales 
                    + "," + humanidades + "," + economicas + "," + admin + "," + artes + "," + exactas;
            
            //lugares
            String guzman = request.getParameter("guzman");
            String tamazula = request.getParameter("tamazula");
            String teca = request.getParameter("teca");
            String sayula = request.getParameter("sayula");
            String gdl = request.getParameter("gdl");
            String zapo = request.getParameter("zapo");
            String tapalpa = request.getParameter("tapalpa");
            String mazamitla = request.getParameter("mazamitla");
            String lugar = guzman + "@" + tamazula + "@" + teca + "@" + sayula + "@" + gdl + "@" + zapo + "@" + tapalpa + "@" + mazamitla;
            
            System.out.print(espaniol + ingles);
            Busqueda_Preferencias bp = new Busqueda_Preferencias();
            String respuesta = bp.buscarpreferenciasdispositivo(titulo,mensaje,lugar,lenguaje,interior,exterior,areas,condicion);
            out.println("<p>"+respuesta+"</p>");
            
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
