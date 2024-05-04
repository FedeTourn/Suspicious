package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.ArrayList;

public class RoomState {
	
	private int id;
	private boolean hasSabotageTask;
	private ArrayList<Crewmate> crewmates;
	
	
	public RoomState(int id) {
		this.id = id;
		this.crewmates = new ArrayList<Crewmate>();
	}


	public String toString() {
		String result = "";
		result += id + " - " + SusEnvironment.ROOM_NAMES.get(id) + "\n";
		for (Crewmate cw : crewmates) {
			result += "Crewmate " + cw.getId() + ": ";
			if (cw.getState() == Crewmate.STATE_ALIVE) {
				result += "Next Jump: " + cw.getNextJump() + "\n";
			} else {
				result += "DEAD \n";
			}
		}
		return result;
	}
	
	public int getId() {
		return id;
	}

	public boolean getHasSabotageTask() {
		return hasSabotageTask;
	}


	public void setHasSabotageTask(boolean hasSabotageTask) {
		this.hasSabotageTask = hasSabotageTask;
	}


	public ArrayList<Crewmate> getCrewmates() {
		return crewmates;
	}


	public void setCrewmates(ArrayList<Crewmate> crewmates) {
		this.crewmates = crewmates;
	}

}