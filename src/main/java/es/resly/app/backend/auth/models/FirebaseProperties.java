package es.resly.app.backend.auth.models;

import lombok.Data;

@Data
public class FirebaseProperties {

    private int sessionExpiryInDays;
    private String databaseUrl;
    private String storageUrl;
    private boolean enableStrictServerSession;
    private boolean enableCheckSessionRevoked;
    private boolean enableLogoutEverywhere;

    public int getSessionExpiryInDays() {
        return sessionExpiryInDays;
    }

    public void setSessionExpiryInDays(int sessionExpiryInDays) {
        this.sessionExpiryInDays = sessionExpiryInDays;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public boolean isEnableStrictServerSession() {
        return enableStrictServerSession;
    }

    public void setEnableStrictServerSession(boolean enableStrictServerSession) {
        this.enableStrictServerSession = enableStrictServerSession;
    }

    public boolean isEnableCheckSessionRevoked() {
        return enableCheckSessionRevoked;
    }

    public void setEnableCheckSessionRevoked(boolean enableCheckSessionRevoked) {
        this.enableCheckSessionRevoked = enableCheckSessionRevoked;
    }

    public boolean isEnableLogoutEverywhere() {
        return enableLogoutEverywhere;
    }

    public void setEnableLogoutEverywhere(boolean enableLogoutEverywhere) {
        this.enableLogoutEverywhere = enableLogoutEverywhere;
    }

    public String getStorageUrl() {
        return storageUrl;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }
}

