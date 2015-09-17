
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*

Procedimiento Empleado    

    CREATE OR REPLACE PROCEDURE RESUMEN_HOSPITAL
    (P_HOSPITALCOD HOSPITAL.HOSPITAL_COD%TYPE
    , P_NOMBRE OUT HOSPITAL.NOMBRE%TYPE
    , P_PLANTILLA OUT INT
    , P_DOCTORES OUT INT
    , P_SUMA OUT INT
    , P_EMPLEADOSHOSPITAL OUT INT)
    AS
      V_SALARIO_DOCTOR INT;
      V_SALARIO_PLANTILLA INT;
    BEGIN
      SELECT NOMBRE INTO P_NOMBRE FROM HOSPITAL 
      WHERE HOSPITAL_COD = P_HOSPITALCOD;
      SELECT COUNT(EMPLEADO_NO), SUM(SALARIO)
      INTO P_PLANTILLA, V_SALARIO_PLANTILLA
      FROM PLANTILLA
      WHERE HOSPITAL_COD = P_HOSPITALCOD;
      SELECT COUNT(DOCTOR_NO), SUM(SALARIO)
      INTO P_DOCTORES, V_SALARIO_DOCTOR  
      FROM DOCTOR
      WHERE HOSPITAL_COD = P_HOSPITALCOD; 
      P_SUMA :=  V_SALARIO_DOCTOR + V_SALARIO_PLANTILLA;
      P_EMPLEADOSHOSPITAL := P_DOCTORES + P_PLANTILLA;
    END; 
*/
     
package modelos;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import objetos.Hospital;
import objetos.ResumenHospital;
import oracle.jdbc.OracleDriver;


/**
 *
 * @author NachoP
 */
public class ModeloHospital 
{
    Connection cn;
    CachedRowSet rs;
    
    private void conectarOracle() throws SQLException
    {
        DriverManager.registerDriver(new OracleDriver());
        String cadenaconexion = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        this.rs = RowSetProvider.newFactory().createCachedRowSet();
        this.rs.setUrl(cadenaconexion);
        this.rs.setUsername("SYSTEM");
        this.rs.setPassword("java");
        this.cn = DriverManager.getConnection(cadenaconexion
         ,"SYSTEM", "java");
    }
    
    public ArrayList<Hospital> getHospitales() throws SQLException
    {
        this.conectarOracle();
        String consulta = "SELECT * FROM HOSPITAL";
        this.rs.setCommand(consulta);
        this.rs.execute();
        this.rs.beforeFirst();
        ArrayList<Hospital> lista = new ArrayList();
        while (this.rs.next())
        {
            Hospital h = new Hospital();
            h.setHospitalcod(this.rs.getInt("HOSPITAL_COD"));
            h.setNombre(this.rs.getString("NOMBRE"));
            h.setDireccion(this.rs.getString("DIRECCION"));
            h.setTelefono(this.rs.getString("TELEFONO"));            
            lista.add(h);
        }
        return lista;
    } 
     

    public ResumenHospital getResumenHospital(int hospitalcod) throws SQLException
    {
        this.conectarOracle();
        String consulta = "{ CALL RESUMEN_HOSPITAL (?,?,?,?,?,?) }";
        //Codigo del hospital, Nombre del hospital,personas en la Plantilla,
        //numero de Doctores,suma salarial
        //número de empleados
        
        CallableStatement cst = this.cn.prepareCall(consulta);
        cst.setInt(1, hospitalcod);
        cst.registerOutParameter(2, java.sql.Types.VARCHAR); 
        cst.registerOutParameter(3, java.sql.Types.INTEGER); 
        cst.registerOutParameter(4, java.sql.Types.INTEGER);         
        cst.registerOutParameter(5, java.sql.Types.INTEGER);         
        cst.registerOutParameter(6, java.sql.Types.INTEGER);         
        cst.execute();
        
        String nombre = cst.getString(2);
        int personasplantilla = cst.getInt(3);
        int personasdoctores = cst.getInt(4);
        int sumasalarial = cst.getInt(5);
        int numeropersonas = cst.getInt(6);
        
        ResumenHospital resumen = new ResumenHospital();
        resumen.setNombreHospital(nombre);
        resumen.setPersonasPlantilla(personasplantilla);
        resumen.setPersonasDoctor(personasdoctores);
        resumen.setSumaSalarial(sumasalarial);
        resumen.setEmpleadosHospital(numeropersonas);
        return resumen;
    }
 
   public Hospital getHospital(int hospitalcod) throws SQLException
    {
        this.conectarOracle();
        String consulta = "SELECT * FROM HOSPITAL WHERE HOSPITAL_COD=?";
        this.rs.setCommand(consulta);
        this.rs.setInt(1, hospitalcod);
        this.rs.execute();
        this.rs.beforeFirst();
        
        if (this.rs.next())
        {
            Hospital h = new Hospital();
            h.setHospitalcod(this.rs.getInt("HOSPITAL_COD"));
            h.setNombre(this.rs.getString("NOMBRE"));
            h.setDireccion(this.rs.getString("DIRECCION"));
            h.setTelefono(this.rs.getString("TELEFONO"));
            h.setCamas(this.rs.getInt("NUM_CAMA"));
            return h;
        }else{
            return null;
        }
        
    }      
    
    public ArrayList<String> getPlantillaHospital(int hospitalcod) throws SQLException
    {
        this.conectarOracle();
        String consulta = "SELECT * FROM PLANTILLA WHERE HOSPITAL_COD=?";
        this.rs.setCommand(consulta);
        this.rs.setInt(1, hospitalcod);
        this.rs.execute();
        this.rs.beforeFirst();
        ArrayList<String> lista = new ArrayList();
        while (this.rs.next())
        {
            lista.add(this.rs.getString("APELLIDO"));
        }
        return lista;
    }     
     
    public ArrayList<String> getDoctoresHospital(int hospitalcod) throws SQLException
    {
        this.conectarOracle();
        String consulta = "SELECT * FROM DOCTOR WHERE HOSPITAL_COD=?";
        this.rs.setCommand(consulta);
        this.rs.setInt(1, hospitalcod);
        this.rs.execute();
        this.rs.beforeFirst();
        ArrayList<String> lista = new ArrayList();
        while (this.rs.next())
        {
            lista.add(this.rs.getString("APELLIDO"));
        }
        return lista;
    }      
}