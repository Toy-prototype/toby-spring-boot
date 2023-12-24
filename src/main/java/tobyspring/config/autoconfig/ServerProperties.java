package tobyspring.config.autoconfig;

public class ServerProperties {
    private String contextPath;
    private int port;

    public ServerProperties(String contextPath, int port) {
        this.contextPath = contextPath;
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public int getPort() {
        return port;
    }
}
