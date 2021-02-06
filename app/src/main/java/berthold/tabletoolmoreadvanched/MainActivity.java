package berthold.tabletoolmoreadvanched;

/**
 * Creates a table.
 * <p>
 * This is a demo of the 'tabletool'- library. This version is the first step
 * in building a usable table tool. For now one can build a table of arbitrary size
 * with or without column and row titles.
 * <p>
 * It is not possible yet to add cell data or title data.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import berthold.tabletool.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements cellWasClickedListener {

    static final int ROWS=4,COLUMNS=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int cellViewID = R.layout.tabel_cell_view;
        int rowTitleViewID = R.layout.row_title;
        int columnTitleViewID = R.layout.column_title;
        int upperLeftCornerCellViewID = R.layout.upper_left_corner_cell;

        int[][] cellData = new int[ROWS][COLUMNS];
        String [] rowTitles={"Row a","Row b","Row c","Row d"};
        String [] columnTitles={"First","Second","Third","forth"};

        for (int rows = 0; rows <= ROWS-1; rows++) {
            for (int columns = 0; columns <= COLUMNS - 1; columns++) {
                cellData[rows][columns] = rows*columns;
            }
        }

        // Builds a table without cell titles
        //Table table=new Table(3,4,cellViewID,this);

        // Builds a table with cell titles
        Table table=new Table(this,ROWS,COLUMNS,upperLeftCornerCellViewID,columnTitleViewID,rowTitleViewID,cellViewID,this);
        table.setAdapter(new MyTableArrayAdapter(cellData,rowTitles,columnTitles));
        View theTable=table.build();

        // Add layout to existing layout
        LinearLayout pl = (LinearLayout) findViewById(R.id.table);
        pl.addView(theTable);
    }

    @Override
    public void getCellTouched(int row,int column,View v){
        TextView text=v.findViewById(R.id.cell_view_text);

        Toast.makeText(this,"Cell "+row+"/"+column+ " was touched  "+text.getText().toString(),Toast.LENGTH_SHORT).show();
    }

}
