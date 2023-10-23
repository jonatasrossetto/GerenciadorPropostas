package GerenciaPropostas.com.api.entities.observacaoDaProposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservacaoDaPropostaRepository extends JpaRepository<ObservacaoDaProposta, Long> {

	List<ObservacaoDaProposta> findByIdproposta(Long id);
	
}
