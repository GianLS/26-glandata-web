package br.com.glandata.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.glandata.web.model.Categoria;
import br.com.glandata.web.model.Produto;
import br.com.glandata.web.repository.CategoriaRepository;
import br.com.glandata.web.repository.ProdutoRepository;

@Controller
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	/**
	 * Método que retorna a página com a listagem de todos os produtos.
	 * 
	 * @return Página com listagem de todos os produtos.
	 */
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("produto/index");

		List<Produto> produtos = produtoRepository.findAll();

		model.addObject("produtos", produtos);

		return model;
	}

	/**
	 * Método para carregar a tela de cadastrar um novo produto.
	 * 
	 * @param produto Objeto Produto que será preenchido para ser incluído.
	 * @return Página para o cadastro de um novo produto.
	 */
	@GetMapping("cadastrar")
	public ModelAndView getIncluir(Produto produto) {
		ModelAndView model = new ModelAndView("produto/cadastrar");

		List<Categoria> categorias = categoriaRepository.findAll();

		model.addObject("categorias", categorias);

		return model;
	}

	/**
	 * Método para incluir um novo Produto no banco de dados
	 * 
	 * @param produto  Objeto Produto para ser gravado.
	 * @param result   Objeto resultante da validação feita no Modelo.
	 * @param redirect Objeto que permite o redirecionamento de página.
	 * @return
	 */
	@PostMapping("cadastrar")
	public ModelAndView postIncluir(@Valid Produto produto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return getIncluir(produto);
		}

		produtoRepository.save(produto);

		redirect.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!!");

		return new ModelAndView("redirect:/produtos");
	}

	/**
	 * Método busca os dados de um produto a partir do seu ID
	 * 
	 * @param id ID do produto.
	 * @return Retorna um formulário com os dados do produto informado pelo ID.
	 */
	@GetMapping("{id}/editar")
	public ModelAndView getEditar(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("produto/editar");

		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			return new ModelAndView("home/pages-404");
		}

		List<Categoria> categorias = categoriaRepository.findAll();

		model.addObject("produto", produto.get());
		model.addObject("categorias", categorias);

		return model;
	}

	/**
	 * Realiza a gravação dos dados de um produto já existe, a partir do objeto
	 * recebido.
	 * 
	 * @param produto Objeto do tipo Produto.
	 * @return Retorna para a página de listagem de produtos, exibindo a mensagem de
	 *         confirmação.
	 */
	@PostMapping("editar")
	public ModelAndView postEditar(@Valid Produto produto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("produto/editar");
		}

		produtoRepository.save(produto);

		redirect.addFlashAttribute("mensagem", "Produto alterado com sucesso!!");

		return new ModelAndView("redirect:/produtos");
	}

	/**
	 * Método para deletar um produto a partir do ID informado.
	 * 
	 * @param id ID do produto.
	 * @return Retorna para a página de listagem de produtos, exibindo a mensagem de
	 *         confirmação
	 */
	@PostMapping("deletar")
	public ModelAndView postDeletar(Long id, RedirectAttributes redirect) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			return new ModelAndView("redirect:/erro404");
		}

		produtoRepository.delete(produto.get());

		redirect.addFlashAttribute("mensagem", "Produto excluído com sucesso!!");

		return new ModelAndView("redirect:/produtos");
	}
}
