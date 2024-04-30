package jose.gabriel.picpaysimplificado.domain.user;

import java.math.BigDecimal;

public record UserResponseDTO(

        Long id,
        String name,
        String email,
        BigDecimal balance,
        UserType userType

) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getBalance(), user.getUserType());
    }
}
