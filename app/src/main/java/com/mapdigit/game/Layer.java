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
import android.graphics.Canvas;

//[------------------------------ MAIN CLASS ----------------------------------]
////////////////////////////////////////////////////////////////////////////////
//--------------------------------- REVISIONS ----------------------------------
//Date       Name                 Tracking #         Description
//--------   -------------------  -------------      --------------------------
//12AUG2010  James Shen                 	         Code review
////////////////////////////////////////////////////////////////////////////////
/**
 * A Layer is an abstract class representing a visual element of a game. Each
 * Layer has position (in terms of the upper-left corner of its visual bounds),
 * width, height, and can be made visible or invisible. Layer subclasses must
 * implement a paint(Canvas) method so that they can be rendered.
 * <hr>
 * <b>&copy; Copyright 2010 Guidebee Pty Ltd. All Rights Reserved.</b>
 * 
 * @version 1.00, 12/08/10
 * @author Guidebee Pty Ltd.
 */
public abstract class Layer {

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Constructor. 
	 * @param x  the top left x postion
	 * @param y  the top left y postion
	 * @param width the width of the layer
	 * @param height the height of the layer
	 * @param visible visialbe or not
	 */
	protected Layer(int x, int y, int width, int height, boolean visible) {
		setSize(width, height);
		setPosition(x, y);
		setVisible(visible);
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * set the layer size.
	 * @param width  the width of the layer
	 * @param height the height of the layer
	 */
	protected void setSize(int width, int height) {
		if (width < 1 || height < 1)
			throw new IllegalArgumentException();

		this.width = width;
		this.height = height;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the current width of this layer, in pixels.
	 * @return the width of the layer.
	 */
	public final int getWidth() {
		return width;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the current height of this layer, in pixels.
	 * @return the height of the layer
	 */
	public final int getHeight() {
		return height;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the horizontal position of this Layer's upper-left corner in the 
	 * painter's coordinate system.
	 */
	public final int getX() {
		return x;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the vertical  position of this Layer's upper-left corner in the 
	 * painter's coordinate system.
	 */
	public final int getY() {
		return y;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the visibility of this Layer.
	 */
	public final boolean isVisible() {
		return visible;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Moves this Layer by the specified horizontal and vertical distances.
	 * @param dx the distance to move along horizontal axis
	 * @param dy the distance to move along vertical axis 
	 */
	public void move(int dx, int dy) {
		synchronized (this) {
			x += dx;
			y += dy;
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Paints this Layer if it is visible.
	 * @param g the graphics object for rendering the Layer 
	 */
	public abstract void paint(Canvas g);

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Sets this Layer's position such that its upper-left corner is located at 
	 * (x,y) in the painter's coordinate system
	 * @param x  the horizontal position
	 * @param y the vertical position
	 */
	public void setPosition(int x, int y) {
		synchronized (this) {
			this.x = x;
			this.y = y;
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Sets the visibility of this Layer.
	 * @param visible true to make the Layer visible, false to make it invisible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	private int width;
	private int height;
	private int x;
	private int y;
	private boolean visible;

}
