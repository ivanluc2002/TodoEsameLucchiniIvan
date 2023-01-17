package com.example.todoesamelucchiniivan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private final FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler1);
        ArrayList<String> azioni= new ArrayList<>();


        db.collection("todo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("fatto", document.getId() + " => " + document.getData().get("azione"));
                                azioni.add(document.getData().get("azione").toString());
                                Log.d("azioni", "azioni"+azioni);

                            }
                        } else {
                            Log.d("errore", "Error getting documents.", task.getException());
                        }
                        TodoAdapter todoAdapter= new TodoAdapter(azioni.toArray(new String[0]));
                        recyclerView.setAdapter(todoAdapter);
                    }

                });


    }
}