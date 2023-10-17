package GerenciaPropostas.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.produto.DadosCadastroProduto;
import GerenciaPropostas.com.api.entities.produto.Produto;
import GerenciaPropostas.com.api.entities.produto.ProdutoRepository;
import GerenciaPropostas.com.api.entities.usuario.DadosCadastroUsuario;
import GerenciaPropostas.com.api.entities.usuario.Usuario;
import GerenciaPropostas.com.api.entities.usuario.UsuarioRepository;
import GerenciaPropostas.com.api.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	TokenService tokenService;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /produtos está ativo";
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProduto dados,  @RequestHeader HttpHeaders headers) {
		System.out.println("**CADASTRAR PRODUTO ** ");
		System.out.println("dados: "+dados);
		var authorization = headers.getFirst(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
		System.out.println("*jwt token: " + authorization);
		var idUsuario = tokenService.getId(authorization);
		System.out.println("Id_usuario: "+ idUsuario);
		var produto = new Produto(dados);
		produto.setIdUsuario(Long.parseLong(idUsuario));
		repository.save(produto);
		System.out.println("id cadastrado:"+produto.getId());
		return ResponseEntity.ok().build();
	}

}
