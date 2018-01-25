package net.openvpn.openvpn;

public class ClientAPI_ServerEntryVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_ServerEntryVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_ServerEntryVector obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_ServerEntryVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ClientAPI_ServerEntryVector() {
        this(ovpncliJNI.new_ClientAPI_ServerEntryVector__SWIG_0(), true);
    }

    public ClientAPI_ServerEntryVector(long n) {
        this(ovpncliJNI.new_ClientAPI_ServerEntryVector__SWIG_1(n), true);
    }

    public long size() {
        return ovpncliJNI.ClientAPI_ServerEntryVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return ovpncliJNI.ClientAPI_ServerEntryVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        ovpncliJNI.ClientAPI_ServerEntryVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return ovpncliJNI.ClientAPI_ServerEntryVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        ovpncliJNI.ClientAPI_ServerEntryVector_clear(this.swigCPtr, this);
    }

    public void add(ClientAPI_ServerEntry x) {
        ovpncliJNI.ClientAPI_ServerEntryVector_add(this.swigCPtr, this, ClientAPI_ServerEntry.getCPtr(x), x);
    }

    public ClientAPI_ServerEntry get(int i) {
        return new ClientAPI_ServerEntry(ovpncliJNI.ClientAPI_ServerEntryVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, ClientAPI_ServerEntry val) {
        ovpncliJNI.ClientAPI_ServerEntryVector_set(this.swigCPtr, this, i, ClientAPI_ServerEntry.getCPtr(val), val);
    }
}
