package by.pvt.epam.entity;

import java.util.Random;

public class Tree extends Plant {

	private int diameter;

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	@Override
	public void increaseHeight() {
		Random rand = new Random();
		int max = 40;
		int min = 10;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		double growth = ((double) randomNum) / 10;
		setHeight(getHeight() + growth);
	}

	@Override
	public String toString() {
		return "TREE (name=" + getName() + ", height=" + getHeight()
				+ " meters" + ", group=" + getGroup() + ", diameter="
				+ getDiameter() + ")";
	}

}
