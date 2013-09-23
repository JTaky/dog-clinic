package ua.taky.dogclinic.service.domain;

/**
 * Domain object for dog.
 * Cannot be inherited because of adding equals method which violated LSV.
 * So must be unique creatable object in hierarchy.
 *  
 * @author taky
 */
public final class Dog extends Animal {
	
	private final String breed;

	/**
	 * Factory method for a Dog class.
	 * 
	 * @param age dog's age
	 * @param breed dog's breed
	 * @return created and initialized dog
	 */
	public static Dog bornDog(int age, String breed) { //factory method is more declarative
		return new Dog(age, breed);
	}
	
	private Dog(int age, String breed){
		super(age);
		this.breed = breed;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object rhsObj){ //let's violate LSV
		if(rhsObj == null || rhsObj.getClass() != this.getClass()){
			return false;
		}
		Dog rhs = (Dog)rhsObj;
		return this.getAge() == rhs.getAge() && this.getBreed().equals(rhs.getBreed());
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode(){
		// return 42;	//it also should works ;)
		return 31 * getAge() + getBreed().hashCode();
	}

	public String getBreed() {
		return breed;
	}

}
