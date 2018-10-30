package apps.prince.wanderindia;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {
    private Context con;
    private  int[] mImageids=new int[] {
            R.drawable.agra,R.drawable.amritsar,R.drawable.chennai,R.drawable.darjeeling,R.drawable.goa
            ,R.drawable.kolkata, R.drawable.manali,R.drawable.pune,
            R.drawable.mumbai,R.drawable.shimla,R.drawable.varanasi};
    ImageAdapter(Context context)
    {
        con=context;
    }
    @Override
    public int getCount() {
        return mImageids.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView= new ImageView(con);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageids[position]);
        container.addView(imageView,0);
        return  imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
