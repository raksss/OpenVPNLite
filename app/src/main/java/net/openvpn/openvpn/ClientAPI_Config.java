package net.openvpn.openvpn;

public class ClientAPI_Config {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_Config(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_Config obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_Config(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setContent(String value) {
        ovpncliJNI.ClientAPI_Config_content_set(this.swigCPtr, this, value);
    }

    public String getContent() {
        return ovpncliJNI.ClientAPI_Config_content_get(this.swigCPtr, this);
    }

    public void setContentList(SWIGTYPE_p_std__vectorT_openvpn__ClientAPI__KeyValue_t value) {
        ovpncliJNI.ClientAPI_Config_contentList_set(this.swigCPtr, this, SWIGTYPE_p_std__vectorT_openvpn__ClientAPI__KeyValue_t.getCPtr(value));
    }

    public SWIGTYPE_p_std__vectorT_openvpn__ClientAPI__KeyValue_t getContentList() {
        long cPtr = ovpncliJNI.ClientAPI_Config_contentList_get(this.swigCPtr, this);
        return cPtr == 0 ? null : new SWIGTYPE_p_std__vectorT_openvpn__ClientAPI__KeyValue_t(cPtr, false);
    }

    public void setGuiVersion(String value) {
        ovpncliJNI.ClientAPI_Config_guiVersion_set(this.swigCPtr, this, value);
    }

    public String getGuiVersion() {
        return ovpncliJNI.ClientAPI_Config_guiVersion_get(this.swigCPtr, this);
    }

    public void setServerOverride(String value) {
        ovpncliJNI.ClientAPI_Config_serverOverride_set(this.swigCPtr, this, value);
    }

    public String getServerOverride() {
        return ovpncliJNI.ClientAPI_Config_serverOverride_get(this.swigCPtr, this);
    }

    public void setPortOverride(String value) {
        ovpncliJNI.ClientAPI_Config_portOverride_set(this.swigCPtr, this, value);
    }

    public String getPortOverride() {
        return ovpncliJNI.ClientAPI_Config_portOverride_get(this.swigCPtr, this);
    }

    public void setProtoOverride(String value) {
        ovpncliJNI.ClientAPI_Config_protoOverride_set(this.swigCPtr, this, value);
    }

    public String getProtoOverride() {
        return ovpncliJNI.ClientAPI_Config_protoOverride_get(this.swigCPtr, this);
    }

    public void setIpv6(String value) {
        ovpncliJNI.ClientAPI_Config_ipv6_set(this.swigCPtr, this, value);
    }

    public String getIpv6() {
        return ovpncliJNI.ClientAPI_Config_ipv6_get(this.swigCPtr, this);
    }

    public void setConnTimeout(int value) {
        ovpncliJNI.ClientAPI_Config_connTimeout_set(this.swigCPtr, this, value);
    }

    public int getConnTimeout() {
        return ovpncliJNI.ClientAPI_Config_connTimeout_get(this.swigCPtr, this);
    }

    public void setTunPersist(boolean value) {
        ovpncliJNI.ClientAPI_Config_tunPersist_set(this.swigCPtr, this, value);
    }

    public boolean getTunPersist() {
        return ovpncliJNI.ClientAPI_Config_tunPersist_get(this.swigCPtr, this);
    }

    public void setGoogleDnsFallback(boolean value) {
        ovpncliJNI.ClientAPI_Config_googleDnsFallback_set(this.swigCPtr, this, value);
    }

    public boolean getGoogleDnsFallback() {
        return ovpncliJNI.ClientAPI_Config_googleDnsFallback_get(this.swigCPtr, this);
    }

    public void setSynchronousDnsLookup(boolean value) {
        ovpncliJNI.ClientAPI_Config_synchronousDnsLookup_set(this.swigCPtr, this, value);
    }

    public boolean getSynchronousDnsLookup() {
        return ovpncliJNI.ClientAPI_Config_synchronousDnsLookup_get(this.swigCPtr, this);
    }

    public void setAutologinSessions(boolean value) {
        ovpncliJNI.ClientAPI_Config_autologinSessions_set(this.swigCPtr, this, value);
    }

    public boolean getAutologinSessions() {
        return ovpncliJNI.ClientAPI_Config_autologinSessions_get(this.swigCPtr, this);
    }

    public void setExternalPkiAlias(String value) {
        ovpncliJNI.ClientAPI_Config_externalPkiAlias_set(this.swigCPtr, this, value);
    }

    public String getExternalPkiAlias() {
        return ovpncliJNI.ClientAPI_Config_externalPkiAlias_get(this.swigCPtr, this);
    }

    public void setDisableClientCert(boolean value) {
        ovpncliJNI.ClientAPI_Config_disableClientCert_set(this.swigCPtr, this, value);
    }

    public boolean getDisableClientCert() {
        return ovpncliJNI.ClientAPI_Config_disableClientCert_get(this.swigCPtr, this);
    }

    public void setSslDebugLevel(int value) {
        ovpncliJNI.ClientAPI_Config_sslDebugLevel_set(this.swigCPtr, this, value);
    }

    public int getSslDebugLevel() {
        return ovpncliJNI.ClientAPI_Config_sslDebugLevel_get(this.swigCPtr, this);
    }

    public void setCompressionMode(String value) {
        ovpncliJNI.ClientAPI_Config_compressionMode_set(this.swigCPtr, this, value);
    }

    public String getCompressionMode() {
        return ovpncliJNI.ClientAPI_Config_compressionMode_get(this.swigCPtr, this);
    }

    public void setPrivateKeyPassword(String value) {
        ovpncliJNI.ClientAPI_Config_privateKeyPassword_set(this.swigCPtr, this, value);
    }

    public String getPrivateKeyPassword() {
        return ovpncliJNI.ClientAPI_Config_privateKeyPassword_get(this.swigCPtr, this);
    }

    public void setDefaultKeyDirection(int value) {
        ovpncliJNI.ClientAPI_Config_defaultKeyDirection_set(this.swigCPtr, this, value);
    }

    public int getDefaultKeyDirection() {
        return ovpncliJNI.ClientAPI_Config_defaultKeyDirection_get(this.swigCPtr, this);
    }

    public void setForceAesCbcCiphersuites(boolean value) {
        ovpncliJNI.ClientAPI_Config_forceAesCbcCiphersuites_set(this.swigCPtr, this, value);
    }

    public boolean getForceAesCbcCiphersuites() {
        return ovpncliJNI.ClientAPI_Config_forceAesCbcCiphersuites_get(this.swigCPtr, this);
    }

    public void setTlsVersionMinOverride(String value) {
        ovpncliJNI.ClientAPI_Config_tlsVersionMinOverride_set(this.swigCPtr, this, value);
    }

    public String getTlsVersionMinOverride() {
        return ovpncliJNI.ClientAPI_Config_tlsVersionMinOverride_get(this.swigCPtr, this);
    }

    public void setTlsCertProfileOverride(String value) {
        ovpncliJNI.ClientAPI_Config_tlsCertProfileOverride_set(this.swigCPtr, this, value);
    }

    public String getTlsCertProfileOverride() {
        return ovpncliJNI.ClientAPI_Config_tlsCertProfileOverride_get(this.swigCPtr, this);
    }

    public void setPeerInfo(SWIGTYPE_p_std__vectorT_openvpn__ClientAPI__KeyValue_t value) {
        ovpncliJNI.ClientAPI_Config_peerInfo_set(this.swigCPtr, this, SWIGTYPE_p_std__vectorT_openvpn__ClientAPI__KeyValue_t.getCPtr(value));
    }

    public SWIGTYPE_p_std__vectorT_openvpn__ClientAPI__KeyValue_t getPeerInfo() {
        long cPtr = ovpncliJNI.ClientAPI_Config_peerInfo_get(this.swigCPtr, this);
        return cPtr == 0 ? null : new SWIGTYPE_p_std__vectorT_openvpn__ClientAPI__KeyValue_t(cPtr, false);
    }

    public void setProxyHost(String value) {
        ovpncliJNI.ClientAPI_Config_proxyHost_set(this.swigCPtr, this, value);
    }

    public String getProxyHost() {
        return ovpncliJNI.ClientAPI_Config_proxyHost_get(this.swigCPtr, this);
    }

    public void setProxyPort(String value) {
        ovpncliJNI.ClientAPI_Config_proxyPort_set(this.swigCPtr, this, value);
    }

    public String getProxyPort() {
        return ovpncliJNI.ClientAPI_Config_proxyPort_get(this.swigCPtr, this);
    }

    public void setProxyUsername(String value) {
        ovpncliJNI.ClientAPI_Config_proxyUsername_set(this.swigCPtr, this, value);
    }

    public String getProxyUsername() {
        return ovpncliJNI.ClientAPI_Config_proxyUsername_get(this.swigCPtr, this);
    }

    public void setProxyPassword(String value) {
        ovpncliJNI.ClientAPI_Config_proxyPassword_set(this.swigCPtr, this, value);
    }

    public String getProxyPassword() {
        return ovpncliJNI.ClientAPI_Config_proxyPassword_get(this.swigCPtr, this);
    }

    public void setProxyAllowCleartextAuth(boolean value) {
        ovpncliJNI.ClientAPI_Config_proxyAllowCleartextAuth_set(this.swigCPtr, this, value);
    }

    public boolean getProxyAllowCleartextAuth() {
        return ovpncliJNI.ClientAPI_Config_proxyAllowCleartextAuth_get(this.swigCPtr, this);
    }

    public void setAltProxy(boolean value) {
        ovpncliJNI.ClientAPI_Config_altProxy_set(this.swigCPtr, this, value);
    }

    public boolean getAltProxy() {
        return ovpncliJNI.ClientAPI_Config_altProxy_get(this.swigCPtr, this);
    }

    public void setDco(boolean value) {
        ovpncliJNI.ClientAPI_Config_dco_set(this.swigCPtr, this, value);
    }

    public boolean getDco() {
        return ovpncliJNI.ClientAPI_Config_dco_get(this.swigCPtr, this);
    }

    public void setEcho(boolean value) {
        ovpncliJNI.ClientAPI_Config_echo_set(this.swigCPtr, this, value);
    }

    public boolean getEcho() {
        return ovpncliJNI.ClientAPI_Config_echo_get(this.swigCPtr, this);
    }

    public void setInfo(boolean value) {
        ovpncliJNI.ClientAPI_Config_info_set(this.swigCPtr, this, value);
    }

    public boolean getInfo() {
        return ovpncliJNI.ClientAPI_Config_info_get(this.swigCPtr, this);
    }

    public void setClockTickMS(long value) {
        ovpncliJNI.ClientAPI_Config_clockTickMS_set(this.swigCPtr, this, value);
    }

    public long getClockTickMS() {
        return ovpncliJNI.ClientAPI_Config_clockTickMS_get(this.swigCPtr, this);
    }

    public void setGremlinConfig(String value) {
        ovpncliJNI.ClientAPI_Config_gremlinConfig_set(this.swigCPtr, this, value);
    }

    public String getGremlinConfig() {
        return ovpncliJNI.ClientAPI_Config_gremlinConfig_get(this.swigCPtr, this);
    }

    public ClientAPI_Config() {
        this(ovpncliJNI.new_ClientAPI_Config(), true);
    }
}
