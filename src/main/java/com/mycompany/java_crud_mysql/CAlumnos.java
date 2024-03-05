
package com.mycompany.java_crud_mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    
    
    // MÉTODOS
    
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
             
             // asignamos en forma de string a los get's
            //("parámetro de la consulta", donde lo vamos a asignar( ) );
             cs.setString(1, getNombresAlumnos( ) );
             cs.setString(2, getApellidosAlumnos( ) );
             
             // ejecuta y lanza mensaje
             cs.execute( );
             JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo insertar correctamente el alumno. ERROR: "+ e.toString( ) );
        } //  try-catch's end
    } // insertar alumno( )'s end
    
    
    
    
    
    /**
     * 
     * @param paramTablaTotalAlumnos 
     * recibimos un parámetro para la tabla
     * conectamos con la bd
     * creamos un modelo para la tabla
     * ordenamos la tabla 
     * creamos la variable sql y agregamos los indices con add colum a la variable modelo.( osea en el modelo que creamos)
     * le asignamos el modelo con setModel
     * asignamos la consulta a la variable creada anteriormente
     * 
     * creamos un array de 3
     * 
     * try
     * realizamos la conexión
     * ejecutamos el sql
     * recorremos la tabla mientras tenga uno adelante
     * while (rs.next( ) ) {                
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
     *
     * // incorpora este modelo después de recorrer la tabla
            paramTablaTotalAlumnos.setModel(modelo);
     * 
     */
    public void mostrarAlumnos(JTable paramTablaTotalAlumnos) {
        
        CConexion objetoConexion = new CConexion();
        
        // creamos un modelo
        DefaultTableModel modelo = new DefaultTableModel();
        
        // ordenamos la tabla
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        sql = "SELECT * FROM Alumnos;";
        
        String datos[ ] = new String[3];
        Statement st;
        
        try {
            
            // realizo la conexión
            st = objetoConexion.estableceConexion().createStatement();
            // ejucutamos el sql
            ResultSet rs = st.executeQuery(sql);
            
            // recorremos la tabla
            while (rs.next( ) ) {                
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            
            // incorpora este modelo después de recorrer la tabla
            paramTablaTotalAlumnos.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron mostrar los registros" +e.toString( ) );
        } // aqui termina el try-catch
    } // aqui termina el método mostrarAlumnos
    
    
    
    
    
    /**
     * 
     * @param paramTablaAlumnos
     * @param paramId
     * @param paramNombres
     * @param paramApellidos 
     * 
     * 
     * recibe los parámetros de la tabla
     * creamos un contador para obtener la fila que se está seleccionando
     * int fila = paramTablaAlumnos.getSelectedRow( ); verá que fila está seleccionando
     * 
     * try
     * asignamos a las variables con setText ("la tabla: paramTablaAlumnos".getValueAt(fila,"numero de posición").toString() )
     * 
     */
    public void seleccionarAlumnos(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos) {
        
        try {
            // contador para obtener la fila que está seleccionando
            int fila = paramTablaAlumnos.getSelectedRow( );
            
            if (fila >= 0) {
                paramId.setText( (paramTablaAlumnos.getValueAt(fila, 0).toString() ));
                paramNombres.setText( (paramTablaAlumnos.getValueAt(fila, 1).toString() ));
                paramApellidos.setText( (paramTablaAlumnos.getValueAt(fila, 2).toString() ));
                
                return;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erros de selección, ERROR "+e.toString( ) );
        } // aqui termina el try-catch
    } // aqui termina el método seleccionarAlumnos
    
    
    
    
    
    
    /**
     * 
     * @param paramCodigo
     * @param paramNombres
     * @param paramApellidos 
     * 
     * pasamos los parámetros a los set 
     * 
     */
    public void modificarAlumnos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos) {
        
        setCodigo(Integer.parseInt(paramCodigo.getText( ) ) );
        setNombreAlumnos(paramNombres.getText( ) );
        setApellidosAlumnos(paramNombres.getText( ) );
        
        CConexion objetoConexion = new CConexion( );
        
        String consulta = "UPDATE Alumnos SET nombres = '?', apellidos = '?' WHERE id = ?;";
        
        try {
            
            // conectamos la bd y preparamos la consulta
            CallableStatement cs = objetoConexion.estableceConexion( ).prepareCall(consulta);
            
            // asignamos en forma de string a los get's
            //("parámetro de la consulta", donde lo vamos a asignar( ) );
            cs.setString(1, getNombresAlumnos( ) );
            cs.setString(2, getApellidosAlumnos() );
            cs.setString(3, Integer.toString(getCodigo( ) ));
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificación exitosa!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar/actualizar los datos. ERROR "+e.toString( ) );
        } // aqui termina el try-catch
    }// aqui termina el método modificarAlumnos
    
    
    
    
    
    public void eliminarAlumnos(JTextField paramCodigo) {
        
        setCodigo(Integer.parseInt(paramCodigo.getText( ) ) );
        
        CConexion objetoConexion = new CConexion( );
        
        String consulta = "DELETE FROM Alumnos WHERE id = ?;";
        
        try {
            
             // conectamos la bd y preparamos la consulta
            CallableStatement cs = objetoConexion.estableceConexion( ).prepareCall(consulta);
            
            cs.setInt(1, getCodigo( ) );
            cs.execute( );
            
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el alumno");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el alumno ERROR: "+e.toString( ) );
        }
        
        
    }
    
    
    
    
    
    
}
