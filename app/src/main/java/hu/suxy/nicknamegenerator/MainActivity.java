package hu.suxy.nicknamegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private LetterAdapter letterAdapter;
    private NicknameGenerator generator;

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
            startActivity(new Intent(this, ConfigActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
