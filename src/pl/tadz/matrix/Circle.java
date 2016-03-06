package pl.tadz.matrix;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Circle extends Figure {

	public Circle(Graphics2D ga, AffineTransform transformer) {
		super(ga, transformer);

		this.figure = new Ellipse2D.Double(100, 50, 200, 200);
		this.preTransformationXY = new int[][] { { 0, 0 }, { 400, 0 }, { 0, 300 }, { 400, 300 } };
		this.postTransformationXY = new int[][] { { 0, 0 }, { -400, 0 }, { 0, -300 }, { -400, -300 } };
	}
}
