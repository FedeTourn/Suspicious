package frsf.cidisi.faia.examples.situationcalculus.wumpus;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.environment.Environment;

public class WumpusPerception extends SituationCalculusPerception {

    private boolean stench;
    private boolean breeze;
    private boolean glitter;
    private boolean bump;
    private boolean wumpusScream;

    public WumpusPerception() {
        super();

        this.stench = false;
        this.breeze = false;
        this.glitter = false;
        this.bump = false;
        this.wumpusScream = false;
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        StringBuffer perceptionString = new StringBuffer("perception([");

        perceptionString.append(this.stench ? "stench" : "nothing");
        perceptionString.append(",");
        perceptionString.append(this.breeze ? "breeze" : "nothing");
        perceptionString.append(",");
        perceptionString.append(this.glitter ? "glitter" : "nothing");
        perceptionString.append(",");
        perceptionString.append(this.bump ? "bump" : "nothing");
        perceptionString.append(",");
        perceptionString.append(this.wumpusScream ? "wumpusScream" : "nothing");
        perceptionString.append("]");

        perceptionString.append(")");

        return perceptionString.toString();
    }

    public boolean isStench() {
        return stench;
    }

    public void setStench(boolean stench) {
        this.stench = stench;
    }

    public boolean isBreeze() {
        return breeze;
    }

    public void setBreeze(boolean breeze) {
        this.breeze = breeze;
    }

    public boolean isGlitter() {
        return glitter;
    }

    public void setGlitter(boolean glitter) {
        this.glitter = glitter;
    }

    public boolean isBump() {
        return bump;
    }

    public void setBump(boolean bump) {
        this.bump = bump;
    }

    public boolean isWumpusScream() {
        return wumpusScream;
    }

    public void setWumpusScream(boolean wumpusScream) {
        this.wumpusScream = wumpusScream;
    }
}
