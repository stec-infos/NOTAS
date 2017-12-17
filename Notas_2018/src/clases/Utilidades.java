package clases;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import elaprendiz.gui.comboBox.ComboBoxRectIcon;
import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;
import org.edisoncor.gui.comboBox.ComboBoxRect;

/**
 *
 * @author GLARA
 */
public class Utilidades {

    public final static int VERIFICAR = 1;
    public final static int LIMPIAR = 2;
    public final static int HABILITAR_DESABILITAR = 3;
    public final static int HABILITAR_TODO = 4;
    public final static int HABILITAR_POR_NOMBRE = 5;
    //static Color BObligatorio = RED; //Color ColorR;  //cambiar por uno mas claro
    static Color FObligatorio = WHITE; //Color ColorR; //cambiar por uno mas claro    
    static Color BObligatorio = (Color.getHSBColor(0, 155, 185));
    static Peticiones peticiones = new Peticiones();

    /**
     * metedo que buscas los botonoes que tiene contiene un contenedor y los
     * habilias o desabihilitas.
     *
     * @param cm
     * @param habilitar
     * @param excepcion
     */
    public static void buscarBotones(Component cm, boolean habilitar, Component[] excepcion) {
        if (cm instanceof JPanel) {
            habilitaBotones(((JPanel) cm).getComponents(), habilitar, excepcion);
        } else {
            habilitaBotones(new Component[]{cm}, habilitar, excepcion);
        }
    }

    private static void habilitaBotones(Component[] cmps, boolean habilitar, Component[] excepcion) {

        for (int i = 0; i < cmps.length; i++) {
            if (cmps[i] instanceof JPanel) {
                habilitaBotones(((JPanel) cmps[i]).getComponents(), habilitar, excepcion);
                continue;
            }
            if (excepcion != null) {
                if (excepcion.length > 0) {
                    boolean iguales = false;
                    for (int j = 0; j < excepcion.length; j++) {
                        if (cmps[i].equals(excepcion[j])) {
                            iguales = true;
                            break;
                        }
                    }

                    if (iguales) {
                        continue;
                    }
                }
            }
            if (cmps[i] instanceof AbstractButton) {
                cmps[i].setEnabled(habilitar);
                if (cmps[i] instanceof JToggleButton) {
                    JToggleButton tmp = (JToggleButton) cmps[i];
                    tmp.setSelected(false);
                }
            }
        }
    }

    /**
     *
     * @param cm contenedor
     * @param opcion marcar componentes encontrados
     * @return
     */
    public static boolean esObligatorio(Component cm, boolean opcion) {
        if (cm instanceof JPanel) {
            return marcarCamposObligatorios(((JPanel) cm).getComponents(), opcion);
        } else if (cm instanceof JScrollPane) {
            return marcarCamposObligatorios(((JScrollPane) cm).getComponents(), opcion);
        } else if (cm instanceof JViewport) {
            return marcarCamposObligatorios(((JViewport) cm).getComponents(), opcion);
        } else if (cm instanceof JTabbedPane) {
            return marcarCamposObligatorios(((JTabbedPane) cm).getComponents(), opcion);
        } else {
            return marcarCamposObligatorios(new Component[]{cm}, opcion);
        }
    }

