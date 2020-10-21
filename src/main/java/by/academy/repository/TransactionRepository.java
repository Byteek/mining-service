package by.academy.repository;

import by.academy.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Transaction findFirstByStamp(int i);

    List<Transaction> findFirst5ByStamp(int i);
}
