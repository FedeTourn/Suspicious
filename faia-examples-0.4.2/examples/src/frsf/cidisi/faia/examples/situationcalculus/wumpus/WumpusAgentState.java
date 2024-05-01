package frsf.cidisi.faia.examples.situationcalculus.wumpus;

import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.situationcalculus.KnowledgeBase;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import java.util.Hashtable;

public class WumpusAgentState extends KnowledgeBase {

    public WumpusAgentState() throws PrologConnectorException {
        super("wumpus_world.pl");

        this.initState();
    }

    @Override
    public ActionFactory getActionFactory() {
        return WumpusActionFactory.getInstance();
    }

    @Override
    public String getSituationPredicate() {
        return "actualSituation";
    }

    @Override
    public String getBestActionPredicate() {
        return "bestAction";
    }

    @Override
    public String getExecutedActionPredicate() {
        return "action";
    }

    @Override
    public void updateState(Perception p) {
        this.tell((SituationCalculusPerception) p);
    }

    @Override
    public void initState() {
        this.addKnowledge("position([0,0],0)");
        this.addKnowledge("orientation(right,0)");
        this.addKnowledge("holding(arrow,0)");
    }

    private String getOrientation() {
        String positionQuery = "orientation(O," + this.getSituation() + ")";

        Hashtable[] pos = this.query(positionQuery);

        String orientation = pos[0].get("O").toString();

        return orientation;
    }

    private int[] getPosition() {
        String positionQuery = "position([X,Y]," + this.getSituation() + ")";

        Hashtable[] pos = this.query(positionQuery);

        int x = Integer.parseInt(pos[0].get("X").toString());
        int y = Integer.parseInt(pos[0].get("Y").toString());

        return new int[]{x, y};
    }

    private int getRow() {
        return this.getPosition()[0];
    }

    private int getColumn() {
        return this.getPosition()[1];
    }

    private String getCellString(int row, int col) {

        String cellStateQuery;

        cellStateQuery = "position([" + row + "," + col + "], " + this.getSituation() + ")";
        if (this.queryHasSolution(cellStateQuery)) {
            return "A";
        }

        cellStateQuery = "glitter([" + row + "," + col + "]," + this.getSituation() + ")";
        if (this.queryHasSolution(cellStateQuery)) {
            return "/";
        }

        cellStateQuery = "belief(wumpus,[" + row + "," + col + "]," + this.getSituation() + ")";
        if (this.queryHasSolution(cellStateQuery)) {
            return "w";
        }

        cellStateQuery = "belief(pit,[" + row + "," + col + "]," + this.getSituation() + ")";
        if (this.queryHasSolution(cellStateQuery)) {
            return "p";
        }

        cellStateQuery = "stench([" + row + "," + col + "]," + this.getSituation() + ")";
        if (this.queryHasSolution(cellStateQuery)) {
            return "~";
        }

        cellStateQuery = "breeze([" + row + "," + col + "]," + this.getSituation() + ")";
        if (this.queryHasSolution(cellStateQuery)) {
            return "*";
        }

        cellStateQuery = "at(nothing,[" + row + "," + col + "]," + this.getSituation() + ")";
        if (this.queryHasSolution(cellStateQuery)) {
            return " ";
        }

        cellStateQuery = "unknown([" + row + "," + col + "]," + this.getSituation() + ")";
        if (this.queryHasSolution(cellStateQuery)) {
            return "X";
        }

        return null;
    }

    @Override
    public String toString() {
        String str = "";

        str = str + "[ \n";
        for (int row = 0; row < WumpusEnvironmentState.WORLD_LENGTH; row++) {
            str = str + "[ ";

            for (int col = 0; col < WumpusEnvironmentState.WORLD_LENGTH; col++) {
                str = str + this.getCellString(row, col) + " ";
            }

            str = str + " ]\n";
        }
        str = str + " ]\n";

        // Orientation
        str = str + "Position: (" + this.getRow() + "," + this.getColumn() + ")\n";
        str = str + "Orientation: " + this.getOrientation();

        return str;
    }
}
