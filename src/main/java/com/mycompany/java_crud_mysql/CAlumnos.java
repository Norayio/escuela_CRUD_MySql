
package com.mycompany.java_crud_mysql;

import java.sql.CallableStatement;
import javax.swing.JTextField;

/**
 *
 * @author Norayio
 */

public class CAlumnos {
    
    int codigo;
    String nombreAlumnos;
    String apellidosAlumnos;
    
    // getters & setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombresAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }
    
    
    // métodos
    public void insertarAlumno(JTextField paramNombres, JTextField paramApellidos) {
        
         setNombreAlumnos(paramNombres.getText( ) );
         setApellidosAlumnos(paramApellidos.getText( ) );
         
         CConexion objetoConexion = new CConexion();
         
         String consulta = ("INSERT INTO Alumnos(nombres, apellidos)\n" +
                                            "VALUES (?,?);")    ;
         
         try {
            // se conecta con la bd y hace la consulta
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             
             // guarda y obtiene nombre y apellido en Strings en los métodos get.
                // 1 = primer parámetro
                // 2 = segundo parámetro
             cs.setString(1, getNombresAlumnos( ) );
             cs.setString(2, getApellidosAlumnos( ) );
             
             // ejecuta
             cs.execute( );
             
        } catch (Exception e) {
            
        }
         
    } // insertar alumno's end
    
}
