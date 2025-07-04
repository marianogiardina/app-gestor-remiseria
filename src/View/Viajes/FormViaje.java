/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Viajes;

import Controller.ChoferController;
import Controller.ViajeController;
import Model.Chofer;
import Model.EstadoViaje;
import Model.Viaje;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

import static View.Index.mostrarPanel;

/**
 *
 * @author Compumar
 */
public class FormViaje extends javax.swing.JPanel {

    private ChoferController choferController = new ChoferController();
    private ViajeController viajeController = new ViajeController();

    private boolean isUpdate = false; // Variable para indicar si es una actualización o creación de viaje
    private Viaje viajeExistente; // Variable para almacenar el viaje a actualizar

    /**
     * Creates new form CreateViaje
     */
    public FormViaje() {

        //Como este constructor es para crear un viaje nuevo, no es una actualización, en este caso isUpdate es false, por lo que envio un null al metodo initComponents.
        // Esto permite reutilizar el mismo panel para crear y actualizar viajes.
        // En el initComponents, recibe un viaje, si es null, no carga los datos del viaje en los campos correspondientes ya que la validacion utiliza el isUpdate.
        initComponents();

        refreshChoferes();

        refreshEstados();

    }

    // Constructor para actualizar un viaje existente, el parametro isUpdate indica si es una actualización para que se pueda reutilizar el mismo panel para crear y actualizar viajes.
    public FormViaje(boolean isUpdate, Viaje viaje) {

        this.isUpdate = isUpdate;

        this.viajeExistente = viaje;

        initComponents();

        refreshChoferes();

        refreshEstados();

    }

    private void refreshChoferes() {
        //Llamo al choferController para obtener la lista de choferes

        List<Chofer> choferes = choferController.readAll();

        if(isUpdate){
            inputChofer.addItem(viajeExistente.getChofer());
        }

        for (Chofer chofer : choferes) {

            //Agrego cada chofer disponible al comboBox de choferes-->por default el comboBox muestra el chofer.toString() por eso lo modifico para que solo muestre el nombre.
            if(chofer.isDisponible()){

                inputChofer.addItem(chofer);

            }
        }
    }

