import oracle.net.aso.f;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

/**********************Login Page GUI***********************/
class LoginPage extends JFrame {

    LoginPage(){

        //Create title:Coupon Inventory System.
        JLabel title = new JLabel("Retailer System", JLabel.CENTER);
        title.setFont(new Font("Calibri", Font.BOLD,40));

        JLabel IDLabel = new JLabel("ID: ", JLabel.CENTER);
        IDLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField IDText = new JTextField(15);
        IDText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField passwordText = new JPasswordField(15);
        passwordText.setFont(new Font("Calibri", Font.PLAIN,20));

        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton signUpButton = new JButton("SIGN UP");
        signUpButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JRadioButton customer = new JRadioButton("Customer");
        customer.setFont(new Font("Calibri", Font.PLAIN,20));
        JRadioButton employee = new JRadioButton("Employee");
        employee.setFont(new Font("Calibri", Font.PLAIN,20));

        //Set Radio button group
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(customer);
        buttonGroup.add(employee);

        JPanel panel1 = new JPanel(new GridLayout(2, 2, 10, 10));
        panel1.add(IDLabel);
        panel1.add(IDText);
        panel1.add(passwordLabel);
        panel1.add(passwordText);

        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        panel2.add(customer);
        panel2.add(employee);

        JPanel panel3 = new JPanel(new GridLayout(1, 3, 30, 10));
        panel3.add(loginButton);
        panel3.add(signUpButton);
        panel3.add(exitButton);

        title.setBounds(200,100,300,50);
        panel1.setBounds(180,300, 300,100);
        panel2.setBounds(225,420, 250,40);
        panel3.setBounds(150,500,400,40);

        add(title);
        add(panel1);
        add(panel2);
        add(panel3);

        //Set Window
        this.setTitle("Login");
        this.setLayout(null);
        this.setBounds(500,200,700,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        Function fn = new Function();

        loginButton.addActionListener(e -> {

            String ID = IDText.getText();
            String password = passwordText.getText();
            //ID = "a@a";
            //password = "a";

            if(ID.equals("") || password.equals("")){
                new showError("Please fill in all fields");
                return;
            }
            if(!Function.isValidEmailAddress(ID)){
                new showError("Invalid Email");
                return;
            }
            //System.out.println("id="+ID);
            //System.out.println("password="+password);
            if (customer.isSelected()){
                //TODO add your function here
                if(fn.login_cust(ID, password)){
                    new CustomerViewMainMenu(fn);
                    setVisible(false);
                }
                else{
                    new showError("Account does not exist");
                }
            }else if (employee.isSelected()){
                //TODO add your function here
                if(fn.login_emp(ID, password)){
                    new EmployeeViewMainMenuPage(fn);
                    setVisible(false);
                }
                else{
                    new showError("Account does not exist");
                }
            }else{
                //Show message.
                new showError("Please select user type!");
                return;
            }
        });

        signUpButton.addActionListener(e -> {
            new SignUpPage();
            setVisible(false);
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}//End LoginPage

/**********************SignUp Page GUI***********************/
class SignUpPage extends JFrame{

    SignUpPage(){

        JButton createButton = new JButton("Create");
        createButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JLabel emailLabel = new JLabel("E-Mail Address: ", JLabel.CENTER);
        emailLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField emailText = new JTextField(15);
        emailText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel passwordLabel = new JLabel("Password: ",JLabel.CENTER);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField passwordText = new JPasswordField(15);
        passwordText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField confirmPasswordText = new JPasswordField(15);
        confirmPasswordText.setFont(new Font("Calibri", Font.PLAIN,20));

        JRadioButton customer = new JRadioButton("Customer");
        customer.setFont(new Font("Calibri", Font.PLAIN,20));
        JRadioButton employee = new JRadioButton("Employee");
        employee.setFont(new Font("Calibri", Font.PLAIN,20));

        //Set Radio button group
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(customer);
        buttonGroup.add(employee);

        JPanel panel1 = new JPanel(new GridLayout(3, 2, 10, 10));
        panel1.add(emailLabel);
        panel1.add(emailText);
        panel1.add(passwordLabel);
        panel1.add(passwordText);
        panel1.add(confirmPasswordLabel);
        panel1.add(confirmPasswordText);

        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        panel2.add(customer);
        panel2.add(employee);

        JPanel panel3 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel3.add(createButton);
        panel3.add(cancelButton);

        panel1.setBounds(150,100,400,150);
        panel2.setBounds(220,280,280,40);
        panel3.setBounds(225,350,250,40);

        add(panel1);
        add(panel2);
        add(panel3);

        //Set Window
        this.setTitle("Sign Up");
        this.setBounds(500,200,700,500);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        Function fn = new Function();

        createButton.addActionListener(e -> {
            String email = emailText.getText();
            String password = passwordText.getText();
            String confirmPassword = confirmPasswordText.getText();

            //TODO, add your function here.
            if(email.equals("") || password.equals("") || confirmPassword.equals("")){
                new showError("Please fill in all fields");
                return;
            }
            if(!Function.isValidEmailAddress(email)){
                new showError("Invalid Email");
                return;
            }
            if(!confirmPassword.equals(password)){
                new showError("Both password fields should be the same");
                return;
            }
            if (customer.isSelected()){
                //TODO add your function here
                if(fn.isAccountExist(email, "customers")){
                    new showError("Account with email already exists");
                }
                else{
                    if(fn.createnew_customer(email, password)) {
                        new showSuccess("Account created");
                        new LoginPage();
                        this.dispose();
                    }
                }
            }else if (employee.isSelected()){
                //TODO add your function here
                if(fn.isAccountExist(email, "employees")){
                    new showError("Account with email already exists");
                }
                else{
                    if(fn.createnew_employee(email, password)) {
                        new showSuccess("Account created");
                        new LoginPage();
                        this.dispose();
                    }
                }
            }else{
                //Show message.
                new showError("Please select user type!");
                return;
            }
        });

        cancelButton.addActionListener(e -> {
            new LoginPage();
            setVisible(false);
        });
    }
}//End SignUpPage

/**********************CustomerViewMainMenu Page GUI***********************/
class CustomerViewMainMenu extends JFrame{
    private JPanel loginSecurityPanel, editButtonPanel;
    private JScrollPane scrollPane;
    private JLabel yourOrdersLabel, loginSecurityLabel;

    CustomerViewMainMenu(Function fn){

        JLabel title = new JLabel("Welcome");
        title.setFont(new Font("Calibri", Font.BOLD,40));
        title.setBounds(400,100,200,40);
        add(title);

        /**********************Menu***********************/
        JMenuBar menuBar = new JMenuBar();
        JMenu account = new JMenu("Account");
        account.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenu search = new JMenu("Search");
        search.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenu orders = new JMenu("Orders");
        orders.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenuItem cart = new JMenuItem("Cart");
        cart.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenuItem yourOrders = new JMenuItem("Your orders");
        yourOrders.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenuItem loginSecurity = new JMenuItem("Login & security");
        loginSecurity.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenuItem address = new JMenuItem("Address");
        address.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenuItem payment = new JMenuItem("Payment options");
        payment.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenuItem signOut = new JMenuItem("Not you? Sign Out");
        signOut.setFont(new Font("Calibri", Font.PLAIN,20));

        account.add(yourOrders);
        account.add(loginSecurity);
        account.add(address);
        account.add(payment);
        account.add(signOut);

        orders.add(yourOrders);
        orders.add(cart);

        menuBar.add(account);
        menuBar.add(search);
        menuBar.add(orders);
        //End Menu page


        /**********************Orders page***********************/
        yourOrdersLabel = new JLabel("Your Orders");
        yourOrdersLabel.setFont(new Font("Calibri", Font.BOLD,30));

        // Order Columns of table
        String[] orderColumns = {"Order number", "Name of Product", "Quantity", "Amount paid", "Purchase date"};

        //Order Data of table
        //String orderData[][] = {{},{}};
                //TODO get order data from database

        List<order> o_list = fn.getOrderList();
        String orderData[][] = new String[o_list.size()][];
        for(int i = 0; i < o_list.size(); i++){
            orderData[i] = new String[5];
            orderData[i][0] = o_list.get(i).orderid.toString();
            orderData[i][1] = o_list.get(i).pName.toString();
            orderData[i][2] = o_list.get(i).qty.toString();
            orderData[i][3] = o_list.get(i).amount.toString();
            orderData[i][4] = o_list.get(i).date.toString();
        }
        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(orderData, orderColumns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTable orderTable = new JTable();
        orderTable.setModel(tableModel);

        //Set center alignment for table
        ((DefaultTableCellRenderer) orderTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int)JTable.CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        orderTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int i = 0; i < orderTable.getColumnCount(); i++)
            orderTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(0,100,995,600);
        scrollPane.setVisible(false);

        yourOrdersLabel.setBounds(50,0,400,100);
        yourOrdersLabel.setVisible(false);

        add(yourOrdersLabel);
        add(scrollPane);
        //End orders page

        /**********************Login & security page***********************/
        loginSecurityLabel = new JLabel("Login & security");
        loginSecurityLabel.setFont(new Font("Calibri", Font.BOLD,30));

        //JLabel idLabel = new JLabel("ID:");
        //idLabel.setFont(new Font("Calibri", Font.BOLD,20));
        //JLabel idText = new JLabel("example");   //TODO get customer's ID from database
        //idText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel emailLabel = new JLabel("E-mail address:");
        emailLabel.setFont(new Font("Calibri", Font.BOLD,20));
        JLabel emailText = new JLabel(fn.user_details.email);   //TODO get customer's email from database
        emailText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Calibri", Font.BOLD,20));
        JLabel passwordText = new JLabel("**********");
        passwordText.setFont(new Font("Calibri", Font.PLAIN,20));

        JButton editEmailButton = new JButton("Edit");
        editEmailButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton editPasswordButton = new JButton("Edit");
        editPasswordButton.setFont(new Font("Calibri", Font.PLAIN,20));

        editButtonPanel = new JPanel(new GridLayout(2,1,0,60));
        editButtonPanel.add(editEmailButton);
        editButtonPanel.add(editPasswordButton);
        editButtonPanel.setBounds(600,160,100,130);
        editButtonPanel.setVisible(false);

        loginSecurityPanel = new JPanel(new GridLayout(3,2));
        //loginSecurityPanel.add(idLabel);
        //loginSecurityPanel.add(idText);
        loginSecurityPanel.add(emailLabel);
        loginSecurityPanel.add(emailText);
        loginSecurityPanel.add(passwordLabel);
        loginSecurityPanel.add(passwordText);
        loginSecurityPanel.setVisible(false);

        loginSecurityPanel.setBounds(200,130,400,300);
        loginSecurityLabel.setBounds(50,0,300,100);
        loginSecurityLabel.setVisible(false);

        add(loginSecurityLabel);
        add(loginSecurityPanel);
        add(editButtonPanel);

        editEmailButton.addActionListener(e -> {
            new EditCustomerEmailPage(fn, emailText);

        });

        editPasswordButton.addActionListener(e -> {
            new EditCustomerPasswordPage(fn);
        });


        /**********************Search page***********************/
        JLabel searchTitle = new JLabel("Search products");
        searchTitle.setFont(new Font("Calibri", Font.BOLD,40));
        searchTitle.setBounds(350,100,300,40);
        searchTitle.setVisible(false);
        add(searchTitle);

        JTextField searchText = new JTextField(20);
        searchText.setFont(new Font("Calibri", Font.PLAIN,20));
        searchText.setBounds(300,300,400,40);
        searchText.setVisible(false);
        add(searchText);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Calibri", Font.PLAIN,20));
        searchButton.setBounds(750,300,100,40);
        searchButton.setVisible(false);
        add(searchButton);

        searchButton.addActionListener(e -> {
            new SearchProductPage(fn, searchText.getText());

            setVisible(false);
        });
        //End Search Page

        //Set Window
        this.setTitle("Main menu");
        this.setJMenuBar(menuBar);
        this.setBounds(500,200,1000,700);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        search.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                searchTitle.setVisible(true);
                searchText.setVisible(true);
                searchButton.setVisible(true);

                title.setVisible(false);
                scrollPane.setVisible(false);
                yourOrdersLabel.setVisible(false);
                loginSecurityLabel.setVisible(false);
                loginSecurityPanel.setVisible(false);
                editButtonPanel.setVisible(false);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                //Don't add code here
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                //Don't add code here
            }
        });

        cart.addActionListener(e -> {
            new CartPage(fn);
            setVisible(false);
        });

        yourOrders.addActionListener(e -> {
            scrollPane.setVisible(true);
            yourOrdersLabel.setVisible(true);

            title.setVisible(false);
            title.setVisible(false);
            searchTitle.setVisible(false);
            searchText.setVisible(false);
            searchButton.setVisible(false);
            loginSecurityLabel.setVisible(false);
            loginSecurityPanel.setVisible(false);
            editButtonPanel.setVisible(false);

        });

        loginSecurity.addActionListener(e -> {
            title.setVisible(false);
            title.setVisible(false);
            searchTitle.setVisible(false);
            searchText.setVisible(false);
            searchButton.setVisible(false);
            scrollPane.setVisible(false);
            yourOrdersLabel.setVisible(false);


            loginSecurityLabel.setVisible(true);
            loginSecurityPanel.setVisible(true);
            editButtonPanel.setVisible(true);


        });

        address.addActionListener(e -> {
            new AddressPage(fn);
        });

        payment.addActionListener(e -> {
            new CreditCardPage(fn);
        });

        signOut.addActionListener(e -> {
            //setVisible(false);
            new LoginPage();
            this.dispose();
        });

    }
}//End CustomerViewMainMenuPage

/**********************SearchProduct Page GUI***********************/
class SearchProductPage extends JFrame{

