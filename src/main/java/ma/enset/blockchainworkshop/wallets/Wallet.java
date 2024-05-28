package ma.enset.blockchainworkshop.wallets;

import ma.enset.blockchainworkshop.transaction.Transaction;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private Map<String, Double> balanceMap;

    public Wallet() {
        generateKeyPair();
        balanceMap = new HashMap<>();
    }

    private void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");

            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair();

            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Transaction signTransaction(String recipient, double amount) {
        if (getBalance() < amount) {
            System.out.println("Insufficient funds to perform transaction.");
            return null;
        }

        Transaction transaction = new Transaction(publicKey.toString(), recipient, amount, null);
        transaction.setSignature(sign(transaction.toString()));
        return transaction;
    }

    public double getBalance() {
        double balance = 0;
        for (Double amount : balanceMap.values()) {
            balance += amount;
        }
        return balance;
    }

    private String sign(String data) {
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            byte[] signatureBytes = signature.sign();
            return bytesToHex(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public String getAddress() {
        return publicKey.toString();
    }
}
