package net.openvpn.openvpn;

public class ClientAPI_LLVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_LLVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_LLVector obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_LLVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ClientAPI_LLVector() {
        this(ovpncliJNI.new_ClientAPI_LLVector__SWIG_0(), true);
    }

    public ClientAPI_LLVector(long n) {
        this(ovpncliJNI.new_ClientAPI_LLVector__SWIG_1(n), true);
    }

    public long size() {
        return ovpncliJNI.ClientAPI_LLVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return ovpncliJNI.ClientAPI_LLVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        ovpncliJNI.ClientAPI_LLVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return ovpncliJNI.ClientAPI_LLVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        ovpncliJNI.ClientAPI_LLVector_clear(this.swigCPtr, this);
    }

    public void add(long x) {
        ovpncliJNI.ClientAPI_LLVector_add(this.swigCPtr, this, x);
    }

    public long get(int i) {
        return ovpncliJNI.ClientAPI_LLVector_get(this.swigCPtr, this, i);
    }

    public void set(int i, long val) {
        ovpncliJNI.ClientAPI_LLVector_set(this.swigCPtr, this, i, val);
    }
}
