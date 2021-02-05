package berthold.tabletoolmoreadvanched;

/**
 * Table Array Adapter.
 *
 * Fills the table cells width data.
 * Gets invoked by {@link berthold.tabletool.Table}
 */

import android.view.View;
import android.widget.TextView;

import berthold.tabletool.TableCustomAdapter;

public class MyTableArrayAdapter extends TableCustomAdapter {

    private int[][] dataSource;
    private String [] columnTitles,rowTitles;

    MyTableArrayAdapter(int [][] dataSource,String [] rowTitles,String [] columnTitles){
        this.dataSource=dataSource;
        this.rowTitles=rowTitles;
        this.columnTitles=columnTitles;
    }
    /**
     * Creates a table cell containing data to be shown...
     *
     * @param cellView
     * @param row
     * @param column
     * @return
     */
    public View createCell(View cellView, int row, int column) {
        TextView cellContent = cellView.findViewById(R.id.cell_view_text);
        String data=""+dataSource [row][column];
        cellContent.setText(data);
        cellContent.setTag("Row:" + row);
        return cellView;
    }

    /**
     * Creates the cell at the upper left corner of the table.
     *
     * @param upperLeftCornerCellView
     * @return
     */
    public View createUpperLeftCornerCell (View upperLeftCornerCellView){
        TextView upperLeftCornerCellContent=upperLeftCornerCellView.findViewById(R.id.upper_left_corner_cell_content);
        upperLeftCornerCellContent.setText("Upper Left");
        return upperLeftCornerCellView;
    }

    /**
     * Creates the titel cells for each column.
     *
     * @param columnTitleView
     * @param column
     * @return
     */
    public View createColumnTitleCell(View columnTitleView, int column){
        TextView columnTitelContent = columnTitleView.findViewById(R.id.column_title_view_text);
        columnTitelContent.setText(columnTitles[column]);
        return columnTitleView;
    }

    /**
     * Creates the title cells for each row.
     *
     * @param rowTitleView
     * @param row
     * @return
     */
    public View createRowTitleCell(View rowTitleView, int row) {
        TextView rowTitelContent = rowTitleView.findViewById(R.id.row_title_view_text);
        rowTitelContent.setText(rowTitles[row]);
        return rowTitleView;
    }
}

