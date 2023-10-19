package GerenciaPropostas.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.infra.security.TokenService;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	@Autowired
	TokenService tokenService;
	
	@GetMapping("/hello")
	public String Hello(@RequestHeader HttpHeaders headers) {
		System.out.println("** HELLO PROPOSTAS ** ");
		var idUsuario = tokenService.getIdUsuarioHeader(headers);
		System.out.println("Id_usuario: "+ idUsuario);
		return "O endpoint /propostas está ativo. Acessado pelo usuario id: "+idUsuario;
	}
	
	

}
