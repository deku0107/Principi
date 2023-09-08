package ViewProveETest;

import Buisness.ArticoloBuisness;
import Buisness.CategoriaBuisness;
import Buisness.SessionManager;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;
import java.util.List;

public class CatalogoPanel extends JPanel {

    private final JPanel mainList;
    private JTree productTree;
    private List articoli;

    public CatalogoPanel(){

        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        //List articoli;
        if(SessionManager.getSession().get(SessionManager.LOGGED_USER)!=null){
            articoli= ArticoloBuisness.getInstance().getCatalogo();
            popola(articoli);
        }else if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER)!=null){
            articoli=ArticoloBuisness.getInstance().getCatalogoManager();
            popola(articoli);

        }else
        {
            articoli = ArticoloBuisness.getInstance().getProdotti();
            popola(articoli);
        }


        initComponents();

    }

    public CatalogoPanel(List<Object> aricoli){
        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());
        popola(aricoli);
        initComponents();

    }


    private void popola(List<Object> articoli){
        Home.getInstance().getCentro().removeAll();
        Home.getInstance().getCentro().setLayout(new GridLayout(1,1));
        mainList.removeAll();
        int i=1;
        for (Object articolo:articoli){
            GridBagLayoutPanel gridBagLayoutPanel=new GridBagLayoutPanel();

            gridBagLayoutPanel.updateData(articolo);

            JPanel panel = new JPanel();

            panel.add(gridBagLayoutPanel);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = i++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbc, 0);
        }

        JScrollPane scrollPane= new JScrollPane(mainList);

        Home.getInstance().getCentro().add(scrollPane, BorderLayout.CENTER);

        Home.getInstance().getCentro().repaint();
        Home.getInstance().getCentro().validate();

    }


    private void initComponents() {


        // Creazione del nodo radice dell'albero
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Catalogo");

        // Aggiunta delle categorie e sottocategorie come nodi figli
        List<Model.Categoria> categoria= CategoriaBuisness.getInstance().getCategoriaChild(1);

        nodePopola(categoria, root);


        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        productTree = new JTree(treeModel);
        productTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                String s=productTree.getLastSelectedPathComponent().toString().trim();

                if(SessionManager.getSession().get(SessionManager.LOGGED_USER)!=null){
                    articoli= ArticoloBuisness.getInstance().getCatalogoByCategoria(s);
                    popola(articoli);
                }else if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER)!=null){
                    articoli=ArticoloBuisness.getInstance().getCatalogoManager();
                    popola(articoli);

                }else if(s.equalsIgnoreCase("Catalogo")){

                    if(SessionManager.getSession().get(SessionManager.LOGGED_USER)!=null){
                        System.out.println("Ciao");
                        articoli= ArticoloBuisness.getInstance().getCatalogo();
                        popola(articoli);

                    }else if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER)!=null){
                        articoli=ArticoloBuisness.getInstance().getCatalogoManager();
                        popola(articoli);

                    }else
                    {
                        articoli = ArticoloBuisness.getInstance().getProdotti();
                        popola(articoli);
                    }


                }else
                {
                    articoli = ArticoloBuisness.getInstance().getProdotti(s);
                    popola(articoli);
                    System.out.println("Nome categoria selezionata "+  s);
                }


            }
        });
        JPanel panel= new JPanel();
        panel.add(productTree);

        JScrollPane scrollPane= new JScrollPane(productTree);
        Home.getInstance().getWest().add(scrollPane, BorderLayout.WEST);
        Home.getInstance().getWest().setSize(Home.getInstance().getWest().getWidth(), (int) Home.getInstance().getSize().getHeight());


    }

    private void nodePopola(List<Model.Categoria> categoria, DefaultMutableTreeNode root) {

        if(categoria!=null){


            for (Model.Categoria o: categoria){
                if(CategoriaBuisness.getInstance().getCategoriaIntId(o)!=1) {

                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(CategoriaBuisness.getInstance().getNome(o));

                    root.add(node);
                    nodePopola(CategoriaBuisness.getInstance().getCategoriaChild(CategoriaBuisness.getInstance().getCategoriaIntId(o)), node);
                }
            }
        }



    }
}
