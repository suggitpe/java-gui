/*
 * PlayerBean.java created on 2 Dec 2008 19:50:05 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.tableviewer;

/**
 * This is the player bean class to represent the data that will be populated into the table.
 * 
 * @author suggitpe
 * @version 1.0 2 Dec 2008
 */
public class PlayerBean {

    private String firstname;
    private String lastname;
    private double pointsPerGame;
    private double reboundsPerGame;
    private double assistsPerGame;

    /**
     * Constructs a new instance.
     * 
     * @param aFirstname
     * @param aLastname
     * @param points
     * @param rebounds
     * @param assists
     */
    public PlayerBean( String aFirstname, String aLastname, double points, double rebounds, double assists ) {
        firstname = aFirstname;
        lastname = aLastname;
        pointsPerGame = points;
        reboundsPerGame = rebounds;
        assistsPerGame = assists;
    }

    /**
     * Returns the value of firstname.
     * 
     * @return Returns the firstname.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the firstname field to the specified value.
     * 
     * @param firstname
     *            The firstname to set.
     */
    public void setFirstname( String firstname ) {
        this.firstname = firstname;
    }

    /**
     * Returns the value of lastname.
     * 
     * @return Returns the lastname.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the lastname field to the specified value.
     * 
     * @param lastname
     *            The lastname to set.
     */
    public void setLastname( String lastname ) {
        this.lastname = lastname;
    }

    /**
     * Returns the value of pointsPerGame.
     * 
     * @return Returns the pointsPerGame.
     */
    public double getPointsPerGame() {
        return pointsPerGame;
    }

    /**
     * Sets the pointsPerGame field to the specified value.
     * 
     * @param pointsPerGame
     *            The pointsPerGame to set.
     */
    public void setPointsPerGame( double pointsPerGame ) {
        this.pointsPerGame = pointsPerGame;
    }

    /**
     * Returns the value of reboundsPerGame.
     * 
     * @return Returns the reboundsPerGame.
     */
    public double getReboundsPerGame() {
        return reboundsPerGame;
    }

    /**
     * Sets the reboundsPerGame field to the specified value.
     * 
     * @param reboundsPerGame
     *            The reboundsPerGame to set.
     */
    public void setReboundsPerGame( double reboundsPerGame ) {
        this.reboundsPerGame = reboundsPerGame;
    }

    /**
     * Returns the value of assistsPerGame.
     * 
     * @return Returns the assistsPerGame.
     */
    public double getAssistsPerGame() {
        return assistsPerGame;
    }

    /**
     * Sets the assistsPerGame field to the specified value.
     * 
     * @param assistsPerGame
     *            The assistsPerGame_ to set.
     */
    public void setAssistsPerGame( double assistsPerGame ) {
        this.assistsPerGame = assistsPerGame;
    }

}
