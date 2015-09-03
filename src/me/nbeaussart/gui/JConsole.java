/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.nbeaussart.gui;

import com.lifeproject.log.UtilLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc

/**
 * Class implementing a Swing-based text console
 *
 * Principles: - provides a fixed number of rows and columns, but can be resized
 * - each cell can have its own foreground and background colour - The main font
 * determines the grid size.
 *
 * @author beaussan
 */
public class JConsole extends JComponent implements HierarchyListener {

	/**
	 * The KeyInputerTerminal Class.
	 */
	private class KeyInputerTerminal extends KeyAdapter {

		/** The Constant STRING. */
		private final static int STRING = 0;

		/** The Constant FLOAT_UNSIGNED. */
		private final static int FLOAT_UNSIGNED = 1;

		/** The Constant FLOAT_SIGNED. */
		private final static int FLOAT_SIGNED = 2;

		/** The Constant INT_UNSIGNED. */
		private final static int INT_UNSIGNED = 3;

		/** The Constant INT_SIGNED. */
		private final static int INT_SIGNED = 4;

		/** The size. */
		private final int size;

		/** The type. */
		private final int type;

		/** The ls objs. */
		private String[] lsObjs;

		/** The curr. */
		private int curr = 0;

		/** The last xc. */
		private int lastXC;

		/** The last yc. */
		private int lastYC;

		/**
		 * Instantiates a new key inputer terminal.
		 *
		 * @param size
		 *            the size
		 * @param type
		 *            the type
		 */
		public KeyInputerTerminal(int size, int type) {
			this.size = size;
			this.type = type;
		}

		/**
		 * Instantiates a new key inputer terminal.
		 *
		 * @param lsObjs
		 *            the ls objs
		 */
		public KeyInputerTerminal(String[] lsObjs) {
			size = -1;
			type = -1;
			this.lsObjs = lsObjs;
			lastXC = getCursorX();
			lastYC = getCursorY();
			writeCurr();
		}

