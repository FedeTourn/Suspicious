package frsf.cidisi.faia.examples.situationcalculus.wumpus;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.examples.situationcalculus.wumpus.actions.*;

public class WumpusActionFactory extends ActionFactory {

    private static WumpusActionFactory instance;

    private WumpusActionFactory() {
    }

    public static WumpusActionFactory getInstance() {
        if (instance == null) {
            instance = new WumpusActionFactory();
        }
        return instance;
    }

    @Override
    protected String endActionString() {
        return "noAction";
    }

    @Override
    protected Action stringToAction(String stringAction) {
        Action actionObject = null;

        if (stringAction.equals("climb")) {
            actionObject = new Climb();
        } else if (stringAction.equals("forward")) {
            actionObject = new Forward();
        } else if (stringAction.equals("grab")) {
            actionObject = new Grab();
        } else if (stringAction.equals("shoot")) {
            actionObject = new Shoot();
        } else if (stringAction.equals("turnleft")) {
            actionObject = new TurnLeft();
        } else if (stringAction.equals("turnright")) {
            actionObject = new TurnRight();
        }

        return actionObject;
    }
}
