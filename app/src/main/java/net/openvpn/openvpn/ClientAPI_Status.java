package net.openvpn.openvpn;

public class ClientAPI_Status {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_Status(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_Status obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_Status(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setError(boolean value) {
        ovpncliJNI.ClientAPI_Status_error_set(this.swigCPtr, this, value);
    }

    public boolean getError() {
        return ovpncliJNI.ClientAPI_Status_error_get(this.swigCPtr, this);
    }

    public void setStatus(String value) {
        ovpncliJNI.ClientAPI_Status_status_set(this.swigCPtr, this, value);
    }

    public String getStatus() {
        return ovpncliJNI.ClientAPI_Status_status_get(this.swigCPtr, this);
    }

    public void setMessage(String value) {
        ovpncliJNI.ClientAPI_Status_message_set(this.swigCPtr, this, value);
    }

    public String getMessage() {
        return ovpncliJNI.ClientAPI_Status_message_get(this.swigCPtr, this);
    }

    public ClientAPI_Status() {
        this(ovpncliJNI.new_ClientAPI_Status(), true);
    }
}
