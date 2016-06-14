//------------------------------------------------------------------------------
//                         COPYRIGHT 2010 GUIDEBEE
//                           ALL RIGHTS RESERVED.
//                     GUIDEBEE CONFIDENTIAL PROPRIETARY 
///////////////////////////////////// REVISIONS ////////////////////////////////
// Date       Name                 Tracking #         Description
// ---------  -------------------  ----------         --------------------------
// 12AUG2010  James Shen                 	          Code review
////////////////////////////////////////////////////////////////////////////////
//--------------------------------- PACKAGE ------------------------------------
package com.mapdigit.game;

//--------------------------------- IMPORTS ------------------------------------
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

//[------------------------------ MAIN CLASS ----------------------------------]
////////////////////////////////////////////////////////////////////////////////
//--------------------------------- REVISIONS ----------------------------------
//Date       Name                 Tracking #         Description
//--------   -------------------  -------------      --------------------------
//12AUG2010  James Shen                 	         Code review
////////////////////////////////////////////////////////////////////////////////
/**
* A TiledLayer is a visual element composed of a grid of cells that can be 
* filled with a set of tile images. This class allows large virtual layers 
* to be created without the need for an extremely large Image. This technique 
* is commonly used in 2D gaming platforms to create very large scrolling
*  backgrounds.
* <hr>
* <b>&copy; Copyright 2010 Guidebee Pty Ltd. All Rights Reserved.</b>
* 
* @version 1.00, 12/08/10
* @author Guidebee Pty Ltd.
*/
public class TiledLayer extends Layer {
	
    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Creates a new TiledLayer. The TiledLayer's grid will be rows cells high 
	 * and columns cells wide. All cells in the grid are initially empty 
	 * (i.e. they contain tile index 0). The contents of the grid may be modified
	 * through the use of setCell(int, int, int) and 
	 * fillCells(int, int, int, int, int). The static tile set for the 
	 * TiledLayer is created from the specified Image with each tile having the
	 * dimensions of tileWidth x tileHeight. The width of the source image 
	 * must be an integer multiple of the tile width, and the height of the 
	 * source image must be an integer multiple of the tile height; otherwise,
	 * an IllegalArgumentException is thrown;The entire static tile set can 
	 * be changed using setStaticTileSet(Image, int, int). These methods 
	 * should be used sparingly since they are both memory and time consuming.
	 * Where possible, animated tiles should be used instead to animate 
	 * tile appearance.
	 * @param cols the width of the TiledLayer, expressed as a number of cells
	 * @param rows the height of the TiledLayer, expressed as a number of cells
	 * @param img the Image to use for creating the static tile set
	 * @param tileWidth the width in pixels of a single tile
	 * @param tileHeight the height in pixels of a single tile 
	 */
	public TiledLayer(int cols, int rows, Bitmap img, int tileWidth,
			int tileHeight) {
		// the specification doesn't states if the TiledLayer is visible on
		// creation
		// we assume it is
		super(0, 0, cols * tileWidth, rows * tileHeight, true);

		if (img == null)
			throw new NullPointerException();
		if (cols <= 0 || rows <= 0 || tileHeight <= 0 || tileWidth <= 0)
			throw new IllegalArgumentException();
		if (img.getWidth() % tileWidth != 0
				|| img.getHeight() % tileHeight != 0)
			throw new IllegalArgumentException();

		this.img = img;
		this.cols = cols;
		this.rows = rows;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.numStaticTiles = (img.getWidth() / tileWidth)
				* (img.getHeight() / tileHeight);
		this.tiles = new int[rows][cols];
		this.animatedTiles = new int[5];
		this.numAnimatedTiles = 0;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Creates a new animated tile and returns the index that refers to the new 
     * animated tile. It is initially associated with the specified tile index 
     * (either a static tile or 0). 
     * The indices for animated tiles are always negative. The first animated
     *  tile shall have the index -1, the second, -2, etc. 
     */
	public int createAnimatedTile(int staticTileIndex) {
		synchronized (this) {
			if (staticTileIndex < 0 || staticTileIndex > numStaticTiles)
				throw new IndexOutOfBoundsException();

			if (numAnimatedTiles == animatedTiles.length) {
				int[] temp = new int[numAnimatedTiles + 6];
				System.arraycopy(animatedTiles, 0, temp, 0, numAnimatedTiles);
				animatedTiles = temp;
			}

			animatedTiles[numAnimatedTiles] = staticTileIndex;
			numAnimatedTiles++;
			return -numAnimatedTiles;
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the tile referenced by an animated tile. 
	 * @param index the index of the animated tile 
	 * @return Returns the tile index currently associated with the animated tile. 
	 */
	public int getAnimatedTile(int index) {
		synchronized (this) {
			index = -index - 1;
			if (index < 0 || index >= numAnimatedTiles)
				throw new IndexOutOfBoundsException();
			return animatedTiles[index];
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Associates an animated tile with the specified static tile. 
	 * @param index the index of the animated tile 
	 * @param  staticTileIndex the index of the associated tile (must be 0 or
	 *  a valid static tile index) . 
	 */
	public void setAnimatedTile(int index, int staticTileIndex) {
		synchronized (this) {
			index = -index - 1;
			if (index < 0 || index >= numAnimatedTiles)
				throw new IndexOutOfBoundsException();
			if (staticTileIndex < 0 || staticTileIndex > numStaticTiles)
				throw new IndexOutOfBoundsException();

			animatedTiles[index] = staticTileIndex;
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the contents of a cell. Gets the index of the static or animated 
	 * tile currently displayed in a cell. The returned index will be 0 if 
	 * the cell is empty. 
	 * @param col the column of cell to check
	 * @param row the row of cell to check 
	 * @return the index of tile in cell 
	 */
	public int getCell(int col, int row) {
		return this.tiles[row][col];
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Sets the contents of a cell. The contents may be set to a static tile index,
	 * an animated tile index, or it may be left empty (index 0)
	 * @param col the column of cell to set
	 * @param row the row of cell to set  
	 * @param  index the index of tile to place in cell 
	 */
	public void setCell(int col, int row, int index) {
		synchronized (this) {
			if (-index - 1 >= numAnimatedTiles || index > numStaticTiles)
				throw new IndexOutOfBoundsException();
			tiles[row][col] = index;
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Change the static tile set. 
	 * Replaces the current static tile set with a new static tile set. See the
	 * constructor TiledLayer(int, int, Image, int, int) for information on how 
	 * the tiles are created from the image.If the new static tile set has as
	 * many or more tiles than the previous static tile set, the the animated
	 * tiles and cell contents will be preserve. If not, the contents of the 
	 * grid will be cleared (all cells will contain index 0) and all animated
	 * tiles will be deleted. 
	 * @param image the Image to use for creating the static tile set
	 * @param tileWidth the width in pixels of a single tile
	 * @param tileHeight the height in pixels of a single tile 
	 */
	public void setStaticTileSet(Bitmap image, int tileWidth, int tileHeight) {
		synchronized (this) {
			if (img == null)
				throw new NullPointerException();
			if (tileHeight <= 0 || tileWidth <= 0)
				throw new IllegalArgumentException();
			if (img.getWidth() % tileWidth != 0
					|| img.getHeight() % tileHeight != 0)
				throw new IllegalArgumentException();

			int newNumStaticTiles = (img.getWidth() / getCellWidth())
					* (img.getHeight() / getCellHeight());

			// recalculate size
			int w = cols * tileWidth;
			int h = rows * tileHeight;

			setSize(w, h);
			this.tileWidth = tileWidth;
			this.tileHeight = tileHeight;

			if (newNumStaticTiles >= numStaticTiles) {
				this.numStaticTiles = newNumStaticTiles;
				return;
			}
			// if there are less static tiles
			// all animated tiles are discarded and
			// the tiledLayer is filled with tiles with index 0

			this.numStaticTiles = newNumStaticTiles;
			this.animatedTiles = new int[5];
			this.numAnimatedTiles = 0;
			this.fillCells(0, 0, getColumns(), getRows(), 0);
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Fills a region cells with the specific tile. The cells may be filled with
	 * a static tile index, an animated tile index, or they may be left empty 
	 * (index 0). 
	 * @param col  the column of top-left cell in the region
	 * @param row the row of top-left cell in the region
	 * @param numCols the number of columns in the region
	 * @param numRows the number of rows in the region
	 * @param index the Index of the tile to place in all cells in the specified 
	 * region 
	 */
	public void fillCells(int col, int row, int numCols, int numRows, int index) {
		synchronized (this) {
			if (numCols < 0 || numRows < 0)
				throw new IllegalArgumentException();
			if (row < 0 || col < 0 || col + numCols > this.cols
					|| row + numRows > this.rows)
				throw new IndexOutOfBoundsException();
			if (-index - 1 >= numAnimatedTiles || index > numStaticTiles)
				throw new IndexOutOfBoundsException();

			int rMax = row + numRows;
			int cMax = col + numCols;
			for (int r = row; r < rMax; r++) {
				for (int c = col; c < cMax; c++) {
					tiles[r][c] = index;
				}
			}
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the number of columns in the TiledLayer grid.
	 * @return the width in columns of the TiledLayer grid
	 */
	public final int getColumns() {
		return cols;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the number of rows in the TiledLayer grid.
	 * @return the width in rows of the TiledLayer grid
	 */
	public final int getRows() {
		return rows;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the width of a single cell, in pixels.
	 * @return the width in pixels of a single cell in the TiledLayer grid
	 */
	public final int getCellWidth() {
		return tileWidth;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the height of a single cell, in pixels.
	 * @return the height in pixels of a single cell in the TiledLayer grid
	 */
	public final int getCellHeight() {
		return tileHeight;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Draws the TiledLayer. The entire TiledLayer is rendered subject to the 
	 * clip region of the Graphics object. The TiledLayer's upper left corner
	 * is rendered at the TiledLayer's current position relative to the origin 
	 * of the Graphics object. The current position of the TiledLayer's 
	 * upper-left corner can be retrieved by calling Layer.getX() 
	 * and Layer.getY(). The appropriate use of a clip region and/or 
	 * translation allows an arbitrary region of the TiledLayer to be rendered.
	 * @param g the graphics object to draw the TiledLayer 
	 */
	public final void paint(Canvas g) {
		synchronized (this) {
			if (!this.isVisible())
				return;

			int x = getX();
			int y = getY();

			int c0 = 0;
			int r0 = 0;
			int cMax = getColumns();
			int rMax = getRows();

			int tW = getCellWidth();
			int tH = getCellHeight();

			int x0 = x;
			int imgCols = img.getWidth() / tW;
			for (int r = r0; r < rMax; r++, y += tH) {
				x = x0;
				for (int c = c0; c < cMax; c++, x += tW) {
					int tile = getCell(c, r);
					if (tile < 0)
						tile = getAnimatedTile(tile);
					if (tile == 0)
						continue;

					tile--;

					int xSrc = tW * (tile % imgCols);
					int ySrc = (tile / imgCols) * tH;
					Rect srcRect = new Rect(xSrc, ySrc, xSrc + tW, ySrc + tH);
					Rect dstRect = new Rect(x, y, x + tW, y + tH);
					g.drawBitmap(img, srcRect, dstRect, null);
				}
			}
		}
	}
	
	private final int rows, cols;
	// package access for collision detection
	Bitmap img;

	private int tileHeight, tileWidth, numStaticTiles;

	// the matrix for storing the tiles
	private int[][] tiles;

	// the list of anmated tiles
	// NOTE the first animatedTile (index -1) goes
	// into the first position in the array (index 0)
	// so to access the correct tile use animatedTiles[-n-1]
	int[] animatedTiles;
	// the ammount of animated tiles
	int numAnimatedTiles;
}
