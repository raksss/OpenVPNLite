package net.openvpn.openvpn;

public class ClientAPI_EvalConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_EvalConfig(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_EvalConfig obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_EvalConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setError(boolean value) {
        ovpncliJNI.ClientAPI_EvalConfig_error_set(this.swigCPtr, this, value);
    }

    public boolean getError() {
        return ovpncliJNI.ClientAPI_EvalConfig_error_get(this.swigCPtr, this);
    }

    public void setMessage(String value) {
        ovpncliJNI.ClientAPI_EvalConfig_message_set(this.swigCPtr, this, value);
    }

    public String getMessage() {
        return ovpncliJNI.ClientAPI_EvalConfig_message_get(this.swigCPtr, this);
    }

    public void setUserlockedUsername(String value) {
        ovpncliJNI.ClientAPI_EvalConfig_userlockedUsername_set(this.swigCPtr, this, value);
    }

    public String getUserlockedUsername() {
        return ovpncliJNI.ClientAPI_EvalConfig_userlockedUsername_get(this.swigCPtr, this);
    }

    public void setProfileName(String value) {
        ovpncliJNI.ClientAPI_EvalConfig_profileName_set(this.swigCPtr, this, value);
    }

    public String getProfileName() {
        return ovpncliJNI.ClientAPI_EvalConfig_profileName_get(this.swigCPtr, this);
    }

    public void setFriendlyName(String value) {
        ovpncliJNI.ClientAPI_EvalConfig_friendlyName_set(this.swigCPtr, this, value);
    }

    public String getFriendlyName() {
        return ovpncliJNI.ClientAPI_EvalConfig_friendlyName_get(this.swigCPtr, this);
    }

    public void setAutologin(boolean value) {
        ovpncliJNI.ClientAPI_EvalConfig_autologin_set(this.swigCPtr, this, value);
    }

    public boolean getAutologin() {
        return ovpncliJNI.ClientAPI_EvalConfig_autologin_get(this.swigCPtr, this);
    }

    public void setExternalPki(boolean value) {
        ovpncliJNI.ClientAPI_EvalConfig_externalPki_set(this.swigCPtr, this, value);
    }

    public boolean getExternalPki() {
        return ovpncliJNI.ClientAPI_EvalConfig_externalPki_get(this.swigCPtr, this);
    }

    public void setStaticChallenge(String value) {
        ovpncliJNI.ClientAPI_EvalConfig_staticChallenge_set(this.swigCPtr, this, value);
    }

    public String getStaticChallenge() {
        return ovpncliJNI.ClientAPI_EvalConfig_staticChallenge_get(this.swigCPtr, this);
    }

    public void setStaticChallengeEcho(boolean value) {
        ovpncliJNI.ClientAPI_EvalConfig_staticChallengeEcho_set(this.swigCPtr, this, value);
    }

    public boolean getStaticChallengeEcho() {
        return ovpncliJNI.ClientAPI_EvalConfig_staticChallengeEcho_get(this.swigCPtr, this);
    }

    public void setPrivateKeyPasswordRequired(boolean value) {
        ovpncliJNI.ClientAPI_EvalConfig_privateKeyPasswordRequired_set(this.swigCPtr, this, value);
    }

    public boolean getPrivateKeyPasswordRequired() {
        return ovpncliJNI.ClientAPI_EvalConfig_privateKeyPasswordRequired_get(this.swigCPtr, this);
    }

    public void setAllowPasswordSave(boolean value) {
        ovpncliJNI.ClientAPI_EvalConfig_allowPasswordSave_set(this.swigCPtr, this, value);
    }

    public boolean getAllowPasswordSave() {
        return ovpncliJNI.ClientAPI_EvalConfig_allowPasswordSave_get(this.swigCPtr, this);
    }

    public void setRemoteHost(String value) {
        ovpncliJNI.ClientAPI_EvalConfig_remoteHost_set(this.swigCPtr, this, value);
    }

    public String getRemoteHost() {
        return ovpncliJNI.ClientAPI_EvalConfig_remoteHost_get(this.swigCPtr, this);
    }

    public void setRemotePort(String value) {
        ovpncliJNI.ClientAPI_EvalConfig_remotePort_set(this.swigCPtr, this, value);
    }

    public String getRemotePort() {
        return ovpncliJNI.ClientAPI_EvalConfig_remotePort_get(this.swigCPtr, this);
    }

    public void setRemoteProto(String value) {
        ovpncliJNI.ClientAPI_EvalConfig_remoteProto_set(this.swigCPtr, this, value);
    }

    public String getRemoteProto() {
        return ovpncliJNI.ClientAPI_EvalConfig_remoteProto_get(this.swigCPtr, this);
    }

    public void setServerList(ClientAPI_ServerEntryVector value) {
        ovpncliJNI.ClientAPI_EvalConfig_serverList_set(this.swigCPtr, this, ClientAPI_ServerEntryVector.getCPtr(value), value);
    }

    public ClientAPI_ServerEntryVector getServerList() {
        long cPtr = ovpncliJNI.ClientAPI_EvalConfig_serverList_get(this.swigCPtr, this);
        return cPtr == 0 ? null : new ClientAPI_ServerEntryVector(cPtr, false);
    }

    public ClientAPI_EvalConfig() {
        this(ovpncliJNI.new_ClientAPI_EvalConfig(), true);
    }
}
