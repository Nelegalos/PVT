package by.pvt.epam.factory;

public class Garden {

	public static void main(String[] args) {

		PlantBuilderFactory sFactory = new PlantBuilderFactory();
		AbstractPlantsBuilder builder = sFactory.createPlantBuilder("stax");
		builder.buildSetPlants("garden.xml");
		System.out.println(builder.getPlants());
	}

}
