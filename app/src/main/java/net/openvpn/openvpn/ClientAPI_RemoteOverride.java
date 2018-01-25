package net.openvpn.openvpn;

public class ClientAPI_RemoteOverride {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_RemoteOverride(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_RemoteOverride obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_RemoteOverride(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setHost(String value) {
        ovpncliJNI.ClientAPI_RemoteOverride_host_set(this.swigCPtr, this, value);
    }

    public String getHost() {
        return ovpncliJNI.ClientAPI_RemoteOverride_host_get(this.swigCPtr, this);
    }

    public void setIp(String value) {
        ovpncliJNI.ClientAPI_RemoteOverride_ip_set(this.swigCPtr, this, value);
    }

    public String getIp() {
        return ovpncliJNI.ClientAPI_RemoteOverride_ip_get(this.swigCPtr, this);
    }

    public void setPort(String value) {
        ovpncliJNI.ClientAPI_RemoteOverride_port_set(this.swigCPtr, this, value);
    }

    public String getPort() {
        return ovpncliJNI.ClientAPI_RemoteOverride_port_get(this.swigCPtr, this);
    }

    public void setProto(String value) {
        ovpncliJNI.ClientAPI_RemoteOverride_proto_set(this.swigCPtr, this, value);
    }

    public String getProto() {
        return ovpncliJNI.ClientAPI_RemoteOverride_proto_get(this.swigCPtr, this);
    }

    public ClientAPI_RemoteOverride() {
        this(ovpncliJNI.new_ClientAPI_RemoteOverride(), true);
    }
}
