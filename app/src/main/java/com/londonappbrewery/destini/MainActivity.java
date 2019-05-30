package com.londonappbrewery.destini;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView mStoryTextView;
    Button mAnswerTop;
    Button mAnswerBottom;
    // TODO: Declare as variaveis aqui:

    //indice corrente da historia

    private int mStoryIndex; // Ao adicionar condição savedInstanceState pediu para informar um TIPO.


    Story mT1 = new Story(R.string.T1_Story);
    Story mT2 = new Story(R.string.T2_Story);
    Story mT3 = new Story(R.string.T3_Story);
    Story mT4 = new Story(R.string.T4_End);
    Story mT5 = new Story(R.string.T5_End);
    Story mT6 = new Story(R.string.T6_End);

    Answer mA1_1 = new Answer(R.string.T1_Ans1);
    Answer mA1_2 = new Answer(R.string.T1_Ans2);
    Answer mA2_1 = new Answer(R.string.T2_Ans1);
    Answer mA2_2 = new Answer(R.string.T2_Ans2);
    Answer mA3_1 = new Answer(R.string.T3_Ans1);
    Answer mA3_2 = new Answer(R.string.T3_Ans2);
//    Answer mA4   = new Answer(R.string.T4_End);
//    Answer mA5   = new Answer(R.string.T5_End);
//    Answer mA6   = new Answer(R.string.T6_End);

    private Story mStorySelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null) {
            mStoryIndex = savedInstanceState.getInt("StoryKey");
        }

        //TODO: Faça o link do layout com a activity

        mAnswerTop     = findViewById(R.id.buttonTop);
        mAnswerBottom  = findViewById(R.id.buttonBottom);
        mStoryTextView = findViewById(R.id.storyTextView);


        //TODO:faça o mapeamento da história

        mT1.setAnswerTop(mA1_1);
        mT1.setAnswerBottom(mA1_2);
          mA1_1.setChildStory(mT3);
          mA1_2.setChildStory(mT2);

        mT2.setAnswerTop(mA2_1);
        mT2.setAnswerBottom(mA2_2);
          mA2_1.setChildStory(mT3);
          mA2_2.setChildStory(mT4);

        mT3.setAnswerTop(mA3_1);
        mT3.setAnswerBottom(mA3_2);
          mA3_1.setChildStory(mT6);
          mA3_2.setChildStory(mT5);



        // TODO: Coloque o evento do click do botão, caso precise colocar a visibilidade no botão invisivel utilize a função
        // do botão setVisibility(View.GONE):
        mStorySelected = mT1;
        mStoryTextView.setText(mStorySelected.getStoryID());
        mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
        mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());

        mAnswerTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStorySelected = mStorySelected.getAnswerTop().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerTop() == null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }
            }
        });

        mAnswerBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStorySelected = mStorySelected.getAnswerBottom().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerBottom() == null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }
            }
        });

    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("StoryKey",mStoryIndex);
    }

}
