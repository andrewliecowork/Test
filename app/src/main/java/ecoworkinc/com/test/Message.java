package ecoworkinc.com.test;

/**
 * Created by andrewhanks on 2017/1/5.
 */

public class Message {
    private String internalName;
    private int dataVersion;
    private String name;
    private int profileIconId;
    private int revisionId;

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public int getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(int dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }

    public Message(String internalName, int dataVersion, String name, int profileIconId, int revisionId) {
        this.internalName = internalName;
        this.dataVersion = dataVersion;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionId = revisionId;
    }
}
