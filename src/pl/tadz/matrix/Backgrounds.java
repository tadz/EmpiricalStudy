package pl.tadz.matrix;

import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Backgrounds implements Randomizable<TexturePaint> {

	final public static String STRIPES = "images/stripes.png";
	final public static String WAVES = "images/waves.png";
	final public static String CIRCLES = "images/circles.png";
	final public static String SQUARES = "images/squares.png";

	public TexturePaint getTextureByFilePath(String filePath) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(filePath));
		} catch (IOException e) {
		}

		// Create a round rectangle.
		// Create a texture rectangle the same size as the texture image.
		Rectangle2D tr = new Rectangle2D.Double(0, 0, img.getWidth(), img.getHeight());
		
		// Create the TexturePaint.
		return new TexturePaint(img, tr);
	}

	@Override
	public List<TexturePaint> toList() {
		List<TexturePaint> list = new ArrayList<TexturePaint>();
		
		list.add(getTextureByFilePath(STRIPES));
		list.add(getTextureByFilePath(WAVES));
		list.add(getTextureByFilePath(CIRCLES));
		list.add(getTextureByFilePath(SQUARES));
		
		return list;
	}
}
