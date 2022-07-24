/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chanchicken;
import java.sql.*;
import com.chanchicken.main;
import javax.swing.table.DefaultTableModel;
import java.text.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

/**
 *
 * @author Richard Maru
 */
public class home extends javax.swing.JFrame {
    String employeeID;
    int access;
    Connection connection;
    PreparedStatement stm;
    ResultSet result;
    Map<String, Integer> roles = new HashMap<>();
    

    public home() {
        initComponents();
        Connect();
        LoadData();
    }
    
    private void LoadTabs() {
        if (access==50){
            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
            jTabbedPane1.setEnabledAt(3, false);
            jTabbedPane1.setSelectedIndex(4);
            jTabbedPane1.setEnabledAt(4, true);
            jTabbedPane1.setEnabledAt(5, false);
        } else if (access==80){
            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
            jTabbedPane1.setEnabledAt(3, false);
            jTabbedPane1.setSelectedIndex(4);
            jTabbedPane1.setEnabledAt(4, true);
            jTabbedPane1.setEnabledAt(5, true);
        } else if (access==100){
            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
            jTabbedPane1.setSelectedIndex(3);
            jTabbedPane1.setEnabledAt(3, true);
            jTabbedPane1.setEnabledAt(4, true);
            jTabbedPane1.setEnabledAt(5, true);
        }
    }
    
