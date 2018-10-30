package apps.prince.wanderindia;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import com.tomtom.online.sdk.map.*;
import java.util.List;

public class DisplayLocationlist extends AppCompatActivity {
private static String category;
private static String cityID;
private static String stateID;
private RecyclerView.Adapter adapter;
private List<FetchedPlaces> listItems;
private RecyclerView recyclerView;

private void loadRecyclerView()
{
    final ProgressDialog localprogress=new ProgressDialog(this);
    localprogress.setMessage("Loading data..");
    localprogress.show();
    
}
}
