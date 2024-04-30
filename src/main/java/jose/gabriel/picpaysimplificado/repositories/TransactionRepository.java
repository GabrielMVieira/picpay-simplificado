package jose.gabriel.picpaysimplificado.repositories;

import jose.gabriel.picpaysimplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByPayerIdOrPayeeId(Long primaryId, Long secundId);
}
