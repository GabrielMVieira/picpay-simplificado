package jose.gabriel.picpaysimplificado.controller;

import jose.gabriel.picpaysimplificado.domain.transaction.TransactionRequestDTO;
import jose.gabriel.picpaysimplificado.domain.transaction.TransactionResponseDTO;
import jose.gabriel.picpaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionResponseDTO> createTransactions(@RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        var newTransaction = this.transactionService.validateTransactions(transactionRequestDTO);
        return new ResponseEntity<>(new TransactionResponseDTO(newTransaction), HttpStatus.CREATED);
    }

}
