package Factory;

import java.awt.*;
import Objects.Line.LineAbstract;
import Objects.BasicObjects.ObjectAbstract;
import Objects.Line.Composition;

public class CompositionLine implements LineInterface{
    @Override
    public LineAbstract addLine(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP) {
        return new Composition(source, target, sourceP, targetP);
    }
}
