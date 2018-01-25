package net.openvpn.openvpn;

public class ClientAPI_ConnectionInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_ConnectionInfo(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_ConnectionInfo obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_ConnectionInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setDefined(boolean value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_defined_set(this.swigCPtr, this, value);
    }

    public boolean getDefined() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_defined_get(this.swigCPtr, this);
    }

    public void setUser(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_user_set(this.swigCPtr, this, value);
    }

    public String getUser() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_user_get(this.swigCPtr, this);
    }

    public void setServerHost(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_serverHost_set(this.swigCPtr, this, value);
    }

    public String getServerHost() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_serverHost_get(this.swigCPtr, this);
    }

    public void setServerPort(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_serverPort_set(this.swigCPtr, this, value);
    }

    public String getServerPort() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_serverPort_get(this.swigCPtr, this);
    }

    public void setServerProto(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_serverProto_set(this.swigCPtr, this, value);
    }

    public String getServerProto() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_serverProto_get(this.swigCPtr, this);
    }

    public void setServerIp(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_serverIp_set(this.swigCPtr, this, value);
    }

    public String getServerIp() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_serverIp_get(this.swigCPtr, this);
    }

    public void setVpnIp4(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_vpnIp4_set(this.swigCPtr, this, value);
    }

    public String getVpnIp4() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_vpnIp4_get(this.swigCPtr, this);
    }

    public void setVpnIp6(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_vpnIp6_set(this.swigCPtr, this, value);
    }

    public String getVpnIp6() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_vpnIp6_get(this.swigCPtr, this);
    }

    public void setGw4(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_gw4_set(this.swigCPtr, this, value);
    }

    public String getGw4() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_gw4_get(this.swigCPtr, this);
    }

    public void setGw6(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_gw6_set(this.swigCPtr, this, value);
    }

    public String getGw6() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_gw6_get(this.swigCPtr, this);
    }

    public void setClientIp(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_clientIp_set(this.swigCPtr, this, value);
    }

    public String getClientIp() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_clientIp_get(this.swigCPtr, this);
    }

    public void setTunName(String value) {
        ovpncliJNI.ClientAPI_ConnectionInfo_tunName_set(this.swigCPtr, this, value);
    }

    public String getTunName() {
        return ovpncliJNI.ClientAPI_ConnectionInfo_tunName_get(this.swigCPtr, this);
    }

    public ClientAPI_ConnectionInfo() {
        this(ovpncliJNI.new_ClientAPI_ConnectionInfo(), true);
    }
}
