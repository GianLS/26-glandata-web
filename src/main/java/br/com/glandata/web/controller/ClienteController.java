package br.com.glandata.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.glandata.web.model.Cliente;
import br.com.glandata.web.repository.ClienteRepository;

@Controller
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("")
	public ModelAndView index() {
		return new ModelAndView("cliente/index");
	}

	@GetMapping("cadastrar")
	public ModelAndView getIncluir(Cliente cliente) {
		return new ModelAndView("cliente/cadastrar");
	}

	@PostMapping("cadastrar")
	public ModelAndView postIncluir(@Valid Cliente cliente, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("cliente/cadastrar");
		}
		
		clienteRepository.save(cliente);
		
		redirect.addFlashAttribute("mensagem", "Cliente cadastrado com Sucesso");
		return new ModelAndView("redirect:/clientes");
	}

	/**
	 * Método busca os dados de um cliente a partir do seu ID
	 * 
	 * @param id ID do cliente.
	 * @return Retorna um formulário com os dados do cliente informado pelo ID.
	 */
	@GetMapping("editar")
	public ModelAndView getEditar(Long id) {
		return new ModelAndView("cliente/editar");
	}

	/**
	 * Realiza a gravação dos dados de um cliente já existe, a partir do objeto
	 * recebido.
	 * 
	 * @param cliente Objeto do tipo Cliente.
	 * @return Retorna para a página de listagem de clientes, exibindo a mensagem de
	 *         confirmação.
	 */
	@PostMapping("editar")
	public ModelAndView postEditar(Cliente cliente) {
		return new ModelAndView("cliente/index");
	}

	/**
	 * Método para deletar um cliente a partir do ID informado.
	 * 
	 * @param id ID do cliente.
	 * @return Retorna para a página de listagem de clientes, exibindo a mensagem de
	 *         confirmação
	 */
	@PostMapping("deletar")
	public ModelAndView postDeletar(Long id) {
		return new ModelAndView("cliente/deletar");
	}
}
