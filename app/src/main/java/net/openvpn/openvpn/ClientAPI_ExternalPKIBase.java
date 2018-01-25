package net.openvpn.openvpn;

public class ClientAPI_ExternalPKIBase {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_ExternalPKIBase(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_ExternalPKIBase obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_ExternalPKIBase(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean sign(String data, SWIGTYPE_p_std__string sig) {
        return ovpncliJNI.ClientAPI_ExternalPKIBase_sign(this.swigCPtr, this, data, SWIGTYPE_p_std__string.getCPtr(sig));
    }
}
