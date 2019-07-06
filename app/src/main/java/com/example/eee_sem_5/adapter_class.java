package com.example.eee_sem_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_class extends RecyclerView.Adapter<adapter_class.view_holder>
{
    Context context;
    ArrayList<notifs> notifs;

    public adapter_class(Context c, ArrayList<notifs> n)
    {
        context = c;
        notifs = n;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new view_holder(LayoutInflater.from(context).inflate(R.layout.card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position)
    {
        holder.title.setText(notifs.get(position).getTitle());
        holder.content.setText(notifs.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }

    class view_holder extends RecyclerView.ViewHolder
    {
        TextView title, content;
        public view_holder(View view)
        {
            super(view);
            title = view.findViewById(R.id.title);
            content = view.findViewById(R.id.content);
        }
    }
}
