package GerenciaPropostas.com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.cliente.Cliente;
import GerenciaPropostas.com.api.entities.itemDaProposta.DadosCadastroItemDaProposta;
import GerenciaPropostas.com.api.entities.itemDaProposta.ItemDaProposta;
import GerenciaPropostas.com.api.entities.itemDaProposta.ItemDaPropostaRepository;
import GerenciaPropostas.com.api.entities.proposta.DadosCadastroProposta;
import GerenciaPropostas.com.api.entities.proposta.Proposta;
import GerenciaPropostas.com.api.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/itemDaProposta")
public class ItemDaPropostaController {

	@Autowired
	TokenService tokenService;
	
	@Autowired
	ItemDaPropostaRepository repository;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /itemDaProposta está ativo";
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody List<DadosCadastroItemDaProposta> dados,  @RequestHeader HttpHeaders headers) {
		System.out.println("**CADASTRAR ITENS DA PROPOSTA ** ");
		var idUsuario = Long.parseLong( tokenService.getIdUsuarioHeader(headers) );
		System.out.println("Id_usuario: "+ idUsuario);
		for(DadosCadastroItemDaProposta item : dados) {
			System.out.println(item);
			var itemDaProposta = new ItemDaProposta(item);
			repository.save(itemDaProposta);
			System.out.println("id do item da proposta cadastrada:"+itemDaProposta.getId());
		}

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idProposta}")
	public ResponseEntity listarItensDaProposta(@PathVariable Long idProposta, @RequestHeader HttpHeaders headers) {
		System.out.println("**LISTAR ITENS DA PROPOSTA ** ");
		var idUsuario = Long.parseLong(tokenService.getIdUsuarioHeader(headers));
		System.out.println("Id_usuario: "+ idUsuario);
		System.out.println("Id_proposta: "+idProposta);
		var page = repository.findByProposta(idProposta);
		return ResponseEntity.ok(page);
	}
	
}
