package ua.taky.dogclinic.service.domain;

import java.util.Comparator;

public abstract class Animal {
	
	public static final Comparator<? super Animal> AGE_COMPARATOR = new Comparator<Animal>() {
		/** {@inheritDoc} */
		@Override
		public int compare(Animal lhs, Animal rhs) {
			return rhs.getAge() - lhs.getAge();
		}
	};
	
	private final int age;
	
	protected Animal(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

}
