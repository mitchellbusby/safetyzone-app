package com.safetyzone.safetyzone;

/**
 * Created by Zak on 22/10/2015.
 */
public class SafetyTipData {
    public String Id;
    public String Tip;
    public String Topic;
    public String source;

    public SafetyTipData(String id, String tip, String topic, String source) {
        this.Id = id;
        this.Tip = tip;
        this.Topic = topic;
        this.source = source;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTip() {
        return Tip;
    }

    public void setTip(String tip) {
        Tip = tip;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }




}
