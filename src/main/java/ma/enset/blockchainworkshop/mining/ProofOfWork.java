package ma.enset.blockchainworkshop.mining;


import ma.enset.blockchainworkshop.blockchain.Block;

public class ProofOfWork {
    public static void mineBlock(Block block, int difficulty) {
        block.mineBlock(difficulty);
    }

    public static boolean validateBlock(Block block, int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        return block.getCurrentHash().substring(0, difficulty).equals(target);
    }
}
