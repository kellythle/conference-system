package Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A class that represents a Message.
 */
public class Message implements Serializable, Comparable<Message> {
    private UUID id; // id of message
    private final String sender;// Stores the name of the sender
    private String receiver;// Stores the name of the receiver
    private String content;// Stores the content of the message
    private final LocalDateTime time = LocalDateTime.now();// time when the message create

    /**
     * Create new message
     * @param sender- sender of this message
     * @param receiver- receiver of this message
     * @param content- content of this message
     */
    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.id = UUID.randomUUID();
    }

    /**
     * Getter of sender
     * @return sender name
     */
    public String getSender() {
        return sender;
    }

    /**
     * Getter of receiver
     * @return receiver name
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Setter of receiver
     * @param receiver- receiver name
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * Getter of content
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter of content
     * @param content- content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter of id
     * @return message id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Getter of time
     * @return time when the message sends
     */
    public LocalDateTime getTime() {
        return time;
    }

    public int compareTo(Message msg) {
        return this.time.compareTo(msg.time);
    }
}