    private static boolean marcarCamposObligatorios(Component[] cm, boolean opcion) {
        boolean existen = false;
        for (int i = 0; i < cm.length; i++) {

            if (cm[i].getName() != null) {
                if (cm[i] instanceof JDateChooser) {

                    if (((JDateChooser) cm[i]).getDate() == null) {
                        if (opcion) {
                            ((JTextFieldDateEditor) ((JDateChooser) cm[i]).getDateEditor()).setBackground(BObligatorio);
                            ((JTextFieldDateEditor) ((JDateChooser) cm[i]).getDateEditor()).setForeground(FObligatorio);
                        } else {
                            ((JTextFieldDateEditor) ((JDateChooser) cm[i]).getDateEditor()).setBackground(Color.WHITE);
                            ((JTextFieldDateEditor) ((JDateChooser) cm[i]).getDateEditor()).setForeground(Color.BLACK);
                        }
                        existen = true;
                    } else if (((JDateChooser) cm[i]).getDate() != null) {
                        ((JTextFieldDateEditor) ((JDateChooser) cm[i]).getDateEditor()).setBackground(Color.WHITE);
                        ((JTextFieldDateEditor) ((JDateChooser) cm[i]).getDateEditor()).setForeground(Color.BLACK);
                    }
                }
            }

            if (cm[i] instanceof JPanel || cm[i] instanceof JScrollPane || cm[i] instanceof JViewport) {
                esObligatorio(cm[i], opcion);
                continue;
            }
            if (cm[i].getName() != null) { //Solo los que tengan nombres seran obligatorios
                if (cm[i] instanceof JTextComponent) {
                    if (((JTextComponent) cm[i]).getText().isEmpty()) {
                        if (opcion) {
                            cm[i].setBackground(BObligatorio);
                            cm[i].setForeground(FObligatorio);
                        } else {
                            cm[i].setBackground(Color.getHSBColor(0, 155, 185));
                            cm[i].setBackground(Color.white);
                            cm[i].setForeground(Color.BLACK);
                        }
                        existen = true;
                    } else if (!((JTextComponent) cm[i]).getText().isEmpty()) {
                        cm[i].setBackground(Color.white);
                        cm[i].setForeground(Color.BLACK);
                    }
                }
                if (cm[i] instanceof JTextArea) {
                    if (((JTextArea) cm[i]).getText().isEmpty()) {
                        if (opcion) {
                            cm[i].setBackground(BObligatorio);
                            cm[i].setForeground(FObligatorio);
                        } else {
                            cm[i].setBackground(Color.getHSBColor(0, 155, 185));
                            cm[i].setBackground(Color.white);
                            cm[i].setForeground(Color.BLACK);
                        }
                        existen = true;
                    } else if (!((JTextArea) cm[i]).getText().isEmpty()) {
                        cm[i].setBackground(Color.white);
                        cm[i].setForeground(Color.BLACK);
                    }
                } else if (cm[i] instanceof JComboBox) {

                    if (((JComboBox) cm[i]).getSelectedIndex() == -1 || ((JComboBox) cm[i]).getSelectedIndex() == 0) {
                        // System.out.print("--1 " + ((JComboBox) cm[i]).getSelectedIndex() + "\n");

                        if (opcion) {

                            cm[i].setBackground(BObligatorio);
                            cm[i].setForeground(FObligatorio);
                        } else {
                            cm[i].setBackground(Color.white);
                            cm[i].setForeground(Color.BLACK);
                        }
                        existen = true;
                    } else if (((JComboBox) cm[i]).getSelectedIndex() != -1) {
                        cm[i].setBackground(Color.white);
                        cm[i].setForeground(Color.BLACK);

                        if (((JComboBox) cm[i]).getSelectedIndex() == 0) {
                            if (opcion) {
                                cm[i].setBackground(BObligatorio);
                                cm[i].setForeground(FObligatorio);
                            } else {
                                cm[i].setBackground(Color.white);
                                cm[i].setForeground(Color.BLACK);
                            }
                            existen = true;
                        }
                    }
                }//
                else if (cm[i] instanceof ComboBoxRectIcon) {
                    if (((ComboBoxRectIcon) cm[i]).getSelectedIndex() == -1) {
                        if (opcion) {
                            cm[i].setBackground(BObligatorio);
                            cm[i].setForeground(FObligatorio);
                        } else {
                            cm[i].setBackground(Color.white);
                            cm[i].setForeground(Color.BLACK);
                        }
                        existen = true;
                    } else if (((ComboBoxRectIcon) cm[i]).getSelectedIndex() != -1) {
                        cm[i].setBackground(Color.white);
                        cm[i].setForeground(Color.BLACK);

                        if (((ComboBoxRectIcon) cm[i]).getSelectedIndex() == 0) {
                            if (opcion) {
                                cm[i].setBackground(BObligatorio);
                                cm[i].setForeground(FObligatorio);
                            } else {
                                cm[i].setBackground(Color.white);
                                cm[i].setForeground(Color.BLACK);
                            }
                            existen = true;
                        }
                    }
                }//
                else if (cm[i] instanceof ComboBoxRect) {
                    if (((ComboBoxRect) cm[i]).getSelectedIndex() == -1) {
                        if (opcion) {
                            cm[i].setBackground(BObligatorio);
                            cm[i].setForeground(FObligatorio);
                        } else {
                            cm[i].setBackground(Color.white);
                            cm[i].setForeground(Color.BLACK);
                        }
                        existen = true;
                    } else if (((ComboBoxRect) cm[i]).getSelectedIndex() != -1) {
                        cm[i].setBackground(Color.white);
                        cm[i].setForeground(Color.BLACK);

                        if (((ComboBoxRect) cm[i]).getSelectedIndex() == 0) {
                            if (opcion) {
                                cm[i].setBackground(BObligatorio);
                                cm[i].setForeground(FObligatorio);
                            } else {
                                cm[i].setBackground(Color.white);
                                cm[i].setForeground(Color.BLACK);
                            }
                            existen = true;
                        }
                    }
                }//
            }
        }
        return existen;
    }

