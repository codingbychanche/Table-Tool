package berthold.tabletool;
/**
 * This builds a table and returns it's view.
 *
 *
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Table extends AppCompatActivity {

    private cellWasClickedListener listener;
    boolean tableHasTitleCells;
    private int columns, rows, cellViewID,upperLeftCornerCellID,columnTitleID,rowTitleID;
    private int dataSource[][];
    private TableCustomAdapter tableArrayAdapter;
    private Context context;

    /**
     * Creates a table with no row- and column- titles
     *
     * @param columns
     * @param rows
     * @param cellViewID
     * @param context
     */
    public Table(cellWasClickedListener listener,int rows, int columns, int cellViewID, Context context) {
        this.listener=listener;
        tableHasTitleCells=false;
        this.columns = columns;
        this.rows = rows;
        this.cellViewID = cellViewID;
        this.context = context;
    }

    public void setAdapter (TableCustomAdapter tableArrayAdapter){
        this.tableArrayAdapter=tableArrayAdapter;
    }

    /**
     * Creates a table with row- and column- titles
     *
     * @param columns
     * @param rows
     * @param columnTitleID
     * @param rowTitleID
     * @param cellViewID
     * @param context
     */
    public Table(cellWasClickedListener listener,int columns,int rows,int upperLeftCornerCellID,int columnTitleID,int rowTitleID,int cellViewID,Context context){
        this.listener=listener;
        tableHasTitleCells=true;
        this.columns = columns;
        this.rows = rows;
        this.columnTitleID=columnTitleID;
        this.upperLeftCornerCellID=upperLeftCornerCellID;
        this.rowTitleID=rowTitleID;
        this.cellViewID = cellViewID;
        this.context = context;
    }

    /**
     * Builds a table of arbitrary size.
     *
     * Table- cells, row- title cells, column- title cells and the cell at the upper left
     * corner are created from their own layout xml file.
     *
     * In this version the width of the row title cells, the column- title cells and the
     * table- cells is set to a fixed size. The height of all cells is set to WARP_CONTENT.
     * The problem is: When cell content needs more space it is cut to that size.
     *
     * @return  View containing the table.
     *
     * todo: check for null on title views....
     */
    public View build() {

        // Create layout params like you would inside the layout.xml file
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

        LayoutInflater inflater = LayoutInflater.from(context);

        // Layout for the table
        LinearLayout tableLayout = new LinearLayout(context);
        tableLayout.setOrientation(LinearLayout.VERTICAL);

        if (tableHasTitleCells) {
            // Layout for title bar containing each columns title.
            LinearLayout titleRowLayout = new LinearLayout(context);
            titleRowLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Add upper left corner cell to title bar
            View upperLeftCornerCellView = (LinearLayout) inflater.inflate(upperLeftCornerCellID, titleRowLayout, false);
            View upperLeftCornerCell = tableArrayAdapter.createUpperLeftCornerCell(upperLeftCornerCellView);
            titleRowLayout.addView(upperLeftCornerCell);

            // Now add column titles to title bar
            for (int column = 0; column <= columns-1; column++) {

                View columnTitleView = inflater.inflate(columnTitleID, null);
                View columnTitle = tableArrayAdapter.createColumnTitleCell(columnTitleView, column);
               titleRowLayout.addView(columnTitle);
            }
            tableLayout.addView(titleRowLayout);
        }

        // Draw row titles and table cells for each row.
        for (int row = 0; row <= rows-1; row++) {

            // Now create the new layout for the row
            LinearLayout rowLayout = new LinearLayout(context);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);

            if(tableHasTitleCells) {
                // First cell of the row is the title
                View rowTitleView = inflater.inflate(rowTitleID, null);
                View rowTitle = tableArrayAdapter.createRowTitleCell(rowTitleView, row);
                rowLayout.addView(rowTitle);
            }

            // Fill row with cells
            for (int column = 0; column <= columns-1; column++) {
                View cellView = inflater.inflate(cellViewID, null);
                View cell = tableArrayAdapter.createCell(cellView, row, column);
                rowLayout.addView(cell);

                final int r=row;
                final int c=column;

                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.getCellTouched(r,c,v);
                    }
                });
            }
            // Add row to table layout
            tableLayout.addView(rowLayout);
            // Next row...
        }
        return tableLayout;
    }
}
