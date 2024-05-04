package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.Random;

public class NumberGeneratorHelper {
	
	
	public static Random random = new Random(1000);
	
	public static Integer generateRoomId(int roomQuantity) {
		return random.nextInt(roomQuantity);
	}
	
	//1 a 3
	public static Integer generateCrewmateNextJump() {
		return 1 + random.nextInt(3);
	}
	
	//30 a 150
	public static Integer generateInitialAgentEnergy() {
		return 30 + random.nextInt(121);
	}
	
	public static Integer generateNextGlobalPerception() {
		return 3 + random.nextInt(3);
	}
	

}
