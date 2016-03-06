package pl.tadz.matrix;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;

abstract public class Figure {

	protected Shape figure;
	protected TexturePaint background = new Backgrounds().getTextureByFilePath(Backgrounds.CIRCLES);
	protected Stroke border = Borders.SOLID;
	protected Color color = Color.BLACK;
	protected Graphics2D ga;
	protected AffineTransform transformer;
	protected int quarter = 1;
	protected int[][] preTransformationXY;
	protected int[][] postTransformationXY;

	public Figure(Graphics2D ga, AffineTransform transformer) {
		this.ga = ga;
		this.transformer = transformer;
	}

	public void setBackground(TexturePaint background) {
		this.background = background;
	}

	public void setBorder(Stroke border) {
		this.border = border;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setGa(Graphics2D ga) {
		this.ga = ga;
	}

	public void setTransformer(AffineTransform transformer) {
		this.transformer = transformer;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	protected void draw() {
		doTransformationActions(preTransformationXY);

		ga.setPaint(color);
		ga.fill(figure);

		ga.setPaint(background);
		ga.fill(figure);

		ga.setPaint(Color.black);
		ga.setStroke(border);

		ga.draw(figure);

		doTransformationActions(postTransformationXY);
	}

	protected void doTransformationActions(int[][] transformationXY) {
		if (null == transformationXY) {
			return;
		}

		if (0 == transformationXY.length) {
			return;
		}

		int[] xy = transformationXY[quarter];

		if (0 == xy.length) {
			return;
		}

		transformer.translate(xy[0], xy[1]);
		ga.setTransform(transformer);
	}
}
