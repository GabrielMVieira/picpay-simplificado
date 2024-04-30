package jose.gabriel.picpaysimplificado.domain.user;

import java.math.BigDecimal;

public record UserRequestDTO(

        String name,
        String document,
        String email,
        String password,
        UserType userType,
        BigDecimal balance

) {
}
