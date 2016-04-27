import java.util.ArrayList;
import java.util.List;

import runnable.Car;

public class Runner {

	public static void main(String[] args) {

		List<Car> cars = new ArrayList<>();
		cars.add(new Car("mercedes",130));
		cars.add(new Car("opel",140));
		cars.add(new Car("audi",150));
		cars.add(new Car("volvo",100));
		cars.add(new Car("bmv",120));

			for(Car car:cars){
				new Thread(car).start();
			}
		
	}

}
