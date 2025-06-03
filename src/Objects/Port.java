package Objects;

import java.awt.*;
import java.util.ArrayList;

public class Port {
    private Point port;
    private ArrayList<Port> targetPorts = new ArrayList<>();
    private String direction;

    public Port(Point port, String direction) {
        this.port = port;
        this.direction = direction;
    }

    public void setPort(Point port) {
        this.port = port;
    }

    public void addTargetPort(Port targetPort) {
        this.targetPorts.add(targetPort);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Point getPort() {
        return port;
    }

    public ArrayList<Port> getTargetPorts() {
        return targetPorts;
    }

    public String getDirection() {
        return direction;
    }
}
