/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.View;

import br.com.pizzaria.Control.PessoaControl;
import br.com.pizzaria.Util.ErroSistema;
import br.com.pizzaria.Http.CepHttp;
import br.com.pizzaria.Http.PessoaHttp;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @Eliezer
 */
public class frmCadPessoa extends javax.swing.JInternalFrame {

    private PessoaHttp http;
    private List<PessoaHttp> listaNomes;
    private CepHttp cepHttp;
    private PessoaControl control;
    private Cursor cursorSql = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
    private Cursor cursorDefault = Cursor.getDefaultCursor();
    private String opcaoForm;

    public frmCadPessoa() {
        initComponents();
        if (this.control == null) {
            this.control = new PessoaControl();
        }
        if (this.http == null) {
            this.http = new PessoaHttp(0);
        }
        if (this.cepHttp == null) {
            this.cepHttp = new CepHttp();
        }
        if (this.listaNomes == null) {
            this.listaNomes = new ArrayList<>();
        }

        limparGrid();
        limparCampos();
    }

    private void limparGrid() {
        DefaultTableModel dbGrid = (DefaultTableModel) jTable_Itens.getModel();
        dbGrid.setNumRows(0);
    }

    private void carregarGrid() {
        DefaultTableModel dbGrid = (DefaultTableModel) jTable_Itens.getModel();
        dbGrid.setNumRows(0);
        for (PessoaHttp p : this.listaNomes) {
            dbGrid.addColumn(new String[]{http.getCpf(),
                http.getNome(),
                http.getEmail(),
                http.getFone()});           //resolver
        }        
        
//        dbGrid.addColumn(new String[]{http.getCpf(),
//                http.getNome(),
//                http.getEmail(),
//                http.getFone()});
    }

    private void limparCampos() {
        this.setCursor(cursorSql);
        txtCpf.setText("");
        txtRg.setText("");
        txtNome.setText("");
        txtTipo.setSelectedIndex(-1);
        txtEmail.setText("");
        txtFone.setText("");
        txtCelular.setText("");
        txtCep.setText("");
        txtRua.setText("");
        txtNr.setText("");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtUF.setText("");
        this.setCursor(cursorDefault);
    }

    private void carregarCampos() {
        if (this.http.getNome().length() > 1) {
            this.setCursor(cursorSql);
            txtRg.setText(this.http.getRg());
            txtNome.setText(this.http.getNome());
            txtTipo.setSelectedItem(this.http.getTipo());
            txtEmail.setText(this.http.getEmail());
            txtFone.setText(this.http.getFone());
            txtCelular.setText(this.http.getCelular());
            txtCep.setText(this.http.getCep());
            txtRua.setText(this.http.getRua());
            txtNr.setText(this.http.getNr());
            txtComplemento.setText(this.http.getComplemento());
            txtBairro.setText(this.http.getBairro());
            txtCidade.setText(this.http.getCidade());
            txtUF.setText(this.http.getUf());
            this.setCursor(cursorDefault);
        }
    }

    private void carregarClasse() {
        this.setCursor(cursorSql);
        this.http.setBairro(txtBairro.getText());
        this.http.setCep(txtCep.getText());
        this.http.setCidade(txtCidade.getText());
        this.http.setComplemento(txtComplemento.getText());
        this.http.setCpf(txtCpf.getText());
        this.http.setEmail(txtEmail.getText());
        this.http.setFone(txtFone.getText());
        this.http.setCelular(txtCelular.getText());
        this.http.setNome(txtNome.getText());
        this.http.setNr(txtNr.getText());
        this.http.setRg(txtRg.getText());
        this.http.setRua(txtRua.getText());
        this.http.setTipo(txtTipo.getSelectedItem().toString());
        this.http.setUf(txtUF.getText());
        this.setCursor(cursorDefault);

    }

    private void carregarEndereco() {
        txtRua.setText(this.cepHttp.getLogradouro().trim().toUpperCase());
        txtBairro.setText(this.cepHttp.getBairro().trim().toUpperCase());
        txtCidade.setText(this.cepHttp.getLocalidade().trim().toUpperCase());
        txtUF.setText(this.cepHttp.getUf().trim().toUpperCase());
    }

    private Boolean validaCampos() {
        Boolean status = false;
        if (txtCpf.getText().replace(".", "").trim()
                .replace("-", "").trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor preencher campo !");
            txtCpf.grabFocus();
            return status = true;
        }
        if (txtRg.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor preencher campo !");
            txtRg.grabFocus();
            return status = true;
        }
        if (txtNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor preencher campo !");
            txtNome.grabFocus();
            return status = true;
        }
        if (txtTipo.getSelectedItem().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor preencher campo !");
            txtTipo.grabFocus();
            return status = true;
        }
        if (txtCep.getText().replace("-", "").trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor preencher campo !");
            txtCep.grabFocus();
            return status = true;
        }
        if (txtRua.getText().equals("")) {
            return status = true;
        }
        if (txtNr.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor preencher campo !");
            txtNr.grabFocus();
            return status = true;
        }
        return status;
    }

