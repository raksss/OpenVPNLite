package net.openvpn.openvpn;

public class CPUUsage {
    private double end_time = 0.0d;
    private boolean halted = false;
    private double start_time = cpu_usage();

    private static native double cpu_usage();

    public double usage() {
        return (this.halted ? this.end_time : cpu_usage()) - this.start_time;
    }

    public void stop() {
        if (!this.halted) {
            this.end_time = cpu_usage();
            this.halted = true;
        }
    }
}
