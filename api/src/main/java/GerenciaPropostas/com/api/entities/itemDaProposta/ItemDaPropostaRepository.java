package GerenciaPropostas.com.api.entities.itemDaProposta;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemDaPropostaRepository extends JpaRepository<ItemDaProposta, Long> {
	
	List<ItemDaProposta> findByProposta(Long idProposta);
	
}
