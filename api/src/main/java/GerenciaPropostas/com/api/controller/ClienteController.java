package GerenciaPropostas.com.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.cliente.Cliente;
import GerenciaPropostas.com.api.entities.cliente.ClienteRepository;
import GerenciaPropostas.com.api.entities.cliente.DadosCadastroCliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroCliente json) {
		System.out.println(json.nome());
		System.out.println(json.email());
		System.out.println(json.telefone());
		System.out.println(json.empresa());
		repository.save(new Cliente(json));
	}
}
