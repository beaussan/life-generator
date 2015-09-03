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

import java.awt.*;

/**
 * Class used for storing console data.
 * 
 * @author beaussan
 */
public final class ConsoleData {

	/** The capacity. */
	private int capacity = 0;

	/** The rows. */
	public int rows;

	/** The columns. */
	public int columns;

	/** The background array. */
	public Color[] background;

	/** The foreground array. */
	public Color[] foreground;

	/** The font array. */
	public Font[] font;

	/** The text array. */
	public char[] text;

	/**
	 * Instantiates a new console data.
	 */
	ConsoleData() {
	}

	/**
	 * Ensure capacity.
	 * 
	 * @param minCapacity
	 *            the min capacity
	 */
	private void ensureCapacity(int minCapacity) {
		if (capacity >= minCapacity) {
			return;
		}

		char[] newText = new char[minCapacity];
		Color[] newBackground = new Color[minCapacity];
		Color[] newForeground = new Color[minCapacity];
		Font[] newFont = new Font[minCapacity];

		int size = rows * columns;
		if (size > 0) {
			System.arraycopy(text, 0, newText, 0, size);
			System.arraycopy(foreground, 0, newForeground, 0, size);
			System.arraycopy(background, 0, newBackground, 0, size);
			System.arraycopy(font, 0, newFont, 0, size);
		}

		text = newText;
		foreground = newForeground;
		background = newBackground;
		font = newFont;
		capacity = minCapacity;
	}

	/**
	 * Fill area.
	 * 
	 * @param c
	 *            the char
	 * @param fg
	 *            the foreground color
	 * @param bg
	 *            the background color
	 * @param f
	 *            the font
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void fillArea(char c, Color fg, Color bg, Font f, int column,
			int row, int width, int height) {
		for (int q = Math.max(0, row); q < Math.min(row + height, rows); q++) {
			for (int p = Math.max(0, column); p < Math.min(column + width,
					columns); p++) {
				int offset = p + q * columns;
				text[offset] = c;
				foreground[offset] = fg;
				background[offset] = bg;
				font[offset] = f;
			}
		}
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
		int offset = column + row * columns;
		return background[offset];
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
		int offset = column + row * columns;
		return text[offset];
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
		int offset = column + row * columns;
		return font[offset];
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
		int offset = column + row * columns;
		return foreground[offset];
	}

	/**
	 * Inits the ConsoleData.
	 * 
	 * @param columns
	 *            the columns
	 * @param rows
	 *            the rows
	 */
	void init(int columns, int rows) {
		ensureCapacity(rows * columns);
		this.rows = rows;
		this.columns = columns;
	}

	/**
	 * Sets a single character position.
	 * 
	 * @param column
	 *            the column
	 * @param row
	 *            the row
	 * @param c
	 *            the char
	 * @param fg
	 *            the foreground color
	 * @param bg
	 *            the background color
	 * @param f
	 *            the font
	 */
	public void setDataAt(int column, int row, char c, Color fg, Color bg,
			Font f) {
		int pos = column + row * columns;
		text[pos] = c;
		foreground[pos] = fg;
		background[pos] = bg;
		font[pos] = f;
	}
}