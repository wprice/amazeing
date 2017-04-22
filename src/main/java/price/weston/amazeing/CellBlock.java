package price.weston.amazeing;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * CellBlock represents a single cell or 'block' of a maze. Note, the play on words :-)
 *
 * @see Maze
 *
 */
public class CellBlock {

    private int row;
    private int column;
    private Set<CellBlock> connections = new HashSet<>();
    private boolean visited;

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

    public boolean connected(int row, int column) {
        return connected(new CellBlock(row, column));
    }
    public boolean connected(final CellBlock cellBlock) {
        return (cellBlock != null) ? connections.contains(cellBlock) : false;
    }

    public Set<CellBlock> getConnections() {
        return this.connections;
    }

    public List<CellBlock> getNonVisitedConnections() {
        return connections.stream().filter(cellBlock -> !cellBlock.isVisited()).collect(Collectors.toList());
    }

    public int nonVisitedConnectionsCount() {
        int count = connections.stream().filter(cellBlock -> !cellBlock.isVisited()).collect(Collectors.toList()).size();
        return count;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Note, we exclude the connections field from the equals method as only our coordinates matter
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CellBlock)) {
            return false;
        }

        CellBlock other = (CellBlock) obj;
        return getRow() == other.getRow() && getColumn() == other.getColumn();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getRow()).append(getColumn()).hashCode();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, "connections");
    }


    public int connectionCount() {
        return connections.size();
    }
}
