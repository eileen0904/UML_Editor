package Objects.BasicObjects;

import java.awt.*;

import Objects.Label;
import Objects.Port;

public class Rect extends ObjectAbstract {

    public Rect(Point position) {
        super.setPosition(position);
        super.setName("rect");
        super.setHeight(150);
        super.setWidth(100);
        super.setLabel(new Label("rect"));
        initializePorts();
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(getPosition().x, getPosition().y, getWidth(), getHeight());

        if(isSelected()) { // draw ports
            g.setColor(Color.BLACK);
            for(Port port : portList) {
                Point absolutePoint = getAbsolutePort(port.getPort());
                g.fillRect(absolutePoint.x - 3, absolutePoint.y - 3, 8, 8);
            }
        }

        if(getLabel().isEnabled()) { // draw label
            getLabel().draw(g, getPosition(), getWidth(), getHeight());
        }
    }

    @Override
    public void initializePorts() {
        portList.add(new Port(new Point(getWidth() / 2, 0), "N")); // 中上
        portList.add(new Port(new Point(getWidth() / 2, getHeight()), "S")); // 中下
        portList.add(new Port(new Point(0, getHeight() / 2), "W")); // 中左
        portList.add(new Port(new Point(getWidth(), getHeight() / 2), "E")); // 中右

        portList.add(new Port(new Point(0, 0), "LN")); // 左上
        portList.add(new Port(new Point(getWidth(), 0), "RN")); // 右上
        portList.add(new Port(new Point(0, getHeight()), "LS")); // 左下
        portList.add(new Port(new Point(getWidth(), getHeight()), "RS")); // 右下
    }
}