    private void buscarCpf(String cpf) {
        try {
            this.http = control.getCpf(cpf);
        } catch (ErroSistema ex) {
            Logger.getLogger(frmCadPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarNomes(String nomes) {
        try {
            this.listaNomes.clear();
            this.listaNomes = this.control.listaNome(nomes);
        } catch (ErroSistema ex) {
            Logger.getLogger(frmCadPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarCep(String cep) {
        try {
            this.cepHttp = this.control.getCep(cep);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cep não encontrado: " + e.getMessage());
        }
    }

    public void novo() {
        this.http = new PessoaHttp(0);
        limparCampos();
        limparGrid();
        txtCpf.grabFocus();
    }

    private void deletar() throws ErroSistema {
        this.control.deletePessoa(http.getId().toString());
    }

    private void salvar() throws ErroSistema {
        carregarClasse();
        try {
            if (this.http.getId().equals(0)
                    || this.http.getId().equals(null)
                    || this.http.getId().equals("")) {
//                JOptionPane.showMessageDialog(null, "PORRA POST !");
                this.control.postPessoa(http);
                buscarCpf(this.http.getCpf());
            } else {
//                JOptionPane.showMessageDialog(null, "PORRA PUT !");
                this.control.putPessoa(http);
            }
            JOptionPane.showMessageDialog(null, "Salvo com sucesso !");
            limparGrid();
            carregarCampos();
            carregarGrid();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar :" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbCpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        lbRg = new javax.swing.JLabel();
        txtRg = new javax.swing.JFormattedTextField();
        lbNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JFormattedTextField();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JFormattedTextField();
        lbFone = new javax.swing.JLabel();
        txtFone = new javax.swing.JFormattedTextField();
        lbCelular = new javax.swing.JLabel();
        txtCelular = new javax.swing.JFormattedTextField();
        lbTipo = new javax.swing.JLabel();
        txtTipo = new javax.swing.JComboBox<>();
        lbCep = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        btnCep = new javax.swing.JButton();
        lbRua = new javax.swing.JLabel();
        txtRua = new javax.swing.JFormattedTextField();
        lbNr = new javax.swing.JLabel();
        txtNr = new javax.swing.JFormattedTextField();
        lbComplemento = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JFormattedTextField();
        lbBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JFormattedTextField();
        lbCidade = new javax.swing.JLabel();
        txtCidade = new javax.swing.JFormattedTextField();
        lbUF = new javax.swing.JLabel();
        txtUF = new javax.swing.JFormattedTextField();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Itens = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarNome = new javax.swing.JFormattedTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cadastro Pessoa");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        lbCpf.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbCpf.setText("CPF");
        lbCpf.setPreferredSize(new java.awt.Dimension(40, 14));

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCpf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCpfFocusLost(evt);
            }
        });

        lbRg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbRg.setText("RG");
        lbRg.setPreferredSize(new java.awt.Dimension(40, 14));

        txtRg.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lbNome.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNome.setText("Nome");
        lbNome.setPreferredSize(new java.awt.Dimension(40, 14));

        lbEmail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbEmail.setText("e-Mail");
        lbEmail.setPreferredSize(new java.awt.Dimension(40, 14));

        lbFone.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbFone.setText("Fone");

        try {
            txtFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbCelular.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbCelular.setText("Celular");

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbTipo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTipo.setText("Tipo");

        txtTipo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "CLIENTE", "ENTREGADOR", "FORNCEDOR", "FUNCIONARIO" }));
        txtTipo.setSelectedIndex(-1);
        txtTipo.setToolTipText("");
        txtTipo.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        txtTipo.setMinimumSize(new java.awt.Dimension(97, 25));
        txtTipo.setPreferredSize(new java.awt.Dimension(97, 25));

        lbCep.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbCep.setText("Cep");

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnCep.setText("....");
        btnCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCepActionPerformed(evt);
            }
        });

        lbRua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbRua.setText("Rua");

