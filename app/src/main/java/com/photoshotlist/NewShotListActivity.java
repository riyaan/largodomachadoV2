package com.photoshotlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.photoshotlist.bll.PSLBusinessHelper;
import com.photoshotlist.bll.PSLBusinessHelperFactory;
import com.photoshotlist.bll.ShotListDO;
import com.photoshotlist.crosscutting.logging.Logger;

public class NewShotListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shot_list);
    }

    public void onClickNewShotlist(View view){

        Logger.getInstance().Debug(this.getClass().getName(), "onClickNewShotList");

        EditText shotListName = null;
        EditText shotListDescription = null;
        TextView errorMessage = null;

        try
        {
            shotListName = (EditText)findViewById(R.id.editTextShotListName);
            shotListDescription = (EditText)findViewById(R.id.editTextShotListDescription);
            errorMessage = (TextView)findViewById(R.id.textViewErrorMessage);

            PSLBusinessHelper businessHelper = PSLBusinessHelperFactory.getInstance().create(this);
            Logger.getInstance().Debug(this.getClass().getName(), "Before InsertShotList");

            ShotListDO sdo = new ShotListDO();
            sdo.setName(shotListName.getText().toString());
            sdo.setLongDescription(shotListDescription.getText().toString());

            sdo = businessHelper.InsertShotList(sdo);

            Logger.getInstance().Debug(this.getClass().getName(), "After InsertShotList");

            errorMessage.setText(String.format("ShotList '%s' was added successfully.", sdo.getName()));
        }
        catch(Exception e){
            errorMessage.setText(String.format("%s", e.getMessage()));
        }
    }
}
