package Factory;

import java.awt.Point;
import Objects.BasicObjects.ObjectAbstract;
import Objects.BasicObjects.Oval;

public class OvalObject implements ObjectInterface {
    
    @Override
    public ObjectAbstract createObject(Point position) {
        return new Oval(position);
    }
}