    SearchProductPage(Function fn, String searchtxt){
        JLabel result = new JLabel("Result:");
        result.setFont(new Font("Calibri", Font.BOLD,30));
        result.setBounds(50,0,400,100);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        quantityLabel.setBounds(800,450,100,40);
        add(quantityLabel);

        Integer[] quantity = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox quantityComboBox = new JComboBox(quantity);
        quantityComboBox.setFont(new Font("Calibri", Font.PLAIN,20));
        quantityComboBox.setBounds(900,450,50,40);
        add(quantityComboBox);

        JButton addButton = new JButton("Add to cart");
        addButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel = new JPanel(new GridLayout(2,1,10,10));
        panel.add(addButton);
        panel.add(cancelButton);
        panel.setBounds(800,520,170,100);
        add(panel);

        //Columns of table
        String[] columns = {"UPC", "Name", "Price", "Tax(%)", "Brand", "Store", "Qty"};

        List<Product> res = fn.getSearchResults(searchtxt);
        //Data of product
        //TODO get data of products from database
        //String data[][] = {{}, {}, {}};

        String data[][] = new String[res.size()][];
        for(int i = 0; i < res.size(); i++){
            data[i] = new String[7];
            data[i][0] = res.get(i).upc;
            data[i][1] = res.get(i).prodName;
            data[i][2] = res.get(i).price.toString();
            data[i][3] = res.get(i).tax.toString();
            data[i][4] = res.get(i).brand;
            data[i][5] = res.get(i).storeName;
            data[i][6] = res.get(i).quantity.toString();
        }

        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTable table = new JTable();
        table.setModel(tableModel);

        //Set center alignment for table
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int)JTable.CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,995,300);

        add(result);
        add(scrollPane);
        //Set Window
        this.setTitle("Search products");
        this.setBounds(500,200,1000,700);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        addButton.addActionListener(e -> {
            int selectRow = table.getSelectedRow();
            if (selectRow != -1) {
                //TODO add your function here
                String upc = table.getModel().getValueAt(selectRow, 0).toString();
                int qty =  quantity[quantityComboBox.getSelectedIndex()];
                if(fn.addToCart(upc, qty)){
                    new showSuccess("Product addeed");
                }

            }else{
                new showError("Please select a product!");
            }

        });

        cancelButton.addActionListener(e -> {
            new CustomerViewMainMenu(fn);
            //setVisible(false);
            this.dispose();
        });
    }
}//End SearchProductPage

/**********************Cart Page GUI***********************/
class CartPage extends JFrame{

    CartPage(Function fn){

        JLabel cartTitle = new JLabel("Your cart:");
        cartTitle.setFont(new Font("Calibri", Font.BOLD,30));
        cartTitle.setBounds(50,0,400,100);
        add(cartTitle);

        JLabel subtotalLabel = new JLabel("Subtotal:");
        subtotalLabel.setFont(new Font("Calibri", Font.BOLD,30));
        JLabel subtotalText = new JLabel();   //TODO calculate subtotal
        subtotalText.setFont(new Font("Calibri", Font.PLAIN,30));

        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panel2.add(subtotalLabel);
        panel2.add(subtotalText);
        panel2.setBounds(100,420,280,40);
        add(panel2);

        JButton checkOutButton = new JButton("Check out");
        checkOutButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton modifyButton = new JButton("Modify quantity");
        modifyButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel = new JPanel(new GridLayout(1,4,10,10));
        panel.add(checkOutButton);
        panel.add(modifyButton);
        panel.add(removeButton);
        panel.add(cancelButton);
        panel.setBounds(150,520,700,40);
        add(panel);

        JTable table = new JTable();
        table.setModel(populateCartItems(fn, subtotalText));

        //Set center alignment for table
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int)JTable.CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,995,300);

        add(scrollPane);
        //Set Window
        this.setTitle("Your cart");
        this.setBounds(500,200,1000,700);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        checkOutButton.addActionListener(e -> {
            if (table.getRowCount() == 0){
                new showError("No products to checkout");
                return;
            }
            List<Product> list = fn.checkQuantityValid();
            if(list.size() > 0){
                new showError("Product '" + list.get(0).upc + "' has only " + list.get(0).quantity + " in stock");
                return;
            }

            List<CreditCard> listcc = fn.getCreditCardList();
            if(listcc.size() <= 0){
                new MissingDataPage(fn, "cc");
                return;
            }
            if(fn.user_details.f_name.equals("")){
                new MissingDataPage(fn, "adr");
                return;
            }
            fn.user_details.cart = fn.getCartItems();


            new CheckoutPage(fn, table, subtotalText, listcc);
        });

        modifyButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow < 0){
                new showError("No product selected");
                return;
            }
            String upc = table.getModel().getValueAt(selectedRow, 0).toString();
            new EditQuantityPage(fn, upc, table, selectedRow, subtotalText);

        });

        removeButton.addActionListener(e -> {
            //TODO add your function here
            int selectedRow = table.getSelectedRow();
            if(selectedRow < 0){
                new showError("No product selected");
                return;
            }
            String upc = table.getModel().getValueAt(selectedRow, 0).toString();
            if(fn.removeFromCart(upc)){
                table.setModel(populateCartItems(fn, subtotalText));
            }
        });

        cancelButton.addActionListener(e -> {
            new CustomerViewMainMenu(fn);
            this.dispose();
        });
    }

    public static DefaultTableModel populateCartItems(Function fn, JLabel subtotalText){
        Float totalPrice = Float.parseFloat("0");
        //Columns of table
        String[] columns = {"UPC", "Name", "Price per unit", "Tax(%)", "Quantity", "Brand"};

        //Data of products
        //TODO get data of products from database
        //String data[][] = {{},{},{}};
        List<Product> lst = fn.getCartItems();
        String data[][] = new String[lst.size()][];
        for(int i = 0; i < lst.size(); i++){
            totalPrice += Float.parseFloat(String.format("%.2f", ((float)lst.get(i).price + (lst.get(i).price * ((float)lst.get(i).tax/(float)100))) * lst.get(i).quantity));
            data[i] = new String[6];
            data[i][0] = lst.get(i).upc;
            data[i][1] = lst.get(i).prodName;
            data[i][2] = lst.get(i).price.toString();
            data[i][3] = lst.get(i).tax.toString();
            data[i][4] = lst.get(i).quantity.toString();
            data[i][5] = lst.get(i).brand;
        }
        subtotalText.setText(totalPrice.toString());
        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        return tableModel;
    }
}//End CartPage


