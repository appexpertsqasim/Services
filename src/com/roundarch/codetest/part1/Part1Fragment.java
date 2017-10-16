package com.roundarch.codetest.part1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.roundarch.codetest.R;

/**
 * Created by mdigiovanni on 8/15/13.
 */
public class Part1Fragment extends Fragment {
    // TODO - any member variables you need to store?
    SeekBar seekbar1;
    SeekBar seekbar2;
    static int progressChangedValue =0;
    static int progressChangedValue2 =0;
    TextView difference;
    ToggleButton toggle;
    //FIXME: Improve something! Anything
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part1, null);

        // TODO - obtain references to your views from the layout
        seekbar1 = (SeekBar)view.findViewById(R.id.seekBar);
        seekbar2 = (SeekBar)view.findViewById(R.id.seekBar2);
        difference=(TextView)view.findViewById(R.id.diff_tv);
        toggle = (ToggleButton) view.findViewById(R.id.toggleButton);

        // TODO - hook up any event listeners that make sense for the task

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled


                } else {
                    // The toggle is disabled

                }
            }
        });

        //set change listener
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (toggle.isChecked()){
                    seekbar2.setProgress(seekbar1.getProgress());
                    difference.setText("0");
                }
                else
                {
                    progressChangedValue = progress;
                difference.setText(String.valueOf(progress-progressChangedValue2));
            }}

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (toggle.isChecked()){
                    seekbar1.setProgress(seekbar2.getProgress());
                    difference.setText("0");
                }
                else
                {
                    progressChangedValue2 = progress;
                    difference.setText(String.valueOf(progress-progressChangedValue));
                }}

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }


}
