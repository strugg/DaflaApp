package dafla.dafla;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
//Facebook SDK import
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
//Google SDK import
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private LoginButton btnFacebook;
    private CallbackManager mCallbackManager;
    public RelativeLayout log_in_activity;
    Button btnLogin;
    EditText input_email, input_password;
    TextView btnSignUp, btnForgotPassword;
    TextInputLayout input_layout_email, input_layout_password;

    //this is for google sign in
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SignInButton signInButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        super.onCreate(savedInstanceState);

        FacebookSdk.setApplicationId(String.valueOf(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_log_in);

        //Var Init
        log_in_activity = (RelativeLayout) findViewById(R.id.log_in_activity);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
        input_email = (EditText) findViewById(R.id.login_email);
        input_password = (EditText) findViewById(R.id.login_password);
        btnSignUp = (TextView) findViewById(R.id.login_btn_sign_up);
        btnForgotPassword = (TextView) findViewById(R.id.login_btn_forgot_password);
        input_layout_email = (TextInputLayout) findViewById(R.id.login_input_email);
        input_layout_password = (TextInputLayout) findViewById(R.id.login_input_password);

        mCallbackManager = CallbackManager.Factory.create();


        btnSignUp.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        btnFacebook = (LoginButton) findViewById(R.id.login_button);

        //Google signin
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        /*
        // Configure Google Sign In
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener(){
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult){
                        Toast.makeText(MainActivity.this, "Hubo un error", Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signIn();
            }
        });*/

        //Facebook signin
        /*btnFacebook = (LoginButton) findViewById(R.id.login_button);
        btnFacebook.setReadPermissions(Arrays.asList("email"));
        btnFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Snackbar snackbar = Snackbar.make(log_in_activity, "Se cancelo la operacion", Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onError(FacebookException error) {
                Snackbar snackbar = Snackbar.make(log_in_activity, "Hubo algun error en la operacion", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Snackbar snackbar = Snackbar.make(log_in_activity, "Firebase error ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });*/
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                //signIn();
                startActivity(new Intent(LogInActivity.this, MainMenuActivity.class));
                finish();
                break;
            case R.id.login_button:
                //signIn();
                startActivity(new Intent(LogInActivity.this, MainMenuActivity.class));
                finish();
                break;
            case R.id.login_btn_forgot_password:
                startActivity(new Intent(LogInActivity.this, ForgotPasswordActivity.class));
                finish();
                break;
            case R.id.login_btn_sign_up:
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
                finish();
                break;
            case R.id.login_btn_login:
                //loginUser(input_email.getText().toString(), input_password.getText().toString());
                startActivity(new Intent(LogInActivity.this, MainMenuActivity.class));
                finish();
                break;
        }
    }

    /*private void loginUser(String s, String s1) {
        private void loginUser(final String email, final String password) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Snackbar snackbar = Snackbar.make(activity_log_in, "Authentication failed", Snackbar.LENGTH_LONG);
                                snackbar.show();
                                if (password.length() < 6) {
                                    Snackbar snackBar = Snackbar.make(activity_log_in, "Password length must be over 6", Snackbar.LENGTH_SHORT);
                                    snackBar.show();
                                }
                            } else {
                                startActivity(new Intent(LogInActivity.this, MainMenuActivity.class));
                            }
                        }
                    });
        }
    }*/
    /*private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/
}
