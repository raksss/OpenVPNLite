package net.openvpn.openvpn;

public class ClientAPI_MergeConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_MergeConfig(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_MergeConfig obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_MergeConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStatus(String value) {
        ovpncliJNI.ClientAPI_MergeConfig_status_set(this.swigCPtr, this, value);
    }

    public String getStatus() {
        return ovpncliJNI.ClientAPI_MergeConfig_status_get(this.swigCPtr, this);
    }

    public void setErrorText(String value) {
        ovpncliJNI.ClientAPI_MergeConfig_errorText_set(this.swigCPtr, this, value);
    }

    public String getErrorText() {
        return ovpncliJNI.ClientAPI_MergeConfig_errorText_get(this.swigCPtr, this);
    }

    public void setBasename(String value) {
        ovpncliJNI.ClientAPI_MergeConfig_basename_set(this.swigCPtr, this, value);
    }

    public String getBasename() {
        return ovpncliJNI.ClientAPI_MergeConfig_basename_get(this.swigCPtr, this);
    }

    public void setProfileContent(String value) {
        ovpncliJNI.ClientAPI_MergeConfig_profileContent_set(this.swigCPtr, this, value);
    }

    public String getProfileContent() {
        return ovpncliJNI.ClientAPI_MergeConfig_profileContent_get(this.swigCPtr, this);
    }

    public void setRefPathList(SWIGTYPE_p_std__vectorT_std__string_t value) {
        ovpncliJNI.ClientAPI_MergeConfig_refPathList_set(this.swigCPtr, this, SWIGTYPE_p_std__vectorT_std__string_t.getCPtr(value));
    }

    public SWIGTYPE_p_std__vectorT_std__string_t getRefPathList() {
        long cPtr = ovpncliJNI.ClientAPI_MergeConfig_refPathList_get(this.swigCPtr, this);
        return cPtr == 0 ? null : new SWIGTYPE_p_std__vectorT_std__string_t(cPtr, false);
    }

    public ClientAPI_MergeConfig() {
        this(ovpncliJNI.new_ClientAPI_MergeConfig(), true);
    }
}
