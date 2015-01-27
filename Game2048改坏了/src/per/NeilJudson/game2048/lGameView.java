package per.NeilJudson.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class lGameView extends LinearLayout {

	public lGameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initlGameView();
	}

	public lGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initlGameView();
	}

	public lGameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initlGameView();
	}
	
	private void initlGameView() {
		setBackgroundColor(0xffffffff);
		System.out.println("lGameView_initlGameView");
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		gGameView.igGameViewWidth = Math.min(h, w);
		
		System.out.println("lGameView_onSizeChanged"+gGameView.igGameViewWidth);
	}
}
