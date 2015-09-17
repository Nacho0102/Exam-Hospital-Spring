/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;

/**
 *
 * @author NachoP
 */
public class EmpleadosHospital {
    private Hospital hospital;
    private ArrayList<String> plantilla;
    private ArrayList<String> doctores;

    
    
    
    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public ArrayList<String> getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(ArrayList<String> plantilla) {
        this.plantilla = plantilla;
    }

    public ArrayList<String> getDoctores() {
        return doctores;
    }

    public void setDoctores(ArrayList<String> doctores) {
        this.doctores = doctores;
    }

    
    
}
