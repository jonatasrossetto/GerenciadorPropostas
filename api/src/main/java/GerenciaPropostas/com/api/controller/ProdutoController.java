package GerenciaPropostas.com.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /produtos está ativo";
	}

}
