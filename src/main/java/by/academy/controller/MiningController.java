package by.academy.controller;

import by.academy.entity.AppUser;
import by.academy.entity.Transaction;
import by.academy.service.MiningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MiningController {

    public static final Logger logger = LoggerFactory.getLogger(MiningController.class);

    @Autowired
    MiningService miningService;


    @PostMapping("/runMining")
    public ResponseEntity runMining(
            @RequestBody AppUser appUser){
        logger.info("AppUser: {}", appUser);

        if(miningService.runMining(appUser.getUsersWallet())){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
