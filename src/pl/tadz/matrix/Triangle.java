package pl.tadz.matrix;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Triangle extends Figure {


	public Triangle(Graphics2D ga, AffineTransform transformer) {
		super(ga, transformer);

		this.figure = new Polygon(new int[] { 200, 75, 325 }, new int[] { 350, 550, 550 }, 3);
		this.preTransformationXY = new int[][] { { 0, -300 }, { 400, -300 },  { 0, 0 }, { 400, 0 } };
		this.postTransformationXY = new int[][] { { 0, 300 }, { -400, 300 },  { 0, 0 }, { -400, 0 } };
	}
}
