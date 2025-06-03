package Objects.Line;

import java.awt.*;
import Objects.BasicObjects.ObjectAbstract;

public class Composition extends LineAbstract {
    public Composition(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP) {
        super.addLine(source, target, sourceP, targetP);
    }

    @Override
    public void draw(Graphics g) {
        Point startPoint = getSource().getAbsolutePort(getStartPort().getPort());
        Point endPoint = getTarget().getAbsolutePort(getEndPort().getPort());

        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

        // 計算箭頭的角度
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        int arrowLength = 15;

        // 箭頭的兩個邊
        int[] arrowX = { (int) Math.round(endPoint.x - arrowLength * Math.cos(angle - Math.PI / 4)),
                endPoint.x, (int) Math.round(endPoint.x - arrowLength * Math.cos(angle + Math.PI / 4)),
                (int) Math.round(endPoint.x - arrowLength * (Math.cos(angle - Math.PI / 4) + Math.cos(angle + Math.PI / 4))) };

        int[] arrowY = { (int) Math.round(endPoint.y - arrowLength * Math.sin(angle - Math.PI / 4)),
                endPoint.y, (int) Math.round(endPoint.y - arrowLength * Math.sin(angle + Math.PI / 4)),
                (int) Math.round(endPoint.y - arrowLength * (Math.sin(angle - Math.PI / 4) + Math.sin(angle + Math.PI / 4))) };

        g.setColor(Color.white);
        g.fillPolygon(new Polygon(arrowX, arrowY, 4));
        g.setColor(Color.BLACK);
        g.drawPolygon(new Polygon(arrowX, arrowY, 4));
    }
}