/**********************Checkout Page GUI***********************/
class CheckoutPage extends JFrame{

    CheckoutPage(Function fn, JTable table, JLabel subtotalText, List<CreditCard> listcc){
        JLabel shoppingAddressLabel = new JLabel("Shipping Address");
        shoppingAddressLabel.setFont(new Font("Calibri", Font.BOLD,30));
        shoppingAddressLabel.setBounds(100,50,300,30);
        add(shoppingAddressLabel);

        JLabel addressText = new JLabel("<html><body><p>" + fn.user_details.f_name + " " + fn.user_details.l_name + "</p><br>" +
                "<p>" + fn.user_details.street + "</p><br><p>" + fn.user_details.apt_num + "</p><br>" +
                "<p>" + fn.user_details.city + ", " + fn.user_details.state + ", " + fn.user_details.country+"</p><br>" +
                "<p>"+fn.user_details.phone+"</p><body></html>");  //TODO get address from database

        addressText.setFont(new Font("Calibri", Font.PLAIN,20));
        addressText.setBounds(100,100,200,300);
        add(addressText);

        //JButton changeAddressButton = new JButton("Change");
        //changeAddressButton.setFont(new Font("Calibri", Font.PLAIN,20));
        //changeAddressButton.setBounds(100,380,100,30);
        //add(changeAddressButton);

        if(listcc.size() > 0) {
            fn.user_details.cc_default = listcc.get(0);
        }

        Float price = Float.parseFloat("0");
        Float tax = Float.parseFloat("0");
        Float totalPrice = Float.parseFloat("0");

        for(int i = 0; i < fn.user_details.cart.size(); i++){
            price += fn.user_details.cart.get(i).price * fn.user_details.cart.get(i).quantity;
            tax += (fn.user_details.cart.get(i).price * ((float)fn.user_details.cart.get(i).tax/(float)100)) * fn.user_details.cart.get(i).quantity;
            totalPrice += ((float)fn.user_details.cart.get(i).price + (fn.user_details.cart.get(i).price * ((float)fn.user_details.cart.get(i).tax/(float)100))) * fn.user_details.cart.get(i).quantity;
        }
        tax = Float.parseFloat(String.format("%.2f", tax));
        totalPrice = Float.parseFloat(String.format("%.2f", totalPrice));

        JLabel creditCardLabel = new JLabel("Credit card");
        creditCardLabel.setFont(new Font("Calibri", Font.BOLD,30));
        creditCardLabel.setBounds(100,500,200,30);
        add(creditCardLabel);

        JLabel creditCardText;
        if(listcc.size() > 0) {
            creditCardText = new JLabel("Ending with " + fn.user_details.cc_default.cc_numb.substring(fn.user_details.cc_default.cc_numb.length() - 4));  //TODO get ending number from database
        }
        else{
            creditCardText = new JLabel("No card added");
        }

        creditCardText.setFont(new Font("Calibri", Font.PLAIN,20));
        creditCardText.setBounds(100,550,150,30);
        add(creditCardText);

        JButton changeCreditCardButton = new JButton("Change");
        changeCreditCardButton.setFont(new Font("Calibri", Font.PLAIN,20));
        changeCreditCardButton.setBounds(100,600,100,30);
        add(changeCreditCardButton);

        JLabel orderSummeryLabel = new JLabel("Order Summery");
        orderSummeryLabel.setFont(new Font("Calibri", Font.BOLD,30));
        orderSummeryLabel.setBounds(600,50,200,50);
        add(orderSummeryLabel);

        JLabel totalBeforeTaxLabel = new JLabel("Total before tax:");
        totalBeforeTaxLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel estimatedTaxtobeCollectedLabel = new JLabel("<html><body><p>Estimated tax to be collected:</p><body></html>");
        estimatedTaxtobeCollectedLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel orderTotalLabel = new JLabel("Total:");
        orderTotalLabel.setFont(new Font("Calibri", Font.BOLD,30));

        JLabel totalBeforeTaxText = new JLabel(price.toString());  //TODO calculate total before tax from database
        totalBeforeTaxText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel estimatedTaxtobeCollectedText = new JLabel(tax.toString()); //TODO calculate estimated tax to be collected from database
        estimatedTaxtobeCollectedText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel orderTotalText = new JLabel(totalPrice.toString());  //TODO calculate order total from database
        orderTotalText.setFont(new Font("Calibri", Font.BOLD,30));
        orderTotalText.setForeground(Color.red);

        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(totalBeforeTaxLabel);
        panel.add(totalBeforeTaxText);
        panel.add(estimatedTaxtobeCollectedLabel);
        panel.add(estimatedTaxtobeCollectedText);
        panel.add(orderTotalLabel);
        panel.add(orderTotalText);
        panel.setBounds(600,100,300,250);
        add(panel);

        JButton placeOrderButton = new JButton("Place your order");
        placeOrderButton.setFont(new Font("Calibri", Font.BOLD,20));
        placeOrderButton.setBounds(600,380,240,50);
        add(placeOrderButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));
        cancelButton.setBounds(600,450,100,35);
        add(cancelButton);

        //Set Window
        this.setTitle("Checkout");
        this.setBounds(500,200,1000,800);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        /*changeAddressButton.addActionListener(e -> {
            new SelectAddressPage();
        });*/

        changeCreditCardButton.addActionListener(e -> {
            new SelectCreditCardPage(fn, listcc, creditCardText);
        });

        placeOrderButton.addActionListener(e -> {
            //TODO add your function here
            if(fn.placeOrder()){
                table.setModel(CartPage.populateCartItems(fn, subtotalText));
                new showSuccess("Your order has been placed");
                this.dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            this.dispose();
        });



    }
}//End CheckoutPage

/**********************SelectAddress Page GUI***********************/
class SelectAddressPage extends JFrame {

