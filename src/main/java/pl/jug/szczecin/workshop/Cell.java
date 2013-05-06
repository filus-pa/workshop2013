package pl.jug.szczecin.workshop;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.Validate;
import org.mockito.Matchers;

import java.util.List;

/**
 * Created for 2013 workshop, by Filip Pająk
 */
public class Cell {
	private CellState state;
	private CellState newState;

	private List<Cell> neighbours;

	/**
	 * Creates new cell with initial state.
	 *
	 * Throws NullPointerException if initial state is not defined.
	 *
	 * @param initialState - initial state of cell.
	 */
	public Cell(final CellState initialState) {
		Validate.notNull(initialState, "Initial State cannot be null");
		this.state = initialState;
	}

	public void initialize(final List<Cell> neighbours) {
		Validate.notNull(neighbours, "Given neighbours cannot be null");
		Validate.isTrue(neighbours.size() == 8, "Given neighbours should have eight entries");

		this.neighbours = new ImmutableList.Builder<Cell>().addAll(neighbours).build();
	}

	public void initialize(final Cell leftTop, final Cell top, final Cell rightTop, final Cell right, final Cell rightBottom, final Cell bottom, final Cell leftBottom, final Cell left) {
		intializeWithArray(leftTop, top, rightTop, right, rightBottom, bottom, leftBottom, left);
	}

	private void intializeWithArray(final Cell... neighbours) {
		Validate.noNullElements(neighbours, "Cannot give null neighbour cells");

		this.neighbours = new ImmutableList.Builder<Cell>().add(neighbours).build();
	}

	public void calculateNewState() {
		if (neighbours == null) {
			throw new IllegalStateException();
		}
	}

	public void actualizeState() {

	}

	public boolean isAlive() {
		return state.equals(CellState.ALIVE);
	}

	public boolean isInitialized() {
		return neighbours != null;
	}

	public enum CellState {
		DEAD,
		ALIVE;
	}
}
