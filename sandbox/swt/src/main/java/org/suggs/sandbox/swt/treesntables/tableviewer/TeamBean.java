/*
 * TeamBean.java created on 3 Dec 2008 06:56:36 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.tableviewer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will be a container class for team players
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2008
 */
public class TeamBean {

    private List<PlayerBean> players = new ArrayList<PlayerBean>();
    private String name;
    private int year;

    /**
     * Constructs a new instance.
     * 
     * @param aName
     * @param aYear
     */
    public TeamBean( String aName, int aYear ) {
        name = aName;
        year = aYear;
    }

    /**
     * Returns the value of players.
     * 
     * @return Returns the players.
     */
    public List<PlayerBean> getPlayers() {
        return players;
    }

    /**
     * Sets the players field to the specified value.
     * 
     * @param players
     *            The players_ to set.
     */
    public void setPlayers( List<PlayerBean> players ) {
        this.players = players;
    }

    /**
     * Adds a new player to the team
     * 
     * @param player
     */
    public void addPlayer( PlayerBean player ) {
        players.add( player );
    }

    /**
     * Returns the value of name.
     * 
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name field to the specified value.
     * 
     * @param name
     *            The name to set.
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Returns the value of year.
     * 
     * @return Returns the year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year field to the specified value.
     * 
     * @param year
     *            The year to set.
     */
    public void setYear( int year ) {
        this.year = year;
    }

}
