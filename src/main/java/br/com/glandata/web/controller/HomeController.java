package br.com.glandata.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class HomeController {

	private static String codigoPagina = "HS-001";

	@GetMapping()
	public ModelAndView start() {
		ModelAndView mav = new ModelAndView("home/pages-starter");
		mav.addObject("codigoPagina", codigoPagina);

		return mav;
	}

	@GetMapping("erro404")
	public ModelAndView erro404() {
		return new ModelAndView("home/pages-404");
	}

}
