
package com.mycompany.java_crud_mysql;

import java.sql.CallableStatement;
import javax.swing.JOptionPane;
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
    
    /**
     * insertarAlumno(JTextField paramNombres, JTextField paramApellidos);
     * 
     * @param paramNombres
     * @param paramApellidos
     * recibe los nombres y apellidos de los textField del formulario
     * instancia una nueva conexion y crea la consulta.
     * 
     * try
     * se conecta con la bd y hace la consulta
     * Obtiene nombre y apellido en Strings en los métodos get.
     * 
     * ejecuta y lanza un mensaje.
     */
    public void insertarAlumno(JTextField paramNombres, JTextField paramApellidos) {
         setNombreAlumnos(paramNombres.getText( ) );
         setApellidosAlumnos(paramApellidos.getText( ) );
         
         CConexion objetoConexion = new CConexion();
         
         String consulta = ("INSERT INTO Alumnos(nombres, apellidos)\n" +
                                            "VALUES (?,?);")    ;
         
         try {
            // se conecta con la bd y hace la consulta
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             
             // Obtiene nombre y apellido en Strings en los métodos get.
                // 1 = primer parámetro  = ?
                // 2 = segundo parámetro = ?
             cs.setString(1, getNombresAlumnos( ) );
             cs.setString(2, getApellidosAlumnos( ) );
             
             // ejecuta y lanza mensaje
             cs.execute( );
             JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo insertar correctamente el alumno. ERROR: "+ e.toString( ) );
        } //  try-catch's end
    } // insertar alumno( )'s end
    
    
    
    
    
    
}
