package GerenciaPropostas.com.api.entities.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByUsuario(Long usuario);
	Boolean existsByEmail(String email);
}
