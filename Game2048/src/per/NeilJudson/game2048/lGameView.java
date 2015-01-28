package per.NeilJudson.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

public class lGameView extends LinearLayout {

	public lGameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public lGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public lGameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initGameView();
	}
	
	private void initGameView() {
		setGravity(Gravity.CENTER);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		gGameView gGameView = new gGameView(getContext());
		int iLength;
		removeAllViews();
		iLength = Math.min(w, h);
		addView(gGameView, iLength, iLength);
		int iCardWidth = (iLength - 16) / 4;
		gGameView.addCard(iCardWidth, iCardWidth);
		gGameView.startGame();
	}
}
