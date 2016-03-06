package pl.tadz.matrix;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Randomizer<E> {

	private List<E> elements;
	private Random random;
	
	public Randomizer(List<E> elements) {
		this.elements = elements;
	}

	public E getRandomElement() {
		E element;

		Collections.shuffle(this.elements);
		
		try {
			element = this.elements.remove(getIndex());
		}
		catch (IndexOutOfBoundsException e) {
			return null;
		}
		
		return element;
	}

	private int getIndex() {
		int n = this.elements.size() - 1;
		int index;
		
		if (0 == n) {
			index = n;
		}
		else {
			index = getRandom().nextInt(n);
		}
		
		return index;
	}
	
	private Random getRandom() {
		if (null == random) {
			random = new Random();
		}
		
		return random;
	}
}
