package br.com.quiver.gildo.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.quiver.gildo.system.model.Time;
import br.com.quiver.gildo.system.service.ConvertTimeService;

/**
 * Controlador da index, reponspavel por receber as rquisição es encaminhar as repostas daa view
 * @author gildo_cordeiro
 *
 */
@Controller
public class IndexController {

	ModelAndView model;
	
	@Autowired
	ConvertTimeService convert;

	@GetMapping(value = "/")
	public ModelAndView index(){
		model = new ModelAndView("index.html");
		model.addObject("time", new Time());
		model.addObject("listTime", convert.getTimeFormat());
		return model;
	}
	
	@PostMapping(value = "/convert")	
	public ModelAndView save(@ModelAttribute(value = "time") Time time) {
		convert.calcular(time);
			
		model = new ModelAndView("redirect:/");
		return model;
	}
}
