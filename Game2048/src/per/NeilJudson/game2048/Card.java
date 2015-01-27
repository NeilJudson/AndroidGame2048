package per.NeilJudson.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {
	private int iNum = 0;
	private TextView tvLabel;

	public Card(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		tvLabel = new TextView(getContext());
		tvLabel.setTextSize(48);
		tvLabel.setGravity(Gravity.CENTER);
		tvLabel.setBackgroundColor(0x44ffffff);
		tvLabel.getPaint().setFakeBoldText(true);
		LayoutParams lp = new LayoutParams(-1, -1);
		lp.setMargins(16, 16, 0, 0);
		addView(tvLabel, lp);
		setNum(0);
	}

	public int getNum() {
		return iNum;
	}

	public void setNum(int iNum) {
		this.iNum = iNum;
		if (iNum <= 0) {
			tvLabel.setText("");
		} else {
			tvLabel.setText(iNum + "");// 这样可以把iNum转化为字符串
		}
	}

	public boolean equals(Card o) {
		// TODO Auto-generated method stub
		return getNum() == o.getNum();
	}
}