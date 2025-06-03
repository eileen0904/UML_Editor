package Factory;

import java.awt.*;
import Objects.Line.LineAbstract;
import Objects.BasicObjects.ObjectAbstract;
import Objects.Line.Association;

public class AssociationLine implements LineInterface{
    
    @Override
    public LineAbstract addLine(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP) {
        return new Association(source, target, sourceP, targetP);
    }
}
