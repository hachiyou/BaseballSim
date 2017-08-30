package com.example.lin.baseballsim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int FIRST_BASE = 0;
    public static final int SECOND_BASE = 1;
    public static final int THIRD_BASE = 2;
    public static final int HOME_PLATE = 3;
    boolean team_A_Play = true;
    List<Base> bases = new ArrayList<>();
    List<Button> buttonsForTeamA = new ArrayList<>();
    int bat_cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b;
        // Add all the buttons for team A;
        b = (Button) findViewById(R.id.A_FB_BTN);
        buttonsForTeamA.add(b);
        b = (Button) findViewById(R.id.A_SB_BTN);
        buttonsForTeamA.add(b);
        b = (Button) findViewById(R.id.A_TB_BTN);
        buttonsForTeamA.add(b);
        b = (Button) findViewById(R.id.A_Bat_BTN);
        buttonsForTeamA.add(b);
        bases.add(new Base(FIRST_BASE));
        bases.add(new Base(SECOND_BASE));
        bases.add(new Base(THIRD_BASE));
        bases.add(new Base(HOME_PLATE));
        TextView tv = (TextView) findViewById(R.id.Status_Screen_TV);
        tv.setMovementMethod(new ScrollingMovementMethod());
    }

    // A point is scored by team A.
    private void scoreIncreaseForTeamA() {
        TextView v = (TextView) findViewById(R.id.A_Score_CTN);
        int curScore = Integer.parseInt(v.getText().toString()) + 1;
        v.setText("" + curScore);
    }

    // A home run is hitted by a batter
    private void homerunByTeamA() {
        TextView status = (TextView) findViewById(R.id.Status_Screen_TV);
        TextView v = (TextView) findViewById(R.id.A_HR_CTN);
        int homerun_CTN = Integer.parseInt(v.getText().toString()) + 1;
        v.setText("" + homerun_CTN);
        status.append("Great job, you hit a home run!");
        scoreIncreaseForTeamA();
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).runnerUp) {
                scoreIncreaseForTeamA();
            }
        }
        resetField();
    }

    //Simulate a pitcher throws a ball
    private int pitcherThrowBall() {
        Random r = new Random();
        return Math.abs(r.nextInt() % 100);
    }

    /*
    Batter Hit Percentage (V0.1)
        Batter hitted a pitch
            Miss_Swing      : 50%
            Out_of_Bound    : 30%
            Short Hit :     : 14%
            Long Hit :      : 5%
            Home_Run        : 1%
    */
    public void play(View view) {
        bat_cnt++;

        TextView strikeCNTView = (TextView) findViewById(R.id.A_Strike_CTN);
        int strike_cnt = Integer.parseInt(strikeCNTView.getText().toString());

        TextView status = (TextView) findViewById(R.id.Status_Screen_TV);

        String str = "\n" + ordinal(bat_cnt) + " bat: ";
        status.append(str);

        int rand = pitcherThrowBall();
        if (rand <= 19) {
            bat_cnt = strike_cnt = 0;
            if (rand == 0) homerunByTeamA();
            else if (rand <= 14) score(1);
            else if (rand <= 19) score(2);
            status.append("\n------ Player switch ------");
        } else {
            if (rand <= 49) {
                if (strike_cnt < 2) strike_cnt++;
                status.append("It's a out-of-bound hit.");
            } else {
                strike_cnt++;
                status.append("You missed the pitch.");
                if (strike_cnt >= 3) {
                    bat_cnt = 0;
                    team_A_Play = !team_A_Play;
                    status.append("\n3 Strikes, Batter out.");
                    findViewById(R.id.A_Bat_BTN).setClickable(false);
                    findViewById(R.id.A_Bat_BTN).setBackgroundResource(R.drawable.gray_btns);
                }
            }
        }
        strikeCNTView.setText("" + strike_cnt);
    }

    // Method for Scoring a hit.
    private void score(int hitPower) {
        TextView status = (TextView) findViewById(R.id.Status_Screen_TV);
        if (hitPower == 1) {
            status.append("Batter hits an in-field ball");
            for (int i = bases.size() - 2; i > -1; i--) {
                if (bases.get(i).runnerUp) {
                    if (i == 2) scoreIncreaseForTeamA();
                    else bases.get(i + 1).runnerUp = true;
                    bases.get(i).runnerUp = false;
                    runToBase(i, i + 1);
                }
            }
            bases.get(FIRST_BASE).runnerUp = true;
            runToBase(-1, FIRST_BASE);

        } else {
            status.append("Batter hits an long range ball");
            for (int i = bases.size() - 2; i > -1; i--) {
                if (bases.get(i).runnerUp) {
                    if (i >= 1) {
                        scoreIncreaseForTeamA();
                        runToBase(i, bases.size() - 1);
                    } else {
                        bases.get(i + 2).runnerUp = true;
                        runToBase(i, i + 2);
                    }
                    bases.get(i).runnerUp = false;
                }
            }
            bases.get(SECOND_BASE).runnerUp = true;
            runToBase(-1, SECOND_BASE);
        }
    }

    // Method
    private void runToBase(int from, int to) {
        TextView status = (TextView) findViewById(R.id.Status_Screen_TV);
        switch (from) {
            case -1:
                status.append("\nBatter ran from Home ");
                break;
            default:
                status.append("\nRunner ran from " + bases.get(from).baseName);
        }
        switch (to) {
            case HOME_PLATE:
                status.append(" back ");
                break;
            case THIRD_BASE:
                thirdBaseReached();
                status.append(" to ");
                break;
            case SECOND_BASE:
                secondBaseReached();
                status.append(" to ");
                break;
            case FIRST_BASE:
                firstBaseReached();
                status.append(" to ");
                break;
        }
        status.append(bases.get(to).baseName + ".");
    }

    // Runner has reached the corresponding base.
    private void firstBaseReached() {
        Button b = (Button) findViewById(R.id.A_FB_BTN);
        b.setBackgroundResource(R.drawable.aqua_btn_bg);
    }

    private void secondBaseReached() {
        Button b = (Button) findViewById(R.id.A_SB_BTN);
        b.setBackgroundResource(R.drawable.aqua_btn_bg);
    }

    private void thirdBaseReached() {
        Button b = (Button) findViewById(R.id.A_TB_BTN);
        b.setBackgroundResource(R.drawable.aqua_btn_bg);
    }

    // Reset the field
    private void resetField() {
        bat_cnt = 0;
        TextView v;
        v = (TextView) findViewById(R.id.A_Strike_CTN);
        v.setText("0");
        for (int i = 0; i < bases.size(); i++) {
            bases.get(i).runnerUp = false;
        }
        if (!team_A_Play) {
            for (int i = 0; i < buttonsForTeamA.size(); i++) {
                buttonsForTeamA.get(i).setBackgroundResource(R.drawable.gray_btns);
                buttonsForTeamA.get(i).setClickable(false);
            }
        } else {
            for (int i = 0; i < buttonsForTeamA.size(); i++) {
                buttonsForTeamA.get(i).setBackgroundResource(R.drawable.blue_btns);
                buttonsForTeamA.get(i).setClickable(true);
            }
        }
    }

    // Start new Game
    public void resetGame(View view) {
        team_A_Play = true;
        TextView v;
        v = (TextView) findViewById(R.id.A_Score_CTN);
        v.setText("0");
        v = (TextView) findViewById(R.id.A_HR_CTN);
        v.setText("0");
        v = (TextView) findViewById(R.id.A_Inning_CTN);
        v.setText("1");
        v = (TextView) findViewById(R.id.A_Inning_TV);
        v.setText("Offense Round:");
        v = (TextView) findViewById(R.id.Status_Screen_TV);
        v.setText("\nGameStart");
        resetField();
    }

    // Change cardinal number to ordinal.
    private String ordinal(int num) {
        if (num >= 11 && num <= 13) return num + "th";
        else {
            switch (num % 10) {
                case 1:
                    return num + "st";
                case 2:
                    return num + "nd";
                case 3:
                    return num + "rd";
                default:
                    return num + "th";
            }
        }
    }
}
