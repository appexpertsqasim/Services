package com.roundarch.codetest.part2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.roundarch.codetest.R;

public class EditActivity extends FragmentActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);

        // TODO - you will need to obtain the model object provided to this activity and provide it to the EditFragment
//
//        DataModel model = (DataModel) getIntent().getSerializableExtra("Edited_Activity");
//        this.finishActivity(1);

//        Intent returnIntent = new Intent();

//        setResult(Activity.RESULT_OK,returnIntent);


    }




    public DataModel getDataModel(){
       DataModel model = (DataModel) getIntent().getSerializableExtra("Editing");

        return model;
    }



}