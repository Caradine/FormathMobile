package be.formath.formathmobile.control;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import be.formath.formathmobile.data.DataRetriever;
import be.formath.formathmobile.R;

public class LoginActivity extends Activity {

    public final static String USER_LOGIN = "be.formath.formathmobile.user_login";
    private static LoginActivity instance;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_login);
    }


    public static void close_activity() {
        if (instance != null)
            instance.finish();
    }

    public void authenticate(View view) {
        Log.d(TAG, "Authenticating");
        String login = ((EditText) findViewById(R.id.login_field_text)).getText().toString();
        if (check_password(login)) {
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra(USER_LOGIN, login);
            startActivity(intent);
        }
        else {
            //TODO: Affichage pop up si mauvais login / password
        }
    }

    private boolean check_password(String login) {
        String password = ((EditText) findViewById(R.id.password_field_text)).getText().toString();
        /*if (login.equals("demo")) {
            if (password.equals("demo"))
                return true;
        }*/
        DataRetriever dr = DataRetriever.getInstance(getBaseContext());
        String storedPassword = dr.getUserPassword(login);
        if (storedPassword != null) {
            if (storedPassword.startsWith("{CLEAR}")) {
                storedPassword = storedPassword.substring(7);
                if (storedPassword.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}

