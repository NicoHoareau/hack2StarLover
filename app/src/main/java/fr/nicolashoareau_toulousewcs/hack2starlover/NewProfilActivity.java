package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class NewProfilActivity extends AppCompatActivity {

    String mSide;
    ImageView mProfilPic;
    EditText mPseudo;
    private Uri mUri = null;
    CheckBox checkBoxfeminin, checkBoxmasculin;


    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profil);

        mSide = getIntent().getExtras().getString("Extra");
        setTitle("You choose the " + mSide + " Side !");

        mProfilPic = findViewById(R.id.iv_profil_pic);
        mPseudo = findViewById(R.id.et_pseudo);
        Button validation = findViewById(R.id.button_validation);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        final Spinner spinner = findViewById(R.id.spinner_planete);
        List<String> planete = new ArrayList<String>();
        planete.add("Tatooine");
        planete.add("Naboo");
        planete.add("Kashyyk");
        planete.add("Dogobah");
        planete.add("Kamino");
        planete.add("Coruscont");
        planete.add("Alderaan");
        planete.add("Jakku");

        //auto.setOnItemSelectedListener

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout
                .simple_spinner_dropdown_item, planete);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        mProfilPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });

        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mPseudo.getText().toString();
                if (firstName.isEmpty()) {
                    Toast.makeText(NewProfilActivity.this, "Choisis ton nom !", Toast.LENGTH_SHORT).show();
                }
                else {
                    saveUserModel();

                    Intent intent = new Intent(NewProfilActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    mUri = imageReturnedIntent.getData();
                    Glide.with(NewProfilActivity.this).load(mUri)
                            .apply(RequestOptions.circleCropTransform()).into(mProfilPic);

                }
                break;
        }
    }

    private void saveUserModel() {
        final String pseudo = mPseudo.getText().toString();

        /*if (mUri == null){

            if (mLink != null){
                String profilPic = mLink;
                UserModel userModel = new UserModel(pseudo, profilPic);
                FirebaseUser user = mAuth.getCurrentUser();
                mDatabaseReference = mDatabase.getReference("User");
                mDatabaseReference.child(user.getUid()).child("Profil").setValue(userModel);
            }
            else{
                String profilPic = mUrlSave;
                UserModel userModel = new UserModel(pseudo, profilPic);
                FirebaseUser user = mAuth.getCurrentUser();
                mDatabaseReference = mDatabase.getReference("User");
                mDatabaseReference.child(user.getUid()).child("Profil").setValue(userModel);
            }


        }
        else {
            StorageReference filePath = mStorageReference.child("profilPicture").child(mUri.getLastPathSegment());
            filePath.putFile(mUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    String profilPic = downloadUrl.toString();
                    UserModel userModel = new UserModel(pseudo, profilPic);
                    FirebaseUser user = mAuth.getCurrentUser();
                    mDatabaseReference = mDatabase.getReference("User");
                    mDatabaseReference.child(user.getUid()).child("Profil").setValue(userModel);
                }
            });
        }*/

        StorageReference filePath = mStorageReference.child("profilPicture").child(mUri.getLastPathSegment());
        filePath.putFile(mUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                String profilPic = downloadUrl.toString();
                UserModel userModel = new UserModel(pseudo, profilPic);
                final FirebaseUser user = mAuth.getCurrentUser();
                mDatabaseReference = mDatabase.getReference("User");
                mDatabaseReference.child(mSide).child(user.getUid()).child("Profil").setValue(userModel);


                checkBoxmasculin = findViewById(R.id.checkBox_m);

                if (checkBoxmasculin.isChecked()){
                    mDatabaseReference.child(mSide).child(user.getUid()).child("Profil").child("genre").
                            setValue("masculin").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(NewProfilActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }

                checkBoxfeminin = findViewById(R.id.checkBox_f);
                if(checkBoxfeminin.isChecked()){

                    mDatabaseReference.child(mSide).child(user.getUid()).child("Profil").child("genre").
                            setValue("feminin").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(NewProfilActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }


            }
        });





    }

}