    private void refreshEstados() {
        //Limpio el comboBox de estados de viaje
        inputEstadoViaje.removeAllItems();

        //Agrego los estados de viaje al comboBox
        for (EstadoViaje estado : EstadoViaje.values()) {
 
            inputEstadoViaje.addItem(estado);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelOrigen = new javax.swing.JLabel();
        inputOrigen = new javax.swing.JTextField();
        labelDestino = new javax.swing.JLabel();
        inputDestino = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        inputKilometros = new javax.swing.JTextField();
        labelValorKm = new javax.swing.JLabel();
        inputValorKm = new javax.swing.JTextField();
        labelEstadoViaje = new javax.swing.JLabel();
        inputEstadoViaje = new javax.swing.JComboBox<>();
        labelChofer = new javax.swing.JLabel();
        inputChofer = new javax.swing.JComboBox<>();
        btnCreateViaje = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(927, 590));

        labelOrigen.setText("Origen");

        inputOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputOrigenActionPerformed(evt);
            }
        });

        labelDestino.setText("Destino");

        inputDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDestinoActionPerformed(evt);
            }
        });

        jLabel1.setText("Kilometros");

        inputKilometros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputKilometrosActionPerformed(evt);
            }
        });

        labelValorKm.setText("Valor del kilometro");

        inputValorKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputValorKmActionPerformed(evt);
            }
        });

        labelEstadoViaje.setText("Estado del viaje");

        //inputEstadoViaje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En curso", "Finalizado" }));
        inputEstadoViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEstadoViajeActionPerformed(evt);
            }
        });

        labelChofer.setText("Chofer");

        inputChofer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputChoferActionPerformed(evt);
            }
        });


        //Cuando inicio el panel, si es una actualización, cambio el texto del botón a "Actualizar Viaje", si no, lo dejo como "Crear Viaje"
        if(this.isUpdate){

            btnCreateViaje.setText("Actualizar Viaje");
            // Si es una actualización, cargo los datos del viaje en los campos correspondientes

            inputOrigen.setText(viajeExistente.getOrigen());
            inputDestino.setText(viajeExistente.getDestino());
            inputKilometros.setText(String.valueOf(viajeExistente.getKilometros()));
            inputValorKm.setText(String.valueOf(viajeExistente.getValorKm()));
            inputEstadoViaje.setSelectedItem(viajeExistente.getEstadoViaje());
            inputChofer.setSelectedItem(viajeExistente.getChofer());

        } else {

            btnCreateViaje.setText("Crear Viaje");

        }
        btnCreateViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateViajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(labelOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelValorKm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputValorKm))
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(labelDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputEstadoViaje, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEstadoViaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateViaje, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputKilometros, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(labelChofer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputChofer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inputKilometros, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorKm, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(labelEstadoViaje, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(labelChofer, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputValorKm, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(inputEstadoViaje)
                    .addComponent(inputChofer))
                .addGap(119, 119, 119)
                .addComponent(btnCreateViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void inputOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputOrigenActionPerformed
    }//GEN-LAST:event_inputOrigenActionPerformed

    private void inputDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDestinoActionPerformed
    }//GEN-LAST:event_inputDestinoActionPerformed

    private void inputKilometrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputKilometrosActionPerformed
    }//GEN-LAST:event_inputKilometrosActionPerformed

    private void inputValorKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputValorKmActionPerformed
    }//GEN-LAST:event_inputValorKmActionPerformed

    private void inputEstadoViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputEstadoViajeActionPerformed
    }//GEN-LAST:event_inputEstadoViajeActionPerformed

    private void inputChoferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputChoferActionPerformed
    }//GEN-LAST:event_inputChoferActionPerformed

    private void btnCreateViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateViajeActionPerformed
        String origen = inputOrigen.getText();
        String destino = inputDestino.getText();
        String kilometrosStr = inputKilometros.getText();
        String valorKmStr = inputValorKm.getText();
        EstadoViaje estadoViaje = (EstadoViaje) inputEstadoViaje.getSelectedItem();
        Chofer chofer = (Chofer) inputChofer.getSelectedItem();
        LocalDateTime fecha = LocalDateTime.now();

        // Valido que no haya campos vacíos
        if (origen.isEmpty() || destino.isEmpty() || kilometrosStr.isEmpty() || valorKmStr.isEmpty() || chofer == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        // Valido que los kilómetros y el valor por kilómetro sean números válidos
        double kilometros, valorKm;
        try {
            kilometros = Double.parseDouble(kilometrosStr);
            valorKm = Double.parseDouble(valorKmStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Kilómetros y Valor por Km deben ser números válidos.");
            return;
        }

        // Valido que los kilómetros y el valor por kilómetro sean mayores a 0

        if (kilometros <= 0 || valorKm <= 0) {
            JOptionPane.showMessageDialog(this, "Kilómetros y Valor por Km deben ser mayores a 0.");
            return;
        }

        if(this.isUpdate){

            // Si es una actualización, modifico el viaje existente, excepto la fecha que se mantiene como la fecha en la que fue creado.

            viajeExistente.setOrigen(origen);
            viajeExistente.setDestino(destino);
            viajeExistente.setKilometros(kilometros);
            viajeExistente.setValorKm(valorKm);
            viajeExistente.setEstadoViaje(estadoViaje);
            viajeExistente.setChofer(chofer);

            viajeController.update(viajeExistente);

        }else{

            viajeController.create(new Viaje(kilometros, valorKm, estadoViaje, chofer, origen, destino, fecha));
        }


        mostrarPanel(new ListadoViajes());

    }//GEN-LAST:event_btnCreateViajeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateViaje;
    private javax.swing.JComboBox<Chofer> inputChofer;
    private javax.swing.JTextField inputDestino;
    private javax.swing.JComboBox<EstadoViaje> inputEstadoViaje;
    private javax.swing.JTextField inputKilometros;
    private javax.swing.JTextField inputOrigen;
    private javax.swing.JTextField inputValorKm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelChofer;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelEstadoViaje;
    private javax.swing.JLabel labelOrigen;
    private javax.swing.JLabel labelValorKm;
    // End of variables declaration//GEN-END:variables
}