		/**
		 * Handle type.
		 *
		 * @param c
		 *            the c
		 */
		public void handleType(char c) {
			if (size != -1) {
				if (currRead.length() >= size) {
					return;
				}
			}
            if ((int)c == 65535){
                return;
            }
			switch (type) {
			case STRING:
				if (Character.isISOControl(c)) {
					return;
				}
				write(c);
				repaint();
				currRead += c;
				break;

			case FLOAT_SIGNED:
			case INT_SIGNED:
				if ((currRead.isEmpty() && c == '-')) {
					write(c);
					repaint();
					currRead += c;
					break;
				}
			case FLOAT_UNSIGNED:
				if (type == FLOAT_SIGNED
						&& (c == '.' && !currRead.contains("."))) {
					write(c);
					repaint();
					currRead += c;
					break;
				}
			case INT_UNSIGNED:
				if (Character.isDigit(c)) {
					write(c);
					repaint();
					currRead += c;
					break;
				}
				break;
			default:
				break;
			}
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			LOG.finest("Got " + e.getKeyCode() + " : " + (int) e.getKeyChar());
			if (!isReading) {
				return;
			}
			if (currRead == null) {
				currRead = "";
			}
			if (type == -1) {
				currRead = lsObjs[curr];
			}

			if (e.getKeyChar() == '\n') {
				isReading = false;
			} else if (type == -1) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					writePrev();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					writeNext();
				}
			} else if (e.getKeyChar() == 8) {
				if (currRead.isEmpty()) {
					return;
				}
				currRead = currRead.substring(0, currRead.length() - 1);
				moveCursor(e.getKeyChar());
				write(' ');
				moveCursor(e.getKeyChar());

			} else {
				handleType(e.getKeyChar());

			}
		}

		/**
		 * Write curr.
		 */
		private void writeCurr() {
			while (getCursorX() != lastXC || getCursorY() != lastYC) {
				backspace();
			}
			write(lsObjs[curr]);
		}

		/**
		 * Write next.
		 */
		private void writeNext() {
			curr++;
			curr = curr % lsObjs.length;
			writeCurr();
		}

		/**
		 * Write prev.
		 */
		private void writePrev() {
			curr--;
			if (curr < 0) {
				curr = lsObjs.length - 1;
			}
			writeCurr();
		}
	}

	/**
	 * Utility class to handle the cursor blink animations.
	 */
	private class TimerAction implements ActionListener {

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (cursorBlinkOn && isShowing()) {
				cursorInverted = !cursorInverted;
				// repaintArea(getCursorX(), getCursorY(), 1, 1);
				repaint();
			} else {
				stopBlinking();
			}
		}
	}

	/** The Constant LOG. */
	private static final Logger LOG = UtilLog.getLog(JConsole.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3571518591759968333L;

	/** The Constant DEFAULT_FOREGROUND. */
	private static final Color DEFAULT_FOREGROUND = Color.LIGHT_GRAY;

	/** The Constant DEFAULT_BACKGROUND. */
	private static final Color DEFAULT_BACKGROUND = Color.BLACK;

	/** The Constant DEFAULT_FONT. */
	private static final Font DEFAULT_FONT = new Font("Courier New",
			Font.PLAIN, 18);

	/** The Constant DEFAULT_BLINKRATE. */
	private static final int DEFAULT_BLINKRATE = 200;

	/** The Constant DEFAULT_BLINK_ON. */
	private static final boolean DEFAULT_BLINK_ON = true;

	/** The data. */
	private final ConsoleData data = new ConsoleData();

	/** The font width. */
	private int fontWidth;

	/** The font height. */
	private int fontHeight;

	/** The font y offset. */
	private int fontYOffset;

	/** The cursor visible. */
	private boolean cursorVisible = false;

	/** The cursor blink on state. */
	private boolean cursorBlinkOn = true;

	/** The cursor inverted. */
	private boolean cursorInverted = true;

	/** The cursor x pos. */
	private int cursorX = 0;

	/** The cursor y pos. */
	private int cursorY = 0;

	/** The main font. */
	private Font mainFont = null;

	/** The current font. */
	private Font currentFont = null;

	/** The current foreground. */
	private Color currentForeground = DEFAULT_FOREGROUND;

	/** The current background. */
	private Color currentBackground = DEFAULT_BACKGROUND;

	/** The blink timer. */
	private Timer blinkTimer;

	/** The ps. */
	private PrintStream ps;

	/** The is reading. */
	private volatile boolean isReading = false;

	/** The curr read. */
	private volatile String currRead = "";

	/**
	 * Instantiates a new j console.
	 *
	 * @param columns
	 *            the columns
	 * @param rows
	 *            the rows
	 */
	public JConsole(int columns, int rows) {
		setMainFont(DEFAULT_FONT);
		setFont(mainFont);
		init(columns, rows);
        setFocusable(true);
		if (DEFAULT_BLINK_ON) {
			setCursorBlink(true);
		}
		addNotify();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#addNotify()
	 */
	@Override
	public void addNotify() {
		super.addNotify();
		addHierarchyListener(this);
	}

	/**
	 * Backspace.
	 */
	public void backspace() {
		moveCursor((char) 8);
		write(' ');
		moveCursor((char) 8);
	}

	/**
	 * Redirects System.out to this console by calling System.setOut
	 */
	public void captureStdOut() {
		System.setOut(getPrintStream());
	}

	/**
	 * Clear everything.
	 */
	public synchronized void clear() {
		clearArea(0, 0, data.columns, data.rows);
	}

	/**
	 * Clear area.
	 *
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	private synchronized void clearArea(int column, int row, int width,
			int height) {
		data.fillArea(' ', currentForeground, currentBackground, currentFont,
				column, row, width, height);
		repaintArea(0, 0, width, height);
	}

	/**
	 * Clear screen.
	 */
	public synchronized void clearScreen() {
		clear();
		resetCursor();
	}

	/**
	 * Fill area.
	 *
	 * @param c
	 *            the char to fill with
	 * @param fg
	 *            the foreground color
	 * @param bg
	 *            the background color
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public synchronized void fillArea(char c, Color fg, Color bg, int column,
			int row, int width, int height) {
		data.fillArea(c, fg, bg, currentFont, column, row, width, height);
		repaintArea(column, row, width, height);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.Component#getBackground()
	 */
	@Override
	public Color getBackground() {
		return currentBackground;
	}

	/**
	 * Gets the background at.
	 *
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @return the background at
	 */
	public Color getBackgroundAt(int column, int row) {
		return data.getBackgroundAt(column, row);
	}

	/**
	 * Gets the char at.
	 *
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @return the char at
	 */
	public char getCharAt(int column, int row) {
		return data.getCharAt(column, row);
	}

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public int getColumns() {
		return data.columns;
	}

	/**
	 * Gets the column value.
	 *
	 * @param y
	 *            the y
	 * @return the column value
	 */
	public int getColumnValue(int y) {
		return y / fontWidth;
	}

	/**
	 * Gets the cursor x.
	 *
	 * @return the cursor x
	 */
	public int getCursorX() {
		return cursorX;
	}

	/**
	 * Gets the cursor y.
	 *
	 * @return the cursor y
	 */
	public int getCursorY() {
		return cursorY;
	}

	/**
	 * Gets the font at.
	 *
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @return the font at
	 */
	public Font getFontAt(int column, int row) {
		return data.getFontAt(column, row);
	}

	/**
	 * Gets the font height.
	 *
	 * @return the font height
	 */
	public int getFontHeight() {
		return fontHeight;
	}

	/**
	 * Gets the font width.
	 *
	 * @return the font width
	 */
	public int getFontWidth() {
		return fontWidth;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.Component#getForeground()
	 */
	@Override
	public Color getForeground() {
		return currentForeground;
	}

	/**
	 * Gets the foreground at.
	 *
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @return the foreground at
	 */
	public Color getForegroundAt(int column, int row) {
		return data.getForegroundAt(column, row);
	}

	/**
	 * Gets the prints the stream.
	 *
	 * @return the prints the stream
	 */
	public PrintStream getPrintStream() {
		if (ps == null) {
			ps = new PrintStream(System.out) {
				@Override
				public void println(String x) {
					writeln(x);
				}
			};
		}
		return ps;
	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public int getRows() {
		return data.rows;
	}

	/**
	 * Gets the row value.
	 *
	 * @param x
	 *            the x
	 * @return the row value
	 */
	public int getRowValue(int x) {
		return x / fontHeight;
	}

	/**
	 * Gets the timer.
	 *
	 * @return the timer
	 */
	private Timer getTimer() {
		if (blinkTimer == null) {
			blinkTimer = new Timer(DEFAULT_BLINKRATE, new TimerAction());
			blinkTimer.setRepeats(true);
			if (cursorBlinkOn) {
				startBlinking();
			}
		}
		return blinkTimer;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.HierarchyListener#hierarchyChanged(java.awt.event.
	 * HierarchyEvent)
	 */
	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
			if (isShowing()) {
				startBlinking();
			} else {
				stopBlinking();
			}
		}
	}

	/**
	 * Initialises the console to a specified size.
	 *
	 * @param columns
	 *            the columns
	 * @param rows
	 *            the rows
	 */
	protected synchronized void init(int columns, int rows) {
		data.init(columns, rows);
		Arrays.fill(data.background, DEFAULT_BACKGROUND);
		Arrays.fill(data.foreground, DEFAULT_FOREGROUND);
		Arrays.fill(data.font, DEFAULT_FONT);
		Arrays.fill(data.text, ' ');

		setPreferredSize(new Dimension(columns * fontWidth, rows * fontHeight));
	}

	/**
	 * Move cursor.
	 *
	 * @param c
	 *            the c
	 */
	private synchronized void moveCursor(char c) {
		switch (c) {
		case '\n':
			cursorY++;
			cursorX = 0;
			break;
		case (char) 8:
			cursorX--;
			break;
		default:
			cursorX++;
			break;
		}

		if (0 > cursorX) {
			cursorX = data.columns - 1;
			cursorY--;
		}

		if (cursorX >= data.columns) {
			cursorX = 0;
			cursorY++;
		}
		if (cursorY >= data.rows) {
			cursorY = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		Rectangle r = g.getClipBounds();

		// AffineTransform textTransform=new AffineTransform();
		// textTransform.scale(fontWidth, fontHeight);
		// g.setTransform(textTransform);

		// calculate x and y range to redraw
		int x1 = (int) (r.getMinX() / fontWidth);
		int x2 = (int) (r.getMaxX() / fontWidth) + 1;
		int y1 = (int) (r.getMinY() / fontWidth);
		int y2 = (int) (r.getMaxY() / fontWidth) + 1;

		int curX = getCursorX();
		int curY = getCursorY();

		for (int j = Math.max(0, y1); j < Math.min(y2, data.rows); j++) {
			int offset = j * data.columns;
			int start = Math.max(x1, 0);
			int end = Math.min(x2, data.columns);

			while (start < end) {
				Color nfg = data.foreground[offset + start];
				Color nbg = data.background[offset + start];
				Font nf = data.font[offset + start];

				// index of ending position
				int i = start + 1;

				if ((j == curY) && (start == curX)) {
					if (cursorVisible && cursorBlinkOn && cursorInverted) {
						// swap foreground and background colours
						Color t = nfg;
						nfg = nbg;
						nbg = t;
					}
				} else {
					// detect run
					while ((i < end) && (!((j == curY) && (i == curX)))
							&& (nfg == data.foreground[offset + i])
							&& (nbg == data.background[offset + i])
							&& (nf == data.font[offset + i])) {
						i++;
					}
				}

				// set font
				g.setFont(nf);

				// draw background
				g.setBackground(nbg);
				g.clearRect(fontWidth * start, j * fontHeight, fontWidth
						* (i - start), fontHeight);

				// draw chars up to this point
				g.setColor(nfg);
				for (int k = start; k < i; k++) {
					g.drawChars(data.text, offset + k, 1, k * fontWidth, j
							* fontHeight + fontYOffset);
				}
				start = i;
			}
		}
	}

	/**
	 * Read.
	 *
	 * @return the string
	 */
	public String read() {
		return read(-1, KeyInputerTerminal.STRING);
	}

	/**
	 * Read.
	 *
	 * @param size
	 *            the size
	 * @return the string
	 */
	public String read(int size) {
		return read(size, KeyInputerTerminal.STRING);
	}

	/**
	 * Read.
	 *
	 * @param size
	 *            the size
	 * @param type
	 *            the type
	 * @return the string
	 */
	private String read(int size, int type) {
		return read(new KeyInputerTerminal(size, type));
	}

	/**
	 * Read.
	 *
	 * @param kl
	 *            the kl
	 * @return the string
	 */
	private String read(KeyInputerTerminal kl) {
		isReading = true;
		currRead = "";
		requestFocus();
		LOG.finest("Adding KeyListener");
		addKeyListener(kl);
		while (isReading) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		isReading = false;
		LOG.finest("Removing KeyListener");
		removeKeyListener(kl);

		return currRead;
	}

	/**
	 * Read.
	 *
	 * @param lsChoix
	 *            the ls choix
	 * @return the string
	 */
	public String read(String[] lsChoix) {
		return read(new KeyInputerTerminal(lsChoix));
	}

	/**
	 * Read float.
	 *
	 * @param size
	 *            the size
	 * @param type
	 *            the type
	 * @return the float
	 */
	private float readFloat(int size, int type) {
		try {
			return Float.valueOf(read(size, type));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Read float signed.
	 *
	 * @return the float
	 */
	public float readFloatSigned() {
		return readFloat(-1, KeyInputerTerminal.FLOAT_SIGNED);
	}

	/**
	 * Read float signed.
	 *
	 * @param size
	 *            the size
	 * @return the float
	 */
	public float readFloatSigned(int size) {
		return readFloat(size, KeyInputerTerminal.FLOAT_SIGNED);
	}

	/**
	 * Read float unsigned.
	 *
	 * @return the float
	 */
	public float readFloatUnSigned() {
		return readFloat(-1, KeyInputerTerminal.FLOAT_UNSIGNED);
	}

	/**
	 * Read float unsigned.
	 *
	 * @param size
	 *            the size
	 * @return the float
	 */
	public float readFloatUnSigned(int size) {
		return readFloat(size, KeyInputerTerminal.FLOAT_UNSIGNED);
	}

	/**
	 * Read int.
	 *
	 * @param size
	 *            the size
	 * @param type
	 *            the type
	 * @return the int
	 */
	private int readInt(int size, int type) {
		try {
			return Integer.valueOf(read(size, type));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Read int signed.
	 *
	 * @return the int
	 */
	public int readIntSigned() {
		return readInt(-1, KeyInputerTerminal.INT_SIGNED);
	}

	/**
	 * Read int signed.
	 *
	 * @param size
	 *            the size
	 * @return the int
	 */
	public int readIntSigned(int size) {
		return readInt(size, KeyInputerTerminal.INT_SIGNED);
	}

	/**
	 * Read int unsigned.
	 *
	 * @return the int
	 */
	public int readIntUnSigned() {
		return readInt(-1, KeyInputerTerminal.INT_UNSIGNED);
	}

	/**
	 * Read int unsigned.
	 *
	 * @param size
	 *            the size
	 * @return the int
	 */
	public int readIntUnSigned(int size) {
		return readInt(size, KeyInputerTerminal.INT_UNSIGNED);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#removeNotify()
	 */
	@Override
	public void removeNotify() {
		removeHierarchyListener(this);
		super.removeNotify();
	}

	/**
	 * Fires a repaint event on a specified rectangle of characters in the
	 * console.
	 *
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void repaintArea(int column, int row, int width, int height) {
		int fw = getFontWidth();
		int fh = getFontHeight();
		repaint(column * fw, row * fh, width * fw, height * fh);
	}

	/**
	 * Reset cursor.
	 */
	public synchronized void resetCursor() {
		repaintArea(cursorX, cursorY, 0, 0);
		cursorX = 0;
		cursorY = 0;
		repaintArea(cursorX, cursorY, 0, 0);
	}

	/**
	 * Redirects System.out to the standard console by calling System.setOut
	 */
	public void resetStdOut() {
		System.setOut(UtilLog.getDefaultOut());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.Component#resize(int, int)
	 */
	@Override
	public void resize(int columns, int rows) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#setBackground(java.awt.Color)
	 */
	@Override
	public void setBackground(Color c) {
		currentBackground = c;
	}

	/**
	 * Sets the blink delay.
	 *
	 * @param millis
	 *            the new blink delay
	 */
	public void setBlinkDelay(int millis) {
		getTimer().setDelay(millis);
	}

	/**
	 * Sets the columns.
	 *
	 * @param columns
	 *            the new columns
	 */
	public synchronized void setColumns(int columns) {
		resize(columns, data.rows);
	}

	/**
	 * Sets the cursor blink.
	 *
	 * @param blink
	 *            the new cursor blink
	 */
	public void setCursorBlink(boolean blink) {
		if (blink) {
			cursorBlinkOn = true;
			startBlinking();
		} else {
			cursorBlinkOn = false;
			stopBlinking();
		}
	}

	/**
	 * Sets the cursor pos.
	 *
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 */
	public synchronized void setCursorPos(int column, int row) {
		if ((column < 0) || (column >= data.columns)) {
			throw new Error("Invalid X cursor position: " + column);
		}
		if ((row < 0) || (row >= data.rows)) {
			throw new Error("Invalid Y cursor position: " + row);
		}
		cursorX = column;
		cursorY = row;
	}

	/**
	 * Sets the cursor visible.
	 *
	 * @param visible
	 *            the new cursor visible
	 */
	public void setCursorVisible(boolean visible) {
		cursorVisible = visible;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#setFont(java.awt.Font)
	 */
	@Override
	public void setFont(Font f) {
		currentFont = f;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#setForeground(java.awt.Color)
	 */
	@Override
	public void setForeground(Color c) {
		currentForeground = c;
	}

	/**
	 * Sets the main font of the console, which is used to determine the size of
	 * characters.
	 *
	 * @param font
	 *            the new main font
	 */
	public void setMainFont(Font font) {
		mainFont = font;

		FontRenderContext fontRenderContext = new FontRenderContext(
				mainFont.getTransform(), false, false);
		Rectangle2D charBounds = mainFont.getStringBounds("X",
				fontRenderContext);
		fontWidth = (int) charBounds.getWidth();
		fontHeight = (int) charBounds.getHeight();
		fontYOffset = -(int) charBounds.getMinY();

		setPreferredSize(new Dimension(data.columns * fontWidth, data.rows
				* fontHeight));

		repaint();
	}

	/**
	 * Sets the rows.
	 *
	 * @param rows
	 *            the new rows
	 */
	public void setRows(int rows) {
		resize(data.columns, rows);
	}

	/**
	 * Start blinking.
	 */
	private void startBlinking() {
		getTimer().start();
	}

	/**
	 * Stop blinking.
	 */
	private void stopBlinking() {
		if (blinkTimer != null) {
			blinkTimer.stop();
			cursorInverted = true;
		}
	}

	/**
	 * Write.
	 *
	 * @param c
	 *            the c
	 */
	public void write(char c) {
		data.setDataAt(cursorX, cursorY, c, currentForeground,
				currentBackground, currentFont);
		moveCursor(c);
	}

	/**
	 * Write.
	 *
	 * @param string
	 *            the string
	 */
	public synchronized void write(String string) {
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			write(c);
		}
	}

	/**
	 * Write.
	 *
	 * @param string
	 *            the string
	 * @param foreGround
	 *            the fore ground
	 * @param backGround
	 *            the back ground
	 */
	public synchronized void write(String string, Color foreGround,
			Color backGround) {
		Color foreTemp = currentForeground;
		Color backTemp = currentBackground;
		setForeground(foreGround);
		setBackground(backGround);
		write(string);
		setForeground(foreTemp);
		setBackground(backTemp);
	}

	/**
	 * Writeln.
	 *
	 * @param line
	 *            the line
	 */
	public synchronized void writeln(String line) {
		write(line);
		write('\n');
	}

}
