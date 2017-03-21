package com.scienceaction.expo_propulsion.helper;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.scienceaction.expo_propulsion.R;
import com.scienceaction.expo_propulsion.pages.Fragment_part3;
import com.scienceaction.expo_propulsion.pages.Fragment_part2;
import com.scienceaction.expo_propulsion.pages.Fragment_part1;
import com.scienceaction.expo_propulsion.pages.Fragment_part4;

import java.util.List;
import java.util.Vector;

public final class ViewpagerActivity extends FragmentActivity {

    private PagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    public ViewPagerCustomDuration viewPager;

    //pour veille
    //protected PowerManager.WakeLock mWakeLock;

    String itemRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        //plein ecran
        PleinEcran.pleinEcran(this);

        //suprime veille + voir onDestroy
        //final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        //this.mWakeLock.acquire();

        //sup veille
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        //creation liste des fragments
        fragments = new Vector<Fragment>();

        //ajout des fragments à la liste
        fragments.add(Fragment.instantiate(this,Fragment_part1.class.getName()));
        fragments.add(Fragment.instantiate(this,Fragment_part2.class.getName()));
        fragments.add(Fragment.instantiate(this,Fragment_part3.class.getName()));
        fragments.add(Fragment.instantiate(this,Fragment_part4.class.getName()));

        //creation de l'adaptateur qui s'occupera de l'affichage des fragments
        this.pagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

//        viewPager = (ViewPagerCustomDuration) super.findViewById(R.id.viewpager);
        viewPager = (ViewPagerCustomDuration) findViewById(R.id.viewpager);
        viewPager.setScrollDurationFactor(1);
        //affectation de l'adapter au viewpager
        viewPager.setAdapter(this.pagerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ViewerPagerActivity","Onresume---------");
        //recupere fragment depuis PageVideo
        try {
            itemRetour = getIntent().getExtras().getString("itemRetour");
            Log.i("itemRetour",itemRetour);
            viewPager.setCurrentItem(Integer.parseInt(itemRetour));
        }catch (Exception  e){
            e.printStackTrace();
        }
        //viewPager.setCurrentItem(3);
    }

    public FragmentActivity getFragmentActivity(){
        return this;
    }


    // on place l'interactivité des boutons du menu pour changer de slide
    public void onClickBtnPart1(View v){
        Log.i("onClickBtnPart1","------------------");
        viewPager.setScrollDurationFactor(5);
        viewPager.setCurrentItem(0); //SEleverDuSol
        viewPager.setScrollDurationFactor(1);
    }

    public void onClickBtnPart2(View v){
        Log.i("onClickBtnPart2","------------------");
        viewPager.setScrollDurationFactor(5);
        viewPager.setCurrentItem(1); //LaCourseALaVitesse
        viewPager.setScrollDurationFactor(1);
    }

    public void onClickBtnPart3(View v){
        Log.i("onClickBtnPart1","------------------");
        viewPager.setScrollDurationFactor(5);
        viewPager.setCurrentItem(2); //SEleverDuSol
        viewPager.setScrollDurationFactor(1);
    }

    public void onClickBtnPart4(View v){
        Log.i("onClickBtnPart1","------------------");
        viewPager.setScrollDurationFactor(5);
        viewPager.setCurrentItem(3); //SEleverDuSol
        viewPager.setScrollDurationFactor(1);
    }

    //@Override
    //public void onDestroy() {
    //    this.mWakeLock.release();
    //   super.onDestroy();
    //}
}
