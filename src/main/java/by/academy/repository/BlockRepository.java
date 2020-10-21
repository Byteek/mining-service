package by.academy.repository;

import by.academy.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, String> {


    List<Block> findByOrderByTimeStampDesc();
}
