package GerenciaPropostas.com.api.entities.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByUsuario(Long usuario);
	
}
