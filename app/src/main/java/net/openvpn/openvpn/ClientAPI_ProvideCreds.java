package net.openvpn.openvpn;

public class ClientAPI_ProvideCreds {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_ProvideCreds(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_ProvideCreds obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_ProvideCreds(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setUsername(String value) {
        ovpncliJNI.ClientAPI_ProvideCreds_username_set(this.swigCPtr, this, value);
    }

    public String getUsername() {
        return ovpncliJNI.ClientAPI_ProvideCreds_username_get(this.swigCPtr, this);
    }

    public void setPassword(String value) {
        ovpncliJNI.ClientAPI_ProvideCreds_password_set(this.swigCPtr, this, value);
    }

    public String getPassword() {
        return ovpncliJNI.ClientAPI_ProvideCreds_password_get(this.swigCPtr, this);
    }

    public void setResponse(String value) {
        ovpncliJNI.ClientAPI_ProvideCreds_response_set(this.swigCPtr, this, value);
    }

    public String getResponse() {
        return ovpncliJNI.ClientAPI_ProvideCreds_response_get(this.swigCPtr, this);
    }

    public void setDynamicChallengeCookie(String value) {
        ovpncliJNI.ClientAPI_ProvideCreds_dynamicChallengeCookie_set(this.swigCPtr, this, value);
    }

    public String getDynamicChallengeCookie() {
        return ovpncliJNI.ClientAPI_ProvideCreds_dynamicChallengeCookie_get(this.swigCPtr, this);
    }

    public void setReplacePasswordWithSessionID(boolean value) {
        ovpncliJNI.ClientAPI_ProvideCreds_replacePasswordWithSessionID_set(this.swigCPtr, this, value);
    }

    public boolean getReplacePasswordWithSessionID() {
        return ovpncliJNI.ClientAPI_ProvideCreds_replacePasswordWithSessionID_get(this.swigCPtr, this);
    }

    public void setCachePassword(boolean value) {
        ovpncliJNI.ClientAPI_ProvideCreds_cachePassword_set(this.swigCPtr, this, value);
    }

    public boolean getCachePassword() {
        return ovpncliJNI.ClientAPI_ProvideCreds_cachePassword_get(this.swigCPtr, this);
    }

    public ClientAPI_ProvideCreds() {
        this(ovpncliJNI.new_ClientAPI_ProvideCreds(), true);
    }
}
