package price.weston.amazeing;

import javafx.scene.control.Cell;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

/**
 * CellBlock represents a single cell or 'block' of a maze. Note, the play on words :-)
 *
 * @see Maze
 *
 *
 */
public class CellBlock {

    private int row;
    private int column;
    private Set<CellBlock> connections = new HashSet<>();
    private boolean entrance;
    private boolean exit;

    public CellBlock(int row, int column) {
        this.row = row;
        this.column = column;

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void addConnection(final CellBlock cellBlock) {

        if(!connections.contains(cellBlock)) {
            connections.add(cellBlock);
            cellBlock.addConnection(this);
        }

    }

    public boolean connected(final CellBlock cellBlock) {
        return (cellBlock != null) ? connections.contains(cellBlock) : false;
    }
    public Set<CellBlock> getConnections() {
        return this.connections;
    }

    public boolean isEntrance() {
        return entrance;
    }

    public void setEntrance(boolean entrance) {
        this.entrance = entrance;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * Note, we exclude the connections field from the equals method as only our coordinates matter
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "connections");
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getRow()).append(getColumn()).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
