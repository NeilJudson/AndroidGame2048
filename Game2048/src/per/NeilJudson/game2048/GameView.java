package per.NeilJudson.game2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class GameView extends GridLayout {
	private Card[][] cardMap = new Card[4][4];
	private List<Point> emptyPoints = new ArrayList<Point>();

	/*
	 * LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
	 * private void test(){ int h=0; int w = 0; // LayoutParams
	 * lp=(LayoutParams) linearLayout.getLayoutParams();
	 * System.out.println("test "+h+" "+w); System.out.println("1"); //
	 * h=lp.height; linearLayout.measure(w, h); System.out.println("2"); //
	 * w=lp.width; System.out.println("test "+h+" "+w); }
	 */

	private void test2() {
		int h, w;
		h = getHeight();
		w = getWidth();
		System.out.println(h + " " + w);
	}

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initGameView();
		runGame();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initGameView();
		runGame();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initGameView();
		runGame();
	}

	private void initGameView() {
		setColumnCount(4);
		setBackgroundColor(0xffbdada0);
		System.out.println("GameView_initGameView");
	}

	private void runGame() {
		/*
		 * System.out.println("GameView.runGame().test2()"); test2();
		 */
		System.out.println("GameView.runGame()");
		setOnTouchListener(new View.OnTouchListener() {
			private float fStartX, fStartY, fOffsetX, fOffsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("GameView_runGame_onTouch");
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					fStartX = event.getX();
					fStartY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					fOffsetX = event.getX() - fStartX;
					fOffsetY = event.getY() - fStartY;
					if (Math.abs(fOffsetX) > Math.abs(fOffsetY)) {
						if (fOffsetX < -5) {
							// System.out.println("left");
							swipeLeft();
						} else if (fOffsetX > 5) {
							// System.out.println("right");
							swipeRight();
						}
					} else {
						if (fOffsetY < -5) {
							// System.out.println("up");
							swipeUp();
						} else if (fOffsetY > 5) {
							// System.out.println("down");
							swipeDown();
						}
					}
					break;
				}
				return true;
			}
		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		System.out.println("GameView_onSizeChanged");

		/*
		 * int iLength=Math.min(w, h); LayoutParams lp=new LayoutParams();
		 * lp.height=iLength; lp.width=iLength; this.setLayoutParams(lp); int
		 * iCardWidth = (iLength - 16) / 4;
		 */
		int iCardWidth = (Math.min(w, h) - 16) / 4;
		addCard(iCardWidth, iCardWidth);
		startGame();
		// test2();
	}

	private void addCard(int iCardWidth, int iCardHight) {
		System.out.println("GameView_addCard");
		Card c;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				c = new Card(getContext());
				c.setNum(2);
				addView(c, iCardWidth, iCardHight);
				cardMap[i][j] = c;
			}
		}
	}

	private void startGame() {
		System.out.println("GameView_startGame");
		MainActivity.getMainActivity().clearScore();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cardMap[i][j].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}

	private void addRandomNum() {
		System.out.println("GameView.addRandomNum()");
		emptyPoints.clear();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cardMap[i][j].getNum() <= 0) {
					emptyPoints.add(new Point(i, j));
				}
			}
		}
		Point p = emptyPoints
				.remove((int) (Math.random() * emptyPoints.size()));
		cardMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

	private void swipeLeft() {
		boolean merge = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int j1 = j + 1; j1 < 4; j1++) {
					if (cardMap[i][j1].getNum() > 0) {
						if (cardMap[i][j].getNum() <= 0) {
							cardMap[i][j].setNum(cardMap[i][j1].getNum());
							cardMap[i][j1].setNum(0);
							j--;
							merge = true;
						} else if (cardMap[i][j].equals(cardMap[i][j1])) {
							cardMap[i][j].setNum(cardMap[i][j].getNum() * 2);
							cardMap[i][j1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[i][j].getNum());
							merge = true;
						}
						break; // 只要cardMap[i][j1]>0，就会跳出此循环。
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeRight() {
		boolean merge = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j >= 0; j--) {
				for (int j1 = j - 1; j1 >= 0; j1--) {
					if (cardMap[i][j1].getNum() > 0) {
						if (cardMap[i][j].getNum() <= 0) {
							cardMap[i][j].setNum(cardMap[i][j1].getNum());
							cardMap[i][j1].setNum(0);
							j++;
							merge = true;
						} else if (cardMap[i][j].equals(cardMap[i][j1])) {
							cardMap[i][j].setNum(cardMap[i][j].getNum() * 2);
							cardMap[i][j1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[i][j].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeUp() {
		boolean merge = false;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				for (int i1 = i + 1; i1 < 4; i1++) {
					if (cardMap[i1][j].getNum() > 0) {
						if (cardMap[i][j].getNum() <= 0) {
							cardMap[i][j].setNum(cardMap[i1][j].getNum());
							cardMap[i1][j].setNum(0);
							i--;
							merge = true;
						} else if (cardMap[i][j].equals(cardMap[i1][j])) {
							cardMap[i][j].setNum(cardMap[i][j].getNum() * 2);
							cardMap[i1][j].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[i][j].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeDown() {
		boolean merge = false;
		for (int j = 0; j < 4; j++) {
			for (int i = 3; i >= 0; i--) {
				for (int i1 = i - 1; i1 >= 0; i1--) {
					if (cardMap[i1][j].getNum() > 0) {
						if (cardMap[i][j].getNum() <= 0) {
							cardMap[i][j].setNum(cardMap[i1][j].getNum());
							cardMap[i1][j].setNum(0);
							i++;
							merge = true;
						} else if (cardMap[i][j].equals(cardMap[i1][j])) {
							cardMap[i][j].setNum(cardMap[i][j].getNum() * 2);
							cardMap[i1][j].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[i][j].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void checkComplete() {
		boolean complete = true;
		ALL: for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cardMap[i][j].getNum() == 0
						|| (i > 0 && cardMap[i][j].equals(cardMap[i - 1][j]))
						|| (i < 3 && cardMap[i][j].equals(cardMap[i + 1][j]))
						|| (j > 0 && cardMap[i][j].equals(cardMap[i][j - 1]))
						|| (j < 3 && cardMap[i][j].equals(cardMap[i][j + 1]))) {
					complete = false;
					break ALL; // 直接跳到ALL，结束了两个循环。
				}
			}
		}
		if (complete) {
			new AlertDialog.Builder(getContext())
					.setTitle("你好")
					.setMessage("游戏结束")
					.setPositiveButton("重来",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									startGame();
								}
							}).show();
		}
	}
}