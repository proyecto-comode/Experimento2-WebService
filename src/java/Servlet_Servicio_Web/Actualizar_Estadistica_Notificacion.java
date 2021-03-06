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
@WebServlet(name = "Actualizar_Estadistica_Notificacion", urlPatterns = {"/Actualizar_Estadistica_Notificacion"})
public class Actualizar_Estadistica_Notificacion extends HttpServlet {

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
            out.println("<title> Actualizar_Estadistica_Notificacion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Notificaciones Dispositivo</h1>");
            out.println("</body>");
            out.println("</html>");
            String id = request.getParameter("id_notificacion");
            String action = request.getParameter("action");
            String respuesta = ActualizarEstadistica(id,action);
            out.println("<p>"+respuesta+"</p>");
        }
    }
    
    public String ActualizarEstadistica(String id, String action){
        
        String respuesta = "";
        
        //Creamos las instancias de cada una de las clases....
        //Inicializando la conexion con el servidor para obtener datos...
        ConnectionServer connectionServer = new ConnectionServer();
        Connection conec = connectionServer.conexion();

        //Decision para ver si la conexion es nula o buena con el servidor...
        if(conec ==  null){
            System.out.println("Conexion Fallida");
        }else{
            try {
                //Obteniendo
                Statement statment = conec.createStatement();
                //Preguntando si ya existe en la bd el dispositivo...
                ResultSet resultSet = statment.executeQuery("select * from notification where Id_Notification='"+id+"'");
                if(resultSet.next()){
                    if(action.equals("Oportuno")){
                        PreparedStatement preparedStatement = conec.prepareStatement("UPDATE notification SET notification.Oportuno = notification.Oportuno + 1 WHERE Id_Notification ="+id);
                        preparedStatement.executeUpdate();
                        respuesta = "Actualizado oportuno"+id;
                    }else if(action.equals("Inoportuno")){
                        PreparedStatement preparedStatement = conec.prepareStatement("UPDATE notification SET notification.Inoportuno = notification.Inoportuno + 1 WHERE Id_Notification ="+id);
                        preparedStatement.executeUpdate();
                        respuesta = "Actualizado inoportuno"+id;
                    }
                }
                
                    
            } catch (SQLException ex) {
                System.out.println("Problemas con el servidor al obtener datos " + ex);
                respuesta = "No se creo instancia";
            }
            
        }
        return respuesta;
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
