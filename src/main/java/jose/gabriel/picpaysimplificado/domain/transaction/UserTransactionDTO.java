package jose.gabriel.picpaysimplificado.domain.transaction;

import jose.gabriel.picpaysimplificado.domain.user.UserResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserTransactionDTO(
        BigDecimal amount,
        UserResponseDTO counterParty,
        LocalDateTime transactionTime
) {
    public UserTransactionDTO(Transaction transaction, Long userId) {
        this(transaction.getAmount(),
                userId.equals(transaction.getPayer().getId()) ? new UserResponseDTO(transaction.getPayee()) : new UserResponseDTO(transaction.getPayer()),
                transaction.getTransactionTime());
    }
}
