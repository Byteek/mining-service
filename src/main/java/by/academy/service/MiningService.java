package by.academy.service;

import by.academy.entity.Block;
import by.academy.entity.Transaction;
import by.academy.entity.Wallet;
import by.academy.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MiningService {

    @Autowired
    BlockRepository blockRepository;
    @Autowired
    PreviousBlockRepository previousBlockRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    UserRepository userRepository;

    public static int difficulty = 5;

    public static final Logger logger = LoggerFactory.getLogger(MiningService.class);


    @Transactional
    public boolean runMining(String usersWallet) {
        logger.info("RUN");

        logger.info("Wallet: {}", usersWallet);

        logger.info("Find transaction by stamp 0");
        Transaction transaction = transactionRepository.findFirstByStamp(0);
        logger.info("We calculate the commission");


        if (blockRepository.findByOrderByTimeStampDesc().isEmpty()) {
            blockRepository.save(new Block("Im first block!", ""));
        }
        Block block = new Block(transaction.getId(),
                blockRepository.findByOrderByTimeStampDesc().stream().findFirst().orElseThrow().getHash());

        logger.info("Saving to database block: {}", block);
        blockRepository.save(block);


        transaction.setStamp(1);

        logger.info("Saving to database transaction with stamp: {}", transaction);
        transactionRepository.save(transaction);

        if (transactionRepository.findById(transaction.getId()).orElseThrow().getStamp() == 0) {
            return false;
        } else {
            return transactionRepository.findById(transaction.getId()).orElseThrow().getStamp() == 1;
        }

    }

}
