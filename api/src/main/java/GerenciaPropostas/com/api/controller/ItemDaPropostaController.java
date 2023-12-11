package GerenciaPropostas.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.itemDaProposta.ItemDaPropostaRepository;
import GerenciaPropostas.com.api.infra.security.TokenService;

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
