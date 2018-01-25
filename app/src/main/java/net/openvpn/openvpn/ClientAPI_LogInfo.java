package net.openvpn.openvpn;

public class ClientAPI_LogInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_LogInfo(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_LogInfo obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_LogInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ClientAPI_LogInfo() {
        this(ovpncliJNI.new_ClientAPI_LogInfo__SWIG_0(), true);
    }

    public ClientAPI_LogInfo(String str) {
        this(ovpncliJNI.new_ClientAPI_LogInfo__SWIG_1(str), true);
    }

    public void setText(String value) {
        ovpncliJNI.ClientAPI_LogInfo_text_set(this.swigCPtr, this, value);
    }

    public String getText() {
        return ovpncliJNI.ClientAPI_LogInfo_text_get(this.swigCPtr, this);
    }
}
