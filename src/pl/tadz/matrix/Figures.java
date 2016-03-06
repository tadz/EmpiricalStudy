package pl.tadz.matrix;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Figures implements Randomizable<Figure> {

	protected Graphics2D ga;
	protected AffineTransform transformer;

	public Figures(Graphics2D ga, AffineTransform transformer) {
		this.ga = ga;
		this.transformer = transformer;
	}

	@Override
	public List<Figure> toList() {
		List<Figure> list = new ArrayList<Figure>();
		
		list.add(new Rhomb(ga, transformer));
		list.add(new Square(ga, transformer));
		list.add(new Triangle(ga, transformer));
		list.add(new Circle(ga, transformer));
		
		return list;
	}

}
