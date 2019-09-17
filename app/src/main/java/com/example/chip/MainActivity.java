package com.example.chip;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class  MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private Button getInput;
    //private ImageButton btnSpeak;
    private EditText input;
    private TextView output;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    chipAI chip = new chipAI(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
                promptSpeechInput();
            }
        });

        tts = new TextToSpeech(this,this);
        getInput  = (Button) findViewById(R.id.getInput);
       // btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);


        getInput.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View arg0) {
                chip.readInput(input.getText().toString());
                output.setText(chip.getConsole());
                        //chip.respond());
                speakOut();
            }

        });

    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("TTS","This Language is not supported");
            }else{
                getInput.setEnabled(true);
                speakOut();
            }
        }else {
            Log.e("TTS","Initilization Failed!");
        }
    }

    /**
     * Receiving speech input
     * */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    chip.readInput(result.get(0));
                    output.setText(chip.getConsole());
                    speakOut();
                }
                break;
            }

        }
    }

    private void speakOut() {
        String text = chip.respond();
        //String text = input.getText().toString();
        input.setText("");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH,null);

    }

/*----*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
