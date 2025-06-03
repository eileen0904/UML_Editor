package Objects.Line;

import java.awt.*;

import Objects.CompositeObject;
import Objects.Port;
import Objects.BasicObjects.ObjectAbstract;

public class LineAbstract {
    private ObjectAbstract source;
    private ObjectAbstract target;
    private Port startPort, endPort;

    public void addLine(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP) {
        checkObjects(source, target, sourceP, targetP);
        this.startPort = source.PointToPort(sourceP);
        this.endPort = target.PointToPort(targetP);
    }

    private void checkObjects(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP) {
        if(source instanceof CompositeObject) {
            this.source = ((CompositeObject) source).findObject(sourceP);
        } 
        else {
            this.source = source;
        }
        if(target instanceof CompositeObject) {
            this.target = ((CompositeObject) target).findObject(targetP);
        } 
        else {
            this.target = target;
        }
    }

    public void draw(Graphics g){
    }

    public ObjectAbstract getSource() {
        return source;
    }

    public ObjectAbstract getTarget() {
        return target;
    }

    public Port getStartPort() {
        return startPort;
    }

    public Port getEndPort() {
        return endPort;
    }

    public void setStartPort(Port startPort) {
        this.startPort = startPort;
    }

    public void setEndPort(Port endPort) {
        this.endPort = endPort;
    }
}
