package GerenciaPropostas.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import GerenciaPropostas.com.api.entities.cliente.Cliente;
import GerenciaPropostas.com.api.entities.observacaoDaProposta.DadosAtualizacaoObservacaoDaProposta;
import GerenciaPropostas.com.api.entities.observacaoDaProposta.DadosCadastroObservacaoDaProposta;
import GerenciaPropostas.com.api.entities.observacaoDaProposta.DadosDetalhamentoObservacaoDaProposta;
import GerenciaPropostas.com.api.entities.observacaoDaProposta.ObservacaoDaProposta;
import GerenciaPropostas.com.api.entities.observacaoDaProposta.ObservacaoDaPropostaRepository;
import GerenciaPropostas.com.api.entities.proposta.DadosAtualizacaoProposta;
import GerenciaPropostas.com.api.entities.proposta.DadosCadastroProposta;
import GerenciaPropostas.com.api.entities.proposta.DadosDetalhamentoProposta;
import GerenciaPropostas.com.api.entities.proposta.Proposta;
import GerenciaPropostas.com.api.entities.proposta.PropostaRepository;
import GerenciaPropostas.com.api.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/observacaoDaProposta")
public class ObservacaoDaPropostaController {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	ObservacaoDaPropostaRepository repository;
	
	@Autowired
	PropostaRepository propostaRepository;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /observacaoDaProposta está ativo";
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroObservacaoDaProposta dados,  @RequestHeader HttpHeaders headers) {
		System.out.println("**CADASTRAR OBSERVAÇÃO DA PROPOSTA ** ");
		
		//recupera o id do usuário que está fazendo a requisição
		var idUsuario = Long.parseLong( tokenService.getIdUsuarioHeader(headers) );
		System.out.println("Id_usuario: "+ idUsuario);

		//verifica se a proposta existe no banco de dados
		if (!propostaRepository.existsById(dados.idproposta())) {
			return ResponseEntity.badRequest().body("A proposta não existe");
		}
		
		// verifica se o usuário que enviou a requisição é responsável pela proposta
		Proposta proposta = propostaRepository.getReferenceById(dados.idproposta());
		if (proposta.getUsuario()!=idUsuario) {
			return ResponseEntity.badRequest().body("O usuário da requisição não é responsável pela proposta informada");
		}
		
		var observacao = new ObservacaoDaProposta(dados);
		repository.save(observacao);
		System.out.println("id da proposta cadastrada:"+proposta.getId());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
		System.out.println("** DETALHAR OBSERVAÇÃO DA PROPOSTA **");
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de observação inexistente");
		}
		
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		
		var observacao = repository.getReferenceById(id);
		var idProposta = observacao.getIdproposta();
		System.out.println("idProposta: "+idProposta);

		// verifica se o usuário que enviou a requisição é responsável pela proposta
		Proposta proposta = propostaRepository.getReferenceById(idProposta);
		System.out.println("proposta.getUsuario(): "+proposta.getUsuario());
		if (proposta.getUsuario()!=idUsuario) {
			return ResponseEntity.badRequest().body("O usuário da requisição não é responsável pela observação informada");
		}
				
		return ResponseEntity.ok(new DadosDetalhamentoObservacaoDaProposta(observacao));
	}

	@GetMapping("/listar/{idProposta}")
	public ResponseEntity listar(@PathVariable Long idProposta, @RequestHeader HttpHeaders headers) {

		System.out.println("**LISTAR OBSERVAÇÕES DA PROPOSTA INFORMADA ** ");
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		System.out.println("Id da proposta solicitada: "+idProposta);
		
		//verifica se a proposta existe no banco de dados
		if (!propostaRepository.existsById(idProposta)) {
			return ResponseEntity.badRequest().body("A proposta não existe");
		}
		
		// verifica se o usuário que enviou a requisição é responsável pela proposta
		Proposta proposta = propostaRepository.getReferenceById(idProposta);
		System.out.println("proposta.getUsuario(): "+proposta.getUsuario());
		if (proposta.getUsuario()!=idUsuario) {
			return ResponseEntity.badRequest().body("O usuário da requisição não é responsável pela observação informada");
		}

		
		
		var page = repository.findByIdproposta(idProposta);
		return ResponseEntity.ok(page);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id , @RequestHeader HttpHeaders headers) {
		System.out.println("** APAGAR OBSERVAÇÃO ** ");
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de observação inexistente");
		}
		
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		
		var observacao = repository.getReferenceById(id);
		var idProposta = observacao.getIdproposta();
		System.out.println("idProposta: "+idProposta);

		// verifica se o usuário que enviou a requisição é responsável pela proposta
		Proposta proposta = propostaRepository.getReferenceById(idProposta);
		System.out.println("proposta.getUsuario(): "+proposta.getUsuario());
		if (proposta.getUsuario()!=idUsuario) {
			return ResponseEntity.badRequest().body("O usuário da requisição não é responsável pela observação informada");
		}
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoObservacaoDaProposta dados, @RequestHeader HttpHeaders headers) {
		System.out.println("** ATUALIZAR OBSERVAÇÃO ** ");
		
		if (!repository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Id de observação inexistente");
		}
		
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		
		var observacao = repository.getReferenceById(dados.id());
		var idProposta = observacao.getIdproposta();
		System.out.println("idProposta: "+idProposta);

		// verifica se o usuário que enviou a requisição é responsável pela proposta
		Proposta proposta = propostaRepository.getReferenceById(idProposta);
		System.out.println("proposta.getUsuario(): "+proposta.getUsuario());
		if (proposta.getUsuario()!=idUsuario) {
			return ResponseEntity.badRequest().body("O usuário da requisição não é responsável pela observação informada");
		}
		
		//verificar correspondência entre observação e proposta antes da atualização
		if(observacao.getIdproposta()!=dados.idproposta()) {
			return ResponseEntity.badRequest().body("Inconsistência entre idObservação e idProposta com o cadastro");
		}
		
		observacao.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoObservacaoDaProposta(observacao));
	}
	
}
