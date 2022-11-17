package com.example.tgarden.User;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tgarden.Databases.BaseDataBindingActivity;
import com.example.tgarden.LocationOwner.RetailerDashboard;
import com.example.tgarden.R;
import com.example.tgarden.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class LoginActivity extends BaseDataBindingActivity<ActivityLoginBinding, AndroidViewModel> {

    private static final int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    GoogleSignInClient mGoogleSignInClient;

    private int attempts = 0;
    private CheckBox remember;
    private SharedPreferences mPref;
    private static final String PREFS_NAME = "PrefsFile";

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initListeners() {
        mBinding.setOnClickBack(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBinding.setOnClickGoogleLogin(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signIntent, RC_SIGN_IN);
            }
        });

        mBinding.setOnClickLogin(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Objects.requireNonNull(mBinding.email.getText()).toString().trim();
                String password = Objects.requireNonNull(mBinding.password.getText()).toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mBinding.email.setError(getResources().getString(R.string.invalid_email));
                    mBinding.email.setFocusable(true);
                } else if (password.length() < 6) {
                    mBinding.password.setError(getResources().getString(R.string.lenght_pass));
                    mBinding.password.setFocusable(true);
                } else {
                    loginUser(email, password);
                }
            }
        });

        mBinding.setOnClickNotHaveAccount(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        mBinding.setOnCLickRecoverPass(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecoverPasswordDialog();
            }
        });
    }

    @Override
    protected void initData() {
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void subscribeToViewModel() {

    }

    private void showRecoverPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle(getResources().getString(R.string.recover_pass));
        LinearLayout linearLayout = new LinearLayout(this);

        final TextInputEditText email = new TextInputEditText(this);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email.setMinEms(16);
        linearLayout.addView(email);
        linearLayout.setPadding(10, 10, 10, 10);
        builder.setView(linearLayout);
        builder.setMessage("").setCancelable(false).setPositiveButton(getResources().getString(R.string.recover), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String emaillog = email.getText().toString().trim();
                beginRecover(emaillog);
            }
        }).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create().show();
    }

    private void beginRecover(String email) {
        progressDialog.setMessage(getResources().getString(R.string.send_mail));
        progressDialog.show();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.send_mail), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Fail.....", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(String email, String password) {
        progressDialog.setMessage(getResources().getString(R.string.login));
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, RetailerDashboard.class));
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.authentication_failed), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.err_login), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account);
                }

            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();

                    if (Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getAdditionalUserInfo()).isNewUser()) {
                        String email = Objects.requireNonNull(user).getEmail();
                        String uid = user.getUid();
                        HashMap<Object, String> hashMap = new HashMap<>();
                        hashMap.put("mail", email);
                        hashMap.put("uid", uid);
                        hashMap.put("name", "");
                        hashMap.put("onlineStatus", "online");
                        hashMap.put("typingTo", "noOne");
                        hashMap.put("phone", "");
                        hashMap.put("image", "");
                        hashMap.put("cover", "");
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("Users");
                        reference.child(uid).setValue(hashMap);
                    }

                    if (user != null) {
                        Toast.makeText(LoginActivity.this, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(LoginActivity.this, RetailerDashboard.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Fail....", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}