    /**
     *
     * @param cm componente a poser editable.
     * @param habilitar opcion para habilitar
     * @param excepcion componente que se ignoraran
     * @param limpiar opcion limpiar. true para limpar los campos detexto
     * @param valor valor para establecer cuando se limpie los campso de texto
     */
    public static void setEditableTexto(Component cm, boolean habilitar, Component[] excepcion, boolean limpiar, String valor) {
        if (cm instanceof JTabbedPane) {
            habilitarTexto(((JTabbedPane) cm).getComponents(), habilitar, excepcion, limpiar, valor);
        }
        if (cm instanceof JPanel) {
            habilitarTexto(((JPanel) cm).getComponents(), habilitar, excepcion, limpiar, valor);
        } else if (cm instanceof JScrollPane) {
            habilitarTexto(((JScrollPane) cm).getComponents(), habilitar, excepcion, limpiar, valor);
        } else if (cm instanceof JViewport) {
            habilitarTexto(((JViewport) cm).getComponents(), habilitar, excepcion, limpiar, valor);
        } else if (cm instanceof JTabbedPane) {
            habilitarTexto(((JTabbedPane) cm).getComponents(), habilitar, excepcion, limpiar, valor);
        } else {
            habilitarTexto(new Component[]{cm}, habilitar, excepcion, limpiar, valor);
        }
    }

