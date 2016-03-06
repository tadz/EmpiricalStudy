package pl.tadz.matrix;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Rhomb extends Figure {

	public Rhomb(Graphics2D ga, AffineTransform transformer) {
		super(ga, transformer);

		this.figure = new Polygon(new int[] { 550, 450, 650, 750 }, new int[] { 350, 550, 550, 350 }, 4);
		this.preTransformationXY = new int[][] { { -400, -300 }, { 0, -300 }, { -400, 0 }, { 0, 0 } };
		this.postTransformationXY = new int[][] { { 400, 300 }, { 0, 300 }, { -400, 0 }, { 0, 0 } };
	}

}
