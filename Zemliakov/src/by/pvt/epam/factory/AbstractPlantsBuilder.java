package by.pvt.epam.factory;

import java.util.HashSet;
import java.util.Set;
import by.pvt.epam.entity.Plant;

public abstract class AbstractPlantsBuilder {
	protected Set<Plant> plants;

	public AbstractPlantsBuilder() {
		plants = new HashSet<Plant>();
	}

	public AbstractPlantsBuilder(Set<Plant> students) {
		this.plants = students;
	}

	public Set<Plant> getPlants() {
		return plants;
	}

	abstract public void buildSetPlants(String fileName);
}
