package hu.suxy.nicknamegenerator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.ViewHolder> {

    private String name;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_letter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mLetter.setText(String.valueOf(name.charAt(position)));
    }

    @Override
    public int getItemCount() {
        if (name == null) {
            return 0;
        }
        return name.length();
    }

    public void setName(String name) {
        this.name = name;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.letter)
        TextView mLetter;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
