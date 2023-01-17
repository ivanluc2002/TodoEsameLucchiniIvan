package com.example.todoesamelucchiniivan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private final FirebaseFirestore db=FirebaseFirestore.getInstance();
private EditText inserisciAzione;
private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler1);
        inserisciAzione=findViewById(R.id.inserisciAzione);
        button=findViewById(R.id.button);
        ArrayList<String> azioni= new ArrayList<>();
        leggiDb(azioni);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDb(azioni);

            }
        });





    }
    public void leggiDb(ArrayList azioni){
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
                        TodoAdapter todoAdapter= new TodoAdapter((String[]) azioni.toArray(new String[0]));
                        recyclerView.setAdapter(todoAdapter);
                    }

                });
    }
    public void addDb(ArrayList azioni){
        CollectionReference ref1= db.collection("todo");
        Map<String,String> data=new HashMap<>();
        data.put("azione",inserisciAzione.getText().toString());
        ref1.add(data).addOnSuccessListener(documentReference -> {
            Log.d("aggiunto", "addDb: "+documentReference.getId());
            azioni.add(inserisciAzione.getText());

        }).addOnFailureListener(e -> {
            Log.w("errore", "addDb: ", e);
        });
    }
}