    SelectAddressPage(){

        JLabel selectAddressLabel = new JLabel("Select an address:");
        selectAddressLabel.setFont(new Font("Calibri", Font.BOLD,30));
        selectAddressLabel.setBounds(50,0,400,100);
        add(selectAddressLabel);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.add(confirmButton);
        panel.add(cancelButton);
        panel.setBounds(375,350, 250,40);
        add(panel);

        //Columns of table
        String[] columns = {"Customer ID", "First name", "Last name",
                "Street", "Apartment", "City", "State", "ZIP code", "Country", "Phone"};

        //Data of Address
        //TODO get data of addresses from database
        String data[][] = {
                {},
                {},
                {},
        };

        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTable table = new JTable();
        table.setModel(tableModel);

        //Set center alignment for table
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int)JTable.CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,995,200);
        add(scrollPane);

        //Set Window
        this.setTitle("Select address");
        this.setLayout(null);
        this.setBounds(500,200,1000,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        confirmButton.addActionListener(e -> {
            //TODO add your function here
        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End SelectAddressPage


/**********************SelectCreditCard Page GUI***********************/
class SelectCreditCardPage extends JFrame {

    SelectCreditCardPage(Function fn, List<CreditCard> cclist, JLabel creditCardText){

        JLabel selectAddressLabel = new JLabel("Select a credit card:");
        selectAddressLabel.setFont(new Font("Calibri", Font.BOLD,30));
        selectAddressLabel.setBounds(50,0,400,100);
        add(selectAddressLabel);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.add(confirmButton);
        panel.add(cancelButton);
        panel.setBounds(375,350, 250,40);
        add(panel);

        //Columns of table
        String[] columns = {"Name on card", "Card number", "Expiration date", "CVV"};

        //Data of Credit card
        //TODO get data of credit card from database

        String data[][] = new String[cclist.size()][];
        for(int i = 0; i < cclist.size(); i++){
            data[i] = new String[4];
            data[i][0] = cclist.get(i).cc_name;
            data[i][1] = cclist.get(i).cc_numb;
            data[i][2] = cclist.get(i).cc_exp_date;
            data[i][3] = cclist.get(i).cc_cvv;
        }


        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTable table = new JTable();
        table.setModel(tableModel);

        //Set center alignment for table
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int)JTable.CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,995,200);
        add(scrollPane);

        //Set Window
        this.setTitle("Select credit card");
        this.setLayout(null);
        this.setBounds(500,200,1000,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        confirmButton.addActionListener(e -> {
            //TODO add your function here
            Integer selectedRow = table.getSelectedRow();
            if(selectedRow < 0){
                new showError("No card selected");
                return;
            }
            else{
                CreditCard cc = new CreditCard();
                cc.cc_name = table.getModel().getValueAt(selectedRow, 0).toString();
                cc.cc_numb = table.getModel().getValueAt(selectedRow, 1).toString();
                cc.cc_exp_date = table.getModel().getValueAt(selectedRow, 2).toString();
                cc.cc_cvv = table.getModel().getValueAt(selectedRow, 3).toString();
                fn.user_details.cc_default = cc;
                creditCardText.setText("Ending with " + fn.user_details.cc_default.cc_numb.substring(fn.user_details.cc_default.cc_numb.length() - 4));
                this.dispose();
            }


        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End SelectCreditCardPage


/**********************EditEmail Page GUI***********************/
class EditQuantityPage extends JFrame {

    EditQuantityPage(Function fn, String upc, JTable table, int index, JLabel subtotalText){

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField quantityText = new JTextField(25);
        quantityText.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel1.add(quantityLabel);
        panel1.add(quantityText);


        JButton saveChangeButton = new JButton("Save change");
        saveChangeButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(saveChangeButton);
        panel2.add(cancelButton);

        panel1.setBounds(150,100, 200,40);
        panel2.setBounds(100,200, 300,40);

        add(panel1);
        add(panel2);

        //Set Window
        this.setTitle("Edit quantity");
        this.setLayout(null);
        this.setBounds(500,200,500,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        saveChangeButton.addActionListener(e -> {
            Integer newQuantity = Integer.parseInt(quantityText.getText());

            //TODO add your function here.
            if(!newQuantity.toString().equals("")) {
                if (newQuantity == 0) {
                    if(fn.removeFromCart(upc)){
                        table.setModel(CartPage.populateCartItems(fn, subtotalText));
                    }
                } else {
                    if (fn.updateCartProductQuantity(newQuantity, upc)) {
                        table.setModel(CartPage.populateCartItems(fn, subtotalText));
                    }
                }
                this.dispose();
            }
        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End EditQuantityPage



/**********************EditCustomerEmail Page GUI***********************/
class EditCustomerEmailPage extends JFrame {

    EditCustomerEmailPage(Function fn, JLabel emailText){

        JLabel oldEmailLabel = new JLabel("Old email address:");
        oldEmailLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel oldEmailText = new JLabel(fn.user_details.email);   //TODO  get customer's email from database
        oldEmailText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel newEmailLabel = new JLabel("New email address:");
        newEmailLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField newEmailText = new JTextField(25);
        newEmailText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel confirmEmailLabel = new JLabel("Re-enter new email:");
        confirmEmailLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField confirmEmailText = new JTextField(25);
        confirmEmailText.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(3, 2, 10, 10));
        panel1.add(oldEmailLabel);
        panel1.add(oldEmailText);
        panel1.add(newEmailLabel);
        panel1.add(newEmailText);
        panel1.add(confirmEmailLabel);
        panel1.add(confirmEmailText);

        JButton saveChangeButton = new JButton("Save change");
        saveChangeButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(saveChangeButton);
        panel2.add(cancelButton);

        panel1.setBounds(100,100, 500,150);
        panel2.setBounds(200,300, 300,40);

        add(panel1);
        add(panel2);

        //Set Window
        this.setTitle("Edit Email");
        this.setLayout(null);
        this.setBounds(500,200,700,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        saveChangeButton.addActionListener(e -> {
            String newEmail = newEmailText.getText();
            String confirmEmail = confirmEmailText.getText();

            //TODO add your function here.
            if(newEmail.equals("") || confirmEmail.equals("")){
                new showError("Please fill in all fields");
                return;
            }
            if(Function.isValidEmailAddress(newEmail) && Function.isValidEmailAddress(confirmEmail)) {
                if (!confirmEmail.equals(newEmail)) {
                    new showError("Both email fields should be the same");
                    return;
                }
                if (fn.updateEmail_cust(newEmail)) {
                    new showSuccess("Email updated");
                    emailText.setText(newEmail);
                    //setVisible(false);
                    this.dispose();
                }
            }
            else{
                new showError("Invalid email");
                return;
            }

        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End EditCustomerEmailPage

/**********************EditCustomerPassword Page GUI***********************/
class EditCustomerPasswordPage extends JFrame {

    EditCustomerPasswordPage(Function fn){

        JLabel currentPasswordLabel = new JLabel("Current password:");
        currentPasswordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField currentPasswordText = new JPasswordField(25);
        currentPasswordText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel newPasswordLabel = new JLabel("New password:");
        newPasswordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField newPasswordText = new JPasswordField(25);
        newPasswordText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel confirmPasswordLabel = new JLabel("Re-enter password:");
        confirmPasswordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField confirmPasswordText = new JPasswordField(25);
        confirmPasswordText.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(3, 2, 10, 10));
        panel1.add(currentPasswordLabel);
        panel1.add(currentPasswordText);
        panel1.add(newPasswordLabel);
        panel1.add(newPasswordText);
        panel1.add(confirmPasswordLabel);
        panel1.add(confirmPasswordText);

        JButton saveChangeButton = new JButton("Save change");
        saveChangeButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(saveChangeButton);
        panel2.add(cancelButton);

        panel1.setBounds(100,100, 500,150);
        panel2.setBounds(200,300, 300,40);

        add(panel1);
        add(panel2);

        //Set Window
        this.setTitle("Edit Password");
        this.setLayout(null);
        this.setBounds(500,200,700,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        saveChangeButton.addActionListener(e -> {
            String currentPassword = currentPasswordText.getText();
            String newPassword = newPasswordText.getText();
            String confirmPassword = confirmPasswordText.getText();

            //TODO add your function here.
            if(currentPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")){
                new showError("Please fill in all fields");
                return;
            }
            if(!currentPassword.equals(fn.user_details.password)){
                new showError("Current password is incorrect");
                return;
            }
            if(!newPassword.equals(confirmPassword)){
                new showError("Both password fields should be the same");
                return;
            }
            if (fn.updatePassword_cust(newPassword)) {
                new showSuccess("Password updated");
                setVisible(false);
            }

        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End EditCustomerPasswordPage

/**********************Address Page GUI***********************/
class AddressPage extends JFrame {

    AddressPage(Function fn){

        JLabel yourAddressLabel = new JLabel("Your address:");
        yourAddressLabel.setFont(new Font("Calibri", Font.BOLD,30));

        //JButton addButton = new JButton("Add");
        //addButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton modifyButton = new JButton("Modify");
        modifyButton.setFont(new Font("Calibri", Font.PLAIN,20));
        //JButton deleteButton = new JButton("Delete");
        //deleteButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JTable table = new JTable();
        if(fn.user_details.f_name.equals("")){
            modifyButton.setText("Add new");
            table.setModel(populateEmptyAddress(fn));
        }
        else {
            table.setModel(populateAddress(fn));
        }
        //Set center alignment for table
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int)JTable.CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,995,200);

        yourAddressLabel.setBounds(50,0,400,100);


        JPanel panel = new JPanel(new GridLayout(1, 1, 10, 10));
        //panel.add(addButton);
        panel.add(modifyButton);
        //panel.add(deleteButton);
        panel.setBounds(300,350,400,40);

        add(yourAddressLabel);
        add(scrollPane);
        add(panel);

        /*addButton.addActionListener(e -> {
            new AddAddressPage();
        });*/

        modifyButton.addActionListener(e -> {
            new EditAddressPage(fn, table, modifyButton);
        });

        /*deleteButton.addActionListener(e -> {

            //TODO add your function here.

        });*/

        //Set Window
        this.setTitle("Your addresses");
        this.setBounds(500,200,1000,500);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static DefaultTableModel populateAddress(Function fn){
        //Columns of table
        String[] columns = {"First name", "Last name",
                "Street", "Apartment", "City", "State", "ZIP code", "Country", "Phone"};

        //Address Data of table
        //TODO get data of addresses from database
        String data[][] = {{fn.user_details.f_name, fn.user_details.l_name, fn.user_details.street,
                fn.user_details.apt_num, fn.user_details.city, fn.user_details.state, fn.user_details.zip,
                fn.user_details.country, fn.user_details.phone}};

        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        return tableModel;
    }
    public static DefaultTableModel populateEmptyAddress(Function fn){
        //Columns of table
        String[] columns = {"First name", "Last name",
                "Street", "Apartment", "City", "State", "ZIP code", "Country", "Phone"};

        //Address Data of table
        //TODO get data of addresses from database
        String data[][] = {};

        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        return tableModel;
    }

}//End AddressPage

/**********************AddAddress Page GUI***********************/
class AddAddressPage extends JFrame {

    AddAddressPage(Function fn){

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField firstNameText = new JTextField(15);
        firstNameText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel lastNameLabel = new JLabel("Last name:");
        lastNameLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField lastNameText = new JTextField(15);
        lastNameText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel streetLabel = new JLabel("Street:");
        streetLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField streetText = new JTextField(20);
        streetText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel apartmentLabel = new JLabel("Apartment:");
        apartmentLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField apartmentText = new JTextField(20);
        apartmentText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField cityText = new JTextField(20);
        cityText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel stateLabel = new JLabel("State:");
        stateLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField stateText = new JTextField(20);
        stateText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel zipCodeLabel = new JLabel("ZIP Code:");
        zipCodeLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField zipCodeText = new JTextField(10);
        zipCodeText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel phoneNumberLabel = new JLabel("Phone number:");
        phoneNumberLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField phoneNumberText = new JTextField(10);
        phoneNumberText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField countryText = new JTextField(20);
        countryText.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(9, 2, 10, 10));
        panel1.add(firstNameLabel);
        panel1.add(firstNameText);
        panel1.add(lastNameLabel);
        panel1.add(lastNameText);
        panel1.add(streetLabel);
        panel1.add(streetText);
        panel1.add(apartmentLabel);
        panel1.add(apartmentText);
        panel1.add(cityLabel);
        panel1.add(cityText);
        panel1.add(stateLabel);
        panel1.add(stateText);
        panel1.add(zipCodeLabel);
        panel1.add(zipCodeText);
        panel1.add(phoneNumberLabel);
        panel1.add(phoneNumberText);
        panel1.add(countryLabel);
        panel1.add(countryText);

        JButton addButton = new JButton("Add address");
        addButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(addButton);
        panel2.add(cancelButton);

        panel1.setBounds(200,50, 400,400);
        panel2.setBounds(250,480, 300,40);

        add(panel1);
        add(panel2);

        //Set Window
        this.setTitle("Add address");
        this.setLayout(null);
        this.setBounds(500,200,800,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        addButton.addActionListener(e -> {
            //TODO add your code here.
            fn.user_details.f_name = firstNameText.getText();
            fn.user_details.l_name = lastNameText.getText();
            fn.user_details.street = streetText.getText();
            fn.user_details.apt_num = apartmentText.getText();
            fn.user_details.city = cityText.getText();
            fn.user_details.state = stateText.getText();
            fn.user_details.zip = zipCodeText.getText();
            fn.user_details.country = countryText.getText();
            fn.user_details.phone = phoneNumberText.getText();

            if(fn.updateAddress()) {
                this.dispose();
            }

        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End AddAddressPage

/**********************EditAddress Page GUI***********************/
class EditAddressPage extends JFrame {

    EditAddressPage(Function fn, JTable table, JButton modifyButton){

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField firstNameText = new JTextField(fn.user_details.f_name);
        firstNameText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel lastNameLabel = new JLabel("Last name:");
        lastNameLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField lastNameText = new JTextField(fn.user_details.l_name);
        lastNameText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel streetLabel = new JLabel("Street:");
        streetLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField streetText = new JTextField(fn.user_details.street);
        streetText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel apartmentLabel = new JLabel("Apartment:");
        apartmentLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField apartmentText = new JTextField(fn.user_details.apt_num);
        apartmentText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField cityText = new JTextField(fn.user_details.city);
        cityText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel stateLabel = new JLabel("State:");
        stateLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField stateText = new JTextField(fn.user_details.state);
        stateText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel zipCodeLabel = new JLabel("ZIP Code:");
        zipCodeLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField zipCodeText = new JTextField(fn.user_details.zip);
        zipCodeText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel phoneNumberLabel = new JLabel("Phone number:");
        phoneNumberLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField phoneNumberText = new JTextField(fn.user_details.phone);
        phoneNumberText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField countryText = new JTextField(fn.user_details.country);
        countryText.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(9, 2, 10, 10));
        panel1.add(firstNameLabel);
        panel1.add(firstNameText);
        panel1.add(lastNameLabel);
        panel1.add(lastNameText);
        panel1.add(streetLabel);
        panel1.add(streetText);
        panel1.add(apartmentLabel);
        panel1.add(apartmentText);
        panel1.add(cityLabel);
        panel1.add(cityText);
        panel1.add(stateLabel);
        panel1.add(stateText);
        panel1.add(zipCodeLabel);
        panel1.add(zipCodeText);
        panel1.add(phoneNumberLabel);
        panel1.add(phoneNumberText);
        panel1.add(countryLabel);
        panel1.add(countryText);

        JButton saveChangeButton = new JButton("Save change");
        saveChangeButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(saveChangeButton);
        panel2.add(cancelButton);

        panel1.setBounds(200,50, 400,400);
        panel2.setBounds(250,480, 300,40);

        add(panel1);
        add(panel2);

        //Set Window
        this.setTitle("Edit Email address");
        this.setLayout(null);
        this.setBounds(500,200,800,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        saveChangeButton.addActionListener(e -> {
            //TODO add your function here.
            fn.user_details.f_name = firstNameText.getText();
            fn.user_details.l_name = lastNameText.getText();
            fn.user_details.street = streetText.getText();
            fn.user_details.apt_num = apartmentText.getText();
            fn.user_details.city = cityText.getText();
            fn.user_details.state = stateText.getText();
            fn.user_details.zip = zipCodeText.getText();
            fn.user_details.country = countryText.getText();
            fn.user_details.phone = phoneNumberText.getText();

            if(fn.updateAddress()) {
                table.setModel(AddressPage.populateAddress(fn));
                modifyButton.setText("Modify");
                this.dispose();
            }
        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End EditAddressPage

/**********************CreditCard page***********************/
class CreditCardPage extends JFrame {

    CreditCardPage(Function fn){
        JLabel yourCreditCardLabel = new JLabel("Your credit cards:");
        yourCreditCardLabel.setFont(new Font("Calibri", Font.BOLD,30));

        JTable table = new JTable();
        table.setModel(populateCreditCards(fn));

        //Set center alignment for table
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int)JTable.CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,995,200);

        yourCreditCardLabel.setBounds(50,0,400,100);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton modifyButton = new JButton("Modify");
        modifyButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.add(addButton);
        panel.add(modifyButton);
        panel.add(deleteButton);
        panel.setBounds(300,350,400,40);

        add(yourCreditCardLabel);
        add(scrollPane);
        add(panel);

        addButton.addActionListener(e -> {
            new AddCreditCardPage(fn, table);
        });

        modifyButton.addActionListener(e -> {
            Integer index = table.getSelectedRow();
            if(index >= 0) {
                CreditCard cc = new CreditCard();
                cc.cc_name = table.getModel().getValueAt(index, 0).toString();
                cc.cc_numb = table.getModel().getValueAt(index, 1).toString();
                cc.cc_exp_date = table.getModel().getValueAt(index, 2).toString();
                cc.cc_cvv = table.getModel().getValueAt(index, 3).toString();
                new EditCreditCardPage(fn, table, cc, index);
            }
            else {
                new showError("No card selected");
                return;
            }
        });

        deleteButton.addActionListener(e -> {
            //TODO add your function here.
            Integer index = table.getSelectedRow();
            if(index >= 0) {
                CreditCard cc = new CreditCard();
                cc.cc_name = table.getModel().getValueAt(index, 0).toString();
                cc.cc_numb = table.getModel().getValueAt(index, 1).toString();
                cc.cc_exp_date = table.getModel().getValueAt(index, 2).toString();
                cc.cc_cvv = table.getModel().getValueAt(index, 3).toString();
                if(fn.deleteCreditcard(cc)){
                    table.setModel(populateCreditCards(fn));
                }
            }
            else
                new showError("No card selected");
        });

        //Set Window
        this.setTitle("Your credit cards");
        this.setBounds(500,200,1000,500);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static DefaultTableModel populateCreditCards(Function fn){
        //Columns of table
        String[] columns = {"Name on card", "Card number",
                "Expiration date", "CVV"};

        //Data of table
        //String data[][] = {{},{},{},{},
        //TODO get data of products from database
        //};

        List<CreditCard> ccList = fn.getCreditCardList();
        String data[][] = new String[ccList.size()][];
        for(int i = 0; i < ccList.size(); i++){
            data[i] = new String[4];
            data[i][0] = ccList.get(i).cc_name;
            data[i][1] = ccList.get(i).cc_numb;
            data[i][2] = ccList.get(i).cc_exp_date;
            data[i][3] = ccList.get(i).cc_cvv;
        }

        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        return tableModel;
    }

}//End CreditCardPage

/**********************AddCreditCard Page GUI***********************/
class AddCreditCardPage extends JFrame {

    AddCreditCardPage(Function fn, JTable ccTable) {

        JLabel nameOnCardLabel = new JLabel("Name on card");
        nameOnCardLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField nameOnCardText = new JTextField(20);
        nameOnCardText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel cardNumberLabel = new JLabel("Card number");
        cardNumberLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField cardNumberText = new JTextField(20);
        cardNumberText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel expirationDateLabel = new JLabel("Expiration date: MM/YY");
        expirationDateLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField expirationDateText = new JTextField(10);
        expirationDateText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel cvvLabel = new JLabel("CVV");
        cvvLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField cvvText = new JTextField(10);
        cvvText.setFont(new Font("Calibri", Font.PLAIN,20));

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(addButton);
        panel2.add(cancelButton);

        nameOnCardLabel.setBounds(50,50,200,40);
        nameOnCardText.setBounds(50,100,150,40);
        cardNumberLabel.setBounds(250,50,200,40);
        cardNumberText.setBounds(250,100,200,40);
        cvvLabel.setBounds(500,50,50,40);
        cvvText.setBounds(500,100,50,40);

        expirationDateLabel.setBounds(600,50,250,40);
        expirationDateText.setBounds(600,100,80,40);

        panel2.setBounds(300,250, 300,40);

        add(nameOnCardLabel);
        add(nameOnCardText);
        add(cardNumberLabel);
        add(cardNumberText);
        add(cvvLabel);
        add(cvvText);
        add(expirationDateLabel);
        add(expirationDateText);
        add(panel2);

        //Set Window
        this.setTitle("Add credit card");
        this.setLayout(null);
        this.setBounds(500,200,900,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        addButton.addActionListener(e -> {
            String nameOnCard = nameOnCardText.getText();
            String cardNumber = cardNumberText.getText();
            String getExpirationDate = expirationDateText.getText();
            String cvv = cvvText.getText();
            if(nameOnCard.equals("") || cardNumber.equals("") || getExpirationDate.equals("") || cvv.equals("")) {
                new showError("Please fill in all fields");
                return;
            }
            else {
                //TODO add your function here.
                CreditCard newcc = new CreditCard();
                newcc.cc_name = nameOnCard;
                newcc.cc_numb = cardNumber;
                newcc.cc_exp_date = getExpirationDate;
                newcc.cc_cvv = cvv;

                if (fn.addCreditcard(newcc)) {
                    ccTable.setModel(CreditCardPage.populateCreditCards(fn));
                    this.dispose();
                }
            }

        });

        cancelButton.addActionListener(e -> this.dispose());
    }

    AddCreditCardPage(Function fn) {

        JLabel nameOnCardLabel = new JLabel("Name on card");
        nameOnCardLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField nameOnCardText = new JTextField(20);
        nameOnCardText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel cardNumberLabel = new JLabel("Card number");
        cardNumberLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField cardNumberText = new JTextField(20);
        cardNumberText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel expirationDateLabel = new JLabel("Expiration date: MM/YY");
        expirationDateLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField expirationDateText = new JTextField(10);
        expirationDateText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel cvvLabel = new JLabel("CVV");
        cvvLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField cvvText = new JTextField(10);
        cvvText.setFont(new Font("Calibri", Font.PLAIN,20));

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(addButton);
        panel2.add(cancelButton);

        nameOnCardLabel.setBounds(50,50,200,40);
        nameOnCardText.setBounds(50,100,150,40);
        cardNumberLabel.setBounds(250,50,200,40);
        cardNumberText.setBounds(250,100,200,40);
        cvvLabel.setBounds(500,50,50,40);
        cvvText.setBounds(500,100,50,40);

        expirationDateLabel.setBounds(600,50,250,40);
        expirationDateText.setBounds(600,100,80,40);

        panel2.setBounds(300,250, 300,40);

        add(nameOnCardLabel);
        add(nameOnCardText);
        add(cardNumberLabel);
        add(cardNumberText);
        add(cvvLabel);
        add(cvvText);
        add(expirationDateLabel);
        add(expirationDateText);
        add(panel2);

        //Set Window
        this.setTitle("Add credit card");
        this.setLayout(null);
        this.setBounds(500,200,900,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        addButton.addActionListener(e -> {
            String nameOnCard = nameOnCardText.getText();
            String cardNumber = cardNumberText.getText();
            String getExpirationDate = expirationDateText.getText();
            String cvv = cvvText.getText();
            if(nameOnCard.equals("") || cardNumber.equals("") || getExpirationDate.equals("") || cvv.equals("")) {
                new showError("Please fill in all fields");
                return;
            }
            else {
                //TODO add your function here.
                CreditCard newcc = new CreditCard();
                newcc.cc_name = nameOnCard;
                newcc.cc_numb = cardNumber;
                newcc.cc_exp_date = getExpirationDate;
                newcc.cc_cvv = cvv;

                if (fn.addCreditcard(newcc)) {
                    this.dispose();
                }
            }

        });

        cancelButton.addActionListener(e -> this.dispose());
    }

}//End AddCreditCardPage

/**********************EditCreditCard Page GUI***********************/
class EditCreditCardPage extends JFrame {

    EditCreditCardPage(Function fn, JTable ccTable, CreditCard cc, int i) {

        JLabel nameOnCardLabel = new JLabel("Name on card");
        nameOnCardLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField nameOnCardText = new JTextField(cc.cc_name);
        nameOnCardText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel cardNumberLabel = new JLabel("Card number");
        cardNumberLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField cardNumberText = new JTextField(cc.cc_numb);
        cardNumberText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel expirationDateLabel = new JLabel("Expiration date: MM/YY");
        expirationDateLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField expirationDateText = new JTextField(cc.cc_exp_date);
        expirationDateText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel cvvLabel = new JLabel("CVV");
        cvvLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField cvvText = new JTextField(cc.cc_cvv);
        cvvText.setFont(new Font("Calibri", Font.PLAIN,20));

        JButton saveChangeButton = new JButton("Save change");
        saveChangeButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(saveChangeButton);
        panel2.add(cancelButton);

        nameOnCardLabel.setBounds(50,50,200,40);
        nameOnCardText.setBounds(50,100,150,40);
        cardNumberLabel.setBounds(250,50,200,40);
        cardNumberText.setBounds(250,100,200,40);
        cvvLabel.setBounds(500,50,50,40);
        cvvText.setBounds(500,100,50,40);

        expirationDateLabel.setBounds(600,50,250,40);
        expirationDateText.setBounds(600,100,80,40);

        panel2.setBounds(300,250, 300,40);

        add(nameOnCardLabel);
        add(nameOnCardText);
        add(cardNumberLabel);
        add(cardNumberText);
        add(cvvLabel);
        add(cvvText);
        add(expirationDateLabel);
        add(expirationDateText);
        add(panel2);

        //Set Window
        this.setTitle("Edit credit card");
        this.setLayout(null);
        this.setBounds(500,200,900,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        saveChangeButton.addActionListener(e -> {
            String nameOnCard = nameOnCardText.getText();
            String cardNumber = cardNumberText.getText();
            String getExpiration = expirationDateText.getText();
            String cvv = cvvText.getText();

            if(nameOnCard.equals("") || cardNumber.equals("") || getExpiration.equals("") || cvv.equals("")) {
                new showError("Please fill in all fields");
                return;
            }
            else {
                //TODO add your function here
                CreditCard newcc = new CreditCard();
                newcc.cc_name = nameOnCard;
                newcc.cc_numb = cardNumber;
                newcc.cc_exp_date = getExpiration;
                newcc.cc_cvv = cvv;

                if (fn.updateCreditcard(newcc, cc)) {
                    //ccTable.setModel(CreditCardPage.populateCreditCards(fn));
                    ccTable.getModel().setValueAt(newcc.cc_name, i, 0);
                    ccTable.getModel().setValueAt(newcc.cc_numb, i, 1);
                    ccTable.getModel().setValueAt(newcc.cc_exp_date, i, 2);
                    ccTable.getModel().setValueAt(newcc.cc_cvv, i, 3);
                    this.dispose();
                }
            }
        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End EditCreditCardPage


/**********************MissingDataPage Page GUI***********************/
class MissingDataPage extends JFrame{
    MissingDataPage(Function fn, String missing){
        if(missing == "adr") {
            JLabel newAddresslabel = new JLabel("No shipping address found");
            newAddresslabel.setFont(new Font("Calibri", Font.BOLD, 30));
            newAddresslabel.setBounds(100, 50, 350, 30);
            add(newAddresslabel);

            JButton newAddressButton = new JButton("Add new address");
            newAddressButton.setFont(new Font("Calibri", Font.PLAIN, 20));
            newAddressButton.setBounds(170, 100, 200, 30);
            add(newAddressButton);

            newAddressButton.addActionListener(e -> {
                new AddAddressPage(fn);
                this.dispose();
            });
        }
        else if(missing == "cc") {
            JLabel newCreditCardlabel = new JLabel("No credit card found");
            newCreditCardlabel.setFont(new Font("Calibri", Font.BOLD, 30));
            newCreditCardlabel.setBounds(140, 50, 350, 30);
            add(newCreditCardlabel);

            JButton newCreditCardButton = new JButton("Add new card");
            newCreditCardButton.setFont(new Font("Calibri", Font.PLAIN, 20));
            newCreditCardButton.setBounds(170, 100, 200, 30);
            add(newCreditCardButton);

            newCreditCardButton.addActionListener(e -> {
                new AddCreditCardPage(fn);

                this.dispose();
            });
        }
        else{

        }

        this.setTitle("Information Missing");
        this.setBounds(600,300,550,240);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }
}//End MissingDataPage


/**********************EmployeeViewMainMenu Page GUI***********************/
class EmployeeViewMainMenuPage extends JFrame {

    EmployeeViewMainMenuPage(Function fn) {

        //Title
        JLabel title = new JLabel("Welcome");
        title.setFont(new Font("Calibri", Font.BOLD,40));
        title.setBounds(400,100,200,40);
        add(title);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu account = new JMenu("Account");
        account.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenuItem loginSecurity = new JMenuItem("Login & security");
        loginSecurity.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenuItem signOut = new JMenuItem("Not you? Sign Out");
        signOut.setFont(new Font("Calibri", Font.PLAIN,20));
        JMenu storeInfo = new JMenu("Store info");
        storeInfo.setFont(new Font("Calibri", Font.PLAIN,20));

        account.add(loginSecurity);
        account.add(signOut);
        menuBar.add(account);
        menuBar.add(storeInfo);

        /**********************Login & security page***********************/
        JLabel loginSecurityLabel = new JLabel("Login & security");
        loginSecurityLabel.setFont(new Font("Calibri", Font.BOLD,30));

        //JLabel idLabel = new JLabel("ID:");
        //idLabel.setFont(new Font("Calibri", Font.BOLD,20));
        //JLabel idText = new JLabel("example");   //TODO get employee's ID from database
        //idText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel emailLabel = new JLabel("E-mail address:");
        emailLabel.setFont(new Font("Calibri", Font.BOLD,20));
        JLabel emailText = new JLabel(fn.user_details.email);   //TODO get employee's email from database
        emailText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Calibri", Font.BOLD,20));
        JLabel passwordText = new JLabel("**********");
        passwordText.setFont(new Font("Calibri", Font.PLAIN,20));

        JButton editEmailButton = new JButton("Edit");
        editEmailButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton editPasswordButton = new JButton("Edit");
        editPasswordButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel editButtonPanel = new JPanel(new GridLayout(2,1,0,60));
        editButtonPanel.add(editEmailButton);
        editButtonPanel.add(editPasswordButton);
        editButtonPanel.setBounds(600,160,100,130);
        editButtonPanel.setVisible(false);

        JPanel loginSecurityPanel = new JPanel(new GridLayout(3,2));
        //loginSecurityPanel.add(idLabel);
        //loginSecurityPanel.add(idText);
        loginSecurityPanel.add(emailLabel);
        loginSecurityPanel.add(emailText);
        loginSecurityPanel.add(passwordLabel);
        loginSecurityPanel.add(passwordText);
        loginSecurityPanel.setVisible(false);

        loginSecurityPanel.setBounds(200,130,400,300);
        loginSecurityLabel.setBounds(50,0,300,100);
        loginSecurityLabel.setVisible(false);

        add(loginSecurityLabel);
        add(loginSecurityPanel);
        add(editButtonPanel);

        editEmailButton.addActionListener(e -> {
            new EditEmployeeEmailPage(fn, emailText);
        });

        editPasswordButton.addActionListener(e -> {
            new EditEmployeePasswordPage(fn);
        });


        /**********************Store information page***********************/
        JLabel storeInfoLabel = new JLabel("Store information:");
        storeInfoLabel.setFont(new Font("Calibri", Font.BOLD,30));
        storeInfoLabel.setVisible(false);
        storeInfoLabel.setBounds(50,0,400,100);
        add(storeInfoLabel);

        JTextField searchText = new JTextField();
        searchText.setFont(new Font("Calibri", Font.PLAIN,20));
        searchText.setVisible(false);
        searchText.setBounds(450,30,300,40);
        add(searchText);

        JButton searchButton = new JButton("Search UPC");
        searchButton.setFont(new Font("Calibri", Font.PLAIN,20));
        searchButton.setVisible(false);
        searchButton.setBounds(770,30,200,40);
        add(searchButton);

        JButton addNewProductButton = new JButton("Add new Product");
        addNewProductButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton addAmountButton = new JButton("Add amount");
        addAmountButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel = new JPanel(new GridLayout(1,2,10,10));
        panel.setBounds(300,550,400,40);
        panel.add(addNewProductButton);
        panel.add(addAmountButton);
        panel.setVisible(false);
        add(panel);

        JTable table = new JTable();
        table.setModel(populateProducts(fn, ""));

        //Set center alignment for table
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment((int)JTable.CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,995,400);
        scrollPane.setVisible(false);
        add(scrollPane);

        loginSecurity.addActionListener(e -> {
            loginSecurityLabel.setVisible(true);
            loginSecurityPanel.setVisible(true);
            editButtonPanel.setVisible(true);

            title.setVisible(false);
            scrollPane.setVisible(false);
            storeInfoLabel.setVisible(false);
            searchText.setVisible(false);
            searchButton.setVisible(false);
            panel.setVisible(false);
        });

        storeInfo.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                title.setVisible(false);
                loginSecurityLabel.setVisible(false);
                loginSecurityPanel.setVisible(false);
                editButtonPanel.setVisible(false);

                scrollPane.setVisible(true);
                storeInfoLabel.setVisible(true);
                searchText.setVisible(true);
                searchButton.setVisible(true);
                panel.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                //Don't add code here
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                //Don't add code here
            }
        });

        searchButton.addActionListener(e -> {
            String searchTxt = searchText.getText();
            table.setModel(populateProducts(fn, searchTxt));
        });

        addNewProductButton.addActionListener(e -> {
            new AddNewProductPage(fn, table);
        });

        addAmountButton.addActionListener(e -> {
            Integer index = table.getSelectedRow();
            if(index >= 0) {
                Product p = new Product();
                p.upc = table.getModel().getValueAt(index, 1).toString();
                p.quantity = Integer.parseInt(table.getModel().getValueAt(index, 5).toString());
                p.storeName = table.getModel().getValueAt(index, 0).toString();
                new AddAmountPage(fn, table, p, index);
            }
            else
                new showError("No product selected");
        });

        signOut.addActionListener(e -> {
            //setVisible(false);
            new LoginPage();
            this.dispose();
        });

        //Set Window
        this.setTitle("Main menu");
        this.setJMenuBar(menuBar);
        this.setBounds(500,200,1000,700);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static DefaultTableModel populateProducts(Function fn, String searchTxt){
        //Columns of table
        String[] columns = {"Name of store", "UPC", "Name of product", "Brand", "Price per unit",
                "Quantity", "Size"};

        //Data of table
        //String data[][] = {{},{},{},{},
        //TODO get data of products from database
        //};

        List<Product> prodList = fn.getInventory(searchTxt);
        String data[][] = new String[prodList.size()][];
        for(int i = 0; i < prodList.size(); i++){
            data[i] = new String[7];
            data[i][0] = prodList.get(i).storeName;
            data[i][1] = prodList.get(i).upc;
            data[i][2] = prodList.get(i).prodName;
            data[i][3] = prodList.get(i).brand;
            data[i][4] = prodList.get(i).price.toString();
            data[i][5] = prodList.get(i).quantity.toString();
            data[i][6] = prodList.get(i).size;
        }

        // Creates Table
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        return tableModel;
    }
}//End EmployeeViewMainMenuPage

/**********************EditEmployeeEmail Page GUI***********************/
class EditEmployeeEmailPage extends JFrame {

    EditEmployeeEmailPage(Function fn, JLabel emailText){

        JLabel oldEmailLabel = new JLabel("Old email address:");
        oldEmailLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel oldEmailText = new JLabel(fn.user_details.email);   //TODO  get employee's email from database
        oldEmailText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel newEmailLabel = new JLabel("New email address:");
        newEmailLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField newEmailText = new JTextField(25);
        newEmailText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel confirmEmailLabel = new JLabel("Re-enter new email:");
        confirmEmailLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField confirmEmailText = new JTextField(25);
        confirmEmailText.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(3, 2, 10, 10));
        panel1.add(oldEmailLabel);
        panel1.add(oldEmailText);
        panel1.add(newEmailLabel);
        panel1.add(newEmailText);
        panel1.add(confirmEmailLabel);
        panel1.add(confirmEmailText);

        JButton saveChangeButton = new JButton("Save change");
        saveChangeButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(saveChangeButton);
        panel2.add(cancelButton);


        panel1.setBounds(100,100, 500,150);
        panel2.setBounds(200,300, 300,40);

        add(panel1);
        add(panel2);

        //Set Window
        this.setTitle("Edit Email");
        this.setLayout(null);
        this.setBounds(500,200,700,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        saveChangeButton.addActionListener(e -> {
            String newEmail = newEmailText.getText();
            String confirmEmail = confirmEmailText.getText();

            //TODO add your function here. Exit employee's email
            if(newEmail.equals("") || confirmEmail.equals("")){
                new showError("Please fill in all fields");
                return;
            }
            if(Function.isValidEmailAddress(newEmail) && Function.isValidEmailAddress(confirmEmail)) {
                if (!confirmEmail.equals(newEmail)) {
                    new showError("Both email fields should be the same");
                    return;
                }
                if (fn.updateEmail_emp(newEmail)) {
                    new showSuccess("Email updated");
                    emailText.setText(newEmail);
                    //setVisible(false);
                    this.dispose();
                }
            }
            else{
                new showError("Invalid email");
                return;
            }
        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End EditEmployeeEmailPage

/**********************EditEmployeePassword Page GUI***********************/
class EditEmployeePasswordPage extends JFrame {

    EditEmployeePasswordPage(Function fn){

        JLabel currentPasswordLabel = new JLabel("Current password:");
        currentPasswordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField currentPasswordText = new JPasswordField(25);
        currentPasswordText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel newPasswordLabel = new JLabel("New password:");
        newPasswordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField newPasswordText = new JPasswordField(25);
        newPasswordText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel confirmPasswordLabel = new JLabel("Re-enter password:");
        confirmPasswordLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField confirmPasswordText = new JPasswordField(25);
        confirmPasswordText.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(3, 2, 10, 10));
        panel1.add(currentPasswordLabel);
        panel1.add(currentPasswordText);
        panel1.add(newPasswordLabel);
        panel1.add(newPasswordText);
        panel1.add(confirmPasswordLabel);
        panel1.add(confirmPasswordText);

        JButton saveChangeButton = new JButton("Save change");
        saveChangeButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(saveChangeButton);
        panel2.add(cancelButton);

        panel1.setBounds(100,100, 500,150);
        panel2.setBounds(200,300, 300,40);

        add(panel1);
        add(panel2);

        //Set Window
        this.setTitle("Edit Password");
        this.setLayout(null);
        this.setBounds(500,200,700,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        saveChangeButton.addActionListener(e -> {
            String currentPassword = currentPasswordText.getText();
            String newPassword = newPasswordText.getText();
            String confirmPassword = confirmPasswordText.getText();

            //TODO add your function here.  Exit employee's password

            if(currentPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")){
                new showError("Please fill in all fields");
                return;
            }
            if(!currentPassword.equals(fn.user_details.password)){
                new showError("Current password is incorrect");
                return;
            }
            if(!newPassword.equals(confirmPassword)){
                new showError("Both password fields should be the same");
                return;
            }
            if (fn.updatePassword_emp(newPassword)) {
                new showSuccess("Password updated");
                setVisible(false);
            }

        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End EditEmployeePasswordPage

/**********************AddNewProduct Page GUI***********************/
class AddNewProductPage extends JFrame {

    AddNewProductPage(Function fn, JTable pTable){

        JLabel upcLabel = new JLabel("UPC:");
        upcLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField upcText = new JTextField(20);
        upcText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel nameOfProductLabel = new JLabel("Name of product:");
        nameOfProductLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField nameOfProductText = new JTextField(15);
        nameOfProductText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel brandLabel = new JLabel("Brand:");
        brandLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField brandText = new JTextField(20);
        brandText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel pricePerUnitLabel = new JLabel("Price per unit:");
        pricePerUnitLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField pricePerUnitText = new JTextField(20);
        pricePerUnitText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField quantityText = new JTextField(20);
        quantityText.setFont(new Font("Calibri", Font.PLAIN,20));
        JLabel sizeLabel = new JLabel("Size:");
        sizeLabel.setFont(new Font("Calibri", Font.PLAIN,20));


        //String [] size = {"XS","S","M","L","XL","XXL"};
        JComboBox sizeComBox = new JComboBox(getSizeList());
        sizeComBox.setFont(new Font("Calibri", Font.PLAIN,20));

        JLabel nameOfStoreLabel = new JLabel("Name of store:");
        nameOfStoreLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        //JTextField nameOfStoreText = new JTextField(20);
        //nameOfStoreText.setFont(new Font("Calibri", Font.PLAIN,20));
        JComboBox selectNameOfStoreComboBox = new JComboBox(getStoreList());
        selectNameOfStoreComboBox.setFont(new Font("Calibri", Font.PLAIN,20));

        JLabel vendorLabel = new JLabel("Vendor:");
        vendorLabel.setFont(new Font("Calibri", Font.PLAIN,20));

        //String[] vendor = {"Liam","Mason","Jacob","William","Ethan",
        //        "James", "Alexander"};   //TODO get vendors from database

        JComboBox selectVendorComboBox = new JComboBox(getVenorList());
        selectVendorComboBox.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(8, 2, 10, 10));
        panel1.add(upcLabel);
        panel1.add(upcText);
        panel1.add(nameOfProductLabel);
        panel1.add(nameOfProductText);
        panel1.add(brandLabel);
        panel1.add(brandText);
        panel1.add(pricePerUnitLabel);
        panel1.add(pricePerUnitText);
        panel1.add(quantityLabel);
        panel1.add(quantityText);
        panel1.add(sizeLabel);
        panel1.add(sizeComBox);
        panel1.add(nameOfStoreLabel);
        panel1.add(selectNameOfStoreComboBox);
        panel1.add(vendorLabel);
        panel1.add(selectVendorComboBox);

        JButton addButton = new JButton("Add product");
        addButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(addButton);
        panel2.add(cancelButton);

        panel1.setBounds(200,50, 400,300);
        panel2.setBounds(250,480, 300,40);

        add(panel1);
        add(panel2);

        //Set Window
        this.setTitle("Add new product");
        this.setLayout(null);
        this.setBounds(500,200,800,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        addButton.addActionListener(e -> {
            String upc = upcText.getText();
            String nameOfProduct = nameOfProductText.getText();
            String brand = brandText.getText();
            String pricePerUnit = pricePerUnitText.getText();
            String quantity = quantityText.getText();
            String getSize = getSelectedSize(sizeComBox.getSelectedIndex());
            String nameOfStore = getSelectedStore(selectNameOfStoreComboBox.getSelectedIndex());
            String getVendor = getSelectedVendor(selectVendorComboBox.getSelectedIndex());

            //TODO add your code here.
            if(upc.equals("") || nameOfProduct.equals("") || brand.equals("") || pricePerUnit.equals("") ||
                    quantity.equals("") || getSize.equals("") || nameOfStore.equals("") || getVendor.equals("")){
                new showError("Please fill in all fields");
                return;
            }
            Product p = new Product();
            p.upc = upc;
            p.prodName = nameOfProduct;
            p.brand = brand;
            p.price = Float.parseFloat(pricePerUnit);
            p.quantity = Integer.parseInt(quantity);
            p.size = getSize;
            p.storeName = nameOfStore;
            p.vendor = getVendor;

            if(fn.product_isUPCexist(upc)){
                new showError("UPC already exists");
                return;
            }
            else {
                Integer storage = fn.checkStoreSpace(p.storeName);
                if (storage >= p.quantity) {
                    if (fn.createnew_product(p)) {
                        //setVisible(false);
                        pTable.setModel(EmployeeViewMainMenuPage.populateProducts(fn, ""));
                        this.dispose();
                    }
                } else {
                    new showError("Storage space remaining: " + storage);
                }
            }
        });

        cancelButton.addActionListener(e -> this.dispose());
    }
    String[] vendors;
    private String[] getVenorList(){
        List vendorsList = Function.getVendorList();

        vendors = new String[vendorsList.size()];
        for(int i = 0; i < vendorsList.size(); i++){
            vendors[i] = vendorsList.get(i).toString();
        }
        return vendors;
    }

    private String getSelectedVendor(int index){
        return vendors[index];
    }
    String[] stores;
    private String[] getStoreList(){
        List storeList = Function.getStoreList();

        stores = new String[storeList.size()];
        for(int i = 0; i < storeList.size(); i++){
            stores[i] = storeList.get(i).toString();
        }
        return stores;
    }
    private String getSelectedStore(int index){
        return stores[index];
    }
    String[] sizes;
    private String[] getSizeList(){
        List storeList = Function.getSizeList();

        sizes = new String[storeList.size()];
        for(int i = 0; i < storeList.size(); i++){
            sizes[i] = storeList.get(i).toString();
        }
        return sizes;
    }
    private String getSelectedSize(int index){
        return sizes[index];
    }

}//End AddNewProductPage

/**********************AddAmount Page GUI***********************/
//Add amount of existed products
class AddAmountPage extends JFrame {

    AddAmountPage(Function fn, JTable pTable, Product prod, int i){

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Calibri", Font.PLAIN,20));
        JTextField amountText = new JTextField(20);
        amountText.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel1 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel1.add(amountLabel);
        panel1.add(amountText);
        panel1.setBounds(200,50, 200,40);
        add(panel1);

        JButton addButton = new JButton("Confirm");
        addButton.setFont(new Font("Calibri", Font.PLAIN,20));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.add(addButton);
        panel2.add(cancelButton);
        panel2.setBounds(150,150, 300,40);
        add(panel2);

        //Set Window
        this.setTitle("Add amount");
        this.setLayout(null);
        this.setBounds(500,200,600,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        addButton.addActionListener(e -> {
            if(!amountText.getText().equals("")){
                Integer amount = Integer.parseInt(amountText.getText());
                prod.quantity = prod.quantity + amount;
                //TODO add your code here.
                if(fn.updateQuantity(prod)) {
                    pTable.getModel().setValueAt(prod.quantity, i, 5);
                    this.dispose();
                }

            }
            else{
                new showError("Invalid");
            }
        });

        cancelButton.addActionListener(e -> this.dispose());
    }
}//End AddAmountPage


/**********************Error message***********************/
//Display error message popup
class showError extends JFrame{
    showError(String text){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null,label,"Error",JOptionPane.ERROR_MESSAGE);
    }
}//End Error message

/**********************Success message***********************/
//Display success message popup
class showSuccess extends JFrame{
    showSuccess(String text){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null,label,"Success",JOptionPane.PLAIN_MESSAGE);
    }
}//End Success message


