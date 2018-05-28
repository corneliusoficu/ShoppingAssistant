package com.soficu.corneliu.shoppingassistant;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseFragmentActivity extends AppCompatActivity {

    public interface FragmentBuilder{
        public void addAdditionalData(Bundle fragmentBundle);
    }

    public void showFragment(String fragmentName) {
        this.showFragment(fragmentName, null);
    }

    public void showFragment(String fragmentName, FragmentBuilder builder) {

        Fragment newFragment = getFragmentByName(fragmentName);

        if(newFragment == null) {
            return;
        }

        if(builder != null){
            Bundle bundle = new Bundle();
            builder.addAdditionalData(bundle);
            newFragment.setArguments(bundle);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getFragmentContainer(), newFragment);
        transaction.addToBackStack(null);
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