    public void LoadData(){
        //Employees
        DefaultTableModel tbEmployee = (DefaultTableModel) tblEmployees.getModel();
        tbEmployee.setRowCount(0);
        try {
            stm = connection.prepareStatement("SELECT * FROM employees");
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("EMPLOYEE_ID"), result.getString("EMPLOYEE_NAME"), result.getString("EMPLOYEE_PHONE"), result.getString("EMPLOYEE_ADD"), result.getInt("ROLE_NO")};
                tbEmployee.addRow(row);
            }
        } catch (Exception e){
            System.out.println("Error fetching employee list.");
        };
        DefaultTableModel tbRoles = (DefaultTableModel) tblRoles.getModel();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbRoles.getModel();
        model.removeAllElements();
        tbRoles.setRowCount(0);
        try {
            stm = connection.prepareStatement("SELECT * FROM roles");
            result = stm.executeQuery();
            while(result.next()){
                tbRoles.addRow(new Object[]{result.getInt("ROLE_NO"), result.getString("ROLE_DESCRIPTION")}); //Table Roles
                roles.put(result.getString("ROLE_DESCRIPTION"), result.getInt("ROLE_NO")); //HashMap Roles
                model.addElement((String)result.getString("ROLE_DESCRIPTION"));
            }
        } catch (Exception e){
            System.out.println("Error fetching role list.");
        };
        //Products
        DefaultTableModel tbProducts = (DefaultTableModel) tblProducts.getModel();
        DefaultTableModel tbProducts2 = (DefaultTableModel) posProdList.getModel();
        tbProducts.setRowCount(0);
        tbProducts2.setRowCount(0);
        try {
            stm = connection.prepareStatement("SELECT * FROM products");
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("PROD_CODE"), result.getString("PROD_DESC"), result.getInt("PROD_STOCK"), result.getDouble("UNIT_PRICE")};
                tbProducts.addRow(row);
                tbProducts2.addRow(row);
            }
        } catch (Exception e){
            System.out.println("Error fetching product list.");
        };
        getOrdersToday();
        
    }
    
    public void AddOrder(int prod_code, double price){
        posProdCode.setText((String.valueOf(prod_code)));
        posPrice.setText((String.valueOf(price)));
    }
    
    public boolean isEmpty(String string){
        return (string == null || string.equals(""));
    }
    
    public void Connect(){
        try {
            connection = DriverManager.getConnection(main.url, main.username, main.password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void getOrdersToday(){
        DefaultTableModel tbOrders = (DefaultTableModel) tblOrder.getModel();
        tbOrders.setRowCount(0);
        String query = "SELECT * FROM chanchicken.order INNER JOIN chanchicken.products ON products.PROD_CODE = order.PROD_CODE INNER JOIN chanchicken.order_invoice ON order_invoice.ORDER_NUM = order.ORDER_NUM WHERE CAST(ORDER_DATE AS DATE)=curdate()";
          
        try {
            System.out.println(query);
            stm = connection.prepareStatement(query);
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("ORDER_NUM"),result.getInt("PROD_CODE"), result.getInt("PROD_QTY"), result.getDouble("PRICE")};
                tbOrders.addRow(row);
            }
        } catch (Exception e){
            System.out.println("Error fetching orders today. " + e.getMessage());
        }
    }
    
    public void getUserInfo(){
        try {
            stm = connection.prepareStatement("select employees.EMPLOYEE_NAME, roles.ROLE_NO, roles.ROLE_DESCRIPTION from employees inner join roles on roles.ROLE_NO = employees.ROLE_NO where EMPLOYEE_ID='"+employeeID+"'");
            result = stm.executeQuery();
            while(result.next()){
                lbl_UserName.setText(result.getString("EMPLOYEE_NAME"));
                lbl_UserRole.setText(result.getString("ROLE_DESCRIPTION"));
                lbl_IDNo.setText(employeeID);
                access = result.getInt("ROLE_NO");
            }
        } catch (Exception e){
            System.out.println("Error fetching user information.");
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_UserName = new javax.swing.JLabel();
        lbl_UserRole = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lbl_IDNo = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelEmployees = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        empID = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        editEmployee = new javax.swing.JButton();
        addEmployee = new javax.swing.JToggleButton();
        searchEmployee = new javax.swing.JButton();
        deleteEmployee = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        empName = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        empPhone = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        empAdd = new javax.swing.JTextArea();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        empUname = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        empPword = new javax.swing.JTextField();
        cbRoles = new javax.swing.JComboBox<>();
        panelRoles = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblRoles = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        roleno = new javax.swing.JTextField();
        searchRole = new javax.swing.JButton();
        addRole = new javax.swing.JButton();
        deleteRole = new javax.swing.JButton();
        editRole = new javax.swing.JButton();
        role_desc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        panelProducts = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        addProduct = new javax.swing.JButton();
        searchProduct = new javax.swing.JButton();
        editProduct = new javax.swing.JButton();
        prod_code = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        deleteProduct = new javax.swing.JButton();
        prod_desc = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        prod_stock = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        prod_price = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        panelInvoice = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ordernum_invoice = new javax.swing.JTextField();
        searchInvoice = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        employeeID_invoice = new javax.swing.JTextField();
        employeeName_invoice = new javax.swing.JTextField();
        custName_invoice = new javax.swing.JTextField();
        transactionDate = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblOrderList = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblOrderInvoice = new javax.swing.JTable();
        panelOrder = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        ordersToday = new javax.swing.JToggleButton();
        searchOrder = new javax.swing.JButton();
        ordernum_order = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAllOrders = new javax.swing.JButton();
        panelPOS = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        posProdList = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        posOrderList = new javax.swing.JTable();
        pay = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        custName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        totalAmt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        posProdCode = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        posQty = new javax.swing.JTextField();
        addProductToOrder = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        posPrice = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChanChicken - Home");
        setName("home"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("User:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Role:");

        lbl_UserName.setText("User");
        lbl_UserName.setName("userName"); // NOI18N

        lbl_UserRole.setText("Role");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel14.setText("ID NO.:");

        lbl_IDNo.setText("jLabel17");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_UserName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_UserRole)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_IDNo)
                    .addComponent(btnLogout))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lbl_UserName))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(lbl_IDNo)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_UserRole)
                    .addComponent(btnLogout)
                    .addComponent(btnRefresh))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        lbl_UserName.getAccessibleContext().setAccessibleName("userName");

        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Name", "Contact Number", "Address", "Role Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEmployees);

        empID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empIDActionPerformed(evt);
            }
        });

        jPanel7.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.selectionBackground"));

        editEmployee.setText("Edit");
        editEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEmployeeActionPerformed(evt);
            }
        });

        addEmployee.setText("Add");
        addEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmployeeActionPerformed(evt);
            }
        });

        searchEmployee.setText("Search");
        searchEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchEmployeeActionPerformed(evt);
            }
        });

        deleteEmployee.setText("Delete");
        deleteEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEmployeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchEmployee)
                    .addComponent(addEmployee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editEmployee)
                    .addComponent(deleteEmployee))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("ID No:");

        jLabel29.setText("Name:");

        jLabel30.setText("Contact No.:");

        jLabel31.setText("Address");

        empAdd.setColumns(20);
        empAdd.setRows(5);
        jScrollPane3.setViewportView(empAdd);

        jLabel32.setText("Role");

        jLabel33.setText("Username");

        jLabel34.setText("Password");

        javax.swing.GroupLayout panelEmployeesLayout = new javax.swing.GroupLayout(panelEmployees);
        panelEmployees.setLayout(panelEmployeesLayout);
        panelEmployeesLayout.setHorizontalGroup(
            panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 28, Short.MAX_VALUE)
                .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmployeesLayout.createSequentialGroup()
                            .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(empID, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(empName, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelEmployeesLayout.createSequentialGroup()
                        .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(empPhone)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEmployeesLayout.createSequentialGroup()
                        .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel32))
                        .addGap(33, 33, 33)
                        .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(empPword)
                            .addComponent(empUname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addComponent(cbRoles, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        panelEmployeesLayout.setVerticalGroup(
            panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
            .addGroup(panelEmployeesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(empID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(empName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(empPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(empUname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(empPword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Employees", panelEmployees);

        tblRoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Role Number", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tblRoles);

        jLabel19.setText("Role No.:");

        searchRole.setText("Search");
        searchRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchRoleActionPerformed(evt);
            }
        });

        addRole.setText("Add");
        addRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoleActionPerformed(evt);
            }
        });

        deleteRole.setText("Delete");
        deleteRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoleActionPerformed(evt);
            }
        });

        editRole.setText("Edit");
        editRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRoleActionPerformed(evt);
            }
        });

        jLabel23.setText("Description");

        javax.swing.GroupLayout panelRolesLayout = new javax.swing.GroupLayout(panelRoles);
        panelRoles.setLayout(panelRolesLayout);
        panelRolesLayout.setHorizontalGroup(
            panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRolesLayout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19)
                    .addGroup(panelRolesLayout.createSequentialGroup()
                        .addComponent(searchRole, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addRole, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRolesLayout.createSequentialGroup()
                        .addComponent(editRole, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteRole, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roleno)
                    .addComponent(jLabel23)
                    .addComponent(role_desc))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        panelRolesLayout.setVerticalGroup(
            panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRolesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchRole)
                    .addComponent(addRole))
                .addGap(18, 18, 18)
                .addGroup(panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteRole)
                    .addComponent(editRole))
                .addGap(55, 55, 55)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(roleno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(role_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRolesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Roles", panelRoles);

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Description", "Stock", "Unit Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
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
        jScrollPane1.setViewportView(tblProducts);

        addProduct.setText("Add");
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        searchProduct.setText("Search");
        searchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchProductActionPerformed(evt);
            }
        });

        editProduct.setText("Edit");
        editProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProductActionPerformed(evt);
            }
        });

        jLabel11.setText("Product Code:");

        deleteProduct.setText("Delete");
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });

        jLabel26.setText("Description:");

        jLabel27.setText("Stock:");

        jLabel28.setText("Unit Price:");

        javax.swing.GroupLayout panelProductsLayout = new javax.swing.GroupLayout(panelProducts);
        panelProducts.setLayout(panelProductsLayout);
        panelProductsLayout.setHorizontalGroup(
            panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addGroup(panelProductsLayout.createSequentialGroup()
                        .addGroup(panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                        .addGroup(panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProductsLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(deleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductsLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(prod_code)
                    .addComponent(jLabel26)
                    .addComponent(prod_desc)
                    .addComponent(jLabel27)
                    .addComponent(prod_stock)
                    .addComponent(jLabel28)
                    .addComponent(prod_price))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        panelProductsLayout.setVerticalGroup(
            panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(panelProductsLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchProduct)
                    .addComponent(addProduct))
                .addGap(18, 18, 18)
                .addGroup(panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editProduct)
                    .addComponent(deleteProduct))
                .addGap(48, 48, 48)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(prod_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(prod_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(prod_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(prod_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Products", panelProducts);

        jLabel6.setText("Order Number:");

        searchInvoice.setText("Search");
        searchInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInvoiceActionPerformed(evt);
            }
        });

        jLabel7.setText("Employee ID:");

        jLabel8.setText("Employee Name:");

        jLabel9.setText("Transaction Date:");

        jLabel10.setText("Customer Name:");

        transactionDate.setEditable(false);

        tblOrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Description", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class
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
        jScrollPane4.setViewportView(tblOrderList);

        tblOrderInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Employee ID", "Order Date", "Customer Name", "Total Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrderInvoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderInvoiceMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblOrderInvoice);
        if (tblOrderInvoice.getColumnModel().getColumnCount() > 0) {
            tblOrderInvoice.getColumnModel().getColumn(0).setResizable(false);
            tblOrderInvoice.getColumnModel().getColumn(1).setResizable(false);
            tblOrderInvoice.getColumnModel().getColumn(2).setResizable(false);
            tblOrderInvoice.getColumnModel().getColumn(3).setResizable(false);
            tblOrderInvoice.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout panelInvoiceLayout = new javax.swing.GroupLayout(panelInvoice);
        panelInvoice.setLayout(panelInvoiceLayout);
        panelInvoiceLayout.setHorizontalGroup(
            panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInvoiceLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                    .addGroup(panelInvoiceLayout.createSequentialGroup()
                        .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInvoiceLayout.createSequentialGroup()
                                .addComponent(ordernum_invoice)
                                .addGap(18, 18, 18)
                                .addComponent(searchInvoice))
                            .addGroup(panelInvoiceLayout.createSequentialGroup()
                                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(employeeID_invoice)
                                    .addComponent(employeeName_invoice, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelInvoiceLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(transactionDate, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                                    .addGroup(panelInvoiceLayout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(custName_invoice))))))
                    .addComponent(jScrollPane4))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        panelInvoiceLayout.setVerticalGroup(
            panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInvoiceLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ordernum_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchInvoice))
                .addGap(18, 18, 18)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(employeeID_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(custName_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(employeeName_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Order Invoice", panelInvoice);

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Product Code", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class
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
        jScrollPane6.setViewportView(tblOrder);

        ordersToday.setText("Orders Today");
        ordersToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersTodayActionPerformed(evt);
            }
        });

        searchOrder.setText("Search Order");
        searchOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchOrderActionPerformed(evt);
            }
        });

        ordernum_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordernum_orderActionPerformed(evt);
            }
        });

        jLabel5.setText("Order No.");

        btnAllOrders.setText("All Orders");
        btnAllOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllOrdersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOrderLayout = new javax.swing.GroupLayout(panelOrder);
        panelOrder.setLayout(panelOrderLayout);
        panelOrderLayout.setHorizontalGroup(
            panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1163, Short.MAX_VALUE)
                    .addGroup(panelOrderLayout.createSequentialGroup()
                        .addComponent(ordersToday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAllOrders)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(ordernum_order, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchOrder)))
                .addContainerGap())
        );
        panelOrderLayout.setVerticalGroup(
            panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrderLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ordersToday)
                        .addComponent(searchOrder)
                        .addComponent(ordernum_order, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAllOrders))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Order", panelOrder);

        jPanel8.setBackground(new java.awt.Color(72, 102, 105));

        jLabel15.setText("PRODUCT LIST");

        posProdList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PROD_CODE", "PROD_DESC", "STOCK", "PRICE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
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
        posProdList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posProdListMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(posProdList);
        if (posProdList.getColumnModel().getColumnCount() > 0) {
            posProdList.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7)
                .addContainerGap())
        );

        jPanel9.setBackground(java.awt.Color.gray);

        jLabel16.setText("ORDER LIST");

        posOrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PROD_CODE", "QTY", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(posOrderList);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );

        pay.setText("Pay");
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });

        jLabel18.setText("Customer Name:");

        jLabel21.setText("Total Amount:");

        totalAmt.setText("0");

        jLabel22.setText("Product Code:");

        jLabel24.setText("Quantity:");

        posQty.setText("1");

        addProductToOrder.setText("Add");
        addProductToOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductToOrderActionPerformed(evt);
            }
        });

        jLabel25.setText("Price:");

        posPrice.setEditable(false);

        javax.swing.GroupLayout panelPOSLayout = new javax.swing.GroupLayout(panelPOS);
        panelPOS.setLayout(panelPOSLayout);
        panelPOSLayout.setHorizontalGroup(
            panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPOSLayout.createSequentialGroup()
                        .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(custName)
                            .addComponent(posProdCode))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelPOSLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(posQty)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(posPrice)
                        .addGap(30, 30, 30)
                        .addComponent(addProductToOrder))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalAmt)
                    .addGroup(panelPOSLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 56, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPOSLayout.setVerticalGroup(
            panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPOSLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(custName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(posPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(posProdCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)
                        .addComponent(posQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addProductToOrder)))
                .addGap(18, 18, 18)
                .addGroup(panelPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPOSLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalAmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("POS", panelPOS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        try {
            connection.close();
        } catch (Exception e) {e.printStackTrace();};
        login loginPage = new login();
        loginPage.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        LoadData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void addProductToOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductToOrderActionPerformed
        // TODO add your handling code here:
        int prod_code = Integer.parseInt(posProdCode.getText());
        int qty = Integer.parseInt(posQty.getText());
        double price = Double.parseDouble(posPrice.getText()) * qty;
        double total = Double.parseDouble(totalAmt.getText());
        DefaultTableModel tbOrders = (DefaultTableModel) posOrderList.getModel();
        tbOrders.addRow(new Object[]{prod_code,qty,price});
        totalAmt.setText(String.valueOf(total+price));
    }//GEN-LAST:event_addProductToOrderActionPerformed

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed
        // TODO add your handling code here:
        String customerName = custName.getText();
        double total = Double.parseDouble(totalAmt.getText());
        int orderno = 0;
        //Parse Table
        DefaultTableModel tbOrders = (DefaultTableModel) posOrderList.getModel();
        Object[] model = tbOrders.getDataVector().toArray();
        //order invoice record
        try {
            stm = connection.prepareStatement("INSERT INTO order_invoice (EMPLOYEE_ID, CUST_NAME, TOTAL_AMOUNT) VALUES (?,?,?)");
            stm.setString(1, employeeID);
            stm.setString(2, customerName);
            stm.setDouble(3, total);
            stm.execute();
            stm = connection.prepareStatement("SELECT LAST_INSERT_ID()");
            result = stm.executeQuery();
            if(result.next()) orderno = result.getInt("LAST_INSERT_ID()");
            System.out.println("ORDERNO: "+orderno);
        } catch (Exception e){
            System.out.println("Error recording invoice");
            e.printStackTrace();
        };

        for (Object a : model){
            Vector vec = (Vector) a;
            Object[] row = vec.toArray();
            try {
                stm = connection.prepareStatement("INSERT INTO chanchicken.order (ORDER_NUM, PROD_CODE, PROD_QTY, PRICE) VALUES (?,?,?,?)");
                stm.setInt(1, orderno);
                stm.setInt(2, (Integer)row[0]);
                stm.setInt(3, (Integer)row[1]);
                stm.setDouble(4, (Double)row[2]);
                stm.execute();
            } catch (Exception e){
                System.out.println("Error recording order list");
                e.printStackTrace();
            };

        }

        //Clear Current Data
        custName.setText("");
        totalAmt.setText("0");
        posProdCode.setText("");
        posQty.setText("1");
        posPrice.setText("");
        tbOrders.setRowCount(0);
    }//GEN-LAST:event_payActionPerformed

    private void posProdListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posProdListMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        int col = source.columnAtPoint( evt.getPoint() );
        if (col>=0 && row>=0){
            DefaultTableModel tbProducts = (DefaultTableModel) posProdList.getModel();
            int prodcode = (Integer) tbProducts.getValueAt(row, 0);
            double price = (Double) tbProducts.getValueAt(row, 3);
            int stock = (Integer) tbProducts.getValueAt(row, 2);
            if (stock>0){
                AddOrder(prodcode, price);
            }
        }
    }//GEN-LAST:event_posProdListMouseClicked

    private void ordernum_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordernum_orderActionPerformed
        // TODO add your handling code here:
        ordernum_order.setText("");
    }//GEN-LAST:event_ordernum_orderActionPerformed

    private void searchOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchOrderActionPerformed
        // TODO add your handling code here:
        String code = ordernum_order.getText();
        String query = "";
        DefaultTableModel tbOrder = (DefaultTableModel) tblOrder.getModel();
        tbOrder.setRowCount(0);
        try {
            query = "SELECT * FROM chanchicken.order WHERE ORDER_NUM=" + code;
            stm = connection.prepareStatement(query);
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("ORDER_NUM"),result.getInt("PROD_CODE"), result.getInt("PROD_QTY"), result.getDouble("PRICE")};
                tbOrder.addRow(row);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        };
    }//GEN-LAST:event_searchOrderActionPerformed

    private void ordersTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersTodayActionPerformed
        // TODO add your handling code here:
        getOrdersToday();
    }//GEN-LAST:event_ordersTodayActionPerformed

    private void tblOrderInvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderInvoiceMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblOrderInvoiceMouseClicked

    private void searchInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInvoiceActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbInvoice = (DefaultTableModel) tblOrderInvoice.getModel();
        DefaultTableModel tbOrder = (DefaultTableModel) tblOrderList.getModel();
        tbInvoice.setRowCount(0);
        tbOrder.setRowCount(0);
        int resCount=0;

        String ON = ordernum_invoice.getText();
        String EID = employeeID_invoice.getText();
        String EName = employeeName_invoice.getText();
        String CName = custName_invoice.getText();

        String query = "select order_invoice.ORDER_NUM, order_invoice.EMPLOYEE_ID, EMPLOYEE_NAME, ORDER_DATE, CUST_NAME, TOTAL_AMOUNT from order_invoice inner join employees on employees.EMPLOYEE_ID = order_invoice.EMPLOYEE_ID";
        if (!isEmpty(ON)) query += " where ORDER_NUM=" + Integer.parseInt(ON);
        if (!isEmpty(ON)&&!isEmpty(EID)) query += " and order_invoice.EMPLOYEE_ID=" + EID;
        if (isEmpty(ON)&&!isEmpty(EID)) query += " where order_invoice.EMPLOYEE_ID=" + EID;
        if (!isEmpty(ON)&&!isEmpty(EName)) query += " and EMPLOYEE_NAME='" + EName + "'";
        if (isEmpty(ON)&&!isEmpty(EName)) query += " where EMPLOYEE_NAME='" + EName + "'";
        if (!isEmpty(ON)&&!isEmpty(CName)) query += " and CUST_NAME='" + CName + "'";
        if (isEmpty(ON)&&!isEmpty(CName)) query += " where CUST_NAME='" + CName + "'";

        try {
            stm = connection.prepareStatement(query);
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("ORDER_NUM"), result.getString("EMPLOYEE_ID"), result.getDate("ORDER_DATE"), result.getString("CUST_NAME"), result.getDouble("TOTAL_AMOUNT")};
                tbInvoice.addRow(row);
                ON = Integer.toString(result.getInt("ORDER_NUM"));
                resCount++;
            }
        } catch (Exception e){
            System.out.println("Error fetching order invoice.");
            e.printStackTrace();
        };
        if (resCount==1){
            try {
                query = "SELECT order.PROD_CODE, PROD_DESC, PROD_QTY, PRICE FROM chanchicken.order INNER JOIN chanchicken.products ON products.PROD_CODE = order.PROD_CODE WHERE ORDER_NUM=" + ON;
                stm = connection.prepareStatement(query);
                result = stm.executeQuery();
                while(result.next()){
                    Object[] row = {result.getString("PROD_CODE"), result.getString("PROD_DESC"), result.getInt("PROD_QTY"), result.getDouble("PRICE")};
                    tbOrder.addRow(row);}
            } catch (Exception e){
                System.out.println(e.getMessage());
            };
        }
    }//GEN-LAST:event_searchInvoiceActionPerformed

    private void editProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProductActionPerformed
        // TODO add your handling code here:
        int code = Integer.parseInt(prod_code.getText());
        String desc = prod_desc.getText();
        int stock = Integer.parseInt(prod_stock.getText());
        double unitPrice = Double.parseDouble(prod_price.getText());
        try {
            stm = connection.prepareStatement("UPDATE products SET PROD_DESC=?, PROD_STOCK=?, UNIT_PRICE=? WHERE PROD_CODE=?");
            stm.setInt(4, code);
            stm.setString(1, desc);
            stm.setInt(2, stock);
            stm.setDouble(3, unitPrice);
            stm.execute();
        } catch (Exception e){
            System.out.println("Error fetching product list.");
        };
        LoadData();
    }//GEN-LAST:event_editProductActionPerformed

    private void searchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchProductActionPerformed
        // TODO add your handling code here:
        String code = prod_code.getText();
        DefaultTableModel tbProducts = (DefaultTableModel) tblProducts.getModel();
        tbProducts.setRowCount(0);
        try {
            stm = connection.prepareStatement("SELECT * FROM products where PROD_CODE="+code.trim());
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("PROD_CODE"), result.getString("PROD_DESC"), result.getInt("PROD_STOCK"), result.getDouble("UNIT_PRICE")};
                tbProducts.addRow(row);
                prod_desc.setText(result.getString("PROD_DESC"));
                prod_stock.setText(String.valueOf(result.getInt("PROD_STOCK")));
                prod_price.setText(String.valueOf(result.getDouble("UNIT_PRICE")));
            }
        } catch (Exception e){
            System.out.println("Error fetching product list.");
        };
    }//GEN-LAST:event_searchProductActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        // TODO add your handling code here:
        int code = Integer.parseInt(prod_code.getText());
        String desc = prod_desc.getText();
        int stock = Integer.parseInt(prod_stock.getText());
        double unitPrice = Double.parseDouble(prod_price.getText());
        try {
            stm = connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?)");
            stm.setInt(1, code);
            stm.setString(2, desc);
            stm.setInt(3, stock);
            stm.setDouble(4, unitPrice);
            stm.execute();
        } catch (Exception e){
            System.out.println("Error fetching product list.");
        };
        LoadData();
    }//GEN-LAST:event_addProductActionPerformed

    private void addEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmployeeActionPerformed
        int id = Integer.parseInt(empID.getText());
        String name = empName.getText();
        String contact = empPhone.getText();
        String address = empAdd.getText();
        int role_no = roles.get((String)cbRoles.getSelectedItem());
        String username = empUname.getText();
        String password = empPword.getText();
        try {
            stm = connection.prepareStatement("INSERT INTO employees VALUES (?,?,?,?,?,?,?)");
            stm.setInt(1,id);
            stm.setString(2,name);
            stm.setString(3,address);
            stm.setString(4,contact);
            stm.setInt(5,role_no);
            stm.setString(6, username);
            stm.setString(7, password);
            stm.execute();
        } catch (Exception e){
            System.out.println("Error updating employee list.");
        };
        LoadData();
    }//GEN-LAST:event_addEmployeeActionPerformed

    private void editEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEmployeeActionPerformed
        int id = Integer.parseInt(empID.getText());
        String name = empName.getText();
        String contact = empPhone.getText();
        String address = empAdd.getText();
        int role_no = roles.get((String)cbRoles.getSelectedItem());
        String username = empUname.getText();
        String password = empPword.getText();
        try {
            String query = "UPDATE employees SET EMPLOYEE_NAME='"+name+"', EMPLOYEE_ADD='"+address+"', EMPLOYEE_PHONE='"+contact+"', ROLE_NO="+role_no+", EMP_UNAME='"+username+"', EMP_PWORD='"+password+"' WHERE EMPLOYEE_ID="+id;
            stm = connection.prepareStatement(query);
            stm.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
        };
        LoadData();
    }//GEN-LAST:event_editEmployeeActionPerformed

    private void searchEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchEmployeeActionPerformed
        // TODO add your handling code here:
        String code = empID.getText();
        DefaultTableModel tbEmployee = (DefaultTableModel) tblEmployees.getModel();
        tbEmployee.setRowCount(0);
        try {
            stm = connection.prepareStatement("SELECT * FROM employees inner join roles on roles.ROLE_NO = employees.ROLE_NO where EMPLOYEE_ID='"+code.trim()+"'");
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("EMPLOYEE_ID"), result.getString("EMPLOYEE_NAME"), result.getString("EMPLOYEE_PHONE"), result.getString("EMPLOYEE_ADD"), result.getString("ROLE_NO")};
                tbEmployee.addRow(row);
                empID.setText(String.valueOf(result.getInt("EMPLOYEE_ID")));
                empName.setText(result.getString("EMPLOYEE_NAME"));
                empPhone.setText(result.getString("EMPLOYEE_PHONE"));
                empAdd.setText(result.getString("EMPLOYEE_ADD"));
                cbRoles.setSelectedItem((String) result.getString("ROLE_DESCRIPTION"));
                empUname.setText(result.getString("EMP_UNAME"));
                empPword.setText(result.getString("EMP_PWORD"));
            }
        } catch (Exception e){
            e.printStackTrace();
        };
        
    }//GEN-LAST:event_searchEmployeeActionPerformed

    private void searchRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchRoleActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String code = roleno.getText();
        DefaultTableModel tbRoles = (DefaultTableModel) tblRoles.getModel();
        tbRoles.setRowCount(0);
        try {
            stm = connection.prepareStatement("SELECT * FROM roles where ROLE_NO="+code.trim());
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("ROLE_NO"), result.getString("ROLE_DESCRIPTION")};
                tbRoles.addRow(row);
            }
        } catch (Exception e){
            System.out.println("Error fetching roles list.");
        };
    }//GEN-LAST:event_searchRoleActionPerformed

    private void deleteEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEmployeeActionPerformed
        // TODO add your handling code here:
        String employeeID = empID.getText();
        try {
            stm = connection.prepareStatement("DELETE FROM employees WHERE EMPLOYEE_ID="+employeeID);
            System.out.println(employeeID);
            stm.execute();
        } catch (Exception e){
            System.out.println("Error updating employee list.");
        };
        LoadData();
    }//GEN-LAST:event_deleteEmployeeActionPerformed

    private void addRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoleActionPerformed
        // TODO add your handling code here:
        int role_no = Integer.parseInt(roleno.getText());
        String desc = role_desc.getText();
        try{
            stm = connection.prepareStatement("INSERT INTO roles VALUES (?,?)");
            stm.setInt(1, role_no);
            stm.setString(2,desc);
            stm.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        LoadData();
    }//GEN-LAST:event_addRoleActionPerformed

    private void deleteRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRoleActionPerformed
        // TODO add your handling code here:
        int role_no = Integer.parseInt(roleno.getText());
        String desc = role_desc.getText();
        try{
            stm = connection.prepareStatement("DELETE FROM roles WHERE ROLE_NO="+role_no);
            stm.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        LoadData();
    }//GEN-LAST:event_deleteRoleActionPerformed

    private void editRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRoleActionPerformed
        // TODO add your handling code here:
        int role_no = Integer.parseInt(roleno.getText());
        String desc = role_desc.getText();
        try{
            stm = connection.prepareStatement("UPDATE roles SET ROLE_DESCRIPTION='"+desc+"' WHERE ROLE_NO="+role_no);
            stm.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        LoadData();
    }//GEN-LAST:event_editRoleActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductActionPerformed
        // TODO add your handling code here:
        int code = Integer.parseInt(prod_code.getText());
        try {
            stm = connection.prepareStatement("DELETE FROM products WHERE PROD_CODE=?");
            stm.setInt(1, code);
            stm.execute();
        } catch (Exception e){
            System.out.println("Error fetching product list.");
        };
        LoadData();
    }//GEN-LAST:event_deleteProductActionPerformed

    private void empIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empIDActionPerformed

    private void btnAllOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllOrdersActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbOrders = (DefaultTableModel) tblOrder.getModel();
        tbOrders.setRowCount(0);
        java.util.Date dt = new java.util.Date();
        String query = "SELECT * FROM chanchicken.order";
        
        
        try {
            System.out.println(query);
            stm = connection.prepareStatement(query);
            result = stm.executeQuery();
            while(result.next()){
                Object[] row = {result.getInt("ORDER_NUM"),result.getInt("PROD_CODE"), result.getInt("PROD_QTY"), result.getDouble("PRICE")};
                tbOrders.addRow(row);
            }
        } catch (Exception e){
            System.out.println("Error fetching orders. " + e.getMessage());
        }
    }//GEN-LAST:event_btnAllOrdersActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    void setEmployeeID(String string) {
        employeeID = string;
        System.out.println("User:" + employeeID);
        getUserInfo();
        LoadTabs();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addEmployee;
    private javax.swing.JButton addProduct;
    private javax.swing.JButton addProductToOrder;
    private javax.swing.JButton addRole;
    private javax.swing.JButton btnAllOrders;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbRoles;
    private javax.swing.JTextField custName;
    private javax.swing.JTextField custName_invoice;
    private javax.swing.JButton deleteEmployee;
    private javax.swing.JButton deleteProduct;
    private javax.swing.JButton deleteRole;
    private javax.swing.JButton editEmployee;
    private javax.swing.JButton editProduct;
    private javax.swing.JButton editRole;
    private javax.swing.JTextArea empAdd;
    private javax.swing.JTextField empID;
    private javax.swing.JTextField empName;
    private javax.swing.JTextField empPhone;
    private javax.swing.JTextField empPword;
    private javax.swing.JTextField empUname;
    private javax.swing.JTextField employeeID_invoice;
    private javax.swing.JTextField employeeName_invoice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_IDNo;
    private javax.swing.JLabel lbl_UserName;
    private javax.swing.JLabel lbl_UserRole;
    private javax.swing.JTextField ordernum_invoice;
    private javax.swing.JTextField ordernum_order;
    private javax.swing.JToggleButton ordersToday;
    private javax.swing.JPanel panelEmployees;
    private javax.swing.JPanel panelInvoice;
    private javax.swing.JPanel panelOrder;
    private javax.swing.JPanel panelPOS;
    private javax.swing.JPanel panelProducts;
    private javax.swing.JPanel panelRoles;
    private javax.swing.JButton pay;
    private javax.swing.JTable posOrderList;
    private javax.swing.JTextField posPrice;
    private javax.swing.JTextField posProdCode;
    private javax.swing.JTable posProdList;
    private javax.swing.JTextField posQty;
    private javax.swing.JTextField prod_code;
    private javax.swing.JTextField prod_desc;
    private javax.swing.JTextField prod_price;
    private javax.swing.JTextField prod_stock;
    private javax.swing.JTextField role_desc;
    private javax.swing.JTextField roleno;
    private javax.swing.JButton searchEmployee;
    private javax.swing.JButton searchInvoice;
    private javax.swing.JButton searchOrder;
    private javax.swing.JButton searchProduct;
    private javax.swing.JButton searchRole;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTable tblOrderInvoice;
    private javax.swing.JTable tblOrderList;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTable tblRoles;
    private javax.swing.JTextField totalAmt;
    private javax.swing.JTextField transactionDate;
    // End of variables declaration//GEN-END:variables

    
}
