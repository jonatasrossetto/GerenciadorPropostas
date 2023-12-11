package GerenciaPropostas.com.api.entities.itemDaProposta;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record DadosCadastroItemDaProposta(
long proposta,
long produto,
long quantidade,
BigDecimal valorUnitario,
long prazoEntrega,
Date dataCriacao
) {

}
