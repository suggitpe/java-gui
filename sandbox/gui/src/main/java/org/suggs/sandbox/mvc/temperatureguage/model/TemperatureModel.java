/*
 * TemperatureModel.java created on 9 Jul 2007 07:42:13 by suggitpe for project SandBox - GUI
 * 
 */
package org.suggs.sandbox.mvc.temperatureguage.model;

import java.util.Observable;

/**
 * This is the observable model for the temperature state management.
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2007
 */
public class TemperatureModel extends Observable {

    private double tempInFarenheight = 32.0;

    /**
     * Getter for the temperature in degrees F
     * 
     * @return the temp in F
     */
    public double getF() {
        return tempInFarenheight;
    }

    /**
     * Getter for the temperature in degrees C
     * 
     * @return the temp in C
     */
    public double getC() {
        return ( tempInFarenheight - 32.0 ) * 5.0 / 9.0;
    }

    /**
     * Setter for the temperature in degrees F
     * 
     * @param aTempF
     *            the temp in F to set
     */
    public void setF( double aTempF ) {
        tempInFarenheight = aTempF;
        setChanged();
        notifyObservers();
    }

    /**
     * setter for the temperature in degreed C
     * 
     * @param tempC
     *            the temp to set in degrees C
     */
    public void setC( double tempC ) {
        tempInFarenheight = tempC * 9.0 / 5.0 + 32.0;
        setChanged();
        notifyObservers();
    }
}
