package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.Random;

public class Crewmate {

	
	public static int STATE_ALIVE = 1; 
	public static int STATE_DEAD = 2; 
	
	private int id;
	private int nextJump;
	private int state;
	private RoomState room;
	
	public Crewmate(int id, int nextJump, RoomState room) {
		this.id = id;
		this.nextJump = nextJump;
		this.room = room;
		this.state = STATE_ALIVE;
	}

	public int getNextJump() {
		return nextJump;
	}

	public void setNextJump(int nextJump) {
		this.nextJump = nextJump;
	}

	public int getId() {
		return id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public RoomState getRoom() {
		return room;
	}

	public void setRoom(RoomState room) {
		this.room = room;
	}
	
	
}
