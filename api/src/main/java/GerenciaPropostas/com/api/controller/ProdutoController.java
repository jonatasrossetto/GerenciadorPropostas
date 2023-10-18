package GerenciaPropostas.com.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.cliente.DadosListagemCliente;
import GerenciaPropostas.com.api.entities.produto.DadosCadastroProduto;
import GerenciaPropostas.com.api.entities.produto.DadosListagemProduto;
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
		var idUsuario = tokenService.getIdUsuarioHeader(headers);
		System.out.println("Id_usuario: "+ idUsuario);
		Usuario usuario =  usuarioRepository.getReferenceById(Long.parseLong(idUsuario));
		System.out.println("login: "+usuario.getLogin());
		var produto = new Produto(dados);
		produto.setIdUsuario(Long.parseLong(idUsuario));
		repository.save(produto);
		System.out.println("id cadastrado:"+produto.getId());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemProduto>> listar(
			@PageableDefault(size = 10, sort = { "descricao" }) Pageable paginacao) {
		var page = repository.findAll(paginacao).map(DadosListagemProduto::new);
		return ResponseEntity.ok(page);
//		exemplo de query: http://localhost:8080/clientes?size=2 ?sort=crm,desc&size=2&page=1
//		exemplo de query: http://localhost:8080/clientes?sort=nome,desc&size=2&page=1
	}

}
