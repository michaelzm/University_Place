package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {}, tableName = "messages")
public class Message {

    @PrimaryKey
    @ColumnInfo(name = "messageID")
    Long messageID;
    @ColumnInfo(name = "senderID")
    Long senderID;
    @ColumnInfo(name = "receiverID")
    Long receiverID;
    @ColumnInfo(name = "content")
    String content;



    public Message(){}

    public Long getMessageID() {
        return messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public Long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Long receiverID) {
        this.receiverID = receiverID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
