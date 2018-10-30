package apps.prince.wanderindia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Categories extends AppCompatActivity {
    public static String categoryname;
    public static String state;
    public static String city;
    ImageView imgwinter;
    ImageView imgadventure;
    ImageView imgspirit;
    ImageView imgsummer;
    ImageView imgbeach;
    ImageView imgwidlife;
    ImageView imgwaterfall;
    ImageView imghistory;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bestplaces);
    this.imgbeach=(ImageView)findViewById(R.id.beaches);
    this.imghistory=(ImageView)findViewById(R.id.historical);
    this.imgsummer=(ImageView)findViewById(R.id.summer);
    this.imgwaterfall=(ImageView)findViewById(R.id.waterfalls);
    this.imgwinter=(ImageView)findViewById(R.id.winter);
    this.imgwidlife=(ImageView)findViewById(R.id.wildlife);
    this.imgadventure=(ImageView)findViewById(R.id.adventures);
    this.imgspirit=(ImageView)findViewById(R.id.spirtual);


    this.imgsummer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Categories.categoryname="Summer Place";
        }
    });

    this.imgwinter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Categories.categoryname="Winter Place";
        }
    });
    this.imgbeach.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Categories.categoryname="Beach Place";
        }
    });

    this.imghistory.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Categories.categoryname="Historical Place";
        }
    });
    this.imgadventure.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Categories.categoryname="Adventure Place";
        }
    });
    this.imgspirit.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            Categories.categoryname="Spiritual Place";
        }
    });
this.imgwidlife.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Categories.categoryname="Wildlife Place";
    }
});
    }
    public  void fetchCities()
    {
      //  OnlineSearchApi a=new OnlineSearchApi();
    }
}
