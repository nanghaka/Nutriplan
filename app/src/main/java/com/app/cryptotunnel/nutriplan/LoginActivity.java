package com.app.cryptotunnel.nutriplan;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";


    private CallbackManager callbackManager;
    private final String TAG = "logging in";


    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());//initialize facebook sdk

        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();//to handle login responses

        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();


        findViewById(R.id.sign_in_button).setOnClickListener(this);
//        findViewById(R.id.login_button).setOnClickListener(this);
        Button debugLoginButton = (Button) findViewById(R.id.debuglogin);
        debugLoginButton.setOnClickListener(this);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);


        loginButton.setReadPermissions("user_friends");
        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        // If using in a fragment
        //loginButton.setFragment(this);
        // Other app specific specialization
        Log.d(TAG,"started login screen" );

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d(TAG,"success" );
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onCancel() {
                // App code
                Log.d(TAG,"canceled" );
                Toast.makeText(getApplicationContext(), "Login canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(TAG,"login_error" );
                Toast.makeText(getApplicationContext(), "login_error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d(TAG, "onConnected:" + bundle);
        mShouldResolve = false;

        // Show the signed-in UI
       // showSignedInUI();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_in_button:
                onSignInClicked();
                break;

            case R.id.debuglogin:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;

        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                showErrorDialog(connectionResult.getErrorCode());
                Log.d(TAG,"error" );
                Toast.makeText(getApplicationContext(), "Login failure", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Show the signed-out UI
            //showSignedOutUI();
            Log.d(TAG,"error" );
            Toast.makeText(getApplicationContext(), "Login failure", Toast.LENGTH_SHORT).show();
        }

    }

    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.
       // mStatusTextView.setText(R.string.signing_in);
        Toast.makeText(getApplicationContext(), "Signing in", Toast.LENGTH_SHORT).show();
    }

    private void onSignOutClicked() {
        // Clear the default account so that GoogleApiClient will not automatically
        // connect in the future.
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }

       // showSignedOutUI();
    }

    /* Creates a dialog for an error message */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
      //  dialogFragment.show(getSupportFragmentManager(), "errordialog");
        dialogFragment.show(getFragmentManager(), "errordialog");
    }
}