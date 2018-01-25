package net.openvpn.openvpn;

public class ClientAPI_SessionToken {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_SessionToken(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_SessionToken obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_SessionToken(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setUsername(String value) {
        ovpncliJNI.ClientAPI_SessionToken_username_set(this.swigCPtr, this, value);
    }

    public String getUsername() {
        return ovpncliJNI.ClientAPI_SessionToken_username_get(this.swigCPtr, this);
    }

    public void setSession_id(String value) {
        ovpncliJNI.ClientAPI_SessionToken_session_id_set(this.swigCPtr, this, value);
    }

    public String getSession_id() {
        return ovpncliJNI.ClientAPI_SessionToken_session_id_get(this.swigCPtr, this);
    }

    public ClientAPI_SessionToken() {
        this(ovpncliJNI.new_ClientAPI_SessionToken(), true);
    }
}
