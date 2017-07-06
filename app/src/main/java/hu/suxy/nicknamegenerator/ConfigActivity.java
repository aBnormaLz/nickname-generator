package hu.suxy.nicknamegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfigActivity extends AppCompatActivity {

    @BindView(R.id.configList)
    RecyclerView mConfigList;

    private ConfigAdapter configAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);
        mConfigList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        configAdapter = new ConfigAdapter((ArrayList<LetterType>) getIntent().getSerializableExtra("config"));
        mConfigList.setAdapter(configAdapter);
    }

    @OnClick(R.id.addButton)
    public void onAddButtonClicked() {
        configAdapter.addConfig();
        mConfigList.post(new Runnable() {
            @Override
            public void run() {
                mConfigList.smoothScrollToPosition(configAdapter.getItemCount() - 1);
            }
        });
    }

    @OnClick(R.id.saveButton)
    public void onSaveButtonClicked() {
        List<LetterType> config = configAdapter.getLetters();
        Intent intent = new Intent();
        intent.putExtra("config", new ArrayList<>(config));
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }
}
