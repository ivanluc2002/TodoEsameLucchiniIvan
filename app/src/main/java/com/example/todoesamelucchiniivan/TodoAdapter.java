package com.example.todoesamelucchiniivan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ProjectViewHolder>{
        private String[] azioni;



        public TodoAdapter(String[] todo){
            this.azioni =todo;

        }
        @NonNull
        @Override
        public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mio_layout,parent,false);

            return new ProjectViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {


            holder.bind(azioni[position]);

        }

        @Override
        public int getItemCount(){
            return azioni.length;
        }


        class ProjectViewHolder extends RecyclerView.ViewHolder {
            private final TextView text;


            public ProjectViewHolder(@NonNull View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.todoText);


            }

            public void bind(String item) {

                text.setText(item);





            }
        }
    }

