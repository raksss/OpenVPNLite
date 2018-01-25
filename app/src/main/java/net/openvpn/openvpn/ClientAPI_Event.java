package net.openvpn.openvpn;

public class ClientAPI_Event {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_Event(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_Event obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_Event(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setError(boolean value) {
        ovpncliJNI.ClientAPI_Event_error_set(this.swigCPtr, this, value);
    }

    public boolean getError() {
        return ovpncliJNI.ClientAPI_Event_error_get(this.swigCPtr, this);
    }

    public void setFatal(boolean value) {
        ovpncliJNI.ClientAPI_Event_fatal_set(this.swigCPtr, this, value);
    }

    public boolean getFatal() {
        return ovpncliJNI.ClientAPI_Event_fatal_get(this.swigCPtr, this);
    }

    public void setName(String value) {
        ovpncliJNI.ClientAPI_Event_name_set(this.swigCPtr, this, value);
    }

    public String getName() {
        return ovpncliJNI.ClientAPI_Event_name_get(this.swigCPtr, this);
    }

    public void setInfo(String value) {
        ovpncliJNI.ClientAPI_Event_info_set(this.swigCPtr, this, value);
    }

    public String getInfo() {
        return ovpncliJNI.ClientAPI_Event_info_get(this.swigCPtr, this);
    }

    public ClientAPI_Event() {
        this(ovpncliJNI.new_ClientAPI_Event(), true);
    }
}
