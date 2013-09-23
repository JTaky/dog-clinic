package ua.taky.dogclinic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ua.taky.dogclinic.service.domain.Animal;
import ua.taky.dogclinic.service.domain.Dog;

/**
 * Actually it is a stub. Class has some predefined dogs, but do not persist new. 
 * 
 * @author taky
 */
public class DogsService {
	
	/* CopyOnWriteArrayList technique for collection synchronization 
	 * is much more effective than with mutex */
	@SuppressWarnings("serial")
	private static final List<Dog> dogs = Collections.synchronizedList(new ArrayList<Dog>() {{
		addAll(Arrays.asList(Dog.bornDog(13, "American Eskimo Dog"), 
				Dog.bornDog(1, "Chow Chow"),
				Dog.bornDog(3, "Cockapoo")));
	}});
	
	/**
	 * Load all available dogs in clinic from storage =) 
	 * @return return dogs ordered by age
	 */
	public List<Dog> getDogs(){
		return sortDogs();
	}
	
	/**
	 * Add dog to the storage
	 * @param dog dog to add
	 */
	public void addDog(Dog dog) {
		dogs.add(dog);
	}
	
	private List<Dog> sortDogs() {
		Collections.sort(dogs, Animal.AGE_COMPARATOR);
		return dogs;
	}

}
