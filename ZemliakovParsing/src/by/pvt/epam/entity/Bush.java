package by.pvt.epam.entity;

import java.util.Random;

public class Bush extends Plant {

	private int stem;

	public int getStem() {
		return stem;
	}

	public void setStem(int stem) {
		this.stem = stem;
	}

	@Override
	public void increaseHeight() {
		Random rand = new Random();
		double growth = ((double) rand.nextInt(10)) / 10;
		setHeight(getHeight() + growth);
	}

	@Override
	public String toString() {
		return "BUSH (name=" + getName() + ", height=" + getHeight()
				+ " meters" + ", group=" + getGroup() + ", stem="
				+ getStem() + ")";
	}

}
