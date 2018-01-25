package net.openvpn.openvpn;

public class ClientAPI_ExternalPKIRequestBase {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_ExternalPKIRequestBase(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_ExternalPKIRequestBase obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_ExternalPKIRequestBase(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setError(boolean value) {
        ovpncliJNI.ClientAPI_ExternalPKIRequestBase_error_set(this.swigCPtr, this, value);
    }

    public boolean getError() {
        return ovpncliJNI.ClientAPI_ExternalPKIRequestBase_error_get(this.swigCPtr, this);
    }

    public void setErrorText(String value) {
        ovpncliJNI.ClientAPI_ExternalPKIRequestBase_errorText_set(this.swigCPtr, this, value);
    }

    public String getErrorText() {
        return ovpncliJNI.ClientAPI_ExternalPKIRequestBase_errorText_get(this.swigCPtr, this);
    }

    public void setInvalidAlias(boolean value) {
        ovpncliJNI.ClientAPI_ExternalPKIRequestBase_invalidAlias_set(this.swigCPtr, this, value);
    }

    public boolean getInvalidAlias() {
        return ovpncliJNI.ClientAPI_ExternalPKIRequestBase_invalidAlias_get(this.swigCPtr, this);
    }

    public void setAlias(String value) {
        ovpncliJNI.ClientAPI_ExternalPKIRequestBase_alias_set(this.swigCPtr, this, value);
    }

    public String getAlias() {
        return ovpncliJNI.ClientAPI_ExternalPKIRequestBase_alias_get(this.swigCPtr, this);
    }

    public ClientAPI_ExternalPKIRequestBase() {
        this(ovpncliJNI.new_ClientAPI_ExternalPKIRequestBase(), true);
    }
}
