/*
 * PlayerTableModel.java created on 3 Dec 2008 07:13:35 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.tableviewer;

/**
 * This class acts as the model provider for the data that we will use.
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2008
 */
public final class PlayerTableModel {

    private PlayerTableModel() {}

    public static final TeamBean createTeam() {
        TeamBean ret = new TeamBean( "SuggsSpecials", 2009 );

        ret.addPlayer( new PlayerBean( "Peter", "Suggitt", 2.1, 3.3, 5.4 ) );
        ret.addPlayer( new PlayerBean( "Will", "Loden", 2.6, 3.9, 5.5 ) );
        ret.addPlayer( new PlayerBean( "Ben", "Clay", 1.1, 6.9, 9.5 ) );
        ret.addPlayer( new PlayerBean( "Andrew", "Evans", 4.1, 6.2, 5.5 ) );
        ret.addPlayer( new PlayerBean( "Ben", "Prag", 3.8, 7.2, 1.1 ) );
        ret.addPlayer( new PlayerBean( "Foo", "Bar", 0.0, 0.0, 0.0 ) );

        return ret;
    }

}
