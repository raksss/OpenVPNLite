package net.openvpn.openvpn;

public class ClientAPI_DynamicChallenge {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_DynamicChallenge(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_DynamicChallenge obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_DynamicChallenge(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setChallenge(String value) {
        ovpncliJNI.ClientAPI_DynamicChallenge_challenge_set(this.swigCPtr, this, value);
    }

    public String getChallenge() {
        return ovpncliJNI.ClientAPI_DynamicChallenge_challenge_get(this.swigCPtr, this);
    }

    public void setEcho(boolean value) {
        ovpncliJNI.ClientAPI_DynamicChallenge_echo_set(this.swigCPtr, this, value);
    }

    public boolean getEcho() {
        return ovpncliJNI.ClientAPI_DynamicChallenge_echo_get(this.swigCPtr, this);
    }

    public void setResponseRequired(boolean value) {
        ovpncliJNI.ClientAPI_DynamicChallenge_responseRequired_set(this.swigCPtr, this, value);
    }

    public boolean getResponseRequired() {
        return ovpncliJNI.ClientAPI_DynamicChallenge_responseRequired_get(this.swigCPtr, this);
    }

    public void setStateID(String value) {
        ovpncliJNI.ClientAPI_DynamicChallenge_stateID_set(this.swigCPtr, this, value);
    }

    public String getStateID() {
        return ovpncliJNI.ClientAPI_DynamicChallenge_stateID_get(this.swigCPtr, this);
    }

    public ClientAPI_DynamicChallenge() {
        this(ovpncliJNI.new_ClientAPI_DynamicChallenge(), true);
    }
}
