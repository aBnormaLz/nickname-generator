package hu.suxy.nicknamegenerator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfigAdapter extends RecyclerView.Adapter<ConfigAdapter.ViewHolder> {

    private List<LetterType> letters = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_config, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mNumber.setText("" + (position + 1) + ".");
        holder.mType.setText(letters.get(position).name());
    }

    @Override
    public int getItemCount() {
        if (letters == null) {
            return 0;
        }
        return letters.size();
    }

    public void addConfig() {
        letters.add(LetterType.DONT_CARE);
        notifyItemInserted(letters.size() - 1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.number)
        TextView mNumber;
        @BindView(R.id.type)
        TextView mType;

        ViewHolder(View configView) {
            super(configView);
            ButterKnife.bind(this, configView);
        }
    }

}
