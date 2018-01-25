package net.openvpn.openvpn;

public class ClientAPI_KeyValue {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_KeyValue(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_KeyValue obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_KeyValue(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ClientAPI_KeyValue() {
        this(ovpncliJNI.new_ClientAPI_KeyValue__SWIG_0(), true);
    }

    public ClientAPI_KeyValue(String key_arg, String value_arg) {
        this(ovpncliJNI.new_ClientAPI_KeyValue__SWIG_1(key_arg, value_arg), true);
    }

    public void setKey(String value) {
        ovpncliJNI.ClientAPI_KeyValue_key_set(this.swigCPtr, this, value);
    }

    public String getKey() {
        return ovpncliJNI.ClientAPI_KeyValue_key_get(this.swigCPtr, this);
    }

    public void setValue(String value) {
        ovpncliJNI.ClientAPI_KeyValue_value_set(this.swigCPtr, this, value);
    }

    public String getValue() {
        return ovpncliJNI.ClientAPI_KeyValue_value_get(this.swigCPtr, this);
    }
}
