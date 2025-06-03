package Factory;

import java.awt.Point;
import Objects.BasicObjects.ObjectAbstract;

/* 依賴抽象，不依賴具體實作 */

public interface ObjectInterface {
    public ObjectAbstract createObject(Point position);
}