    private static void habilitarTexto(Component[] cmps, boolean habilitar, Component[] excepcion, boolean limpiar, String valor) {
        for (int i = 0; i < cmps.length; i++) {

            if (cmps[i] instanceof JTabbedPane) {
                setEditableTexto(((JTabbedPane) cmps[i]), habilitar, excepcion, limpiar, valor);
                continue;
            }
//            if (cmps[i] instanceof JCheckBox) {
//                JCheckBox rb = (JCheckBox) cmps[i];
//                //rb.setEnabled(habilitar);
//                if (cmps[i].getName() != null) {
//                    System.out.print("" + cmps[i].getName() + " " + rb.isSelected() + "\n");
//                    //rb.setText("Activo");
//                    //rb.setSelected(true);
//                    //rb.setBackground(new java.awt.Color(102, 204, 0));
//                }
//                continue;
//            }

            if (cmps[i] instanceof JCheckBox) {
                JCheckBox rb = (JCheckBox) cmps[i];
                rb.setEnabled(habilitar);
                if (limpiar) {
                    //rb.setText("Activo");
                    rb.setSelected(false);
                    //rb.setBackground(new java.awt.Color(102, 204, 0));
                }
                continue;
            }

            if (cmps[i] instanceof JDateChooser) {
                JDateChooser tm = (JDateChooser) cmps[i];
                tm.setEnabled(habilitar);
                if (limpiar) {
                    tm.setDate(Calendar.getInstance().getTime());
                }
                continue;
            }
            if (cmps[i] instanceof JRadioButton) {
                JRadioButton rb = (JRadioButton) cmps[i];
                rb.setEnabled(habilitar);
                if (limpiar) {
                    rb.setText("Activo");
                    rb.setSelected(true);
                    rb.setBackground(new java.awt.Color(102, 204, 0));
                }
                continue;
            }
            if (cmps[i] instanceof JComboBox) {
                JComboBox tm = (JComboBox) cmps[i];
                tm.setEnabled(habilitar);
                tm.setEditable(habilitar);
                if (limpiar) {
                    tm.setSelectedIndex(-1);
                }
                continue;
            }
            if (cmps[i] instanceof ComboBoxRect) {
                ComboBoxRect tm = (ComboBoxRect) cmps[i];
                tm.setEnabled(habilitar);
                tm.setEditable(habilitar);
                if (limpiar) {
                    tm.setSelectedIndex(-1);
                }
                continue;
            }
            if (cmps[i] instanceof JSpinner) {
                JSpinner tm = (JSpinner) cmps[i];
                tm.setEnabled(habilitar);
                if (limpiar) {
                    tm.setValue(new Date());
                }
                continue;
            }
            if (cmps[i] instanceof JPanel || cmps[i] instanceof JScrollPane || cmps[i] instanceof JTabbedPane || cmps[i] instanceof JViewport) {
                setEditableTexto(cmps[i], habilitar, excepcion, limpiar, valor);
                continue;
            }
            if (excepcion != null) {
                if (excepcion.length > 0) {
                    boolean iguales = false;
                    for (int j = 0; j < excepcion.length; j++) {
                        if (cmps[i].equals(excepcion[j])) {
                            iguales = true;
                            break;
                        }
                    }
                    if (iguales) {
                        continue;
                    }
                }
            }
            if (cmps[i] instanceof JTextComponent) {
                JTextComponent tmp = (JTextComponent) cmps[i];
                tmp.setEditable(habilitar);
                tmp.setEnabled(habilitar);
                tmp.setBackground(Color.white);
                tmp.setForeground(Color.BLACK);
                if (limpiar) {
                    if (cmps[i] instanceof JFormattedTextField) {
                        ((JFormattedTextField) cmps[i]).setValue(null);
                    } else if (cmps[i] instanceof JTextField) {
                        tmp.setText(valor);
                    }
                }
            }
            if (cmps[i] instanceof JTextArea) {
                JTextArea tmp = (JTextArea) cmps[i];
                tmp.setEditable(habilitar);
                tmp.setEnabled(habilitar);
                tmp.setBackground(Color.white);
                tmp.setForeground(Color.BLACK);
                if (limpiar) {
                    if (cmps[i] instanceof JTextArea) {
                        ((JTextArea) cmps[i]).setText(null);
                    }
//                    else if (cmps[i] instanceof JTextField) {
//                        tmp.setText(valor);
//                    }
                }
            }
        }
    }

//    /**
//     * metedo que buscas los menus que tiene contiene un contenedor y los
//     * habilias o desabihilitas.
//     *
//     * @param cm
//     * @param habilitar
//     * @param excepcion
//     */
//    public static void buscarMenu(Component cm, boolean habilitar, boolean editar) {
//        if (cm instanceof JTabbedPane) {
//            habilitaMenus(((JTabbedPane) cm).getComponents(), habilitar, editar);
//        } else {
//            habilitaMenus(new Component[]{cm}, habilitar, editar);
//        }
//    }
//
//    private static void habilitaMenus(Component[] cmps, boolean habilitar, boolean editar) {
//        
//        for (int i = 0; i < cmps.length; i++) {
//            if (cmps[i] instanceof JPanel) {
//                habilitaMenus(((JPanel) cmps[i]).getComponents(), habilitar, editar);
//                continue;
//            }
//            if (cmps[i] instanceof JCheckBox) {
//                int id=0;
//                JCheckBox rb = (JCheckBox) cmps[i];
//                //rb.setEnabled(habilitar);
//                if (cmps[i].getName() != null) {
//                    id=peticiones.consultaMenu("'"+cmps[i].getName()+"'");
//                    System.out.print("" + cmps[i].getName() + " " + rb.isSelected() + " " +id+" \n");
//                    //rb.setText("Activo");
//                    //rb.setSelected(true);
//                    //rb.setBackground(new java.awt.Color(102, 204, 0));
//                }
//                continue;
//            }
//
////            if (excepcion != null) {
////                if (excepcion.length > 0) {
////                    boolean iguales = false;
////                    for (int j = 0; j < excepcion.length; j++) {
////                        if (cmps[i].equals(excepcion[j])) {
////                            iguales = true;
////                            break;
////                        }
////                    }
////
////                    if (iguales) {
////                        continue;
////                    }
////                }
////            }
////            if (cmps[i] instanceof AbstractButton) {
////                cmps[i].setEnabled(habilitar);
////                if (cmps[i] instanceof JToggleButton) {
////                    JToggleButton tmp = (JToggleButton) cmps[i];
////                    tmp.setSelected(false);
////                }
////            }
//        }
//    }
    /**
     * Este metodo ajusta el tamaño de las columnas en un JTable de acuerdo al
     * tamaño de sus registros , el registro más largo de una columna sera el
     * que defina el ancho de las columnas (maxwidth) o el largo del encabezado.
     *
     * @param table
     */
    public static void ajustarAnchoColumnas(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        for (int col = 1; col < table.getColumnCount(); col++) {//col = 1 para que no tome la col del id
            int maxwidth = 0;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer rend = table.getCellRenderer(row, col);
                Object value = table.getValueAt(row, col);
                /**
                 * Verifica si el texto del encabezado es mayor al texto de la
                 * columna para dejar como ancho de columna al aue sea mayor
                 */
                if (value == null) {
                    value = table.getModel().getColumnName(col);
                } else if (table.getModel().getColumnName(col).length() > value.toString().length()) {
                    value = table.getModel().getColumnName(col);
                }
                Component comp = rend.getTableCellRendererComponent(table, value, false, false, row, col);
                maxwidth = Math.max(comp.getPreferredSize().width, maxwidth);
            } // para fila
            TableColumn column = columnModel.getColumn(col);
            column.setPreferredWidth(maxwidth);
        } // para columnas 
        table.setAutoCreateRowSorter(false);//para ordenar el Jtable al dar clic encima del titulo de la columna
        table.getTableHeader().setReorderingAllowed(false) ;
    }

    public static String objectToString(Object Obj) {
        String Str = "";
        if (Obj != null) {
            Str = Obj.toString();
        }
        return Str;
    }
}
