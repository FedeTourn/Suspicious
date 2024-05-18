package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class SusProblem extends Problem {

	private HashMap<Action, Integer> actionPriorities; 
	
	public SusProblem(GoalTest goalTest, SearchBasedAgentState initState, Vector<SearchAction> actions) {
		super(goalTest, initState, actions);
		setActions(actions);
	}

    public void setActions(Vector<SearchAction> actions) {
        this.actions = actions;
        actionPriorities = new HashMap<Action, Integer>();
        actions.stream().forEach(a -> actionPriorities.put(a, 1));
    }
    
    public void updateActionPriority(Action action) {  	
    	actionPriorities.put(action, actionPriorities.get(action) + 1);
    	actions = new Vector<SearchAction>(actions.stream()
    					.sorted((a1, a2) -> actionPriorities.get(a1).compareTo(actionPriorities.get(a2)))
    					.collect(Collectors.toList()));
//    	System.out.println("Priorities updated: " + actions);
    }
	
}
