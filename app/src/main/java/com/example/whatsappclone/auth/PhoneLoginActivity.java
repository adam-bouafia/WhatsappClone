package com.example.whatsappclone.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappclone.MainActivity;
import com.example.whatsappclone.R;
import com.example.whatsappclone.databinding.ActivityPhoneLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneLoginActivity extends AppCompatActivity {

    private static final String TAG="PhoneLoginActivity";
    private ActivityPhoneLoginBinding binding;

    private FirebaseAuth mAuth;
    private String mVerificationId;


    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_phone_login);

        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog( this);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.btnNext.getText().equals("NEXT"))
                {

                    progressDialog.setMessage("Please wait..");
                    progressDialog.show();

                    String phone="+"+binding.edtCountryCode.getText().toString()+binding.edtPhone.getText().toString();
                    startPhoneNumberVerification(phone);

                }

                else
                {

                    progressDialog.setMessage("Verifying...");
                    progressDialog.show();

                    verifyPhoneNumberWithCode(mVerificationId,binding.edtCode.getText().toString());

                }

            }

        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                Log.d(TAG, "onVerificationComplete: Complete");
                signInWithPhoneAuthCredential(phoneAuthCredential);
                progressDialog.dismiss();

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                Log.d(TAG, "onVerificationFailed: "+e.getMessage());

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                binding.btnNext.setText("Verify");
                progressDialog.dismiss();
            }
        };



    }

        private void verifyPhoneNumberWithCode(String mVerificationId, String code) {

        if (code.isEmpty())
        {
            Toast.makeText(this, "Verification can not be empty!", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
        else
        {
            // [START verify_with_code]
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            // [END verify_with_code]
            signInWithPhoneAuthCredential(credential);
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();

                            startActivity(new Intent(PhoneLoginActivity.this,MainActivity.class));
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            progressDialog.dismiss();
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Log.d(TAG, "onComplete: Error Code");
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
    // [END sign_in_with_phone]

    private void startPhoneNumberVerification(String phone){

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}