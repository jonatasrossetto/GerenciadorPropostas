package GerenciaPropostas.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.cliente.Cliente;
import GerenciaPropostas.com.api.entities.cliente.ClienteRepository;
import GerenciaPropostas.com.api.entities.produto.DadosDetalhamentoProduto;
import GerenciaPropostas.com.api.entities.proposta.DadosCadastroProposta;
import GerenciaPropostas.com.api.entities.proposta.DadosDetalhamentoProposta;
import GerenciaPropostas.com.api.entities.proposta.Proposta;
import GerenciaPropostas.com.api.entities.proposta.PropostaRepository;
import GerenciaPropostas.com.api.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	PropostaRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/hello")
	public String Hello(@RequestHeader HttpHeaders headers) {
		System.out.println("** HELLO PROPOSTAS ** ");
		var idUsuario = tokenService.getIdUsuarioHeader(headers);
		System.out.println("Id_usuario: "+ idUsuario);
		return "O endpoint /propostas está ativo. Acessado pelo usuario id: "+idUsuario;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProposta dados,  @RequestHeader HttpHeaders headers) {
		System.out.println("**CADASTRAR PROPOSTA ** ");
		var idUsuario = Long.parseLong( tokenService.getIdUsuarioHeader(headers) );
		System.out.println("Id_usuario: "+ idUsuario);
		
		Cliente cliente = clienteRepository.getReferenceById(dados.cliente());
		if (cliente.getUsuario()!=idUsuario) {
			return ResponseEntity.badRequest().body("O usuário da requisição não é responsável pelo cliente informado");
		}
		var proposta = new Proposta(dados);
		proposta.setUsuario(idUsuario);
		repository.save(proposta);
		System.out.println("id da proposta cadastrada:"+proposta.getId());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity listar(@RequestHeader HttpHeaders headers) {
		//!!!!!!!!
		//precisa verificar como paginar o retorno da listagem
		//https://www.baeldung.com/spring-data-jpa-convert-list-page
		//!!!!!!!!
		System.out.println("**LISTAR PROPOSTAS ** ");
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		var page = repository.findByUsuario(idUsuario);
		return ResponseEntity.ok(page);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id , @RequestHeader HttpHeaders headers) {
		System.out.println("** APAGAR PROPOSTA ** ");
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de proposta inexistente");
		}
		
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		var proposta = repository.getReferenceById(id);
		System.out.println("proposta.getUsuario(): "+proposta.getUsuario());
		
		if((proposta.getUsuario()!=idUsuario)) {
			return ResponseEntity.badRequest().body("Proposta não pertence ao usuário que solicitou a requisição");
		}
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
		System.out.println("** DETALHAR PROPOSTA **");
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de proposta inexistente");
		}
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		var proposta = repository.getReferenceById(id);
		System.out.println("proposta.getUsuario(): "+proposta.getUsuario());
		if((proposta.getUsuario()!=idUsuario)) {
			return ResponseEntity.badRequest().body("Proposta não pertence ao usuário que solicitou a requisição");
		}
		
		return ResponseEntity.ok(new DadosDetalhamentoProposta(proposta));
	}
	
}
