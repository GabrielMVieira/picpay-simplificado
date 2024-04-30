package jose.gabriel.picpaysimplificado.domain.transaction;

import java.math.BigDecimal;

public record TransactionRequestDTO(

        BigDecimal amount,
        Long payerId,
        Long payeeId

        ) {
}
