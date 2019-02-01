package Servicios;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Macbook
 */
public class Busqueda_Preferencias {
    
    
    public String buscarpreferenciasdispositivo(String titulo, String mensaje, String lugar, String len, String interior,String exterior, String areas, String condicion){
        
        //Creamos las instancias de cada una de las clases....
        //Inicializando la conexion con el servidor para obtener datos...
        ConnectionServer connectionServer = new ConnectionServer();
        Connection conec = connectionServer.conexion();
        
        String id_notificacion = "";
        
        if(conec ==  null){
            System.out.println("Conexion Fallida");
        }else{
            try {
                //Obteniendo
                Statement statment = conec.createStatement();
                //Consiguiendo el id de la ontologia del dispositivo...
                PreparedStatement preparedStatement = conec.prepareStatement("insert into notification (Titulo, Mensaje, Dirigido, Oportuno, Inoportuno) values('"+titulo+"','"+mensaje+"','"+condicion+"',0,0)");
                preparedStatement.executeUpdate();
                ResultSet resultSet2 = statment.executeQuery("select * from notification");
                while (resultSet2.next()) 
                { 
                    id_notificacion = resultSet2.getString(1);
                }
             
            } catch (SQLException ex) {
                System.out.println("1 Problemas con el servidor al obtener datos " + ex);
            }
            
        }
    
        String respuesta = "";
        
        String NS = "http://www.comode.org/comode.owl";
        OntModel model = null;

        // crear un modelo utilizando como razonador OWL_MEM_RULE_INF
        model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );

        // abrir el archivo con la ontología
        java.io.InputStream inp = FileManager.get().open( "file:/Users/Macbook/Desktop/comoderdf.owl" );
        if (inp == null) {
            throw new IllegalArgumentException("Archivo no encontrado");
        }

        // leer el archivo RDF/XML ontolgia...
        model.read(inp, "");
        
        if(condicion.equals("Preferencias")){
            //Recorriendo todas las instancias de dispositivos
            OntClass users = model.getOntClass(NS+"#"+"User");
            Property location = model.getProperty(NS+"#"+"LocationDetailed");
            Property lenguaje = model.getProperty(NS+"#"+"LanguaguePreferences");
            Property pinterior = model.getProperty(NS+"#"+"IndoorActivity");
            Property pexterior = model.getProperty(NS+"#"+"OutdoorActivity");
            Property pareas = model.getProperty(NS+"#"+"AreasInterest");
            ExtendedIterator iteratorClasses = model.listClasses(); 
            while ( iteratorClasses.hasNext() ){
                OntClass ontClass = (OntClass) iteratorClasses.next();
                if(ontClass.equals(users)){
                    System.out.println( ontClass );
                    ExtendedIterator iteratorInstances = ontClass.listInstances();
                    while ( iteratorInstances.hasNext() ){
                        Individual ind;
                        String individual;
                        System.out.println( "\t"+ String.valueOf(ind = (Individual) iteratorInstances.next()));
                        individual = ind.toString();
                        String propiedad;
                        System.out.println(propiedad = String.valueOf(ind.getPropertyValue(location)));
                        String lenguague;
                        System.out.println(lenguague = String.valueOf(ind.getPropertyValue(lenguaje)));
                        String lenguajes[] = lenguague.split(",");
                        for(int x = 0; x < lenguajes.length; x++){
                            String len2[] = len.split(",");
                            for(int a = 0; a < len2.length; a++){
                                if(lenguajes[x].equals(len2[a])){
                                    actualizarEstadoNotificacion(individual);
                                }
                            }
                        }
                        String act_interiores;
                        System.out.println(act_interiores = String.valueOf(ind.getPropertyValue(pinterior)));
                        String act_interioress[] = act_interiores.split(",");
                        for(int x = 0; x < act_interioress.length; x++){
                            String interior2[] = interior.split(",");
                            for(int a = 0; a < interior2.length; a++){
                                if(act_interioress[x].equals(interior2[a])){
                                    actualizarEstadoNotificacion(individual);
                                }
                            }
                        }
                        String act_exteriores;
                        System.out.println(act_exteriores = String.valueOf(ind.getPropertyValue(pexterior)));
                        String act_exterioress[] = act_exteriores.split(",");
                        for(int x = 0; x < act_exterioress.length; x++){
                            String exterior2[] = exterior.split(",");
                            for(int a = 0; a < exterior2.length; a++){
                                if(act_exterioress[x].equals(exterior2[a])){
                                    actualizarEstadoNotificacion(individual);
                                }
                            }
                        }
                        String areas_interes;
                        System.out.println(areas_interes = String.valueOf(ind.getPropertyValue(pareas)));
                        String areass[] = areas_interes.split(",");
                        for(int x = 0; x < areass.length; x++){
                            String areas2[] = areas.split(",");
                            for(int a = 0; a < areas2.length; a++){
                                if(areass[x].equals(areas2[a])){
                                    actualizarEstadoNotificacion(individual);
                                }
                            }
                        }
                        String lugar2[] = lugar.split("@");
                        for(int a = 0; a < lugar2.length; a++){
                            if(propiedad.equals(lugar2[a])){                               
                            actualizarEstadoNotificacion(individual);
                            }
                        }
                    }
                }    
            }
                enviarnotificacion(titulo,mensaje,"Preferencias",id_notificacion);
        }else{
                enviarnotificacion(titulo,mensaje,"Todos",id_notificacion);
        }
        return "Dispositivos Actualizados correctamente a continuacion se enviaran las notificaciones...."+id_notificacion;
        
    }
    
    public String actualizarEstadoNotificacion(String token){
            
        String tok = token.substring(33);
        
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
                //Consiguiendo el id de la ontologia del dispositivo...
                ResultSet resultSet = statment.executeQuery("select * from token where Token='"+tok+"'");
                if(resultSet.next()){
                    PreparedStatement preparedStatement = conec.prepareStatement("update token set Enviar='true' where Token='"+tok+"'");
                    preparedStatement.executeUpdate();
                    if(!resultSet.next()){
                        respuesta = "Dispositivo actualizado para notificar";
                    }else{
                        respuesta = "Dispositivo no actualizado";
                    }
                }
             
            } catch (SQLException ex) {
                System.out.println("Problemas con el servidor al obtener datos " + ex);
            }
            
        }
        return respuesta;
    }
    
    public void enviarnotificacion(String titulo, String mensaje, String condicion,String id_notificacion){
                    //Enviando los datos para crear la ontologia del dispositivo....
            String url_servidor = "http://localhost:8888/Notificaciones_COMODE/Enviar_Notificacion.php";
            HttpClient cliente = new DefaultHttpClient();
            HttpPost post = new HttpPost(url_servidor);
            List<NameValuePair> postParameters = new ArrayList<NameValuePair>(1);
            postParameters.add(new BasicNameValuePair("titulo",titulo));
            postParameters.add(new BasicNameValuePair("mensaje",mensaje));
            postParameters.add(new BasicNameValuePair("condicion",condicion));
            postParameters.add(new BasicNameValuePair("id_notificacion", id_notificacion));
            try {
                post.setEntity(new UrlEncodedFormEntity(postParameters, HTTP.UTF_8));
                cliente.execute(post);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

}