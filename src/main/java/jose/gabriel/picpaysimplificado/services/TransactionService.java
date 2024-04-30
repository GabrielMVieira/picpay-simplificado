package jose.gabriel.picpaysimplificado.services;

import jose.gabriel.picpaysimplificado.domain.transaction.Transaction;
import jose.gabriel.picpaysimplificado.domain.transaction.TransactionRequestDTO;
import jose.gabriel.picpaysimplificado.domain.transaction.UserTransactionDTO;
import jose.gabriel.picpaysimplificado.domain.user.User;
import jose.gabriel.picpaysimplificado.domain.user.UserResponseDTO;
import jose.gabriel.picpaysimplificado.infra.exception.CustomException;
import jose.gabriel.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository repository;

    @Value("${api.autorization.mock}")
    private String mockAutorization;

    @Value("${api.true.mock}")
    private String mockTrue;

    public Transaction validateTransactions(TransactionRequestDTO transactionRequestDTO) throws Exception {
        Long payerId = transactionRequestDTO.payerId();
        Long payeeId = transactionRequestDTO.payeeId();

        if (payerId == null || payeeId == null) {
            throw new CustomException("IDs do pagador e/ou do beneficiário não podem ser nulos");
        }

        User payer = this.userService.findUserById(transactionRequestDTO.payerId());
        User payee = this.userService.findUserById(transactionRequestDTO.payeeId());

        userService.validateUser(payer, transactionRequestDTO.amount());

        boolean isAuthorize = authorizeTransaction();

        if (!isAuthorize) {
            throw new CustomException("Transação não autoriazada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionRequestDTO.amount());
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setTransactionTime(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transactionRequestDTO.amount()));
        payee.setBalance(payee.getBalance().add(transactionRequestDTO.amount()));

        this.repository.save(newTransaction);
        this.userService.saveUser(payer);
        this.userService.saveUser(payee);

        sendNotification();
        return newTransaction;
    }

    public Map<String, Object> getUserTransactionHistory(Long userId) throws Exception {
        User user = userService.findUserById(userId);
        List<Transaction> transactions = repository.findByPayerIdOrPayeeId(user.getId(), user.getId());

        var userDTO = new UserResponseDTO(user);

        List<Transaction> lastTenTransactions = transactions.subList(Math.max(0, transactions.size() - 10), transactions.size());

        Map<String, Object> response = new HashMap<>();
        response.put("user", userDTO);
        response.put("transactions", lastTenTransactions.stream()
                .map(transaction -> new UserTransactionDTO(transaction, userId))
                .collect(Collectors.toList()));

        return response;
    }

    private boolean authorizeTransaction() {
        var response = restTemplate.getForEntity(mockAutorization, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }

    private void sendNotification() {
        var response = restTemplate.getForEntity(mockTrue, Map.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new CustomException("Erro ao enviar notificação: status " + response.getStatusCodeValue());
        }
    }
}