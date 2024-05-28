package ma.enset.blockchainworkshop.transaction;

public class Transaction {
    private String sender;
    private String recipient;
    private double amount;
    private String signature;

    public Transaction(String sender, String recipient, double amount, String signature) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.signature = signature;
    }

    // Getters and setters
    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public String getSignature() {
        return signature;
    }
}
