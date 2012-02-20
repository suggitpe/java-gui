/*
 * MercuryMain.java created on 13 Sep 2008 08:52:01 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury;

import org.suggs.apps.mercury.model.util.file.IFileManager;
import org.suggs.apps.mercury.model.util.file.impl.FileManager;
import org.suggs.apps.mercury.model.util.image.ImageManager;
import org.suggs.apps.mercury.view.IMenuFactory;
import org.suggs.apps.mercury.view.IToolBarFactory;
import org.suggs.apps.mercury.view.panels.MainWindow;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * This is the main screen for the Mercury application. This is here you can coordinate all of the main
 * application activities.
 * 
 * @author suggitpe
 * @version 1.0 13 Sep 2008
 */
public class MercuryMain extends ApplicationWindow {

    private static final Logger LOG = LoggerFactory.getLogger( MercuryMain.class );

    /**
     * Constructs a new instance.
     */
    public MercuryMain() {
        // let the parent set up the shell
        super( null );
    }

    /**
     * Run the gui setup
     */
    public void run() {
        addMenuBar();
        addToolBar( SWT.FLAT | SWT.WRAP );
        setBlockOnOpen( true );
        open();
        Display.getCurrent().dispose();

    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent ) {
        getShell().setText( "Mercury messenger" );
        getShell().setImage( new Image( getShell().getDisplay(),
                                        ImageManager.getImageStream( ImageManager.IMAGE_MERCURY ) ) );

        @SuppressWarnings("unused")
        MainWindow mainWindow = new MainWindow( parent );

        // remove this
        return parent;
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    @Override
    protected MenuManager createMenuManager() {
        IMenuFactory fact = (IMenuFactory) ContextProvider.instance().getBean( "menuFactory" );
        return fact.createMenuManager( "MAIN" );
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createToolBarManager(int)
     */
    @Override
    protected ToolBarManager createToolBarManager( int style ) {
        IToolBarFactory fact = (IToolBarFactory) ContextProvider.instance().getBean( "toolBarFactory" );
        return fact.createToolbar( "MAIN", style );
    }

    /**
     * This method is used to initialise the users file system correctly. If there are no default files set up
     * then this method will initialise them.
     */
    protected static void setupFileSystem() {
        LOG.debug( "Checking that the file system is correctly set up" );

        String dir = System.getProperty( "user.home" ) + "/.mercury";
        File c = new File( dir + "/connectionStore.xml" );
        File ctx = new File( dir + "/context.properties" );

        IFileManager fm = new FileManager();

        if ( !c.exists() ) {
            LOG.info( "Creating default connectionStore xml file" );
            try {
                String storeXml = fm.retrieveClobFromResource( "default_config/connection_store_default.xml" );
                fm.persistClobToFile( storeXml, c );
            }
            catch ( IOException ioe ) {
                throw new IllegalStateException( "Failed to create default connection store file for Mercury",
                                                 ioe );
            }
        }

        // this creates the basic
        if ( !ctx.exists() ) {
            LOG.info( "Creating default context.properties file" );
            try {
                String ctxProps = fm.retrieveClobFromResource( "default_config/context_default.properties" );
                fm.persistClobToFile( ctxProps, ctx );
            }
            catch ( IOException ioe ) {
                throw new IllegalStateException( "Failed to create default context.properties for Mercury",
                                                 ioe );
            }
        }
    }

    /**
     * This is the main method. It will simply creates an instance of this class and opens it up for use.
     * 
     * @param args
     *            the arguments passed in from the command line
     */
    public static void main( String[] args ) {
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "*************************************" );
            LOG.debug( "Classpath: " + System.getProperty( "java.class.path" ) );
            LOG.debug( "Java version: " + System.getProperty( "java.version" ) );
            LOG.debug( "*************************************" );
        }

        // this mut be done first so it can set up the file system
        MercuryMain.setupFileSystem();

        // this then kicks everything off
        new MercuryMain().run();

    }
}
