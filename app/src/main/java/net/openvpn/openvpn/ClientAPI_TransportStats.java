package net.openvpn.openvpn;

public class ClientAPI_TransportStats {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_TransportStats(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_TransportStats obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_TransportStats(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setBytesIn(long value) {
        ovpncliJNI.ClientAPI_TransportStats_bytesIn_set(this.swigCPtr, this, value);
    }

    public long getBytesIn() {
        return ovpncliJNI.ClientAPI_TransportStats_bytesIn_get(this.swigCPtr, this);
    }

    public void setBytesOut(long value) {
        ovpncliJNI.ClientAPI_TransportStats_bytesOut_set(this.swigCPtr, this, value);
    }

    public long getBytesOut() {
        return ovpncliJNI.ClientAPI_TransportStats_bytesOut_get(this.swigCPtr, this);
    }

    public void setPacketsIn(long value) {
        ovpncliJNI.ClientAPI_TransportStats_packetsIn_set(this.swigCPtr, this, value);
    }

    public long getPacketsIn() {
        return ovpncliJNI.ClientAPI_TransportStats_packetsIn_get(this.swigCPtr, this);
    }

    public void setPacketsOut(long value) {
        ovpncliJNI.ClientAPI_TransportStats_packetsOut_set(this.swigCPtr, this, value);
    }

    public long getPacketsOut() {
        return ovpncliJNI.ClientAPI_TransportStats_packetsOut_get(this.swigCPtr, this);
    }

    public void setLastPacketReceived(int value) {
        ovpncliJNI.ClientAPI_TransportStats_lastPacketReceived_set(this.swigCPtr, this, value);
    }

    public int getLastPacketReceived() {
        return ovpncliJNI.ClientAPI_TransportStats_lastPacketReceived_get(this.swigCPtr, this);
    }

    public ClientAPI_TransportStats() {
        this(ovpncliJNI.new_ClientAPI_TransportStats(), true);
    }
}
