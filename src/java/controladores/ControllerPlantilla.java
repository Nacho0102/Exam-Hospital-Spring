/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloHospital;
import objetos.EmpleadosHospital;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author NachoP
 */
public class ControllerPlantilla implements Controller{

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("WebPlantillaHospital");
        int hospitalcod = Integer.parseInt(hsr.getParameter("hospitalcod"));
        ModeloHospital modelo = new ModeloHospital();
        EmpleadosHospital emp = new EmpleadosHospital();
        emp.setHospital(modelo.getHospital(hospitalcod));
        emp.setDoctores(modelo.getDoctoresHospital(hospitalcod));
        emp.setPlantilla(modelo.getPlantillaHospital(hospitalcod));
        mv.addObject("empleados", emp);
        return mv;
    }
    
    
}
