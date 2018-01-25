package net.openvpn.openvpn;

public class ClientAPI_InterfaceStats {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_InterfaceStats(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_InterfaceStats obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_InterfaceStats(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setBytesIn(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_bytesIn_set(this.swigCPtr, this, value);
    }

    public long getBytesIn() {
        return ovpncliJNI.ClientAPI_InterfaceStats_bytesIn_get(this.swigCPtr, this);
    }

    public void setPacketsIn(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_packetsIn_set(this.swigCPtr, this, value);
    }

    public long getPacketsIn() {
        return ovpncliJNI.ClientAPI_InterfaceStats_packetsIn_get(this.swigCPtr, this);
    }

    public void setErrorsIn(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_errorsIn_set(this.swigCPtr, this, value);
    }

    public long getErrorsIn() {
        return ovpncliJNI.ClientAPI_InterfaceStats_errorsIn_get(this.swigCPtr, this);
    }

    public void setBytesOut(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_bytesOut_set(this.swigCPtr, this, value);
    }

    public long getBytesOut() {
        return ovpncliJNI.ClientAPI_InterfaceStats_bytesOut_get(this.swigCPtr, this);
    }

    public void setPacketsOut(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_packetsOut_set(this.swigCPtr, this, value);
    }

    public long getPacketsOut() {
        return ovpncliJNI.ClientAPI_InterfaceStats_packetsOut_get(this.swigCPtr, this);
    }

    public void setErrorsOut(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_errorsOut_set(this.swigCPtr, this, value);
    }

    public long getErrorsOut() {
        return ovpncliJNI.ClientAPI_InterfaceStats_errorsOut_get(this.swigCPtr, this);
    }

    public ClientAPI_InterfaceStats() {
        this(ovpncliJNI.new_ClientAPI_InterfaceStats(), true);
    }
}
