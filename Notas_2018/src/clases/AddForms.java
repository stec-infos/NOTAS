package clases;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

/**
 * importare
 *
 * @author Glara
 */
public class AddForms {

    /*  Este metodo administra agrega JInternalFrame al JDesktopPane del form principal (MDI)
     * @param dp , JDesktopPane del form principal 
     * qparam vnt , nombre del componente JInternalFrame a agregar al form principal
     */
    public static void adminInternalFrame(JDesktopPane dp, JInternalFrame vnt) {

        JInternalFrame[] cm = dp.getAllFrames();
        boolean estado = false;

        //Verifica si ya esta abierto o minimizado  estado=true
        for (JInternalFrame cm1 : cm) {

            if (cm1.getName().equals(vnt.getName())) {
                estado = true;
                //JOptionPane.showMessageDialog(null, "EL FORMULARIO YA ESTA ABIERTO \n");
                cm1.toFront();

                if (cm1 != null && !cm1.isShowing()) {
                    if (cm1.isIcon() == true) {
                        try {
                            cm1.setIcon(false);
                            cm1.toFront();
                        } catch (PropertyVetoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            //Logger.getLogger(AddForms.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

            }
        }
        //Verifica Si no esta abiero o minimizado estado=false
        if (estado != true) {
            if (vnt != null && !vnt.isShowing()) {
                //Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension pantalla = dp.getSize();
                //obtenemos el tamaño de la ventana
                Dimension ventana = vnt.getSize();
                //para centrar la ventana lo hacemos con el siguiente calculo

                vnt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 5 /*10*/);

                vnt.show();
                dp.remove(vnt);
                try {
                    dp.add(vnt, JLayeredPane.DEFAULT_LAYER);
                    vnt.setClosable(true);// icono de cerrar
                    //vnt.setMaximizable(true);// icono de cerrar
                    try {
                        vnt.setMaximum(true);
                        //vnt.setMaximizable(true);
                        vnt.setIconifiable(true);
                        
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(AddForms.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    vnt.toFront();
                } catch (IllegalArgumentException ex) {
                    dp.add(vnt, JLayeredPane.DEFAULT_LAYER);
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }
}
