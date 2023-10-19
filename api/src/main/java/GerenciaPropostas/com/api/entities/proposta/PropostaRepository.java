package GerenciaPropostas.com.api.entities.proposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	List<Proposta> findByUsuario(Long idUsuario);

}
