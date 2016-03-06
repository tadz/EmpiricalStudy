package pl.tadz.matrix;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Colors implements Randomizable<Color> {

	@Override
	public List<Color> toList() {
		List<Color> list = new ArrayList<Color>();
		
		list.add(Color.RED);
		list.add(Color.YELLOW);
		list.add(Color.BLUE);
		list.add(Color.GREEN);
		
		return list;
	}
}
