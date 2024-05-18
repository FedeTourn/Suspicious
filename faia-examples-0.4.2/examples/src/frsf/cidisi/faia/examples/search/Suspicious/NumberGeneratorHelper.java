package frsf.cidisi.faia.examples.search.Suspicious;

import java.time.Instant;
import java.util.Random;

public class NumberGeneratorHelper {
	
	
	public static Random random = new Random(100000);
	
	public static Integer generateRoomId() {
		return random.nextInt(SusEnvironment.ROOM_NAMES.size());
	}
	
	//1 a 3
	public static Integer generateCrewmateNextJump() {
		return generateNumberInRange(1, 3);
	}
	
	//30 a 150
	public static Integer generateInitialAgentEnergy() {
		return generateNumberInRange(30, 150);
	}
	
	//3 a 5
	public static Integer generateNextGlobalPerception() {
		return generateNumberInRange(3, 5);
	}
	
	public static Integer generateListIndex(Integer listSize) {
		return random.nextInt(listSize);
	}
	
	public static Integer generateNumberInRange(Integer inferiorLimit , Integer superiorLimit) {
		return inferiorLimit + random.nextInt(superiorLimit - inferiorLimit + 1);
	}

}
