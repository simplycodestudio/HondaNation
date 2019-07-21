package com.example.jacek.hondadiagnostic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Jacek on 2018-03-07.
 */

class TabsPagerAdapter extends FragmentPagerAdapter {




    public TabsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                ResponseRequestsFragment responseRequestsFragment = new ResponseRequestsFragment();
                return responseRequestsFragment;

            case 1:
                ResponseChatsFragment responseChatsFragment = new ResponseChatsFragment();
                return responseChatsFragment;

            case 2:
                ResponseFriendsFragment responseFriendsFragment = new ResponseFriendsFragment();
                return responseFriendsFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Request";
            case 1:
                return "Chats";
            case 2:
                return "Friends";
            default:
                return null;

        }
    }
}
