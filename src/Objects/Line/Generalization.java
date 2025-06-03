package Objects.Line;

import java.awt.*;
import Objects.BasicObjects.ObjectAbstract;

public class Generalization extends LineAbstract{
    public Generalization(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP) {
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
        int[] arrowX = { (int) (endPoint.x - arrowLength * Math.cos(angle - Math.PI / 6)),
                endPoint.x, (int) (endPoint.x - arrowLength * Math.cos(angle + Math.PI / 6)) };

        int[] arrowY = { (int) (endPoint.y - arrowLength * Math.sin(angle - Math.PI / 6)),
                endPoint.y, (int) (endPoint.y - arrowLength * Math.sin(angle + Math.PI / 6)) };

        // 畫出箭頭的三角形
        g.setColor(Color.white);
        g.fillPolygon(new Polygon(arrowX, arrowY, 3));
        g.setColor(Color.BLACK);
        g.drawPolygon(new Polygon(arrowX, arrowY, 3));
    }
}
