package Factory;

import java.awt.Point;
import Objects.BasicObjects.ObjectAbstract;
import Objects.BasicObjects.Rect;

public class RectObject implements ObjectInterface {
    
    @Override
    public ObjectAbstract createObject(Point position) {
        return new Rect(position);
    }
}
