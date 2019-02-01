/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet_Servicio_Web;

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
@WebServlet(name = "Ontologia_Dispositivo", urlPatterns = {"/Ontologia_Dispositivo"})
public class Ontologia_Dispositivo extends HttpServlet {

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
            out.println("<title>Servlet Ontologia_Dispositivo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ontologia Dispositivo</h1>");
            out.println("</body>");
            out.println("</html>");
            String respuesta = "";
            String condicion = request.getParameter("cond");
            if(condicion.equals("instancia")){
                String token = request.getParameter("token");
                String loc = request.getParameter("loc");
                String carac = "";
                carac = request.getParameter("carac");
                String inact = "";
                inact = request.getParameter("in");
                String outact = "";
                outact = request.getParameter("out");
                String leng = "";
                leng = request.getParameter("leng");
                String areas = "";
                areas = request.getParameter("areas");
                out.println("<p>instancia</p>");
                respuesta = InstanciaDispositivoenOntologia(token,loc,carac,inact,outact,leng,areas);
            }else if(condicion.equals("actualizacion")){
                String token = request.getParameter("token");
                String loc = request.getParameter("loc");
                out.println("<p>actualizacion</p>");
                respuesta = actualizarlocalizacion(token,loc);              
            }
            out.println("<p>"+respuesta+"</p>");
        }
    }
   
    public String actualizarlocalizacion(String token,String loc){
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
                ResultSet resultSet = statment.executeQuery("select * from token where Token='"+token+"'");
                if(resultSet.next()){
                    //Inicializando metodo para crear su instancia del dispositivo en la ontologia o practicamente actualizarla...
                    Gestion_Ontologia_Dispositivo actualizar_Ontologia_Dispositivo = new Gestion_Ontologia_Dispositivo();
                    actualizar_Ontologia_Dispositivo.actualizarlocdispositivo(token,loc);
                    respuesta = "Se actualizo el token y se mando la localizacion en la ontologia token = "+token;
                }else{
                    respuesta = "No se encontro el token del dispositivo en la base de datos";
                }
                
                    
            } catch (SQLException ex) {
                System.out.println("Problemas con el servidor al obtener datos " + ex);
                respuesta = "No se creo instancia";
            }
            
        }
        return respuesta;
    }
    
    public String InstanciaDispositivoenOntologia(String token, String localizacion, String caracteristicas, String indoor, String outdoor, String lenguajes, String areas){
        
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
                ResultSet resultSet = statment.executeQuery("select * from token where Token='"+token+"'");
                if(resultSet.next()){
                    //Inicializando metodo para crear su instancia del dispositivo en la ontologia o practicamente actualizarla...
                    Gestion_Ontologia_Dispositivo actualizar_Ontologia_Dispositivo = new Gestion_Ontologia_Dispositivo();
                    actualizar_Ontologia_Dispositivo.actualizarOntologia(token,localizacion,caracteristicas,indoor,outdoor,lenguajes,areas);
                    respuesta = "Se encontro el token y se mando a crear su instancia en la ontologia = "+token;
                }else{
                    respuesta = "No se encontro el token del dispositivo en la base de datos";
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
