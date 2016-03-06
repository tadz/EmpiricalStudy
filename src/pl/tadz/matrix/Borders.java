package pl.tadz.matrix;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

public class Borders implements Randomizable<Stroke> {

	final public static Stroke DOTTED = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
			new float[] { 12, 12 }, 0);
	final public static Stroke DASHED = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0,
			new float[] { 1, 20 }, 0);
	final public static Stroke SOLID_BOLD = new BasicStroke(7);
	final public static Stroke SOLID = new BasicStroke(2);

	@Override
	public List<Stroke> toList() {
		List<Stroke> list = new ArrayList<Stroke>();
		
		list.add(DOTTED);
		list.add(DASHED);
		list.add(SOLID_BOLD);
		list.add(SOLID);
		
		return list;
	}
}