        txtRua.setEditable(false);
        txtRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRuaActionPerformed(evt);
            }
        });

        lbNr.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNr.setText("Nr");

        lbComplemento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbComplemento.setText("Complemento");

        lbBairro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbBairro.setText("Bairro");

        txtBairro.setEditable(false);

        lbCidade.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbCidade.setText("Cidade");

        txtCidade.setEditable(false);

        lbUF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbUF.setText("UF");

        txtUF.setEditable(false);

        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnDeletar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jTable_Itens.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable_Itens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cpf", "Nome", "eMail", "Fone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_Itens);
        if (jTable_Itens.getColumnModel().getColumnCount() > 0) {
            jTable_Itens.getColumnModel().getColumn(0).setMinWidth(70);
            jTable_Itens.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable_Itens.getColumnModel().getColumn(0).setMaxWidth(100);
            jTable_Itens.getColumnModel().getColumn(3).setMinWidth(70);
            jTable_Itens.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable_Itens.getColumnModel().getColumn(3).setMaxWidth(100);
        }
        jTable_Itens.getAccessibleContext().setAccessibleParent(this);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nome :");

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addGap(8, 8, 8))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBuscarNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(348, 348, 348)
                                .addComponent(lbTipo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbCep)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnCep, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovo)
                                .addGap(6, 6, 6)
                                .addComponent(btnSalvar)
                                .addGap(6, 6, 6)
                                .addComponent(btnDeletar)
                                .addGap(6, 6, 6)
                                .addComponent(btnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(249, 249, 249)
                                        .addComponent(lbFone)))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCelular)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbRua))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNr, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNr))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbComplemento)
                                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbBairro))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbCidade))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbUF)
                                    .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lbRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipo))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbFone)
                        .addComponent(lbCelular)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCep)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRua)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbComplemento)
                        .addComponent(lbNr)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbBairro)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCidade)
                        .addComponent(lbUF)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnDeletar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRuaActionPerformed

    private void btnCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCepActionPerformed
        String cep = txtCep.getText().replace("-", "").trim();
        if (cep.length() < 8) {
            JOptionPane.showMessageDialog(null, "Cep inválido !");
            return;
        }
        buscarCep(txtCep.getText());
        if (this.cepHttp.getLogradouro().length() > 0) {
            carregarEndereco();
            txtNr.grabFocus();
        }
    }//GEN-LAST:event_btnCepActionPerformed

    private void txtCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusLost
        if (this.opcaoForm == "SAIR") {
            this.opcaoForm = "";
            return;
        }
        String cpf = txtCpf.getText().replace(".", "").trim();
        cpf = cpf.replace("-", "").trim();

        if (cpf.length() < 11) {
            JOptionPane.showMessageDialog(null, "CPF Invalido !");
            return;
        }
        if (cpf.length() == 11) {
            try {
                buscarCpf(txtCpf.getText());
                if (this.http.getNome().length() > 1) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado !");
                    limparGrid();
                    carregarCampos();
                    carregarGrid();
                }
            } catch (Exception e) {
                Logger.getLogger(frmCadPessoa.class.getName()).log(Level.SEVERE, null, e);
//                JOptionPane.showMessageDialog(null, "Erro ao carregar cpf !" + e.getMessage());
            }
        }

    }//GEN-LAST:event_txtCpfFocusLost

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (validaCampos() == true) {
            return;
        }
        try {
            salvar();
        } catch (ErroSistema ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        novo();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        if (http.getId().equals(0)) {
            JOptionPane.showMessageDialog(null, "Pessoa não encontrada !");
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja deletar Pessoa[ " + this.http.getNome() + " ]",
                "Sistema Pizzaria", JOptionPane.YES_NO_OPTION);
        try {
            if (resposta == JOptionPane.YES_OPTION) {
                deletar();
                novo();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso !");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        carregarCampos();
        txtCpf.grabFocus();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
//       JOptionPane.showMessageDialog(null, "saiui");
        this.opcaoForm = "SAIR";
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtBuscarNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "favor preencher campo busca !");
            txtBuscarNome.grabFocus();
            return;
        }
        try {
            buscarNomes(txtBuscarNome.getText());
            carregarGrid();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCep;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Itens;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCep;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbComplemento;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbFone;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNr;
    private javax.swing.JLabel lbRg;
    private javax.swing.JLabel lbRua;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JLabel lbUF;
    private javax.swing.JFormattedTextField txtBairro;
    private javax.swing.JFormattedTextField txtBuscarNome;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JFormattedTextField txtCidade;
    private javax.swing.JFormattedTextField txtComplemento;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtEmail;
    private javax.swing.JFormattedTextField txtFone;
    private javax.swing.JFormattedTextField txtNome;
    private javax.swing.JFormattedTextField txtNr;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtRua;
    private javax.swing.JComboBox<String> txtTipo;
    private javax.swing.JFormattedTextField txtUF;
    // End of variables declaration//GEN-END:variables
}
