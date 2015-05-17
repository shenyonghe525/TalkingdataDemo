package com.syh.talkingdatademo;

import java.util.HashMap;
import java.util.Map;
import com.tendcloud.tenddata.TCAgent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button buyBtn, staffBtn,actorBtn;

	private final String EVENTID = "购买";

	private final String EVENTLABEL = "工具栏里面的XXX设备";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TCAgent.LOG_ON = true;
		TCAgent.init(this);
		TCAgent.setReportUncaughtExceptions(true);
		initViews();
	}

	private void initViews() {
		buyBtn = (Button) findViewById(R.id.buy);
		staffBtn = (Button) findViewById(R.id.staff);
		actorBtn = (Button) findViewById(R.id.actor);
		buyBtn.setOnClickListener(this);
		staffBtn.setOnClickListener(this);
		actorBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		Map<String, Object> kv;
		switch (v.getId()) {
		case R.id.buy:
			kv = new HashMap<String, Object>();
			kv.put("商品类型", "道具");
			kv.put("价格", "5～10元");
			kv.put("购买个数", "2个");
			TCAgent.onEvent(MainActivity.this, EVENTID, EVENTLABEL, kv);
			break;

		case R.id.staff:
			kv = new HashMap<String, Object>();
			kv.put("职业", "员工");
			TCAgent.onEvent(MainActivity.this, "人物属性", "职业", kv);
			break;
			
		case R.id.actor:
			kv = new HashMap<String, Object>();
			kv.put("职业", "演员");
			TCAgent.onEvent(MainActivity.this, "人物属性", "职业", kv);
			break;
		}
	}

	protected void onResume() {
		super.onResume();
		TCAgent.onResume(this);
	}

	protected void onPause() {
		super.onPause();
		TCAgent.onPause(this);
	}

}
