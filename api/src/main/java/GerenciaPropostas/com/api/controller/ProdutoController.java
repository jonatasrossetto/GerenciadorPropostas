package GerenciaPropostas.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.produto.DadosCadastroProduto;
import GerenciaPropostas.com.api.entities.produto.Produto;
import GerenciaPropostas.com.api.entities.produto.ProdutoRepository;
import GerenciaPropostas.com.api.entities.usuario.DadosCadastroUsuario;
import GerenciaPropostas.com.api.entities.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /produtos está ativo";
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProduto dados) {
		System.out.println("**CADASTRAR PRODUTO ** ");
		System.out.println("dados: "+dados);
		var produto = new Produto(dados);
		repository.save(produto);
		System.out.println("id cadastrado:"+produto.getId());
		return ResponseEntity.ok().build();
	}

}
