package ma.enset.blockchainworkshop.controller;

import ma.enset.blockchainworkshop.blockchain.BlockChain;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BlockchainController {

    @MessageMapping("/blocks")
    @SendTo("/topic/blocks")
    public BlockChain blockchain() {
        // Return the blockchain
        return null; // Placeholder
    }

}