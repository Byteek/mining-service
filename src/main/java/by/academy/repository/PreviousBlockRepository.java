package by.academy.repository;


import by.academy.entity.PreviousBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreviousBlockRepository extends JpaRepository<PreviousBlock, String> {



}
