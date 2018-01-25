package net.openvpn.openvpn;

public class OpenVPNClientThread extends ClientAPI_OpenVPNClient implements Runnable {
    private int bytes_in_index = -1;
    private int bytes_out_index = -1;
    private boolean connect_called = false;
    private ClientAPI_Status m_connect_status;
    private EventReceiver parent;
    private Thread thread;
    private TunBuilder tun_builder;

    public static class ConnectCalledTwice extends RuntimeException {
    }

    public interface EventReceiver {
        void done(ClientAPI_Status clientAPI_Status);

        void event(ClientAPI_Event clientAPI_Event);

        void external_pki_cert_request(ClientAPI_ExternalPKICertRequest clientAPI_ExternalPKICertRequest);

        void external_pki_sign_request(ClientAPI_ExternalPKISignRequest clientAPI_ExternalPKISignRequest);

        void log(ClientAPI_LogInfo clientAPI_LogInfo);

        boolean pause_on_connection_timeout();

        boolean socket_protect(int i);

        TunBuilder tun_builder_new();
    }

    public interface TunBuilder {
        boolean tun_builder_add_address(String str, int i, String str2, boolean z, boolean z2);

        boolean tun_builder_add_dns_server(String str, boolean z);

        boolean tun_builder_add_route(String str, int i, boolean z);

        boolean tun_builder_add_search_domain(String str);

        int tun_builder_establish();

        boolean tun_builder_exclude_route(String str, int i, boolean z);

        boolean tun_builder_reroute_gw(boolean z, boolean z2, long j);

        boolean tun_builder_set_mtu(int i);

        boolean tun_builder_set_remote_address(String str, boolean z);

        boolean tun_builder_set_session_name(String str);

        void tun_builder_teardown(boolean z);
    }

    public OpenVPNClientThread() {
        int n = ClientAPI_OpenVPNClient.stats_n();
        for (int i = 0; i < n; i++) {
            String name = ClientAPI_OpenVPNClient.stats_name(i);
            if (name.equals("BYTES_IN")) {
                this.bytes_in_index = i;
            }
            if (name.equals("BYTES_OUT")) {
                this.bytes_out_index = i;
            }
        }
    }

    public void connect(EventReceiver parent_arg) {
        if (this.connect_called) {
            throw new ConnectCalledTwice();
        }
        this.connect_called = true;
        this.parent = parent_arg;
        this.m_connect_status = null;
        this.thread = new Thread(this, "OpenVPNClientThread");
        this.thread.start();
    }

    public void wait_thread_short() {
        Thread th = this.thread;
        if (th != null) {
            try {
                th.join(5000);
            } catch (InterruptedException e) {
            }
            if (th.isAlive()) {
                ClientAPI_Status status = new ClientAPI_Status();
                status.setError(true);
                status.setMessage("CORE_THREAD_ABANDONED");
                call_done(status);
            }
        }
    }

    public void wait_thread_long() {
        if (this.thread != null) {
            boolean interrupted;
            do {
                interrupted = false;
                try {
                    this.thread.join();
                    continue;
                } catch (InterruptedException e) {
                    interrupted = true;
                    super.stop();
                    continue;
                }
            } while (interrupted);
        }
    }

    public long bytes_in() {
        return super.stats_value(this.bytes_in_index);
    }

    public long bytes_out() {
        return super.stats_value(this.bytes_out_index);
    }

    private void call_done(ClientAPI_Status status) {
        EventReceiver p = finalize_thread(status);
        if (p != null) {
            p.done(this.m_connect_status);
        }
    }

    private synchronized EventReceiver finalize_thread(ClientAPI_Status connect_status) {
        EventReceiver p;
        p = this.parent;
        if (p != null) {
            this.m_connect_status = connect_status;
            this.parent = null;
            this.tun_builder = null;
            this.thread = null;
        }
        return p;
    }

    public void run() {
        call_done(super.connect());
    }

    public boolean socket_protect(int socket) {
        EventReceiver p = this.parent;
        if (p != null) {
            return p.socket_protect(socket);
        }
        return false;
    }

    public boolean pause_on_connection_timeout() {
        EventReceiver p = this.parent;
        if (p != null) {
            return p.pause_on_connection_timeout();
        }
        return false;
    }

    public void event(ClientAPI_Event event) {
        EventReceiver p = this.parent;
        if (p != null) {
            p.event(event);
        }
    }

    public void log(ClientAPI_LogInfo loginfo) {
        EventReceiver p = this.parent;
        if (p != null) {
            p.log(loginfo);
        }
    }

    public void external_pki_cert_request(ClientAPI_ExternalPKICertRequest req) {
        EventReceiver p = this.parent;
        if (p != null) {
            p.external_pki_cert_request(req);
        }
    }

    public void external_pki_sign_request(ClientAPI_ExternalPKISignRequest req) {
        EventReceiver p = this.parent;
        if (p != null) {
            p.external_pki_sign_request(req);
        }
    }

    public boolean tun_builder_new() {
        EventReceiver p = this.parent;
        if (p == null) {
            return false;
        }
        this.tun_builder = p.tun_builder_new();
        if (this.tun_builder != null) {
            return true;
        }
        return false;
    }

    public boolean tun_builder_set_remote_address(String address, boolean ipv6) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_set_remote_address(address, ipv6);
        }
        return false;
    }

    public boolean tun_builder_add_address(String address, int prefix_length, String gateway, boolean ipv6, boolean net30) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_add_address(address, prefix_length, gateway, ipv6, net30);
        }
        return false;
    }

    public boolean tun_builder_reroute_gw(boolean ipv4, boolean ipv6, long flags) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_reroute_gw(ipv4, ipv6, flags);
        }
        return false;
    }

    public boolean tun_builder_add_route(String address, int prefix_length, int metric, boolean ipv6) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_add_route(address, prefix_length, ipv6);
        }
        return false;
    }

    public boolean tun_builder_exclude_route(String address, int prefix_length, int metric, boolean ipv6) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_exclude_route(address, prefix_length, ipv6);
        }
        return false;
    }

    public boolean tun_builder_add_dns_server(String address, boolean ipv6) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_add_dns_server(address, ipv6);
        }
        return false;
    }

    public boolean tun_builder_add_search_domain(String domain) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_add_search_domain(domain);
        }
        return false;
    }

    public boolean tun_builder_set_mtu(int mtu) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_set_mtu(mtu);
        }
        return false;
    }

    public boolean tun_builder_set_session_name(String name) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_set_session_name(name);
        }
        return false;
    }

    public int tun_builder_establish() {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            return tb.tun_builder_establish();
        }
        return -1;
    }

    public void tun_builder_teardown(boolean disconnect) {
        TunBuilder tb = this.tun_builder;
        if (tb != null) {
            tb.tun_builder_teardown(disconnect);
        }
    }
}
