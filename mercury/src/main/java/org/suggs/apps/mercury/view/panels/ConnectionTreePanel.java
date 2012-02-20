/*
 * ConnectionTreePanel.java created on 24 Nov 2008 09:14:02 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.panels;

import org.suggs.apps.mercury.ContextProvider;
import org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManager;
import org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManagerListener;
import org.suggs.apps.mercury.model.connection.connectionmanager.impl.ConnectionManager;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.model.util.MercuryUtilityException;
import org.suggs.apps.mercury.model.util.xml.IXsltTransformerUtil;
import org.suggs.apps.mercury.view.actions.ActionManager;
import org.suggs.apps.mercury.view.actions.connection.EditConnectionAction;
import org.suggs.apps.mercury.view.actions.connection.RemoveConnectionAction;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * In this panel we look at the connections from the connection store as a tree that we can traverse
 * 
 * @author suggitpe
 * @version 1.0 24 Nov 2008
 */
public class ConnectionTreePanel extends Composite implements IConnectionManagerListener {

    private static final String XSLT = "xslt/connection-tree.xsl";

    private TreeViewer viewer;
    private IXsltTransformerUtil xsltUtil;

    // initialiser
    {
        xsltUtil = (IXsltTransformerUtil) ContextProvider.instance().getBean( "xsltUtil" );
        ConnectionManager.instance().addConnectionManagerListener( this );
    }

    /**
     * Constructs a new instance.
     * 
     * @param parent
     */
    public ConnectionTreePanel( Composite parent ) {
        super( parent, SWT.BORDER );
        populateCtrls();
    }

    /**
     * Populates the tree and its contents.
     */
    private void populateCtrls() {
        setLayout( new FillLayout() );

        viewer = new TreeViewer( this, SWT.BORDER );
        viewer.getTree().setToolTipText( "Select a connection" );
        viewer.setContentProvider( new ConnectionTreeContentProvider() );
        viewer.setLabelProvider( new ConnectionTreeLabelProvider() );

        // now lets populate the table itself
        viewer.setInput( createConnectionData() );

        viewer.getTree().setMenu( buildTreeMenu() );
        viewer.expandAll();
    }

    /**
     * Here we manage the creation of the input data for the tree viewer.
     * 
     * @return the node that will be used for the tree viewer data.
     */
    private Node createConnectionData() {
        try {
            IConnectionManager connMgr = ConnectionManager.instance();
            String b = connMgr.getConnectionDump();
            return xsltUtil.transformXmlToDom( b.getBytes(), XSLT );
        }
        catch ( ConnectionStoreException cse ) {
            throw new IllegalStateException( "Failed to retrieve connection store details from the internal connection store",
                                             cse );
        }
        catch ( MercuryUtilityException mue ) {
            throw new IllegalStateException( "Failed to parse xml document for connections", mue );
        }
    }

    /**
     * Build the menu that the tree will be associated with the underlying tree.
     * 
     * @return a new popup menu to associate with the tree.
     */
    private Menu buildTreeMenu() {

        final ActionManager mgr = (ActionManager) ContextProvider.instance()
            .getBean( ActionManager.BEAN_NAME );

        final IConnectionStore str = (IConnectionStore) ContextProvider.instance()
            .getBean( "connectionStoreManager" );

        MenuManager popupMenu = new MenuManager();
        popupMenu.addMenuListener( new IMenuListener() {

            @Override
            public void menuAboutToShow( IMenuManager aMenuMgr ) {
                aMenuMgr.removeAll();
                aMenuMgr.add( mgr.getAction( "CREATE_CONNECTION" ) );
                String nm = viewer.getTree().getSelection()[0].getText();
                IConnectionManager connMgr = ConnectionManager.instance();
                if ( viewer.getTree().getSelectionCount() == 1 && connMgr.containsConnection( nm ) ) {
                    aMenuMgr.add( new EditConnectionAction( str, nm ) );
                    aMenuMgr.add( new RemoveConnectionAction( str, nm ) );
                }
            }
        } );

        return popupMenu.createContextMenu( viewer.getTree() );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManagerListener#handleConnectionManagerChange(java.lang.String,
     *      org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManagerListener.ConnectionManagerEvent)
     */
    @Override
    public void handleConnectionManagerChange( String aConnectionName, ConnectionManagerEvent aEvent ) {
        switch ( aEvent ) {
            case CREATE:
            case REMOVE:
                viewer.setInput( createConnectionData() );
                break;
            case EDIT:
                // no change to the tree
                break;
            default:
                throw new IllegalStateException( "Unknown ConnectionStoreEvent received ["
                                                 + aEvent.toString() + "]" );
        }
        viewer.expandAll();
    }

    /**
     * Recursive algorithm to find the required text in the tree
     * 
     * @param aStart
     *            the starting treeitem
     * @param aText
     *            the text to look for
     * @return the tree item that we are looking for
     */
    TreeItem findTreeItem( TreeItem aStart, String aText ) {
        if ( aStart.getText().equals( aText ) ) {
            return aStart;
        }

        for ( TreeItem t : aStart.getItems() ) {
            TreeItem tr = findTreeItem( t, aText );
            if ( tr != null ) {
                return tr;
            }
        }
        return null;
    }

    /**
     * This class is used to actually label the data that you are using within the tree. This is extending the
     * default impl as the functionality we need to add is small
     * 
     * @author suggitpe
     * @version 1.0 26 Nov 2008
     */
    static class ConnectionTreeLabelProvider extends LabelProvider {

        /**
         * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
         */
        @Override
        public String getText( Object node ) {
            Node n = (Node) node;
            if ( n.getAttributes().getNamedItem( "name" ) != null ) {
                return n.getAttributes().getNamedItem( "name" ).getTextContent();
            }
            return null;
        }

    }

    /**
     * Content provider that allows the viewer to process the data that is set on it in a manner that suits
     * the data.
     * 
     * @author suggitpe
     * @version 1.0 26 Nov 2008
     */
    static class ConnectionTreeContentProvider implements ITreeContentProvider {

        /**
         * This is called every time it needs the child elements of an object.
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        @Override
        public Object[] getChildren( Object parent ) {
            return toObjectArray( ( (Node) parent ).getChildNodes() );
        }

        /**
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        @Override
        public Object getParent( Object node ) {
            return ( (Node) node ).getParentNode();
        }

        /**
         * This is called by the tree viewer to enable it to understand whether a node in the tree ie
         * expandable.
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        @Override
        public boolean hasChildren( Object node ) {
            return ( ( (Node) node ).getChildNodes().getLength() > 0 );
        }

        /**
         * This is called by the tree viewer to get the initial set of elements for the input object. In the
         * instance of this tree we want to ignore the top level element as this is no good and instead we
         * want to get the layer beneath (ie the impl layer).
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        @Override
        public Object[] getElements( Object node ) {
            return toObjectArray( ( (Node) node ).getChildNodes().item( 0 ).getChildNodes() );
        }

        /**
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        @Override
        public void dispose() {}

        /**
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        @Override
        public void inputChanged( Viewer aViewer, Object oldInput, Object newInput ) {}

        /**
         * Converts a nodelist into an object array
         * 
         * @param list
         *            the nodelist from which to get the nodes
         * @return an objecyt array of the nodes
         */
        private Object[] toObjectArray( NodeList list ) {
            int len = list.getLength();
            Object[] ret = new Object[len];
            for ( int i = 0; i < len; ++i ) {
                ret[i] = list.item( i );
            }
            return ret;
        }

    }

}
