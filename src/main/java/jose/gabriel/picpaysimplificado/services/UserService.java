package jose.gabriel.picpaysimplificado.services;

import jose.gabriel.picpaysimplificado.domain.user.User;
import jose.gabriel.picpaysimplificado.domain.user.UserRequestDTO;
import jose.gabriel.picpaysimplificado.domain.user.UserResponseDTO;
import jose.gabriel.picpaysimplificado.domain.user.UserType;
import jose.gabriel.picpaysimplificado.infra.exception.CustomException;
import jose.gabriel.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        this.userRepository.save( user);
    }

    public User createUser(UserRequestDTO userDTO) {
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public Page<UserResponseDTO> listUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable).map(UserResponseDTO::new);
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new CustomException("Usuário não encontrado"));
    }

    public boolean validateUser(User payer, BigDecimal amount) throws Exception {

        if(payer.getUserType() == UserType.MERCHANT){
            throw new CustomException("Um usuário lojista não pode realizar transações");
        }

        if(payer.getBalance().compareTo(amount) < 0){
            throw new CustomException("Saldo insuficiente para realizar transações");
        }

        return true;
    }
}
