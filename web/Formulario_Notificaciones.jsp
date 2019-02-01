<%-- 
    Document   : Formulario_Notificaciones
    Created on : 08-nov-2016, 10:36:26
    Author     : Macbook
--%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/form-basic.css">

        <title>Formulario Notificaciones COMoDE</title>
    </head>
    <body>
        <div class="main-content">
            <form id="formulario" method="post" class="form-basic" action="Gestion_Notificacion">
                <div class="form-title-row">
                    <h1>Envio de Notificaciones COMoDE</h1>
                </div>

                <div class="form-row">
                    <label>
                        <span>TITULO</span>
                        <input type="text" name="titulo" id="titulo">
                    </label>
                </div>

                <div class="form-row">
                    <label>
                        <span>MENSAJE:</span>
                        <input type="text" name="mensaje" id="mensaje">
                    </label>
                </div>
                <br>
                <br>
                <br>

                <div class="form-row">
                    <label>
                        <center>
                            <span>PREFERENCIAS</span>   
                        </center>
                    </label>
                </div>

                <label>
                    <span>LUGAR</span>                       
                </label>     
                
                <div class="form-row">                    
                        <input type="checkbox" name="guzman" value="Ciudad Guzman,Jalisco,México"> Ciudad Guzman,Jalisco,México
                        <input type="checkbox" name="tamazula" value="Tamazula de Gordiano,Jalisco,México"> Tamazula de Gordiano,Jalisco,México<br>
                        <input type="checkbox" name="teca" value="Tecalitlán,Jalisco,México"> Tecalitlán,Jalisco,México
                        <input type="checkbox" name="sayula" value="Sayula,Jalisco,México"> Sayula,Jalisco,México<br>
                        <input type="checkbox" name="gdl" value="Guadalajara,Jalisco,México"> Guadalajara,Jalisco,México
                        <input type="checkbox" name="zapo" value="Zapotiltic,Jalisco,México"> Zapotiltic,Jalisco,México<br>
                        <input type="checkbox" name="tapalpa" value="Tapalpa,Jalisco,México"> Tapalpa,Jalisco,México
                        <input type="checkbox" name="mazamitla" value="Mazamitla,Jalisco,México"> Mazamitla,Jalisco,México                       
                </div>

                <label>
                    <span>LENGUAJES </span>                           
                </label>
                
                <div class="form-row">                  
                        <input type="checkbox" name="espaniol" value="Español"> Español
                        <input type="checkbox" name="ingles" value="Ingles"> Ingles
                </div>
             
                <label>
                    <span>ACTIVIDADES DE INTERIOR </span>
                </label>
                
                <div class="form-row">
                        <input type="checkbox" name="juegosmesa" value="Juegos de Mesa"> Juegos de Mesa
                        <input type="checkbox" name="cine" value="Cine"> Cine
                        <input type="checkbox" name="museo" value="Museo"> Museo
                        <input type="checkbox" name="literatura" value="Literatura"> Literatura<br>
                        <input type="checkbox" name="videojuegos" value="Video Juegos"> Video Juegos
                        <input type="checkbox" name="restaurante" value="Restaurante"> Restaurante
                </div>
                
           
                    <label>
                        <span>ACTIVIDADES DE EXTERIOR </span><br>
                    </label>
                
                <div class="form-row">
                        <input type="checkbox" name="picnic" value="Picnic"> Picnic               
                        <input type="checkbox" name="parque" value="Parque"> Parque
                        <input type="checkbox" name="deporte" value="Deporte"> Deporte
                        <input type="checkbox" name="turismo" value="Turismo"> Turismo<br>
                        <input type="checkbox" name="arte" value="Arte"> Arte
                        <input type="checkbox" name="caminar" value="Caminar"> Caminar
                </div>
                
       
                <label>
                    <span>AREAS DE INTERES </span>                       
                </label>
                
                 <div class="form-row">                                         
                        <input type="checkbox" name="salud" value="Salud"> Salud
                        <input type="checkbox" name="computacion" value="Computacion"> Computacion
                        <input type="checkbox" name="tecnologia" value="Tecnologia"> Tecnologia
                        <input type="checkbox" name="naturaleza" value="Naturaleza"> Naturaleza
                        <input type="checkbox" name="sociales" value="Sociales"> Sociales<br>
                        <input type="checkbox" name="humanidades" value="Humanidades"> Humanidades
                        <input type="checkbox" name="economicas" value="Economicas"> Economicas
                        <input type="checkbox" name="admin" value="Administrativas"> Administrativas<br>
                        <input type="checkbox" name="artes" value="Artes"> Artes
                        <input type="checkbox" name="exactas" value="Exactas"> Exactas
                </div>

                <div class="form-row">
                    <label>
                        <span>Envio: </span>
                        <select name="condicion">
                            <option>Todos</option>
                            <option>Preferencias</option>
                        </select>
                    </label>
                </div>

                <div class="form-row">
                    <button type="submit">Enviar Notificacion</button>
                </div>
            </form>
        </div>
    </body>
</html>
