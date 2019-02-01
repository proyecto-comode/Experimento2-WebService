/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.impl.AnnotationPropertyImpl;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.util.FileManager;

/**
 *
 * @author Macbook
 */
public class Gestion_Ontologia_Dispositivo {
    
    
	
    public void actualizarOntologia(String tok, String localizacion, String caracteristicas, String indoor, String outdoor, String lenguajes, String areas){

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

        //Instanciando el usuario en user del dispositivo en la ontologia del mismo y agregando los values con sus respectivos dataproperity...
        OntClass users = model.getOntClass(NS+"#"+"User");
        Individual user = model.createIndividual(NS+"#"+tok,users);

        //Device Characteristic...
        String carac[] = caracteristicas.split(",");
        user.setPropertyValue(model.getProperty(NS+"#"+"VersionAndroid"), model.createTypedLiteral(carac[0].trim()));
                    user.setPropertyValue(model.getProperty(NS+"#"+"Board"), model.createTypedLiteral(carac[1].trim()));
        user.setPropertyValue(model.getProperty(NS+"#"+"Display"), model.createTypedLiteral(carac[2].trim()));
        user.setPropertyValue(model.getProperty(NS+"#"+"Hardware"), model.createTypedLiteral(carac[3].trim()));
        user.setPropertyValue(model.getProperty(NS+"#"+"Manufacture"), model.createTypedLiteral(carac[4].trim()));
        user.setPropertyValue(model.getProperty(NS+"#"+"DeviceModel"), model.createTypedLiteral(carac[5].trim()));

        //Location..
        user.setPropertyValue(model.getProperty(NS+"#"+"LocationDetailed"), model.createTypedLiteral(localizacion));

        //Fecha y hora de la semana y dia de la semana...
        Calendar calendar = Calendar.getInstance();
        String dia = Integer.toString(calendar.get(Calendar.DAY_OF_WEEK));
        user.setPropertyValue(model.getProperty(NS+"#"+"DayOfWeek"), model.createTypedLiteral(dia));
        java.util.Date fecha = new Date();
        String date = fecha.toString();
        user.setPropertyValue(model.getProperty(NS+"#"+"DateTimeDescription"), model.createTypedLiteral(date));

        //Preferencias del dispositivo....
        user.setPropertyValue(model.getProperty(NS+"#"+"IndoorActivity"), model.createTypedLiteral(indoor));
        user.setPropertyValue(model.getProperty(NS+"#"+"OutdoorActivity"), model.createTypedLiteral(outdoor));
        user.setPropertyValue(model.getProperty(NS+"#"+"LanguaguePreferences"), model.createTypedLiteral(lenguajes));
        user.setPropertyValue(model.getProperty(NS+"#"+"AreasInterest"), model.createTypedLiteral(areas));

        //Actualizamos la ontologia cargandola con el mismo nombre y ubicacion para no duplicar archivos...
        File file = new File("/Users/Macbook/Desktop/comoderdf.owl");
        //Hay que capturar las Excepciones
        try {
        if (!file.exists()){
                file.createNewFile();
        }
        model.write(new PrintWriter(file));

        } catch (IOException ex) {
                Logger.getLogger(Gestion_Ontologia_Dispositivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarlocdispositivo(String token, String loc){
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

        //Instanciando el usuario en user del dispositivo en la ontologia del mismo y agregando los values con sus respectivos dataproperity...
        OntClass users = model.getOntClass(NS+"#"+"User");
        Individual user = model.createIndividual(NS+"#"+token,users);
        
        //Location..
        user.setPropertyValue(model.getProperty(NS+"#"+"LocationDetailed"), model.createTypedLiteral(loc));
        
        //Actualizamos la ontologia cargandola con el mismo nombre y ubicacion para no duplicar archivos...
        File file = new File("/Users/Macbook/Desktop/comoderdf.owl");
        //Hay que capturar las Excepciones
        try {
        if (!file.exists()){
                file.createNewFile();
        }
        model.write(new PrintWriter(file));

        } catch (IOException ex) {
                Logger.getLogger(Gestion_Ontologia_Dispositivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
