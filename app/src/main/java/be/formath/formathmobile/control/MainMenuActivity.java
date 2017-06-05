package be.formath.formathmobile.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import be.formath.formathmobile.R;

public class MainMenuActivity extends Activity {

    private Intent intent;
    private String user_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.intent = getIntent();
        user_login = this.intent.getStringExtra(LoginActivity.USER_LOGIN);
        LoginActivity.close_activity();
        setContentView(R.layout.activity_main_menu);
    }

    public void playGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("USER_OBJECT", this.user_login);
        startActivity(intent);
    }

    public void displayTutorial(View view) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

}
