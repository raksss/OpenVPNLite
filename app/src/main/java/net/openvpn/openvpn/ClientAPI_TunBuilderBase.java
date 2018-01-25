package net.openvpn.openvpn;

public class ClientAPI_TunBuilderBase {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ClientAPI_TunBuilderBase(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ClientAPI_TunBuilderBase obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_TunBuilderBase(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean tun_builder_new() {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_new(this.swigCPtr, this);
    }

    public boolean tun_builder_set_layer(int layer) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_layer(this.swigCPtr, this, layer);
    }

    public boolean tun_builder_set_remote_address(String address, boolean ipv6) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_remote_address(this.swigCPtr, this, address, ipv6);
    }

    public boolean tun_builder_add_address(String address, int prefix_length, String gateway, boolean ipv6, boolean net30) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_add_address(this.swigCPtr, this, address, prefix_length, gateway, ipv6, net30);
    }

    public boolean tun_builder_set_route_metric_default(int metric) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_route_metric_default(this.swigCPtr, this, metric);
    }

    public boolean tun_builder_reroute_gw(boolean ipv4, boolean ipv6, long flags) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_reroute_gw(this.swigCPtr, this, ipv4, ipv6, flags);
    }

    public boolean tun_builder_add_route(String address, int prefix_length, int metric, boolean ipv6) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_add_route(this.swigCPtr, this, address, prefix_length, metric, ipv6);
    }

    public boolean tun_builder_exclude_route(String address, int prefix_length, int metric, boolean ipv6) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_exclude_route(this.swigCPtr, this, address, prefix_length, metric, ipv6);
    }

    public boolean tun_builder_add_dns_server(String address, boolean ipv6) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_add_dns_server(this.swigCPtr, this, address, ipv6);
    }

    public boolean tun_builder_add_search_domain(String domain) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_add_search_domain(this.swigCPtr, this, domain);
    }

    public boolean tun_builder_set_mtu(int mtu) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_mtu(this.swigCPtr, this, mtu);
    }

    public boolean tun_builder_set_session_name(String name) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_session_name(this.swigCPtr, this, name);
    }

    public boolean tun_builder_add_proxy_bypass(String bypass_host) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_add_proxy_bypass(this.swigCPtr, this, bypass_host);
    }

    public boolean tun_builder_set_proxy_auto_config_url(String url) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_proxy_auto_config_url(this.swigCPtr, this, url);
    }

    public boolean tun_builder_set_proxy_http(String host, int port) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_proxy_http(this.swigCPtr, this, host, port);
    }

    public boolean tun_builder_set_proxy_https(String host, int port) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_proxy_https(this.swigCPtr, this, host, port);
    }

    public boolean tun_builder_add_wins_server(String address) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_add_wins_server(this.swigCPtr, this, address);
    }

    public boolean tun_builder_set_block_ipv6(boolean block_ipv6) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_block_ipv6(this.swigCPtr, this, block_ipv6);
    }

    public boolean tun_builder_set_adapter_domain_suffix(String name) {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_set_adapter_domain_suffix(this.swigCPtr, this, name);
    }

    public int tun_builder_establish() {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_establish(this.swigCPtr, this);
    }

    public boolean tun_builder_persist() {
        return ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_persist(this.swigCPtr, this);
    }

    public void tun_builder_establish_lite() {
        ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_establish_lite(this.swigCPtr, this);
    }

    public void tun_builder_teardown(boolean disconnect) {
        ovpncliJNI.ClientAPI_TunBuilderBase_tun_builder_teardown(this.swigCPtr, this, disconnect);
    }

    public ClientAPI_TunBuilderBase() {
        this(ovpncliJNI.new_ClientAPI_TunBuilderBase(), true);
    }
}
