package pl.tadz.matrix;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Square extends Figure {

	public Square(Graphics2D ga, AffineTransform transformer) {
		super(ga, transformer);

		this.figure = new Rectangle2D.Double(450, 50, 300, 200);
		this.preTransformationXY = new int[][] { { -400, 0 }, { 0, 0 }, { -400, 300 }, { 0, 300 } };
		this.postTransformationXY = new int[][] { { 400, 0 }, { 0, 0 }, { 400, -300 }, { 0, -300 } };
	}
}
