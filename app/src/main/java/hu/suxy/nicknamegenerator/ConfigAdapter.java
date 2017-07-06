package hu.suxy.nicknamegenerator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        holder.mType.setSelection(Arrays.asList(LetterType.values()).indexOf(letters.get(position)));
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

    public void removeConfig(int position) {
        letters.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    private void typeSelected(int position, LetterType letterType) {
        letters.remove(position);
        letters.add(position, letterType);
        notifyItemChanged(position);
    }

    public List<LetterType> getLetters() {
        return letters;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.number)
        TextView mNumber;
        @BindView(R.id.type)
        Spinner mType;

        ViewHolder(View configView) {
            super(configView);
            ButterKnife.bind(this, configView);
            setupLetterTypes();
        }

        public void setupLetterTypes() {
            final List<String> list = new ArrayList<>();
            for (LetterType letterType : LetterType.values()) {
                list.add(letterType.name());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mType.setAdapter(dataAdapter);
            mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    typeSelected(getAdapterPosition(), LetterType.valueOf(list.get(position)));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        @OnClick(R.id.deleteButton)
        protected void onItemDelete() {
            removeConfig(getAdapterPosition());
        }
    }

}
