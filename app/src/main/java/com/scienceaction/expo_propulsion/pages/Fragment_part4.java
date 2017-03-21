package com.scienceaction.expo_propulsion.pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scienceaction.expo_propulsion.R;
import com.scienceaction.expo_propulsion.helper.*;

public class Fragment_part4 extends Fragment {

    Button btnVersPageVideo;
    Button btnVersPageAudio;
    ViewPagerCustomDuration viewPager;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate (R.layout.layout_part4, container, false);
        viewPager = (ViewPagerCustomDuration) container;

        btnVersPageVideo = (Button) view.findViewById(R.id.btnVersPageVideo);
        btnVersPageAudio = (Button) view.findViewById(R.id.btnVersPageAudio);

        btnVersPageVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick( View view){
                Intent pageVideo = new Intent(getActivity(), PageVideo.class);
                pageVideo.putExtra("videoALire","v-partie4");
                //pour fragment retour
                pageVideo.putExtra("itemRetour","3");
                startActivity(pageVideo);
                getActivity().overridePendingTransition(R.transition.transition_vers_video_out,R.transition.transition_vers_video_in);
            }
        });

        btnVersPageAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick( View view){
                Intent pageVideo = new Intent(getActivity(), PageVideo.class);
                pageVideo.putExtra("videoALire","a-partie4");
                //pour fragment retour
                pageVideo.putExtra("itemRetour","3");
                startActivity(pageVideo);
                getActivity().overridePendingTransition(R.transition.transition_vers_video_out,R.transition.transition_vers_video_in);
            }
        });

        return view;
    }
}

