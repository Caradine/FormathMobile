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
    private static final String TAG = GameActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        When the Activity is created.
         */
        super.onCreate(savedInstanceState);
        String user_login = getIntent().getExtras().getString("USER_OBJECT");
        dataGame = new Game();
        //TODO: Getting user infos
        User user = new User();
        dataGame.setType(GameType.NORMAL);
        user.setUserName(user_login);
        dataGame.setUser(user);
        setContentView(R.layout.activity_game);
        SelectCategoryFragment categoryFragment = SelectCategoryFragment.newInstance(this);
        changeFragment(categoryFragment, true);
    }

    @Override
    public void onFragmentSelectCategoryInteraction(int category) throws Exception {
        /*
        When the user select a category.
         */
        if (category == 10) {
            backToMenu();
        }
        else if (category != 0) {
            SelectLevelFragment newFragment = SelectLevelFragment.newInstance(this, category);
            changeFragment(newFragment, true);
        }
    }

    public void backToSelectedFragment(String fragmentName) {
        Log.d("GameActivity", "back to selected fragment");
        getFragmentManager().popBackStack(fragmentName, 0);
        getFragmentManager().popBackStack("MainActivity", 0);
    }

    public void backToMenu() {
        finish();
    }

    @Override
    public void onFragmentSelectLevelInteraction (int category, int level) {
        /*
        When the user select a level.
        TODO: Adding back button to category selection
         */
        if (level == 10) {
            Log.d("GameActivity", "Level Interaction level 10");
            //backToSelectedFragment("be.formath.formathmobile.control.GameActivityFragments.SelectLevelFragment");
            try {
                changeFragment(SelectCategoryFragment.newInstance(this), false);
            }
            catch (Exception e) {
                // TODO: DO smthg here
                Log.d("GameActivity", "Exception in level 10");
            }
        }
        else if (level != 0) {
            dataGame.setGameStartDateTime(new GregorianCalendar());
            ArrayList<Operation> listOper = GeneratorUtilities.generate_problem_list(category, level);
            dataGame.setListOperation(listOper);
            dataGame.setCurrentOperationIndex(0);
            Operation oper = dataGame.getListOperation().get(dataGame.getCurrentOperationIndex());
            String title = "Catégorie " + category + ", Niveau " + level;
            PlayFieldFragment newFragment = PlayFieldFragment.newInstance(oper.getLabel(), dataGame.getCurrentOperationIndex() + 1, title);
            changeFragment(newFragment, true);
        }
    }

    @Override
    public void onFragmentPlayFieldInteraction(String action, String userResponse, String title) {
        if (action == "answer") {
            if (userResponse != null && !userResponse.trim().equals("")) {
                dataGame.setAnswerToCurrentOperation(userResponse);
            }
            Operation newOper = dataGame.goToNextUnansweredOperation();
            if (newOper == null) {
                // Enregistrement en DB de la partie
                dataGame.generateResult();
                DataWriter dr = DataWriter.getInstance(getBaseContext());
                dr.saveGame(dataGame);
                // Calcul du gain de médailles

                // Lancement du fragment de fin de partie
                ResultFragment newFragment = ResultFragment.newInstance(dataGame.getListOperation());
                changeFragment(newFragment, false);
            } else {
                PlayFieldFragment newFragment = PlayFieldFragment.newInstance(newOper.getLabel(), dataGame.getCurrentOperationIndex() + 1, title);
                changeFragment(newFragment, false);
            }
        } else if (action == "pass") {
            Operation newOper = dataGame.goToNextUnansweredOperation();
            if (newOper != null) {
                PlayFieldFragment newFragment = PlayFieldFragment.newInstance(newOper.getLabel(), dataGame.getCurrentOperationIndex() + 1, title);
                changeFragment(newFragment, false);
            }
        }
    }

    private void changeFragment(Fragment newFragment, boolean saveInBackstack) {
        String backStateName = ((Object) newFragment).getClass().getName();
        Log.d("changeFragment", "backStateName " + backStateName);
        try {
            FragmentManager manager = getFragmentManager();
            boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
            if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
                //fragment not in back stack, create it.
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.activity_game_container, newFragment, backStateName);
                if (saveInBackstack) {
                    transaction.addToBackStack(backStateName);
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
        Log.i(TAG, "Closing fragment");
        this.finish();
    }
}
