package com.soficu.corneliu.shoppingassistant;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseFragmentActivity extends AppCompatActivity {

    public void showFragment(String fragmentName) {
        Fragment newFragment = getFragmentByName(fragmentName);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(newFragment != null) {
            transaction.replace(getFragmentContainer(), newFragment);
        }

        transaction.commit();
    }

    abstract protected int getFragmentContainer();

    protected String getFragmentTagFromName(String name) {
        return String.format("%s_fragment", name);
    }

    private Fragment getFragmentByName(String fragmentName){
        try {
            return (Fragment) getFragmentClassByName(fragmentName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch(Resources.NotFoundException e){
            e.printStackTrace();
            return null;
        } catch(NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    private Class<?> getFragmentClassByName(String fragmentName) {
        try {
            String fragmentNamePath = getResources().getString(
                    getResources().getIdentifier(getFragmentTagFromName(fragmentName), "string", getPackageName()));
            Class<?> fragmentClass = Class.forName(fragmentNamePath);

            if(! Fragment.class.isAssignableFrom(fragmentClass)) {
                throw new ClassNotFoundException(String.format("%s is not a Fragment", fragmentClass));
            }

            return fragmentClass;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
