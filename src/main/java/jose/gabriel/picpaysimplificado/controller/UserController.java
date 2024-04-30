package jose.gabriel.picpaysimplificado.controller;

import jose.gabriel.picpaysimplificado.domain.user.User;
import jose.gabriel.picpaysimplificado.domain.user.UserRequestDTO;
import jose.gabriel.picpaysimplificado.domain.user.UserResponseDTO;

import jose.gabriel.picpaysimplificado.services.TransactionService;
import jose.gabriel.picpaysimplificado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO, UriComponentsBuilder uri) {
        User user = userService.createUser(userDTO);
        URI location = uri.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(new UserResponseDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) throws Exception {
        var newUser = userService.findUserById(id);
        return ResponseEntity.ok(new UserResponseDTO(newUser));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> listUser(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var usersPage = userService.listUsers(pageable);
        return ResponseEntity.ok(usersPage);
    }

    @GetMapping("/{userId}/transactions")
    public ResponseEntity<Map<String, Object>> getUserTransactionHistory(@PathVariable Long userId) throws Exception {
        Map<String, Object> transactionHistory = transactionService.getUserTransactionHistory(userId);
        return ResponseEntity.ok(transactionHistory);
    }
}
