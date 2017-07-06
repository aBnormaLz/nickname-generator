package hu.suxy.nicknamegenerator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        configAdapter = new ConfigAdapter();
        mConfigList.setAdapter(configAdapter);
    }

    @OnClick(R.id.addButton)
    public void onViewClicked() {
        configAdapter.addConfig();
        mConfigList.post(new Runnable() {
            @Override
            public void run() {
                mConfigList.smoothScrollToPosition(configAdapter.getItemCount() - 1);
            }
        });
    }
}
