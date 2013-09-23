package ua.taky.dogclinic.service.domain;

import org.junit.Assert;
import org.junit.Test;

public class DogTest {
	
	private static final String STANDART_BREED = "Breed1";
	
	@Test
	public void equalsTestShouldBeEqual(){
		Dog dog7 = Dog.bornDog(7, STANDART_BREED);
		Dog dog007 = Dog.bornDog(007, STANDART_BREED);
		Assert.assertEquals(dog7, dog007);
	}
	
	@Test
	public void equalsTestShouldBeRefletive(){
		Dog dog7 = Dog.bornDog(7, STANDART_BREED);
		
		Assert.assertEquals(dog7, dog7);
	}
	
	@Test
	public void equalsTestShouldSymetric(){
		Dog dog7 = Dog.bornDog(7, STANDART_BREED);
		Dog dog007 = Dog.bornDog(007, STANDART_BREED);
		
		Assert.assertEquals(dog7, dog007);
		Assert.assertEquals(dog007, dog7);
	}
	
	@Test
	public void equalsTestShouldBeTransitive(){
		Dog dog7 = Dog.bornDog(7, STANDART_BREED);
		Dog dog007 = Dog.bornDog(007, STANDART_BREED);
		Dog dog07 = Dog.bornDog(07, STANDART_BREED);
		
		Assert.assertEquals(dog7, dog007);
		Assert.assertEquals(dog7, dog07);
		Assert.assertEquals(dog07, dog007);
	}
	
	@Test
	public void equalsTestShouldNotBeEqualNull(){
		Dog dog7 = Dog.bornDog(7, STANDART_BREED);
		
		Assert.assertNotEquals(dog7, null);
	}
	
	@Test
	public void equalsTestShouldNotBeEqualIfAge(){
		Dog dog7 = Dog.bornDog(7, STANDART_BREED);
		Dog dog17 = Dog.bornDog(17, STANDART_BREED);
		
		Assert.assertNotEquals(dog7, dog17);
	}
	
	@Test
	public void equalsTestShouldNotBeEqualIfBred(){
		Dog dog7 = Dog.bornDog(7, STANDART_BREED);
		Dog dog17 = Dog.bornDog(7, STANDART_BREED + "_");
		
		Assert.assertNotEquals(dog7, dog17);
	}
	
	@Test
	public void equalsHCTestShouldHasSameHashCode(){
		Dog dog7 = Dog.bornDog(7, STANDART_BREED);
		Dog dog007 = Dog.bornDog(007, STANDART_BREED);
		
		Assert.assertEquals(dog7, dog007);
		Assert.assertEquals(dog7.hashCode(), dog007.hashCode());
	}

}
