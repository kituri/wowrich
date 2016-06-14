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
import java.util.Vector;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region.Op;

//[------------------------------ MAIN CLASS ----------------------------------]
////////////////////////////////////////////////////////////////////////////////
//--------------------------------- REVISIONS ----------------------------------
//Date       Name                 Tracking #         Description
//--------   -------------------  -------------      --------------------------
//12AUG2010  James Shen                 	         Code review
////////////////////////////////////////////////////////////////////////////////
/**
* The LayerManager manages a series of Layers. The LayerManager simplifies the
*  process of rendering the Layers that have been added to it by automatically 
*  rendering the correct regions of each Layer in the appropriate order. The 
*  LayerManager maintains an ordered list to which Layers can be appended,
*  inserted and removed. A Layer's index correlates to its z-order; the layer 
*  at index 0 is closest to the user while a the Layer with the highest index 
*  is furthest away from the user. The indices are always contiguous; that is, 
*  if a Layer is removed, the indices of subsequent Layers will be adjusted to 
*  maintain continuity. The LayerManager class provides several features that
*  control how the game's Layers are rendered on the screen. The view window 
*  controls the size of the visible region and its position relative to 
*  the LayerManager's coordinate system. Changing the position of the view
*  window enables effects such as scrolling or panning the user's view. 
*  For example, to scroll to the right, simply move the view window's 
*  location to the right. The size of the view window controls how large 
*  the user's view will be, and is usually fixed at a size that is appropriate 
*  for the device's screen. 
* <hr>
* <b>&copy; Copyright 2010 Guidebee Pty Ltd. All Rights Reserved.</b>
* 
* @version 1.00, 12/08/10
* @author Guidebee Pty Ltd.
*/
public class LayerManager {

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Creates a new LayerManager.
	 */
	public LayerManager() {
		layers = new Vector<Layer>();
		viewX = viewY = 0;
		viewW = viewH = Integer.MAX_VALUE;
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Appends a Layer to this LayerManager.The Layer is appended to the list of 
	 * existing Layers such that it has the highest index (i.e. it is furthest 
	 * away from the user). The Layer is first removed from this LayerManager 
	 * if it has already been added.
	 * @param layer the Layer to be added 
	 */
	public void append(Layer layer) {
		synchronized (this) {
			if (layer == null)
				throw new NullPointerException();
			layers.add(layer);
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the Layer with the specified index. 
	 * @param i the Layer to be inserted
	 * @return the index at which the new Layer is to be inserted 
	 */
	public Layer getLayerAt(int i) {
		// needs not be synchronized
		return (Layer) layers.get(i);
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the number of Layers in this LayerManager.
	 * @return the number of Layers
	 */
	public int getSize() {
		// needs not be synchronized
		return layers.size();
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Inserts a new Layer in this LayerManager at the specified index. The Layer
	 * is first removed from this LayerManager if it has already been added. 
	 * @param layer the Layer to be inserted
	 * @param i the index at which the new Layer is to be inserted 
	 */
	public void insert(Layer layer, int i) {
		synchronized (this) {
			if (layer == null)
				throw new NullPointerException();
			layers.insertElementAt(layer, i);
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Removes the specified Layer from this LayerManager. This method does 
	 * nothing if the specified Layer is not added to the this LayerManager
	 * @param layer the Layer to be removed 
	 */
	public void remove(Layer layer) {
		synchronized (this) {
			if (layer == null)
				throw new NullPointerException();
			layers.remove(layer);
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Sets the view window on the LayerManager. The view window specifies the 
	 * region that the LayerManager draws when its paint(Canvas, int, int) method
	 * is called. It allows the developer to control the size of the visible 
	 * region, as well as the location of the view window relative to the 
	 * LayerManager's coordinate system. 
	 * @param x the horizontal location of the view window relative to the 
	 *        LayerManager's origin
	 * @param y the vertical location of the view window relative to the 
	 *        LayerManager's origin
	 * @param width the width of the view window
	 * @param height the height of the view window 
	 */
	public void setViewWindow(int x, int y, int width, int height) {
		synchronized (this) {
			if (width < 0 || height < 0)
				throw new IllegalArgumentException();
			viewX = x;
			viewY = y;
			viewW = width;
			viewH = height;
		}
	}

    ///////////////////////////////////////////////////////////////////////////
    //--------------------------------- REVISIONS ------------------------------
    // Date       Name                 Tracking #         Description
    // ---------  -------------------  -------------      ----------------------
    // 12AUG2010  James Shen                 	          Code review
    ////////////////////////////////////////////////////////////////////////////
	/**
	 * Renders the LayerManager's current view window at the specified location. 
	 * @param g the graphics instance with which to draw the LayerManager
	 * @param x the horizontal location at which to render the view window, 
	 *       relative to the Graphics' translated origin
	 * @param y  the vertical location at which to render the view window, 
	 *       relative to the Graphics' translated origin 
	 */
	public void paint(Canvas g, int x, int y) {
		synchronized (this) {
			if (g == null)
				throw new NullPointerException();

			Rect clipRect = g.getClipBounds();
			int clipX = clipRect.left;
			int clipY = clipRect.top;
			int clipW = clipRect.width();
			int clipH = clipRect.height();
			g.translate(x - viewX, y - viewY);
			g.clipRect(viewX, viewY, viewW, viewH);
			for (int i = getSize(); --i >= 0;) {
				Layer comp = getLayerAt(i);
				if (comp.isVisible()) {
					comp.paint(g);
				}
			}
			g.translate(-x + viewX, -y + viewY);
			g.clipRect(0, 0, g.getWidth(), g.getHeight(), Op.UNION);
			g.clipRect(clipX, clipY, clipW, clipH);
		}
	}

	private Vector<Layer> layers;
	private int viewX, viewY, viewW, viewH;
}
