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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int cellViewID=R.layout.tabel_cell_view;
        int rowTitleViewID=R.layout.row_title;
        int columnTitleViewID=R.layout.column_title;
        int upperLeftCornerCellViewID=R.layout.upper_left_corner_cell;


        // Builds a table without cell titles
        //Table table=new Table(3,4,cellViewID,this);

        // Builds a table with cell titles
        Table table=new Table(3,4,upperLeftCornerCellViewID,columnTitleViewID,rowTitleViewID,cellViewID,this);
        View theTable=table.build();

        // Add layout to existing layout
        LinearLayout pl = (LinearLayout) findViewById(R.id.table);
        pl.addView(theTable);


    }
}
