package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    public static String CACHE_USERNAME = "username";
    public static String EXTRA_LOGIN = "EXTRA_LOGIN";

    private EditText mEditEmail;
    private EditText mEditPassword;
    private EditText mNewEmail;
    private EditText mNewPassword;
    private Button mImageLogin;
    Button mImageSignUp;
    Button mBtnNewAccount;
    ProgressBar mProgressBar;
    Button mReturn;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mProgressBar = findViewById(R.id.progressbar);
        mEditEmail = findViewById(R.id.edit_email);
        mEditPassword = findViewById(R.id.edit_password);
        mImageLogin = findViewById(R.id.image_log);
        Button imageLog = (Button) findViewById(R.id.image_log);



        mImageSignUp = findViewById(R.id.image_signup);
        mReturn = findViewById(R.id.iv_close_friend);

        mImageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginValue = mEditEmail.getText().toString();
                String passwordValue = mEditPassword.getText().toString();
                mProgressBar.setVisibility(view.VISIBLE);
                if (loginValue.isEmpty() || passwordValue.isEmpty()) {
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "entrez un pseudo et un mot de passe", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(loginValue, passwordValue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }

        });




        mImageSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewEmail = findViewById(R.id.new_mail);
                mNewPassword = findViewById(R.id.new_password);
                mBtnNewAccount = findViewById(R.id.btn_new_account);
                final Button buttonSignIn = findViewById(R.id.image_log);

                mReturn.setVisibility(View.VISIBLE);
                mNewPassword.setVisibility(View.VISIBLE);
                mNewEmail.setVisibility(View.VISIBLE);
                mBtnNewAccount.setVisibility(View.VISIBLE);
                mEditEmail.setVisibility(View.GONE);
                mEditPassword.setVisibility(View.GONE);
                buttonSignIn.setVisibility(View.GONE);
                mImageSignUp.setVisibility(View.GONE);

                mBtnNewAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String emailN = mNewEmail.getText().toString();
                        String passwordN = mNewPassword.getText().toString();
                        if (TextUtils.isEmpty(emailN) || (TextUtils.isEmpty(passwordN))){
                            Toast.makeText(LoginActivity.this, "Entrez tous les champs demandés", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mAuth.createUserWithEmailAndPassword(emailN, passwordN).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Compte créé", Toast.LENGTH_SHORT).show();
                                        Intent intentProfil = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intentProfil);
                                    }
                                }
                            });
                        }
                    }
                });


                mReturn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mNewPassword.setVisibility(View.GONE);
                        mNewEmail.setVisibility(View.GONE);
                        mBtnNewAccount.setVisibility(View.GONE);
                        buttonSignIn.setVisibility(View.VISIBLE);
                        mEditEmail.setVisibility(View.VISIBLE);
                        mEditPassword.setVisibility(View.VISIBLE);
                        mReturn.setVisibility(View.GONE);
                        mImageSignUp.setVisibility(View.VISIBLE);

                    }
                });
            }
        });



        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

}