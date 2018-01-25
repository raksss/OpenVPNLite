package net.openvpn.openvpn;

public class ClientAPI_ServerEntry {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_ServerEntry(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_ServerEntry obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_ServerEntry(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setServer(String value) {
        ovpncliJNI.ClientAPI_ServerEntry_server_set(this.swigCPtr, this, value);
    }

    public String getServer() {
        return ovpncliJNI.ClientAPI_ServerEntry_server_get(this.swigCPtr, this);
    }

    public void setFriendlyName(String value) {
        ovpncliJNI.ClientAPI_ServerEntry_friendlyName_set(this.swigCPtr, this, value);
    }

    public String getFriendlyName() {
        return ovpncliJNI.ClientAPI_ServerEntry_friendlyName_get(this.swigCPtr, this);
    }

    public ClientAPI_ServerEntry() {
        this(ovpncliJNI.new_ClientAPI_ServerEntry(), true);
    }
}
