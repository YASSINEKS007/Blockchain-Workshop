package ma.enset.blockchainworkshop.blockchain;


import ma.enset.blockchainworkshop.transaction.TransactionPool;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
    private List<Block> chain;
    private TransactionPool transactionPool;
    private int difficulty;

    public BlockChain() {
        this.chain = new ArrayList<>();
        this.chain.add(generateGenesisBlock());
        this.transactionPool = new TransactionPool();
        this.difficulty = 4;
    }

    private Block generateGenesisBlock() {
        return new Block(0, "0", "Genesis Block");
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    public boolean validateChain() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getCurrentHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getCurrentHash())) {
                return false;
            }
        }
        return true;
    }
}
