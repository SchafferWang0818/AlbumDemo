package schaffer.albumdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import schaffer.albumdemo.utils.LogUtils;

/**
 * Created by SchafferW on 2016/10/26.
 */

public abstract class BaseFragment extends Fragment {
    public Activity activity;
    private View view;
    private boolean mIsVisibleToGuest;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = initViews(inflater, container, savedInstanceState);
            initData();
        }
        return view;
    }

    protected abstract void initData();

    protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToGuest = isVisibleToUser;
        LogUtils.w(getClass().getSimpleName() + "是否可见:" + mIsVisibleToGuest);
    }
}
