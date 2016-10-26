package schaffer.albumdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class DetailsPageFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    Context context;
    String[] titles = new String[]{"歌曲列表", "专辑介绍"};

    public DetailsPageFragmentAdapter(FragmentManager fm, List<Fragment> list,
                                      Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
