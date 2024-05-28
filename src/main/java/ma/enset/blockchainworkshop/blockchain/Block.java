package ma.enset.blockchainworkshop.blockchain;

import java.time.Instant;

public class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String currentHash;
    private String data;
    private int nonce;

    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = Instant.now().getEpochSecond();
        this.currentHash = calculateHash();
    }

    public String calculateHash() {
        String input = index + timestamp + previousHash + data + nonce;
        return HashUtil.applySha256(input);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!currentHash.substring(0, difficulty).equals(target)) {
            nonce++;
            currentHash = calculateHash();
        }
        System.out.println("Block Mined! : " + currentHash);
    }

    // Getters and setters
    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public String getData() {
        return data;
    }

    public int getNonce() {
        return nonce;
    }
}
