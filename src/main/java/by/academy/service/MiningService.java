package by.academy.service;

import by.academy.entity.Block;
import by.academy.entity.Transaction;
import by.academy.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

        Transaction transaction;

        transaction = transactionRepository.findFirstByStamp(0);
        if (transaction == null) {
            logger.info("Transaction NULL");
            logger.info("Search all transaction by stamp 0");
            List<Transaction> allByStamp = transactionRepository.findAllByStamp(0);
            if(allByStamp.isEmpty()) {
                logger.info("All transaction by stamp 0:{}", allByStamp);
                return false;
            }else {
                Transaction transactionFromList = allByStamp.get(0);
                logger.info("Transaction from list: {}", transactionFromList);
                transaction = transactionFromList;
            }
            return false;
        }
        logger.info("Transaction by stamp 0: {}", transaction);
        logger.info("We calculate the commission");

        transaction.setCommission(0L);

        logger.info("Find last block");

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
