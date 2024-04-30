package jose.gabriel.picpaysimplificado.domain.transaction;

import jose.gabriel.picpaysimplificado.domain.user.UserResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long id,
        BigDecimal amount,
        UserResponseDTO payer,
        UserResponseDTO payee,
        LocalDateTime transactionTime
) {
    public TransactionResponseDTO(Transaction transaction) {
        this(transaction.getId(),
                transaction.getAmount(),
                new UserResponseDTO(transaction.getPayer()),
                new UserResponseDTO(transaction.getPayee()),
                transaction.getTransactionTime());
    }
}
