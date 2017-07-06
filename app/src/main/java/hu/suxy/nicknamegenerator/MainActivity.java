package hu.suxy.nicknamegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private LetterAdapter letterAdapter;
    private NicknameGenerator generator;
    private static final int configRequest = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        letterAdapter = new LetterAdapter();
        mRecyclerView.setAdapter(letterAdapter);
        generator = new NicknameGenerator();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        letterAdapter.setName(generator.generateNickname());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent configIntent = new Intent(this, ConfigActivity.class);
            configIntent.putExtra("config", new ArrayList<>(generator.getConfig()));
            startActivityForResult(configIntent, configRequest);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == configRequest && resultCode == AppCompatActivity.RESULT_OK) {
            generator.setConfig((ArrayList<LetterType>) data.getSerializableExtra("config"));
            letterAdapter.setName(generator.generateNickname());
        }
    }
}
