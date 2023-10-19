package GerenciaPropostas.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import GerenciaPropostas.com.api.entities.cliente.Cliente;
import GerenciaPropostas.com.api.entities.cliente.ClienteRepository;
import GerenciaPropostas.com.api.entities.cliente.DadosAtualizacaoCliente;
import GerenciaPropostas.com.api.entities.cliente.DadosCadastroCliente;
import GerenciaPropostas.com.api.entities.cliente.DadosDetalhamentoCliente;
import GerenciaPropostas.com.api.entities.cliente.DadosListagemCliente;
import GerenciaPropostas.com.api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	TokenService tokenService;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder, @RequestHeader HttpHeaders headers) {
		
		System.out.println("** CADASTRAR CLIENTE ** ");
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		
		if (repository.existsByEmail(dados.email())) {
			return ResponseEntity.badRequest().body("Email de cliente já cadastrado");
		}
		
		var cliente = new Cliente(dados);
		cliente.setUsuario(idUsuario);
				
		repository.save(cliente);
		System.out.println("id cliente cadastrado:" + cliente.getId());
		var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
	}

	@GetMapping("/page")
	public ResponseEntity<Page<DadosListagemCliente>> listarPage(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAll(paginacao).map(DadosListagemCliente::new);
		return ResponseEntity.ok(page);
//		exemplo de query: http://localhost:8080/clientes?size=2 ?sort=crm,desc&size=2&page=1
//		exemplo de query: http://localhost:8080/clientes?sort=nome,desc&size=2&page=1
	}
	
	@GetMapping()
	public ResponseEntity listar(@RequestHeader HttpHeaders headers) {
		System.out.println("** LISTAR CLIENTE ** ");
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
				
		var page = repository.findByUsuario(idUsuario);
		return ResponseEntity.ok(page);

	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados, @RequestHeader HttpHeaders headers) {
		System.out.println("** ATUALIZAR CLIENTE ** ");
		
		if (!repository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Id de cliente inexistente");
		}
		
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		var cliente = repository.getReferenceById(dados.id());
		
		System.out.println("cliente.getUsuario(): "+cliente.getUsuario());
		
		if((cliente.getUsuario()!=idUsuario)) {
			return ResponseEntity.badRequest().body("Cliente não pertence ao usuário que solicitou a requisição");
		}
		
		cliente.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
		System.out.println("** EXCLUIR CLIENTE ** ");
		
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de cliente inexistente");
		}
		
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		var cliente = repository.getReferenceById(id);
		System.out.println("cliente.getUsuario(): "+cliente.getUsuario());
		
		if((cliente.getUsuario()!=idUsuario)) {
			return ResponseEntity.badRequest().body("Cliente não pertence ao usuário que solicitou a requisição");
		}
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
		System.out.println("** DETALHAR CLIENTE ** ");
		
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de cliente inexistente");
		}
		
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		Cliente cliente = repository.getReferenceById(id);
		
		if((cliente.getUsuario()!=idUsuario)) {
			return ResponseEntity.badRequest().body("Cliente não pertence ao usuário que solicitou a requisição");
		}
		
		return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
	}

}
