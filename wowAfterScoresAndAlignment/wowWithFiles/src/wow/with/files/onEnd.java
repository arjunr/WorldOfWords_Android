package wow.with.files;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class onEnd extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onend);
		TextView res=(TextView) findViewById(R.id.score);
		res.setText(getIntent().getExtras().getString("score"));
		TextView ans=(TextView) findViewById(R.id.result);
		ans.setText(getIntent().getExtras().getString("result"));
	}
}
