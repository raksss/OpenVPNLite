package net.openvpn.openvpn;

public class ClientAPI_ExternalPKISignRequest extends ClientAPI_ExternalPKIRequestBase {
    private transient long swigCPtr;

    protected ClientAPI_ExternalPKISignRequest(long cPtr, boolean cMemoryOwn) {
        super(ovpncliJNI.ClientAPI_ExternalPKISignRequest_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_ExternalPKISignRequest obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_ExternalPKISignRequest(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setData(String value) {
        ovpncliJNI.ClientAPI_ExternalPKISignRequest_data_set(this.swigCPtr, this, value);
    }

    public String getData() {
        return ovpncliJNI.ClientAPI_ExternalPKISignRequest_data_get(this.swigCPtr, this);
    }

    public void setSig(String value) {
        ovpncliJNI.ClientAPI_ExternalPKISignRequest_sig_set(this.swigCPtr, this, value);
    }

    public String getSig() {
        return ovpncliJNI.ClientAPI_ExternalPKISignRequest_sig_get(this.swigCPtr, this);
    }

    public ClientAPI_ExternalPKISignRequest() {
        this(ovpncliJNI.new_ClientAPI_ExternalPKISignRequest(), true);
    }
}
