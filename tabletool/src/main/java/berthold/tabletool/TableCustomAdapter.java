package berthold.tabletool;

import android.view.View;

/**
 * Custom adapter to populate the table with content.
 */
public abstract class TableCustomAdapter {

    public abstract View createCell(View cellView,int row,int column);

    public abstract View createUpperLeftCornerCell(View upperLeftCornerCellView);

    public abstract View createColumnTitleCell(View columnTitleView, int column);

    public abstract View createRowTitleCell(View rowTitleView, int row);
}
