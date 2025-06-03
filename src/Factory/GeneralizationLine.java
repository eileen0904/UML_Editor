package Factory;

import java.awt.*;
import Objects.Line.LineAbstract;
import Objects.BasicObjects.ObjectAbstract;
import Objects.Line.Generalization;

public class GeneralizationLine implements LineInterface{
    
    @Override
    public LineAbstract addLine(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP) {
        return new Generalization(source, target, sourceP, targetP);
    }
}
