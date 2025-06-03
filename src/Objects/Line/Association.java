package Objects.Line;

import java.awt.*;
import Objects.BasicObjects.ObjectAbstract;

public class Association extends LineAbstract{
    public Association(ObjectAbstract source, ObjectAbstract target, Point sourceP, Point targetP) {
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

        g.drawLine(endPoint.x, endPoint.y, arrowX[0], arrowY[0]);
        g.drawLine(endPoint.x, endPoint.y, arrowX[2], arrowY[2]);
    }
}
