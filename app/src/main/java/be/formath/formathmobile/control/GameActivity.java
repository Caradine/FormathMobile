package be.formath.formathmobile.control;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import be.formath.formathmobile.control.GameActivityFragments.PlayFieldFragment;
import be.formath.formathmobile.control.GameActivityFragments.ResultFragment;
import be.formath.formathmobile.control.GameActivityFragments.SelectCategoryFragment;
import be.formath.formathmobile.control.GameActivityFragments.SelectLevelFragment;
import be.formath.formathmobile.data.DataWriter;
import be.formath.formathmobile.generator.GeneratorUtilities;
import be.formath.formathmobile.model.Game;
import be.formath.formathmobile.model.GameType;
import be.formath.formathmobile.model.Operation;
import be.formath.formathmobile.model.User;

import be.formath.formathmobile.R;

public class GameActivity extends Activity
        implements SelectCategoryFragment.OnFragmentSelectCategoryInteractionListener,
        SelectLevelFragment.OnFragmentSelectLevelInteractionListener,
        PlayFieldFragment.OnFragmentPlayFieldInteractionListener,
        ResultFragment.OnFragmentResultInteractionListener{

    private Game dataGame;
    private int currentOperationIndex;

    private static final String TAG = "Game Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String user_login = getIntent().getExtras().getString("USER_OBJECT");
        dataGame = new Game();
        //TODO: Récupération des infos utilisateur
        User user = new User();
        dataGame.setType(GameType.NORMAL);
        user.setUserName(user_login);
        dataGame.setUser(user);
        setContentView(R.layout.activity_game);
        SelectCategoryFragment newFragment = SelectCategoryFragment.newInstance(this);
        changeFragment(newFragment, true);
    }

    @Override
    public void onFragmentSelectCategoryInteraction(int category) throws Exception {
        if (category != 0) {
            SelectLevelFragment newFragment = SelectLevelFragment.newInstance(this, category);
            changeFragment(newFragment, true);
        }
    }

    @Override
    public void onFragmentSelectLevelInteraction (int category, int level) {
        if (level != 0) {
            dataGame.setGameStartDateTime(new GregorianCalendar());
            ArrayList<Operation> listOper = GeneratorUtilities.generate_problem_list(category, level);
            dataGame.setListOperation(listOper);
            currentOperationIndex = 0;
            Operation oper = dataGame.getListOperation().get(currentOperationIndex);
            String title = "Catégorie " + category + ", Niveau " + level;
            PlayFieldFragment newFragment = PlayFieldFragment.newInstance(oper.getLabel(), currentOperationIndex + 1, title);
            changeFragment(newFragment, true);
        }
    }

    @Override
    public void onFragmentPlayFieldInteraction(String userResponse, String title) {
        dataGame.getListOperation().get(currentOperationIndex).setGivenResponse(userResponse);
        if (currentOperationIndex < 9) {
            currentOperationIndex++;
            Operation oper = dataGame.getListOperation().get(currentOperationIndex);
            PlayFieldFragment newFragment = PlayFieldFragment.newInstance(oper.getLabel(), currentOperationIndex + 1, title);
            changeFragment(newFragment, false);
        }
        else {
            // Enregistrement en DB de la partie
            dataGame.generateResult();
            DataWriter dr = DataWriter.getInstance(getBaseContext());
            dr.saveGame(dataGame);
            // Calcul du gain de médailles

            // Lancement du fragment de fin de partie
            ResultFragment newFragment = ResultFragment.newInstance(dataGame.getListOperation());
            changeFragment(newFragment, false);
        }
    }

    private void changeFragment(Fragment newFragment, boolean saveInBackstack) {
        String backStateName = ((Object) newFragment).getClass().getName();
        Log.d(TAG, "Start change Fragment");
        try {
            FragmentManager manager = getFragmentManager();
            boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
            Log.d(TAG, "fragmentPopped = " + fragmentPopped);
            Log.d(TAG, "manager.findFragmentByTag(backStateName) = " + manager.findFragmentByTag(backStateName));
            if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
                //fragment not in back stack, create it.
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.activity_game_container, newFragment, backStateName);

                if (saveInBackstack) {
                    Log.d(TAG, "Change Fragment: addToBackTack " + backStateName);
                    transaction.addToBackStack(backStateName);
                } else {
                    Log.d(TAG, "Change Fragment: NO addToBackTack");
                }

                transaction.commit();
            } else {
                // custom effect if fragment is already instanciated
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.activity_game_container, newFragment, backStateName);

                transaction.commit();
            }
        } catch (IllegalStateException exception) {
            Log.w(TAG, "Unable to commit fragment, could be activity as been killed in background. " + exception.toString());
        }
    }

    @Override
    public void onFragmentResultInteraction() {
        this.finish();
    }
}
