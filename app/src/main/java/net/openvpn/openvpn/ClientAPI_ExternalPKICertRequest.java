package net.openvpn.openvpn;

public class ClientAPI_ExternalPKICertRequest extends ClientAPI_ExternalPKIRequestBase {
    private transient long swigCPtr;

    protected ClientAPI_ExternalPKICertRequest(long cPtr, boolean cMemoryOwn) {
        super(ovpncliJNI.ClientAPI_ExternalPKICertRequest_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_ExternalPKICertRequest obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_ExternalPKICertRequest(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setCert(String value) {
        ovpncliJNI.ClientAPI_ExternalPKICertRequest_cert_set(this.swigCPtr, this, value);
    }

    public String getCert() {
        return ovpncliJNI.ClientAPI_ExternalPKICertRequest_cert_get(this.swigCPtr, this);
    }

    public void setSupportingChain(String value) {
        ovpncliJNI.ClientAPI_ExternalPKICertRequest_supportingChain_set(this.swigCPtr, this, value);
    }

    public String getSupportingChain() {
        return ovpncliJNI.ClientAPI_ExternalPKICertRequest_supportingChain_get(this.swigCPtr, this);
    }

    public ClientAPI_ExternalPKICertRequest() {
        this(ovpncliJNI.new_ClientAPI_ExternalPKICertRequest(), true);
    }
}
