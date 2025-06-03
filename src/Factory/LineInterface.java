package Factory;

import java.awt.*;
import Objects.BasicObjects.ObjectAbstract;
import Objects.Line.LineAbstract;

public interface LineInterface {
    public LineAbstract addLine(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP);
}
