package wowtry2.in.and23;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WowTry2Activity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    Button b0,b1,b2,b3,b4,b5,b6;
    TextView t1,t2,t3,t4,t5,t6;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String inputString="oranges";
        Button b0=(Button) findViewById(R.id.button1);
        Button b1=(Button) findViewById(R.id.Button02);
        Button b2=(Button) findViewById(R.id.Button03);
        Button b3=(Button) findViewById(R.id.Button04);
        Button b4=(Button) findViewById(R.id.Button05);
        Button b5=(Button) findViewById(R.id.Button06);
        Button b6=(Button) findViewById(R.id.Button07);
        Button b[]={b0,b1,b2,b3,b4,b5,b6};
        TextView t1=(TextView) findViewById(R.id.textView1);
		TextView t2=(TextView) findViewById(R.id.textView3);
		TextView t3=(TextView) findViewById(R.id.textView4);
		TextView t4=(TextView) findViewById(R.id.textView5);
		TextView t5=(TextView) findViewById(R.id.textView6);
		TextView t6=(TextView) findViewById(R.id.textView7);
        int len = inputString.length();
        for(int i=0;i<len;i++){
        	b[i].setOnClickListener(this);
        	String word=inputString.substring(i, i+1);
        	b[i].setText(word);
        	Log.d("arjun",word);
        }
        
        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
			//t3.setText("Button Three");
	//	b3.setVisibility(View.GONE);
		}
	}
