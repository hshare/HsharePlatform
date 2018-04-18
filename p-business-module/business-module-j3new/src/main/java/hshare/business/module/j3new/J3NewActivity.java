package hshare.business.module.j3new;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import hshare.base.component.view.statusbar.StatusBarUtil;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.BACKGROUND_STYLE_DEFAULT;
import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED;
import static com.ashokvarma.bottomnavigation.ShapeBadgeItem.SHAPE_OVAL;

public class J3NewActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    BottomNavigationBar bottomNavigationBar;

    FloatingActionButton fabHome;

    @Nullable
    TextBadgeItem numberBadgeItem;

    @Nullable
    ShapeBadgeItem shapeBadgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gan);
        StatusBarUtil.setTranslucentForImageView(this,110,null);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        fabHome = (FloatingActionButton) findViewById(R.id.fab_home);

        bottomNavigationBar.clearAll();
        bottomNavigationBar.setFab(fabHome);

        bottomNavigationBar.setMode(MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BACKGROUND_STYLE_DEFAULT);

        numberBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.blue)
                .setText("3")
                .setHideOnSelect(true);

        shapeBadgeItem = new ShapeBadgeItem()
                .setShape(SHAPE_OVAL)
                .setShapeColorResource(R.color.teal)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(true);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "Home").setActiveColorResource(R.color.orange).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "Books").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "Music").setActiveColorResource(R.color.blue).setBadgeItem(shapeBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "Movies & TV").setActiveColorResource(R.color.brown))
                .setFirstSelectedPosition(0)
                .initialise();


        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
