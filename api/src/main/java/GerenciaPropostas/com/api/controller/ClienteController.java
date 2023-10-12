package GerenciaPropostas.com.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
		var cliente = new Cliente(dados);
		repository.save(cliente);
		System.out.println("id cadastrado:"+cliente.getId());
		var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemCliente>> listar(@PageableDefault(size = 10,
			sort = {"nome"}) Pageable paginacao) {
		var page =repository.findAll(paginacao).map(DadosListagemCliente::new); 
		return ResponseEntity.ok(page);
//		exemplo de query: http://localhost:8080/clientes?size=2 ?sort=crm,desc&size=2&page=1
//		exemplo de query: http://localhost:8080/clientes?sort=nome,desc&size=2&page=1
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
		var cliente = repository.getReferenceById(dados.id());
		cliente.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		System.out.println("existe o id:"+id+"? "+repository.existsById(id));
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		Cliente cliente = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
	}
	
}
