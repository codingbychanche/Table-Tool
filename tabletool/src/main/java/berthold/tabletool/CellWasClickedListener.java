package berthold.tabletool;
import android.view.View;

/**
 * Implement this to receive onClickListener events from
 * cells touched.
 */
public interface CellWasClickedListener {

    /**
     * Cell was touched.
     *
     * @param row       Cells row.
     * @param column    Cells column.
     * @param v         Cells view {@see View}
     */
    void getCellTouched (int row, int column, View v);
}
