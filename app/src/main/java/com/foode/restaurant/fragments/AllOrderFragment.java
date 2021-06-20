package com.foode.restaurant.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.foode.restaurant.R;
import com.foode.restaurant.activities.MainActivity2;
import com.foode.restaurant.models.PendingOrderModel;
import com.foode.restaurant.view.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AllOrderFragment extends BaseFragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    public AllOrderFragment() {
        // Required empty public constructor
    }

    public AllOrderFragment(Activity mActivity, PendingOrderModel pendingOrderModel) {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_order, container, false);
        findViewById(view);
        MainActivity2.switchOnOff.setVisibility(View.VISIBLE);
        return view;
    }

    void findViewById(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PendingOrderFragment(), "Pending");
        adapter.addFragment(new AcceptedOrderFragment(), "Accepted");
        adapter.addFragment(new PreparingOrderFragment(), "Preparing");
       // adapter.addFragment(new OnTheWayOrderFragment(), "OnTheWay");
        adapter.addFragment(new DeliveredOrderFragment(), "Delivered");
        adapter.addFragment(new CancelledOrderFragment(), "Cancelled");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}